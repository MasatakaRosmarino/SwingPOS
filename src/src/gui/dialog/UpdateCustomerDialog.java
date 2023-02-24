package gui.dialog;

import gui.guiform.ContactGUIForm;
import gui.guiform.CustomerGUIForm;
import gui.guilistener.ContactGUIListener;
import gui.guilistener.CustomerGUIListener;
import gui.guilistener.SupplierGUIListener;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.YearMonth;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.Gender;

public class UpdateCustomerDialog extends BaseDialog {
    private int selectedYear;
    private int selectedMonth;
    private String dateString = "";

    private GridBagConstraints constraint;

    private JPanel birthDatePanel;

    private JTextField nameField;
    private JTextField lastnameField;
    private JComboBox<Gender> genderCombo;
    private DefaultComboBoxModel<Gender> genderModel;
    private JTextField idNumberField;
    private JComboBox<String> yearCombo;
    private DefaultComboBoxModel<String> yearModel;
    private JComboBox<String> monthCombo;
    private DefaultComboBoxModel<String> monthModel;
    private JComboBox<String> dayCombo;
    private DefaultComboBoxModel<String> dayModel;

    private JTextField phoneField;
    private JTextField emailField;
    private JTextField addressField;
    private JTextField zipCodeField;
    private JTextField cityField;
    private JTextField provinceField;
    private JTextField countryField;
    private JButton saveButton;
        
