package gui.guilistener;

import gui.guiform.ProductGUIForm;

public interface ProductGUIListener extends GUIListener {

    void productsTableDisplayed();
    
    void productGUIFormObjectSet();
    
    void guiTableRowSaved(ProductGUIForm productForm);
    
    public void productFiltered(String filter, int foreignKey);
    
    public void productPanelReset();
    
    void productAddedToCart(ProductGUIForm productForm);
    
    void productRemovedFromCart(int index);
    
    void cartEmptied();

    void productPanelEnabled(boolean enabled);
    
}
