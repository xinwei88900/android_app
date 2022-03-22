package com.example.gestiondesdpenses.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.gestiondesdpenses.R;


public class DBOpenHelper extends SQLiteOpenHelper {

    public DBOpenHelper(@Nullable Context context) {
        super(context, "gestiondesdpenses.db", null, 1);
    }


    // methods executed when the project is run for the first time
    // create the dataBase for our project
    @Override
    public void onCreate(SQLiteDatabase db) {
        //create a table for all types of transactions
        String sql = "create table typeTb(" +
                "id integer primary key autoincrement, " +
                "type_name varchar(20)," +
                "imageID integer," +
                "select_imageID integer," +
                "kind integer)";
        db.execSQL(sql);
        insertType(db);

        sql = "create table accountTb(" +
                "id integer primary key autoincrement," +
                "type_name varchar(20)," +
                "imageID integer," +
                "remarks varchar(100)," +
                "money float," +
                "kind integer," +
                "time varchar(30)," +
                "year integer," +
                "month integer," +
                "day integer)";
        db.execSQL(sql);
    }




    private void insertType(SQLiteDatabase db) {
        String sql = "insert into typeTb (type_name,imageID,select_imageID,kind) values (?,?,?,?)";
        db.execSQL(sql,new Object[]{"Autres", R.mipmap.elsegray,R.mipmap.elsered,0});  //0 represent outcome
        db.execSQL(sql,new Object[]{"Divertissement", R.mipmap.entertainmentgray,R.mipmap.entertainment,0});
        db.execSQL(sql,new Object[]{"Diète", R.mipmap.foodanddrinkgray,R.mipmap.foodanddrink,0});
        db.execSQL(sql,new Object[]{"Internet", R.mipmap.internetmobilegray,R.mipmap.internetmobile,0});
        db.execSQL(sql,new Object[]{"Médicament", R.mipmap.medicinegray,R.mipmap.medicine,0});
        db.execSQL(sql,new Object[]{"Residence", R.mipmap.residencegray,R.mipmap.residence,0});
        db.execSQL(sql,new Object[]{"Shopping", R.mipmap.shoppinggray,R.mipmap.shopping,0});
        db.execSQL(sql,new Object[]{"Supermarché", R.mipmap.supermarketgray,R.mipmap.supermarket,0});
        db.execSQL(sql,new Object[]{"Tabac&alcool", R.mipmap.tobaccoandalcoholgray,R.mipmap.tobaccoandalcohol,0});
        db.execSQL(sql,new Object[]{"Trafic", R.mipmap.trafficgray,R.mipmap.traffic,0});
        db.execSQL(sql,new Object[]{"Factures", R.mipmap.waterandelectricitygray,R.mipmap.waterandelectricity,0});

        db.execSQL(sql,new Object[]{"Autres", R.mipmap.elsegray,R.mipmap.elseblue,1});  //1 represent income
        db.execSQL(sql,new Object[]{"Salaire", R.mipmap.salarygray,R.mipmap.salary,1});
        db.execSQL(sql,new Object[]{"Vente", R.mipmap.salegray,R.mipmap.sale,1});
        db.execSQL(sql,new Object[]{"Finance", R.mipmap.financialmanagementgray,R.mipmap.financialmanagement,1});

    }

    //methods executed when the dataBase version needs to update
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

