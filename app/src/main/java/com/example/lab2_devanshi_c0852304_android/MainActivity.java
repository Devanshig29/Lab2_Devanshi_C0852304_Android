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
    Button view_more;
    DatabaseAdapter databaseAdapter;
    Context context;
    Toolbar toolbar;
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
        });
    }

    private void insertdatadb() {
        databaseAdapter.addProduct("Apple","Apple bulk",15.00);
        databaseAdapter.addProduct("Milk","Homo Milk",01.00);
        databaseAdapter.addProduct("Banana","Fruit Banana bulk",10.00);
        databaseAdapter.addProduct("Chocolate","Cadbury dairy milk",60.00);
        databaseAdapter.addProduct("Chocolate Muffin","Chocolate Muffin",70.00);
        databaseAdapter.addProduct("Kitkat","kitkat chocolate mini pack",90.00);
        databaseAdapter.addProduct("Orange Juice","Natural orange juice",20.00);
        databaseAdapter.addProduct("Apple Juice","Natural apple juice",15.00);
        databaseAdapter.addProduct("Coke","Original coke",70.20);
    }

    private void findID() {

        prod_name=findViewById(R.id.et_name);
        prod_desc=findViewById(R.id.et_Description);
        prod_price=findViewById(R.id.et_price);
        view_more=findViewById(R.id.btn_view_more);
    }
}