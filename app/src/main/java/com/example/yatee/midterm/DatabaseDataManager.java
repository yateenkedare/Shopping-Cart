package com.example.yatee.midterm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.List;

public class DatabaseDataManager {
    private Context context;
    private DatabaseOpenHelper openHelper;
    private SQLiteDatabase db;
    private ItemDAO itemDAO;

    public DatabaseDataManager(Context context){
        this.context=context;
        openHelper=new DatabaseOpenHelper(this.context);
        db=openHelper.getWritableDatabase();
        itemDAO =new ItemDAO(db);
        Log.d("DATAMANAGER: ","Created");

    }
    public void close(){
        if(db!=null){
            db.close();
        }
    }
    public ItemDAO getItemDAO(){
        return this.itemDAO;
    }

    public long saveItem(Item item) {
        Log.d("SAVEITEM: ","Created");
        return this.itemDAO.saveItem(item);
    }

    public boolean updatedItem(Item item) {
        return itemDAO.updatedItem(item);
    }

    public boolean deleteItem(Item item) {
        return this.itemDAO.deleteItem(item);
    }

    public List<Item> getAll() {
        return this.itemDAO.getAll();
    }

    public List<Item> getAllUID(String uid) {
        return this.itemDAO.getAllUID(uid);
    }

    public Item get(long id) {
        return this.itemDAO.get(id);
    }


    }
