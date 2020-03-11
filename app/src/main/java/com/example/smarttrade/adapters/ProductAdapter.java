package com.example.smarttrade.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    private boolean hasToShowStockDetails =  false;

    public  ProductAdapter(Context mContext, List<Product> products, ProjectAdapterInterface adaperInterfce){
        this.mContext =  mContext;
        this.products =  products;
        this.adaperInterfce = adaperInterfce;
    }

    public void setHasToShowStockDetails(boolean hasToShowStockDetails) {
        this.hasToShowStockDetails = hasToShowStockDetails;
    }

    public interface ProjectAdapterInterface{
        public void onViewDetailsClicked(String productId);
        public void onEditViewClicked(String productId);
        public void onDeleteViewClicked(String productId);
    }

    @NonNull
    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_layout, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        final Product aProduct = products.get(position);
        holder.name.setText(aProduct.getItem().getName());
        holder.priceName.setText(aProduct.getPrice()+" Rs");
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adaperInterfce.onViewDetailsClicked(aProduct.getId());
            }
        });
        Glide.with(mContext).load(AppClient.MASTEERURL+ aProduct.getItem().getImageUrl()).placeholder(R.drawable.shop_placeholder).into(holder.productImage);


        if(hasToShowStockDetails){
            holder.stockDetails.setVisibility(View.VISIBLE);
            holder.editLayout.setVisibility(View.VISIBLE);
            holder.button.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        public TextView name, priceName, stockDetails;
        MaterialButton button, editButton, removeButton;
        ImageView productImage;
        LinearLayout editLayout;

        public ProductViewHolder(View view) {
            super(view);
            productImage = view.findViewById(R.id.productImage);
            priceName =  view.findViewById(R.id.priceValue);
            stockDetails = view.findViewById(R.id.stockDetails);
            name = view.findViewById(R.id.productName);
            button = view.findViewById(R.id.view_details);
            editButton = view.findViewById(R.id.edit_details);
            removeButton =  view.findViewById(R.id.remove_details);
            editLayout = view.findViewById(R.id.edit_layout);
        }
    }
}
