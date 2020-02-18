package com.example.smarttrade.ui.home;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smarttrade.R;
import com.example.smarttrade.ViewDetails;
import com.example.smarttrade.adapters.ProductAdapter;
import com.example.smarttrade.config.DataManager;
import com.example.smarttrade.interfaces.RetrofitCallBack;
import com.example.smarttrade.models.Product;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RecyclerView productRecyclerView;
    private TextView  infoTextView;
    private ProductAdapter productAdapter;
    private ArrayList<Product> products;
    private ProductAdapter.ProjectAdapterInterface projectAdapterInterface;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        productRecyclerView = view.findViewById(R.id.productRecyclerView);
        infoTextView = view.findViewById(R.id.info_text);

        projectAdapterInterface = new ProductAdapter.ProjectAdapterInterface() {
            @Override
            public void onViewDetailsClicked() {
                startActivity(new Intent(getActivity(), ViewDetails.class));
            }
        };
        products = new ArrayList<>();
        productAdapter = new ProductAdapter(getActivity(), products, projectAdapterInterface);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);
        productRecyclerView.setLayoutManager(mLayoutManager);
        productRecyclerView.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(8), true));
        productRecyclerView.setAdapter(productAdapter);
        productRecyclerView.setHasFixedSize(true);

        getProducts();
    }


    private void getProducts(){
        DataManager.getDataManager().getProducts(new RetrofitCallBack<ArrayList<Product>>() {
            @Override
            public void Success(ArrayList<Product> data) {
                    if(data.size() == 0){
                        productRecyclerView.setVisibility(View.GONE);
                        infoTextView.setVisibility(View.VISIBLE);
                    }else{

                        productRecyclerView.setVisibility(View.VISIBLE);
                        infoTextView.setVisibility(View.GONE);
                        products = data;
                        productAdapter.notifyDataSetChanged();
                    }
            }

            @Override
            public void Failure(String error) {

            }
        });
    }


    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}