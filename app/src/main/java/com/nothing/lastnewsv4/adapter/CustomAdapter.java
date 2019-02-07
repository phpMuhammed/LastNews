package com.nothing.lastnewsv4.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nothing.lastnewsv4.R;
import com.nothing.lastnewsv4.TappedActivity;
import com.nothing.lastnewsv4.database.DBHelper;
import com.nothing.lastnewsv4.fragment.LastNewsFragment;
import com.nothing.lastnewsv4.model.News;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    Activity activity;
    ArrayList<News> news;
    LayoutInflater layoutInflater;

    public CustomAdapter(Activity activity, ArrayList<News> news) {
        this.activity = activity;
        this.news = news;
        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return news.size();
    }

    @Override
    public Object getItem(int position) {
        return news.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View myview = convertView;

        if (myview == null) {
            myview = layoutInflater.inflate(R.layout.news_item_design, null);
            ImageView newsImage = myview.findViewById(R.id.news_image);
            TextView newsTitle = myview.findViewById(R.id.news_title);
            TextView newsDate = myview.findViewById(R.id.news_date);
            final Button favButton = myview.findViewById(R.id.news_fave);

            if (news.get(position).getImg() == null) {
                newsImage.setImageResource(R.drawable.ic_launcher_background);
            } else {
                //Bitmap bitmap = new Bitmap();
                // newsImage.setImageBitmap();

                byte[] theNewsImg = news.get(position).getImg();
                Bitmap bitmap = BitmapFactory.decodeByteArray(theNewsImg, 0, theNewsImg.length);
                if (theNewsImg == null || bitmap == null) {
                    newsImage.setImageResource(R.drawable.ic_launcher_background);

                } else {
                    newsImage.setImageBitmap(bitmap);

                }
                //Log.d("moh",bitmap.toString());
            }
            newsTitle.setText(news.get(position).getTitle());
            newsDate.setText(news.get(position).getDate());


            if (news.get(position).getIsFave() != 0) {
                favButton.setBackgroundResource(R.drawable.ic_favorite_black_24dp);
                favButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        favButton.setBackgroundResource(R.drawable.ic_favorite_border_black_24dp);
                        DBHelper dbHelper = new DBHelper(activity);
                        dbHelper.updateNewsWhereFave(news.get(position).getId(), 0);
                        //  favButton.setBackgroundResource(R.drawable.ic_favorite_border_black_24dp);
//                        notifyDataSetChanged();
                        //notifyDataSetChanged();
                        //        TappedActivity.refreshFragments();

                    }
                });
            } else {
                favButton.setBackgroundResource(R.drawable.ic_favorite_border_black_24dp);
                favButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        favButton.setBackgroundResource(R.drawable.ic_favorite_black_24dp);
                        DBHelper dbHelper = new DBHelper(activity);
                        dbHelper.updateNewsWhereFave(news.get(position).getId(), 1);
                        //favButton.setBackgroundResource(R.drawable.ic_favorite_black_24dp);
                        //notifyDataSetChanged();
                        //this.notifyAll();
                        //notifyDataSetChanged();

                        //     TappedActivity.refreshFragments();
                    }
                });
            }

        }
        return myview;
    }
}
