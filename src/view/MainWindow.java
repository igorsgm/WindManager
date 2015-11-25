package view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import control.AccountController;
import control.CharacterController;
import view.listener.ChangePasswordBtnListener;
import view.listener.DeleteAccountBtnListener;
import view.listener.DeleteCharacterBtnListener;
import view.listener.EditCharacterInfoBtnListener;
import view.listener.RegisterAccountListener;
import view.listener.RegisterCharacterListener;


public class MainWindow extends JFrame {
    private AccountController accountController;
    private CharacterController characterController;
    private JPanel contentPane;
    private JTable tableAccounts;
    private JTable tableCharacters;
    private DefaultTableModel modelTableAccounts;
    private DefaultTableModel modelTableCharacters;
    private JTextField characterFilter_TF;
    private JTextField accountFilter_TF;
    private TableRowSorter<TableModel> characterRowSorter;
    private TableRowSorter<TableModel> accountRowSorter;

    public MainWindow(AccountController accountController, CharacterController characterController) {
       
        this.accountController = accountController;
        this.characterController = characterController;
        setVisible(true);
        setResizable(false);
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
        scrollPane.setBounds(15, 58, 835, 445);
        accountsTab.add(scrollPane);
       
        
        
        tableAccounts = new JTable();
        tableAccounts.setModel(new DefaultTableModel(
            new Object[][] {
                //lines here
            },
            new String[] {
                "Acc ID", "Account Name", "Password", "Amount Of Chars", "Chars Ready to Hunt"
            }
        ) {
            boolean[] columnEditables = new boolean[] {
                false, false, false, false, false
            };
            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });
        
        //Columns properties 
        tableAccounts.getColumnModel().getColumn(0).setResizable(false);
        tableAccounts.getColumnModel().getColumn(0).setMaxWidth(45);
        tableAccounts.getColumnModel().getColumn(1).setResizable(false);
        tableAccounts.getColumnModel().getColumn(2).setResizable(false);
        tableAccounts.getColumnModel().getColumn(3).setResizable(false);
        tableAccounts.getColumnModel().getColumn(4).setResizable(false);
        scrollPane.setViewportView(tableAccounts);
       
        JButton btnDeleteAccount = new JButton("Delete Account");
        accountsTab.add(btnDeleteAccount);
        btnDeleteAccount.addActionListener(new DeleteAccountBtnListener(this.accountController, this));
        btnDeleteAccount.setBounds(187, 6, 150, 40);
        
        this.accountFilter_TF = new JTextField();
        this.accountFilter_TF.setColumns(10);
        this.accountFilter_TF.setBounds(620, 11, 230, 35);
        accountsTab.add(accountFilter_TF);
        
        JLabel label = new JLabel("");
        label.setIcon(new ImageIcon(MainWindow.class.getResource("/images/search-icon.png")));
        label.setBounds(586, 11, 32, 35);
        accountsTab.add(label);
        
        JButton btnChangePassword = new JButton("Change Password");
        btnChangePassword.addActionListener(new ChangePasswordBtnListener(this, this.accountController));
        btnChangePassword.setBounds(349, 6, 150, 40);
        accountsTab.add(btnChangePassword);
       
       
        /*
         * Character List Tab
         */
        JPanel characterListTab = new JPanel();
        tabbedPane.addTab("Character List", null, characterListTab, null);
        characterListTab.setLayout(null);
       
        JButton btnRegister = new JButton("Register Character");
        btnRegister.addActionListener(new RegisterCharacterListener(this.characterController));
        btnRegister.setBounds(15, 6, 150, 40);
        characterListTab.add(btnRegister);
        
        this.characterFilter_TF = new JTextField();
        this.characterFilter_TF.setBounds(620, 11, 230, 35);
        characterListTab.add(characterFilter_TF);
        
        JLabel label1 = new JLabel("");
        label1.setIcon(new ImageIcon(MainWindow.class.getResource("/images/search-icon.png")));
        label1.setBounds(586, 11, 32, 35);
        characterListTab.add(label1);

        JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setBounds(15, 58, 835, 445);
        characterListTab.add(scrollPane1);
        
        this.tableCharacters = new JTable();
        this.tableCharacters.setModel(new DefaultTableModel(
            new Object[][] {
                //lines here
            },
            new String[] {
                "Acc ID", "Name", "Level", "Vocation", "Stamina", "Status", "Bank Balance"
            }
        ) {
            boolean[] columnEditables = new boolean[] {
                false, false, false, false, false, false, false
            };
            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });
        
        //this.characterRowSorter = new TableRowSorter<>(tableCharacters.getModel());
        //this.tableCharacters.setRowSorter(this.characterRowSorter);
        //characterFilter_TF.getDocument().addDocumentListener(new TableFilterListener(this.characterFilter_TF, this.characterRowSorter));
        
        
        //Columns properties
        tableCharacters.getColumnModel().getColumn(0).setResizable(false);
        tableCharacters.getColumnModel().getColumn(0).setMaxWidth(45);
        tableCharacters.getColumnModel().getColumn(1).setResizable(false);
        tableCharacters.getColumnModel().getColumn(2).setResizable(false);
        tableCharacters.getColumnModel().getColumn(3).setResizable(false);
        tableCharacters.getColumnModel().getColumn(4).setResizable(false);
        tableCharacters.getColumnModel().getColumn(5).setResizable(false);
        tableCharacters.getColumnModel().getColumn(6).setResizable(false);
        scrollPane1.setViewportView(tableCharacters);
       
        JButton btnDeleteCharacter = new JButton("Delete Character");
        btnDeleteCharacter.addActionListener(new DeleteCharacterBtnListener(this, this.characterController));
        btnDeleteCharacter.setBounds(177, 6, 150, 40);
        characterListTab.add(btnDeleteCharacter);
        
        JButton btnEditCharacterInfo = new JButton("Edit Character Info");
        btnEditCharacterInfo.addActionListener(new EditCharacterInfoBtnListener(this, this.characterController));
        btnEditCharacterInfo.setBounds(339, 6, 150, 40);
        characterListTab.add(btnEditCharacterInfo);
       
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

	public JTextField getCharacterFilter_TF() {
		return characterFilter_TF;
	}

	public JTextField getAccountFilter_TF() {
		return accountFilter_TF;
	}

	public TableRowSorter<TableModel> getCharacterRowSorter() {
		return characterRowSorter;
	}

	public void setCharacterRowSorter(TableRowSorter<TableModel> characterRowSorter) {
		this.characterRowSorter = characterRowSorter;
	}

	public TableRowSorter<TableModel> getAccountRowSorter() {
		return accountRowSorter;
	}

	public void setAccountRowSorter(TableRowSorter<TableModel> accountRowSorter) {
		this.accountRowSorter = accountRowSorter;
	}
}