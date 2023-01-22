package gui.guilistener;

import gui.guiform.SupplierGUIForm;

public interface SupplierGUIListener extends GUIListener {
    
    void supplierGUIFormObjectSet();

    void supplierFiltered(String filter);

    void supplierPanelReset();
    
    void supplierPanelEnabled(boolean visible);
    
    void guiTableRowSaved(SupplierGUIForm supplierGUIForm);
    
    boolean supplierProductExistenceChecked();
}
