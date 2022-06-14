package com.example.lab2_devanshi_c0852304_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ProductEdit extends AppCompatActivity implements View.OnClickListener {

    Button btnAddToDb;
    EditText etProductName, etProductDescription, etProductPrice;
    DatabaseAdapter databaseAdapter = new DatabaseAdapter(this);
    String prod_id,prod_name,prod_desc,prod_price;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_product);
        findId();
        Intent i=getIntent();
        prod_id = i.getStringExtra("prod_id");
        prod_name=i.getStringExtra("prod_name");
        prod_desc = i.getStringExtra("prod_desc");
        prod_price= i.getStringExtra("prod_price");
        etProductName.setText(prod_name);
        etProductDescription.setText(prod_desc);
        etProductPrice.setText(prod_price);
    }
    private void findId(){
        etProductName = findViewById(R.id.et_name);
        etProductDescription = findViewById(R.id.et_Description);
        tv = findViewById(R.id.tv);
        etProductPrice = findViewById(R.id.et_price);
        btnAddToDb = findViewById(R.id.btn_add_product);
        btnAddToDb.setText("Edit Product");
        tv.setText("Edit Product");
        btnAddToDb.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_add_product){
            databaseAdapter.updateProduct(Integer.parseInt(prod_id),etProductName.getText().toString(),
                    etProductDescription.getText().toString(),
                    Double.parseDouble(etProductPrice.getText().toString()));
            startActivity(new Intent(ProductEdit.this, ProductListActivity.class));

        }
    }
}
