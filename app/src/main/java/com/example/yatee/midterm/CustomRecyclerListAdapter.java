package com.example.yatee.midterm;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomRecyclerListAdapter extends RecyclerView.Adapter<CustomRecyclerListAdapter.ViewHolder>{

    Context context;
    int resource;
    List<Item> objects;
    IData activity;

    CustomRecyclerListAdapter(Context context, int resource, List<Item> ItemsList, IData activity){
        this.context = context;
        this.resource = resource;
        objects=ItemsList;
        this.activity = activity;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTV, priceTV, discTV;
        public ImageView imageView;
        public int position;
        public Button addToCart;
        public ViewHolder(View itemView) {
            super(itemView);
            titleTV= (TextView) itemView.findViewById(R.id.titleTV);
            priceTV= (TextView) itemView.findViewById(R.id.priceTV);
            discTV= (TextView) itemView.findViewById(R.id.discountTV);
            imageView= (ImageView) itemView.findViewById(R.id.imageView);
            addToCart = (Button) itemView.findViewById(R.id.button);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(resource, parent, false);
        ViewHolder vh=new ViewHolder(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Item item= objects.get(position);

        holder.titleTV.setText(item.getTitle());
        holder.priceTV.setText(item.getSale_price());
        holder.discTV.setText(item.getDiscount());
        Picasso.with(context)
                .load(item.getImageURL())
                .resize(300,400)
                .into(holder.imageView);
        holder.position = position;

        if(item.getUid() == null) {

            holder.addToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.ListResultValues(position);
                    holder.addToCart.setEnabled(false);

                }
            });
        }
        else
            holder.addToCart.setEnabled(false);
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public interface IData{
        public void ListResultValues(int result);
    }
}
