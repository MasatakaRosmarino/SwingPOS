package gui.guilistener;

import gui.guiform.CustomerGUIForm;

public interface CustomerGUIListener extends GUIListener {
    
    void customerGUIFormObjectSet();

    void customerFiltered(String filter);

    void customerPanelReset();
    
    void customerPanelEnabled(boolean visible);
    
    void guiTableRowSaved(CustomerGUIForm customerForm);
    
    boolean invoicedCustomerExistenceChecked();
        
}
