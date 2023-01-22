package gui.dialog;

import database.DataBaseManager;
import gui.guilistener.MenuBarListener;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import utilities.SettingsFile;

public class SettingsDialog extends JDialog {

    private final JPanel settingsPanel;
    private final JTextField dbURLField;
    private final JSpinner dbPort;
    private final JTextField dbUsernameField;
    private final JTextField dbPasswordField;
    private final JSpinner queryLimitSpinner;
    private final SpinnerNumberModel queryLimitModel;
    private final SpinnerNumberModel dbPortModel;
    
    private final JPanel buttonPanel;
    private final JButton saveButton;
    
    private final GridBagConstraints constraint;
    
    public SettingsDialog(SettingsFile settingsFile, MenuBarListener menuBarListener) {
        setLayout(new BorderLayout());
             
        settingsPanel = new JPanel();
        
        settingsPanel.setLayout(new GridBagLayout());
        
        constraint = new GridBagConstraints();
        
        dbURLField = new JTextField(30);
        dbUsernameField = new JTextField(20);
        dbPasswordField = new JTextField(20);
        
        dbURLField.setText(DataBaseManager.dbUrl);
        dbUsernameField.setText(DataBaseManager.dbUser);
        dbPasswordField.setText(DataBaseManager.dbPassword);
        
        dbPortModel = new SpinnerNumberModel(3306, 0, 9999, 1);
        dbPort = new JSpinner(dbPortModel);
        
        queryLimitModel = new SpinnerNumberModel(5, 5, DataBaseManager.MAX_LIMIT, 5);
        queryLimitSpinner = new JSpinner(queryLimitModel);
        
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        
        saveButton = new JButton("Save");
        
        buttonPanel.add(saveButton);
        
        //setting up constraints parameters
        constraint.weightx = 1;
        constraint.weighty = 0.1;
        constraint.insets = new Insets(0, 0, 0, 5);
        
        //Database url
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.anchor = GridBagConstraints.LINE_END;
        settingsPanel.add(new JLabel("Database URL:"), constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_START;
        settingsPanel.add(dbURLField, constraint);
        
        //Database port
        constraint.gridx = 0;
        constraint.gridy++;
        constraint.anchor = GridBagConstraints.LINE_END;
        settingsPanel.add(new JLabel("Database Port:"), constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_START;
        settingsPanel.add(dbPort, constraint);
        
        //Database user
        constraint.gridx = 0;
        constraint.gridy++;
        constraint.anchor = GridBagConstraints.LINE_END;
        settingsPanel.add(new JLabel("Database username:"), constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_START;
        settingsPanel.add(dbUsernameField, constraint);
        
        //Database password
        constraint.gridx = 0;
        constraint.gridy++;
        constraint.anchor = GridBagConstraints.LINE_END;
        settingsPanel.add(new JLabel("Database password:"), constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_START;
        settingsPanel.add(dbPasswordField, constraint);
        
        //Max rows
        constraint.gridx = 0;
        constraint.gridy++;
        constraint.anchor = GridBagConstraints.LINE_END;
        settingsPanel.add(new JLabel("Records in table:"), constraint);

        constraint.gridx = 1;
        constraint.anchor = GridBagConstraints.LINE_START;
        settingsPanel.add(queryLimitSpinner, constraint);
        
        add(settingsPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        setDialogFieldsContent(settingsFile);
        
        setMinimumSize(new Dimension(600, 600));

        setVisible(true);
        
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settingsFile.setDbURL(dbURLField.getText());
                settingsFile.setDbPort(Integer.toString((Integer) dbPort.getValue()));
                settingsFile.setDbUsername(dbUsernameField.getText());
                settingsFile.setDbPassword(dbPasswordField.getText());
                settingsFile.setQueryLimit(Integer.toString((Integer) queryLimitSpinner.getValue()));
                
                menuBarListener.settingsSaved();
                
                disposeOfDialog();
            }
        });
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                disposeOfDialog();
            }
        });
    }
    
    private void disposeOfDialog() {
        dispose();
        System.gc();
    }
    
    private void setDialogFieldsContent(SettingsFile settingsFile){
        dbURLField.setText(settingsFile.getDbURL());
        dbPort.setValue(Integer.valueOf(settingsFile.getDbPort()));
        dbUsernameField.setText(settingsFile.getDbUsername());
        dbPasswordField.setText(settingsFile.getDbPassword());
        queryLimitSpinner.setValue(Integer.valueOf(settingsFile.getQueryLimit()));
    }
}
