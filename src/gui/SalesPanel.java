package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import utilities.Utilities;

public class SalesPanel extends JPanel {

    private JTree salesTree;
    private DefaultMutableTreeNode salesRoot;
//    private DefaultTreeModel treeModel;
    
    private JPanel invoicePanel;
    private GridBagConstraints constraint;
    private File folder;
    private File[] listOfFiles;
	private JButton[] viewButton;
	private String folderPath;
	private String[] fileName;
	
	private JPanel fillingPanel;

    public SalesPanel() {
        setLayout(new BorderLayout());
        
//        treeModel = new DefaultTreeModel(salesRoot);
        
        invoicePanel = new JPanel();
        constraint = new GridBagConstraints();
		
        invoicePanel.setLayout(new GridBagLayout());
        invoicePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        
        fillingPanel = new JPanel();
        
//        folderPath = Utilities.getMainFolderPath() + "\\Customers_Invoices";
//        folderPath = FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + "\\Point_Of_Sale\\Customers_Invoices";
//        folderPath = Utilities.getCustomerInvoicePath();
        folderPath = Utilities.getFolderPath(Utilities.CUSTOMER_INVOICE_FOLDER_PATH);
        
//        folder = new File(folderPath);
//        
//        listOfFiles = folder.listFiles();
//        
//        if(listOfFiles != null && listOfFiles.length >= 0){
//        	fileName = new String[listOfFiles.length];
//            viewButton = new JButton[listOfFiles.length];
////            populateInvoiceList();
//        }
        
    }
    
    public void populateSalesTree(Map<String, List<String>> salesInfoMap) {
        salesRoot = new DefaultMutableTreeNode("YEAR");
        
        for(String purchaseDate : salesInfoMap.keySet()){
            DefaultMutableTreeNode firstTier = new DefaultMutableTreeNode(purchaseDate);
            salesRoot.add(firstTier);
            
            List<String> salesList = salesInfoMap.get(purchaseDate);
            
            for(String sale : salesList){
                DefaultMutableTreeNode secondTier = new DefaultMutableTreeNode(sale);
                firstTier.add(secondTier);
            }
            
        }

        salesTree = new JTree(salesRoot);
        salesTree.setCellRenderer(new SalesCellRenderer());
        salesTree.setOpaque(false);
        setBackground(Color.white);
        
        add(salesTree, BorderLayout.CENTER);
    }
    
    public void populateInvoiceList(String path) {
      folder = new File(path);
      
      listOfFiles = folder.listFiles();
      
      if(listOfFiles != null && listOfFiles.length >= 0){
      	fileName = new String[listOfFiles.length];
          viewButton = new JButton[listOfFiles.length];
      }
    	constraint.weightx = 1;
        constraint.weighty = 0.1;
        constraint.insets = new Insets(0, 5, 0, 5);
        
        //test
        int yPos = 0;
        //
        for (int i = 0; i < listOfFiles.length; i++) {
        	if (listOfFiles[i].isFile()) {
        		fileName[i] = listOfFiles[i].getName();
        		String selectedFile = fileName[i];
        		
        		constraint.gridx = 0;
                constraint.gridy = i;
                constraint.anchor = GridBagConstraints.LINE_START;
                invoicePanel.add(new JLabel(listOfFiles[i].getName()), constraint);

                viewButton[i] = new JButton("View");
                
                viewButton[i].setMargin(new Insets(1, 1, 1, 1));
                
                constraint.gridx = 1;
                constraint.anchor = GridBagConstraints.LINE_END;
                invoicePanel.add(viewButton[i], constraint);
                
                viewButton[i].addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							Runtime.getRuntime().exec("explorer.exe /select, " + folderPath + "\\" + selectedFile);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						
					}
				});
                
                //test
                yPos = i;
                //
        	}
        	
        }
        
        //test pushing up gridbaglayout components must set a variable that sets the gridY components for this last element added to the row.
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.anchor = GridBagConstraints.NORTHWEST;
        constraint.weightx = 1;
        constraint.weighty = 1;
        
        yPos++;
        constraint.gridy = yPos;
        invoicePanel.remove(fillingPanel);
        invoicePanel.add(fillingPanel, constraint);
        //
        
        add(invoicePanel, BorderLayout.EAST);
    }
    
//    public void populateInvoiceList() {
//    	constraint.weightx = 1;
//        constraint.weighty = 0.1;
//        constraint.insets = new Insets(0, 5, 0, 5);
//        
//        for (int i = 0; i < listOfFiles.length; i++) {
//        	if (listOfFiles[i].isFile()) {
//        		fileName[i] = listOfFiles[i].getName();
//        		String selectedFile = fileName[i];
//        		
//        		constraint.gridx = 0;
//                constraint.gridy = i;
//                constraint.anchor = GridBagConstraints.LINE_START;
//                invoicePanel.add(new JLabel(listOfFiles[i].getName()), constraint);
//
//                viewButton[i] = new JButton("View");
//                
//                viewButton[i].setMargin(new Insets(1, 1, 1, 1));
//                
//                constraint.gridx = 1;
//                constraint.anchor = GridBagConstraints.LINE_END;
//                invoicePanel.add(viewButton[i], constraint);
//                
//                viewButton[i].addActionListener(new ActionListener() {
//					
//					@Override
//					public void actionPerformed(ActionEvent e) {
//						try {
//							Runtime.getRuntime().exec("explorer.exe /select, " + folderPath + "\\" + selectedFile);
//						} catch (IOException e1) {
//							e1.printStackTrace();
//						}
//						
//					}
//				});
//        	}
//        }
//        
//        add(invoicePanel, BorderLayout.EAST);
//    }

}
