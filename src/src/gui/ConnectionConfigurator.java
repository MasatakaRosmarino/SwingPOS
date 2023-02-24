package gui;

import database.DataBaseManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.filechooser.FileSystemView;
import utilities.SettingsFile;
import utilities.SettingsManager;
import utilities.Utilities;

public class ConnectionConfigurator extends JFrame {
    
    private final JPanel settingsPanel;
    private final JTextField dbURLField;
    private final JSpinner dbPort;
    private final SpinnerNumberModel dbPortModel;
    private final JTextField dbUsernameField;
    private final JTextField dbPasswordField;
    
    private final JPanel buttonPanel;
    private final JButton saveButton;
    
    private final GridBagConstraints constraint;
    
    public ConnectionConfigurator(){
        setTitle("Connection configurator");
        
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
        
        add(settingsPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
                
        setMinimumSize(new Dimension(600, 500));
        
        setAlwaysOnTop(true);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
        
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Saving settings...");
                
//                SettingsManager settingsManager = new SettingsManager(FileSystemView.getFileSystemView().getDefaultDirectory().getPath());                
//                SettingsManager settingsManager = new SettingsManager(Utilities.getMainFolderPath()); 
                SettingsManager settingsManager = new SettingsManager(Utilities.getFolderPath(Utilities.MAIN_FOLDER_PATH)); 

                SettingsFile settingsFile = new SettingsFile(dbURLField.getText(), Integer.toString((Integer) dbPort.getValue()), dbUsernameField.getText(), dbPasswordField.getText(), "20");

                settingsManager.writeSettingsToFile(settingsFile);

                settingsFile = settingsManager.getSettingsFromFile();

                if(settingsFile.getDbURL().equals(dbURLField.getText()) && settingsFile.getDbPort().equals(Integer.toString((Integer) dbPort.getValue())) && settingsFile.getDbUsername().equals(dbUsernameField.getText()) && settingsFile.getDbPassword().equals(dbPasswordField.getText())){
                    Utilities.notifyOfSettingsChange();
                    System.exit(0);
                }
            }
        });
        
    }
}
