package com.shzlabs.androidtest2.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.shzlabs.androidtest2.Data.model.DataModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shaz on 25/7/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "androidtest.db";

    public static final String SQL_CREATE_MAIN_DATA_TABLE =
            "CREATE TABLE " + DataContract.Data.TABLE_NAME + "(" +
                    DataContract.Data._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DataContract.Data.COLUMN_NAME_DATA_TYPE + " TEXT," +
                    DataContract.Data.COLUMN_NAME_DATA_VALUE + " TEXT )";

    public static final String SQL_DELETE_MAIN_DATA_TABLE =
            "DROP TABLE IF EXISTS" + DataContract.Data.TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_MAIN_DATA_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_MAIN_DATA_TABLE);
    }

    public long addNewData(String dataType, String dataValue) {
        ContentValues values = new ContentValues();
        values.put(DataContract.Data.COLUMN_NAME_DATA_TYPE, dataType);
        values.put(DataContract.Data.COLUMN_NAME_DATA_VALUE, dataValue);
        return getWritableDatabase().insert(DataContract.Data.TABLE_NAME, null, values);
    }

    public void getData() {

        List<DataModel> items = new ArrayList<>();

        String query = "SELECT * FROM " + DataContract.Data.TABLE_NAME;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cr = db.rawQuery(query, null);
        if(cr.moveToFirst()){
            int id = cr.getInt(0);
            String dataType = cr.getString(cr.getColumnIndex(DataContract.Data.COLUMN_NAME_DATA_TYPE));
            String dataValue = cr.getString(cr.getColumnIndex(DataContract.Data.COLUMN_NAME_DATA_VALUE));

            DataModel item = new DataModel(id, dataType, dataValue);

            items.add(item);
        }
        cr.close();
    }

}
