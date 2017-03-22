package com.example.yatee.midterm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class Cart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        String uid = getIntent().getStringExtra("Order");
        CustomListAdapter adapter = new CustomListAdapter(this,MainActivity.dm.getAllUID(uid));
        ListView ll = (ListView) findViewById(R.id.listView);
        ll.setAdapter(adapter);
    }
}
