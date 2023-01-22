package gui.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HelpDialog extends JDialog {

	private final JLabel messageTitle;
	private final JPanel messagePanel;
	private final JLabel messageLabel;

	public HelpDialog() {
		super();
		setTitle("About...");
		messageTitle = new JLabel();
		messagePanel = new JPanel();
		messageLabel = new JLabel();
		
		messagePanel.setLayout(new BorderLayout());
		
		messageTitle.setText("Thrif-T-rill Point Of Sale");
		messageLabel.setText("Software version 1.0.0");
		
		messagePanel.add(messageLabel);
		
		add(messageTitle, BorderLayout.NORTH);
		add(messagePanel, BorderLayout.CENTER);
		
		setMinimumSize(new Dimension(240, 120));
		
		pack();
		setLocationRelativeTo(null);
		
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {
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
	
}
