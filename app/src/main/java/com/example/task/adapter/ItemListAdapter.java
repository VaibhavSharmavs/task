package com.example.task.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task.R;
import com.example.task.fragment.ItemListFragment;
import com.example.task.network.response.GetCategoryResponse;
import com.example.task.network.response.ItemListResponse;
import com.example.task.utils.Helper;

import java.util.ArrayList;
import java.util.List;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ViewHolder> {

    Activity context;
    List<ItemListResponse.Items> responseList;
    LinearLayout cartLayout;
    TextView itemCount;

    public ItemListAdapter(Activity context, LinearLayout cartLayout, TextView itemCount, List<ItemListResponse.Items> data) {
        this.context = context;
        this.responseList = data;
        this.cartLayout = cartLayout;
        this.itemCount = itemCount;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, @SuppressLint("RecyclerView") int position) {

        viewHolder.name.setText(responseList.get(position).getName());
        viewHolder.discountedPrice.setText(""+responseList.get(position).getDiscounted_price());
        viewHolder.price.setText(""+responseList.get(position).getPrice());
        if (responseList.get(position).getQuantity()==0){
            viewHolder.add.setText("ADD");
        }else {
            viewHolder.add.setText(""+responseList.get(position).getQuantity());
        }

        if (responseList.get(position).getDiscounted_price()==responseList.get(position).getPrice()){
            viewHolder.price.setVisibility(View.GONE);
        }else {
            viewHolder.price.setVisibility(View.VISIBLE);
        }

        viewHolder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (responseList.get(position).getQuantity()==0){
                    responseList.get(position).setQuantity(1);
                    notifyDataSetChanged();
                    showHideCartLayout();
                }
            }
        });

        viewHolder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity=responseList.get(position).getQuantity();
                responseList.get(position).setQuantity(quantity+1);
                notifyDataSetChanged();
                showHideCartLayout();
            }
        });
        viewHolder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (responseList.get(position).getQuantity()!=0){
                    int quantity=responseList.get(position).getQuantity();
                    responseList.get(position).setQuantity(quantity-1);
                    notifyDataSetChanged();
                    showHideCartLayout();
                }
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
        ImageView image;
        LinearLayout offerLayout;
        TextView offer;
        TextView discountedPrice;
        TextView price;
        TextView minus;
        TextView add;
        TextView plus;


        public ViewHolder(View itemView) {
            super(itemView);
            mainLayout=itemView.findViewById(R.id.mainLayout);
            name=itemView.findViewById(R.id.name);
            image=itemView.findViewById(R.id.image);
            offerLayout=itemView.findViewById(R.id.offerLayout);
            offer=itemView.findViewById(R.id.offer);
            discountedPrice=itemView.findViewById(R.id.offer);
            price=itemView.findViewById(R.id.price);
            minus=itemView.findViewById(R.id.minus);
            add=itemView.findViewById(R.id.add);
            plus=itemView.findViewById(R.id.plus);

        }
    }




    @Override
    public int getItemViewType(int position) {
        return position;
    }

    List<Integer> showHideCartLayout(){
       List<Integer> ids=new ArrayList<>();
       int count = 0;
        for (int i=0;i<responseList.size();i++){
            if (responseList.get(i).getQuantity()>0){
                ids.add(responseList.get(i).getId());
                count=count+responseList.get(i).getQuantity();
            }
        }

        itemCount.setText(""+count);

        if (ids.size()>0){
            cartLayout.setVisibility(View.VISIBLE);
        }else {
            cartLayout.setVisibility(View.GONE);
        }

        return ids;
    }


}