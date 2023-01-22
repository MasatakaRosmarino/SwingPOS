package gui.guilistener;

import gui.guiform.ContactGUIForm;
import gui.guiform.CustomerGUIForm;

public interface ContactGUIListener extends GUIListener {

    void customerRelationalIdsInserted(int customerId, int contactId);
    
    void supplierRelationalIdsInserted(int supplierId, int contactId);

    int[] newRowsIdsRetrieved();
    
    void guiTableRowSaved(ContactGUIForm contactForm);
}
