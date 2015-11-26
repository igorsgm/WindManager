package control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import database.SimulatedDataBase;
import exception.AccountNotFoundException;
import model.Account;
import model.Character;
import view.AccountConfirmDeletionWindow;
import view.ChangePasswordWindow;
import view.MainWindow;
import view.RegisterAccountWindow;
import view.listener.TableFilterListener;

public class AccountController {
	
	private SimulatedDataBase sdb;
	private RegisterAccountWindow registerAccountWindow;
	private CharacterController characterController;
	private MainWindow mainWindow;
	private AccountConfirmDeletionWindow accountConfirmDeletionWindow;
	private ChangePasswordWindow changePasswordWindow;
	
	public AccountController(){
		this.sdb = new SimulatedDataBase();
		this.characterController = new CharacterController(this.sdb, this);
		this.mainWindow = new MainWindow(this, this.characterController);
		try {
			this.refreshTables();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			this.refreshTables();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Account> getAccounts() {
		return this.sdb.getAccounts();
	}
	
	public boolean isRepeatedAccID(int accID){
		return this.sdb.isRepeatedAccID(accID);
	}
	
	//Registering and deleting
	public void registerNewAccount(int accountID, String accountName, String password) {
		Account newAccount = new Account(accountID, accountName, password);
		this.sdb.saveAccount(newAccount);
	}

	public void deleteAccount(int accID) {
		try {
			this.sdb.deleteAccount(accID);
		} catch (AccountNotFoundException e) {
			e.getMessage();
			e.printStackTrace();
		}
	}
	
	
	//Creating windows
	public void createRegisterAccountWindow() {
		this.registerAccountWindow = new RegisterAccountWindow(this);
	}

	public void createAccountConfirmDeletionWindow(int accID) {
		this.accountConfirmDeletionWindow = new AccountConfirmDeletionWindow(this, accID);
	}
	
	public void createChangePasswordWindow(int accID, String accountName) {
		this.changePasswordWindow = new ChangePasswordWindow(this, accID, accountName);
	}
	

	//Closing windows
	public void closeRegisterAccountWindow() {
		this.registerAccountWindow.setVisible(false);
		this.registerAccountWindow = null;
	}

	public void closeAccountConfirmDeletionWindow() {
		this.accountConfirmDeletionWindow.setVisible(false);
		this.accountConfirmDeletionWindow = null;
	}
	
	public void closeChangePasswordWindow() {
		this.changePasswordWindow.setVisible(false);
		this.changePasswordWindow = null;
	}
	
	
	//Populating tables
	public void refreshTables() throws IOException {
		this.populateAccountTable();
		this.populateCharacterTable();
	}
	
	@SuppressWarnings("serial")
	public void populateAccountTable() {
		ArrayList<Account> accounts = this.getAccounts();
		Object[][] contents = new Object[accounts.size()][6];
			for(int i = 0; i < accounts.size(); i++) {
				contents[i][0] = accounts.get(i).getAccId();
				contents[i][1] = accounts.get(i).getAccStatus();
				contents[i][2] = accounts.get(i).getAccName();
				contents[i][3] = accounts.get(i).getAccPassword();
				contents[i][4] = accounts.get(i).getCharacters().size();
				contents[i][5] = Arrays.toString(accounts.get(i).getCharacters().toArray());
			}
			this.mainWindow.getTableAccounts().setModel(new DefaultTableModel(contents,
		            new String[] {
		                    "Acc ID", "Status", "Account Name", "Password", "Amount Of Chars", "Chars Ready to Hunt"
		                }
		            ) {
		                boolean[] columnEditables = new boolean[] {
		                    false, false, false, false, false, false
		                };
		                public boolean isCellEditable(int row, int column) {
		                    return columnEditables[column];
		                }
		            });
			/*
			 * Enabling Account filter by text after populating
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
			 * Enabling Character filter by text after populating
			 * */
			this.mainWindow.setCharacterRowSorter(new TableRowSorter<>(this.mainWindow.getTableCharacters().getModel()));
	        this.mainWindow.getTableCharacters().setRowSorter(this.mainWindow.getCharacterRowSorter());
			this.mainWindow.getCharacterFilter_TF().getDocument().addDocumentListener(new TableFilterListener(this.mainWindow.getCharacterFilter_TF(), this.mainWindow.getCharacterRowSorter()));
	}

	public void updatePassword(int accID, String accountName, String newPassword_TF) {
		ArrayList<Character> charactersToMove = new ArrayList<Character>();
		for(int i=0; i < this.getAccounts().size(); i++){
			if(this.getAccounts().get(i).getAccId() == accID){
				//getting characters from older account
				charactersToMove = this.getAccounts().get(i).getCharacters();
				
				//deleting account
				this.deleteAccount(accID);
				
				//create new account with new characters
				this.registerNewAccount(accID, accountName, newPassword_TF);
				
				//appending new characters inside new account
				for (int j=0; j < charactersToMove.size(); j++){
					try {
						this.characterController.registerNewCharacter(charactersToMove.get(j).getCharAcc(), charactersToMove.get(j).getName(), charactersToMove.get(j).getVocation(), charactersToMove.get(j).getStamina(), charactersToMove.get(j).getBankBalance());
					} catch (AccountNotFoundException | IOException e1) {
						e1.getMessage();
						e1.printStackTrace();
					}
				}
			}
		}
		
	}
	
	

}