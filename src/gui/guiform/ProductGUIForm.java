package gui.guiform;

public class ProductGUIForm extends GUIForm implements Cloneable {

    private String productName;
    private String productDescription;
    private String acquisitionPrice;
    private String sellingPrice;
    private String productItemsPerUnit;
    private String productCondition;
    private String addedOn;
    private String productCategoryId;
    private String supplierId;
    private String invoiceId;

    public ProductGUIForm(String productName, String productDescription, String acquisitionPrice, String sellingPrice, String productItemsPerUnit, String productCondition, String addedOn, String productCategoryId, String supplierId, String invoiceId) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.acquisitionPrice = acquisitionPrice;
        this.sellingPrice = sellingPrice;
        this.productItemsPerUnit = productItemsPerUnit;
        this.productCondition = productCondition;
        this.addedOn = addedOn;
        this.productCategoryId = productCategoryId;
        this.supplierId = supplierId;
        this.invoiceId = invoiceId;
    }

    public ProductGUIForm(String productId, String productName, String productDescription, String acquisitionPrice, String sellingPrice, String productItemsPerUnit, String productCondition, String addedOn, String productCategoryId, String supplierId, String invoiceId) {
        this(productName, productDescription, acquisitionPrice, sellingPrice, productItemsPerUnit, productCondition, addedOn, productCategoryId, supplierId, invoiceId);
        this.formIdField = productId;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getAcquisitionPrice() {
        return acquisitionPrice;
    }

    public void setAcquisitionPrice(String acquisitionPrice) {
        this.acquisitionPrice = acquisitionPrice;
    }
    
    public String getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(String sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getProductItemsPerUnit() {
        return productItemsPerUnit;
    }

    public void setProductItemsPerUnit(String productItemsPerUnit) {
        this.productItemsPerUnit = productItemsPerUnit;
    }

    public String getProductCondition() {
        return productCondition;
    }

    public void setProductCondition(String productCondition) {
        this.productCondition = productCondition;
    }

    public String getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(String addedOn) {
        this.addedOn = addedOn;
    }

    public String getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(String productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }
    
    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    @Override
    public String toString() {
        return "ProductGUIForm [productId=" + formIdField + ", productName=" + productName + ", productDescription="
                + productDescription + ", acquisitionPrice=" + acquisitionPrice + ", sellingPrice=" + sellingPrice + ", productItemsPerUnit=" + productItemsPerUnit
                + ", productCondition=" + productCondition + ", addedOn=" + addedOn + ", productCategoryId="
                + productCategoryId + ", supplierId=" + supplierId + ", invoiceId=" + invoiceId + "]";
    }
}
