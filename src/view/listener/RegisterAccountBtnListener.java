package view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import control.AccountController;
import view.RegisterAccountWindow;

public class RegisterAccountBtnListener implements ActionListener {
	
	private AccountController accountController;
	private RegisterAccountWindow registerAccountWindow;
	
	public RegisterAccountBtnListener(AccountController accountController, RegisterAccountWindow registerAccountWindow){
		this.accountController = accountController;
		this.registerAccountWindow = registerAccountWindow;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.accountController.registerNewAccount(this.registerAccountWindow.getAccID_TF(),
												  this.registerAccountWindow.getAccName_TF(),
												  this.registerAccountWindow.getPassword_TF());
		this.accountController.populateAccountTable();
		this.accountController.closeRegisterAccountWindow();
		

	}

}
