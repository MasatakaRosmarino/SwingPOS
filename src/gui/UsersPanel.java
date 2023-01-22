package gui;

import database.DataBaseManager;
import gui.dialog.BaseDialog;
import gui.dialog.CreateCustomerDialog;
import gui.dialog.CreateSupplierDialog;
import gui.dialog.UpdateCustomerDialog;
import gui.dialog.UpdateSupplierDialog;
import gui.dialog.ViewCustomerDialog;
import gui.dialog.ViewSupplierDialog;
import gui.guiform.ContactGUIForm;
import gui.guiform.CustomerGUIForm;
import gui.guiform.SupplierGUIForm;
import gui.guilistener.CustomerContactGUIListener;
import gui.guilistener.CustomerGUIListener;
import gui.guilistener.PopUpMenuDialogListener;
import gui.guilistener.SupplierContactGUIListener;
import gui.guilistener.SupplierGUIListener;
//import gui.guilistener.UsersGUIListener;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import utilities.Utilities;

public class UsersPanel extends JPanel {

    //JToolbar components
    private final JToolBar userToolbar;
    private final JButton createRecordButton;
    private final JButton viewRecordButton;
    private final JButton updateRecordButton;
    private final JButton removeRecordButton;
    private final JPanel userSearchPanel;
    private final JTextField userSearchField;
    private final JButton userSearchButton;
    private final JButton cancelSearchButton;

    private final JPanel usersTablePanel;
    private JTabbedPane usersTabbedPane;

    private final CustomerPanel customerPanel;
    private ContactPanel contactPanel;

    private SupplierPanel supplierPanel;

    private JTable customerTable;
    private JPopupMenu customerTablePopUp;

    private JTable supplierTable;
    private JPopupMenu supplierTablePopUp;
    
    private BaseDialog crudDialog;

    private ViewSupplierDialog viewSupplierDialog;
    private CreateSupplierDialog createSupplierDialog;
    private UpdateSupplierDialog updateSupplierDialog;

    private ActionListener viewCustomerListener;
    private ActionListener createCustomerListener;
    private ActionListener updateCustomerListener;
    private ActionListener deleteCustomerListener;
    private ActionListener viewSupplierListener;
    private ActionListener createSupplierListener;
    private ActionListener updateSupplierListener;
    private ActionListener deleteSupplierListener;
    private ActionListener searchButtonListener;
    private ActionListener cancelSearchListener;

    private CustomerGUIListener customerGUIListener;
    private CustomerContactGUIListener customerContactGUIListener;
    private SupplierGUIListener supplierGUIListener;
    private SupplierContactGUIListener supplierContactGUIListener;

    private CustomerGUIForm customerGUIForm;

    private ContactGUIForm contactGUIForm;

    private SupplierGUIForm supplierGUIForm;

