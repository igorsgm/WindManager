package view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import control.CharacterController;

public class CancelCharacterDeletionBtnListener implements ActionListener {

	private CharacterController characterController;
	
	public CancelCharacterDeletionBtnListener(CharacterController characterController){
		this.characterController = characterController;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.characterController.closeCharacterConfirmDeletionWindow();
	}

}
