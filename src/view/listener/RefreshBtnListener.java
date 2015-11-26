package view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import control.AccountController;

public class RefreshBtnListener implements ActionListener {

	private AccountController accountController;
	
	public RefreshBtnListener(AccountController accountController){
		this.accountController = accountController;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.accountController.refreshTables();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
