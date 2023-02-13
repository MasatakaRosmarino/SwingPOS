package gui;

import controller.CategoryController;
import controller.ContactController;
import controller.CustomerController;
import controller.InvoiceController;
import controller.ProductController;
import controller.SupplierController;
import database.DataBaseManager;
import gui.guiform.CategoryGUIForm;
import gui.guiform.ContactGUIForm;
import gui.guiform.CustomerGUIForm;
import gui.guiform.InvoiceGUIForm;
import gui.guiform.ProductGUIForm;
import gui.guiform.SupplierGUIForm;
import gui.guilistener.GUIAdapter;
import gui.guilistener.MenuBarListener;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileSystemView;
import utilities.InvoiceManager;
import utilities.SettingsFile;
import utilities.SettingsManager;
import utilities.Utilities;

public class MainFrame extends JFrame {

    private JSplitPane mainSplit;
    private CustomerPanel customerPanel;
    private ContactPanel contactPanel;
    private SupplierPanel supplierPanel;
    private JTabbedPane productTabbedPane;
    private ProductPanel productPanel;
    private SalesPanel salesPanel;
    private UsersPanel usersPanel;

    private MainMenu mainMenu;

    private CustomerController customerController;
    private CategoryController categoryController;
    private ContactController contactController;
    private SupplierController supplierController;
    private ProductController productController;
    private InvoiceController invoiceController;
    
    private List<CustomerGUIForm> customerGUIFormsList;
    private CustomerGUIForm customerGUIForm;
    private ContactGUIForm contactGUIForm;
    private List<CategoryGUIForm> categoryGUIFormsList;
    private ProductGUIForm productGUIForm;
    private List<ProductGUIForm> productGUIFormsList;
    private List<String> saleInfosList;
    private Map<String, List<String>> salesInfoMap;
    private List<String> salesYearsList;
    private SupplierGUIForm supplierGUIForm;
    private List<SupplierGUIForm> supplierGUIFormsList;
    private Map<Integer, ProductGUIForm> shoppingCartMap;

    public static int selectedTab;

    private SettingsFile settingsFile;
    private final SettingsManager settingsManager;
    private InvoiceManager invoiceManager;

    public MainFrame() {
    	//load settings file, database and controllers
//        settingsManager = new SettingsManager(FileSystemView.getFileSystemView().getDefaultDirectory().getPath());
        settingsManager = new SettingsManager(Utilities.getMainFolderPath());

        if (settingsManager.fileExists()) {
            settingsFile = (SettingsFile) settingsManager.getSettingsFromFile();

            DataBaseManager.dbUrl = settingsFile.getDbURL();
            DataBaseManager.dbUser = settingsFile.getDbUsername();
            DataBaseManager.dbPassword = settingsFile.getDbPassword();

            DataBaseManager.limit = Integer.parseInt(settingsFile.getQueryLimit());
        }

        DataBaseManager database = new DataBaseManager();
        database.connectToDB();

        if (database.getConnection() == null) {
            new ConnectionConfigurator();
        }

        customerController = new CustomerController(database);
        supplierController = new SupplierController(database);
        categoryController = new CategoryController(database);
        contactController = new ContactController(database);
        productController = new ProductController(database);
        invoiceController = new InvoiceController(database);
    	//
    	
        setTitle("Thrif-T-hrill");

        usersPanel = new UsersPanel();

        customerPanel = usersPanel.getCustomerPanel();
        contactPanel = usersPanel.getContactPanel();
        supplierPanel = usersPanel.getSupplierPanel();

        productTabbedPane = new JTabbedPane();
        productPanel = new ProductPanel();
        salesPanel = new SalesPanel();

        mainMenu = new MainMenu();

        setJMenuBar(mainMenu);

        productTabbedPane.addTab("Stock", productPanel);
        productTabbedPane.addTab("Sales", salesPanel);

        productPanel.setMinimumSize(new Dimension(900, 768));

        mainSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, productTabbedPane, usersPanel);

