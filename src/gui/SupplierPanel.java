package gui;

import gui.guiform.SupplierGUIForm;
import gui.guilistener.PopUpMenuDialogListener;
import gui.guilistener.SupplierGUIListener;
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

public class SupplierPanel extends JPanel {
    
    private final JTable supplierTable;
    private final PointOfSaleTableModel supplierTableModel;
    private final JScrollPane scrollPane;
    private final JPopupMenu tablePopup;
    private final JMenuItem viewItem;
    private final JMenuItem updateItem;
    private final JMenuItem removeItem;
    
    private final JPanel arrowsPanel;
    private final JButton nextButton;
    private final JButton previousButton;
    
    private SupplierGUIListener supplierGUIListener;

    //variables for populating the table
    private String columnNames[] = {"Name", "VAT Number", "Business"};

    private Object[][] tableItems;

    private PopUpMenuDialogListener dialogListener;
    
    public static int supplierId;
    public static String selectedSupplierId = "";
    public static boolean isSupplierSelected = false;
    
    public SupplierPanel() {
        setLayout(new BorderLayout());

        //supplier table
        supplierTableModel = new PointOfSaleTableModel(tableItems, columnNames);
        supplierTable = new JTable(supplierTableModel);
        scrollPane = new JScrollPane(supplierTable);
        supplierTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
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
                supplierGUIListener.nextPageDisplayed();
            }
        });
        
        previousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                supplierGUIListener.previousPageDisplayed();
            }
        });
    }
    
    public void populateSuppliersTable(List<SupplierGUIForm> supplierGUIFormsList) {
        for (SupplierGUIForm supplier : supplierGUIFormsList) {
            //populate table rows
            Object[] customerFields = new Object[6];
            
            customerFields[0] = supplier.getName();
            customerFields[1] = supplier.getVatNumber();
            customerFields[2] = supplier.isIsBusiness().equals("true") ? "Yes" : "-";

            supplierTableModel.addRow(customerFields);
        }
    }

    public void clearSupplierTable() {
        for (int i = (supplierTableModel.getRowCount() - 1); i >= 0; i--) {
            supplierTableModel.removeRow(i);
        }
    }

    /**
     *
     * @return index of the selected table row
     */
    public int getSelectedTableRowIndex() {
        return supplierTable.getSelectedRow();
    }

    public void removeRowFromTable(int index) {
        supplierTableModel.removeRow(index);
    }

    public void setTableEnabled(boolean enabled) {
        supplierTable.setEnabled(enabled);
    }

    public JTable getSupplierTable() {
        return supplierTable;
    }

    public JPopupMenu getTablePopUp() {
        return tablePopup;
    }
    
    public void setDialogListener(PopUpMenuDialogListener dialogListener) {
        this.dialogListener = dialogListener;
    }
    
    public void setNextButtonEnabled(boolean enabled){
        nextButton.setEnabled(enabled);
    }
    
    public void setPreviousButtonEnabled(boolean enabled){
        previousButton.setEnabled(enabled);
    }
    
    public void setSupplierGUIListener(SupplierGUIListener supplierGUIListener) {
        this.supplierGUIListener = supplierGUIListener;
    }
    
}
