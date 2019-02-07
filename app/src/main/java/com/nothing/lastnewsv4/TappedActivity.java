package com.nothing.lastnewsv4;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.nothing.lastnewsv4.adapter.SectionsPagerAdapter;
import com.nothing.lastnewsv4.database.DBHelper;
import com.nothing.lastnewsv4.model.News;

public class TappedActivity extends AppCompatActivity {

    private static SectionsPagerAdapter mSectionsPagerAdapter;
    static final int SECOND_ACTIVITY_REQUEST_CODE = 0;
//    DBHelper dbHelper = new DBHelper(TappedActivity.this,DBHelper.DB_NAME);

    private static ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tapped);

        Toolbar toolbar = (Toolbar) findViewById(R.id.own_tool_bar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
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

                Snackbar.make(view, "click add to add new news", Snackbar.LENGTH_LONG)
                        .setAction("add", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
//                            Intent intent = new Intent(TappedActivity.this,AddNewsActivity.class);
//                            startActivity(intent);

                                Intent pickContactIntent = new Intent(TappedActivity.this, AddNewsActivity.class);
                                //    pickContactIntent.setType(Phone.CONTENT_TYPE); // Show user only contacts w/ phone numbers
                                startActivityForResult(pickContactIntent, SECOND_ACTIVITY_REQUEST_CODE);
                            }
                        }).show();
            }
        });


     //   onActivityResult(TappedActivity.PICK_CONTACT_REQUEST, 1, getIntent());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        if (resultCode == RESULT_OK){
//            Fragment fr = getSupportFragmentManager().findFragmentById(R.id.last_news_fragment);
//            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//            ft.replace(R.id.last_news_fragment,fr);
//            ft.commit();
//            ft.addToBackStack(null);
//
//            mSectionsPagerAdapter.notifyDataSetChanged();
//            Toast.makeText(this, "result toast ok", Toast.LENGTH_SHORT).show();
//
//
//        }

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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public static void refreshFragments(){
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }



}
