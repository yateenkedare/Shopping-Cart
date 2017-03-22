package com.example.yatee.midterm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.example.yatee.midterm.R.id.imageView;

/**
 * Created by yatee on 3/20/2017.
 */

public class CustomListAdapter extends ArrayAdapter<Item> {
    // View lookup cache
    Context context;
    private static class ViewHolder {
        TextView title;
        TextView price;
        ImageView imageView;
    }

    public CustomListAdapter(Context context, List<Item> users) {
        super(context, R.layout.my_list, users);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position

        Item item = getItem(position);

        ViewHolder viewHolder; // view lookup cache stored in tag

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.my_list, parent, false);
            viewHolder.title= (TextView) convertView.findViewById(R.id.listTitleTV);
            viewHolder.price= (TextView) convertView.findViewById(R.id.listPriceTV);
            viewHolder.imageView= (ImageView) convertView.findViewById(R.id.imageView2);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.title.setText(item.getTitle());
        viewHolder.price.setText(item.getSale_price());


          Picasso.with(context)
                            .load(item.getImageURL())
                            .into(viewHolder.imageView);

        return convertView;
    }
}
