package view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import control.CharacterController;

public class ConfirmCharacterDeletionBtnListener implements ActionListener {

	private CharacterController characterController;
	private int characterAccID;
	private String characterName;
	
	public ConfirmCharacterDeletionBtnListener(CharacterController characterController, int characterAccID, String characterName){
		this.characterController = characterController;
		this.characterAccID = characterAccID;
		this.characterName = characterName;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.characterController.deleteCharacter(characterAccID, characterName);
		//this.accountController.deleteCharacter(characterAccID, characterName);
		try {
			this.characterController.callRefreshTables();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		this.characterController.closeCharacterConfirmDeletionWindow();
		//this.accountController.closeCharacterConfirmDeletionWindow();
		

	}

}
