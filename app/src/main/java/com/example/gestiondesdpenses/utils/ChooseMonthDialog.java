package com.example.gestiondesdpenses.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.gestiondesdpenses.R;
import com.example.gestiondesdpenses.adapter.CalendarMonthAdapter;
import com.example.gestiondesdpenses.database.DBManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ChooseMonthDialog extends Dialog implements View.OnClickListener {

    LinearLayout linearLayout;
    GridView gridView;
    ImageView imageView;
    List<TextView> year_views;
    List<Integer> years;
    CalendarMonthAdapter adapter;
    int selected_year = -1;
    int selected_month = -1;
    public ChooseMonthDialog(@NonNull Context context) {
        super(context);
    }

    public interface OnRefreshListener{
        public void onRefresh(int sel_pos, int year, int month);
    }
    OnRefreshListener onRefreshListener;

    public void setOnRefreshListener(OnRefreshListener onRefreshListener) {
        this.onRefreshListener = onRefreshListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_choose_month);
        linearLayout = findViewById(R.id.dialog_choose_month_ll);
        gridView = findViewById(R.id.dialog_choose_month_gv);
        imageView = findViewById(R.id.dialog_choose_month_close);

        imageView.setOnClickListener(this);
        year_views = new ArrayList<>();
        addViewToLayout();
        initGridView();
        setGvListener();
    }

    private void setGvListener() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.selected_pos = position;
                selected_month = position;
                adapter.notifyDataSetInvalidated();
            }
        });
    }

    private void initGridView() {
        adapter = new CalendarMonthAdapter(getContext(),years.get(selected_year));
        if(selected_month == -1){
            adapter.selected_pos = Calendar.getInstance().get(Calendar.MONTH);
        }else{
            adapter.selected_pos = selected_month - 1;
        }
        gridView.setAdapter(adapter);
    }

    private void addViewToLayout() {
        years = DBManager.obtainYearList();
        for(int i=0;i<years.size();i++){
            int year = years.get(i);
            View view = getLayoutInflater().inflate(R.layout.item_sv_year_choice,null);
            linearLayout.addView(view);
            TextView textView = view.findViewById(R.id.item_year_tv);
            textView.setText(year+"");
            year_views.add(textView);
        }
        if(selected_year == -1){
            selected_year = years.size() - 1;
        }
        changeSelectedBackground();
        setLinearLayoutClickListener();
    }

    private void changeSelectedBackground(){
        for(int i=0;i<year_views.size();i++){
            TextView tv = year_views.get(i);
            tv.setBackgroundResource(R.drawable.dialog_btn_background);
            tv.setTextColor(Color.BLACK);
        }
        TextView selectTv = year_views.get(selected_year);
        selectTv.setBackgroundResource(R.drawable.main_setting_btn_bg);
        selectTv.setTextColor(Color.WHITE);
    }

    private void setLinearLayoutClickListener(){
        for(int i=0;i<year_views.size();i++){
            TextView view = year_views.get(i);
            final int pos = i;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selected_year = pos;
                    changeSelectedBackground();
                    adapter.setYear(years.get(selected_year));
                }
            });
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dialog_choose_month_close:
                int month = selected_month;
                int year = years.get(selected_year);
                onRefreshListener.onRefresh(selected_year,year,month);
                cancel();
                break;
            default:
                break;
        }
    }

    public void setDialogSize(){
        Window window = getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        Display d = window.getWindowManager().getDefaultDisplay();
        wlp.width = (int) d.getWidth();
        wlp.gravity = Gravity.TOP;
        //window.setBackgroundDrawableResource();
        window.setAttributes(wlp);
    }
    
}