        add(mainSplit);

//        //load db and program settings
//        settingsManager = new SettingsManager(FileSystemView.getFileSystemView().getDefaultDirectory().getPath());
////        settingsManager = new SettingsManager(Utilities.getMainFolderPath());
//
//        if (settingsManager.fileExists()) {
//            settingsFile = (SettingsFile) settingsManager.getSettingsFromFile();
//
//            DataBaseManager.dbUrl = settingsFile.getDbURL();
//            DataBaseManager.dbUser = settingsFile.getDbUsername();
//            DataBaseManager.dbPassword = settingsFile.getDbPassword();
//
//            DataBaseManager.limit = Integer.parseInt(settingsFile.getQueryLimit());
//        }
//
//        DataBaseManager database = new DataBaseManager();
//        database.connectToDB();
//
//        if (database.getConnection() == null) {
//            new ConnectionConfigurator();
//        }
//
//        customerController = new CustomerController(database);
//        supplierController = new SupplierController(database);
//        categoryController = new CategoryController(database);
//        contactController = new ContactController(database);
//        productController = new ProductController(database);
//        invoiceController = new InvoiceController(database);

        shoppingCartMap = new LinkedHashMap<>();
        productPanel.setShoppingCartMap(shoppingCartMap);

        productGUIFormsList = new ArrayList<>();

        salesInfoMap = new LinkedHashMap<>();

        salesYearsList = invoiceController.fetchInvoiceYears();

        for (String year : salesYearsList) {
            saleInfosList = productController.fetchSalesObjectsListFromDatabase(year);

            salesInfoMap.put(year, saleInfosList);
        }

        populateSalesTree();
        
        //test
        salesPanel.populateInvoiceList(Utilities.getCustomerInvoicePath());
        //

        //fetching customers from DB
        populateCustomerGUITableFromDataBase();

        if (customerController.getDatabaseTableRowsCount() >= DataBaseManager.limit) {
            customerPanel.setNextButtonEnabled(true);
        } else {
            customerPanel.setNextButtonEnabled(false);
        }

        customerPanel.setPreviousButtonEnabled(false);

        productPanel.setNextButtonEnabled(false);
        productPanel.setPreviousButtonEnabled(false);

        //fetching suppliers from DB
        populateSupplierGUITableFromDataBase();

        if (supplierController.getDatabaseTableRowsCount() > DataBaseManager.limit) {
            supplierPanel.setNextButtonEnabled(true);
        } else {
            supplierPanel.setNextButtonEnabled(false);
        }

        supplierPanel.setPreviousButtonEnabled(false);

        //fetching product categories from DB
        categoryGUIFormsList = categoryController.fetchObjectsListFromDatabase();
        productPanel.setCategoryGUIFormsList(categoryGUIFormsList);
        productPanel.populateComboBox(categoryGUIFormsList);

        //clear products in the cart in case there is any
        productController.clearShoppingCart();

        setMinimumSize(new Dimension(1366, 768));
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        if (shoppingCartMap.isEmpty()) {
            productPanel.setCartButtonsEnabled(false);
        }

        usersPanel.setCustomerGUIListener(new GUIAdapter() {
            @Override
            public void customerGUIFormObjectSet() {
                SupplierPanel.isSupplierSelected = false;

                customerGUIForm = customerGUIFormsList.get(customerPanel.getSelectedTableRowIndex());

                CustomerPanel.customerId = Integer.valueOf(customerGUIForm.getFormIdField());

                usersPanel.setCustomerGUIForm(customerGUIForm);

                productPanel.setCustomerGUIForm(customerGUIForm);
            }

            @Override
            public void guiTableRowSaved(CustomerGUIForm customerGUIForm) {
                customerController.saveDatabaseTableRow(customerGUIForm);

                if (!customerGUIFormsList.isEmpty()) {
                    clearCustomerGUIFormsList();
                }

                customerPanel.clearCustomerTable();

                if (customerGUIForm.getFormIdField() == null || customerGUIForm.getFormIdField().isEmpty()) {
                    Utilities.notifyOfRowInsertion("customer");
                } else {
                    Utilities.notifyOfRowUpdate("customer");
                }

                populateCustomerGUITableFromDataBase();
            }

            @Override
            public void databaseTableRowRemoved(int id) {
                if (!customerController.invoicedCustomerExists(customerGUIForm)) {
                    customerController.deleteDatabaseTableRow(customerGUIForm);
                    Utilities.notifyOfRowDeletion("Customer");
                }
            }

            @Override
            public void customerFiltered(String filter) {
                clearCustomerGUIFormsList();
                customerPanel.clearCustomerTable();

                customerGUIFormsList = customerController.fetchFilteredObjectsListFromDatabase(filter);
                customerPanel.populateCustomersTable(customerGUIFormsList);

            }

            @Override
            public void customerPanelReset() {
                clearCustomerGUIFormsList();
                customerPanel.clearCustomerTable();

                resetCustomerTable();

                populateCustomerGUITableFromDataBase();
            }

            @Override
            public void customerPanelEnabled(boolean enabled) {
                customerPanel.setTableEnabled(enabled);
            }
            
            @Override
            public boolean invoicedCustomerExistenceChecked() {
                return customerController.invoicedCustomerExists(customerGUIForm);
            }
        });

