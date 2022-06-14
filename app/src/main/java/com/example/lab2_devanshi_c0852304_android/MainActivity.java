package com.example.lab2_devanshi_c0852304_android;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    TextView prod_name,prod_desc,prod_price;
    Button view_more,add_product;
    DatabaseAdapter databaseAdapter;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findID();

        context=MainActivity.this;
        databaseAdapter =new DatabaseAdapter(context);
        insertdatadb();
        Cursor row = databaseAdapter.getFirstProduct();
        if (row.moveToFirst()){
            do {
                // Passing values
                prod_name.setText(row.getString(1));
                prod_desc.setText(row.getString(2));
                prod_price.setText(row.getString(3));
            } while(row.moveToNext());
        }
        row.close();
        view_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ProductListActivity.class));
            }
        });add_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ProductDetailActivity.class));
            }
        });
    }

    private void insertdatadb() {
        databaseAdapter.addProduct("Chair","Comfortable",32.00);
        databaseAdapter.addProduct("Headphones","Good Quality",10.00);
        databaseAdapter.addProduct("Coffee","Fresh Brew",5.00);
        databaseAdapter.addProduct("Chocolate","Cadbury dairy milk",60.00);
        databaseAdapter.addProduct("Pizza","Delicious",18.00);
        databaseAdapter.addProduct("Pillow","Soft Material",11.00);
        databaseAdapter.addProduct("Orange Juice","Natural orange juice",20.00);
        databaseAdapter.addProduct("Basket","Best Product",12.00);
        databaseAdapter.addProduct("Bag","Good colour",25.20);
    }
    private void findID() {

        add_product=findViewById(R.id.add_product);
        prod_name=findViewById(R.id.tv_name);
        prod_desc=findViewById(R.id.tv_description);
        prod_price=findViewById(R.id.tv_price);
        view_more=findViewById(R.id.btn_view_more);
    }
}