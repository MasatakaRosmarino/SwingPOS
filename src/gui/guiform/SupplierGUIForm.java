package gui.guiform;

public class SupplierGUIForm extends GUIForm {
    private String name;
    private String vatNumber;
    private String isBusiness;

    public SupplierGUIForm(String name, String vatNumber, String isBusiness) {
        this.name = name;
        this.vatNumber = vatNumber;
        this.isBusiness = isBusiness;
    }
    
    public SupplierGUIForm(String supplierId, String name, String vatNumber, String isBusiness) {
        this(name, vatNumber, isBusiness);
        this.formIdField = supplierId;
    }
    
    public String getName() {
        return name;
    }

    public String getVatNumber() {
        return vatNumber;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }
    
    public String isIsBusiness() {
        return isBusiness;
    }
    
    public void setIsBusiness(String isBusiness) {
        this.isBusiness = isBusiness;
    }
    
    @Override
    public String toString() {
        return "Supplier{supplierId: " + formIdField + ", name=" + name + ", vatNumber=" + vatNumber + ", isBusiness=" + isBusiness + '}';
    }
}
