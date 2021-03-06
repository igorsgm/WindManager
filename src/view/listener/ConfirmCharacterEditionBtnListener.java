package view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import control.CharacterController;
import helper.WebsiteReader;
import view.CharacterEditInfoWindow;

public class ConfirmCharacterEditionBtnListener implements ActionListener {

	private CharacterController characterController;
	private CharacterEditInfoWindow characterEditInfoWindow;
	private WebsiteReader websiteReader;
	
	public ConfirmCharacterEditionBtnListener(CharacterEditInfoWindow characterEditInfoWindow, CharacterController characterController) {
		this.characterEditInfoWindow = characterEditInfoWindow;
		this.characterController = characterController;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (this.characterEditInfoWindow.checkFields()){
			this.characterController.deleteCharacter(this.characterEditInfoWindow.getInitialCharacterAccID(), this.characterEditInfoWindow.getInitialCharacterName());
			this.websiteReader = new WebsiteReader(this.characterEditInfoWindow.getCharacterName_TF(), "Vocation");
			try {
				this.characterController.updateCharacter(this.characterController.getAccountByID(this.characterEditInfoWindow.getAccountComboBoxValue()),
														this.characterEditInfoWindow.getCharacterName_TF(),
														this.websiteReader.characterInfoReader(), //vocation
														Integer.parseInt(this.characterEditInfoWindow.getCurrentStamina_TF()),
														Integer.parseInt(this.characterEditInfoWindow.getBankBalance_TF()));
			} catch (IOException e1) {
				e1.getMessage();
				e1.printStackTrace();
			}
			this.characterController.callRefreshTables();
			this.characterController.closeCharacterEditInfoWindow();
		}else{
			//Error Message
			JOptionPane.showMessageDialog(null, "Incorrect filled fields. Try again.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

}
