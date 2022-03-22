package com.example.gestiondesdpenses.frag_addnew;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gestiondesdpenses.R;
import com.example.gestiondesdpenses.database.TypeBean;

import java.util.List;

public class TypeBaseAdapter extends BaseAdapter{

    Context context;
    List<TypeBean> typeList;
    int selected_position=0;

    public TypeBaseAdapter(Context context, List<TypeBean> data) {
        this.context = context;
        this.typeList = data;
    }

    @Override
    public int getCount() {
        return typeList.size();
    }

    @Override
    public Object getItem(int position) {
        return typeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.item_gv_edit,parent,false);
        ImageView imageView = convertView.findViewById(R.id.item_gv_iv);
        TextView textView = convertView.findViewById(R.id.item_gv_tv);

        //get data source by position
        TypeBean typeBean = typeList.get(position);
        textView.setText(typeBean.getType_name());
        if(position == selected_position){
            imageView.setImageResource(typeBean.getSelect_imageID());
        }
        else{
            imageView.setImageResource(typeBean.getImageID());
        }
        return convertView;
    }
}
