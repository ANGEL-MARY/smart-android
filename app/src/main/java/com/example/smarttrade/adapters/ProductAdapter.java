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

import com.bumptech.glide.Glide;
import com.example.smarttrade.R;
import com.example.smarttrade.models.Product;
import com.example.smarttrade.retrofit.AppClient;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{

    private Context mContext;
    private List<Product> products;
    private ProjectAdapterInterface adaperInterfce;

    public  ProductAdapter(Context mContext, List<Product> products, ProjectAdapterInterface adaperInterfce){
        this.mContext =  mContext;
        this.products =  products;
        this.adaperInterfce = adaperInterfce;
    }

    public interface ProjectAdapterInterface{
        public void onViewDetailsClicked();
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
        holder.name.setText(aProduct.getItem().getName());
        holder.priceName.setText(aProduct.getPrice()+" Rs");
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adaperInterfce.onViewDetailsClicked();
            }
        });
        Glide.with(mContext).load(AppClient.MASTEERURL+ aProduct.getItem().getImageUrl()).placeholder(R.drawable.shop_placeholder).into(holder.productImage);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        public TextView name, priceName;
        MaterialButton button;
        ImageView productImage;

        public ProductViewHolder(View view) {
            super(view);
            productImage = view.findViewById(R.id.productImage);
            priceName =  view.findViewById(R.id.priceValue);
            name = view.findViewById(R.id.productName);
            button = view.findViewById(R.id.view_details);
        }
    }
}
