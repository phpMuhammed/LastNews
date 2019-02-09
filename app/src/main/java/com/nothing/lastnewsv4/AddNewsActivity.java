package com.nothing.lastnewsv4;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.nothing.lastnewsv4.database.DBHelper;
import com.nothing.lastnewsv4.fragment.LastNewsFragment;
import com.nothing.lastnewsv4.model.News;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Calendar;

public class AddNewsActivity extends AppCompatActivity implements IPickResult {
    DBHelper dbHelper;
    ImageView imgButn;
    Button addBtn;
    EditText titleEd;
    EditText detailsEd;
    boolean clicked = false;

    static final int RESULT_CANCELED = 0;
    byte[] img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_news);


    }

    @Override
    protected void onStart() {
        super.onStart();

        imgButn = findViewById(R.id.add_img_from_gallary_add);
        titleEd = findViewById(R.id.add_title);
        detailsEd = findViewById(R.id.add_desc);
        addBtn = findViewById(R.id.addBtn);

        imgButn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PickImageDialog.build(new PickSetup()).show(AddNewsActivity.this);
                clicked = true;
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("mohammed", titleEd.getText().toString());
                if (!titleEd.getText().toString().equals("") && !detailsEd.getText().toString().equals("")) {
                    dbHelper = new DBHelper(AddNewsActivity.this);


                    String title = titleEd.getText().toString();
                    String details = detailsEd.getText().toString();
                    Calendar c = Calendar.getInstance();

                    String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());

                    int isFave = 0;


                    if (clicked == true) {
                        img = imageViewToByte(imgButn);

                    } else {
                        img = imageViewToByte(imgButn, 0);
                    }
                    if (dbHelper.insertNews(title, details, mydate, isFave, img)) {

                        setResult(RESULT_OK);
                        finish();
                    } else {
                        Toast.makeText(AddNewsActivity.this, "error", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    animate(Techniques.Wave, 400, 5, titleEd);
                    animate(Techniques.StandUp, 400, 5, imgButn);
                    animate(Techniques.Swing, 400, 5, detailsEd);

                }
            }
        });

    }

    public static void animate(Techniques techniques, long duration, int repeate, View target) {
        YoYo.with(techniques)
                .duration(duration)
                .repeat(repeate)
                .playOn(target);
    }

    @Override
    public void onPickResult(PickResult pickResult) {
        if (pickResult.getError() == null) {

            imgButn.setImageBitmap(pickResult.getBitmap());

        } else {
            //Handle possible errors
            //TODO: do what you have to do with r.getError();
            Toast.makeText(this, pickResult.getError().getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private byte[] imageViewToByte(ImageView imageView) {
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;

    }

    private byte[] imageViewToByte(ImageView imageView, int t) {
        imageView.buildDrawingCache();
        Bitmap bmap = imageView.getDrawingCache();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

}