package com.example.smarttrade.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smarttrade.R;
import com.example.smarttrade.models.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{

    private Context mContext;
    private List<Product> products;


    public  ProductAdapter(Context mContext, List<Product> products){
        this.mContext =  mContext;
        this.products =  products;
    }


    @NonNull
    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_layout, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product aProduct = products.get(position);
        holder.productName.setText(aProduct.getName());
        holder.priceName.setText("("+aProduct.getPrice()+")");
        Log.d("Adapter", aProduct.getName());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        public TextView productName, priceName;

        public ProductViewHolder(View view) {
            super(view);
            priceName =  view.findViewById(R.id.priceValue);
            productName = view.findViewById(R.id.productName);
        }
    }
}
