package model;

import java.time.LocalDateTime;

public class Product extends Model {

    private String productName;
    private String productDescription;
    private double acquisitionPrice;
    private double sellingPrice;
    private int productItemsPerUnit;
    private ProductCondition productCondition;
    private String addedOn;
    private int productCategoryId;
    private int supplierId;
    private Integer invoiceId;

    public Product(String productName, String productDescription, double acquisitionPrice, double sellingPrice,
            int productItemsPerUnit, ProductCondition productCondition, String addedOn, int productCategoryId, int supplierId, Integer invoiceId) {
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
    
    public Product(int productId, String productName, String productDescription, double acquisitionPrice, double sellingPrice,
            int productItemsPerUnit, ProductCondition productCondition, String addedOn, int productCategoryId, int supplierId, Integer invoiceId) {
        this(productName, productDescription, acquisitionPrice, sellingPrice, productItemsPerUnit, productCondition, addedOn, productCategoryId, supplierId, invoiceId);
        this.modelId = productId;
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
    
    public double getAcquisitionPrice() {
        return acquisitionPrice;
    }

    public void setAcquisitionPrice(double acquisitionPrice) {
        this.acquisitionPrice = acquisitionPrice;
    }
    
    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }
    
    public int getProductItemsPerUnit() {
        return productItemsPerUnit;
    }

    public void setProductItemsPerUnit(int productItemsPerUnit) {
        this.productItemsPerUnit = productItemsPerUnit;
    }
    
    public ProductCondition getProductCondition() {
        return productCondition;
    }

    public void setProductCondition(ProductCondition productCondition) {
        this.productCondition = productCondition;
    }
    
    public String getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(String addedOn) {
        this.addedOn = addedOn;
    }
    
    public int getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(int productCategoryId) {
        this.productCategoryId = productCategoryId;
    }
    
    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }
    
    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }
    
    @Override
    public String toString() {
        return "Product [productId=" + modelId + ", productName=" + productName + ", productDescription="
                + productDescription + ", acquisitionPrice=" + acquisitionPrice + ", sellingPrice=" + sellingPrice + ", productItemsPerUnit=" + productItemsPerUnit
                + ", productCondition=" + productCondition + ", addedOn=" + addedOn + ", productCategoryId="
                + productCategoryId + " supplierId=" + supplierId + "]";
    }

}
