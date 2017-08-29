package com.example.oneapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.coderchoy.barcodereaderview.decode.BarcodeReaderView;
import com.example.oneapp.gson.Scan;
import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ScanActivity extends AppCompatActivity implements BarcodeReaderView.OnBarcodeReadListener {

    public static final String JSON = "application/json; charset=utf-8";

    private BarcodeReaderView scan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        scan = (BarcodeReaderView) findViewById(R.id.scan);
        scan.setOnBarcodeReadListener(this);

        Button button = (Button) findViewById(R.id.back_scan);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        List<BarcodeFormat> barcodeFormats = new ArrayList<>();
        barcodeFormats.add(BarcodeFormat.QR_CODE);
        barcodeFormats.add(BarcodeFormat.CODE_128);
        scan.setDecodeFormats(barcodeFormats);
    }

    @Override
    protected void onResume() {
        super.onResume();
        scan.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        scan.onPause();
    }

    @Override
    public void onCameraNotFound() {
        Toast.makeText(this, "Camera Not Found!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCameraInitError() {
        Toast.makeText(this, "Camera Init Error!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBarcodeRead(Result result, Bitmap barcode, float scaleFactor) {
        final String scanning = result.getText();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("https://wxapptest.saofu.cn/wxapp/merchant/qrcode/get_code_info");

                    OkHttpClient client = new OkHttpClient();

                    String jsonString = "{\"qrcodeUrl\":\"" + scanning +"\"}";

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

    private void show(String post) {
        Gson gson = new Gson();
        Scan scan = gson.fromJson(post, Scan.class);
        String success = scan.attach.getSuccess();
        String reason = scan.attach.getReason();
        if (success.equals("false")) {
            Looper.prepare();
            Toast.makeText(ScanActivity.this, reason, Toast.LENGTH_SHORT).show();
            finish();
        } else {
            String hasBind = scan.attach.result.codeInfo.getHasBind();
            SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
            String shopName = scan.attach.result.codeInfo.getShopName();
            String qrcodeKey = scan.attach.result.codeInfo.getQrcodeKey();
            String qrcodeUrl = scan.attach.result.codeInfo.getQrcodeUrl();
            editor.putString("shopName", shopName);
            editor.putString("qrcodeKey", qrcodeKey);
            editor.putString("qrcodeUrl", qrcodeUrl);
            editor.apply();
            if (hasBind.equals("false")) {
                Intent intent = new Intent(ScanActivity.this, BindingActivity.class);
                startActivity(intent);
                finish();
            } else {
                Intent intent = new Intent(ScanActivity.this, UnBindingActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }

}
