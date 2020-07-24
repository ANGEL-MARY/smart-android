package com.example.smarttrade.ui.sellerorders;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smarttrade.R;
import com.example.smarttrade.ViewDetails;
import com.example.smarttrade.adapters.OrderAdapter;
import com.example.smarttrade.config.DataManager;
import com.example.smarttrade.interfaces.RetrofitCallBack;
import com.example.smarttrade.models.DeleteModel;
import com.example.smarttrade.models.Order;

import java.util.ArrayList;

public class OrdersFragment extends Fragment {

    TextView infoTextView;
    RecyclerView orderItems;
    private OrderAdapter adapter;
    private OrderAdapter.OrderAdapterInterface adapterInterface;
    private ArrayList<Order>  orders;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.seller_orders_fragment, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        infoTextView = view.findViewById(R.id.order_empty);
        orderItems =  view.findViewById(R.id.orders_list);

        adapterInterface =  new OrderAdapter.OrderAdapterInterface() {
            @Override
            public void onRemoveClickListener(Order order) {
            }

            @Override
            public void onViewClickListener(Order order) {
                startActivity(new Intent(getActivity(), ViewDetails.class).putExtra("productId", order.getProduct().getId()));
            }

            @Override
            public void onAcceptClickListener(Order order) {

            }

            @Override
            public void onRejectClickListener(Order order) {

            }
        };

        orders =  new ArrayList();
        adapter = new OrderAdapter(getActivity(), true, orders, adapterInterface );

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        orderItems.setLayoutManager(mLayoutManager);
        orderItems.setAdapter(adapter);
        orderItems.setHasFixedSize(true);

        getOrders();

    }

    private void getOrders(){
        DataManager.getDataManager().getSellerOrders(new RetrofitCallBack<ArrayList<Order>>() {
            @Override
            public void Success(ArrayList<Order> data) {
                if(data.size() == 0){
                    orderItems.setVisibility(View.GONE);
                    infoTextView.setVisibility(View.VISIBLE);
                }else{

                    orderItems.setVisibility(View.VISIBLE);
                    infoTextView.setVisibility(View.GONE);
                    orders.clear();
                    int orderSize =  data.size();
                    for(int i=0; i<orderSize; i++)
                        orders.add(data.get(i));

                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void Failure(String error) {

            }
        });
    }
}