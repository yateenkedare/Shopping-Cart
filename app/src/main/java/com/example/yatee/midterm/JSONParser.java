package com.example.yatee.midterm;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yatee on 3/20/2017.
 */

public class JSONParser {
    public static ArrayList<Item> parseJSON(String result){
        ArrayList<Item> itemArrayList =new ArrayList<Item>();
        try {
            JSONArray ItemsRootArray=new JSONArray(result);

            for(int i=0;i<ItemsRootArray.length();i++){
                JSONObject itemObject = ItemsRootArray.getJSONObject(i);
                Item item=new Item();
                item.setTitle(itemObject.getString("name"));
                item.setImageURL(itemObject.getJSONObject("image_urls").getJSONArray("300x400").getJSONObject(0).getString("url"));
                item.setMsrp_price(itemObject.getJSONArray("skus").getJSONObject(0).getString("msrp_price"));
                item.setSale_price(itemObject.getJSONArray("skus").getJSONObject(0).getString("sale_price"));

                itemArrayList.add(item);
//                Log.d("items",item.toString());
            }
            return itemArrayList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

}
