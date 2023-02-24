package gui.guilistener;

import gui.guiform.ContactGUIForm;
import gui.guiform.CustomerGUIForm;

public interface CustomerContactGUIListener  extends ContactGUIListener{
    
    void customerContactInfoDisplayed();
    
    void customerContactRelationshipDeleted();
    
    void customerContactDeleted();
        
}
