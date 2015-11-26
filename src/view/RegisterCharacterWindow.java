package view;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import control.AccountController;
import control.CharacterController;
import model.Account;
import view.listener.RegisterCharacterBtnListener;

@SuppressWarnings("serial")
public class RegisterCharacterWindow extends JFrame {

	private CharacterController characterController;
	private AccountController accountController;
	private JPanel contentPane;
	private JTextField characterName_TF;
	private JTextField currentStamina_TF;
	private JTextField bankBalance_TF;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxAccounts;

	@SuppressWarnings("rawtypes")
	public RegisterCharacterWindow(CharacterController characterController, AccountController accountController) {
		this.characterController = characterController;
		this.accountController = accountController;
		
		setVisible(true);
		setResizable(false);
		setTitle("Add New Character");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 375, 235);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAccount = new JLabel("Account");
		lblAccount.setBounds(16, 27, 52, 16);
		contentPane.add(lblAccount);
		
		this.comboBoxAccounts = new JComboBox();
		contentPane.add(this.comboBoxAccounts);
		this.comboBoxAccounts.setMaximumRowCount(100);
		this.comboBoxAccounts.setBounds(156, 16, 190, 27);
		this.populateAccountComboBox(this.characterController.getAccounts());
		
		
		JLabel lblCharacterName = new JLabel("Character name");
		lblCharacterName.setBounds(16, 55, 106, 16);
		contentPane.add(lblCharacterName);
		
		characterName_TF = new JTextField();
		characterName_TF.setBounds(156, 49, 190, 28);
		contentPane.add(characterName_TF);
		characterName_TF.setColumns(10);
		
		JLabel lblCurrentStamina = new JLabel("Current Stamina");
		lblCurrentStamina.setBounds(16, 83, 106, 16);
		contentPane.add(lblCurrentStamina);
		
		currentStamina_TF = new JTextField();
		currentStamina_TF.setBounds(156, 77, 190, 28);
		currentStamina_TF.setColumns(10);
		contentPane.add(currentStamina_TF);
		
		JLabel lblCurrentBankBalance = new JLabel("Start Bank Balance");
		lblCurrentBankBalance.setBounds(16, 111, 136, 16);
		contentPane.add(lblCurrentBankBalance);
		
		bankBalance_TF = new JTextField();
		bankBalance_TF.setBounds(156, 105, 190, 28);
		bankBalance_TF.setToolTipText("Insert the quantity in gold coins (ex: 15000)");
		contentPane.add(bankBalance_TF);
		bankBalance_TF.setColumns(10);
		
		JButton btnConfirmRegisterCharacter = new JButton("Register Character");
		btnConfirmRegisterCharacter.addActionListener(new RegisterCharacterBtnListener(this.characterController, this, this.accountController));
		btnConfirmRegisterCharacter.setBounds(100, 150, 159, 40);
		contentPane.add(btnConfirmRegisterCharacter);
		
	}
	
	//Getters and other Methods
	
	public int getAccountComboBoxValue() {
		return Integer.parseInt(this.comboBoxAccounts.getSelectedItem().toString());
	}
	
	@SuppressWarnings("unchecked")
	public void populateAccountComboBox(ArrayList<Account> accounts) {
		this.comboBoxAccounts.removeAllItems();
		this.comboBoxAccounts.addItem("Select an account");
		for(int i = 0; i < accounts.size(); i++){
			this.comboBoxAccounts.addItem(accounts.get(i).getAccId());
		}
	}
	
	public String getCharacterName_TF() {
		return characterName_TF.getText();
	}

	public int getCurrentStamina_TF() {
		return Integer.parseInt(currentStamina_TF.getText());
	}

	public int getBankBalance_TF() {
		return Integer.parseInt(bankBalance_TF.getText());
	}
	

}
