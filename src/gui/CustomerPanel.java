package gui;

import gui.guiform.CustomerGUIForm;
import gui.guilistener.CustomerContactGUIListener;
import gui.guilistener.CustomerGUIListener;
import gui.guilistener.PopUpMenuDialogListener;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import utilities.Utilities;

public class CustomerPanel extends JPanel {

    private final JTable customerTable;
    private final PointOfSaleTableModel customerTableModel;
    private final JScrollPane scrollPane;
    private final JPopupMenu tablePopup;
    private final JMenuItem viewItem;
    private final JMenuItem updateItem;
    private final JMenuItem removeItem;
    
    private final JPanel arrowsPanel;
    private final JButton nextButton;
    private final JButton previousButton;

    
    private final String columnNames[] = {"Name", "Lastname", "ID Number"};

    private Object[][] tableItems;

    private PopUpMenuDialogListener dialogListener;
    
    private CustomerGUIListener customerGUIListener;
    private CustomerContactGUIListener customerContactGUIListener;
    
    public static int customerId;

    public CustomerPanel() {
        setLayout(new BorderLayout());

        //customer table
        customerTableModel = new PointOfSaleTableModel(tableItems, columnNames);
        customerTable = new JTable(customerTableModel);
        scrollPane = new JScrollPane(customerTable);
        customerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        //arrow buttons
        arrowsPanel = new JPanel();
        arrowsPanel.setLayout(new FlowLayout());
        nextButton = new JButton(">");
        previousButton = new JButton("<");

        //JPopup and its components
        tablePopup = new JPopupMenu();
        viewItem = new JMenuItem("View");
        updateItem = new JMenuItem("Update");
        removeItem = new JMenuItem("Remove");

        tablePopup.add(viewItem);
        tablePopup.add(updateItem);
        tablePopup.add(removeItem);
        
        arrowsPanel.add(previousButton);
        arrowsPanel.add(nextButton);

        add(scrollPane, BorderLayout.CENTER);
        add(arrowsPanel, BorderLayout.SOUTH);

        viewItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialogListener.viewDialogShown();
            }
        });

        updateItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                dialogListener.updateDialogShown();
            }
        });

        removeItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(null, "Do you want to remove this record?", "Confirm deletion", JOptionPane.OK_CANCEL_OPTION);

                if (choice == JOptionPane.OK_OPTION) {
                    dialogListener.recordRemoved();
                }
            }
        });
        
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customerGUIListener.nextPageDisplayed();
            }
        });
        
        previousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customerGUIListener.previousPageDisplayed();
            }
        });
    }

    public void populateCustomersTable(List<CustomerGUIForm> customerGUIFormsList) {
        for (CustomerGUIForm customer : customerGUIFormsList) {
            //populate table rows
            Object[] customerFields = new Object[6];
            
            customerFields[0] = customer.getName();
            customerFields[1] = customer.getLastname();
            customerFields[2] = customer.getIdNumber();

            customerTableModel.addRow(customerFields);
        }
    }

    public void clearCustomerTable() {
        for (int i = (customerTableModel.getRowCount() - 1); i >= 0; i--) {
            customerTableModel.removeRow(i);
        }
    }

    /**
     *
     * @return index of the selected table row
     */
    public int getSelectedTableRowIndex() {
        return customerTable.getSelectedRow();
    }

    public void removeRowFromTable(int index) {
        customerTableModel.removeRow(index);
    }

    public void setTableEnabled(boolean enabled) {
        customerTable.setEnabled(enabled);
    }

    public JTable getCustomerTable() {
        return customerTable;
    }

    public JPopupMenu getTablePopUp() {
        return tablePopup;
    }
    
    public void setNextButtonEnabled(boolean enabled){
        nextButton.setEnabled(enabled);
    }
    
    public void setPreviousButtonEnabled(boolean enabled){
        previousButton.setEnabled(enabled);
    }

    public void setDialogListener(PopUpMenuDialogListener dialogListener) {
        this.dialogListener = dialogListener;
    }
    
    public void setCustomerGUIListener(CustomerGUIListener customerGUIListener) {
        this.customerGUIListener = customerGUIListener;
    }
    
    public void setCustomerContactGUIListener(CustomerContactGUIListener customerContactGUIListener) {
        this.customerContactGUIListener = customerContactGUIListener;
    }
}
