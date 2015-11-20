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
import view.listener.CancelCharacterDeletionBtnListener;
import view.listener.ConfirmCharacterDeletionBtnListener;

public class CharacterConfirmDeletionWindow extends JFrame {

	private AccountController accountController;
	private String characterName;
	private int characterAccID;
	private JPanel contentPane;

	public CharacterConfirmDeletionWindow(AccountController accountController, int characterAccID, String characterName) {
		setTitle("Delete Character");
		this.accountController = accountController;
		this.characterAccID = characterAccID;
		this.characterName = characterName;
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 360, 135);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel warningLbl = new JLabel("<html><p style=\"text-align:center;padding:15px;\">Are you sure that you want to permanently <b>DELETE</b> the character which name is <b>" + String.valueOf(this.characterName) + "</b>?</html>");
		warningLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		warningLbl.setBounds(0, 0, 354, 60);
		contentPane.add(warningLbl);
		
		JButton cancelCharacterDeletionBtn = new JButton("NO");
		cancelCharacterDeletionBtn.addActionListener(new CancelCharacterDeletionBtnListener(this.accountController));
		cancelCharacterDeletionBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		cancelCharacterDeletionBtn.setBounds(10, 68, 155, 30);
		contentPane.add(cancelCharacterDeletionBtn);
		
		JButton confirmCharacterDeletionBtn = new JButton("YES");
		confirmCharacterDeletionBtn.addActionListener(new ConfirmCharacterDeletionBtnListener(this.accountController, this.characterAccID, this.characterName));
		confirmCharacterDeletionBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		confirmCharacterDeletionBtn.setBounds(191, 68, 155, 30);
		contentPane.add(confirmCharacterDeletionBtn);
		
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
