package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import control.AccountController;
import view.listener.CancelAccountDeletionBtnListener;
import view.listener.ConfirmAccountDeletionBtnListener;

@SuppressWarnings("serial")
public class AccountConfirmDeletionWindow extends JFrame {

	private AccountController accountController;
	private int accID;
	private JPanel contentPane;

	public AccountConfirmDeletionWindow(AccountController accountController, int accID) {
		setTitle("Delete");
		this.accountController = accountController;
		this.accID = accID;
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 360, 135);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel warningLbl = new JLabel("<html><p style=\"text-align:center;padding:15px;\">Are you sure that you want to permanently <b>DELETE</b> the account which ID is <b>" + String.valueOf(this.accID) + "</b> and all characters associated to it?</html>");
		warningLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		warningLbl.setBounds(0, 0, 354, 60);
		contentPane.add(warningLbl);
		
		JButton cancelPlayerDeletionBtn = new JButton("NO");
		cancelPlayerDeletionBtn.addActionListener(new CancelAccountDeletionBtnListener(this.accountController));
		cancelPlayerDeletionBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		cancelPlayerDeletionBtn.setBounds(10, 68, 155, 30);
		contentPane.add(cancelPlayerDeletionBtn);
		
		JButton confirmPlayerDeletionBtn = new JButton("YES");
		confirmPlayerDeletionBtn.addActionListener(new ConfirmAccountDeletionBtnListener(this.accountController, this.accID));
		confirmPlayerDeletionBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		confirmPlayerDeletionBtn.setBounds(191, 68, 155, 30);
		contentPane.add(confirmPlayerDeletionBtn);
		
		JSeparator horizontalSeparator = new JSeparator();
		horizontalSeparator.setForeground(Color.BLACK);
		horizontalSeparator.setBounds(-10, 58, 420, 1);
		contentPane.add(horizontalSeparator);
		
		JSeparator verticalSeparator = new JSeparator();
		verticalSeparator.setForeground(Color.BLACK);
		verticalSeparator.setOrientation(SwingConstants.VERTICAL);
		verticalSeparator.setBounds(177, 59, 1, 100);
		contentPane.add(verticalSeparator);
	}
}
