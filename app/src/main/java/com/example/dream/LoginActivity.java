package com.example.dream;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dream.ApplicationClass;
import com.example.dream.Interfaces.LoginActivityView;
import com.example.dream.Models.LoginResponse;
import com.example.dream.Services.LoginService;

import static com.example.dream.ApplicationClass.b_type;
import static com.example.dream.ApplicationClass.donor_count;
import static com.example.dream.ApplicationClass.mode;
import static com.example.dream.ApplicationClass.u_id;

public class LoginActivity extends AppCompatActivity implements LoginActivityView {

    EditText main_edtTv_id;
    EditText main_edtTv_pw;
    Button main_btn_signIn;
    Button main_btn_makeId;
    CheckBox main_cBox_donor;
    CheckBox main_cBox_receiver;
    int sign_in_flag = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setUp();
        setListeners();
    }

    public void setUp(){
        main_edtTv_id = (EditText)findViewById(R.id.main_edtTv_id);
        main_edtTv_pw = (EditText)findViewById(R.id.main_edtTv_pw);
        main_btn_signIn = (Button)findViewById(R.id.main_btn_signIn);
        main_btn_makeId = (Button)findViewById(R.id.main_btn_makeId);
        main_cBox_donor = (CheckBox)findViewById(R.id.main_cBox_donor);
        main_cBox_receiver = (CheckBox)findViewById(R.id.main_cBox_receiver);
    }

    public void setListeners(){
        activityMover();
        setCheckBoxListeners();
    }

    public void setCheckBoxListeners(){

        main_cBox_donor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    if(main_cBox_receiver.isChecked()) {
                        main_cBox_receiver.setChecked(false);
                    }
                    mode = String.valueOf(1);
                    System.out.println(mode);
                }
                Log.d("flag_debug", String.valueOf(sign_in_flag));
            }
        });

        main_cBox_receiver.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    if(main_cBox_donor.isChecked()) {
                        main_cBox_donor.setChecked(false);
                    }
                    mode = String.valueOf(2);
                    System.out.println(mode);
                }
                Log.d("flag_debug", String.valueOf(sign_in_flag));
            }

        });

    }

    public void activityMover(){
        main_btn_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 유저 ID 저장
                SharedPreferences sharedPreferences = getSharedPreferences("ID", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                String id = main_edtTv_id.getText().toString();
                editor.putString("user_id", id);
                editor.apply();

                System.out.println("현재 로그인한 유저의 아이디 " + id);
                if(main_edtTv_id.getText().toString()!=null && main_edtTv_pw.getText().toString()!=null){
                    new LoginService(LoginActivity.this).postLogIn(
                            main_edtTv_id.getText().toString(),
                            main_edtTv_pw.getText().toString()
                    );
                }else {
                    Toast.makeText(LoginActivity.this, "회원정보를 다시 입력해주세요", Toast.LENGTH_SHORT).show();
                }

            }
        });

        main_btn_makeId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    @Override
    public void validateFailure() {
        Toast.makeText(LoginActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void validateSuccess(LoginResponse response) {
        if (response == null){
            Toast.makeText(LoginActivity.this, "회원정보를 다시 입력해주세요", Toast.LENGTH_SHORT).show();
        }else {
            if(response.getCount() > 0){
                String count = String.valueOf(response.getCount());
                Integer d_count = response.getData().getDonor_count();
                u_id = response.getData().getId();
                donor_count = String.valueOf(response.getData().getDonor_count());
                b_type = response.getData().getBlood_type();
                System.out.println("ID" +
                        " = " + u_id);
                Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                intent.putExtra("donor_count",d_count);
                startActivity(intent);
                finish();
            }

        }

    }
}