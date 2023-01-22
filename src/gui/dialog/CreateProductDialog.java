package gui.dialog;

import gui.guiform.ProductGUIForm;
import gui.ProductPanel;
import gui.SupplierPanel;
import gui.guilistener.ProductGUIListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import utilities.Utilities;

public class CreateProductDialog extends BaseDialog {

    private JTextField nameField;
    private JTextArea descriptionArea;
    private JSpinner acquisitionPriceSpinner;
    private JSpinner sellingPriceSpinner;
    private final SpinnerNumberModel acquisitionPriceModel;
    private final SpinnerNumberModel sellingPriceModel;
    private JSpinner itemsPerUnitSpinner;
    private final SpinnerNumberModel itemsPerUnitModel;
    private JComboBox<String> conditionBox;
    private final DefaultComboBoxModel<String> conditionModel;
    private final JComboBox<String> categoryBox;
    private final DefaultComboBoxModel<String> categoryModel;
    private final JLabel categoryLabel;
    private final JButton saveButton;
    private final GridBagConstraints constraint;

    public CreateProductDialog(ProductPanel productPanel, ProductGUIListener productListener) {
    	setTitle("Register new product");
    	
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        setLayout(new GridBagLayout());

        constraint = new GridBagConstraints();

        nameField = new JTextField(10);
        descriptionArea = new JTextArea(5, 20);

        acquisitionPriceModel = new SpinnerNumberModel(0.01, 0.01, 9999.99, 0.01);
        sellingPriceModel = new SpinnerNumberModel(0.01, 0.01, 9999.99, 0.01);
        acquisitionPriceSpinner = new JSpinner(acquisitionPriceModel);
        sellingPriceSpinner = new JSpinner(sellingPriceModel);

        itemsPerUnitModel = new SpinnerNumberModel(1, 1, 99, 1);
        itemsPerUnitSpinner = new JSpinner(itemsPerUnitModel);

        conditionModel = new DefaultComboBoxModel<>();

        conditionModel.addElement("brandnew");
        conditionModel.addElement("likenew");
        conditionModel.addElement("good");
        conditionModel.addElement("acceptable");
        conditionModel.addElement("worn");

        conditionBox = new JComboBox<>(conditionModel);

        categoryModel = productPanel.getCategoryComboBoxItems();
        categoryBox = new JComboBox<>(categoryModel);
        categoryLabel = new JLabel((String) categoryBox.getSelectedItem());

        saveButton = new JButton("Save");

        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setLineWrap(true);
        descriptionArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));

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

        //description
        constraint.gridx = 0;
        constraint.gridy++;
        constraint.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Description:"), constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_START;
        add(descriptionArea, constraint);

        //acquisition Price
        constraint.gridx = 0;
        constraint.gridy++;
        constraint.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Acquisition Price:"), constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_START;
        add(acquisitionPriceSpinner, constraint);

        //selling Price
        constraint.gridx = 0;
        constraint.gridy++;
        constraint.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Selling Price:"), constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_START;
        add(sellingPriceSpinner, constraint);

        //availability
        constraint.gridx = 0;
        constraint.gridy++;
        constraint.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Items per unit:"), constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_START;
        add(itemsPerUnitSpinner, constraint);

        //condition
        constraint.gridx = 0;
        constraint.gridy++;
        constraint.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Condition:"), constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_START;
        add(conditionBox, constraint);

        //category
        constraint.gridx = 0;
        constraint.gridy++;
        constraint.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Category:"), constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_START;
        add(categoryLabel, constraint);
        categoryBox.setEnabled(false);

        //save button
        constraint.weightx = 1.0;
        constraint.weighty = 2.0;
        constraint.gridx = 1;
        constraint.gridy++;
        constraint.anchor = GridBagConstraints.FIRST_LINE_START;
        add(saveButton, constraint);

        setVisible(true);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String description = descriptionArea.getText();
                Double acquisitionPrice = (Double) acquisitionPriceSpinner.getValue();
                Double sellingPrice = (Double) sellingPriceSpinner.getValue();
                String acquisitionPriceString = Double.toString(acquisitionPrice);
                String sellingPriceString = Double.toString(sellingPrice);
                String itemsPerUnit = itemsPerUnitSpinner.getValue().toString();
                String condition = (String) conditionBox.getSelectedItem();
                String stringCategoryId = Integer.toString(ProductPanel.productCategoryId);
                String supplierId = Integer.toString(SupplierPanel.supplierId);

                ProductGUIForm productGUIForm = new ProductGUIForm(name, description, acquisitionPriceString, sellingPriceString, itemsPerUnit, condition, null, stringCategoryId, supplierId, null);
                
                if(name.isEmpty() || description.isEmpty() || acquisitionPrice == 0.0  || sellingPrice == 0.0 || itemsPerUnit.equals("0")){
                    JOptionPane.showMessageDialog(null, "Please fill out all the fields");
                    return;
                }else if(sellingPrice < acquisitionPrice){
                    JOptionPane.showMessageDialog(null, "Please set a higher selling price value");
                    return;
                }else{
                    productListener.guiTableRowSaved(productGUIForm);
                }

                disposeOfDialog();
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                productPanel.setTableEnabled(false);
            }

            @Override
            public void windowClosed(WindowEvent e) {
                productPanel.setTableEnabled(true);

                disposeOfDialog();
            }
        });
    }

    private void disposeOfDialog() {
        dispose();
        System.gc();
    }
}
