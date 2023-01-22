package controller;

import database.DataBaseManager;
import gui.guiform.ContactGUIForm;
import gui.guiform.CustomerGUIForm;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Customer;
import model.Gender;

public class CustomerController extends BaseController {

    private final DataBaseManager database;

    public CustomerController(DataBaseManager database) {
        this.database = database;
    }

    @Override
    public List<CustomerGUIForm> fetchObjectsListFromDatabase() {
        List<Customer> customersList = database.fetchCustomersListFromDatabase(DataBaseManager.limit, DataBaseManager.customerOffset);
        List<CustomerGUIForm> customerGUIFormsList = new ArrayList<>();

        for (Customer customer : customersList) {
            CustomerGUIForm customerGUIForm = new CustomerGUIForm(Integer.toString(customer.getModelId()), customer.getName(), customer.getLastname(), customer.getGender().name(), customer.getIdNumber(), customer.getBirthDate().toString());
            customerGUIFormsList.add(customerGUIForm);
        }

        return customerGUIFormsList;
    }

    public boolean invoicedCustomerExists(CustomerGUIForm customerGUIForm) {
        return database.invoicedCustomerExists(Integer.parseInt(customerGUIForm.getFormIdField()));
    }
    
    public boolean databaseTableRowExists(CustomerGUIForm customerGUIForm) {
        return database.customerRowExists(Integer.parseInt(customerGUIForm.getFormIdField()));
    }

    public void deleteDatabaseTableRow(CustomerGUIForm customerGUIForm) {
        database.deleteCustomerTableRow(Integer.parseInt(customerGUIForm.getFormIdField()));
    }
    
    public void deleteCustomerContactRelationship(CustomerGUIForm customerGUIForm, ContactGUIForm contactGUIForm){
        database.deleteCustomerContactRelationship(Integer.parseInt(customerGUIForm.getFormIdField()), Integer.parseInt(contactGUIForm.getFormIdField()));
    }

    @Override
    public int getHighestIdValue() {
        return database.getHighestCustomerId();
    }
    
    @Override
    public int getDatabaseTableRowsCount() {
        return database.getCustomerCount();
    }
    
    @Override
    public List<CustomerGUIForm> fetchFilteredObjectsListFromDatabase(String filter) {
        List<CustomerGUIForm> customerGUIFormsList = new ArrayList<>();

        List<Customer> customersList = database.fetchFilteredCustomersListFromDatabase(filter);
        
        for (Customer customer : customersList) {
            CustomerGUIForm customerGUIForm = new CustomerGUIForm(Integer.toString(customer.getModelId()), customer.getName(), customer.getLastname(), customer.getGender().name(), customer.getIdNumber(), customer.getBirthDate().toString());
            customerGUIFormsList.add(customerGUIForm);
        }
        
        return customerGUIFormsList;
    }

    public void saveDatabaseTableRow(CustomerGUIForm customerGUIForm) {
        Customer customer;
        if (customerGUIForm.getFormIdField() == null || customerGUIForm.getFormIdField().isEmpty()) {
                customer = new Customer(customerGUIForm.getName(), customerGUIForm.getLastname(), Gender.valueOf(customerGUIForm.getGender()), customerGUIForm.getIdNumber(), LocalDate.parse(customerGUIForm.getBirthDate()));
            } else {
                customer = new Customer(Integer.parseInt(customerGUIForm.getFormIdField()), customerGUIForm.getName(), customerGUIForm.getLastname(), Gender.valueOf(customerGUIForm.getGender()), customerGUIForm.getIdNumber(), LocalDate.parse(customerGUIForm.getBirthDate()));
            }

            database.saveCustomer(customer);
    }

}
