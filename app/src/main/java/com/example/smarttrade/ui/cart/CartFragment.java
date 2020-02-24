package com.example.smarttrade.ui.cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smarttrade.R;
import com.example.smarttrade.adapters.CartAdapter;
import com.example.smarttrade.adapters.ProductAdapter;
import com.example.smarttrade.config.DataManager;
import com.example.smarttrade.interfaces.RetrofitCallBack;
import com.example.smarttrade.models.Cart;
import com.example.smarttrade.ui.home.HomeFragment;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class CartFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private RecyclerView cartItems;
    private MaterialButton buyNowButton;
    private TextView emptyCart;
    private CartAdapter cartAdapter;
    private CartAdapter.CartAdapterInterface cartAdapterInterface;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
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

            }

            @Override
            public void onRemoveClicked(String cartId) {

            }
        };

        getCartItems();
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
                    cartAdapter = new CartAdapter(getActivity(), data, cartAdapterInterface);

                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                    cartItems.setLayoutManager(mLayoutManager);
                    cartItems.setAdapter(cartAdapter);
                    cartItems.setHasFixedSize(true);
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