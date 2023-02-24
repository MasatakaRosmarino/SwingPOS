package utilities;


import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

import model.ProductCondition;

public final class Utilities {
	
	public static final int MAIN_FOLDER_PATH = 0;
	public static final int CUSTOMER_INVOICE_FOLDER_PATH = 1;
	
	private Utilities() {
		/*Do not instantiate this class*/
	}
        
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
    
    //Enum functions
    public static final String convertStringToEnum(String string) {
		string = string.toLowerCase(); 	
    	String[] strArr = string.split(" ");   	
    	String finalString = String.join("", strArr);
    	
    	return finalString;
    }
    
    public static final String formatProductCondition(ProductCondition productCondition) {
    	switch(productCondition) {
	    	case brandnew:
	    		return "Brand new";
			case likenew:
	    		return "Like new";
			case good:
				return "Good";
			case acceptable:
				return "Acceptable";
			case worn:
				return "Worn";
			default:
	    		return "Not set";
    	}
    }
    
    public static final String formatPaymentMethod(String paymentMethod) {
    	switch(paymentMethod) {
	    	case "cash":
	    		return "Cash";
	    	case "banktransfer":
	    		return "Bank transfer";
	    	case "creditcard":
	    		return "Credit card";
	    	case "debitcard":
	    		return "Debit card";
	    	case "cheque":
	    		return "Cheque";
	    	case "paypal":
	    		return "PayPal";
    		default:
    			return "Not specified";
    	}
    }
    
    public static final String getFolderPath(int pathType) {
    	String basePath = FileSystemView.getFileSystemView().getDefaultDirectory().getPath();
    	switch (pathType) {
		case MAIN_FOLDER_PATH:
			return basePath + "\\Point_Of_Sale";	
		case CUSTOMER_INVOICE_FOLDER_PATH:
			return basePath + "\\Point_Of_Sale\\Customers_Invoices";
		}
    	return null;
    }
    
    //Get path of main folder
//    public static final String getMainFolderPath() {
//    	return FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + "\\Point_Of_Sale";
//    }
    
    //Get path of customer invoices
//    public static final String getCustomerInvoicePath() {
//    	return FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + "\\Point_Of_Sale\\Customers_Invoices";
//    }
      
    //Notifications
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
        JOptionPane.showMessageDialog(null, "Products sold to customer.\nOpen the invoice file from the Sales panel.");
    }
    
    public static void notifyOfError(String error) {
    	JOptionPane.showMessageDialog(null, error);
    }
}
