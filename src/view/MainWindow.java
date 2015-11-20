package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import control.AccountController;
import view.listener.DeleteAccountBtnListener;
import view.listener.RegisterAccountListener;
import view.listener.RegisterCharacterListener;

public class MainWindow extends JFrame {
	private AccountController accountController;
	private JPanel contentPane;
	private JTable tableAccounts;
	private JTable tableCharacters;
	private DefaultTableModel modelTableAccounts;
	private DefaultTableModel modelTableCharacters;

	public MainWindow(AccountController accountController) {
		
		this.accountController = accountController;
		setVisible(true);
		setTitle("WindManager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(6, 6, 888, 566);
		contentPane.add(tabbedPane);
		contentPane.setLayout(null);
		
		JPanel accountsTab = new JPanel();
		tabbedPane.addTab("Accounts", null, accountsTab, null);
		accountsTab.setLayout(null);
		
		JButton btnRegisterAccount = new JButton("Register Account");
		btnRegisterAccount.addActionListener(new RegisterAccountListener(this.accountController));
		btnRegisterAccount.setBounds(15, 6, 150, 40);
		accountsTab.add(btnRegisterAccount);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 58, 600, 445);
		accountsTab.add(scrollPane);
		
		tableAccounts = new JTable();
		 this.modelTableAccounts = new DefaultTableModel(new Object[][] { {null, null, null, null, null},
																	{null, null, null, null, null},
																	{null, null, null, null, null} },
																	new String[] {"Account ID", "Account Name", "Password",
																				  "Ammout of Chars", "Chars Ready to Hunt"});
		tableAccounts.setModel(this.modelTableAccounts);
		scrollPane.setViewportView(tableAccounts);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(627, 78, 223, 426);
		accountsTab.add(textArea);
		
		JLabel lblAccountInformation = new JLabel("Account Information");
		lblAccountInformation.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccountInformation.setBackground(Color.BLACK);
		lblAccountInformation.setBounds(627, 59, 223, 16);
		accountsTab.add(lblAccountInformation);
		
		JButton btnDeleteAccount = new JButton("Delete Account");
		accountsTab.add(btnDeleteAccount);
		btnDeleteAccount.addActionListener(new DeleteAccountBtnListener(this.accountController, this));
		btnDeleteAccount.setBounds(187, 6, 150, 40);
		
		
		//Character List Tab
		JPanel characterListTab = new JPanel();
		tabbedPane.addTab("Character List", null, characterListTab, null);
		characterListTab.setLayout(null);
		
		JButton btnRegister = new JButton("Register Character");
		btnRegister.addActionListener(new RegisterCharacterListener(this.accountController));
		btnRegister.setBounds(15, 6, 150, 40);
		characterListTab.add(btnRegister);

		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(15, 58, 834, 445);
		characterListTab.add(scrollPane1);
		
		tableCharacters = new JTable();
		this.modelTableCharacters = new DefaultTableModel(new Object[][] { {null, null, null, null, null, null, null},
																	  {null, null, null, null, null, null, null},
																	  {null, null, null, null, null, null, null} },
																	  new String[] {"Name", "Level", "Vocation", "Current Stamina",
																					"Status", "Start Bank Balance", "Current Bank Balance"});
		tableCharacters.setModel(modelTableCharacters);
		scrollPane1.setViewportView(tableCharacters);
		
		JButton btnDeleteCharacter = new JButton("Delete Character");
		btnDeleteCharacter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDeleteCharacter.setBounds(177, 6, 150, 40);
		characterListTab.add(btnDeleteCharacter);
		
	}

	public JTable getTableAccounts() {
		return tableAccounts;
	}

	public JTable getTableCharacters() {
		return tableCharacters;
	}

	public DefaultTableModel getModelTableAccounts() {
		return modelTableAccounts;
	}

	public DefaultTableModel getModelTableCharacters() {
		return modelTableCharacters;
	}
	

}
