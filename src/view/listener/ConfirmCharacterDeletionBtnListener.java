package view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import control.AccountController;
import exception.AccountNotFoundException;
import exception.CharacterNotFoundException;

public class ConfirmCharacterDeletionBtnListener implements ActionListener {

	private AccountController accountController;
	private int characterAccID;
	private String characterName;
	
	public ConfirmCharacterDeletionBtnListener(AccountController accountController, int characterAccID, String characterName){
		this.accountController = accountController;
		this.characterAccID = characterAccID;
		this.characterName = characterName;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.accountController.deleteCharacter(characterAccID, characterName);
		this.accountController.closeCharacterConfirmDeletionWindow();
		this.accountController.refreshTables();
		

	}

}
