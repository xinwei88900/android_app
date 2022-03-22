package com.example.gestiondesdpenses;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gestiondesdpenses.adapter.AccountBaseAdapter;
import com.example.gestiondesdpenses.database.AccountBean;
import com.example.gestiondesdpenses.database.DBManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private ImageButton imageButton_plus,imageButton_monthly;

    ListView listView;
    List<AccountBean> accountList;
    AccountBaseAdapter adapter;
    int year,month,day;

    //for listViewHeader
    private View headerView;
    TextView dateTv,depenseMonthTV,revenueMonthTv,dailyMoneyTv;
    String headerTime;
    String[] monthNames = {"Janvier","Février","Mars","Avril","Mai","Juin","Juillet","Août","Septembre","Octobre","Novembre","Décembre"};
    int dailyIncome,dailyOutcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setInstantTime();
        setContentView(R.layout.activity_main);
        imageButton_plus = (ImageButton) findViewById(R.id.btn_plus);
        imageButton_monthly = (ImageButton) findViewById(R.id.btn_list);
        listView = findViewById(R.id.lv_daily);
        addListViewHeader();

        accountList = new ArrayList<>();
        adapter = new AccountBaseAdapter(this,accountList);
        listView.setAdapter(adapter);


    }

    private void addListViewHeader() {
        headerView = getLayoutInflater().inflate(R.layout.item_lv_main_top,null);
        listView.addHeaderView(headerView);

        dateTv = headerView.findViewById(R.id.item_top_tv_date);
        depenseMonthTV = headerView.findViewById(R.id.item_top_tv_depense_money);
        revenueMonthTv = headerView.findViewById(R.id.item_top_tv_revenue_money);
        dailyMoneyTv = headerView.findViewById(R.id.item_top_tv_daily_money);

        dateTv.setText(headerTime);
    }
    private void upDateHeaderMoney() {
        float sumOutcome = DBManager.obtainDailySum(year,month,day,0);
        float sumIncome = DBManager.obtainDailySum(year,month,day,1);
        dailyMoneyTv.setText("Aujourd'hui:  dépenses "+sumOutcome+"€  , revenus "+sumIncome+"€.");
        depenseMonthTV.setText(DBManager.obtainMonthlySum(year,month,0)+" €");
        revenueMonthTv.setText(DBManager.obtainMonthlySum(year,month,1)+" €");
    }

    private void setInstantTime() {
        year = Calendar.getInstance().get(Calendar.YEAR);
        month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        headerTime = day+" " +monthNames[month-1]+" "+year;
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadDataToListView();
        upDateHeaderMoney();
    }

    private void loadDataToListView() {
        List<AccountBean> data = new ArrayList<>();
        data = DBManager.obtainDailyAccounts(year,month,day);
        accountList.clear();
        accountList.addAll(data);

        adapter.notifyDataSetChanged();
    }

    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()){
            case R.id.btn_list:
                intent = new Intent(MainActivity.this, MonthlyAccountsActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_plus:
                intent = new Intent(MainActivity.this,editActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_setting:
                intent = new Intent(MainActivity.this, SetUpActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}

