package com.shzlabs.androidtest2.Data;

import android.provider.BaseColumns;

/**
 * Created by shaz on 25/7/17.
 */

public class DataContract {

    public static abstract class Data implements BaseColumns{
        public static final String TABLE_NAME = "main_data";
        public static final String COLUMN_NAME_DATA_TYPE = "data_type";
        public static final String COLUMN_NAME_DATA_VALUE = "data_value";
    }
}
