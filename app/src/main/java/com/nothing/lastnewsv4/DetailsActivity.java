package com.nothing.lastnewsv4;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nothing.lastnewsv4.model.News;

import java.io.ByteArrayOutputStream;

public class DetailsActivity extends AppCompatActivity {
    ImageView detailsImageView;
    TextView detailsTime, detailsTitle, detailsDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.nav_news_title);
        setSupportActionBar(toolbar);


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

            detailsTime.setText(news.getDate());
            toolbar.setTitle(news.getTitle());
            
        }else{
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.back_arrow) {
            item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    onBackPressed();
                    return true;
                }
            });
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
