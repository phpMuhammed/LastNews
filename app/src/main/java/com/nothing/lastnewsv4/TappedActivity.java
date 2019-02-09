package com.nothing.lastnewsv4;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;

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
import com.nothing.lastnewsv4.fragment.FaveNewsFragment;
import com.nothing.lastnewsv4.fragment.LastNewsFragment;


public class TappedActivity extends AppCompatActivity {

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

//                Snackbar.make(view, "click add to add new news", Snackbar.LENGTH_LONG)
//                        .setAction("add", new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
////                            Intent intent = new Intent(TappedActivity.this,AddNewsActivity.class);
////                            startActivity(intent);

                Intent pickContactIntent = new Intent(TappedActivity.this, AddNewsActivity.class);
                //    pickContactIntent.setType(Phone.CONTENT_TYPE); // Show user only contacts w/ phone numbers
                startActivityForResult(pickContactIntent, SECOND_ACTIVITY_REQUEST_CODE);
            }
//                        }).show();
//            }
        });


        //   onActivityResult(TappedActivity.PICK_CONTACT_REQUEST, 1, getIntent());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tapped, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

        } else if (id == R.id.action_search) {

            item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    int x = opened++;
                    if (x % 2 == 0) {
                        search.setVisibility(View.INVISIBLE);
                    } else {
                        search.setVisibility(View.VISIBLE);
                    }

                    return true;
                }
            });

        }

        return true;
    }


    public static void refreshFragments() {
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }


}
