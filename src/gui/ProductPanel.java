package gui;

import database.DataBaseManager;
import gui.dialog.BaseDialog;
import gui.dialog.CreateInvoiceDialog;
import gui.dialog.CreateProductDialog;
import gui.dialog.UpdateProductDialog;
import gui.dialog.ViewProductDialog;
import gui.guiform.CategoryGUIForm;
import gui.guiform.CustomerGUIForm;
import gui.guiform.ProductGUIForm;
import gui.guilistener.InvoiceGUIListener;
import gui.guilistener.ProductGUIListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import utilities.Utilities;

public class ProductPanel extends JPanel {

    private final JPanel categoryPanel;
    private final JLabel categoryLabel;
    private JComboBox<String> categoryComboBox;
    private final DefaultComboBoxModel<String> categoryComoboBoxModel;
    private JTable productTable;
    private final PointOfSaleTableModel productTableModel;
    private final JPanel productTablePanel;
    private final JScrollPane tableScroll;
    private JPopupMenu tablePopup;
    private final JMenuItem viewItem;
    private final JMenuItem updateItem;
    private final JMenuItem removeItem;
    private final JPanel productToolBar;
    private JButton viewProductButton;
    private JButton createProductButton;
    private JButton updateProductButton;
    private JButton removeProductButton;
    private JButton addToCartButton;

    private final JPanel productSearchPanel;
    private JTextField productSearchField;
    private final JButton productSearchButton;
    private final JButton clearSearchButton;
    
    private final JPanel arrowsPanel;
    private final JButton nextButton;
    private final JButton previousButton;

    private final JPanel categoryAndProductPanel;
    private final JPanel productPanel;
    private final JPanel purchasePanel;
    private final JPanel shoppingCartPanel;
    private final JPanel totalAmountPanel;
    private final JLabel totalAmountLabel;
    private final JButton confirmPurchaseButton;
    private final JButton emptyCartButton;
    private final JScrollPane shoppingCartScroll;
    private final JPanel shoppingCartTitle;

    private final GridBagConstraints constraint;

//    private ViewProductDialog viewProductDialog;
//    private CreateProductDialog createProductDialog;
//    private UpdateProductDialog updateProductDialog;
    
    private BaseDialog crudDialog;

    //variables for populating the table
    private final String columnNames[] = {"Name", "Price", "Items per unit", "Condition"};
    private Object[][] tableItems;

    private List<CategoryGUIForm> categoryGUIFormsList;

    private ProductGUIForm productGUIForm;

    private ProductGUIListener productGUIListener;
    private InvoiceGUIListener invoiceGUIListener;

    private final List<JButton> removeButtonsList;
    private final List<JLabel> productNameList;
    private final List<JLabel> productPriceList;

    //if using a list, when removing the last element, you get an indexoutofbound exception
    private Map<Integer, ProductGUIForm> shoppingCartMap;
    private CustomerGUIForm customerGUIForm;
    
    private CreateInvoiceDialog createInvoiceDialog;
    public static int purchaseListIndex = 0;
    
    public static int productCategoryId;
    public static String productCategory;
    public static String purchaseTotalAmount;
    public static String purchaseFinalAmount;

