package gui;

import gui.dialog.HelpDialog;
import gui.dialog.SettingsDialog;
import gui.guilistener.MenuBarListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileSystemView;
import utilities.SettingsFile;
import utilities.SettingsManager;

public class MainMenu extends JMenuBar {

    private final JMenu fileMenu;
    private final JMenu settingsMenu;
    private final JMenu helpMenu;
    private final JMenuItem exitItem;
    private final JMenuItem systemItem;
    private final JMenuItem infoItem;

    private SettingsDialog settingsDialog;

    private MenuBarListener menuBarListener;
    private SettingsFile settingsFile;

    public MainMenu() {
        helpMenu = new JMenu("Help");
        settingsMenu = new JMenu("Settings");
        fileMenu = new JMenu("File");

        exitItem = new JMenuItem("Exit");
        systemItem = new JMenuItem("System");
        infoItem = new JMenuItem("About...");
        
        settingsMenu.add(systemItem);
        fileMenu.add(exitItem);
        helpMenu.add(infoItem);

        add(fileMenu);
        add(settingsMenu);
        add(helpMenu);

        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuBarListener.systemExited();
            }
        });

        systemItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (settingsDialog == null || !settingsDialog.isShowing()){
                    SettingsManager settingsManager = new SettingsManager(FileSystemView.getFileSystemView().getDefaultDirectory().getPath());
                    
                    settingsFile = (SettingsFile) settingsManager.getSettingsFromFile();
                                        
                    settingsDialog = new SettingsDialog(settingsFile, menuBarListener);
                }
            }
        });
        
        infoItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new HelpDialog();
			}
		});
    }
    
    public SettingsFile getSettingsFile() {
        return settingsFile;
    }

    /* Setting listeners*/
    public void setMenuBarListener(MenuBarListener menuBarListener) {
        this.menuBarListener = menuBarListener;
    }
}