package control;

import java.util.ArrayList;

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

	public void registerNewCharacter(Account account, String characterName,
									String vocation, int currentStamina, int currentBankBalance) throws AccountNotFoundException {
		Character newCharacter = new Character(account, characterName, vocation, currentStamina, currentBankBalance);
		this.sdb.saveCharacter(account.getAccId(), newCharacter);
	}

	public Account getAccountByID(int accountComboBoxValue) throws AccountNotFoundException {
		return this.sdb.getAccountByID(accountComboBoxValue);
	}
	
	public void closeRegisterCharacterWindow() {
		this.registerCharacterWindow.setVisible(false);
		this.registerCharacterWindow = null;	
	}

}
