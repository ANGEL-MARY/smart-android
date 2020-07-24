package com.example.smarttrade.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.smarttrade.R;
import com.example.smarttrade.models.Cart;
import com.example.smarttrade.models.Product;
import com.example.smarttrade.retrofit.AppClient;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ProductViewHolder>{

    private Context mContext;
    private List<Cart> carts;
    private CartAdapter.CartAdapterInterface adaperInterfce;

    public  CartAdapter(Context mContext, List<Cart> carts, CartAdapter.CartAdapterInterface adaperInterfce){
        this.mContext =  mContext;
        this.carts =  carts;
        this.adaperInterfce = adaperInterfce;
    }

    public interface CartAdapterInterface{
        public void onViewDetailsClicked(String productId);
        public void onRemoveClicked(String cartId);
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        final Product aProduct = carts.get(position).getProduct();
        final Cart cart = carts.get(position);
        holder.name.setText(aProduct.getItem().getName());
        holder.priceName.setText(cart.getCurrent_price()+" Rs");
        holder.viewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adaperInterfce.onViewDetailsClicked(aProduct.getId());
            }
        });

        holder.removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adaperInterfce.onRemoveClicked(cart.getId());
            }
        });
        Glide.with(mContext).load(AppClient.MASTEERURL+ aProduct.getItem().getImageUrl()).placeholder(R.drawable.shop_placeholder).into(holder.productImage);
    }


    @Override
    public int getItemCount() {
        return carts.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        public TextView name, priceName;
        MaterialButton viewDetails, removeItem;
        ImageView productImage;

        public ProductViewHolder(View view) {
            super(view);
            productImage = view.findViewById(R.id.productImage);
            priceName =  view.findViewById(R.id.priceValue);
            name = view.findViewById(R.id.productName);
            viewDetails = view.findViewById(R.id.view_details);
            removeItem = view.findViewById(R.id.remove_cart);

        }
    }
}