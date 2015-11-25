package control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import database.SimulatedDataBase;
import exception.AccountNotFoundException;
import exception.CharacterNotFoundException;
import model.Account;
import model.Character;
import view.AccountConfirmDeletionWindow;
import view.MainWindow;
import view.RegisterAccountWindow;
import view.listener.TableFilterListener;

public class AccountController {
	
	private SimulatedDataBase sdb;
	private RegisterAccountWindow registerAccountWindow;
	private CharacterController characterController;
	private MainWindow mainWindow;
	private AccountConfirmDeletionWindow accountConfirmDeletionWindow;
	
	public AccountController(){
		this.sdb = new SimulatedDataBase();
		this.characterController = new CharacterController(this.sdb, this);
		this.mainWindow = new MainWindow(this, this.characterController);
	}
	
	public ArrayList<Account> getAccounts() {
		return this.sdb.getAccounts();
	}
	
	public void createRegisterAccountWindow() {
		this.registerAccountWindow = new RegisterAccountWindow(this);
	}

	public void createAccountConfirmDeletionWindow(int accID) {
		this.accountConfirmDeletionWindow = new AccountConfirmDeletionWindow(this, accID);
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

	public void closeAccountConfirmDeletionWindow() {
		this.accountConfirmDeletionWindow.setVisible(false);
		this.accountConfirmDeletionWindow = null;
	}
	
	public void refreshTables() throws IOException {
		this.populateAccountTable();
		this.populateCharacterTable();
	}
	
	@SuppressWarnings("serial")
	public void populateAccountTable() {
		ArrayList<Account> accounts = this.getAccounts();
		Object[][] contents = new Object[accounts.size()][5];
			for(int i = 0; i < accounts.size(); i++) {
				/*AccID*/		contents[i][0] = accounts.get(i).getAccId();
				/*Acc Name*/	contents[i][1] = accounts.get(i).getAccName(); 
				/*Password*/	contents[i][2] = accounts.get(i).getAccPassword();
				/**/		contents[i][3] = Arrays.toString(accounts.get(i).getCharacters().toArray());
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
			/*
			 * Enabling dynamical Account filter by text after populating
			 * */
			this.mainWindow.setAccountRowSorter(new TableRowSorter<>(this.mainWindow.getTableAccounts().getModel()));
	        this.mainWindow.getTableAccounts().setRowSorter(this.mainWindow.getAccountRowSorter());
			this.mainWindow.getAccountFilter_TF().getDocument().addDocumentListener(new TableFilterListener(this.mainWindow.getAccountFilter_TF(), this.mainWindow.getAccountRowSorter()));
	}

	@SuppressWarnings("serial")
	public void populateCharacterTable() throws IOException {
		ArrayList<Character> characters = this.sdb.getCharacters();
		Object[][] contents = new Object[characters.size()][7];
			for(int i = 0; i < characters.size(); i++) {
				/*AccID*/		contents[i][0] = characters.get(i).getCharAcc().getAccId();
				/*Name*/		contents[i][1] = characters.get(i).getName();
				/*Level*/		contents[i][2] = characters.get(i).getLevel();
				/*Vocation*/	contents[i][3] = characters.get(i).getVocation();
				/*Stamina*/		contents[i][4] = characters.get(i).getStamina();
				/*Status*/		contents[i][5] = characters.get(i).getStatus();
				/*BankBalance*/	contents[i][6] = characters.get(i).getBankBalance();
			}
			this.mainWindow.getTableCharacters().setModel(new DefaultTableModel(contents,
		            new String[] {
		                    "Acc ID", "Name", "Level", "Vocation", "Stamina", "Status", "Bank Balance"
		                }
		            ) {
		                boolean[] columnEditables = new boolean[] {
		                    false, false, false, false, false, false, false
		                };
		                public boolean isCellEditable(int row, int column) {
		                    return columnEditables[column];
		                }
		            });
			/*
			 * Enabling dynamical Character filter by text after populating
			 * */
			this.mainWindow.setCharacterRowSorter(new TableRowSorter<>(this.mainWindow.getTableCharacters().getModel()));
	        this.mainWindow.getTableCharacters().setRowSorter(this.mainWindow.getCharacterRowSorter());
			this.mainWindow.getCharacterFilter_TF().getDocument().addDocumentListener(new TableFilterListener(this.mainWindow.getCharacterFilter_TF(), this.mainWindow.getCharacterRowSorter()));
	}

}