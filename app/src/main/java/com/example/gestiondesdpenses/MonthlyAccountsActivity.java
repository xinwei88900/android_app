package com.example.gestiondesdpenses;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gestiondesdpenses.adapter.AccountBaseAdapter;
import com.example.gestiondesdpenses.database.AccountBean;
import com.example.gestiondesdpenses.database.DBManager;
import com.example.gestiondesdpenses.utils.ChooseMonthDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MonthlyAccountsActivity extends AppCompatActivity {

    ListView listView;
    List<AccountBean> accountList;
    AccountBaseAdapter adapter;
    TextView textView;
    int year, month;
    String headerTime;
    String[] monthNames = {"Janvier","Février","Mars","Avril","Mai","Juin","Juillet","Août","Septembre","Octobre","Novembre","Décembre"};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_accounts);
        setInstantTime();
        textView = findViewById(R.id.monthly_account_tv);
        listView = findViewById(R.id.monthly_account_lv);
        textView.setText(headerTime);
        accountList = new ArrayList<>();
        adapter = new AccountBaseAdapter(this,accountList);
        listView.setAdapter(adapter);
        //setLvLongClickListener();
    }

    private void setLvLongClickListener() {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AccountBean bean = accountList.get(position);
                int bean_id = bean.getId();
                comeUpDeleteDialog(bean_id);

                return false;
            }
        });
    }

    private void comeUpDeleteDialog(int bean_id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("information:").setMessage("Êtes-vous sûr de supprimer ce compte?")
                .setNegativeButton("Annuler",null)
                .setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBManager.deleteAccount(bean_id);
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadDataToListView();
    }

    private void loadDataToListView() {
        List<AccountBean> data = new ArrayList<>();
        data = DBManager.obtainMonthlyAccounts(year,month);
        accountList.clear();
        accountList.addAll(data);
        adapter.notifyDataSetChanged();//??or invalide??
    }

    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()){
            case R.id.monthly_account_iv_close:
                finish();
                break;
            case R.id.monthly_account_bt:
                intent = new Intent(MonthlyAccountsActivity.this, AnalyseActivity.class);
                startActivity(intent);
                break;
            case R.id.monthly_account_iv_date:
                comeUpDialog();
                break;
            default:
                break;
        }
    }

    private void comeUpDialog() {
        ChooseMonthDialog dialog = new ChooseMonthDialog(this);
        dialog.show();
        dialog.setDialogSize();
        dialog.setOnRefreshListener(new ChooseMonthDialog.OnRefreshListener() {
            @Override
            public void onRefresh(int sel_pos, int year, int month) {
                setChosedTime(year, month);
                //headerTime = monthNames[month]+" "+year;
                //textView.setText(headerTime);
                loadDataToListView();
            }

        });
    }

    private void setChosedTime(int sel_year, int sel_month) {
        this.year = sel_year;
        this.month = sel_month+1;
        this.headerTime = monthNames[month-1]+" "+this.year;
        textView.setText(headerTime);
    }

    private void setInstantTime() {
        year = Calendar.getInstance().get(Calendar.YEAR);
        month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        headerTime = monthNames[month-1]+" "+year;
    }



}