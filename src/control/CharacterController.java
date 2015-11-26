package control;

import java.io.IOException;
import java.util.ArrayList;

import database.SimulatedDataBase;
import exception.AccountNotFoundException;
import model.Account;
import model.Character;
import view.CharacterConfirmDeletionWindow;
import view.CharacterEditInfoWindow;
import view.RegisterCharacterWindow;

public class CharacterController {
	
	private SimulatedDataBase sdb;
	private RegisterCharacterWindow registerCharacterWindow;
	private AccountController accountController;
	private CharacterEditInfoWindow characterEditInfoWindow;
	private CharacterConfirmDeletionWindow characterConfirmDeletionWindow;
	
	
	public CharacterController(SimulatedDataBase sdb, AccountController accountController){
		this.sdb = sdb;
		this.accountController = accountController;
	}

	public ArrayList<Account> getAccounts() {
		return this.sdb.getAccounts();
	}
	
	public Account getAccountByID(int accountComboBoxValue) {
		try {
			return this.sdb.getAccountByID(accountComboBoxValue);
		} catch (AccountNotFoundException e) {
			e.getMessage();
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean isRepeatedCharacterName(String characterName) {
		return this.sdb.isRepeatedCharacterName(characterName);
	}
	
	public void callRefreshTables() {
		try {
			this.accountController.refreshTables();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Registering, deleting and updating
	public void registerNewCharacter(Account account, String characterName, String vocation,
									int currentStamina, int bankBalance) throws AccountNotFoundException {
		Character newCharacter = new Character(account, characterName, vocation ,currentStamina, bankBalance);
		this.sdb.saveCharacter(account.getAccId(), newCharacter);
	}
	
	public void updateCharacter(Account account, String characterName, String vocation,
			int currentStamina, int bankBalance) {
		Character editedCharacter = new Character(account, characterName, vocation, currentStamina, bankBalance);
		try {
			this.sdb.saveCharacter(account.getAccId(), editedCharacter);
		} catch (AccountNotFoundException e) {
			e.getMessage();
			e.printStackTrace();
		}
	}
	
	public void deleteCharacter(int characterAccID, String characterName) {
		this.sdb.deleteCharacter(characterAccID, characterName);
	}
	

	//Creating windows
	public void createRegisterCharacterWindow() {
		this.registerCharacterWindow = new RegisterCharacterWindow(this, this.accountController);
	}
	
	public void createCharacterConfirmDeletionWindow(int characterAccID, String characterName) {
		this.characterConfirmDeletionWindow = new CharacterConfirmDeletionWindow(this, characterAccID, characterName);
	}
	
	public void createCharacterEditInfoWindow(int characterAccID, String characterName) {
		this.characterEditInfoWindow = new CharacterEditInfoWindow(this, characterAccID, characterName);
	}
	
	//Closing windows
	public void closeRegisterCharacterWindow() {
		this.registerCharacterWindow.setVisible(false);
		this.registerCharacterWindow = null;	
	}

	public void closeCharacterEditInfoWindow() {
		this.characterEditInfoWindow.setVisible(false);
		this.characterEditInfoWindow = null;
	}
	
	public void closeCharacterConfirmDeletionWindow() {
		this.characterConfirmDeletionWindow.setVisible(false);
		this.characterConfirmDeletionWindow = null;
	}
	
	
}
