package control;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import database.SimulatedDataBase;
import exception.AccountNotFoundException;
import exception.CharacterNotFoundException;
import model.Account;
import model.Character;
import view.ConfirmDeletionWindow;
import view.MainWindow;
import view.RegisterAccountWindow;

public class AccountController {
	//Attributes
	private SimulatedDataBase sdb;
	private RegisterAccountWindow registerAccountWindow;
	private CharacterController characterController;
	private MainWindow mainWindow;
	private ConfirmDeletionWindow confirmDeletionWindow;
	
	//Constructors
	public AccountController(){
		this.sdb = new SimulatedDataBase();
		this.characterController = new CharacterController(this.sdb, this);
		this.mainWindow = new MainWindow(this);
	}
	
	public ArrayList<Account> getAccounts() {
		return this.sdb.getAccounts();
	}

	//Methods	
	public void createRegisterAccountWindow() {
		this.registerAccountWindow = new RegisterAccountWindow(this);
	}
	
	public void createRegisterCharacterWindow() {
		this.characterController.createRegisterCharacterWindow();
	}

	public void createConfirmDeletionWindow(int accID) {
		this.confirmDeletionWindow = new ConfirmDeletionWindow(this, accID);
	}

	public void registerNewAccount(int accountID, String accountName, String password) {
		Account newAccount = new Account(accountID, accountName, password);
		this.sdb.saveAccount(newAccount);
	}

	public void deleteAccount(int accID) throws AccountNotFoundException, CharacterNotFoundException {
		this.sdb.deleteAccount(accID);
	}
	
	public void closeRegisterAccountWindow() {
		this.registerAccountWindow.setVisible(false);
		this.registerAccountWindow = null;
	}

	public void closeConfirmDeletionWindow() {
		this.confirmDeletionWindow.setVisible(false);
		this.confirmDeletionWindow = null;
	}
	
	public void populateAccountTable() {
		ArrayList<Account> accounts = this.getAccounts();
		Object[][] contents = new Object[accounts.size()][5];
			for(int i = 0; i < accounts.size(); i++) {
				contents[i][0] = accounts.get(i).getAccId();
				contents[i][1] = accounts.get(i).getAccName(); 
				contents[i][2] = accounts.get(i).getAccPassword();
				contents[i][3] = Arrays.toString(accounts.get(i).getCharacters().toArray());
				contents[i][4] = Arrays.toString(accounts.get(i).getCharacters().toArray()); 
			}
			this.mainWindow.getTableAccounts().setModel(new DefaultTableModel(contents,
	        new String[] {"Account ID", "Account Name", "Password", "Ammout of Chars", "Chars Ready to Hunt"}));
	}

	public void populateCharacterTable() {
		ArrayList<Character> characters = this.sdb.getCharacters();
		Object[][] contents = new Object[characters.size()][7];
			for(int i = 0; i < characters.size(); i++) {
				contents[i][0] = characters.get(i).getName();
				contents[i][1] = characters.get(i).getLevel();
				contents[i][2] = characters.get(i).getVocation();
				contents[i][3] = characters.get(i).getStamina();
				contents[i][4] = characters.get(i).getStatus();
				contents[i][5] = characters.get(i).getBankBalance();
				contents[i][6] = "x";
			}
			this.mainWindow.getTableCharacters().setModel(new DefaultTableModel(contents,
			        new String[] {"Name", "Level", "Vocation", "Current Stamina", "Status", "Start Bank Balance", "Current Bank Balance"}));
	}

	public void refreshTables() {
		this.populateAccountTable();
		this.populateCharacterTable();
	}

}