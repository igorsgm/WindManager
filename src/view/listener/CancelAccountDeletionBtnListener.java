package view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import control.AccountController;

public class CancelAccountDeletionBtnListener implements ActionListener {

	private AccountController accountController;
	
	public CancelAccountDeletionBtnListener(AccountController accountController){
		this.accountController = accountController;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.accountController.closeConfirmDeletionWindow();
	}

}
