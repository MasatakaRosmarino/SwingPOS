package gui.guiform;

public class CategoryGUIForm extends GUIForm {

    private String categoryName;

    public CategoryGUIForm(String categoryId, String category) {
        super();
        this.formIdField = categoryId;
        this.categoryName = category;
    }

    public String getCategoryName() {
        return categoryName;
    }

    @Override
    public String toString() {
        return "CategoryGUIForm [categoryId=" + formIdField + ", category=" + categoryName + "]";
    }

}
