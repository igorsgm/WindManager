package view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import control.CharacterController;
import view.MainWindow;

public class EditCharacterInfoBtnListener implements ActionListener {
	
	private MainWindow mainWindow;
	private CharacterController characterController;
	
	public EditCharacterInfoBtnListener(MainWindow mainWindow, CharacterController characterController){
		this.mainWindow = mainWindow;
		this.characterController = characterController;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int selectedRow = this.mainWindow.getTableCharacters().getSelectedRow();
		//Checking if some row has been selected
		if (selectedRow != -1) {
			//Getting characterName and acc ID from the selected row
			int characterAccID = (int) this.mainWindow.getTableCharacters().getValueAt(selectedRow, 0);
			String characterName = (String) this.mainWindow.getTableCharacters().getValueAt(selectedRow, 1);
			this.characterController.createCharacterEditInfoWindow(characterAccID, characterName);
		}else{
			//Do nothing
		}
	}

}
