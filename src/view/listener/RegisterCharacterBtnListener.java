package view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import control.AccountController;
import control.CharacterController;
import exception.AccountNotFoundException;
import view.RegisterCharacterWindow;

public class RegisterCharacterBtnListener implements ActionListener {
	
	//Attributes
	private CharacterController characterController;
	private RegisterCharacterWindow registerCharacterWindow;
	private AccountController accountController;
	
	//Constructor
	public RegisterCharacterBtnListener(CharacterController characterController, RegisterCharacterWindow registerCharacterWindow, AccountController accountController) {
		this.characterController = characterController;
		this.registerCharacterWindow = registerCharacterWindow;
		this.accountController = accountController;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.characterController.registerNewCharacter(this.characterController.getAccountByID(this.registerCharacterWindow.getAccountComboBoxValue()),
														  this.registerCharacterWindow.getCharacterName_TF(),
														  this.registerCharacterWindow.getVocationComboBoxValue(),
														  this.registerCharacterWindow.getCurrentStamina_TF(),
														  this.registerCharacterWindow.getBankBalance_TF());
		} catch (AccountNotFoundException e1) {
			e1.getMessage();
		}
		this.accountController.populateCharacterTable();
		this.accountController.populateAccountTable();
		this.characterController.closeRegisterCharacterWindow();
	}
	

}
