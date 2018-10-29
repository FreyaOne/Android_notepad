package com.example.fling.note;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.LinkedList;

public class ListAdapter extends BaseAdapter {

    private LinkedList<Note> data;
    private Context context;

    public ListAdapter(LinkedList<Note> data,Context context){
        this.data = data;
        this.context = context;
    }

    public int getCount(){
        return data.size();
    }

    public Object getItem(int position){
        return null;
    }

    public long getItemId(int position){
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        convertView = LayoutInflater.from(context).inflate(R.layout.my_listitem,parent,false);
        TextView title = (TextView) convertView.findViewById(R.id.itemTitle);
        TextView time = (TextView) convertView.findViewById(R.id.itemTime);
        title.setText(data.get(position).getTitle());
        time.setText(data.get(position).getTime());
        return convertView;
    }
}
