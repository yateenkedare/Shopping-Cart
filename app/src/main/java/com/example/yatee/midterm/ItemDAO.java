package com.example.yatee.midterm;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class ItemDAO {
    private SQLiteDatabase db;

    public ItemDAO(SQLiteDatabase db) {
        this.db = db;
    }

    public long saveItem(Item item){
        ContentValues cv=new ContentValues();
        cv.put(ItemTable.COLUMN_Title, item.getTitle());
        cv.put(ItemTable.COLUMN_MSP, item.getMsrp_price());
        cv.put(ItemTable.COLUMN_SP, item.getSale_price());
        cv.put(ItemTable.COLUMN_URL, item.getImageURL());
        cv.put(ItemTable.COLUMN_UID, item.getUid());

        Log.d("SAVEITEMDAO: ",cv.toString());

        return db.insert(ItemTable.TABLENAME,null,cv);
    }

    public boolean updatedItem(Item item){
        ContentValues cv=new ContentValues();
        cv.put(ItemTable.COLUMN_Title, item.getTitle());
        cv.put(ItemTable.COLUMN_MSP, item.getMsrp_price());
        cv.put(ItemTable.COLUMN_SP, item.getSale_price());
        cv.put(ItemTable.COLUMN_URL, item.getImageURL());
        cv.put(ItemTable.COLUMN_UID, item.getImageURL());

        return db.update(ItemTable.TABLENAME,cv, ItemTable.COLUMN_ID+"=?",new String[]{item.get_id()+""})>0;
    }

    public boolean deleteItem(Item item){
        return db.delete(ItemTable.TABLENAME, ItemTable.COLUMN_ID+"=?",new String[]{item.get_id()+""})>0;
    }

    public Item get(long id){       //gets single item
        Item item =null;
        //TODO - change the query

        Cursor cursor= db.query(true, ItemTable.TABLENAME,new String[]{ItemTable.COLUMN_ID, ItemTable.COLUMN_Title, ItemTable.COLUMN_MSP, ItemTable.COLUMN_SP, ItemTable.COLUMN_URL, ItemTable.COLUMN_UID}, ItemTable.COLUMN_ID+"=?",new String[]{id+""},null,null,null,null,null);
        if(cursor!=null && cursor.moveToFirst()){
            item = buildItemFromCursor(cursor);
            cursor.close();
        }
        Log.d("GETDAO: ","Created");
        if(item != null) return item;
        else return null;
    }
    private Item buildItemFromCursor(Cursor cursor){
        Item item =null;
        if (cursor!=null){
            item =new Item();
            //TODO - set the appropriate item values in proper order
            item.set_id(cursor.getLong(0));
            item.setTitle(cursor.getString(1));
            item.setMsrp_price(cursor.getString(2));
            item.setSale_price(cursor.getString(3));
            item.setImageURL(cursor.getString(4));
            item.setUid(cursor.getString(5));
        }
        Log.d("ITEM PROCESSING: ", item.toString());
        return item;

    }

    public List<Item> getAll(){

        List<Item> items =new ArrayList<Item>();

        Cursor cursor= db.query(ItemTable.TABLENAME,new String[]{ItemTable.COLUMN_ID, ItemTable.COLUMN_Title, ItemTable.COLUMN_MSP, ItemTable.COLUMN_SP, ItemTable.COLUMN_URL, ItemTable.COLUMN_UID},null,null,null,null,null);
        if(cursor!=null && cursor.moveToFirst())
        {
            do {
                Item item = buildItemFromCursor(cursor);
                if(item !=null)
                    items.add(item);
                Log.d("GETALLDAO: ", item.toString());
            }while (cursor.moveToNext());
            if(!cursor.isClosed())
                cursor.close();
        }
        return items;
    }

    public List<Item> getAllUID(String uid){

        List<Item> items =new ArrayList<Item>();

        Cursor cursor= db.query(true, ItemTable.TABLENAME,new String[]{ItemTable.COLUMN_ID, ItemTable.COLUMN_Title, ItemTable.COLUMN_MSP, ItemTable.COLUMN_SP, ItemTable.COLUMN_URL, ItemTable.COLUMN_UID}, ItemTable.COLUMN_UID+"=?",new String[]{uid+""},null,null,null,null,null);
        if(cursor!=null && cursor.moveToFirst())
        {
            do {
                Item item = buildItemFromCursor(cursor);
                if(item !=null)
                    items.add(item);
                Log.d("GETALLDAO: ", item.toString());
            }while (cursor.moveToNext());
            if(!cursor.isClosed())
                cursor.close();
        }
        return items;
    }
}
