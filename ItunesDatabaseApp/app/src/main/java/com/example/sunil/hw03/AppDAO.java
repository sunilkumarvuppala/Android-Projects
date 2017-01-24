package com.example.sunil.hw03;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sunil on 16-06-2016.
 */
public class AppDAO {
    private SQLiteDatabase db;

    public AppDAO(SQLiteDatabase db) {
        this.db = db;
    }

    public long save(App app){
        ContentValues values = new ContentValues();
        values.put(AppsTable.COLUMN_ID,app.getId());
        values.put(AppsTable.COLUMN_APPNAME,app.getAppTitle());
        values.put(AppsTable.COLUMN_DEVNAME,app.getDevName());
        values.put(AppsTable.COLUMN_DATE,app.getReleaseDate());
        values.put(AppsTable.COLUMN_PRICE,app.getPrice());
        values.put(AppsTable.COLUMN_CATEGORY,app.getCategory());
        values.put(AppsTable.COLUMN_IMGURL,app.getLargeImage());

      //  Log.d("insert",db.insert(AppsTable.TABLE_NAME,null,values)+"");

        return db.insert(AppsTable.TABLE_NAME,null,values);

      //  return 0;//return id saved
    }

    public boolean update(App app){
        ContentValues values = new ContentValues();
        values.put(AppsTable.COLUMN_ID,app.getId());
        values.put(AppsTable.COLUMN_APPNAME,app.getAppTitle());
        values.put(AppsTable.COLUMN_DEVNAME,app.getDevName());
        values.put(AppsTable.COLUMN_DATE,app.getReleaseDate());
        values.put(AppsTable.COLUMN_PRICE,app.getPrice());
        values.put(AppsTable.COLUMN_CATEGORY,app.getCategory());
        values.put(AppsTable.COLUMN_IMGURL,app.getLargeImage());

        return db.update(AppsTable.TABLE_NAME,values, AppsTable.COLUMN_ID + "=?", new String[]{app.getId()+""}) > 0; //? is replaced with id from String array values beside ?

     //   return false;//if updated sends true
    }

    public boolean delete(App app){

        return db.delete(AppsTable.TABLE_NAME, AppsTable.COLUMN_ID + "=?",new String[]{app.getId()+""}) > 0;
        //return false;//if updated sends true
    }

    public App get(String id){
        App app = null;

        Cursor cursor = db.query(true, AppsTable.TABLE_NAME, new String[]{AppsTable.COLUMN_ID, AppsTable.COLUMN_APPNAME,
                AppsTable.COLUMN_DEVNAME, AppsTable.COLUMN_DATE, AppsTable.COLUMN_PRICE, AppsTable.COLUMN_CATEGORY, AppsTable.COLUMN_IMGURL},
                AppsTable.COLUMN_ID + "=?", new String[]{id+""},null, null,null,null,null);

        //true,tablename, cloumns in strings, where, groupby, having, orderby, limit, cancellation signal

        //if query is not happy change sdk from 8 to 16 in <user sdk> minSdkVersion in manifest
        if (cursor != null && cursor.moveToFirst()){
                app = buildNoteFromCursor(cursor);
            if(!cursor.isClosed()){
                cursor.close();
            }
        }


        return app;  //returns note with id
    }

    public List<App> getAll(){
        List<App> notes = new ArrayList<>();

        Cursor cursor=db.query(AppsTable.TABLE_NAME,new String[]{AppsTable.COLUMN_ID, AppsTable.COLUMN_APPNAME, AppsTable.COLUMN_DEVNAME,
                               AppsTable.COLUMN_DATE, AppsTable.COLUMN_PRICE, AppsTable.COLUMN_CATEGORY, AppsTable.COLUMN_IMGURL},null,null,null,null,null );
        if (cursor != null && cursor.moveToFirst()){
            do{
                App app = buildNoteFromCursor(cursor);
                if(cursor != null){
                    notes.add(app);
                }
            }while(cursor.moveToNext());
            if(!cursor.isClosed()){
                cursor.close();
            }
        }
        return notes;
    }

    public App buildNoteFromCursor(Cursor cursor){

        App app = null;
        if(cursor != null){

            app = new App();
            app.setId(cursor.getString(0));
            app.setAppTitle(cursor.getString(1));
            app.setDevName(cursor.getString(2));
            app.setReleaseDate(cursor.getString(6));
            app.setPrice(cursor.getString(4));
            app.setCategory(cursor.getString(3));
            app.setLargeImage(cursor.getString(5));
        }
        return app;

    }
}
