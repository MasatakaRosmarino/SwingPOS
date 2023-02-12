/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import gui.guiform.ContactGUIForm;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author ROM1BAC
 */
public class ContactPanel extends JPanel {

    private JTextField phoneField;
    private JTextField emailField;
    private JTextField addressField;
    private JTextField zipCodeField;
    private JTextField cityField;
    private JTextField provinceField;
    private JTextField countryField;
    
    private GridBagConstraints constraint;
    
    private ContactGUIForm contactGUIForm;
    
    public static int contactId;
    
    public ContactPanel() {
        setLayout(new GridBagLayout());
        
        constraint = new GridBagConstraints();
        
        phoneField = new JTextField(10);
        emailField = new JTextField(10);
        addressField = new JTextField(15);
        zipCodeField = new JTextField(7);
        cityField = new JTextField(10);
        provinceField = new JTextField(10);
        countryField = new JTextField(10);
         
        //setting up constraints parameters
        constraint.weightx = 1;
        constraint.weighty = 0.1;
        constraint.insets = new Insets(5, 5, 5, 5);

        // Title
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("CONTACT:"), constraint);

        // Phone
        constraint.gridx = 0;
        constraint.gridy++;
        constraint.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("PHONE:"), constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_START;
        add(phoneField, constraint);

        // email
        constraint.gridx = 0;
        constraint.gridy++;
        constraint.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("E-MAIL:"), constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_START;
        add(emailField, constraint);

        // address
        constraint.gridx = 0;
        constraint.gridy++;
        constraint.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("ADDRESS:"), constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_START;
        add(addressField, constraint);

        // zip code
        constraint.gridx = 0;
        constraint.gridy++;
        constraint.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("ZIP CODE:"), constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_START;
        add(zipCodeField, constraint);

        // city
        constraint.gridx = 0;
        constraint.gridy++;
        constraint.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("CITY:"), constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_START;
        add(cityField, constraint);

        // province
        constraint.gridx = 0;
        constraint.gridy++;
        constraint.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("PROVINCE/STATE:"), constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_START;
        add(provinceField, constraint);

        // country
        constraint.gridx = 0;
        constraint.gridy++;
        constraint.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("COUNTRY:"), constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_START;
        add(countryField, constraint);
        
        setContactEditableFields(false);
    }
    
    public void setContactFormFields(ContactGUIForm contactGUIForm) {
        phoneField.setText(contactGUIForm.getPhone());
        emailField.setText(contactGUIForm.getEmail());
        addressField.setText(contactGUIForm.getAddress());
        zipCodeField.setText(contactGUIForm.getZipCode());
        cityField.setText(contactGUIForm.getCity());
        provinceField.setText(contactGUIForm.getProvince());
        countryField.setText(contactGUIForm.getCountry());
    }

    public void setEmptyContactForm() {
        phoneField.setText("");
        emailField.setText("");
        addressField.setText("");
        zipCodeField.setText("");
        cityField.setText("");
        provinceField.setText("");
        countryField.setText("");
    }

    private void setContactEditableFields(boolean editable) {
        phoneField.setEditable(editable);
        emailField.setEditable(editable);
        addressField.setEditable(editable);
        zipCodeField.setEditable(editable);
        cityField.setEditable(editable);
        provinceField.setEditable(editable);
        countryField.setEditable(editable);
    }

    /**
     *
     * @return ContactGUIForm object from the edited contact panel
     */
    public ContactGUIForm getContactPanelFormInfo() {
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

        return contactGUIForm;
    }
    
    public void setContactGUIForm(ContactGUIForm contactGUIForm) {
        this.contactGUIForm = contactGUIForm;
    }
}
