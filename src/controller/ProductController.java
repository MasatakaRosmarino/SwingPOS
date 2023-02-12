package controller;

import database.DataBaseManager;
import gui.ProductPanel;
import gui.guiform.ProductGUIForm;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import model.Product;
import model.ProductCondition;
import utilities.Utilities;

public class ProductController extends BaseController {
    
    private DataBaseManager database;

    public ProductController(DataBaseManager database) {
        this.database = database;
    }

    public int getDatabaseTableRowsCount(int categoryId) {
        return database.getProductCount(categoryId);
    }
    
    public void deleteDatabaseTableRow(ProductGUIForm productGUIForm) {
        database.deleteProductTableRow(Integer.parseInt(productGUIForm.getFormIdField()));
    }

    public boolean databaseTableRowExists(ProductGUIForm productGUIForm) {
        return database.productRowExists(Integer.parseInt(productGUIForm.getFormIdField()));
    }
    
    public List<ProductGUIForm> fetchObjectsListFromDatabase(int foreignKey) {
    	List<Product> productsList = database.fetchProductsListFromDatabase(foreignKey, DataBaseManager.limit, DataBaseManager.productOffset);
    	List<ProductGUIForm> productGUIFormsList = new ArrayList<>();
    	
    	for(Product product : productsList) {
    		ProductGUIForm productGUIForm = new ProductGUIForm(Integer.toString(product.getModelId()), product.getProductName(), product.getProductDescription(), Double.toString(product.getAcquisitionPrice()), Double.toString(product.getSellingPrice()), Integer.toString(product.getProductItemsPerUnit()), Utilities.formatProductCondition(product.getProductCondition()), product.getAddedOn(), Integer.toString(product.getProductCategoryId()), Integer.toString(product.getSupplierId()), null);

    		productGUIFormsList.add(productGUIForm);
    	}
    	
        return productGUIFormsList;
    }
    
    public List<ProductGUIForm> fetchFilteredDatabaseTableRows(String filter) {
    	List<Product> productsList = database.fetchFilteredProductsListFromDatabase(filter, ProductPanel.productCategoryId);
        List<ProductGUIForm> productGUIFormsList = new ArrayList<>();
        
    	for(Product product : productsList) {
    		ProductGUIForm productGUIForm = new ProductGUIForm(Integer.toString(product.getModelId()), product.getProductName(), product.getProductDescription(), Double.toString(product.getAcquisitionPrice()), Double.toString(product.getSellingPrice()), Integer.toString(product.getProductItemsPerUnit()), Utilities.formatProductCondition(product.getProductCondition()), product.getAddedOn(), Integer.toString(product.getProductCategoryId()), Integer.toString(product.getSupplierId()), null);

    		productGUIFormsList.add(productGUIForm);
    	}

        return productGUIFormsList;
    }
    
    public void addProductToCart(ProductGUIForm productGUIForm) {
        database.addProductToCart(Integer.parseInt(productGUIForm.getFormIdField()));
    }
    
    public void removeProductFromCart(ProductGUIForm productGUIForm) {
    	int productId = Integer.parseInt(productGUIForm.getFormIdField());
        database.removeProductFromCart(productId);
    }

    public void clearShoppingCart() {
        database.clearShoppingCart();
    }
    
    public List<String> fetchSalesObjectsListFromDatabase(String date) {
        return database.fetchSalesListFromDatabase(date);
    }
    
    public void saveDatabaseTableRow(ProductGUIForm productGUIForm) {
    	Product product;
        try {
            if (productGUIForm.getFormIdField() == null || productGUIForm.getFormIdField().isEmpty()) {
                product = new Product(productGUIForm.getProductName(), productGUIForm.getProductDescription(), Double.parseDouble(productGUIForm.getAcquisitionPrice()), Double.parseDouble(productGUIForm.getSellingPrice()), Integer.valueOf(productGUIForm.getProductItemsPerUnit()), ProductCondition.valueOf(Utilities.convertStringToEnum(productGUIForm.getProductCondition())), null, Integer.valueOf(productGUIForm.getProductCategoryId()), Integer.valueOf(productGUIForm.getSupplierId()), 0);
            } else {
                product = new Product(Integer.parseInt(productGUIForm.getFormIdField()), productGUIForm.getProductName(), productGUIForm.getProductDescription(), Double.parseDouble(productGUIForm.getAcquisitionPrice()), Double.parseDouble(productGUIForm.getSellingPrice()), Integer.valueOf(productGUIForm.getProductItemsPerUnit()), ProductCondition.valueOf(productGUIForm.getProductCondition()), null, Integer.valueOf(productGUIForm.getProductCategoryId()), Integer.valueOf(productGUIForm.getSupplierId()), 0);
            }
            
            database.saveProduct(product);
        } catch (NumberFormatException ex) {
        	JOptionPane.showMessageDialog(null, "NumberFormatException occurred when saving value: " + Double.parseDouble(productGUIForm.getSellingPrice()));
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
