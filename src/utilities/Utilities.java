package utilities;

import javax.swing.JOptionPane;

public class Utilities {
        
    public static int getIntegerValueFromDecimalNumber(String amount){
        return (int) (Double.valueOf(amount) * 100);
    }
    
    public static String roundTo2ndDecimal(String amount){
        double decimalAmount = Double.parseDouble(amount);
        
        double roundedAmount = Math.round(decimalAmount * 100.0) / 100.0;
        
        return Double.toString(roundedAmount);
    }
    
    public static String getShopName(){
        return "Thif-T-rill\nThrill Avenue 23, 09853\nNew York City, USA";
    }
            
    public static void notifyOfRowInsertion(String entityName){
        JOptionPane.showMessageDialog(null, "New " + entityName + " added successfully!");
    }
    
    public static void notifyOfRowUpdate(String entityName){
        JOptionPane.showMessageDialog(null, entityName + " info updated successfully!");
    }
    
    public static void notifyOfRowDeletion(String entityName){
        JOptionPane.showMessageDialog(null, entityName + " deleted successfully!");
    }
    
    public static void notifyOfSettingsChange(){
        JOptionPane.showMessageDialog(null, "Configuration set, please restart the application.");
    }
    
    public static void notifyOfInvoiceCreation(){
        JOptionPane.showMessageDialog(null, "Products sold to customer.");
    }
    
    public static void notifyOfError(String error) {
    	JOptionPane.showMessageDialog(null, error);
    }
}
