package com.example.task.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task.R;
import com.example.task.fragment.ItemListFragment;
import com.example.task.network.response.GetCategoryResponse;
import com.example.task.utils.Helper;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.ViewHolder> {

    Activity context;
    List<GetCategoryResponse.StoreCategories> responseList;

    public CategoryListAdapter(Activity context, List<GetCategoryResponse.StoreCategories> data) {
        this.context = context;
        this.responseList = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_category, viewGroup, false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, @SuppressLint("RecyclerView") int position) {

        viewHolder.name.setText(responseList.get(position).getName());
        viewHolder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.fragmentSwitching(context,""+responseList.get(position).getId(),new ItemListFragment());
            }
        });

    }


    @Override
    public int getItemCount() {
        return responseList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout mainLayout;
        TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            mainLayout=itemView.findViewById(R.id.mainLayout);
            name=itemView.findViewById(R.id.name);

        }
    }




    @Override
    public int getItemViewType(int position) {
        return position;
    }


}