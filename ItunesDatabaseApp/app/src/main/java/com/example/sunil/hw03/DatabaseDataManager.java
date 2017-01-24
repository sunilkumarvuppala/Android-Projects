package com.example.sunil.hw03;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by Sunil on 16-06-2016.
 */
public class DatabaseDataManager {

   private Context context;
    private DatabaseOpenHelper databaseOpenHelper;
    private SQLiteDatabase sqLiteDatabase;
    private AppDAO appDAO;

    public DatabaseDataManager(Context context) {
        this.context = context;
        databaseOpenHelper = new DatabaseOpenHelper(this.context);
        sqLiteDatabase = databaseOpenHelper.getWritableDatabase();
        appDAO = new AppDAO(sqLiteDatabase);
    }

    public void close(){
        if (sqLiteDatabase != null){
            sqLiteDatabase.close();
        }
    }

    public AppDAO getappDAO(){
        return this.appDAO;
    }

    public long saveApp(App app){
        return this.appDAO.save(app);
    }

    public boolean updateApp(App app){
        return this.appDAO.update(app);
    }

    public boolean deleteApp(App app){
        return this.appDAO.delete(app);
    }

    public App getApp (String id){
        return this.appDAO.get(id);
    }

    public List<App> getAllApps(){
        return this.appDAO.getAll();
    }

}
