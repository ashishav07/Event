package com.example.owner.event;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class viewAdapter extends RecyclerView.Adapter<viewAdapter.viewHolder>{
    @NonNull
    private Context context;
    private ArrayList<view> list;

    public viewAdapter(@NonNull Context context, ArrayList<view> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.list,null);
        return new viewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder viewHolder, int i) {
        view vItem = list.get(i);
        viewHolder.nameTextView.setText(vItem.getmName());
        viewHolder.dateTextView.setText(vItem.getmDate());
        viewHolder.timeTextView.setText(vItem.getmTime());
        //viewHolder.imageView.setImageDrawable(context.getResources().getDrawable());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class viewHolder extends RecyclerView.ViewHolder{

        TextView nameTextView,dateTextView,timeTextView;
        ImageView imageView;

        public viewHolder(View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.name);
            dateTextView = itemView.findViewById(R.id.date);
            timeTextView = itemView.findViewById(R.id.stime);
            imageView = itemView.findViewById(R.id.img);
        }
    }
}
/*public class viewAdapter extends ArrayAdapter<view> {
    public viewAdapter(Activity context, ArrayList<view> views) {
        super(context, 0, views);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list, parent, false);
        }
        view currentView=getItem(position);
        TextView name = (TextView) listItemView.findViewById(R.id.name);
        TextView date = (TextView) listItemView.findViewById(R.id.date);
        TextView time = (TextView) listItemView.findViewById(R.id.stime);
        name.setText(currentView.getmName());
        date.setText(currentView.getmDate());
        time.setText(currentView.getmTime());
//        View textContainer=listItemView.findViewById(R.id.textContainer);

        return listItemView;
    }
}
*/