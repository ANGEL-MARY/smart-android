package com.example.smarttrade.ui.sellerhome;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smarttrade.R;
import com.example.smarttrade.adapters.CartAdapter;
import com.example.smarttrade.config.DataManager;
import com.example.smarttrade.interfaces.RetrofitCallBack;
import com.example.smarttrade.models.Cart;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class SellerHomeFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private RecyclerView cartItems;
    private MaterialButton buyNowButton;
    private TextView emptyCart;
    private CartAdapter cartAdapter;
    private CartAdapter.CartAdapterInterface cartAdapterInterface;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.seller_fragment_home, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

}