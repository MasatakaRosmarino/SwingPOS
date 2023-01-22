package gui.dialog;

import gui.ProductPanel;
import gui.guiform.CustomerGUIForm;
import gui.guiform.InvoiceGUIForm;
import gui.guiform.ProductGUIForm;
import gui.guilistener.InvoiceGUIListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import model.PaymentMethod;
import utilities.Utilities;

public class CreateInvoiceDialog extends JDialog {

    private final JPanel checkoutPanel;
    private final JScrollPane checkoutScroll;
    private final JPanel buttonsPanel;
    private final JSpinner discountSpinner;
    private final SpinnerNumberModel discountModel;
    private final JButton discountButton;
    private final JComboBox<PaymentMethod> paymentMethodComboBox;
    private final JLabel totalPayment;
    private final DefaultComboBoxModel<PaymentMethod> paymentMethodModel;
    private final JTextArea noteArea;
    private final JButton confirmButton;

    private final GridBagConstraints constraint;
    
    private String taxAmount = "20";
    
    private InvoiceGUIForm invoiceGUIForm;

    public CreateInvoiceDialog(CustomerGUIForm customerGUIForm, Map<Integer, ProductGUIForm> shoppingCartItems, InvoiceGUIListener invoiceGUIListener) {
        setTitle("Checkout");

        setLayout(new BorderLayout());
        
        checkoutPanel = new JPanel();
        checkoutScroll = new JScrollPane(checkoutPanel);
        
        checkoutPanel.setLayout(new GridBagLayout());
        
        constraint = new GridBagConstraints();

        buttonsPanel = new JPanel();
        
        discountButton = new JButton("Set");
        
        paymentMethodModel = new DefaultComboBoxModel<>();
        paymentMethodComboBox = new JComboBox<>();
        
        paymentMethodModel.addElement(PaymentMethod.cash);
        paymentMethodModel.addElement(PaymentMethod.banktransfer);
        paymentMethodModel.addElement(PaymentMethod.creditcard);
        paymentMethodModel.addElement(PaymentMethod.debitcard);
        paymentMethodModel.addElement(PaymentMethod.cheque);
        paymentMethodModel.addElement(PaymentMethod.paypal);
        
        paymentMethodComboBox.setModel(paymentMethodModel);
        
        totalPayment = new JLabel();
        
        noteArea = new JTextArea(5, 15);
        
        noteArea.setWrapStyleWord(true);
        noteArea.setLineWrap(true);
        noteArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        
        confirmButton = new JButton("Confirm Order");

        //setting up constraints parameters
        constraint.weightx = 1;
        constraint.weighty = 0.1;

        //start row
        constraint.gridy = 0;
        
        //header
        constraint.gridx = 0;
        constraint.anchor = GridBagConstraints.LINE_START;
        constraint.insets = new Insets(0, 15, 0, 0);
        checkoutPanel.add(new JLabel("ITEM"), constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_END;
        constraint.insets = new Insets(0, 0, 0, 15);
        checkoutPanel.add(new JLabel("PRICE(€)"), constraint);
        
        //header
        constraint.gridx = 0;
        constraint.anchor = GridBagConstraints.LINE_START;
        constraint.insets = new Insets(0, 15, 0, 0);
        checkoutPanel.add(new JLabel("ITEM"), constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_END;
        constraint.insets = new Insets(0, 0, 0, 15);
        checkoutPanel.add(new JLabel("PRICE(€)"), constraint);

        constraint.gridy++;
        
        for (Map.Entry<Integer, ProductGUIForm> entry : shoppingCartItems.entrySet()) {
            constraint.gridx = 0;
            constraint.anchor = GridBagConstraints.LINE_START;
            constraint.insets = new Insets(0, 15, 0, 0);
            checkoutPanel.add(new JLabel(entry.getValue().getProductName()), constraint);

            constraint.gridx = 1;
            constraint.anchor = GridBagConstraints.LINE_END;
            constraint.insets = new Insets(0, 0, 0, 15);
            checkoutPanel.add(new JLabel(entry.getValue().getSellingPrice()), constraint);

            constraint.gridy++;
        }
        
        //subtotal
        constraint.gridx = 0;
        constraint.anchor = GridBagConstraints.LINE_END;
        checkoutPanel.add(new JLabel("Subtotal:"), constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_END;
        constraint.insets = new Insets(0, 0, 0, 15);
        checkoutPanel.add(new JLabel(ProductPanel.purchaseTotalAmount), constraint);
        
        //tax
        constraint.gridx = 0;
        constraint.gridy++;
        constraint.anchor = GridBagConstraints.LINE_END;
        checkoutPanel.add(new JLabel("Tax(%):"), constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_END;
        checkoutPanel.add(new JLabel(taxAmount + "%"), constraint);
        
        //total
        double subtotal = Double.parseDouble(ProductPanel.purchaseTotalAmount);
        double total = subtotal + (subtotal * 0.2);
        ProductPanel.purchaseFinalAmount = Double.toString(total);
        String totalAmount = Utilities.roundTo2ndDecimal(ProductPanel.purchaseFinalAmount);
        
        constraint.gridy++;
        constraint.gridx = 0;
        constraint.anchor = GridBagConstraints.LINE_END;
        checkoutPanel.add(new JLabel("Total:"), constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_END;
        checkoutPanel.add(new JLabel(totalAmount), constraint);
        
        //discount
        discountModel = new SpinnerNumberModel(0.0, 0.0, Double.parseDouble(ProductPanel.purchaseFinalAmount), 0.01);
        discountSpinner = new JSpinner(discountModel);
        
        constraint.gridy++;
        constraint.gridx = 0;
        constraint.anchor = GridBagConstraints.LINE_END;
        checkoutPanel.add(new JLabel("Discount:"), constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_END;
        checkoutPanel.add(discountSpinner, constraint);
        
        constraint.gridx = 2;
        constraint.anchor = GridBagConstraints.LINE_START;
        checkoutPanel.add(discountButton, constraint);
        
        //total payment
        totalPayment.setText(totalAmount);
                
        constraint.gridy++;
        constraint.gridx = 0;
        constraint.anchor = GridBagConstraints.LINE_END;
        checkoutPanel.add(new JLabel("Total payment:"), constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_END;
        checkoutPanel.add(totalPayment, constraint);
        
        //payment method
        constraint.gridy++;
        constraint.gridx = 0;
        constraint.anchor = GridBagConstraints.LINE_END;
        checkoutPanel.add(new JLabel("Payment method:"), constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_END;
        constraint.fill = GridBagConstraints.NONE;
        checkoutPanel.add(paymentMethodComboBox, constraint);
                
        //notes
        constraint.gridy++;
        constraint.gridx = 0;
        constraint.anchor = GridBagConstraints.LINE_END;
        checkoutPanel.add(new JLabel("Note:"), constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_START;
        checkoutPanel.add(noteArea, constraint);

        buttonsPanel.add(confirmButton);
        
        add(checkoutScroll, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
        
        setMinimumSize(new Dimension(480, 640));
        
        setVisible(true);
        
        discountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double finalAmountNumber = Double.parseDouble(totalAmount);
                double discountNumber = (Double) discountSpinner.getValue();
                
                double payedAmount = finalAmountNumber - discountNumber;
                
                totalPayment.setText(Double.toString(payedAmount));
            }
        });

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {    
                String customerId = customerGUIForm.getFormIdField();
                double discount = (Double) discountSpinner.getValue();
                String discountString = Double.toString(discount);
                String paymentMethod = paymentMethodComboBox.getSelectedItem().toString();
                String finalAmount = ProductPanel.purchaseFinalAmount;
                String totalPaymentString = totalPayment.getText();
                        
                invoiceGUIForm = new InvoiceGUIForm(customerId, taxAmount, finalAmount, discountString, totalPaymentString, paymentMethod, noteArea.getText(), null, null, null);
                                        
                invoiceGUIListener.invoiceGenerated(invoiceGUIForm);
                
                Utilities.notifyOfInvoiceCreation();
                
                disposeOfDialog();
            }
        });
    }

    private void disposeOfDialog() {
        dispose();
        System.gc();
    }
}
