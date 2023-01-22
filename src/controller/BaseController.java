package controller;

import database.DataBaseManager;
import gui.guiform.GUIForm;
import java.sql.Connection;
import java.util.List;

public abstract class BaseController implements QueryListener {

    public static Connection connection;

    public BaseController() {
        connection = DataBaseManager.connection;
    }

    @Override
    public int getHighestIdValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<? extends GUIForm> fetchObjectsListFromDatabase() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<? extends GUIForm> fetchObjectsListFromDatabase(int foreignKey) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<? extends GUIForm> fetchObjectsListFromDatabase(int limit, int offset) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<? extends GUIForm> fetchFilteredObjectsListFromDatabase(String filter) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getDatabaseTableRowsCount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GUIForm fetchObjectFromTableRow(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
