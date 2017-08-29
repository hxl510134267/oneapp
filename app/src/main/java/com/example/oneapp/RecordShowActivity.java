package com.example.oneapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oneapp.gson.Record;
import com.google.gson.Gson;

public class RecordShowActivity extends AppCompatActivity {

    private LinearLayout recordshow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        recordshow = (LinearLayout) findViewById(R.id.show_record);
        recordshow.removeAllViews();

        Button button = (Button) findViewById(R.id.back_record);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
        String post = pref.getString("record", "");

        Gson gson = new Gson();
        final Record record = gson.fromJson(post, Record.class);
        String code = record.getCode();
        if (!(code.equals("0"))) {
            Toast.makeText(this, record.getMsg(), Toast.LENGTH_SHORT).show();
        } else {
            for (Record.Attach.LList list : record.attach.list) {
                View view = LayoutInflater.from(this).inflate(R.layout.activity_record_1,
                        recordshow, false);
                TextView showname = (TextView) view.findViewById(R.id.showName_record);
                TextView bindfun = (TextView) view.findViewById(R.id.bindFun_record);
                TextView bindtime = (TextView) view.findViewById(R.id.bindTime_record);
                showname.setText(list.getShopName());
                bindfun.setText(list.getBindFun());
                bindtime.setText(list.bindTime);
                recordshow.addView(view);
            }
        }
    }
}
