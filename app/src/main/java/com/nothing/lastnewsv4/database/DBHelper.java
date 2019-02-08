package com.nothing.lastnewsv4.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.nothing.lastnewsv4.model.News;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "news";
    SQLiteDatabase sqLiteDatabase;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
        sqLiteDatabase = getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(News.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(" drop table if exists " + News.TABLE_NAME);
        onCreate(db);
    }

    public boolean insertNews(String title, String details, String date, int isFave, byte[] img) {


        ContentValues contentValues = new ContentValues();
        contentValues.put(News.COL_TITLE, title);
        contentValues.put(News.COL_DETAILS, details);

        contentValues.put(News.COL_DATE, date);
        contentValues.put(News.COL_IS_FAVE, isFave);

        contentValues.put(News.COL_IMAGE, img);
        //notifyDataSetChanged();
        return sqLiteDatabase.insert(News.TABLE_NAME, null, contentValues) > 0;
    }

    public boolean updateNews(int oldId, String title, String details, String date, int isFave, byte[] img) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(News.COL_TITLE, title);
        contentValues.put(News.COL_DETAILS, details);

        contentValues.put(News.COL_DATE, date);
        contentValues.put(News.COL_IS_FAVE, isFave);

        //contentValues.put(News.COL_IMAGE, img);
        return sqLiteDatabase.update(News.TABLE_NAME, contentValues, "id=?", new String[]{String.valueOf(oldId)}) > 0;
    }

    public boolean updateNewsWhereFave(int oldId, int isFave) {

        ContentValues contentValues = new ContentValues();

        contentValues.put(News.COL_IS_FAVE, isFave);

        return sqLiteDatabase.update(News.TABLE_NAME, contentValues, "id=?", new String[]{String.valueOf(oldId)}) > 0;
    }

    public ArrayList<News> getAllNews() {

        String sqlQuery = "select * from " + News.TABLE_NAME + " order by id desc";
        return returnNewsFromDataBase(sqlQuery);
    }
    public ArrayList<News> getAllNewsWithFillter(String constraint){
//        String sqlQuery = "select * from " + News.TABLE_NAME +
//                " where " + News.COL_TITLE + " like  %"+constraint+"% ,OR " +
//                News.COL_DETAILS + " like %"+constraint+"%";

        Cursor cursor = sqLiteDatabase.rawQuery("select * from " + News.TABLE_NAME +
                " where " + News.COL_DETAILS + " like ? OR " +
                News.COL_TITLE + " like ?", new String[]{"%" + constraint + "%", "%" + constraint + "%"});
        Log.d("moh","test execute");
        return returnNewsFromDataBase(cursor);

    }
    public ArrayList<News> getAllNewsWithFillter(String constraint,boolean fave){
//        String sqlQuery = "select * from " + News.TABLE_NAME +
//                " where " + News.COL_TITLE + " like  %"+constraint+"% ,OR " +
//                News.COL_DETAILS + " like %"+constraint+"%";

        Cursor cursor = sqLiteDatabase.rawQuery("select * from " + News.TABLE_NAME +
                " where isfave = 1 and " + News.COL_DETAILS + " like ? OR " +
                News.COL_TITLE + " like ?", new String[]{"%" + constraint + "%", "%" + constraint + "%"});
        return returnNewsFromDataBase(cursor);

    }

    private ArrayList<News> returnNewsFromDataBase(Cursor cursor) {
        ArrayList<News> newsArrayList = new ArrayList<>();
        //Cursor cursor = sqLiteDatabase.rawQuery(sqlQuery, null);
        if (cursor.moveToFirst()) {
            do {
                News news = new News();
                news.setId(cursor.getInt(cursor.getColumnIndex(News.COL_ID)));
                news.setDate(cursor.getString(cursor.getColumnIndex(News.COL_DATE)));
                news.setDetails(cursor.getString(cursor.getColumnIndex(News.COL_DETAILS)));
                news.setTitle(cursor.getString(cursor.getColumnIndex(News.COL_TITLE)));
                news.setIsFave(cursor.getInt(cursor.getColumnIndex(News.COL_IS_FAVE)));
                news.setImg(cursor.getBlob(cursor.getColumnIndex(News.COL_IMAGE)));

                newsArrayList.add(news);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return newsArrayList;
    }

    /**
     * show favorit news
     */

    public ArrayList<News> getFaveNews() {

        String sqlQuery = "select * from " + News.TABLE_NAME + " where isfave = 1 order by id desc";
        return returnNewsFromDataBase(sqlQuery);
    }

    public ArrayList<News> returnNewsFromDataBase(String sqlQuery) {
        ArrayList<News> newsArrayList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sqlQuery, null);
        if (cursor.moveToFirst()) {
            do {
                News news = new News();
                news.setId(cursor.getInt(cursor.getColumnIndex(News.COL_ID)));
                news.setDate(cursor.getString(cursor.getColumnIndex(News.COL_DATE)));
                news.setDetails(cursor.getString(cursor.getColumnIndex(News.COL_DETAILS)));
                news.setTitle(cursor.getString(cursor.getColumnIndex(News.COL_TITLE)));
                news.setIsFave(cursor.getInt(cursor.getColumnIndex(News.COL_IS_FAVE)));
                news.setImg(cursor.getBlob(cursor.getColumnIndex(News.COL_IMAGE)));

                newsArrayList.add(news);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return newsArrayList;

    }

}
