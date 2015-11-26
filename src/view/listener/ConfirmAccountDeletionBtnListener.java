package view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import control.AccountController;
import exception.AccountNotFoundException;
import exception.CharacterNotFoundException;

public class ConfirmAccountDeletionBtnListener implements ActionListener {

	private AccountController accountController;
	private int accID;
	
	public ConfirmAccountDeletionBtnListener(AccountController accountController, int accID){
		this.accountController = accountController;
		this.accID = accID;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.accountController.deleteAccount(accID);
		} catch (AccountNotFoundException e1) {
			e1.getMessage();
			e1.printStackTrace();
		} catch (CharacterNotFoundException e1) {
			e1.getMessage();
			e1.printStackTrace();
		}
		try {
			this.accountController.refreshTables();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		this.accountController.closeAccountConfirmDeletionWindow();
		

	}

}
