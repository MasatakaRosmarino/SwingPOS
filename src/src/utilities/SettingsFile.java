package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SettingsFile implements Serializable {

    private String dbURL;
    private String dbPort;
    private String dbUsername;
    private String dbPassword;
    private String queryLimit;

    
    public SettingsFile(){
        this("No URL set", "3306", "No username set", "No password set", "20");
    }
    
    public SettingsFile(String dbURL, String dbPort, String dbUsername, String dbPassword, String queryLimit) {
        this.dbURL = dbURL;
        this.dbPort = dbPort;
        this.dbUsername = dbUsername;
        this.dbPassword = dbPassword;
        this.queryLimit = queryLimit;

    }
    
    public String getDbURL() {
        return dbURL;
    }

    public String getDbPort() {
        return dbPort;
    }

    public String getDbUsername() {
        return dbUsername;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public String getQueryLimit() {
        return queryLimit;
    }

    public void setDbURL(String dbURL) {
        this.dbURL = dbURL;
    }

    public void setDbPort(String dbPort) {
        this.dbPort = dbPort;
    }

    public void setDbUsername(String dbUsername) {
        this.dbUsername = dbUsername;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    public void setQueryLimit(String queryLimit) {
        this.queryLimit = queryLimit;
    }
}
