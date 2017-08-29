package com.example.oneapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oneapp.gson.Scan;
import com.google.gson.Gson;

import java.net.URL;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class BindingActivity extends AppCompatActivity {

    public static final String JSON = "application/json; charset=utf-8";
    public String show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binding);

        Button button = (Button) findViewById(R.id.back_binding);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
        final String shopName = pref.getString("shopName", "");
        final String qrcodeKey = pref.getString("qrcodeKey", "");
        final String qrcodeUrl = pref.getString("qrcodeUrl", "");

        new Thread(new Runnable() {
            public void run() {
                TextView showname = (TextView) findViewById(R.id.showName_binding);
                TextView qrcodekey = (TextView) findViewById(R.id.qrcodeKey_binding);
                showname.setText(shopName);
                qrcodekey.setText(qrcodeKey);
            }
        }).start();

        Button binding = (Button) findViewById(R.id.button_binding);
        binding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            URL url = new URL("https://wxapptest.saofu.cn/wxapp/merchant/qrcode/bind_code");

                            OkHttpClient client = new OkHttpClient();

                            String jsonString = "{\"flag\":0,\"qrcodeUrl\":\"" + qrcodeUrl +"\"}";

                            RequestBody formBody = RequestBody.create(MediaType.parse(JSON), jsonString);

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
                                    show(str);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }

    private void show(String post) {
        Gson gson = new Gson();
        Scan scan = gson.fromJson(post, Scan.class);
        String success = scan.attach.getSuccess();
        if (success.equals("false")) {
            Looper.prepare();
            Toast.makeText(BindingActivity.this, scan.attach.getReason(), Toast.LENGTH_SHORT).show();
        }
        finish();
    }
}
