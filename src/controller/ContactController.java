package controller;

import database.DataBaseManager;
import gui.guiform.ContactGUIForm;
import gui.guiform.CustomerGUIForm;
import gui.guiform.SupplierGUIForm;
import model.Contact;

public class ContactController extends BaseController  {
    
    private DataBaseManager database;

    public ContactController(DataBaseManager database) {
        this.database = database;
    }
    
    public ContactGUIForm fetchCustomerContactFromDatabase(CustomerGUIForm customerGUIForm) {
        Contact contact = database.fetchCustomerContactRow(Integer.parseInt(customerGUIForm.getFormIdField()));
        
        return new ContactGUIForm(Integer.toString(contact.getContactId()), contact.getPhone(), contact.getEmail(), contact.getAddress(), contact.getZipCode(), contact.getCity(), contact.getProvince(), contact.getCountry());
    }
    
    public ContactGUIForm fetchSupplierContactFromDatabase(SupplierGUIForm supplierGUIForm) {
        Contact contact = database.fetchSupplierContactRow(Integer.parseInt(supplierGUIForm.getFormIdField()));
        
        return new ContactGUIForm(Integer.toString(contact.getContactId()), contact.getPhone(), contact.getEmail(), contact.getAddress(), contact.getZipCode(), contact.getCity(), contact.getProvince(), contact.getCountry());        
    }

    @Override
    public int getHighestIdValue() {
        return database.getHighestContactId();
    }

    /**
     * Insert customer_id and contact_id into shop_user_contact table
     */
    public void createCustomerContactRelation(int customerId, int contactId) {
        database.insertCustomerIdIntoRelationalTable(customerId, contactId);
    }
    
    /**
     * Insert supplier_id and contact_id into shop_user_contact table
     */
    public void createSupplierContactRelation(int supplierId, int contactId) {
        database.insertSupplierIdIntoRelationalTable(supplierId, contactId);
    }

    public boolean customerContactTableRowExists(CustomerGUIForm customerGUIForm) {
        return database.customerContactRowExists(Integer.parseInt(customerGUIForm.getFormIdField()));
    }
    
    public boolean supplierContactTableRowExists(SupplierGUIForm supplierGUIForm) {
        return database.supplierContactRowExists(Integer.parseInt(supplierGUIForm.getFormIdField()));
    }
    
    public void saveDatabaseTableRow(ContactGUIForm contactGUIForm) {
    	Contact contact;
        if (contactGUIForm.getFormIdField() == null || contactGUIForm.getFormIdField().isEmpty()) {
                contact = new Contact(contactGUIForm.getPhone(), contactGUIForm.getEmail(), contactGUIForm.getAddress(), contactGUIForm.getZipCode(), contactGUIForm.getCity(), contactGUIForm.getProvince(), contactGUIForm.getCountry());
            } else {
                contact = new Contact(Integer.parseInt(contactGUIForm.getFormIdField()), contactGUIForm.getPhone(), contactGUIForm.getEmail(), contactGUIForm.getAddress(), contactGUIForm.getZipCode(), contactGUIForm.getCity(), contactGUIForm.getProvince(), contactGUIForm.getCountry());
            }
            
            database.saveContact(contact);
    }

    public void deleteDatabaseTableRow(ContactGUIForm contactGUIForm){
        database.deleteContact(Integer.parseInt(contactGUIForm.getFormIdField()));
    }
}