package com.example.dream;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    ImageButton img_back;
    Button btn_modify_myinfo;
    Button btn_modify_mypw;

    TextView txt_myid;
    TextView txt_mynickname;
    TextView txt_mybloodtype;
    TextView txt_myarea;
    CheckBox checkbox_push_alarm;
    CheckBox checkbox_requestlist_alarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        setUp();
        activityMover();
    }

    public void setUp(){
        img_back = (ImageButton)findViewById(R.id.menu_imgbtn_back);
        btn_modify_myinfo = (Button)findViewById(R.id.menu_btn_modify_myinfo);
        btn_modify_mypw = (Button)findViewById(R.id.menu_btn_modify_mypw);
        txt_myid = (TextView) findViewById(R.id.menu_txt_myid);
        txt_mynickname = (TextView)findViewById(R.id.menu_txt_mynickname);
        txt_mybloodtype = (TextView)findViewById(R.id.menu_txt_mybloodtype);
        txt_myarea = (TextView)findViewById(R.id.menu_txt_myarea);
        checkbox_push_alarm = (CheckBox)findViewById(R.id.menu_checkbox_push_alarm);
        checkbox_requestlist_alarm = (CheckBox)findViewById(R.id.menu_checkbox_requestlist_alarm);
    }

    public void activityMover(){
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MenuActivity.this,HomeActivity.class);
        startActivity(intent);
        finish();
    }

}