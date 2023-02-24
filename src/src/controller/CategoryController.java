package controller;

import gui.guiform.CategoryGUIForm;
import java.util.ArrayList;
import java.util.List;

import database.DataBaseManager;
import model.ProductCategory;

public class CategoryController extends BaseController {
    
    private DataBaseManager database;
    

    public CategoryController(DataBaseManager database) {
		this.database = database;
	}


	@Override
    public List<CategoryGUIForm> fetchObjectsListFromDatabase() {
        List<CategoryGUIForm> categoryGUIFormsList = new ArrayList<>();
        List<ProductCategory> categoriesList = database.fetchCategoriesListFromDatabase();
        
        for(ProductCategory productCategory : categoriesList) {
        	CategoryGUIForm categoryGUIForm = new CategoryGUIForm(Integer.toString(productCategory.getModelId()), productCategory.getCategoryName());
        	
        	categoryGUIFormsList.add(categoryGUIForm);
        }
        
        return categoryGUIFormsList;
    }

}
