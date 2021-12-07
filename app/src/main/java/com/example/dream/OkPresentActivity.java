package com.example.dream;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dream.Interfaces.RewardActivityView;
import com.example.dream.Models.RewardResponse;
import com.example.dream.Services.RewardService;

import static com.example.dream.ApplicationClass.donor_count;
import static com.example.dream.ApplicationClass.u_id;

public class OkPresentActivity extends AppCompatActivity implements RewardActivityView {

    TextView ok_tv_present;
    EditText ok_edt_address;
    TextView ok_tv_my_count,ok_tv_present_count;
    private String present_count;
    Button ok_btn_done,ok_btn_cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_present);
        setUp();
        activityMover();

        Intent intent = getIntent();

        ok_tv_present.setText(intent.getStringExtra("present"));
        present_count = intent.getStringExtra("count");
        ok_tv_present_count.setText(present_count);
        ok_tv_my_count.setText(donor_count);
    }

    public void setUp(){
        ok_tv_present = (TextView)findViewById(R.id.ok_tv_present);
        ok_edt_address = (EditText)findViewById(R.id.ok_edt_address);
        ok_tv_my_count = (TextView)findViewById(R.id.ok_tv_my_count);
        ok_tv_present_count = (TextView)findViewById(R.id.ok_tv_present_count);
        ok_btn_done = (Button)findViewById(R.id.ok_btn_done);
        ok_btn_cancel = (Button)findViewById(R.id.ok_btn_cancel);
    }

    public void activityMover(){
        ok_btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OkPresentActivity.this,PresentActivity.class);
                startActivity(intent);
                finish();
            }
        });
        ok_btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.valueOf(present_count)<Integer.valueOf(donor_count)){
                    //API 호출
                    new RewardService(OkPresentActivity.this).getRewardS(
                            u_id,
                            ok_edt_address.getText().toString(),
                            ok_tv_present.getText().toString(),
                            Integer.valueOf(present_count)
                    );
                }
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(OkPresentActivity.this,PresentActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void validateFailure() {
        Toast.makeText(OkPresentActivity.this, "보상받기 실패", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void validateSuccess(RewardResponse rewardResponse) {
        System.out.println("OK석세스");
        Toast.makeText(OkPresentActivity.this, "보상받기 완료", Toast.LENGTH_SHORT).show();
        donor_count = String.valueOf(rewardResponse.getData().getDonor_count());
        Intent intent = new Intent(OkPresentActivity.this,PresentActivity.class);
        startActivity(intent);
        finish();
    }
}