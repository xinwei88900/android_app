package com.example.gestiondesdpenses.database;

public class TypeBean {

    int id;
    String type_name;
    int imageID;
    int select_imageID;
    int kind; //outcome:0 , income:-1


    public TypeBean() {
    }

    public TypeBean(int id, String type_name, int imageID, int select_imageID, int kind) {
        this.id = id;
        this.type_name = type_name;
        this.imageID = imageID;
        this.select_imageID = select_imageID;
        this.kind = kind;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public int getSelect_imageID() {
        return select_imageID;
    }

    public void setSelect_imageID(int select_imageID) {
        this.select_imageID = select_imageID;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }
}

