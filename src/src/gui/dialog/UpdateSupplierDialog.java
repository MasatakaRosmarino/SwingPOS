package gui.dialog;

import gui.guiform.ContactGUIForm;
import gui.guiform.SupplierGUIForm;
import gui.guilistener.ContactGUIListener;
import gui.guilistener.CustomerGUIListener;
import gui.guilistener.SupplierContactGUIListener;
import gui.guilistener.SupplierGUIListener;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class UpdateSupplierDialog extends JDialog {
    private GridBagConstraints constraint;
    
    private JTextField nameField;
    private JTextField vatNumberField;
    private JCheckBox isBusinessCheckBox;
    
    private JTextField phoneField;
    private JTextField emailField;
    private JTextField addressField;
    private JTextField zipCodeField;
    private JTextField cityField;
    private JTextField provinceField;
    private JTextField countryField;
    private JButton saveButton;
    
    public UpdateSupplierDialog(SupplierGUIForm supplierGUIForm, ContactGUIForm contactGUIForm, SupplierGUIListener supplierGUIListener, CustomerGUIListener customerGUIListener, ContactGUIListener contactGUIListener) {

        setTitle("Update Supplier and Contact");
        
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        constraint = new GridBagConstraints();

        setLayout(new GridBagLayout());

        setAlwaysOnTop(true);

        nameField = new JTextField(10);
        vatNumberField = new JTextField(10);
        isBusinessCheckBox = new JCheckBox();
        
        phoneField = new JTextField(10);
        emailField = new JTextField(10);
        addressField = new JTextField(15);
        zipCodeField = new JTextField(7);
        cityField = new JTextField(10);
        provinceField = new JTextField(10);
        countryField = new JTextField(10);
        
        saveButton = new JButton("Save");

        //setting up constraints parameters
        constraint.weightx = 1;
        constraint.weighty = 0.1;
        constraint.insets = new Insets(0, 0, 0, 5);

        //name
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Name:"), constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_START;
        add(nameField, constraint);

        //lastname
        constraint.gridx = 0;
        constraint.gridy++;
        constraint.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("VAT Number:"), constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_START;
        add(vatNumberField, constraint);

        //gender
        constraint.gridx = 0;
        constraint.gridy++;
        constraint.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Is Business:"), constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_START;
        constraint.fill = GridBagConstraints.NONE;
        add(isBusinessCheckBox, constraint);

        //phone
        constraint.gridx = 0;
        constraint.gridy++;
        constraint.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Phone:"), constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_START;
        add(phoneField, constraint);

        //e-mail
        constraint.gridx = 0;
        constraint.gridy++;
        constraint.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("e-mail:"), constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_START;
        add(emailField, constraint);

        //address
        constraint.gridx = 0;
        constraint.gridy++;
        constraint.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Address:"), constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_START;
        add(addressField, constraint);

        //zip code
        constraint.gridx = 0;
        constraint.gridy++;
        constraint.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Zip Code:"), constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_START;
        add(zipCodeField, constraint);

        //city
        constraint.gridx = 0;
        constraint.gridy++;
        constraint.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("City:"), constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_START;
        add(cityField, constraint);

        //province
        constraint.gridx = 0;
        constraint.gridy++;
        constraint.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Province/State:"), constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_START;
        add(provinceField, constraint);

        //country
        constraint.gridx = 0;
        constraint.gridy++;
        constraint.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Country:"), constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_START;
        add(countryField, constraint);

        //save button
        constraint.weightx = 1.0;
        constraint.weighty = 2.0;
        constraint.gridx = 1;
        constraint.gridy++;
        constraint.anchor = GridBagConstraints.FIRST_LINE_START;
        add(saveButton, constraint);

        setMinimumSize(new Dimension(480, 640));

        setVisible(true);

        showDetailsFromSupplierPanel(supplierGUIForm, contactGUIForm);
        
        isBusinessCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    vatNumberField.setEnabled(true);
                    vatNumberField.setText("");
                } else {
                    vatNumberField.setText("-");
                    vatNumberField.setEnabled(false);
                }
            }
        });
        
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {                
                String name = nameField.getText();
                String vatNumber = vatNumberField.getText();
                String isBusiness = Boolean.toString(isBusinessCheckBox.isSelected());
                
                supplierGUIForm.setName(name);
                supplierGUIForm.setVatNumber(vatNumber);
                supplierGUIForm.setIsBusiness(isBusiness);

                String phone = phoneField.getText();
                String email = emailField.getText();
                String address = addressField.getText();
                String zipCode = zipCodeField.getText();
                String city = cityField.getText();
                String province = provinceField.getText();
                String country = countryField.getText();
                
                contactGUIForm.setPhone(phone);
                contactGUIForm.setEmail(email);
                contactGUIForm.setAddress(address);
                contactGUIForm.setZipCode(zipCode);
                contactGUIForm.setCity(city);
                contactGUIForm.setProvince(province);
                contactGUIForm.setCountry(country);

                if (name.isEmpty() || (isBusinessCheckBox.isSelected() && vatNumber.isEmpty()) || phone.isEmpty() || email.isEmpty() || address.isEmpty() || zipCode.isEmpty() || city.isEmpty() || province.isEmpty() || country.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill out all the fields");
                    return;
                } else {
                    supplierGUIListener.guiTableRowSaved(supplierGUIForm);
                    
                    contactGUIListener.guiTableRowSaved(contactGUIForm);
                }

                disposeOfDialog();
            }
        });
        
        addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                supplierGUIListener.supplierPanelEnabled(false);
                customerGUIListener.customerPanelEnabled(false);
            }

            public void windowClosed(WindowEvent e) {
                supplierGUIListener.supplierPanelEnabled(true);
                customerGUIListener.customerPanelEnabled(true);

                disposeOfDialog();
            }
        });
    }
    
    private void disposeOfDialog() {
        dispose();
        System.gc();
    }
    
    private void showDetailsFromSupplierPanel(SupplierGUIForm supplierGUIForm, ContactGUIForm contactGUIForm) {
        String name = supplierGUIForm.getName();
        String vatNumber = supplierGUIForm.getVatNumber();
        String isBusiness = supplierGUIForm.isIsBusiness();

        nameField.setText(name);
        vatNumberField.setText(vatNumber);
        
        if(isBusiness.equals("true")){
            isBusinessCheckBox.setSelected(true);
        }else{
            isBusinessCheckBox.setSelected(false);
        }
        
        //contact fields
        phoneField.setText(contactGUIForm.getPhone());
        emailField.setText(contactGUIForm.getEmail());
        addressField.setText(contactGUIForm.getAddress());
        zipCodeField.setText(contactGUIForm.getZipCode());
        cityField.setText(contactGUIForm.getCity());
        provinceField.setText(contactGUIForm.getProvince());
        countryField.setText(contactGUIForm.getCountry());
    }
}
