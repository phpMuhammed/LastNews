package com.nothing.lastnewsv4;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.view.ViewPager;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.nothing.lastnewsv4.adapter.SectionsPagerAdapter;
import com.nothing.lastnewsv4.database.DBHelper;


public class TappedActivity extends AppCompatActivity {
    static boolean x = true;
    private static SectionsPagerAdapter mSectionsPagerAdapter;
    static final int SECOND_ACTIVITY_REQUEST_CODE = 0;
    int opened = 0;
    DBHelper dbHelper;
    public static EditText search;
    private static ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tapped);
        search = findViewById(R.id.search_news);

        Toolbar toolbar = (Toolbar) findViewById(R.id.own_tool_bar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        dbHelper = new DBHelper(getApplicationContext());
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent pickContactIntent = new Intent(TappedActivity.this, AddNewsActivity.class);
                startActivityForResult(pickContactIntent, SECOND_ACTIVITY_REQUEST_CODE);
            }

        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tapped, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

        } else if (id == R.id.action_search) {

            search.setVisibility(View.VISIBLE);
            search.setVisibility(View.INVISIBLE);
            if(x){
                search.setVisibility(View.VISIBLE);
                x = false;
            }else{
                search.setVisibility(View.INVISIBLE);
                x = true;
            }
//
//            x = true;
//            item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//
//                @Override
//
//                public boolean onMenuItemClick(MenuItem item) {
//                    search.setVisibility(View.INVISIBLE);
//
//                    return true;
//                }
//            });
//            if (x == true) {
//                search.setVisibility(View.VISIBLE);
//            }

            return true;

        }

        return super.onOptionsItemSelected(item);
    }


    public static void refreshFragments() {
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }


}
