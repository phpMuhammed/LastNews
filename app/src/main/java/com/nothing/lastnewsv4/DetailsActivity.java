package com.nothing.lastnewsv4;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nothing.lastnewsv4.database.DBHelper;
import com.nothing.lastnewsv4.model.News;

import java.io.ByteArrayOutputStream;

public class DetailsActivity extends AppCompatActivity {
    ImageView detailsImageView;
    TextView detailsTime, detailsTitle, detailsDescription;

    static int x = 0;
    DBHelper database;
    static int user_id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.details_toolbar);
        setSupportActionBar(toolbar);

        database = new DBHelper(getApplicationContext());
        detailsImageView = findViewById(R.id.details_news_img);
        detailsTime = findViewById(R.id.details_news_time);
        detailsTitle = findViewById(R.id.details_news_title);
        detailsDescription = findViewById(R.id.details_news_detials);


        if (getIntent().getSerializableExtra("news") != null) {
            News news = (News) getIntent().getSerializableExtra("news");
            byte[] foodImage = news.getImg();
            Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);
            detailsImageView.setImageBitmap(bitmap);

            detailsDescription.setText(news.getDetails());

            detailsTitle.setText(news.getTitle());
            user_id = news.getId();

            detailsTime.setText(news.getDate());
            toolbar.setTitle(news.getTitle());

        } else {
            Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
        }


    }

    private byte[] imageViewToByte(ImageView imageView) {
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.return_back, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (item.getItemId()) {
            case R.id.back_arrow:
                x += 1;
                onBackPressed();
                return true;
            case R.id.delete_post:
                onDeleteAlertDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onDeleteAlertDialog() {

        AlertDialog alertDialog = new AlertDialog.Builder(DetailsActivity.this).create();
        alertDialog.setTitle("Delete");
        alertDialog.setMessage("Alert message to be shown");

        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        int user_id = DetailsActivity.user_id;
                        if (database.deleteNews(user_id)) {
                            Intent intent = new Intent(DetailsActivity.this, TappedActivity.class);
                            startActivity(intent);
                            finish();
                            Toast.makeText(DetailsActivity.this, "Deleted Successfully ...", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(DetailsActivity.this, "nothing deleted ", Toast.LENGTH_SHORT).show();
                        }

                        dialog.dismiss();
//                        test = true;
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
//                test = false;
            }
        });
        alertDialog.show();
        // return test;
    }

}
