package com.example.dream;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.dream.ApplicationClass.donor_count;

public class PresentActivity extends AppCompatActivity {
    Context context;
    ImageButton present_imgbtn_back;
    TextView present_tv_my_donor_count;
    ImageButton present_cookie,present_bear,present_ticket,present_perfume,present_headphone,present_airpod;
    Button present_btn_1,present_btn_2,present_btn_3,present_btn_4,present_btn_5,present_btn_6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_present);

        setUp();
        activityMover();
        present_tv_my_donor_count.setText(donor_count);


        if (2 < Integer.valueOf(donor_count)){
            present_btn_1.setEnabled(true);
            present_btn_1.setBackgroundColor(Color.parseColor("#E85398"));
        }
        if (4 < Integer.valueOf(donor_count)){
            present_btn_2.setEnabled(true);
            present_btn_2.setBackgroundColor(Color.parseColor("#E85398"));
        }
        if (6 < Integer.valueOf(donor_count)){
            present_btn_3.setEnabled(true);
            present_btn_3.setBackgroundColor(Color.parseColor("#E85398"));
        }
        if (9 < Integer.valueOf(donor_count)){
            present_btn_4.setEnabled(true);
            present_btn_4.setBackgroundColor(Color.parseColor("#E85398"));
        }
        if (14 < Integer.valueOf(donor_count)){
            present_btn_5.setEnabled(true);
            present_btn_5.setBackgroundColor(Color.parseColor("#E85398"));
        }
        if (19 < Integer.valueOf(donor_count)){
            present_btn_6.setEnabled(true);
            present_btn_6.setBackgroundColor(Color.parseColor("#E85398"));
        }
    }

    public void setUp(){
        present_tv_my_donor_count = (TextView)findViewById(R.id.present_tv_my_donor_count);
        present_imgbtn_back = (ImageButton)findViewById(R.id.present_imgbtn_back);
        present_cookie = (ImageButton) findViewById(R.id.present_cookie);
        present_bear = (ImageButton)findViewById(R.id.present_bear);
        present_ticket = (ImageButton)findViewById(R.id.present_ticket);
        present_perfume = (ImageButton)findViewById(R.id.present_perfume);
        present_headphone = (ImageButton)findViewById(R.id.present_headphone);
        present_airpod = (ImageButton)findViewById(R.id.present_airpod);
        present_btn_1 = (Button) findViewById(R.id.present_btn_1);
        present_btn_2 = (Button) findViewById(R.id.present_btn_2);
        present_btn_3 = (Button) findViewById(R.id.present_btn_3);
        present_btn_4 = (Button) findViewById(R.id.present_btn_4);
        present_btn_5 = (Button) findViewById(R.id.present_btn_5);
        present_btn_6 = (Button) findViewById(R.id.present_btn_6);
    }
    public void activityMover(){
        present_btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PresentActivity.this,OkPresentActivity.class);
                intent.putExtra("present","수제 쿠키");
                intent.putExtra("count","3");
                startActivity(intent);
                finish();
            }
        });
        present_btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PresentActivity.this,OkPresentActivity.class);
                intent.putExtra("present","곰 인형");
                intent.putExtra("count","5");
                startActivity(intent);
                finish();
            }
        });
        present_btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PresentActivity.this,OkPresentActivity.class);
                intent.putExtra("present","영화관람권 2매");
                intent.putExtra("count","7");
                startActivity(intent);
                finish();
            }
        });
        present_btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PresentActivity.this,OkPresentActivity.class);
                intent.putExtra("present","향수");
                intent.putExtra("count","10");
                startActivity(intent);
                finish();
            }
        });
        present_btn_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PresentActivity.this,OkPresentActivity.class);
                intent.putExtra("present","헤드폰");
                intent.putExtra("count","15");
                startActivity(intent);
                finish();
            }
        });
        present_btn_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PresentActivity.this,OkPresentActivity.class);
                intent.putExtra("present","에어팟");
                intent.putExtra("count","20");
                startActivity(intent);
                finish();
            }
        });
        present_imgbtn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PresentActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(PresentActivity.this,HomeActivity.class);
        startActivity(intent);
        finish();
    }
}