package view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import control.AccountController;

public class RegisterAccountListener implements ActionListener {

	private AccountController accountController;
	
	public RegisterAccountListener(AccountController accountController){
		this.accountController = accountController;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.accountController.createRegisterAccountWindow();
	}

}
