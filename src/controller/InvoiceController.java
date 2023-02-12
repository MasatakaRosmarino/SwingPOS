package controller;

import gui.guiform.CustomerGUIForm;
import gui.guiform.InvoiceGUIForm;
import gui.guiform.ProductGUIForm;
import java.util.List;

import database.DataBaseManager;
import model.Invoice;
import model.PaymentMethod;
import model.Product;
import model.ProductCondition;
import utilities.Utilities;

public class InvoiceController extends BaseController {
    
    private Invoice invoice;
    private Product product;
    private DataBaseManager database;
    
    public InvoiceController(DataBaseManager database) {
    	this.database = database;
    }
    
    public void saveDatabaseTableRow(InvoiceGUIForm invoiceGUIForm, CustomerGUIForm customerGUIForm) {
    	int customerId = Integer.parseInt(customerGUIForm.getFormIdField());
        int appliedTax = Integer.parseInt(invoiceGUIForm.getAppliedTax());
        double totalAmount = Double.parseDouble(invoiceGUIForm.getTotalAmount());
        double discount = Double.parseDouble(invoiceGUIForm.getDiscount());

        double totalPayment = Double.parseDouble(invoiceGUIForm.getTotalPayment());
        PaymentMethod paymentMethod = PaymentMethod.valueOf(invoiceGUIForm.getPaymentMethod());
        String note = invoiceGUIForm.getNote();
        boolean voided = invoiceGUIForm.getVoided() == null ? false : true;
        String voidedReason = invoiceGUIForm.getVoidedReason();
        
        invoice = new Invoice(customerId, appliedTax, totalAmount, discount, totalPayment, paymentMethod, note, null, voided, voidedReason);
        
        database.saveInvoice(invoice);
    }
    
    public void updateDatabaseTableRow(ProductGUIForm productGUIForm) {
    	int id = Integer.parseInt(productGUIForm.getFormIdField());
    	String name = productGUIForm.getProductName();
        String description = productGUIForm.getProductDescription();
        double acquisitionPrice = Double.parseDouble(productGUIForm.getAcquisitionPrice());
        double sellingPrice = Double.parseDouble(productGUIForm.getSellingPrice());
        int itemsPerUnit = Integer.parseInt(productGUIForm.getProductItemsPerUnit());
        ProductCondition productCondition = ProductCondition.valueOf(Utilities.convertStringToEnum(productGUIForm.getProductCondition()));
        int categoryId = Integer.parseInt(productGUIForm.getProductCategoryId());
        int supplierId = Integer.parseInt(productGUIForm.getSupplierId());
        int invoiceId = Integer.parseInt(productGUIForm.getInvoiceId());
        
        product = new Product(id, name, description, acquisitionPrice, sellingPrice, itemsPerUnit, productCondition, null, categoryId, supplierId, invoiceId);
        
        database.updateProductTableRow(product);
    }

    @Override
    public int getHighestIdValue() {
        return database.getHighestInvoiceId();
    }
    
    public List<String> fetchInvoiceYears(){
        return database.fetchInvoiceYears();
    }

}
