package com.example.lab2_devanshi_c0852304_android;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class ProductDetailAdapter extends RecyclerView.Adapter<ProductDetailAdapter.ProductDetailHolder> {

    private Context mContext;
    ArrayList<Products> products;
    DatabaseAdapter databaseAdapter;


    public ProductDetailAdapter(Context mContext,int resource, ArrayList<Products> products) {
        this.mContext = mContext;
        this.products = products;
    }

    @NonNull
    @Override
    public ProductDetailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.detail_view,parent,false);
        return new ProductDetailHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductDetailHolder holder, @SuppressLint("RecyclerView") int position) {

        Log.d("PRODLIST222:", products.get(position).getProduct_name());
        databaseAdapter =new DatabaseAdapter(mContext);
       holder.prod_name.setText(products.get(position).getProduct_name());
       holder.prod_desc.setText(products.get(position).getProduct_description());
       holder.prod_price.setText(String.valueOf(products.get(position).getProduct_price()));
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(mContext, ProductDetailActivity.class);
                i.putExtra("prod_id",products.get(position).getProduct_id());
                i.putExtra("prod_name",products.get(position).getProduct_name());
                i.putExtra("prod_desc",products.get(position).getProduct_description());
                i.putExtra("prod_price",products.get(position).getProduct_price());
                mContext.startActivity(i);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseAdapter.deleteProduct(products.get(position).getProduct_id());
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ProductDetailHolder extends RecyclerView.ViewHolder {
        TextView prod_name,prod_desc,prod_price;
        Button edit,delete;
        public ProductDetailHolder(View itemView) {
            super(itemView);
            prod_name = itemView.findViewById(R.id.et_name);
            prod_desc = itemView.findViewById(R.id.et_Description);
            prod_price = itemView.findViewById(R.id.et_price);
            edit = itemView.findViewById(R.id.btn_edit);
            delete = itemView.findViewById(R.id.btn_delete);
        }
    }
}
