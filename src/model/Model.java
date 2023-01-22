package model;

public abstract class Model {

    protected int modelId;
    protected String tableName;

    public int getModelId() {
        return modelId;
    }

    public String getTableName() {
        return getClass().getName().toLowerCase();
    }

}
