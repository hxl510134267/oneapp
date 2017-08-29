package com.example.oneapp;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oneapp.gson.Main;
import com.google.gson.Gson;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    public Main main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CircleImageView circleImageView = (CircleImageView) findViewById(R.id.image_main);
        Drawable drawableColor = new ColorDrawable(Color.parseColor("#03a9f5"));
        circleImageView.setImageDrawable(drawableColor);

        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new
                    String[]{ Manifest.permission.CAMERA }, 1);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
        String post = pref.getString("main", "");
        Gson gson = new Gson();
        main = gson.fromJson(post, Main.class);
        String code = main.getCode();
        if (!(code.equals("0"))) {
            Toast.makeText(MainActivity.this, main.getMsg(), Toast.LENGTH_SHORT).show();
        } else {
            new Thread( new Runnable(){
                public void run() {
                    String attach = main.getAttach();
                    TextView titlemain = (TextView) findViewById(R.id.title_main);
                    titlemain.setText(attach);
                }
            }).start();
        }

        ImageView imageView = (ImageView) findViewById(R.id.image_main);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ScanActivity.class);
                startActivity(intent);
            }
        });

        TextView textmain = (TextView) findViewById(R.id.text_main);
        textmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RecordActivity.class);
                startActivity(intent);
            }
        });

    }

}
