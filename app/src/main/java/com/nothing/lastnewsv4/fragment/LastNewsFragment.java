package com.nothing.lastnewsv4.fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
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
    public static ListView listView;
     EditText searchEd = TappedActivity.search;

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
        // searchEd = root.findViewById(R.id.search_news);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        showLastNews();

        searchEd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                String constraint = s.toString();
                ArrayList<News> newsArrayList =dbHelper.getAllNewsWithFillter(constraint);
                //customAdapter.getFilter().filter(constraint);
                customAdapter = new CustomAdapter(getActivity(),newsArrayList);
                listView.setAdapter(customAdapter);

            }
        });

    }

    public void showLastNews() {

        dbHelper = new DBHelper(getActivity());
        newsArrayList = dbHelper.getAllNews();
        customAdapter = new CustomAdapter(getActivity(), newsArrayList);
        listView.setTextFilterEnabled(true);
        listView.setAdapter(customAdapter);

        //  TappedActivity.refreshFragments();


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
