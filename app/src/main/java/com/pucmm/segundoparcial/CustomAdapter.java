package com.pucmm.segundoparcial;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{

    private Context context;
    Activity activity;
    private ArrayList item_id, item_name, item_price, item_cat;

    CustomAdapter(Activity activity, Context context, ArrayList item_id, ArrayList item_name, ArrayList item_price, ArrayList item_cat){
        this.activity = activity;
        this.context = context;
        this.item_id = item_id;
        this.item_name = item_name;
        this.item_price = item_price;
        this.item_cat = item_cat;

    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, final int position) {
        //holder.item_id_txt.setText(String.valueOf(item_id.get(position)));
        holder.item_name_txt.setText(String.valueOf(item_name.get(position)));
        holder.item_price_txt.setText(String.valueOf(item_price.get(position)));
        holder.item_cat_txt.setText(String.valueOf(item_cat.get(position)));

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("name", String.valueOf(item_name.get(position)));
                intent.putExtra("price", String.valueOf(item_price.get(position)));
                intent.putExtra("category", String.valueOf(item_cat.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return item_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView item_id_txt, item_name_txt, item_price_txt, item_cat_txt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            item_name_txt = itemView.findViewById(R.id.item_name);
            item_price_txt = itemView.findViewById(R.id.item_price);
            item_cat_txt = itemView.findViewById(R.id.item_cat);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
