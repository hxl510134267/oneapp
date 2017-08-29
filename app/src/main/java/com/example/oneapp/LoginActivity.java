package com.example.oneapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.net.URL;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("https://wxapptest.saofu.cn/wxapp/merchant/qrcode/get_shop_name");

                    OkHttpClient client = new OkHttpClient();
                    RequestBody formBody = new FormBody.Builder()
                            .build();

                    Request request = new Request.Builder()
                            .url(url)
                            .addHeader("WXAPP-MERCHANT-TOKEN", "ec83b499-c12f-4102-a559-9f9b8f8833c3")
                            .addHeader("WXAPP-MERCHANT-DEVICE-ID", "26020300115254")
                            .addHeader("WXAPP-MERCHANT-SHOP-SERIAL", "10549840601068216320")
                            .addHeader("WXAPP-MERCHANT-SHOP-BRANCH-ID", "1516662985")
                            .post(formBody)
                            .build();

                    Response response = client.newCall(request).execute();
                    String str = response.body().string();
                    show(str.toString());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void show(String post) {
        SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
        editor.putString("main", post);
        editor.apply();
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
