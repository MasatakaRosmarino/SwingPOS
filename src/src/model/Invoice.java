package model;

import java.time.LocalDateTime;

public class Invoice extends Model {
    private int customerId;
    private int appliedTax;
    private double totalAmount;
    private double discount;
    private double totalPayment;
    private PaymentMethod paymentMethod;
    private String note;
    private LocalDateTime invoicedOn;
    private boolean voided;
    private String voidedReason;

    public Invoice(int customerId, int appliedTax, double totalAmount, double discount, double totalPayment, PaymentMethod paymentMethod, String note, LocalDateTime invoicedOn, boolean voided, String voidedReason) {
        this.customerId = customerId;
        this.appliedTax = appliedTax;
        this.totalAmount = totalAmount;
        this.discount = discount;
        this.totalPayment = totalPayment;
        this.paymentMethod = paymentMethod;
        this.note = note;
        this.invoicedOn = invoicedOn;
        this.voided = voided;
        this.voidedReason = voidedReason;
    }
    
    public Invoice(int invoiceId, int customerId, int appliedTax, double totalAmount, double discount, double totalPayment, PaymentMethod paymentMethod, String note, LocalDateTime invoicedOn, boolean voided, String voidedReason) {
        this(customerId, appliedTax, totalAmount, discount, totalPayment, paymentMethod, note, invoicedOn, voided, voidedReason);
        this.modelId = invoiceId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getAppliedTax() {
        return appliedTax;
    }

    public void setAppliedTax(int appliedTax) {
        this.appliedTax = appliedTax;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(double totalPayment) {
        this.totalPayment = totalPayment;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
    public LocalDateTime getInvoicedOn() {
        return invoicedOn;
    }

    public boolean isVoided() {
        return voided;
    }

    public void setVoided(boolean voided) {
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
        return "Invoice{invoiceId=" + modelId + ", customerId=" + customerId + ", appliedTax=" + appliedTax + 
        		", totalAmount=" + totalAmount + ", discount=" + discount + ", totalPayment=" + totalPayment + 
        		", paymentMethod=" + paymentMethod + ", text=" + note + ", invoicedOn=" + invoicedOn + ", voided=" + voided + ", voidedReason=" + voidedReason + '}';
    }
}
