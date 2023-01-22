package gui.dialog;

import gui.ProductPanel;
import gui.guiform.ProductGUIForm;
import gui.guilistener.ProductGUIListener;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class ViewProductDialog extends BaseDialog {

    private final JLabel productIdLabel;
    private final JLabel nameLabel;
    private final JLabel descriptionLabel;
    private final JLabel acquisitionPriceLabel;
    private final JLabel sellingPriceLabel;
    private final JLabel itemsPerUnitLabel;
    private final JLabel conditionLabel;
    private final JLabel addedOnLabel;
    private final JLabel categoryLabel;

    private final GridBagConstraints constraint;

    public ViewProductDialog(ProductGUIForm productGUIForm, ProductGUIListener productGUIListener) {   
    	setTitle("View product info");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        setLayout(new GridBagLayout());

        constraint = new GridBagConstraints();

        productIdLabel = new JLabel();
        nameLabel = new JLabel();
        descriptionLabel = new JLabel();
        acquisitionPriceLabel = new JLabel();
        sellingPriceLabel = new JLabel();
        itemsPerUnitLabel = new JLabel();
        conditionLabel = new JLabel();
        addedOnLabel = new JLabel();
        categoryLabel = new JLabel();

        //setting up constraints parameters
        constraint.weightx = 1;
        constraint.weighty = 0.1;
        constraint.insets = new Insets(0, 0, 0, 5);

        //id
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("ID:"), constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_START;
        add(productIdLabel, constraint);
        
        //name
        constraint.gridx = 0;
        constraint.gridy++;
        constraint.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("NAME:"), constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_START;
        add(nameLabel, constraint);

        //description
        constraint.gridx = 0;
        constraint.gridy++;
        constraint.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("DESCRIPTION:"), constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_START;
        add(descriptionLabel, constraint);

        //acquisition sellingPrice
        constraint.gridx = 0;
        constraint.gridy++;
        constraint.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("ACQUISITION PRICE:"), constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_START;
        add(acquisitionPriceLabel, constraint);
        
        //selling sellingPrice
        constraint.gridx = 0;
        constraint.gridy++;
        constraint.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("SELLING PRICE:"), constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_START;
        add(sellingPriceLabel, constraint);

        //items per unit
        constraint.gridx = 0;
        constraint.gridy++;
        constraint.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("ITEMS PER UNIT:"), constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_START;
        add(itemsPerUnitLabel, constraint);

        //condition
        constraint.gridx = 0;
        constraint.gridy++;
        constraint.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("CONDITION:"), constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_START;
        add(conditionLabel, constraint);
        
        //added on
        constraint.gridx = 0;
        constraint.gridy++;
        constraint.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("ADDED ON:"), constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_START;
        add(addedOnLabel, constraint);

        //category
        constraint.gridx = 0;
        constraint.gridy++;
        constraint.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("CATEGORY:"), constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_START;
        add(categoryLabel, constraint);

        setVisible(true);

        //setting product fields from the calling table
        setObjectFieldsFromParentComponent(productGUIForm);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                productGUIListener.productPanelEnabled(false);
            }

            @Override
            public void windowClosed(WindowEvent e) {
                productGUIListener.productPanelEnabled(true);

                disposeOfDialog();
            }
        });
    }

    private void setObjectFieldsFromParentComponent(ProductGUIForm productGUIForm) {
        String id = productGUIForm.getFormIdField();
        String name = productGUIForm.getProductName();
        String description = productGUIForm.getProductDescription();
        String acquisitionPrice = productGUIForm.getAcquisitionPrice();
        String sellingPrice = productGUIForm.getSellingPrice();
        String itemsPerUnit = productGUIForm.getProductItemsPerUnit();
        String condition = productGUIForm.getProductCondition();
        String addedOn = productGUIForm.getAddedOn();

        productIdLabel.setText(id);
        nameLabel.setText(name);
        descriptionLabel.setText(description);
        acquisitionPriceLabel.setText(acquisitionPrice);
        sellingPriceLabel.setText(sellingPrice);
        itemsPerUnitLabel.setText(itemsPerUnit);
        conditionLabel.setText(condition);
        addedOnLabel.setText(addedOn);
        categoryLabel.setText(ProductPanel.productCategory);
    }

    private void disposeOfDialog() {
        dispose();
        System.gc();
    }
}
