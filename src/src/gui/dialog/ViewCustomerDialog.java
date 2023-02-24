package gui.dialog;

import gui.UsersPanel;
import gui.guiform.ContactGUIForm;
import gui.guiform.CustomerGUIForm;
import gui.guilistener.CustomerGUIListener;
import gui.guilistener.SupplierGUIListener;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ViewCustomerDialog extends BaseDialog {

    private GridBagConstraints constraint;

    private JPanel birthDatePanel;

    private JLabel customerIdLabel;
    private JLabel idNumberLabel;
    private JLabel nameLabel;
    private JLabel lastnameLabel;
    private JLabel genderLabel;
    private JLabel yearLabel;
    private JLabel monthLabel;
    private JLabel dayLabel;
    
    private JLabel phoneLabel;
    private JLabel emailLabel;
    private JLabel addressLabel;
    private JLabel zipCodeLabel;
    private JLabel cityLabel;
    private JLabel provinceLabel;
    private JLabel countryLabel;
    
    public ViewCustomerDialog(CustomerGUIForm customerGUIForm, ContactGUIForm contactGUIForm, CustomerGUIListener customerGUIListener, SupplierGUIListener supplierGUIListener){
        setTitle("View Customer and Contact");
        
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        constraint = new GridBagConstraints();
        
        customerIdLabel = new JLabel();
        
        idNumberLabel = new JLabel();

        birthDatePanel = new JPanel();

        birthDatePanel.setLayout(new FlowLayout());

        setLayout(new GridBagLayout());

        setAlwaysOnTop(true);

        nameLabel = new JLabel();
        lastnameLabel = new JLabel();
        
        genderLabel = new JLabel();
        
        yearLabel = new JLabel();
        monthLabel = new JLabel();
        dayLabel = new JLabel();
        
        birthDatePanel.add(yearLabel);
        birthDatePanel.add(new JLabel("-"));
        birthDatePanel.add(monthLabel);
        birthDatePanel.add(new JLabel("-"));
        birthDatePanel.add(dayLabel);

        //setting up constraints parameters
        constraint.weightx = 1;
        constraint.weighty = 0.1;
        constraint.insets = new Insets(5, 5, 0, 5);

        //DB id
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("ID:"), constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_START;
        add(customerIdLabel, constraint);
        
        //name
        constraint.gridx = 0;
        constraint.gridy++;
        constraint.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("NAME:"), constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_START;
        add(nameLabel, constraint);

        //lastname
        constraint.gridx = 0;
        constraint.gridy++;
        constraint.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("LASTNAME:"), constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_START;
        add(lastnameLabel, constraint);

        //gender
        constraint.gridx = 0;
        constraint.gridy++;
        constraint.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("GENDER:"), constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_START;
        constraint.fill = GridBagConstraints.NONE;
        add(genderLabel, constraint);

        //idNumber
        constraint.gridx = 0;
        constraint.gridy++;
        constraint.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("ID NUMBER:"), constraint);
        
        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_START;
        constraint.fill = GridBagConstraints.NONE;
        add(idNumberLabel, constraint);
        
        //birthdate
        constraint.gridx = 0;
        constraint.gridy++;
        constraint.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("BIRTH DATE:"), constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_START;
        constraint.fill = GridBagConstraints.NONE;
        add(birthDatePanel, constraint);

        phoneLabel = new JLabel();
        emailLabel = new JLabel();
        addressLabel = new JLabel();
        zipCodeLabel = new JLabel();
        cityLabel = new JLabel();
        provinceLabel = new JLabel();
        countryLabel = new JLabel();

        //phone
        constraint.gridx = 0;
        constraint.gridy++;
        constraint.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("PHONE:"), constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_START;
        add(phoneLabel, constraint);

        //e-mail
        constraint.gridx = 0;
        constraint.gridy++;
        constraint.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("E-MAIL:"), constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_START;
        add(emailLabel, constraint);

        //address
        constraint.gridx = 0;
        constraint.gridy++;
        constraint.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("ADDRESS:"), constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_START;
        add(addressLabel, constraint);

        //zip code
        constraint.gridx = 0;
        constraint.gridy++;
        constraint.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("ZIP CODE:"), constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_START;
        add(zipCodeLabel, constraint);

        //city
        constraint.gridx = 0;
        constraint.gridy++;
        constraint.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("CITY:"), constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_START;
        add(cityLabel, constraint);

        //province
        constraint.gridx = 0;
        constraint.gridy++;
        constraint.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("PROVINCE/STATE:"), constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_START;
        add(provinceLabel, constraint);

        //country
        constraint.gridx = 0;
        constraint.gridy++;
        constraint.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("COUNTRY:"), constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_START;
        add(countryLabel, constraint);

        setVisible(true);

        showDetailsFromCustomerPanel(customerGUIForm, contactGUIForm);
        
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
    
    private void showDetailsFromCustomerPanel(CustomerGUIForm customerGUIForm, ContactGUIForm contactGUIForm) {
        String dbCustomerId = customerGUIForm.getFormIdField();
        String name = customerGUIForm.getName();
        String lastname = customerGUIForm.getLastname();
        String gender = customerGUIForm.getGender();
        String idNumber = customerGUIForm.getIdNumber();

        customerIdLabel.setText(dbCustomerId);
        nameLabel.setText(name);
        lastnameLabel.setText(lastname);
        genderLabel.setText(gender);
        idNumberLabel.setText(idNumber);

        LocalDate birthDate = LocalDate.parse(customerGUIForm.getBirthDate());

        String year = "" + birthDate.getYear();
        yearLabel.setText(year);

        String month = birthDate.getMonthValue() < 10 ? "0" + birthDate.getMonthValue() : "" + birthDate.getMonthValue();
        monthLabel.setText(month);

        String day = birthDate.getDayOfMonth() < 10 ? "0" + birthDate.getDayOfMonth() : "" + birthDate.getDayOfMonth();
        dayLabel.setText(day);

        //contact fields
        phoneLabel.setText(contactGUIForm.getPhone());
        emailLabel.setText(contactGUIForm.getEmail());
        addressLabel.setText(contactGUIForm.getAddress());
        zipCodeLabel.setText(contactGUIForm.getZipCode());
        cityLabel.setText(contactGUIForm.getCity());
        provinceLabel.setText(contactGUIForm.getProvince());
        countryLabel.setText(contactGUIForm.getCountry());
    }
}
