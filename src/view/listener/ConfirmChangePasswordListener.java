package view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import control.AccountController;
import control.CharacterController;
import exception.AccountNotFoundException;
import exception.CharacterNotFoundException;
import model.Character;
import view.ChangePasswordWindow;

public class ConfirmChangePasswordListener implements ActionListener {

	private ChangePasswordWindow changePasswordWindow;
	private AccountController accountController;
	private CharacterController characterController;
	
	public ConfirmChangePasswordListener(ChangePasswordWindow changePasswordWindow, AccountController accountController, CharacterController characterController) {
		this.changePasswordWindow = changePasswordWindow;
		this.accountController = accountController;
		this.characterController = characterController;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(!this.changePasswordWindow.getNewPassword_TF().isEmpty()){
			ArrayList<Character> characters = new ArrayList<Character>();
			for(int i=0; i < this.accountController.getAccounts().size(); i++){
				if(this.accountController.getAccounts().get(i).getAccId() == this.changePasswordWindow.getAccID()){
					//getting characters from older account
					characters = this.accountController.getAccounts().get(i).getCharacters();
					//deletar a acc antiga
					try {
						this.accountController.deleteAccount(this.changePasswordWindow.getAccID());
					} catch (AccountNotFoundException | CharacterNotFoundException e1) {
						e1.getMessage();
						e1.printStackTrace();
					}
		
					//create new account with new characters
					this.accountController.registerNewAccount(this.changePasswordWindow.getAccID(), this.changePasswordWindow.getAccountName(), this.changePasswordWindow.getNewPassword_TF());
					
					//appending new characters inside new account
					for (int j=0; j < characters.size(); j++){
						try {
							this.characterController.registerNewCharacter(characters.get(j).getCharAcc(), characters.get(j).getName(), characters.get(j).getVocation(), characters.get(j).getStamina(), characters.get(j).getBankBalance());
						} catch (AccountNotFoundException | IOException e1) {
							e1.getMessage();
							e1.printStackTrace();
						}
					}
				}
			}
			try {
				this.accountController.refreshTables();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			this.accountController.closeChangePasswordWindow();
		}else{
			//Do nothing
		}

	}

}
