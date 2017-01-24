package com.example.sunil.hw03;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Sunil on 16-06-2016.
 */
public class AppsTable {

    static final String TABLE_NAME ="AppList";
    static final String COLUMN_ID ="id";
    static final String COLUMN_APPNAME ="appname";
    static final String COLUMN_DEVNAME ="devname";
    static final String COLUMN_DATE ="releaseDate";
    static final String COLUMN_PRICE ="price";
    static final String COLUMN_CATEGORY ="category";
    static final String COLUMN_IMGURL ="imgURL";

    static public void onCreate(SQLiteDatabase db){
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE "+TABLE_NAME + "(");
        sb.append(COLUMN_ID +" integer primary key, ");
        sb.append(COLUMN_APPNAME + " text not null, ");
        sb.append(COLUMN_DEVNAME + " text not null, ");
        sb.append(COLUMN_DATE + " text not null, ");
        sb.append(COLUMN_PRICE + " text not null, ");
        sb.append(COLUMN_CATEGORY + " text not null, ");
        sb.append(COLUMN_IMGURL + " text not null);");

        try{
            db.execSQL(sb.toString());
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    static public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        AppsTable.onCreate(
                db);
    }

}
