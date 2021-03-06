package view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		this.characterController.callRefreshTables();
		this.characterController.closeCharacterConfirmDeletionWindow();
	}

}
