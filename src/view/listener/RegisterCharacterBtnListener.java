package view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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
		this.websiteReader = new WebsiteReader(this.registerCharacterWindow.getCharacterName_TF(), "Vocation");
		try {
			try {
				this.characterController.registerNewCharacter(this.characterController.getAccountByID(this.registerCharacterWindow.getAccountComboBoxValue()),
															  this.registerCharacterWindow.getCharacterName_TF(),
															  this.websiteReader.characterInfoReader(),
															  this.registerCharacterWindow.getCurrentStamina_TF(),
															  this.registerCharacterWindow.getBankBalance_TF());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (AccountNotFoundException | NumberFormatException e1) {
			e1.getMessage();
		}
		try {
			this.accountController.populateCharacterTable();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		this.accountController.populateAccountTable();
		this.characterController.closeRegisterCharacterWindow();
	}
	

}
