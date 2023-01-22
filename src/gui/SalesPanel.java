package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class SalesPanel extends JPanel {

    private JTree salesTree;
    private DefaultMutableTreeNode salesRoot;
    private DefaultTreeModel treeModel;

    public SalesPanel() {
        setLayout(new BorderLayout());
        
        treeModel = new DefaultTreeModel(salesRoot);
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

}
