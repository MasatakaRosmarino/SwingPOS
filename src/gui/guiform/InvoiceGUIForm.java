package gui.guiform;

import java.time.LocalDateTime;

public class InvoiceGUIForm extends GUIForm {
    
    private String customerId;
    private String appliedTax;
    private String totalAmount;
    private String discount;
    private String totalPayment;
    private String paymentMethod;
    private String note;
    private String invoicedOn;
    private String voided;
    private String voidedReason;

    public InvoiceGUIForm(String customerId, String appliedTax, String totalAmount, String discount, String totalPayment, String paymentMethod, String text, String invoicedOn, String voided, String voidedReason) {
        this.customerId = customerId;
        this.appliedTax = appliedTax;
        this.totalAmount = totalAmount;
        this.discount = discount;
        this.totalPayment = totalPayment;
        this.paymentMethod = paymentMethod;
        this.note = text;
        this.invoicedOn = invoicedOn;
        this.voided = voided;
        this.voidedReason = voidedReason;
    }
    
    public InvoiceGUIForm(String invoiceId, String customerId, String appliedTax, String totalAmount, String discount, String totalPayment, String paymentMethod, String text, String invoicedOn, String voided, String voidedReason) {
        this(customerId, appliedTax, totalAmount, discount, totalPayment, paymentMethod, text, invoicedOn, voided, voidedReason);
        this.formIdField = invoiceId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getAppliedTax() {
        return appliedTax;
    }

    public void setAppliedTax(String appliedTax) {
        this.appliedTax = appliedTax;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(String totalPayment) {
        this.totalPayment = totalPayment;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getInvoicedOn() {
        return invoicedOn;
    }

    public void setInvoicedOn(String invoicedOn) {
        this.invoicedOn = invoicedOn;
    }

    public String getVoided() {
        return voided;
    }

    public void setVoided(String voided) {
        this.voided = voided;
    }

    public String getVoidedReason() {
        return voidedReason;
    }

    public void setVoidedReason(String voidedReason) {
        this.voidedReason = voidedReason;
    }

    @Override
    public String toString() {
        return "InvoiceGUIForm{invoiceId=" + formIdField + ", customerId=" + customerId + ", appliedTax=" + appliedTax + ", totalAmount=" + totalAmount + ", discount=" + discount + ", totalPayment=" + totalPayment + ", paymentMethod=" + paymentMethod + ", text=" + note + ", invoicedOn=" + invoicedOn + ", voided=" + voided + ", voidedReason=" + voidedReason + '}';
    }
    
    
}
