package database;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

import exception.AccountNotFoundException;
import exception.CharacterNotFoundException;
import model.Account;
import model.Character;

public class SimulatedDataBase {
	
	public ArrayList<Account> accounts;
	public ArrayList<Character> characters;
	
	//Constructors
	public SimulatedDataBase(){
		this.accounts = new ArrayList<Account>();
		this.characters = new ArrayList<Character>();
	}
	
	//Methods
	public void saveAccount(Account acc){
		this.accounts.add(acc);
	}
	
	public void saveCharacter(int accountID, Character character) throws AccountNotFoundException {
		this.getAccountByID(accountID).addCharacter(character);
		this.characters.add(character);
	}
	
	//Getters
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

	public void deleteAccount(int accID) throws AccountNotFoundException {
		this.accounts.remove(this.getAccountByID(accID));
		this.deleteAccountCharacters(accID);
	}
}