        usersPanel.setSupplierGUIListener(new GUIAdapter() {
            @Override
            public void supplierGUIFormObjectSet() {
                SupplierPanel.isSupplierSelected = true;

                supplierGUIForm = supplierGUIFormsList.get(supplierPanel.getSelectedTableRowIndex());
                SupplierPanel.supplierId = Integer.valueOf(supplierGUIForm.getFormIdField());

                usersPanel.setSupplierGUIForm(supplierGUIForm);
            }

            @Override
            public void guiTableRowSaved(SupplierGUIForm supplierGUIForm) {
                supplierController.saveDatabaseTableRow(supplierGUIForm);

                if (!supplierGUIFormsList.isEmpty()) {
                    clearSupplierGUIFormsList();
                }

                supplierPanel.clearSupplierTable();

                if (supplierGUIForm.getFormIdField() == null || supplierGUIForm.getFormIdField().isEmpty()) {
                    Utilities.notifyOfRowInsertion("supplier");
                } else {
                    Utilities.notifyOfRowUpdate("supplier");
                }

                populateSupplierGUITableFromDataBase();
            }

            @Override
            public void supplierFiltered(String filter) {
                clearSupplierGUIFormsList();
                supplierPanel.clearSupplierTable();

                supplierGUIFormsList = supplierController.fetchFilteredObjectsListFromDatabase(filter);
                supplierPanel.populateSuppliersTable(supplierGUIFormsList);
            }

            @Override
            public void supplierPanelReset() {
                clearSupplierGUIFormsList();
                supplierPanel.clearSupplierTable();

                resetSupplierTable();

                populateSupplierGUITableFromDataBase();
            }

            @Override
            public void supplierPanelEnabled(boolean enabled) {
                supplierPanel.setTableEnabled(enabled);
            }

            @Override
            public void databaseTableRowRemoved(int id) {
                if (!supplierController.suppliedProductExists(supplierGUIForm)) {
                    supplierController.deleteDatabaseTableRow(supplierGUIForm);
                    Utilities.notifyOfRowDeletion("Supplier");
                }
            }

            @Override
            public boolean supplierProductExistenceChecked() {
                return supplierController.suppliedProductExists(supplierGUIForm);
            }
            
            
        });

        usersPanel.setCustomerContactGUIListener(new GUIAdapter() {
            @Override
            public void customerContactInfoDisplayed() {
                if (customerPanel.getSelectedTableRowIndex() >= 0) {
                    customerGUIForm = customerGUIFormsList.get(customerPanel.getSelectedTableRowIndex());

                    CustomerPanel.customerId = Integer.valueOf(customerGUIForm.getFormIdField());
                } else {
                    System.out.println("No table index available");
                }

                //setting model based on the selected customertable row index
                if (contactController.customerContactTableRowExists(customerGUIForm)) {
                    //fetch contact object from contact table in the DB
                    contactGUIForm = contactController.fetchCustomerContactFromDatabase(customerGUIForm);
                    usersPanel.setContactGUIForm(contactGUIForm);
                    contactPanel.setContactGUIForm(contactGUIForm);

                    //passing contactGUIForm fields to the contact form
                    contactPanel.setContactFormFields(contactGUIForm);
                } else {
                    usersPanel.setContactGUIForm(contactGUIForm);
                    contactPanel.setContactGUIForm(contactGUIForm);
                }
            }

            @Override
            public void guiTableRowSaved(ContactGUIForm contactGUIForm) {
                contactController.saveDatabaseTableRow(contactGUIForm);

                contactPanel.setEmptyContactForm();
            }

            public void customerRelationalIdsInserted(int customerId, int contactId) {
                contactController.createCustomerContactRelation(customerId, contactId);
            }

            @Override
            public int[] newRowsIdsRetrieved() {
                int contactId = contactController.getHighestIdValue();
                int customerId = customerController.getHighestIdValue();

                int[] relationalIds = {customerId, contactId};

                return relationalIds;
            }
            
            @Override
            public void customerContactRelationshipDeleted() {
                customerController.deleteCustomerContactRelationship(customerGUIForm, contactGUIForm);
            }

            @Override
            public void customerContactDeleted() {
                contactController.deleteDatabaseTableRow(contactGUIForm);
            }
            
        });

