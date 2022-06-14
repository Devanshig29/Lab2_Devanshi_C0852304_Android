package com.example.lab2_devanshi_c0852304_android;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.ArrayList;

public class ProductListActivity extends AppCompatActivity  {

    EditText search_product;
    RecyclerView product_list;
    RecyclerView.LayoutManager layoutManager;
    Button add_prod, btn_search;
    ArrayList<Products> prodlist;
    ProductRecyclerViewAdapter adapter;
    DatabaseAdapter databaseAdapter;
    Context mc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_more);
        findId();

        mc=ProductListActivity.this;
        prodlist=new ArrayList<>();
        databaseAdapter =new DatabaseAdapter(mc);
        Cursor row = databaseAdapter.getAllProducts();
        //row.moveToFirst();
        if (row.moveToFirst()){
            do {
                String column1 = row.getString(0);
                String column2 = row.getString(1);
                String column3 = row.getString(2);
                String column4 = row.getString(3);
                Products data = new Products(Integer.parseInt(column1), column2,column3,Double.parseDouble(column4));
                prodlist.add(data);
                Log.d("PRODLIST:", data.getProduct_name());

            } while(row.moveToNext());
        }
        row.close();


        layoutManager = new LinearLayoutManager(mc, LinearLayoutManager.VERTICAL, false);
        product_list.setLayoutManager(layoutManager);
        adapter = new ProductRecyclerViewAdapter(prodlist,mc);
        product_list.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    @SuppressLint("WrongViewCast")
    private void findId() {
        search_product=findViewById(R.id.et_search);
        product_list=findViewById(R.id.lv_products);
        btn_search=findViewById(R.id.btn_search);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchString = search_product.getText().toString();
                prodlist = new ArrayList<>();
                Cursor cursor = databaseAdapter.findProductByName(searchString);
                if (cursor != null && cursor.moveToFirst()){
                    do {
                        // Passing values
                        String column1 = cursor.getString(0);
                        String column2 = cursor.getString(1);
                        String column3 = cursor.getString(2);
                        String column4 = cursor.getString(3);
                        // Do something Here with values
//                Log.d("DB_DEBUG_SEARCH", "col1: " + column1 + ", col2:" +
//                        column2 + ", col3:" + column3 + ", col4:" + column4);
                        Products data = new Products(Integer.parseInt(column1), column2,column3,Double.parseDouble(column4));
                        prodlist.add(data);
                    } while(cursor.moveToNext());
                }
                adapter.filterList(prodlist);
            }
        });

    }


}
