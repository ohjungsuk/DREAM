package com.example.dream;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.dream.ApplicationClass.donor_count;
import static com.example.dream.ApplicationClass.mode;
import static com.example.dream.ApplicationClass.u_id;

public class HomeActivity extends AppCompatActivity {

    ImageView img_call_blood_donation,img_requestlist,img_check_my_donationInfo,img_rewardService,img_caution;
    TextView home_tv_my_donor_count,home_tv_myid,txtview,t3;
    ImageButton imgbtn_menu;

    LinearLayout linear_calllist,linear_list,linear_gift,linear_check,linear1,linear2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setUp();
        activityMover();
        System.out.println("Home" + mode);
//        Intent intent = getIntent();
//
//        //String mode = String.valueOf(intent.getExtras().getInt("mode"));
//
//        Toast.makeText(HomeActivity.this, mode + u_id, Toast.LENGTH_SHORT).show();
//
//        home_tv_my_donor_count.setText(String.valueOf(intent.getExtras().getInt("donor_count"))+" ");
        if (Integer.valueOf(mode) == 1){//헌혈자 모드
            if (linear_calllist.getVisibility()==View.VISIBLE){
                linear_calllist.setVisibility(View.GONE);
            }
            if (linear_gift.getVisibility()==View.GONE){
                linear_gift.setVisibility(View.VISIBLE);
            }
            txtview.setText("헌");
            t3.setText("헌");
        }else { //수혈자 모드
            if (linear_calllist.getVisibility()==View.GONE){
                linear_calllist.setVisibility(View.VISIBLE);
            }
            if (linear_gift.getVisibility()==View.VISIBLE){
                linear_gift.setVisibility(View.GONE);
            }
            txtview.setText("수");
            t3.setText("수");
        }
        home_tv_myid.setText(u_id);
        home_tv_my_donor_count.setText(donor_count);
    }

    public void setUp(){
        linear_calllist = (LinearLayout)findViewById(R.id.linear_calllist);
        linear_list = (LinearLayout)findViewById(R.id.linear_list);
        linear_gift = (LinearLayout)findViewById(R.id.linear_gift);
        linear_check = (LinearLayout)findViewById(R.id.linear_check);
        linear1 = (LinearLayout)findViewById(R.id.linear1);
        linear2 = (LinearLayout)findViewById(R.id.linear2);
        home_tv_myid = (TextView)findViewById(R.id.home_tv_myid);
        txtview = (TextView)findViewById(R.id.txtview);
        t3 = (TextView)findViewById(R.id.t3);
        home_tv_my_donor_count = (TextView)findViewById(R.id.home_tv_my_donor_count);
        img_call_blood_donation = (ImageView)findViewById(R.id.home_call_blood_donation);
        img_requestlist = (ImageView)findViewById(R.id.home_requestlist);
        img_check_my_donationInfo = (ImageView)findViewById(R.id.home_check_my_donationInfo);
        img_rewardService = (ImageView)findViewById(R.id.home_rewardService);

        imgbtn_menu = (ImageButton)findViewById(R.id.home_imgbtn_menu);
    }

    public void activityMover(){
        imgbtn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,MenuActivity.class);
                startActivity(intent);
                finish();
            }
        });

        img_call_blood_donation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,WriteRequestList.class);
                startActivity(intent);
                finish();
            }
        });

        img_rewardService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,PresentActivity.class);
                startActivity(intent);
                finish();
            }
        });

        img_requestlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,RequestList.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(HomeActivity.this)
                .setMessage("앱을 종료하시겠습니까?")
                .setPositiveButton("종료", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).show();
    }
}