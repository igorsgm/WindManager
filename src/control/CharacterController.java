package control;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import database.SimulatedDataBase;
import exception.AccountNotFoundException;
import model.Account;
import model.Character;
import view.RegisterCharacterWindow;

public class CharacterController {
	//Attributes
	private SimulatedDataBase sdb;
	private RegisterCharacterWindow registerCharacterWindow;
	private AccountController accountController;
	
	//Constructors
	public CharacterController(SimulatedDataBase sdb, AccountController accountController){
		this.sdb = sdb;
		this.accountController = accountController;
	}

	public ArrayList<Account> getAccounts() {
		return this.sdb.getAccounts();
	}
	
	//Methods
	public void createRegisterCharacterWindow() {
		this.registerCharacterWindow = new RegisterCharacterWindow(this, this.accountController);
	}

	public void registerNewCharacter(Account account, String characterName, String vocation,
									int currentStamina, int startBankBalance) throws AccountNotFoundException {
		Character newCharacter = new Character(account, characterName, vocation, currentStamina, startBankBalance);
		this.sdb.saveCharacter(account.getAccId(), newCharacter);
	}

	public Account getAccountByID(int accountComboBoxValue) throws AccountNotFoundException {
		return this.sdb.getAccountByID(accountComboBoxValue);
	}
	
	public void closeRegisterCharacterWindow() {
		this.registerCharacterWindow.setVisible(false);
		this.registerCharacterWindow = null;	
	}
	
	public String WebPageInfoReader(String characterName, String fieldRequired) throws IOException{
		//Connecting to de website
		Document doc = Jsoup.connect("http://www.tibia.com/community/?subtopic=characters&name=" + characterName).userAgent("Mozilla").get();
		//Searching for some specific Field, eg "Level:"
		Elements result = doc.select("*:containsOwn(" + fieldRequired +":)");
		//Getting results and returning the next elemen, that is the information
		for( Element e : result )
		{
		    String value = e.nextElementSibling().text(); /* text of next element */
		    return value;
		}
		throw new IOException();
	}
	
	public String WebPageCharacterStatusReader(String characterWorld, String characterName) throws IOException{
		Document doc = Jsoup.connect("https://secure.tibia.com/community/?subtopic=worlds&world=" + characterWorld).userAgent("Mozilla").get();
		//Checking if in the specific page, has some element containing playName as text
		if (doc.getElementsContainingOwnText(characterName).hasText()){
			return "online";
		}else{
			return "offline";
		}
	}

}
