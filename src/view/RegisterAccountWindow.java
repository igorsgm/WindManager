package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import control.AccountController;
import view.listener.RegisterAccountBtnListener;

@SuppressWarnings("serial")
public class RegisterAccountWindow extends JFrame {
	
	private AccountController accountController;
	private JPanel contentPane;
	private JTextField accName_TF;
	private JTextField accID_TF;
	private JTextField password_TF;

	public RegisterAccountWindow(AccountController accountController) {
		this.accountController = accountController;
		
		setTitle("Add New Account");
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 360, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAccountID = new JLabel("Account ID");
		lblAccountID.setBounds(16, 22, 106, 16);
		contentPane.add(lblAccountID);
		
		JLabel lblName = new JLabel("Account Name");
		lblName.setBounds(16, 50, 106, 16);
		contentPane.add(lblName);
		
		accName_TF = new JTextField();
		accName_TF.setBounds(156, 44, 190, 28);
		contentPane.add(accName_TF);
		accName_TF.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(16, 78, 106, 16);
		contentPane.add(lblPassword);
		
		JButton btnConfirmRegisterAccount = new JButton("Register Account");
		btnConfirmRegisterAccount.addActionListener(new RegisterAccountBtnListener(this.accountController, this));
		btnConfirmRegisterAccount.setBounds(100, 112, 159, 40);
		contentPane.add(btnConfirmRegisterAccount);
		
		accID_TF = new JTextField();
		accID_TF.setColumns(10);
		accID_TF.setBounds(156, 16, 190, 28);
		contentPane.add(accID_TF);
		
		password_TF = new JTextField();
		password_TF.setColumns(10);
		password_TF.setBounds(156, 72, 190, 28);
		contentPane.add(password_TF);
		
		
	}
	
	public boolean checkFields(){
		if (this.getAccID_TF().isEmpty() || this.getAccName_TF().isEmpty() || this.getPassword_TF().isEmpty() ||
			!this.getAccID_TF().matches("\\d+") || !this.getAccName_TF().matches("[a-zA-Z0-9]+") ||
			this.accountController.isRepeatedAccID(Integer.parseInt(this.getAccID_TF()))){
			return false;
		}
		return true;
	}
	
	//Getters
	public String getAccID_TF() {
		return accID_TF.getText();
	}
	public String getAccName_TF() {
		return accName_TF.getText();
	}
	public String getPassword_TF() {
		return password_TF.getText();
	}
	
}
