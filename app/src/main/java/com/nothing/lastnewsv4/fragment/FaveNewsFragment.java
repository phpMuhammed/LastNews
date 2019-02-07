package com.nothing.lastnewsv4.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.nothing.lastnewsv4.DetailsActivity;
import com.nothing.lastnewsv4.R;
import com.nothing.lastnewsv4.adapter.CustomAdapter;
import com.nothing.lastnewsv4.database.DBHelper;
import com.nothing.lastnewsv4.model.News;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FaveNewsFragment extends Fragment {
    DBHelper dbHelper;
    ArrayList<News> newsArrayList;
    CustomAdapter customAdapter;
    ListView listView ;
    public FaveNewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_fave_news, container, false);
         listView = root.findViewById(R.id.fave_list_view);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        showFave();
    }

    public void showFave(){
        dbHelper = new DBHelper(getActivity());
        newsArrayList = dbHelper.getFaveNews();
        customAdapter = new CustomAdapter(getActivity(), newsArrayList);
        listView.setAdapter(customAdapter);


        // Log.d("eee",newsArrayList.toString());

        //  dbHelper = new DBHelper(getContext());


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
