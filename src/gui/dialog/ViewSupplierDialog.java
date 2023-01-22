package gui.dialog;

import gui.guiform.ContactGUIForm;
import gui.guiform.SupplierGUIForm;
import gui.guilistener.CustomerGUIListener;
import gui.guilistener.SupplierGUIListener;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class ViewSupplierDialog extends JDialog {
    private final GridBagConstraints constraint;

    private final JLabel idNumberLabel;
    private final JLabel nameLabel;
    private final JLabel vatNumberLabel;
    private final JLabel isBusinessLabel;
    
    private final JLabel phoneLabel;
    private final JLabel emailLabel;
    private final JLabel addressLabel;
    private final JLabel zipCodeLabel;
    private final JLabel cityLabel;
    private final JLabel provinceLabel;
    private final JLabel countryLabel;
    
    public ViewSupplierDialog(SupplierGUIForm supplierGUIForm, ContactGUIForm contactGUIForm, CustomerGUIListener customerGUIListener, SupplierGUIListener supplierGUIListener) {
        setTitle("View Supplier and Contact");
        
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        constraint = new GridBagConstraints();
        
        idNumberLabel = new JLabel();
        
        setLayout(new GridBagLayout());

        setAlwaysOnTop(true);

        nameLabel = new JLabel();
        vatNumberLabel = new JLabel();
        isBusinessLabel = new JLabel();
        
        phoneLabel = new JLabel();
        emailLabel = new JLabel();
        addressLabel = new JLabel();
        zipCodeLabel = new JLabel();
        cityLabel = new JLabel();
        provinceLabel = new JLabel();
        countryLabel = new JLabel();
        
        //setting up constraints parameters
        constraint.weightx = 1;
        constraint.weighty = 0.1;
        constraint.insets = new Insets(5, 5, 0, 5);

        //id
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("ID:"), constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_START;
        add(idNumberLabel, constraint);
        
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
        add(new JLabel("VAT NUMBER:"), constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_START;
        add(vatNumberLabel, constraint);

        //gender
        constraint.gridx = 0;
        constraint.gridy++;
        constraint.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("IS BUSINESS:"), constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_START;
        constraint.fill = GridBagConstraints.NONE;
        add(isBusinessLabel, constraint);
        
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
        
        setMinimumSize(new Dimension(480, 640));

        setVisible(true);

        showDetailsFromSupplierPanel(supplierGUIForm, contactGUIForm);
        
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
    
    private void showDetailsFromSupplierPanel(SupplierGUIForm supplierGUIForm, ContactGUIForm contactGUIForm) {        
        String id = supplierGUIForm.getFormIdField();
        String name = supplierGUIForm.getName();
        String vatNumber = supplierGUIForm.getVatNumber();
        String isBusiness = supplierGUIForm.isIsBusiness();

        idNumberLabel.setText(id);
        nameLabel.setText(name);
        vatNumberLabel.setText(vatNumber);
        isBusinessLabel.setText((isBusiness.equals("true") ? "Yes" : "-"));
        
        phoneLabel.setText(contactGUIForm.getPhone());
        emailLabel.setText(contactGUIForm.getEmail());
        addressLabel.setText(contactGUIForm.getAddress());
        zipCodeLabel.setText(contactGUIForm.getZipCode());
        cityLabel.setText(contactGUIForm.getCity());
        provinceLabel.setText(contactGUIForm.getProvince());
        countryLabel.setText(contactGUIForm.getCountry());
    }
}