        usersPanel.setSupplierContactGUIListener(new GUIAdapter() {
            @Override
            public void supplierContactInfoDisplayed() {
                if (supplierPanel.getSelectedTableRowIndex() >= 0) {
                    supplierGUIForm = supplierGUIFormsList.get(supplierPanel.getSelectedTableRowIndex());
                    SupplierPanel.supplierId = Integer.valueOf(supplierGUIForm.getFormIdField());

                } else {
                    System.out.println("No table index available");
                }

                //setting model based on the selected customertable row index
                if (contactController.supplierContactTableRowExists(supplierGUIForm)) {
                    //fetch contact object from contact table in the DB                   
                    contactGUIForm = contactController.fetchSupplierContactFromDatabase(supplierGUIForm);

                    usersPanel.setContactGUIForm(contactGUIForm);
                    contactPanel.setContactGUIForm(contactGUIForm);

                    //passing contactGUIForm fields to the contact form
                    contactPanel.setContactFormFields(contactGUIForm);
                } else {
                    usersPanel.setContactGUIForm(contactGUIForm);
                    contactPanel.setContactGUIForm(contactGUIForm);
                }
            }

            @Override
            public int[] newRowsIdsRetrieved() {
                int contactId = contactController.getHighestIdValue();
                int supplierId = supplierController.getHighestIdValue();

                int[] relationalIds = {supplierId, contactId};

                return relationalIds;
            }

            @Override
            public void guiTableRowSaved(ContactGUIForm contactGUIForm) {
                contactController.saveDatabaseTableRow(contactGUIForm);

                contactPanel.setEmptyContactForm();
            }

            @Override
            public void supplierRelationalIdsInserted(int supplierId, int contactId) {
                contactController.createSupplierContactRelation(supplierId, contactId);
            }

            @Override
            public void supplierContactRelationshipDeleted() {
                supplierController.deleteSupplierContactRelationship(supplierGUIForm, contactGUIForm);
            }

            @Override
            public void supplierContactDeleted() {
                contactController.deleteDatabaseTableRow(contactGUIForm);
            }
        });

        customerPanel.setCustomerGUIListener(new GUIAdapter() {
            @Override
            public void previousPageDisplayed() {
                if (DataBaseManager.customerOffset >= 0) {
                    DataBaseManager.customerOffset -= DataBaseManager.limit;

                    clearCustomerGUIFormsList();
                    customerPanel.clearCustomerTable();
                    populateCustomerGUITableFromDataBase();

                    if (DataBaseManager.customerOffset < DataBaseManager.limit) {
                        customerPanel.setPreviousButtonEnabled(false);
                        customerPanel.setNextButtonEnabled(true);
                        DataBaseManager.resetCustomerOffset();
                    }

                    if (DataBaseManager.customerOffset <= (customerController.getDatabaseTableRowsCount() - DataBaseManager.limit)) {
                        customerPanel.setNextButtonEnabled(true);
                    }
                }
            }

            @Override
            public void nextPageDisplayed() {
                if (DataBaseManager.customerOffset <= (customerController.getDatabaseTableRowsCount() - DataBaseManager.limit)) {
                    DataBaseManager.customerOffset += DataBaseManager.limit;

                    clearCustomerGUIFormsList();
                    customerPanel.clearCustomerTable();
                    populateCustomerGUITableFromDataBase();

                    customerPanel.setPreviousButtonEnabled(true);

                    if (DataBaseManager.customerOffset >= (customerController.getDatabaseTableRowsCount() - DataBaseManager.limit)) {
                        customerPanel.setPreviousButtonEnabled(true);
                        customerPanel.setNextButtonEnabled(false);
                    }
                }
            }
        });
        
