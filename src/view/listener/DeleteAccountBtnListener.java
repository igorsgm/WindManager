package view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import control.AccountController;
import view.MainWindow;

public class DeleteAccountBtnListener implements ActionListener {

	private MainWindow mainWindow;
	private AccountController accountController;
	
	public DeleteAccountBtnListener(AccountController accountController, MainWindow mainWindow){
		this.accountController = accountController;
		this.mainWindow = mainWindow;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		int selectedRow = this.mainWindow.getTableAccounts().getSelectedRow();
		//Checking if some row has been selected
		if (selectedRow != -1) {
			//Getting accID from the selected row
			int accID = (int) this.mainWindow.getTableAccounts().getValueAt(selectedRow, 0);
			this.accountController.createConfirmDeletionWindow(accID);
		}
	}

}
