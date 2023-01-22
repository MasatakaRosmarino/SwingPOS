package gui.dialog;

import java.awt.Dimension;

import javax.swing.JDialog;

public abstract class BaseDialog extends JDialog {

	public BaseDialog() {
		super();
		setMinimumSize(new Dimension(480, 640));
	}

	
}
