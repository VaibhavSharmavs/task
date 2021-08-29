package com.example.task.fragment;


import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task.R;
import com.example.task.adapter.CategoryListAdapter;
import com.example.task.base.ApiCallFragment;
import com.example.task.network.response.GetCategoryResponse;

import org.greenrobot.eventbus.Subscribe;


public class CategoryListFragment extends ApiCallFragment {



    RecyclerView recyclerView;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.category_list_fragment, container, false);
        recyclerView=view.findViewById(R.id.recyclerView);
        apiCallBack(getApi().getCategory());

        return view;

    }

    @Subscribe
    public void getCategory(GetCategoryResponse response){

        recyclerView.setHasFixedSize(true);
        recyclerView.setFocusable(false);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new CategoryListAdapter(getActivity(),response.getStore_categories()));
    }

}
