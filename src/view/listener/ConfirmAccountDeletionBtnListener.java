package view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import control.AccountController;

public class ConfirmAccountDeletionBtnListener implements ActionListener {

	private AccountController accountController;
	private int accID;
	
	public ConfirmAccountDeletionBtnListener(AccountController accountController, int accID){
		this.accountController = accountController;
		this.accID = accID;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.accountController.deleteAccount(accID);
		try {
			this.accountController.refreshTables();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		this.accountController.closeAccountConfirmDeletionWindow();
	}

}