    public UsersPanel() {
        setLayout(new BorderLayout());

        //JToolbar and its components
        userToolbar = new JToolBar();
        userToolbar.setFloatable(false);
        createRecordButton = new JButton("New");
        viewRecordButton = new JButton("view");
        updateRecordButton = new JButton("Update");
        removeRecordButton = new JButton("Remove");
        userSearchPanel = new JPanel();
        userSearchPanel.setLayout(new FlowLayout());
        userSearchField = new JTextField(10);
        userSearchButton = new JButton("Search");
        cancelSearchButton = new JButton("X");

        usersTabbedPane = new JTabbedPane();

        usersTablePanel = new JPanel();
        usersTablePanel.setLayout(new BorderLayout());

        userSearchPanel.setLayout(new FlowLayout());

        customerPanel = new CustomerPanel();
        contactPanel = new ContactPanel();

        supplierPanel = new SupplierPanel();

        createRecordButton.setMargin(new Insets(1, 1, 1, 1));
        viewRecordButton.setMargin(new Insets(1, 1, 1, 1));
        updateRecordButton.setMargin(new Insets(1, 1, 1, 1));
        removeRecordButton.setMargin(new Insets(1, 1, 1, 1));
        userSearchButton.setMargin(new Insets(1, 1, 1, 1));
        cancelSearchButton.setMargin(new Insets(2, 2, 2, 2));

        supplierTable = supplierPanel.getSupplierTable();

        viewCustomerListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (crudDialog == null || !crudDialog.isShowing()) {
            		crudDialog = new ViewCustomerDialog(customerGUIForm, contactGUIForm, customerGUIListener, supplierGUIListener);
                }
            }
        };

        createCustomerListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (crudDialog == null || !crudDialog.isShowing()) {
            		crudDialog = new CreateCustomerDialog(customerGUIListener, customerContactGUIListener);
                }
            }
        };

        updateCustomerListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (crudDialog == null || !crudDialog.isShowing()) {
            		crudDialog = new UpdateCustomerDialog(customerGUIForm, contactGUIForm, customerGUIListener, supplierGUIListener, customerContactGUIListener);
                }
            }
        };

        deleteCustomerListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(null, "Do you want to remove this record?", "Confirm deletion", JOptionPane.OK_CANCEL_OPTION);

                if (choice == JOptionPane.OK_OPTION) {
                    if (!customerGUIListener.invoicedCustomerExistenceChecked()) {
                        customerContactGUIListener.customerContactRelationshipDeleted();

                        customerContactGUIListener.customerContactDeleted();
                        customerGUIListener.databaseTableRowRemoved(Integer.parseInt(customerGUIForm.getFormIdField()));

                        DataBaseManager.resetCustomerOffset();

                        customerGUIListener.customerPanelReset();
                    } else {
                        Utilities.notifyOfError("Cannot delete customer as it has been invoiced");
                    }
                }
            }
        };

        viewSupplierListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (viewSupplierDialog == null || !viewSupplierDialog.isShowing()) {
                    viewSupplierDialog = new ViewSupplierDialog(supplierGUIForm, contactGUIForm, customerGUIListener, supplierGUIListener);
                }
            }
        };

        createSupplierListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (createSupplierDialog == null || !createSupplierDialog.isShowing()) {
                    createSupplierDialog = new CreateSupplierDialog(supplierPanel, supplierGUIListener, supplierContactGUIListener);
                }
            }
        };

        updateSupplierListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (updateSupplierDialog == null || !updateSupplierDialog.isShowing()) {
                    updateSupplierDialog = new UpdateSupplierDialog(supplierGUIForm, contactGUIForm, supplierGUIListener, customerGUIListener, supplierContactGUIListener);
                }
            }
        };

        deleteSupplierListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(null, "Do you want to remove this record?", "Confirm deletion", JOptionPane.OK_CANCEL_OPTION);

                if (choice == JOptionPane.OK_OPTION) {
                    if (!supplierGUIListener.supplierProductExistenceChecked()) {
                        supplierContactGUIListener.supplierContactRelationshipDeleted();

                        supplierContactGUIListener.supplierContactDeleted();
                        supplierGUIListener.databaseTableRowRemoved(Integer.parseInt(supplierGUIForm.getFormIdField()));

                        DataBaseManager.resetSupplierOffset();

                        supplierGUIListener.supplierPanelReset();
                    } else {
                        Utilities.notifyOfError("Cannot delete supplier as it has sold products");
                    }
                }
            }
        };

        searchButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (MainFrame.selectedTab) {
                    case 0:
                        customerGUIListener.customerFiltered(userSearchField.getText());
                        break;
                    case 1:
                        supplierGUIListener.supplierFiltered(userSearchField.getText());
                        break;
                }
            }
        };

        cancelSearchListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (MainFrame.selectedTab) {
                    case 0:
                        customerGUIListener.customerPanelReset();
                        userSearchField.setText("");
                        break;
                    case 1:
                        supplierGUIListener.supplierPanelReset();
                        userSearchField.setText("");
                        break;
                }
            }
        };

        userSearchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    switch (MainFrame.selectedTab) {
                        case 0:
                            customerGUIListener.customerFiltered(userSearchField.getText());
                            break;
                        case 1:
                            supplierGUIListener.supplierFiltered(userSearchField.getText());
                            break;
                    }
                }
            }

        });

        usersTabbedPane.add("Customer", customerPanel);
        usersTabbedPane.add("Supplier", supplierPanel);
        usersTablePanel.add(usersTabbedPane, BorderLayout.CENTER);

        userToolbar.setLayout(new FlowLayout(FlowLayout.LEFT));
        userToolbar.add(createRecordButton);
        userToolbar.add(viewRecordButton);
        userToolbar.add(updateRecordButton);
        userToolbar.add(removeRecordButton);
        userSearchPanel.add(userSearchField);
        userSearchPanel.add(userSearchButton);
        userSearchPanel.add(cancelSearchButton);
        userToolbar.add(Box.createHorizontalGlue());
        userToolbar.add(userSearchPanel);

        add(userToolbar, BorderLayout.NORTH);
        add(usersTablePanel, BorderLayout.CENTER);
        add(contactPanel, BorderLayout.SOUTH);

        customerTable = customerPanel.getCustomerTable();
        customerTablePopUp = customerPanel.getTablePopUp();
        supplierTablePopUp = supplierPanel.getTablePopUp();

        /**
         * if no row is selected, the toolbar buttons are not enabled
         */
        if (!customerTable.isRowSelected(customerTable.getSelectedRow())) {
            viewRecordButton.setEnabled(false);
            updateRecordButton.setEnabled(false);
            removeRecordButton.setEnabled(false);
        }

        customerTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int mouseEventsource = e.getButton();

                switch (mouseEventsource) {
                    case 1: {
                        customerContactGUIListener.customerContactInfoDisplayed();

                        if (customerTable.isRowSelected(customerTable.getSelectedRow())) {
                            viewRecordButton.setEnabled(true);
                            updateRecordButton.setEnabled(true);
                            removeRecordButton.setEnabled(true);

                            //setting customerGUIForm object from the underlying list every time a cutomer table row is selected
                            customerGUIListener.customerGUIFormObjectSet();
                        }
                        break;
                    }
                    case 3: {
                        if (customerTable.isCellSelected(customerTable.getSelectedRow(), customerTable.getSelectedColumn())) {
                            //gets the row number corresponding to the mouse cursor position
                            int row = customerTable.rowAtPoint(e.getPoint());

                            //shows the popup menu only if the row the cursor is on is the same as the selected row
                            if (row == customerTable.getSelectedRow()) {
                                customerTablePopUp.show(customerTable, e.getX(), e.getY());
                            }
                        }
                        break;
                    }
                    default:
                        System.out.println("Unexpected value: " + mouseEventsource);
                }
            }
        });

        supplierTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int mouseEventsource = e.getButton();

                switch (mouseEventsource) {
                    case 1: {
                        supplierContactGUIListener.supplierContactInfoDisplayed();

                        if (supplierTable.isRowSelected(supplierTable.getSelectedRow())) {
                            viewRecordButton.setEnabled(true);
                            updateRecordButton.setEnabled(true);
                            removeRecordButton.setEnabled(true);

                            //setting supplierGUIForm object from the underlying list every time a cutomer table row is selected
                            supplierGUIListener.supplierGUIFormObjectSet();
                        }
                        break;
                    }
                    case 3: {
                        if (supplierTable.isCellSelected(supplierTable.getSelectedRow(), supplierTable.getSelectedColumn())) {
                            //gets the row number corresponding to the mouse cursor position
                            int row = supplierTable.rowAtPoint(e.getPoint());

                            //shows the popup menu only if the row the cursor is on is the same as the selected row
                            if (row == supplierTable.getSelectedRow()) {
                                supplierTablePopUp.show(supplierTable, e.getX(), e.getY());
                            }
                        }
                        break;
                    }
                    default:
                        System.out.println("Unexpected value: " + mouseEventsource);
                }
            }
        });

        viewRecordButton.addActionListener(viewCustomerListener);
        createRecordButton.addActionListener(createCustomerListener);
        updateRecordButton.addActionListener(updateCustomerListener);
        removeRecordButton.addActionListener(deleteCustomerListener);

        usersTabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                MainFrame.selectedTab = usersTabbedPane.getSelectedIndex();

                //if a row was previously selected it clears the selection
                customerTable.getSelectionModel().clearSelection();
                supplierTable.getSelectionModel().clearSelection();

                viewRecordButton.setEnabled(false);
                updateRecordButton.setEnabled(false);
                removeRecordButton.setEnabled(false);

                contactPanel.setEmptyContactForm();

                switch (MainFrame.selectedTab) {
                    case 0:
                        viewRecordButton.removeActionListener(viewSupplierListener);
                        createRecordButton.removeActionListener(createSupplierListener);
                        updateRecordButton.removeActionListener(updateSupplierListener);
                        removeRecordButton.removeActionListener(deleteSupplierListener);

                        viewRecordButton.addActionListener(viewCustomerListener);
                        createRecordButton.addActionListener(createCustomerListener);
                        updateRecordButton.addActionListener(updateCustomerListener);
                        removeRecordButton.addActionListener(deleteCustomerListener);

                        supplierGUIListener.supplierPanelReset();
                        break;
                    case 1:
                        viewRecordButton.removeActionListener(viewCustomerListener);
                        createRecordButton.removeActionListener(createCustomerListener);
                        updateRecordButton.removeActionListener(updateCustomerListener);
                        removeRecordButton.removeActionListener(deleteCustomerListener);

                        viewRecordButton.addActionListener(viewSupplierListener);
                        createRecordButton.addActionListener(createSupplierListener);
                        updateRecordButton.addActionListener(updateSupplierListener);
                        removeRecordButton.addActionListener(deleteSupplierListener);
                        
                        customerGUIListener.customerPanelReset();
                        break;
                }
            }
        });

        userSearchButton.addActionListener(searchButtonListener);
        cancelSearchButton.addActionListener(cancelSearchListener);

        /**
         * ****** Setting listeners for popup items *******
         */
        customerPanel.setDialogListener(new PopUpMenuDialogListener() {
            @Override
            public void viewDialogShown() {
//                if (viewCustomerDialog == null || !viewCustomerDialog.isShowing()) {
//                    viewCustomerDialog = new ViewCustomerDialog(customerGUIForm, contactGUIForm, customerGUIListener, supplierGUIListener);
//                }
            	
            	if (crudDialog == null || !crudDialog.isShowing()) {
            		crudDialog = new ViewCustomerDialog(customerGUIForm, contactGUIForm, customerGUIListener, supplierGUIListener);
                }
            }

            @Override
            public void updateDialogShown() {
//                if (updateCustomerDialog == null || !updateCustomerDialog.isShowing()) {
//                    updateCustomerDialog = new UpdateCustomerDialog(customerGUIForm, contactGUIForm, customerGUIListener, supplierGUIListener, customerContactGUIListener);
//                }
            	if (crudDialog == null || !crudDialog.isShowing()) {
            		crudDialog = new UpdateCustomerDialog(customerGUIForm, contactGUIForm, customerGUIListener, supplierGUIListener, customerContactGUIListener);
                }
            }

            @Override
            public void recordRemoved() {
                if (!customerGUIListener.invoicedCustomerExistenceChecked()) {
                    customerContactGUIListener.customerContactRelationshipDeleted();

                    customerContactGUIListener.customerContactDeleted();
                    customerGUIListener.databaseTableRowRemoved(Integer.parseInt(customerGUIForm.getFormIdField()));

                    customerGUIListener.customerPanelReset();

                    DataBaseManager.resetCustomerOffset();
                } else {
                    Utilities.notifyOfError("Cannot delete customer as it has been invoiced");
                }
            }
        });

        supplierPanel.setDialogListener(new PopUpMenuDialogListener() {
            @Override
            public void viewDialogShown() {
                if (viewSupplierDialog == null || !viewSupplierDialog.isShowing()) {
                    viewSupplierDialog = new ViewSupplierDialog(supplierGUIForm, contactGUIForm, customerGUIListener, supplierGUIListener);
                }
            }

            @Override
            public void updateDialogShown() {
                if (updateSupplierDialog == null || !updateSupplierDialog.isShowing()) {
                    updateSupplierDialog = new UpdateSupplierDialog(supplierGUIForm, contactGUIForm, supplierGUIListener, customerGUIListener, supplierContactGUIListener);
                }
            }

            @Override
            public void recordRemoved() {
                if (!supplierGUIListener.supplierProductExistenceChecked()) {
                    supplierContactGUIListener.supplierContactRelationshipDeleted();

                    supplierContactGUIListener.supplierContactDeleted();
                    supplierGUIListener.databaseTableRowRemoved(Integer.parseInt(supplierGUIForm.getFormIdField()));

                    DataBaseManager.resetSupplierOffset();

                    supplierGUIListener.supplierPanelReset();
                } else {
                    Utilities.notifyOfError("Cannot delete supplier as it has sold products");
                }
            }
        });
    }

    public JToolBar getUserToolbar() {
        return userToolbar;
    }

    public CustomerPanel getCustomerPanel() {
        return customerPanel;
    }

    public ContactPanel getContactPanel() {
        return contactPanel;
    }

    public SupplierPanel getSupplierPanel() {
        return supplierPanel;
    }

    public JTabbedPane getUsersTabbedPane() {
        return usersTabbedPane;
    }

    public CustomerGUIForm getCustomerGUIForm() {
        return customerGUIForm;
    }

    public void setCustomerGUIForm(CustomerGUIForm customerGUIForm) {
        this.customerGUIForm = customerGUIForm;
    }

    public ContactGUIForm getContactGUIForm() {
        return contactGUIForm;
    }

    public void setContactGUIForm(ContactGUIForm contactGUIForm) {
        this.contactGUIForm = contactGUIForm;
    }

    public SupplierGUIForm getSupplierGUIForm() {
        return supplierGUIForm;
    }

    public void setSupplierGUIForm(SupplierGUIForm supplierGUIForm) {
        this.supplierGUIForm = supplierGUIForm;
    }

    /*---------------- listener setter ----------------------*/
    public void setCustomerGUIListener(CustomerGUIListener customerGUIListener) {
        this.customerGUIListener = customerGUIListener;
    }

    public void setCustomerContactGUIListener(CustomerContactGUIListener customerContactGUIListener) {
        this.customerContactGUIListener = customerContactGUIListener;
    }

    public void setSupplierGUIListener(SupplierGUIListener supplierGUIListener) {
        this.supplierGUIListener = supplierGUIListener;
    }

    public void setSupplierContactGUIListener(SupplierContactGUIListener supplierContactGUIListener) {
        this.supplierContactGUIListener = supplierContactGUIListener;
    }
}