        customerPanel.setCustomerContactGUIListener(new GUIAdapter(){
            @Override
            public void customerContactRelationshipDeleted() {
                customerController.deleteCustomerContactRelationship(customerGUIForm, contactGUIForm);
            }

            @Override
            public void customerContactDeleted() {
                contactController.deleteDatabaseTableRow(contactGUIForm);
            } 
            
        });

        supplierPanel.setSupplierGUIListener(new GUIAdapter() {
            @Override
            public void previousPageDisplayed() {
                if (DataBaseManager.supplierOffset >= 0) {
                    DataBaseManager.supplierOffset -= DataBaseManager.limit;

                    clearSupplierGUIFormsList();
                    supplierPanel.clearSupplierTable();
                    populateSupplierGUITableFromDataBase();

                    if (DataBaseManager.supplierOffset < DataBaseManager.limit) {
                        supplierPanel.setPreviousButtonEnabled(false);
                        supplierPanel.setNextButtonEnabled(true);
                        DataBaseManager.resetSupplierOffset();
                    }

                    if (DataBaseManager.supplierOffset <= (supplierController.getDatabaseTableRowsCount() - DataBaseManager.limit)) {
                        supplierPanel.setNextButtonEnabled(true);
                    }
                }

            }

            @Override
            public void nextPageDisplayed() {
                if (DataBaseManager.supplierOffset <= (supplierController.getDatabaseTableRowsCount() - DataBaseManager.limit)) {
                    DataBaseManager.supplierOffset += DataBaseManager.limit;

                    clearSupplierGUIFormsList();
                    supplierPanel.clearSupplierTable();
                    populateSupplierGUITableFromDataBase();

                    supplierPanel.setPreviousButtonEnabled(true);

                    if (DataBaseManager.supplierOffset >= (supplierController.getDatabaseTableRowsCount() - DataBaseManager.limit)) {
                        supplierPanel.setNextButtonEnabled(false);
                        supplierPanel.setPreviousButtonEnabled(true);
                    }
                }
            }
        });

