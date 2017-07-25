package com.shzlabs.androidtest2.Data.model;

/**
 * Created by shaz on 25/7/17.
 */

public class DataModel {
    int id;
    String dataType;
    String dataValue;

    public DataModel(int id, String dataType, String dataValue) {
        this.id = id;
        this.dataType = dataType;
        this.dataValue = dataValue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getDataValue() {
        return dataValue;
    }

    public void setDataValue(String dataValue) {
        this.dataValue = dataValue;
    }
}
