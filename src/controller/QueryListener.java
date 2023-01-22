package controller;

import gui.guiform.GUIForm;
import java.util.List;

public interface QueryListener {
    
    /**
     * Fetches the last inserted row by searching by id. 
     * This method makes sure the record has been inserted
     * @return id's int value
     */
    int getHighestIdValue();
        
    List<? extends GUIForm> fetchObjectsListFromDatabase();
    
    List<? extends GUIForm> fetchObjectsListFromDatabase(int limit, int offset);
    
    List<? extends GUIForm> fetchObjectsListFromDatabase(int id);
    
    GUIForm fetchObjectFromTableRow(int id);    
    
    List<? extends GUIForm> fetchFilteredObjectsListFromDatabase(String filter);

    int getDatabaseTableRowsCount();
}
