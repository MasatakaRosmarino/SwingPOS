package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SettingsManager {
    
    private FileOutputStream fos;
    private ObjectOutputStream oos;
    private FileInputStream fis;
    private ObjectInputStream ois;
    private String fullFilePath;
    private File file;
    
    public SettingsManager(String filePath){
    	filePath = Utilities.getMainFolderPath();
    	
    	fullFilePath = filePath;
    	file = new File(fullFilePath);
        
        if(!file.exists()){
            file.mkdir();
            file = new File(fullFilePath);
            file.mkdir();
        }
        
        file = new File(fullFilePath + "\\settings.sav");
        
        File file = new File(Utilities.getCustomerInvoicePath());
        if(!file.exists()){
            file.mkdir();
            file = new File(Utilities.getCustomerInvoicePath());
            file.mkdir();
        }        
    }
    
    public boolean fileExists(){
        return file.exists();
    }
    
    public void createSettingsFile(SettingsFile settingsFile){
        try {
            file.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(SettingsFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void writeSettingsToFile(SettingsFile settingsFile) {
        try {
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(settingsFile);
            oos.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SettingsFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SettingsFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public SettingsFile getSettingsFromFile() {
        try {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            SettingsFile settingsFile = (SettingsFile) ois.readObject();
            ois.close();
            
            return settingsFile;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SettingsFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(SettingsFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public String getInvoiceFilePath(){
        return fullFilePath + "\\Customers_Invoices";
    }
}
