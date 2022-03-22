package com.example.gestiondesdpenses.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gestiondesdpenses.R;

import java.util.ArrayList;
import java.util.List;

public class CalendarMonthAdapter extends BaseAdapter{
    Context context;
    List<String> list;
    int year;
    public int selected_pos = -1;

    public CalendarMonthAdapter(Context context, int year) {
        this.context = context;
        this.year = year;
        list = new ArrayList<>();
        loadData();
    }

    public void setYear(int selected_year){
        this.year = selected_year;
        list.clear();
        loadData();
        notifyDataSetChanged();
    }
    private void loadData() {
        for(int i=1;i<13;i++){
            String data = i + "/" + year;
            list.add(data);
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.item_gv_month_choice,parent,false);
        TextView tv = convertView.findViewById(R.id.item_month_tv);
        tv.setText(list.get(position));
        tv.setBackgroundResource(R.color.background_grey1);
        tv.setTextColor(Color.BLACK);

        if(position == selected_pos){
            tv.setBackgroundResource(R.color.background_green1);
            tv.setTextColor(Color.WHITE);
        }
        return convertView;
    }
}
