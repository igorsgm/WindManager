package view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import control.AccountController;
import view.ChangePasswordWindow;

public class ConfirmChangePasswordListener implements ActionListener {

	private ChangePasswordWindow changePasswordWindow;
	private AccountController accountController;
	
	public ConfirmChangePasswordListener(ChangePasswordWindow changePasswordWindow, AccountController accountController) {
		this.changePasswordWindow = changePasswordWindow;
		this.accountController = accountController;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(this.changePasswordWindow.checkFields()){
			this.accountController.updatePassword(this.changePasswordWindow.getAccID(), this.changePasswordWindow.getAccountName(),
												this.changePasswordWindow.getNewPassword_TF());
			try {
				this.accountController.refreshTables();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			this.accountController.closeChangePasswordWindow();
		}else{
			//Error Message
			JOptionPane.showMessageDialog(null, "Incorrect filled field. Try again.", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

}
