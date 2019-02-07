package com.nothing.lastnewsv4.fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.nothing.lastnewsv4.DetailsActivity;
import com.nothing.lastnewsv4.R;
import com.nothing.lastnewsv4.TappedActivity;
import com.nothing.lastnewsv4.adapter.CustomAdapter;
import com.nothing.lastnewsv4.database.DBHelper;
import com.nothing.lastnewsv4.model.News;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class LastNewsFragment extends Fragment {

    public static CustomAdapter customAdapter;
    DBHelper dbHelper;
    ArrayList<News> newsArrayList;
    ListView listView;

    public LastNewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_last_news, container, false);
        listView = root.findViewById(R.id.news_list_view);
     //   showLastNews();

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        showLastNews();
    }

    public void showLastNews() {

        dbHelper = new DBHelper(getActivity());
        newsArrayList = dbHelper.getAllNews();
        customAdapter = new CustomAdapter(getActivity(), newsArrayList);

        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra("news", newsArrayList.get(position));
                startActivity(intent);
            }
        });
    }
}
