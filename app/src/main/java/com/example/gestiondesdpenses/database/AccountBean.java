package com.example.gestiondesdpenses.database;

public class AccountBean {

    int id;
    int typeImageId;
    String typeName;
    String remarks;
    float money;
    int kind; // 0: outcome, 1:income
    String time;
    int year;
    int month;
    int day;

    public AccountBean() {
    }

    public AccountBean(int id, int typeImageId, String typeName, String remarks, float money, int kind, String time, int year, int month, int day) {
        this.id = id;
        this.typeImageId = typeImageId;
        this.typeName = typeName;
        this.remarks = remarks;
        this.money = money;
        this.kind = kind;
        this.time = time;
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTypeImageId() {
        return typeImageId;
    }

    public void setTypeImageId(int typeImageId) {
        this.typeImageId = typeImageId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
