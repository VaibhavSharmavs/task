package com.example.task.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task.MainActivity;
import com.example.task.OnBackPressedListener;
import com.example.task.R;
import com.example.task.adapter.CategoryListAdapter;
import com.example.task.adapter.ItemListAdapter;
import com.example.task.base.ApiCallFragment;
import com.example.task.network.response.GetCategoryResponse;
import com.example.task.network.response.ItemListResponse;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class ItemListFragment extends ApiCallFragment implements OnBackPressedListener {
    RecyclerView recyclerView;
    LinearLayout cartLayout;
    TextView itemCount;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_list_fragment, container, false);
        recyclerView=view.findViewById(R.id.recyclerView);
        cartLayout=view.findViewById(R.id.cartLayout);
        itemCount=view.findViewById(R.id.itemCount);
        cartLayout.setVisibility(View.GONE);
        apiCallBack(getApi().getItemList(getArguments().getString("id")));
        return view;

    }

    @Subscribe
    public void getItemList(ItemListResponse response){

        recyclerView.setHasFixedSize(true);
        recyclerView.setFocusable(false);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyclerView.setAdapter(new ItemListAdapter(getActivity(),cartLayout,itemCount,response.getItems()));
    }

    @Override
    public void onBackPressed() {


    }
}
