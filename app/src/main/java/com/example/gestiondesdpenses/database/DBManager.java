package com.example.gestiondesdpenses.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.gestiondesdpenses.R;
import com.example.gestiondesdpenses.adapter.AccountBaseAdapter;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DBManager {
    /*
     **Database management class, operate on the contents of the table
     */


    private static SQLiteDatabase db; // Object of the local database

    //Initilize the database
    public static void initDB(Context context){

        DBOpenHelper helper = new DBOpenHelper(context); //Get the helper object

        db = helper.getWritableDatabase();//Get the database object from the just opened helper object
    }

    //Read the type data from the database and store to a list of TypeBeans.
    public static List<TypeBean>getTypeList(int mykind){
        List<TypeBean> list = new ArrayList<>();
        String sql = "select * from typeTb where kind="+mykind;//////temporaire
        Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String type_name = cursor.getString(cursor.getColumnIndex("type_name"));
            int imageID = cursor.getInt(cursor.getColumnIndex("imageID"));
            int select_imageID = cursor.getInt(cursor.getColumnIndex("select_imageID"));
            int kind = cursor.getInt(cursor.getColumnIndex("kind"));//ohhou!!
            TypeBean typeBean = new TypeBean(id,type_name,imageID,select_imageID,kind); //construct un TypeBean object by data
            list.add(typeBean);
        }
        cursor.close();
        return list;
    }

    public static void insertAccount(AccountBean accountBean){
        String sql = "insert into accountTb(type_name,imageID,remarks,money,kind,time,year,month,day) values(?,?,?,?,?,?,?,?,?)";
        db.execSQL(sql,new Object[]{accountBean.getTypeName(),accountBean.getTypeImageId(),accountBean.getRemarks(),
        accountBean.getMoney(),accountBean.getKind(),accountBean.getTime(),
        accountBean.getYear(),accountBean.getMonth(),accountBean.getDay()});
    }

    public static List<AccountBean> obtainDailyAccounts(int year, int month, int day){
        List<AccountBean> accounts = new ArrayList<>();
        String sql = "select * from accountTB where " +
                "year="+year+" and " +
                "month="+month+" and " +
                "day="+day+" order by id desc";
        Cursor cursor = db.rawQuery(sql,null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String type_name = cursor.getString(cursor.getColumnIndex("type_name"));
            int imageID = cursor.getInt(cursor.getColumnIndex("imageID"));
            String remarks = cursor.getString(cursor.getColumnIndex("remarks"));
            float money = cursor.getFloat(cursor.getColumnIndex("money"));
            int kind = cursor.getInt(cursor.getColumnIndex("kind"));
            String time = cursor.getString(cursor.getColumnIndex("time"));
            AccountBean account = new AccountBean(id,imageID,type_name,remarks,money,kind,time,year,month,day);
            accounts.add(account);
        }
        cursor.close();
        return accounts;
    }

    public static List<AccountBean> obtainMonthlyAccounts(int year, int month){
        List<AccountBean> accounts = new ArrayList<>();
        String sql = "select * from accountTB where " +
                "year="+year+" and " +
                "month="+month+" order by id desc";
        Cursor cursor = db.rawQuery(sql,null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String type_name = cursor.getString(cursor.getColumnIndex("type_name"));
            int imageID = cursor.getInt(cursor.getColumnIndex("imageID"));
            String remarks = cursor.getString(cursor.getColumnIndex("remarks"));
            float money = cursor.getFloat(cursor.getColumnIndex("money"));
            int kind = cursor.getInt(cursor.getColumnIndex("kind"));
            String time = cursor.getString(cursor.getColumnIndex("time"));
            int day = cursor.getInt(cursor.getColumnIndex("day"));
            AccountBean account = new AccountBean(id,imageID,type_name,remarks,money,kind,time,year,month,day);
            accounts.add(account);
        }
        cursor.close();
        return accounts;
    }

    public static float obtainDailySum(int year,int month,int day,int kind){
        float sum = 0;
        String sql = "select sum(money) as sum_money " +
                "from accountTb " +
                "where year ="+year+" and " +
                "month="+month+" and " +
                "day="+day+" and " +
                "kind="+kind;
        Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext()){
            sum = cursor.getFloat(cursor.getColumnIndex("sum_money"));
        }
        cursor.close();
        return sum;
    }

    public static float obtainMonthlySum(int year, int month, int kind){
        float sum = 0;
        String sql = "select sum(money) as sum_money " +
                "from accountTb " +
                "where year ="+year+" and " +
                "month="+month+" and " +
                "kind="+kind;
        Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext()){
            sum = cursor.getFloat(cursor.getColumnIndex("sum_money"));
        }
        cursor.close();
        return sum;
    }

    public static List<Integer> obtainYearList(){
        List<Integer> years = new ArrayList<>();
        String sql = "select distinct year " +
                "from accountTb " +
                "order by year asc";
        Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext()){
            years.add(cursor.getInt(cursor.getColumnIndex("year")));
        }
        cursor.close();
        return years;
    }

    public static int deleteAccount (int id){
        int i = -1;
        i = db.delete("accountTb","id=?",new String[]{id+""});
        return i;
    }
}
