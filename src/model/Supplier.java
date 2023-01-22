package model;

public class Supplier extends Model {
    private String name;
    private String vatNumber;
    private boolean isBusiness;

    public void setIsBusiness(boolean isBusiness) {
        this.isBusiness = isBusiness;
    }

    public Supplier(String name, String vatNumber, boolean isBusiness) {
        this.name = name;
        this.vatNumber = vatNumber;
        this.isBusiness = isBusiness;
    }
    
    public Supplier(int supplierId, String name, String vatNumber, boolean isBusiness) {
        this(name, vatNumber, isBusiness);
        this.modelId = supplierId;
    }
    
    public String getName() {
        return name;
    }

    public String getVatNumber() {
        return vatNumber;
    }

    public boolean isIsBusiness() {
        return isBusiness;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }
    
    @Override
    public String toString() {
        return "Supplier{supplierId: " + modelId + ", name=" + name + ", vatNumber=" + vatNumber + ", isBusiness=" + isBusiness + '}';
    }
}
