package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{

    Context context;
    List<Product.Item> product;

    public MyAdapter(Context context, List<Product.Item> product) {
        this.context = context;
        this.product = product;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.prod_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.TV1.setText(product.get(position).getProdname());
        holder.TV2.setText(product.get(position).getDesc());
        holder.imageView.setImageResource(product.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return product.size();
    }
}
