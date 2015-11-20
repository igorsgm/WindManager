package model;

import java.util.ArrayList;
import model.Character;

public class Account {
	
	//Attributes
	private int accId; //number of the account - ex: acc maker 1, 2, 3 - must be auto-incremented
	private String accName; //login
	private String accPassword; //password
	private ArrayList<Character> characters;
	private ArrayList<Character> charactersAvaliableToHunt; //ammount of avaliable chars to hunt: stamina > x
	private boolean premium; // is premium account?

	//Constructor method
	public Account(int accId, String accName, String accPassword){
		this.setAccId(accId);
		this.setAccName(accName);
		this.setAccPassword(accPassword);
		this.characters = new ArrayList<Character>();
	}

	//Getters and setters
	public int getAccId() {
		return accId;
	}
	public void setAccId(int accId) {
		this.accId = accId;
	}
	public String getAccName() {
		return accName;
	}
	public void setAccName(String accName) {
		this.accName = accName;
	}
	public String getAccPassword() {
		return accPassword;
	}
	public void setAccPassword(String accPassword) {
		this.accPassword = accPassword;
	}
	public boolean isPremium() {
		return premium;
	}

	public void setPremium(boolean premium) {
		this.premium = premium;
	}

	public ArrayList<Character> getCharacters() {
		return characters;
	}

	public void setCharacters(ArrayList<Character> characters) {
		this.characters = characters;
	}
	
	public void addCharacter(Character character) {
		this.characters.add(character);
	}
	public void removeCharacter(Character character) {
		this.characters.remove(character);
	}

	public ArrayList<Character> getCharactersAvaliableToHunt() {
		return charactersAvaliableToHunt;
	}

	public void setCharactersAvaliableToHunt(ArrayList<Character> charactersAvaliableToHunt) {
		this.charactersAvaliableToHunt = charactersAvaliableToHunt;
	}
	
	public String getCharactersNames(){
		return this.getCharacters().toString();
	}
	
	
}