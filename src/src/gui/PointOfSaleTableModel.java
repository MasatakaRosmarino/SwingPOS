package gui;

import javax.swing.table.DefaultTableModel;

public class PointOfSaleTableModel extends DefaultTableModel {

    public PointOfSaleTableModel() {
        super();
    }

    public PointOfSaleTableModel(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
    }

    //making cells uneditable by overriding this method
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
