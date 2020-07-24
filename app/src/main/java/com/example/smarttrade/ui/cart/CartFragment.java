package com.example.smarttrade.ui.cart;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smarttrade.R;
import com.example.smarttrade.ViewDetails;
import com.example.smarttrade.adapters.CartAdapter;
import com.example.smarttrade.config.DataManager;
import com.example.smarttrade.interfaces.RetrofitCallBack;
import com.example.smarttrade.models.Cart;
import com.example.smarttrade.models.DeleteModel;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.HashMap;

public class CartFragment extends Fragment {

    private RecyclerView cartItems;
    private MaterialButton buyNowButton;
    private TextView emptyCart;
    private CartAdapter cartAdapter;
    private CartAdapter.CartAdapterInterface cartAdapterInterface;
    private  ArrayList<Cart> carts;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_cart, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cartItems =  view.findViewById(R.id.cart_list);
        emptyCart = view.findViewById(R.id.cart_empty);
        buyNowButton = view.findViewById(R.id.cart_buy_now);

        cartAdapterInterface = new CartAdapter.CartAdapterInterface() {
            @Override
            public void onViewDetailsClicked(String productId) {
                startActivity(new Intent(getActivity(), ViewDetails.class).putExtra("productId", productId));
            }

            @Override
            public void onRemoveClicked(String cartId) {
                DataManager.getDataManager().removeCart(cartId, new RetrofitCallBack<DeleteModel>() {
                    @Override
                    public void Success(DeleteModel data) {
                        Toast.makeText(getContext(), "Item removed", Toast.LENGTH_LONG).show();
                        getCartItems();
                    }

                    @Override
                    public void Failure(String error) {

                    }
                });
            }
        };

        buyNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> cartIds = new ArrayList();
                int cartSize =  carts.size();
                for(int i=0; i<cartSize; i++)
                     cartIds.add(carts.get(i).getId());

            }
        });


        carts = new ArrayList();
        cartAdapter = new CartAdapter(getActivity(), carts, cartAdapterInterface);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        cartItems.setLayoutManager(mLayoutManager);
        cartItems.setAdapter(cartAdapter);
        cartItems.setHasFixedSize(true);

        getCartItems();
    }

    private HashMap<String, String> getOrderItemsParams(ArrayList<String> cartIds) {

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("carts", cartIds.toString());
        return hashMap;
    }

    private void orderItem(ArrayList<String> cartIds){
        DataManager.getDataManager().addToOrders(getOrderItemsParams(cartIds), new RetrofitCallBack<String>() {
            @Override
            public void Success(String data) {
                
            }

            @Override
            public void Failure(String error) {

            }
        });
    }

    public  void  getCartItems(){
        DataManager.getDataManager().getCarts(new RetrofitCallBack<ArrayList<Cart>>() {
            @Override
            public void Success(ArrayList<Cart> data) {
                if(data.size() == 0){
                    cartItems.setVisibility(View.GONE);
                    emptyCart.setVisibility(View.VISIBLE);
                }else{

                    cartItems.setVisibility(View.VISIBLE);
                    emptyCart.setVisibility(View.GONE);
                    carts.clear();
                    int cartSize =  data.size();
                    for(int i=0; i<cartSize; i++)
                        carts.add(data.get(i));

                    cartAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void Failure(String error) {
                cartItems.setVisibility(View.GONE);
                emptyCart.setVisibility(View.VISIBLE);
            }
        });
    }
}