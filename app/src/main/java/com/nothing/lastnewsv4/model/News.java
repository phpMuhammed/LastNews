package com.nothing.lastnewsv4.model;

import java.io.Serializable;

public class News implements Serializable {

    public static final String COL_ID = "id";
    public static final String COL_TITLE = "title";
    public static final String COL_DETAILS = "details";
    public static final String COL_DATE = "date";
    public static final String COL_IMAGE = "img";
    public static final String COL_IS_FAVE = "isfave";

    public static final String TABLE_NAME = "lastnews";


    public static final String CREATE_TABLE = "" +
            "create table lastnews( id integer primary key autoincrement, title varchar2 ," +
            " details text , date varchar , img blob , isfave integer )";


    private int id;
    private String title;
    private String details;
    private String date;
    private byte[] img;
    private int isFave;

    public News(int id, String title, String details, String date, byte[] img, int isFave) {
        this.id = id;
        this.title = title;
        this.details = details;
        this.date = date;
        this.img = img;
        this.isFave = isFave;
    }

    public News(String title, String details, String date, byte[] img, int isFave) {
        this.title = title;
        this.details = details;
        this.date = date;
        this.img = img;
        this.isFave = isFave;
    }

    public News() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public int getIsFave() {
        return isFave;
    }

    public void setIsFave(int isFave) {
        this.isFave = isFave;
    }
}
