package com.example.gestiondesdpenses.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gestiondesdpenses.R;
import com.example.gestiondesdpenses.database.AccountBean;

import java.util.Calendar;
import java.util.List;

public class AccountBaseAdapter extends BaseAdapter {

    Context context;
    List<AccountBean> accountList;
    LayoutInflater inflater;
    int year,month,day;

    public AccountBaseAdapter(Context context, List<AccountBean> accountList) {
        this.context = context;
        this.accountList = accountList;
        inflater = LayoutInflater.from(context);

        year = Calendar.getInstance().get(Calendar.YEAR);
        month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public int getCount() {
        return accountList.size();
    }

    @Override
    public Object getItem(int position) {
        return accountList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView==null){
            convertView = inflater.inflate(R.layout.item_lv_main,parent,false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        AccountBean account = accountList.get(position);
        viewHolder.typeIV.setImageResource(account.getTypeImageId());
        viewHolder.typeTV.setText(account.getTypeName());
        viewHolder.remarkTV.setText(account.getRemarks());
        viewHolder.moneyTV.setText(String.valueOf(account.getMoney())+" €");
        if(account.getYear()==year && account.getMonth()==month && account.getDay()==day){
            String timeHour = account.getTime().split(" ")[1];
            viewHolder.timeTV.setText("Aujourd'hui "+timeHour);
        }else {
            viewHolder.timeTV.setText(account.getTime());
        }
        return convertView;
    }


    class ViewHolder{

        ImageView typeIV;
        TextView typeTV,remarkTV,moneyTV,timeTV;
        public  ViewHolder(View view){
            typeIV = view.findViewById(R.id.item_iv);
            typeTV = view.findViewById(R.id.item_lv_main_lv1_tv_type);
            remarkTV = view.findViewById(R.id.item_lv_main_lv1_tv_remarks);
            moneyTV = view.findViewById(R.id.item_lv_main_lv2_tv_money);
            timeTV = view.findViewById(R.id.item_lv_main_lv2_tv_time);
        }
    }
}
