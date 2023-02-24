package controller;

import database.DataBaseManager;
import gui.guiform.ContactGUIForm;
import gui.guiform.SupplierGUIForm;
import java.util.ArrayList;
import java.util.List;
import model.Supplier;

public class SupplierController extends BaseController {

    private DataBaseManager database;

    public SupplierController(DataBaseManager database) {
        this.database = database;
    }

    @Override
    public List<SupplierGUIForm> fetchObjectsListFromDatabase() {
        List<Supplier> suppliersList = database.fetchSuppliersListFromDatabase(DataBaseManager.limit, DataBaseManager.supplierOffset);
        List<SupplierGUIForm> supplierGUIFormsList = new ArrayList<>();

        for (Supplier supplier : suppliersList) {
            SupplierGUIForm supplierGUIForm = new SupplierGUIForm(Integer.toString(supplier.getModelId()), supplier.getName(), supplier.getVatNumber(), Boolean.toString(supplier.isIsBusiness()));

            supplierGUIFormsList.add(supplierGUIForm);
        }

        return supplierGUIFormsList;
    }

    @Override
    public List<SupplierGUIForm> fetchFilteredObjectsListFromDatabase(String filter) {
        List<Supplier> suppliersList = database.fetchFilteredSuppliersListFromDatabase(filter);
        List<SupplierGUIForm> supplierGUIFormsList = new ArrayList<>();

        for (Supplier supplier : suppliersList) {
            SupplierGUIForm supplierGUIForm = new SupplierGUIForm(Integer.toString(supplier.getModelId()), supplier.getName(), supplier.getVatNumber(), Boolean.toString(supplier.isIsBusiness()));

            supplierGUIFormsList.add(supplierGUIForm);
        }

        return supplierGUIFormsList;

    }

    public void saveDatabaseTableRow(SupplierGUIForm supplierGUIForm) {
        Supplier supplier;
        if (supplierGUIForm.getFormIdField() == null || supplierGUIForm.getFormIdField().isEmpty()) {
            supplier = new Supplier(supplierGUIForm.getName(), supplierGUIForm.getVatNumber(), Boolean.valueOf(supplierGUIForm.isIsBusiness()));
        } else {
            supplier = new Supplier(Integer.parseInt(supplierGUIForm.getFormIdField()), supplierGUIForm.getName(), supplierGUIForm.getVatNumber(), Boolean.valueOf(supplierGUIForm.isIsBusiness()));
        }

        database.saveSupplier(supplier);
    }

    public boolean databaseTableRowExists(SupplierGUIForm supplierGUIForm) {
        return database.supplierRowExists(Integer.parseInt(supplierGUIForm.getFormIdField()));
    }

    @Override
    public int getHighestIdValue() {
        return database.getHighestSupplierId();
    }

    @Override
    public int getDatabaseTableRowsCount() {
        return database.getSupplierCount();
    }

    public void addSupplierGUIFormToList(List<SupplierGUIForm> supplierGUIFormsList, String[] fields) {
        SupplierGUIForm supplierGUIForm = new SupplierGUIForm(fields[0], fields[1], fields[2], fields[3]);
        supplierGUIFormsList.add(supplierGUIForm);
    }

    public void deleteSupplierContactRelationship(SupplierGUIForm supplierGUIForm, ContactGUIForm contactGUIForm){
        database.deleteSupplierContactRelationship(Integer.parseInt(supplierGUIForm.getFormIdField()), Integer.parseInt(contactGUIForm.getFormIdField()));
    }
    
    public boolean suppliedProductExists(SupplierGUIForm supplierGUIForm){
        return database.suppliedProductExists(Integer.parseInt(supplierGUIForm.getFormIdField()));
    }
    
    public void deleteDatabaseTableRow(SupplierGUIForm supplierGUIForm){
        database.deleteSupplierTableRow(Integer.parseInt(supplierGUIForm.getFormIdField()));
    }
}
