package model;

import java.io.IOException;
import java.util.ArrayList;

import helper.WebsiteReader;

public class Account {
	
	//Attributes
	private int accId; //number of the account - ex: acc maker 1, 2, 3 - must be auto-incremented
	private String accName; //login
	private String accPassword; //password
	private ArrayList<Character> characters;
	private int amountOfChars;
	private String accStatus; // is premium account?

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

	public String getAccStatus(){
		/*checking if one character of this account is premium
		* in website, "status" is Premium Account/Free Account
		*/
		if (! this.characters.isEmpty()){
			WebsiteReader webpageReader = new WebsiteReader(this.characters.get(0).getName(), "Status");
			try {
				this.setAccStatus(webpageReader.characterInfoReader());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return accStatus;
		}
		return "Free Account";
	}

	public void setAccStatus(String accStatus) {
		this.accStatus = accStatus;
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
		ArrayList<Character> charactersAvaliableToHunt = new ArrayList<Character>();
		for (int i=0; i < this.characters.size(); i++){
			if (this.characters.get(i).getStamina() > 22){
				charactersAvaliableToHunt.add(this.characters.get(i));
			}
		}
		return charactersAvaliableToHunt;
	}
	
	public String getCharactersNames(){
		return this.getCharacters().toString();
	}

	public int getAmountOfChars() {
		return amountOfChars;
	}

	public void setAmountOfChars(int amountOfChars) {
		this.amountOfChars = amountOfChars;
	}
	
	
}
