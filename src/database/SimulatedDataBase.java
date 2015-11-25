package database;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

import exception.AccountNotFoundException;
import exception.CharacterNotFoundException;
import helper.Reader;
import helper.Writer;
import model.Account;
import model.Character;

public class SimulatedDataBase {
	
	public ArrayList<Account> accounts;
	public ArrayList<Character> characters;
	public String separatorToSplit = "/---/";
	

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
		while( line != null){
			String [] word = line.split(separatorToSplit);
				this.accounts.add(new Account(Integer.parseInt(word[0]), word[1], word[2]));
				line = reader.readLine();
		}		
			//JOptionPane.showMessageDialog(null, this.accounts);
	}
	
	public void populateCharacters(){
		Reader reader = new Reader("characters.txt");
		String line = reader.readLine();
		while( line != null){
			String [] word = line.split(separatorToSplit);
				try {
					this.characters.add(new Character(this.getAccountByID(Integer.parseInt(word[0])), word[1], Integer.parseInt(word[2]), word[3], Integer.parseInt(word[4]), Integer.parseInt(word[5])));
				} catch (NumberFormatException | AccountNotFoundException e) {
					e.getMessage();
					e.printStackTrace();
				}
				line = reader.readLine();
		}		
			//JOptionPane.showMessageDialog(null, this.characters);
	}
	
	public void saveAccount(Account acc){
		Writer writer = new Writer("accounts.txt");
		writer.write(acc.getAccId() + separatorToSplit + acc.getAccName() + separatorToSplit + acc.getAccPassword());
		this.accounts.add(acc);
	}
	
	public void saveCharacter(int accountID, Character character) throws AccountNotFoundException {
		Writer writer = new Writer("characters.txt");
		try {
			writer.write(Integer.toString(accountID) + separatorToSplit + character.getName() + separatorToSplit + character.getLevel() + separatorToSplit + character.getVocation() + separatorToSplit + character.getStamina() + separatorToSplit + character.getBankBalance());
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.getAccountByID(accountID).addCharacter(character);
		this.characters.add(character);
	}
	

	public ArrayList<Account> getAccounts() {
		return accounts;
	}

	public ArrayList<Character> getCharacters() {
		return characters;
	}
	
	public Account getAccountByID(int accID) throws AccountNotFoundException {
		for (int i = 0; i < this.accounts.size(); i++){
			if(this.accounts.get(i).getAccId() == accID){
				return this.accounts.get(i);
			}
		}
		throw new AccountNotFoundException();
	}
	
	//comparing array of character's account with sdb array of characters, if equals, will be deleted.
	public void deleteAccountCharacters(int accID){
		ArrayList<Character> accountCharacters = this.getCharactersByAccID(accID);
		for (int i = 0; i < this.characters.size(); i++) {
			for(int j = 0; j < accountCharacters.size(); j++){
				if(this.characters.get(i).equals(accountCharacters.get(j))){
					this.characters.remove(i);
				}
			}
		}
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

	public void deleteAccount(int accID) throws AccountNotFoundException {
		this.accounts.remove(this.getAccountByID(accID));
		this.deleteAccountCharacters(accID);
	}
	
	public void deleteCharacter(int characterAccID, String characterName) {
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
	
	
	
	
}
