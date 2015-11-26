package database;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import exception.AccountNotFoundException;
import exception.CharacterNotFoundException;
import exception.LineNotFoundException;
import helper.Reader;
import helper.Writer;
import model.Account;
import model.Character;

public class SimulatedDataBase {
	
	public ArrayList<Account> accounts;
	public ArrayList<Character> characters;
	public String separatorToSplit = ";;";
	

	public SimulatedDataBase(){
		this.accounts = new ArrayList<Account>();
		this.characters = new ArrayList<Character>();
		this.populateSimulatedDataBase();
	}

	public void populateSimulatedDataBase(){
		this.populateAccounts();
		this.populateCharacters();
	}
	
	public void populateAccounts(){
		Reader reader = new Reader("accounts.txt");
		String line = reader.readLine();
		while(line != null){
			String [] word = line.split(separatorToSplit);
				if(!line.isEmpty()){
					this.accounts.add(new Account(Integer.parseInt(word[0]), word[1], word[2]));
				}
				line = reader.readLine();
		}		
			//JOptionPane.showMessageDialog(null, this.accounts);
	}
	
	public void populateCharacters(){
		Reader reader = new Reader("characters.txt");
		String line = reader.readLine();
		while(line != null){
			String [] word = line.split(separatorToSplit);
				Character character;
				if(! line.isEmpty()){
					try {
						character = new Character(this.getAccountByID(Integer.parseInt(word[0])), word[1], Integer.parseInt(word[2]), word[3], Integer.parseInt(word[4]), Integer.parseInt(word[5]));
						this.getAccountByID(Integer.parseInt(word[0])).addCharacter(character);
						this.characters.add(character);
					} catch (NumberFormatException | AccountNotFoundException e) {
						e.getMessage();
						e.printStackTrace();
					}
				}
				line = reader.readLine();
		}		
			//JOptionPane.showMessageDialog(null, this.characters);
	}
	
	//SAVES
	
	public void saveAccount(Account acc){
		Writer writer = new Writer("accounts.txt");
		writer.write(acc.getAccId() + separatorToSplit + acc.getAccName() + separatorToSplit + acc.getAccPassword());
		writer.close();
		this.accounts.add(acc);
	}
	
	public void saveCharacter(int accountID, Character character) throws AccountNotFoundException {
		Writer writer = new Writer("characters.txt");
		try {
			writer.write(Integer.toString(accountID) + separatorToSplit + character.getName() + separatorToSplit + character.getLevel() + separatorToSplit + character.getVocation() + separatorToSplit + character.getStamina() + separatorToSplit + character.getBankBalance());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.getAccountByID(accountID).addCharacter(character);
		this.characters.add(character);
	}
	
	//DELETES
	
	
	public void deleteAccount(int accID) throws AccountNotFoundException {
		try {
			this.updateFile(this.getAccountLineByID(accID), "accounts.txt");
		} catch (LineNotFoundException e) {
			e.getMessage();
			e.printStackTrace();
		}
		this.deleteAccountCharacters(accID);
		this.accounts.remove(this.getAccountByID(accID));
	}
	
	//comparing array of character's account with sdb array of characters, if equals, will be deleted.
	public void deleteAccountCharacters(int accID){
		ArrayList<Character> accountCharacters = this.getCharactersByAccID(accID);
		for (int i = 0; i < this.characters.size(); i++) {
			for(int j = 0; j < accountCharacters.size(); j++){
				if(this.characters.get(i).equals(accountCharacters.get(j))){
					try {
						this.updateFile(this.getCharacterLineByName(this.characters.get(i).getName()), "characters.txt");
					} catch (LineNotFoundException e) {
						e.getMessage();
						e.printStackTrace();
					}
					this.characters.remove(i);
				}
			}
		}
	}
	
	public void deleteCharacter(int characterAccID, String characterName) {
		try {
			this.updateFile(this.getCharacterLineByName(characterName), "characters.txt");
		} catch (LineNotFoundException e) {
			e.getMessage();
			e.printStackTrace();
		}
		try {
			this.getAccountByID(characterAccID).removeCharacter(this.getCharacterByName(characterName));
		} catch (AccountNotFoundException | CharacterNotFoundException e) {
			e.getMessage();
			e.printStackTrace();
		}
		try {
			this.characters.remove(this.getCharacterByName(characterName));
		} catch (CharacterNotFoundException e) {
			e.getMessage();
			e.printStackTrace();
		}
	}
	
	
	
	
	//GETTERS
	

	public ArrayList<Account> getAccounts() {
		return accounts;
	}

	public ArrayList<Character> getCharacters() {
		return characters;
	}
	
	public boolean isRepeatedAccID(int accID) {
		for(int i = 0; i < this.accounts.size(); i++){
			if(this.accounts.get(i).getAccId() == accID){
				return true;
			}
		}
		return false;
	}
	
	public boolean isRepeatedCharacterName(String characterName) {
		for(int i = 0; i < this.characters.size(); i++){
			if(this.characters.get(i).getName().equalsIgnoreCase(characterName)){
				return true;
			}
		}
		return false;
	}
	
	public Account getAccountByID(int accID) throws AccountNotFoundException {
		for (int i = 0; i < this.accounts.size(); i++){
			if(this.accounts.get(i).getAccId() == accID){
				return this.accounts.get(i);
			}
		}
		throw new AccountNotFoundException();
	}
	
	//To get the characters from specific account
	public ArrayList<Character> getCharactersByAccID(int accID){
		ArrayList<Character> accountCharacters = new ArrayList<Character>();
		for(int i = 0; i < this.characters.size(); i++){
			if(this.characters.get(i).getCharAcc().getAccId() == accID){
				accountCharacters.add(this.characters.get(i));
			}
		}
		return accountCharacters;
	}
	
	public Character getCharacterByName(String characterName) throws CharacterNotFoundException{
		for(int i = 0; i <  this.characters.size(); i++){
			if(this.characters.get(i).getName().equals(characterName)){
				return this.characters.get(i);
			}
		}
		throw new CharacterNotFoundException();
	}

	public String getAccountLineByID(int accID) throws LineNotFoundException{
			Reader reader = new Reader("accounts.txt");
			String line = reader.readLine();
			while(line != null){
				String[] accInfo = line.split(";;");
				if(!line.isEmpty()){
					if (Integer.parseInt(accInfo[0]) == accID) {
						return line;
					}else{
						line = reader.readLine();
					}
				}
			}
			throw new LineNotFoundException();
	}
	
	public String getCharacterLineByName(String characterName) throws LineNotFoundException{
		Reader reader = new Reader("characters.txt");
		String line = reader.readLine();
		while(line != null){
			if(!line.isEmpty()){
				String[] charInfo = line.split(";;");
				if (charInfo[1].equals(characterName)) {
					return line;
				}else{
					line = reader.readLine();
				}
			}
		}
		throw new LineNotFoundException();
}
	
	public void updateFile(String lineToRemove, String fileName){
		File inputFile = new File(fileName);
		File tempFile = new File("fileTemp.txt");
		
		Reader reader = new Reader(fileName);
		Writer writer = new Writer("fileTemp.txt");
		
		String currentLine;

		while((currentLine = reader.readLine()) != null) {
		    // trim newline when comparing with lineToRemove
		    String trimmedLine = currentLine.trim();
		    if(trimmedLine.equals(lineToRemove)) continue;
		    writer.write(currentLine);
		}
		writer.close();
		boolean successful = tempFile.renameTo(inputFile);
	}
	
	
	
}
