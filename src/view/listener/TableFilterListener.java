package view.listener;

import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class TableFilterListener implements DocumentListener {
	
    private JTextField search_TF;
    private TableRowSorter<TableModel> rowSorter;
	
	public TableFilterListener(JTextField search_TF, TableRowSorter<TableModel> rowSorter){
		this.search_TF = search_TF;
		this.rowSorter = rowSorter;
	}

    @Override
    public void insertUpdate(DocumentEvent e) {
        String text = search_TF.getText();
        if (text.trim().length() == 0) {
            rowSorter.setRowFilter(null);
        } else {
            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        String text = search_TF.getText();

        if (text.trim().length() == 0) {
            rowSorter.setRowFilter(null);
        } else {
            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
        }
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
