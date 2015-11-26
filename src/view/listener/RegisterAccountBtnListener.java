package view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

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
		if(this.registerAccountWindow.checkFields()){
			this.accountController.registerNewAccount(Integer.parseInt(this.registerAccountWindow.getAccID_TF()),
														this.registerAccountWindow.getAccName_TF(),
														this.registerAccountWindow.getPassword_TF());
			this.accountController.populateAccountTable();
			this.accountController.closeRegisterAccountWindow();
		}else{
			//Error Message
			JOptionPane.showMessageDialog(null, "Incorrect filled fields or account id already exists", "Error", JOptionPane.ERROR_MESSAGE);
		}
		

	}

}
