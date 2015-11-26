package view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import control.AccountController;
import control.CharacterController;
import exception.AccountNotFoundException;
import helper.WebsiteReader;
import view.RegisterCharacterWindow;

public class RegisterCharacterBtnListener implements ActionListener {
	
	private CharacterController characterController;
	private RegisterCharacterWindow registerCharacterWindow;
	private AccountController accountController;
	private WebsiteReader websiteReader;
	
	public RegisterCharacterBtnListener(CharacterController characterController, RegisterCharacterWindow registerCharacterWindow, AccountController accountController) {
		this.characterController = characterController;
		this.registerCharacterWindow = registerCharacterWindow;
		this.accountController = accountController;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(this.registerCharacterWindow.checkFields()){
			this.websiteReader = new WebsiteReader(this.registerCharacterWindow.getCharacterName_TF(), "Vocation");
			try {
				this.characterController.registerNewCharacter(this.characterController.getAccountByID(this.registerCharacterWindow.getAccountComboBoxValue()),
															this.registerCharacterWindow.getCharacterName_TF(),
															this.websiteReader.characterInfoReader(),
															Integer.parseInt(this.registerCharacterWindow.getCurrentStamina_TF()),
															Integer.parseInt(this.registerCharacterWindow.getBankBalance_TF()));
			} catch (AccountNotFoundException | IOException e1) {
				e1.printStackTrace();
				e1.getMessage();
			}
			try {
				this.accountController.refreshTables();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			this.characterController.closeRegisterCharacterWindow();
		}else{
			//Error Message
			JOptionPane.showMessageDialog(null, "Incorrect filled fields or this character name already exists", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	

}
