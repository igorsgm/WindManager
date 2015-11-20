package view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import control.AccountController;
import view.MainWindow;

public class DeleteCharacterBtnListener implements ActionListener {

	private AccountController accountController;
	private MainWindow mainWindow;
	
	public DeleteCharacterBtnListener(AccountController accountController, MainWindow mainWindow){
		this.accountController = accountController;
		this.mainWindow = mainWindow;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int selectedRow = this.mainWindow.getTableCharacters().getSelectedRow();
		//Checking if some row has been selected
		if (selectedRow != -1) {
			//Getting characterName and acc ID from the selected row
			int characterAccID = (int) this.mainWindow.getTableCharacters().getValueAt(selectedRow, 0);
			String characterName = (String) this.mainWindow.getTableCharacters().getValueAt(selectedRow, 1);
			this.accountController.createCharacterConfirmDeletionWindow(characterAccID, characterName);
		}
	}

}
