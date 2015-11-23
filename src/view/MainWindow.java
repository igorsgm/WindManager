package view;

import java.util.ArrayList;

import javax.swing.DefaultRowSorter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import control.AccountController;
import view.listener.DeleteAccountBtnListener;
import view.listener.DeleteCharacterBtnListener;
import view.listener.RegisterAccountListener;
import view.listener.RegisterCharacterListener;

@SuppressWarnings("serial")
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
        
        //Setting column order
        tableAccounts.setAutoCreateRowSorter(true);
        DefaultRowSorter sorter = ((DefaultRowSorter)tableAccounts.getRowSorter());
        ArrayList list = new ArrayList();
        list.add( new RowSorter.SortKey(2, SortOrder.ASCENDING) );
        sorter.setSortKeys(list);
        sorter.sort();
       
        JButton btnDeleteAccount = new JButton("Delete Account");
        accountsTab.add(btnDeleteAccount);
        btnDeleteAccount.addActionListener(new DeleteAccountBtnListener(this.accountController, this));
        btnDeleteAccount.setBounds(187, 6, 150, 40);
       
       
        /*
         * Character List Tab
         */
        JPanel characterListTab = new JPanel();
        tabbedPane.addTab("Character List", null, characterListTab, null);
        characterListTab.setLayout(null);
       
        JButton btnRegister = new JButton("Register Character");
        btnRegister.addActionListener(new RegisterCharacterListener(this.accountController));
        btnRegister.setBounds(15, 6, 150, 40);
        characterListTab.add(btnRegister);

        JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setBounds(15, 58, 835, 445);
        characterListTab.add(scrollPane1);
       
        tableCharacters = new JTable();
        tableCharacters.setModel(new DefaultTableModel(
            new Object[][] {
                //lines here
            },
            new String[] {
                "Acc ID", "Name", "Level", "Vocation", "Stamina", "Status", "Start Bank Balance", "Current Bank Balance"
            }
        ) {
            boolean[] columnEditables = new boolean[] {
                false, false, false, false, false, false, false, false
            };
            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });
        
        //Setting column order
        tableCharacters.setAutoCreateRowSorter(true);
        DefaultRowSorter sorter2 = ((DefaultRowSorter)tableCharacters.getRowSorter());
        ArrayList list2 = new ArrayList();
        list2.add( new RowSorter.SortKey(2, SortOrder.ASCENDING) );
        sorter.setSortKeys(list);
        sorter.sort();
        
        //Columns properties
        tableCharacters.getColumnModel().getColumn(0).setResizable(false);
        tableCharacters.getColumnModel().getColumn(0).setMaxWidth(45);
        tableCharacters.getColumnModel().getColumn(1).setResizable(false);
        tableCharacters.getColumnModel().getColumn(2).setResizable(false);
        //tableCharacters.getColumnModel().getColumn(2).setMaxWidth(70);
        tableCharacters.getColumnModel().getColumn(3).setResizable(false);
        //tableCharacters.getColumnModel().getColumn(3).setMaxWidth(110);
        tableCharacters.getColumnModel().getColumn(4).setResizable(false);
        //tableCharacters.getColumnModel().getColumn(4).setMaxWidth(110);
        tableCharacters.getColumnModel().getColumn(5).setResizable(false);
        //tableCharacters.getColumnModel().getColumn(5).setMaxWidth(95);
        tableCharacters.getColumnModel().getColumn(6).setResizable(false);
        tableCharacters.getColumnModel().getColumn(7).setResizable(false);
        scrollPane1.setViewportView(tableCharacters);
       
        JButton btnDeleteCharacter = new JButton("Delete Character");
        btnDeleteCharacter.addActionListener(new DeleteCharacterBtnListener(this.accountController, this));
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