    public ProductPanel() {
        setLayout(new BorderLayout());

        removeButtonsList = new ArrayList<>();
        productNameList = new ArrayList<>();
        productPriceList = new ArrayList<>();

        categoryGUIFormsList = new ArrayList<>();

        addToCartButton = new JButton("Add to cart");

        categoryAndProductPanel = new JPanel();
        categoryAndProductPanel.setLayout(new BorderLayout());
        
        productPanel = new JPanel();
        productPanel.setLayout(new BorderLayout());
        
        productTablePanel = new JPanel();
        productTablePanel.setLayout(new BorderLayout());
        
        //arrow buttons
        arrowsPanel = new JPanel();
        arrowsPanel.setLayout(new FlowLayout());
        nextButton = new JButton(">");
        previousButton = new JButton("<");

        purchasePanel = new JPanel();
        purchasePanel.setLayout(new BorderLayout());
        
        shoppingCartTitle = new JPanel();
        shoppingCartTitle.setLayout(new FlowLayout(FlowLayout.CENTER));

        shoppingCartPanel = new JPanel(new GridBagLayout());

        shoppingCartScroll = new JScrollPane(shoppingCartPanel);

        constraint = new GridBagConstraints();

        //setting up constraints parameters
        constraint.weightx = 1;
        constraint.weighty = 0.1;
        constraint.insets = new Insets(5, 5, 5, 5);
        constraint.gridx = 0;
        constraint.gridy = 0;

        totalAmountPanel = new JPanel();
        totalAmountLabel = new JLabel("Total: 0.0");
        confirmPurchaseButton = new JButton("Confirm order");
        emptyCartButton = new JButton("Empty cart");

        totalAmountPanel.setLayout(new FlowLayout());

        categoryComboBox = new JComboBox<>();
        categoryComoboBoxModel = new DefaultComboBoxModel<>();

        categoryPanel = new JPanel();
        categoryLabel = new JLabel("Products categories: ");

        productTableModel = new PointOfSaleTableModel(tableItems, columnNames);
        productTable = new JTable(productTableModel);
        tableScroll = new JScrollPane(productTable);

        productTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        productTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        categoryComoboBoxModel.addElement("Select a category");
        categoryComboBox.setModel(categoryComoboBoxModel);

        categoryPanel.setLayout(new FlowLayout());

        categoryPanel.add(categoryLabel);
        categoryPanel.add(categoryComboBox);

        //JPopup and its components
        tablePopup = new JPopupMenu();
        viewItem = new JMenuItem("View");
        updateItem = new JMenuItem("Update");
        removeItem = new JMenuItem("Remove");

        tablePopup.add(viewItem);
        tablePopup.add(updateItem);
        tablePopup.add(removeItem);
        //

        //Toolbar
        productToolBar = new JPanel();
        createProductButton = new JButton("New");
        viewProductButton = new JButton("View");
        updateProductButton = new JButton("Update");
        removeProductButton = new JButton("Remove");

        productSearchPanel = new JPanel();
        productSearchPanel.setLayout(new FlowLayout());
        productSearchField = new JTextField(10);
        productSearchButton = new JButton("Search");
        clearSearchButton = new JButton("X");

        productToolBar.setLayout(new FlowLayout());
        
        addToCartButton.setMargin(new Insets(1, 1, 1, 1));
        createProductButton.setMargin(new Insets(1, 1, 1, 1));
        viewProductButton.setMargin(new Insets(1, 1, 1, 1));
        updateProductButton.setMargin(new Insets(1, 1, 1, 1));
        removeProductButton.setMargin(new Insets(1, 1, 1, 1));
        productSearchButton.setMargin(new Insets(1, 1, 1, 1));
        clearSearchButton.setMargin(new Insets(2, 2, 2, 2));
        confirmPurchaseButton.setMargin(new Insets(1, 1, 1, 1));
        emptyCartButton.setMargin(new Insets(1, 1, 1, 1));

        productToolBar.add(addToCartButton);
        productToolBar.add(createProductButton);
        productToolBar.add(viewProductButton);
        productToolBar.add(updateProductButton);
        productToolBar.add(removeProductButton);
        productSearchPanel.add(productSearchField);
        productSearchPanel.add(productSearchButton);
        productSearchPanel.add(clearSearchButton);
        productToolBar.add(Box.createHorizontalGlue());
        productToolBar.add(productSearchPanel);

        if (!productTable.isRowSelected(productTable.getSelectedRow())) {
            addToCartButton.setEnabled(false);
            createProductButton.setEnabled(false);
            viewProductButton.setEnabled(false);
            updateProductButton.setEnabled(false);
            removeProductButton.setEnabled(false);
        }
        
        arrowsPanel.add(previousButton);
        arrowsPanel.add(nextButton);
        
        productTablePanel.add(tableScroll, BorderLayout.CENTER);

        categoryAndProductPanel.add(categoryPanel, BorderLayout.NORTH);
        categoryAndProductPanel.add(productPanel, BorderLayout.CENTER);
        productPanel.add(productToolBar, BorderLayout.NORTH);
        productPanel.add(productTablePanel, BorderLayout.CENTER);
        productPanel.add(arrowsPanel, BorderLayout.SOUTH);

        totalAmountPanel.add(totalAmountLabel);
        totalAmountPanel.add(confirmPurchaseButton);
        totalAmountPanel.add(emptyCartButton);
        
        purchasePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        shoppingCartScroll.setBorder(null);
        
        shoppingCartTitle.add(new JLabel("ORDER"));

        purchasePanel.add(shoppingCartTitle, BorderLayout.NORTH);
        purchasePanel.add(shoppingCartScroll, BorderLayout.CENTER);
        purchasePanel.add(totalAmountPanel, BorderLayout.SOUTH);

        add(categoryAndProductPanel, BorderLayout.CENTER);
        add(purchasePanel, BorderLayout.WEST);

        categoryComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String categoryItem = (String) e.getItem();
                    
                    DataBaseManager.resetProductOffset();
                    setPreviousButtonEnabled(false);

                    if (categoryItem.equals("Select a category")) {
                        addToCartButton.setEnabled(false);
                        createProductButton.setEnabled(false);
                        viewProductButton.setEnabled(false);
                        updateProductButton.setEnabled(false);
                        removeProductButton.setEnabled(false);
                        
                        productCategoryId = -1;
                        System.out.println("Prod category id: " + productCategoryId);
                    }else{
                        productCategoryId = categoryComboBox.getSelectedIndex();
                        productCategory = (String) categoryComboBox.getSelectedItem();
                    }

                    if (!productTable.isRowSelected(productTable.getSelectedRow())) {
                        addToCartButton.setEnabled(false);
                        viewProductButton.setEnabled(false);
                        updateProductButton.setEnabled(false);
                        removeProductButton.setEnabled(false);
                    }

                    createProductButton.setEnabled(true);
                    viewProductButton.setEnabled(false);
                    updateProductButton.setEnabled(false);
                    removeProductButton.setEnabled(false);

                    //getting the CategoryGUIForm object from the list in order to get the id field
                    int categoryListIndex = categoryComboBox.getSelectedIndex();

                    //this if statements prevents an array index out of bound exception
                    if (categoryListIndex != -1) {
                        productGUIListener.productsTableDisplayed();
                    }
                }
            }
        });

        productTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int mouseEventsource = e.getButton();

                switch (mouseEventsource) {
                    case 1: {
                        if (productTable.isRowSelected(productTable.getSelectedRow())) {
                            addToCartButton.setEnabled(true);
                            viewProductButton.setEnabled(true);
                            updateProductButton.setEnabled(true);
                            removeProductButton.setEnabled(true);

                            productGUIListener.productGUIFormObjectSet();
                        }
                        break;
                    }
                    case 3: {
                        if (productTable.isCellSelected(productTable.getSelectedRow(), productTable.getSelectedColumn())) {
                            //gets the row number corresponding to the mouse cursor position
                            int row = productTable.rowAtPoint(e.getPoint());

                            //shows the popup menu only if the row the cursor is on is the same as the selected row
                            if (row == productTable.getSelectedRow()) {
                                tablePopup.show(productTable, e.getX(), e.getY());
                            }
                        }
                        break;
                    }
                    default:
                        System.out.println("Unexpected value: " + mouseEventsource);
                }
            }
        });
        
        viewItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                callViewProductDialog();
            }
        });
        
        updateItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                callUpdateProductDialog();
            }
        });
        
        removeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeProduct();
            }
        });

        createProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                callCreateProductDialog();
            }
        });
        
        viewProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                callViewProductDialog();
            }
        });

        updateProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                callUpdateProductDialog();
            }
        });

        removeProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeProduct();
            }
        });
        
        productSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productGUIListener.productFiltered(productSearchField.getText(), productCategoryId);
            }
        });
        
        clearSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productGUIListener.productPanelReset();
                productSearchField.setText("");
            }
        });
        
        productSearchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == 10){
                    productGUIListener.productFiltered(productSearchField.getText(), productCategoryId);
                }
            }
        });
        
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productGUIListener.nextPageDisplayed();
            }
        });
        
        previousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productGUIListener.previousPageDisplayed();
            }
        });
        
        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productGUIListener.productAddedToCart(productGUIForm);
            }
        });

        emptyCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productGUIListener.cartEmptied();
            }
        });

        confirmPurchaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (createInvoiceDialog == null || !createInvoiceDialog.isShowing()) {
                    createInvoiceDialog = new CreateInvoiceDialog(customerGUIForm, shoppingCartMap, invoiceGUIListener);
                }
            }
        });
    }
    
    private void callViewProductDialog(){
    	if (crudDialog == null || !crudDialog.isShowing()) {
    		crudDialog = new ViewProductDialog(productGUIForm, productGUIListener);
        }
    }
    
    private void callCreateProductDialog(){
        if (MainFrame.selectedTab != 1 || !SupplierPanel.isSupplierSelected) {
            JOptionPane.showMessageDialog(null, "Please select a supplier", null, JOptionPane.WARNING_MESSAGE);
        }

        if (MainFrame.selectedTab == 1 && SupplierPanel.isSupplierSelected) {
        	if (crudDialog == null || !crudDialog.isShowing()) {
        		crudDialog = new CreateProductDialog(ProductPanel.this, productGUIListener);
            }
        }
    }
    
    private void callUpdateProductDialog(){
    	if (crudDialog == null || !crudDialog.isShowing()) {
    		crudDialog = new UpdateProductDialog(ProductPanel.this, productGUIListener);
        }
    }
    
    private void removeProduct(){
        int choice = JOptionPane.showConfirmDialog(null, "Do you want to remove this record?", "Confirm deletion", JOptionPane.OK_CANCEL_OPTION);

        if (choice == JOptionPane.OK_OPTION) {
            productGUIListener.databaseTableRowRemoved(Integer.valueOf(productGUIForm.getFormIdField()));
        }
    }

    public void clearShoppingCartPane() {
        shoppingCartPanel.removeAll();
        shoppingCartPanel.revalidate();
        shoppingCartPanel.repaint();
    }

    /**
     * adds a new product to the cart and refreshes the purchase list panel
     * after every addition
     *
     * @param productGUIForm
     */
    public void addProductToShoppingCartPane(ProductGUIForm productGUIForm) {
        ProductGUIForm productGUIFormInMap;

        try {
            productGUIFormInMap = (ProductGUIForm) productGUIForm.clone();
            shoppingCartMap.put(purchaseListIndex, productGUIFormInMap);
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(ProductPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

        //add selected product to purchase list panel
        JLabel productName = new JLabel(productGUIForm.getProductName());
        productNameList.add(purchaseListIndex, productName);

        JLabel productPrice = new JLabel(productGUIForm.getSellingPrice());
        productPriceList.add(purchaseListIndex, productPrice);

        JButton removeButton = new JButton("-");
        removeButton.setMargin(new Insets(1, 3, 1, 3));
        removeButtonsList.add(purchaseListIndex, removeButton);

        constraint.gridx = 0;
        constraint.anchor = GridBagConstraints.LINE_START;
        shoppingCartPanel.add(productName, constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_END;
        shoppingCartPanel.add(productPrice, constraint);

        constraint.gridx = 2;
        constraint.anchor = GridBagConstraints.LINE_END;
        shoppingCartPanel.add(removeButtonsList.get(purchaseListIndex), constraint);

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productGUIListener.productRemovedFromCart(removeButtonsList.indexOf(removeButton));
            }
        });

        purchaseListIndex++;

        constraint.gridy++;
        
        addToCartButton.setEnabled(false);

        /**
         * repaints the purchaseList every time an item is added
         */
        revalidate();
    }

    public void removeShoppingCartItemFromPanel(int index) {
        shoppingCartPanel.remove(productNameList.get(index));
        shoppingCartPanel.remove(productPriceList.get(index));
        shoppingCartPanel.remove(removeButtonsList.get(index));

        purchasePanel.repaint();
        purchasePanel.revalidate();
    }

    public void updateTotalAmountPanel(Map<Integer, ProductGUIForm> shoppingCartItems) {
        double totalAmount = 0.0;

        for (Map.Entry<Integer, ProductGUIForm> entry : shoppingCartItems.entrySet()) {
            totalAmount += Utilities.getIntegerValueFromDecimalNumber(entry.getValue().getSellingPrice());
        }

        float decimalTotalAmount = ((float) totalAmount) / 100;
        totalAmountLabel.setText("Total: " + decimalTotalAmount);

        totalAmountLabel.repaint();
        
        purchaseTotalAmount = Float.toString(decimalTotalAmount);
    }

    public void resetTotalAmountPanel() {
        totalAmountLabel.setText("Total: 0.0");

        totalAmountLabel.repaint();
    }

    public List<JButton> getRemoveButtonsList() {
        return removeButtonsList;
    }

    public void populateComboBox(List<CategoryGUIForm> categoryGUIFormsList) {
        for (CategoryGUIForm categoryForm : categoryGUIFormsList) {
            categoryComoboBoxModel.addElement(categoryForm.getCategoryName());
        }
    }

    public void clearProductsTable() {
        for (int i = (productTableModel.getRowCount() - 1); i >= 0; i--) {
            productTableModel.removeRow(i);
        }
    }

    public void populateProductsTable(List<ProductGUIForm> productGUIFormsList) {
        for (ProductGUIForm productForm : productGUIFormsList) {
            Object[] productFields = new Object[6];
            
            productFields[0] = productForm.getProductName();
            productFields[1] = productForm.getSellingPrice();
            productFields[2] = productForm.getProductItemsPerUnit();
            productFields[3] = productForm.getProductCondition();

            productTableModel.addRow(productFields);
        }
    }

    public DefaultComboBoxModel<String> getCategoryComboBoxItems() {
        return categoryComoboBoxModel;
    }

    public void setTableEnabled(boolean enabled) {
        productTable.setEnabled(enabled);
    }
    
    public void setCartButtonsEnabled(boolean enabled){
        confirmPurchaseButton.setEnabled(enabled);
        emptyCartButton.setEnabled(enabled);
    }

    public int getSelectedCategoryIndex() {
        //the element with index 0 is the "Select a category" element
        return (categoryComboBox.getSelectedIndex() - 1);
    }

    /**
     *
     * @return index of the selected table row
     */
    public int getSelectedTableRowIndex() {
        return productTable.getSelectedRow();
    }

    public void removeRowFromTable(int index) {
        productTableModel.removeRow(index);
    }
    
    public void setNextButtonEnabled(boolean enabled){
        nextButton.setEnabled(enabled);
    }
    
    public void setPreviousButtonEnabled(boolean enabled){
        previousButton.setEnabled(enabled);
    }
    
    public void setCreateProductButtonEnabled(boolean enabled){
        createProductButton.setEnabled(enabled);
    }

    public List<CategoryGUIForm> getCategoryGUIFormsList() {
        return categoryGUIFormsList;
    }

    public void setCategoryGUIFormsList(List<CategoryGUIForm> categoryGUIFormsList) {
        this.categoryGUIFormsList = categoryGUIFormsList;
    }

    public ProductGUIForm getProductGUIForm() {
        return productGUIForm;
    }

    public void setProductGUIForm(ProductGUIForm productGUIForm) {
        this.productGUIForm = productGUIForm;
    }

    public void setShoppingCartMap(Map<Integer, ProductGUIForm> shoppingCartMap) {
        this.shoppingCartMap = shoppingCartMap;
    }

    public void setCustomerGUIForm(CustomerGUIForm customerGUIForm) {
        this.customerGUIForm = customerGUIForm;
    }

    /*--------------Listeners-------------------*/
    public void setProductGUIListener(ProductGUIListener productGUIListener) {
        this.productGUIListener = productGUIListener;
    }

    public void setInvoiceGUIListener(InvoiceGUIListener invoiceGUIListener) {
        this.invoiceGUIListener = invoiceGUIListener;
    }
}
