package control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.table.DefaultTableModel;

import database.SimulatedDataBase;
import exception.AccountNotFoundException;
import exception.CharacterNotFoundException;
import model.Account;
import model.Character;
import view.AccountConfirmDeletionWindow;
import view.CharacterConfirmDeletionWindow;
import view.MainWindow;
import view.RegisterAccountWindow;

public class AccountController {
	//Attributes
	private SimulatedDataBase sdb;
	private RegisterAccountWindow registerAccountWindow;
	private CharacterController characterController;
	private MainWindow mainWindow;
	private AccountConfirmDeletionWindow accountConfirmDeletionWindow;
	private CharacterConfirmDeletionWindow characterConfirmDeletionWindow;
	
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

	public void createAccountConfirmDeletionWindow(int accID) {
		this.accountConfirmDeletionWindow = new AccountConfirmDeletionWindow(this, accID);
	}
	
	public void createCharacterConfirmDeletionWindow(int characterAccID, String characterName) {
		this.characterConfirmDeletionWindow = new CharacterConfirmDeletionWindow(this, characterAccID, characterName);
	}

	public void registerNewAccount(int accountID, String accountName, String password) {
		Account newAccount = new Account(accountID, accountName, password);
		this.sdb.saveAccount(newAccount);
	}

	public void deleteAccount(int accID) throws AccountNotFoundException, CharacterNotFoundException {
		this.sdb.deleteAccount(accID);
	}
	
	public void deleteCharacter(int characterAccID, String characterName) {
		this.sdb.deleteCharacter(characterAccID, characterName);
		
	}
	
	public void closeRegisterAccountWindow() {
		this.registerAccountWindow.setVisible(false);
		this.registerAccountWindow = null;
	}

	public void closeAccountConfirmDeletionWindow() {
		this.accountConfirmDeletionWindow.setVisible(false);
		this.accountConfirmDeletionWindow = null;
	}
	
	public void closeCharacterConfirmDeletionWindow() {
		this.characterConfirmDeletionWindow.setVisible(false);
		this.characterConfirmDeletionWindow = null;
	}
	
	@SuppressWarnings("serial")
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
		            new String[] {
		                    "Acc ID", "Account Name", "Password", "Amount Of Chars", "Chars Ready to Hunt"
		                }
		            ) {
		                boolean[] columnEditables = new boolean[] {
		                    false, false, false, false, false
		                };
		                public boolean isCellEditable(int row, int column) {
		                    return columnEditables[column];
		                }
		            });
	}

	@SuppressWarnings("serial")
	public void populateCharacterTable() throws IOException {
		ArrayList<Character> characters = this.sdb.getCharacters();
		Object[][] contents = new Object[characters.size()][8];
			for(int i = 0; i < characters.size(); i++) {
				contents[i][0] = characters.get(i).getCharAcc().getAccId();
				contents[i][1] = characters.get(i).getName();
				contents[i][2] = this.characterController.WebPageInfoReader(characters.get(i).getName(), "Level");
				contents[i][3] = this.characterController.WebPageInfoReader(characters.get(i).getName(), "Vocation");
				contents[i][4] = characters.get(i).getStamina();
				contents[i][5] = this.characterController.WebPageCharacterStatusReader(this.characterController.WebPageInfoReader(characters.get(i).getName(), "World"), characters.get(i).getName());
				contents[i][6] = characters.get(i).getBankBalance();
				contents[i][7] = "x";
			}
			this.mainWindow.getTableCharacters().setModel(new DefaultTableModel(contents,
		            new String[] {
		                    "Acc ID", "Name", "Level", "Vocation", "Stamina", "Status", "Start Bank Balance", "Current Bank Balance"
		                }
		            ) {
		                boolean[] columnEditables = new boolean[] {
		                    false, false, false, false, false, false, false, false
		                };
		                public boolean isCellEditable(int row, int column) {
		                    return columnEditables[column];
		                }
		            });
	}

	public void refreshTables() throws IOException {
		this.populateAccountTable();
		this.populateCharacterTable();
	}

}