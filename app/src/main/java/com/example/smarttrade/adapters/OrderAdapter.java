package com.example.smarttrade.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.smarttrade.R;
import com.example.smarttrade.models.Order;
import com.example.smarttrade.models.Product;
import com.example.smarttrade.retrofit.AppClient;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class OrderAdapter extends  RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private Context context;
    private List<Order> orders;
    private boolean isSeller;
    private OrderAdapterInterface adapterInterface;

    public  OrderAdapter(Context context, boolean isSeller, List<Order> orders, OrderAdapterInterface adapterInterface){
        this.context = context;
        this.isSeller=isSeller;
        this.orders = orders;
        this.adapterInterface =  adapterInterface;
    }


    public  interface  OrderAdapterInterface {
        public void onRemoveClickListener(Order order);
        public void onViewClickListener(Order order);
        public void onAcceptClickListener(Order order);
        public void onRejectClickListener(Order order);
    }


    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, parent, false);
        return new OrderAdapter.OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        final Order mOrder = orders.get(position);
        String status = mOrder.getStatus();
        Product mProduct =  mOrder.getProduct();
        holder.priceName.setText(mProduct.getPrice());

        holder.removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterInterface.onRemoveClickListener(mOrder);
            }
        });
        holder.viewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterInterface.onViewClickListener(mOrder);
            }
        });

        holder.rejectOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterInterface.onRejectClickListener(mOrder);
            }
        });

        holder.acceptOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterInterface.onAcceptClickListener(mOrder);
            }
        });

        Glide.with(context).load(AppClient.MASTEERURL+ mProduct.getItem().getImageUrl()).placeholder(R.drawable.shop_placeholder).into(holder.productImage);
        if(isSeller){
            holder.removeItem.setVisibility(View.GONE);
            holder.sellerOptionLayout.setVisibility(View.VISIBLE);
            holder.rejectOrder.setVisibility(View.VISIBLE);
        }

        if(status.equals("pending"))
            holder.statusText.setText("Waiting for seller's confirmation");
        else if(status.equals("accepted"))
            holder.statusText.setText("Seller has been accepted your order");
        else if(status.equals("rejected"))
            holder.statusText.setText("Seller has been rejected your order");
        else holder.statusText.setVisibility(View.GONE);


    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {
        public TextView name, priceName,statusText;
        LinearLayout sellerOptionLayout;
        MaterialButton viewDetails, removeItem, acceptOrder, rejectOrder;
        ImageView productImage;

        public OrderViewHolder(View view) {
            super(view);
            productImage = view.findViewById(R.id.productImage);
            priceName =  view.findViewById(R.id.priceValue);
            statusText =  view.findViewById(R.id.status);
            name = view.findViewById(R.id.productName);
            viewDetails = view.findViewById(R.id.view_order);
            removeItem = view.findViewById(R.id.remove_order);
            acceptOrder =  view.findViewById(R.id.accept_order);
            rejectOrder =  view.findViewById(R.id.reject_order);
            sellerOptionLayout = view.findViewById(R.id.seller_option);

        }
    }
}