        productPanel.setProductGUIListener(new GUIAdapter() {
            @Override
            public void productsTableDisplayed() {
                if (productPanel.getSelectedCategoryIndex() < 0) {
                    productPanel.setCreateProductButtonEnabled(false);
                }

                if (!productGUIFormsList.isEmpty()) {
                    clearProductGUIFormsList();
                }

                productPanel.clearProductsTable();

                populateProductGUITableFromDataBase();

                DataBaseManager.resetProductOffset();

                if (productController.getDatabaseTableRowsCount(ProductPanel.productCategoryId) > DataBaseManager.limit) {
                    productPanel.setNextButtonEnabled(true);
                } else {
                    productPanel.setNextButtonEnabled(false);
                }

                //the if block prevents the index out of bound exception to arise
                if (!productGUIFormsList.isEmpty()) {
                    productGUIForm = productGUIFormsList.get((productPanel.getSelectedTableRowIndex() + 1));
                }
            }

            @Override
            public void productGUIFormObjectSet() {
                productGUIForm = productGUIFormsList.get(productPanel.getSelectedTableRowIndex());
                productPanel.setProductGUIForm(productGUIForm);
            }

            @Override
            public void guiTableRowSaved(ProductGUIForm productGUIForm) {
                productController.saveDatabaseTableRow(productGUIForm);

                if (productGUIForm.getFormIdField() == null || productGUIForm.getFormIdField().isEmpty()) {
                    Utilities.notifyOfRowInsertion("product");
                } else {
                    Utilities.notifyOfRowUpdate("product");
                }

                clearProductGUIFormsList();
                productPanel.clearProductsTable();

                populateProductGUITableFromDataBase();
            }

            @Override
            public void databaseTableRowRemoved(int id) {
                productController.deleteDatabaseTableRow(productGUIForm);

                //updating customer list
                if (!productController.databaseTableRowExists(productGUIForm)) {
                    Utilities.notifyOfRowDeletion("Product");

                    productGUIForm = productGUIFormsList.get(productPanel.getSelectedTableRowIndex());
                    productPanel.setProductGUIForm(productGUIForm);

                    productPanel.removeRowFromTable(productPanel.getSelectedTableRowIndex());
                }
            }

            @Override
            public void previousPageDisplayed() {
                if (DataBaseManager.productOffset >= 0) {
                    DataBaseManager.productOffset -= DataBaseManager.limit;

                    clearProductGUIFormsList();
                    productPanel.clearProductsTable();
                    populateProductGUITableFromDataBase();

                    if (DataBaseManager.productOffset < DataBaseManager.limit) {
                        productPanel.setPreviousButtonEnabled(false);
                        productPanel.setNextButtonEnabled(true);
                        DataBaseManager.resetProductOffset();
                    }

                    if (DataBaseManager.productOffset <= (productController.getDatabaseTableRowsCount(ProductPanel.productCategoryId) - DataBaseManager.limit)) {
                        productPanel.setNextButtonEnabled(true);
                    }
                }
            }

            @Override
            public void nextPageDisplayed() {
                if (DataBaseManager.productOffset <= (productController.getDatabaseTableRowsCount(ProductPanel.productCategoryId) - DataBaseManager.limit)) {
                    DataBaseManager.productOffset += DataBaseManager.limit;

                    clearProductGUIFormsList();
                    productPanel.clearProductsTable();
                    populateProductGUITableFromDataBase();

                    productPanel.setPreviousButtonEnabled(true);

                    if (DataBaseManager.productOffset >= (productController.getDatabaseTableRowsCount(ProductPanel.productCategoryId) - DataBaseManager.limit)) {
                        productPanel.setPreviousButtonEnabled(true);
                        productPanel.setNextButtonEnabled(false);
                    }
                }
            }

            @Override
            public void productAddedToCart(ProductGUIForm productGUIForm) {
                if (customerPanel.getSelectedTableRowIndex() < 0) {
                    JOptionPane.showMessageDialog(MainFrame.this, "Please select a customer first", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    productPanel.setProductGUIForm(productGUIForm);

                    productPanel.addProductToShoppingCartPane(productGUIForm);
                    productController.addProductToCart(productGUIForm);

                    productGUIFormsList.remove(productPanel.getSelectedTableRowIndex());
                    productPanel.removeRowFromTable(productPanel.getSelectedTableRowIndex());

                    productPanel.updateTotalAmountPanel(shoppingCartMap);

                    if (!shoppingCartMap.isEmpty()) {
                        customerPanel.getCustomerTable().setEnabled(false);
                        productPanel.setCartButtonsEnabled(true);
                    }
                }
            }

            @Override
            public void productRemovedFromCart(int index) {
                productGUIForm = shoppingCartMap.get(index);

                productController.removeProductFromCart(productGUIForm);

                productPanel.removeShoppingCartItemFromPanel(index);

                shoppingCartMap.remove(index);

                productGUIFormsList.clear();
                productPanel.clearProductsTable();

                productGUIFormsList = productController.fetchObjectsListFromDatabase(ProductPanel.productCategoryId);

                productPanel.populateProductsTable(productGUIFormsList);

                productPanel.updateTotalAmountPanel(shoppingCartMap);

                if (shoppingCartMap.isEmpty()) {
                    customerPanel.getCustomerTable().setEnabled(true);
                    productPanel.setCartButtonsEnabled(false);
                }
            }

            @Override
            public void cartEmptied() {
                clearBackEndShoppingCart();

                clearFrontEndShoppingCart();

                populateProductGUITableFromDataBase();
            }

            @Override
            public void productFiltered(String filter, int foreingKey) {
                clearProductGUIFormsList();
                productPanel.clearProductsTable();

                productGUIFormsList = productController.fetchFilteredDatabaseTableRows(filter);
                productPanel.populateProductsTable(productGUIFormsList);
            }

            @Override
            public void productPanelReset() {
                clearProductGUIFormsList();
                productPanel.clearProductsTable();

                populateProductGUITableFromDataBase();
            }

            @Override
            public void productPanelEnabled(boolean enabled) {
                productPanel.setTableEnabled(enabled);
            }
        });

        productPanel.setInvoiceGUIListener(new GUIAdapter() {
            @Override
            public void invoiceGenerated(InvoiceGUIForm invoiceGUIForm) {
                invoiceController.saveDatabaseTableRow(invoiceGUIForm, customerGUIForm);

                int lastCreatedInvoiceId = invoiceController.getHighestIdValue();

                List<ProductGUIForm> purchasedProductsList = new ArrayList<>();

                for (Map.Entry<Integer, ProductGUIForm> entry : shoppingCartMap.entrySet()) {
                    entry.getValue().setInvoiceId(Integer.toString(lastCreatedInvoiceId));

                    invoiceController.updateDatabaseTableRow(entry.getValue());

                    purchasedProductsList.add(entry.getValue());
                }

                invoiceManager = new InvoiceManager();
                
                String filePath = settingsManager.getInvoiceFilePath() + "\\Invoice_" + Integer.toString(lastCreatedInvoiceId) + "_" + customerGUIForm.getName() + "_" + customerGUIForm.getLastname() + ".pdf";
                
                invoiceManager.generateInvoice(filePath, invoiceGUIForm, lastCreatedInvoiceId, customerGUIForm, contactGUIForm, purchasedProductsList);
                
                try {
                    Desktop.getDesktop().open(new File(settingsManager.getInvoiceFilePath()));
                } catch (IOException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }

                clearFrontEndShoppingCart();

                populateProductGUITableFromDataBase();

                salesYearsList.clear();
                salesInfoMap.clear();
                salesPanel.removeAll();

                salesYearsList = invoiceController.fetchInvoiceYears();

                for (String year : salesYearsList) {
                    saleInfosList = productController.fetchSalesObjectsListFromDatabase(year);

                    salesInfoMap.put(year, saleInfosList);
                }

                salesPanel.populateSalesTree(salesInfoMap);
                
                salesPanel.populateInvoiceList(Utilities.getCustomerInvoicePath());
            }
        });

        mainMenu.setMenuBarListener(new MenuBarListener() {
            @Override
            public void systemExited() {
                quitApplication();
            }

            @Override
            public void settingsSaved() {
                settingsFile = mainMenu.getSettingsFile();

                settingsManager.writeSettingsToFile(settingsFile);

                Utilities.notifyOfSettingsChange();
            }
        });

        productTabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

            }
        });

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                quitApplication();
            }
        });
    }

    private void quitApplication() {
        int choice = JOptionPane.showConfirmDialog(MainFrame.this, "Do you want to quit the application?", "Confirm exit", JOptionPane.OK_CANCEL_OPTION);

        if (choice == JOptionPane.OK_OPTION) {
            productController.clearShoppingCart();
            clearBackEndShoppingCart();
            clearFrontEndShoppingCart();

            DataBaseManager.disconnectFromDB();
            System.exit(0);
            dispose();
            System.gc();
        }
    }

    private void clearSupplierGUIFormsList() {
        supplierGUIFormsList.clear();
    }

    private void clearCustomerGUIFormsList() {
        customerGUIFormsList.clear();
    }

    private void clearProductGUIFormsList() {
        productGUIFormsList.clear();
    }

    private void populateSalesTree() {
        salesPanel.populateSalesTree(salesInfoMap);
    }

    private void populateCustomerGUITableFromDataBase() {
        customerGUIFormsList = customerController.fetchObjectsListFromDatabase();
        customerPanel.populateCustomersTable(customerGUIFormsList);
    }

    private void resetCustomerTable() {
        DataBaseManager.resetCustomerOffset();
        customerPanel.setPreviousButtonEnabled(false);
        customerPanel.setNextButtonEnabled(true);
        contactPanel.setEmptyContactForm();
    }

    private void resetSupplierTable() {
        DataBaseManager.resetSupplierOffset();
        supplierPanel.setPreviousButtonEnabled(false);
        supplierPanel.setNextButtonEnabled(true);
        contactPanel.setEmptyContactForm();
    }

    private void populateSupplierGUITableFromDataBase() {
        supplierGUIFormsList = supplierController.fetchObjectsListFromDatabase();
        supplierPanel.populateSuppliersTable(supplierGUIFormsList);
    }

    private void populateProductGUITableFromDataBase() {
        productGUIFormsList = productController.fetchObjectsListFromDatabase(ProductPanel.productCategoryId);
        productPanel.populateProductsTable(productGUIFormsList);
    }

    private void clearBackEndShoppingCart() {
        productController.clearShoppingCart();
    }

    private void clearFrontEndShoppingCart() {
        shoppingCartMap.clear();
        productPanel.clearShoppingCartPane();

        ProductPanel.purchaseListIndex = 0;

        productGUIFormsList.clear();
        productPanel.clearProductsTable();

        //resets total amount to 0
        productPanel.resetTotalAmountPanel();

        if (shoppingCartMap.isEmpty()) {
            customerPanel.getCustomerTable().setEnabled(true);
            productPanel.setCartButtonsEnabled(false);
        }
    }
}
