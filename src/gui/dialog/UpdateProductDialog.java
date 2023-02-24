package gui.dialog;

import gui.ProductPanel;
import gui.SupplierPanel;
import gui.guiform.CategoryGUIForm;
import gui.guiform.ProductGUIForm;
import gui.guilistener.ProductGUIListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.NumberFormatter;

public class UpdateProductDialog extends BaseDialog {

    private final JTextField nameField;
    private final JTextArea descriptionArea;
    private final JSpinner acquisitionPriceSpinner;
    private final JSpinner sellingPriceSpinner;
    private final SpinnerNumberModel acquisitionPriceModel;
    private final JFormattedTextField acquisitionPriceText ;
    private final SpinnerNumberModel sellingPriceModel;
    private final JFormattedTextField sellingPriceText;
    private final JSpinner itemsPerUnitSpinner;
    private final SpinnerNumberModel itemsPerUnitModel;
    private final JFormattedTextField itemsPerUnitText;
    private final JComboBox<String> conditionBox;
    private final DefaultComboBoxModel<String> conditionModel;
    private final JComboBox<String> categoryBox;
    private final DefaultComboBoxModel<String> categoryModel;

    private final JButton saveButton;

    private final GridBagConstraints constraint;

    private CategoryGUIForm categoryGUIForm;
    private ProductGUIForm productGUIForm;

    private int selectedCategoryIndex;
    
    public UpdateProductDialog(ProductPanel productPanel, ProductGUIListener productListener) {
    	setTitle("Update product info");
    	
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
        
        acquisitionPriceText = ((JSpinner.NumberEditor) acquisitionPriceSpinner.getEditor()).getTextField();
        ((NumberFormatter) acquisitionPriceText.getFormatter()).setAllowsInvalid(false);
        
        sellingPriceText = ((JSpinner.NumberEditor) sellingPriceSpinner.getEditor()).getTextField();
        ((NumberFormatter) sellingPriceText.getFormatter()).setAllowsInvalid(false);
        
        itemsPerUnitText = ((JSpinner.NumberEditor) itemsPerUnitSpinner.getEditor()).getTextField();
        ((NumberFormatter) itemsPerUnitText.getFormatter()).setAllowsInvalid(false);

        conditionModel = new DefaultComboBoxModel<>();
        
        conditionModel.addElement("Brand new");
        conditionModel.addElement("Like new");
        conditionModel.addElement("Good");
        conditionModel.addElement("Acceptable");
        conditionModel.addElement("Worn");

        conditionBox = new JComboBox<>(conditionModel);

        categoryModel = productPanel.getCategoryComboBoxItems();
        categoryBox = new JComboBox<>(categoryModel);

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

        //acquisition sellingPrice
        constraint.gridx = 0;
        constraint.gridy++;
        constraint.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Acquisition Price:"), constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_START;
        add(acquisitionPriceSpinner, constraint);
        
        //selling sellingPrice
        constraint.gridx = 0;
        constraint.gridy++;
        constraint.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Selling Price:"), constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_START;
        add(sellingPriceSpinner, constraint);

        //items per unit
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
        add(categoryBox, constraint);

        //save button
        constraint.weightx = 1.0;
        constraint.weighty = 2.0;
        constraint.gridx = 1;
        constraint.gridy++;
        constraint.anchor = GridBagConstraints.FIRST_LINE_START;
        add(saveButton, constraint);

        setVisible(true);

        //setting product fields from the calling table
        setObjectFieldsFromParentComponent(productPanel);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (categoryBox.getSelectedIndex() != -1) {
                    selectedCategoryIndex = (categoryBox.getSelectedIndex());

                    //setting productGUIForm fields from the update dialog
                    productGUIForm.setProductName(nameField.getText());
                    productGUIForm.setProductDescription(descriptionArea.getText());
                    Double acquisitionPrice = (Double) acquisitionPriceSpinner.getValue();
                    Double sellingPrice = (Double) sellingPriceSpinner.getValue();
                    String acquisitionPriceString = Double.toString(acquisitionPrice);
                    String sellingPriceString = Double.toString(sellingPrice);
                    
                    productGUIForm.setAcquisitionPrice(acquisitionPriceString);
                    productGUIForm.setSellingPrice(sellingPriceString);
                    String itemsPerUnit = Integer.toString((Integer) itemsPerUnitModel.getValue());
                    productGUIForm.setProductItemsPerUnit(itemsPerUnit);
                    String condition = (String) conditionBox.getSelectedItem();
                    productGUIForm.setProductCondition(condition);
                    productGUIForm.setProductCategoryId(Integer.toString(selectedCategoryIndex));
                    productGUIForm.setSupplierId(Integer.toHexString(SupplierPanel.supplierId));
                    
                    productListener.guiTableRowSaved(productGUIForm);
                }
                
                disposeOfDialog();
            }
        });
    }

    private void setObjectFieldsFromParentComponent(ProductPanel productPanel) {
        productGUIForm = productPanel.getProductGUIForm();
        
        String name = productGUIForm.getProductName();
        String description = productGUIForm.getProductDescription();

        String acquisitionPrice = productGUIForm.getAcquisitionPrice();
        String sellingPrice = productGUIForm.getSellingPrice();

        String itemsPerUnit = productGUIForm.getProductItemsPerUnit();
        String condition = productGUIForm.getProductCondition();

        int selectedCategoryIndex = productPanel.getSelectedCategoryIndex();
        List<CategoryGUIForm> categoriesList = productPanel.getCategoryGUIFormsList();

        if (selectedCategoryIndex != -1) {
            categoryGUIForm = categoriesList.get(selectedCategoryIndex);
        }

        nameField.setText(name);
        descriptionArea.setText(description);

        acquisitionPriceModel.setValue((Double) Double.parseDouble(acquisitionPrice));
        sellingPriceModel.setValue((Double) Double.parseDouble(sellingPrice));
        itemsPerUnitModel.setValue(Integer.parseInt(itemsPerUnit));
        conditionModel.setSelectedItem((String) condition);
        categoryModel.setSelectedItem(categoryGUIForm.getCategoryName());
    }
    
    private void disposeOfDialog() {
        dispose();
        System.gc();
    }
}
