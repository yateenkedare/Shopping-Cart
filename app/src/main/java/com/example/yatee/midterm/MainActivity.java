package com.example.yatee.midterm;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity implements GetJSONDataAsync.IData, CustomRecyclerListAdapter.IData {
    public  static DatabaseDataManager dm;
    RecyclerView mRecyclerView;
    ProgressBar pb;
    public static int TotalOrders = 0;
    public static UUID uid;
    ArrayList<Item> items;
    ArrayList<String> ordersUID;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getTitle()+"") {
            case "Cart":

                final ArrayList<String> arrayList= new ArrayList<>();
                for(int i = 1; i <= TotalOrders; i++){
                    arrayList.add("Order "+i);
                }
                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setTitle("Select an Order")
                        .setItems(arrayList.toArray(new CharSequence[arrayList.size()]), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, Cart.class);
                        intent.putExtra("Order", ordersUID.get(which));
                        startActivity(intent);
                    }
                });
                final AlertDialog alert=builder.create();
                alert.show();

                return true;
            case "History":
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ordersUID = new ArrayList<>();
        uid = UUID.randomUUID();
        ordersUID.add(uid.toString());
        TotalOrders++;
        pb = (ProgressBar) findViewById(R.id.progressBar);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        pb.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.INVISIBLE);

        try{
            new GetJSONDataAsync(this).execute("http://52.90.79.130:8080/MidTerm/get/products");
        }catch (Exception e) {
            Log.d("ExceptionJSON", e.toString());
        }


        dm=new DatabaseDataManager(this);


    }

    @Override
    public void JSONResultValues(String result) {
        items = JSONParser.parseJSON(result);
        pb.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
        Log.d("ITEMS", items.toString());
        displayGridItems(items);
    }

    public void displayGridItems(List<Item> items){

        mRecyclerView.setHasFixedSize(true);
        GridLayoutManager mLayoutManagerGrid = new GridLayoutManager(MainActivity.this, 3);
        mRecyclerView.setLayoutManager(mLayoutManagerGrid);
        CustomRecyclerListAdapter mAdapterGrid = new CustomRecyclerListAdapter(this, R.layout.grid_view, items, this);
        mRecyclerView.setAdapter(mAdapterGrid);

    }

    @Override
    public void ListResultValues(int result) {
        items.get(result).setUid(uid.toString());
     dm.saveItem(items.get(result));
    }
}