    public UpdateCustomerDialog(CustomerGUIForm customerGUIForm, ContactGUIForm contactGUIForm, CustomerGUIListener customerGUIListener, SupplierGUIListener supplierGUIListener, ContactGUIListener contactGUIListener){ 
        setTitle("Update Customer and Contact");
        
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        constraint = new GridBagConstraints();

        birthDatePanel = new JPanel();

        birthDatePanel.setLayout(new FlowLayout());

        setLayout(new GridBagLayout());

        setAlwaysOnTop(true);

        nameField = new JTextField(10);
        lastnameField = new JTextField(10);
        genderCombo = new JComboBox<>();
        genderModel = new DefaultComboBoxModel<>();
        idNumberField = new JTextField(10);

        yearCombo = new JComboBox<>();
        yearModel = new DefaultComboBoxModel<>();

        initializeYearComboBox(yearModel);

        yearCombo.setModel(yearModel);

        monthCombo = new JComboBox<>();
        monthModel = new DefaultComboBoxModel<>();

        initializeMonthComboBox(monthModel);

        monthCombo.setModel(monthModel);

        dayCombo = new JComboBox<>();
        dayModel = new DefaultComboBoxModel<>();

        initializeDayComboBox(dayModel, 31);

        dayCombo.setModel(dayModel);

        birthDatePanel.add(yearCombo);
        birthDatePanel.add(monthCombo);
        birthDatePanel.add(dayCombo);

        saveButton = new JButton("Save");

        genderModel.addElement(Gender.male);
        genderModel.addElement(Gender.female);
        genderCombo.setModel(genderModel);

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
        add(new JLabel("Lastname:"), constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_START;
        add(lastnameField, constraint);

        //gender
        constraint.gridx = 0;
        constraint.gridy++;
        constraint.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Gender:"), constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_START;
        constraint.fill = GridBagConstraints.NONE;
        add(genderCombo, constraint);

        //idNumber
        constraint.gridx = 0;
        constraint.gridy++;
        constraint.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("ID number:"), constraint);
        
        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_START;
        constraint.fill = GridBagConstraints.NONE;
        add(idNumberField, constraint);
        
        //birthdate
        constraint.gridx = 0;
        constraint.gridy++;
        constraint.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Birth date:"), constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_START;
        constraint.fill = GridBagConstraints.NONE;
        add(birthDatePanel, constraint);

        phoneField = new JTextField(10);
        emailField = new JTextField(10);
        addressField = new JTextField(15);
        zipCodeField = new JTextField(7);
        cityField = new JTextField(10);
        provinceField = new JTextField(10);
        countryField = new JTextField(10);

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

        showDetailsFromCustomerPanel(customerGUIForm, contactGUIForm);
        
        yearCombo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent event) {
                if (event.getStateChange() == ItemEvent.SELECTED) {
                    Object item = event.getItem();

                    dateString = dateString + item + "-";
                    
                    selectedYear = Integer.valueOf((String)item);
                    
                    monthModel.removeAllElements();
                    initializeMonthComboBox(monthModel);
                }
            }
        });

        monthCombo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent event) {
                if (event.getStateChange() == ItemEvent.SELECTED) {
                    Object item = event.getItem();

                    dateString = dateString + item + "-";
                    
                    selectedMonth = Integer.valueOf((String)item);
                    
                    int daysInSelectedMonth = YearMonth.of(selectedYear, selectedMonth).lengthOfMonth();
                    
                    /**
                     *This makes sure that when selecting February, if leap year, the total amount of days is 29
                     */
                    initializeDayComboBox(dayModel, daysInSelectedMonth);
                }
            }
        });

        dayCombo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent event) {
                if (event.getStateChange() == ItemEvent.SELECTED) {
                    Object item = event.getItem();

                    dateString = dateString + item;
                }
            }
        });

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {                
                String name = nameField.getText();
                String lastname = lastnameField.getText();
                String gender = genderCombo.getSelectedItem().toString();
                String idNumber = idNumberField.getText();
                String birthdate = (String) yearCombo.getSelectedItem() + "-" + (String) monthCombo.getSelectedItem() + "-" + (String) dayCombo.getSelectedItem();
                
                customerGUIForm.setName(name);
                customerGUIForm.setLastname(lastname);
                customerGUIForm.setGender(gender);
                customerGUIForm.setIdNumber(idNumber);
                customerGUIForm.setBirthDate(birthdate);
                
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

                if (name.isEmpty() || lastname.isEmpty() || idNumber.isEmpty() || phone.isEmpty() || email.isEmpty() || address.isEmpty() || zipCode.isEmpty() || city.isEmpty() || province.isEmpty() || country.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill out all the fields");
                    return;
                } else {
                    customerGUIListener.guiTableRowSaved(customerGUIForm);
                    
                    contactGUIListener.guiTableRowSaved(contactGUIForm);
                }

                disposeOfDialog();
            }
        });
        
        addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                customerGUIListener.customerPanelEnabled(false);
                supplierGUIListener.supplierPanelEnabled(false);
            }

            public void windowClosed(WindowEvent e) {
                customerGUIListener.customerPanelEnabled(true);
                supplierGUIListener.supplierPanelEnabled(true);

                disposeOfDialog();
            }
        });
    }
    
    private void disposeOfDialog() {
        dispose();
        System.gc();
    }

    private void initializeYearComboBox(DefaultComboBoxModel<String> yearModel) {
        LocalDate today = LocalDate.now();

        int currentYear = today.getYear();
        int minYear = currentYear - 120;
        int maxYear = currentYear - 18;

        for (int i = minYear; i <= maxYear; i++) {
            String year = "" + i;
            yearModel.addElement(year);
        }
    }

    private void initializeMonthComboBox(DefaultComboBoxModel<String> monthModel) {
        for (int i = 1; i <= 12; i++) {
            String month = "";

            if (i < 10) {
                month = month + "0" + i;
            } else {
                month = month + i;
            }

            monthModel.addElement(month);
        }
    }

    private void initializeDayComboBox(DefaultComboBoxModel<String> dayModel, int maxNumber) {
        if (dayModel.getSize() > 0) {
            dayModel.removeAllElements();
        }

        for (int i = 1; i <= maxNumber; i++) {
            String day = "";

            if (i < 10) {
                day = day + "0" + i;
            } else {
                day = day + i;
            }
            dayModel.addElement(day);
        }
    }

    private void showDetailsFromCustomerPanel(CustomerGUIForm customerGUIForm, ContactGUIForm contactGUIForm) {
        String name = customerGUIForm.getName();
        String lastname = customerGUIForm.getLastname();
        String gender = customerGUIForm.getGender();
        String idNumber = customerGUIForm.getIdNumber();

        nameField.setText(name);
        lastnameField.setText(lastname);
        genderModel.setSelectedItem(gender);
        idNumberField.setText(idNumber);

        LocalDate birthDate = LocalDate.parse(customerGUIForm.getBirthDate());

        String year = "" + birthDate.getYear();
        yearModel.setSelectedItem(year);

        String month = birthDate.getMonthValue() < 10 ? "0" + birthDate.getMonthValue() : "" + birthDate.getMonthValue();
        monthModel.setSelectedItem(month);

        String day = birthDate.getDayOfMonth() < 10 ? "0" + birthDate.getDayOfMonth() : "" + birthDate.getDayOfMonth();
        dayModel.setSelectedItem(day);

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