package view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import control.AccountController;
import view.MainWindow;

public class ChangePasswordBtnListener implements ActionListener {

	private MainWindow mainWindow;
	private AccountController accountController;
	
	public ChangePasswordBtnListener(MainWindow mainWindow, AccountController accountController){
		this.mainWindow = mainWindow;
		this.accountController = accountController;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int selectedRow = this.mainWindow.getTableAccounts().getSelectedRow();
		//Checking if some row has been selected
		if (selectedRow != -1) {
			//Getting characterName and acc ID from the selected row
			int AccID = (int) this.mainWindow.getTableAccounts().getValueAt(selectedRow, 0);
			String accountName = (String) this.mainWindow.getTableAccounts().getValueAt(selectedRow, 2);
			this.accountController.createChangePasswordWindow(AccID, accountName);
		}

	}

}
