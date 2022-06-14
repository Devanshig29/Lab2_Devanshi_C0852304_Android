package com.example.lab2_devanshi_c0852304_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ProductDetailActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnAddToDb;
    EditText etProductName, etProductDescription, etProductPrice;
    DatabaseAdapter databaseAdapter = new DatabaseAdapter(this);
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_product);
        findId();
        toolbar.setTitle("Add Product");
        setSupportActionBar(toolbar);

    }
    private void findId(){

        etProductName = findViewById(R.id.et_name);
        etProductDescription = findViewById(R.id.et_Description);
        etProductPrice = findViewById(R.id.et_price);
        btnAddToDb = findViewById(R.id.btn_add_product);
        btnAddToDb.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_add_product){
            databaseAdapter.addProduct(etProductName.getText().toString(),
                    etProductDescription.getText().toString(),
                    Double.parseDouble(etProductPrice.getText().toString()));
            Toast.makeText(ProductDetailActivity.this,"Product Added",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(ProductDetailActivity.this, ProductListActivity.class));
        }
    }
}