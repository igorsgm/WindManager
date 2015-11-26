package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import control.AccountController;
import view.listener.CancelChangePasswordListener;
import view.listener.ConfirmChangePasswordListener;

@SuppressWarnings("serial")
public class ChangePasswordWindow extends JFrame {
	
	private AccountController accountController;
	private int accID;
	private String accountName;
	private JPanel contentPane;
	private JTextField newPassword_TF;

	public ChangePasswordWindow(AccountController accountController, int accID, String accountName) {
		this.accountController = accountController;
		this.accID = accID;
		this.accountName = accountName;
		
		setTitle("Change Account Password");
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 360, 135);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPassword = new JLabel("New Password");
		lblPassword.setBounds(17, 20, 106, 16);
		contentPane.add(lblPassword);
		
		JButton btnConfirmChangePassword = new JButton("Confirm");
		btnConfirmChangePassword.addActionListener(new ConfirmChangePasswordListener(this, this.accountController));
		btnConfirmChangePassword.setBounds(195, 54, 140, 40);
		contentPane.add(btnConfirmChangePassword);
		
		newPassword_TF = new JTextField();
		newPassword_TF.setColumns(10);
		newPassword_TF.setBounds(145, 14, 190, 28);
		contentPane.add(newPassword_TF);
		
		JButton btnCancelChangePassword = new JButton("Cancel");
		btnCancelChangePassword.addActionListener(new CancelChangePasswordListener(this.accountController));
		btnCancelChangePassword.setBounds(16, 54, 140, 40);
		contentPane.add(btnCancelChangePassword);
		
		
	}
	public boolean checkFields(){
		if (this.getNewPassword_TF().isEmpty()){
			return false;
		}
		return true;
	}
	
	//Getters
	public String getNewPassword_TF() {
		return newPassword_TF.getText();
	}

	public int getAccID() {
		return accID;
	}

	public String getAccountName() {
		return accountName;
	}
	
}
