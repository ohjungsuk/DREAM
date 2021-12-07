package com.example.dream;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.dream.Interfaces.SignUpActivityView;
import com.example.dream.Services.SignUpService;

public class SignUpActivity extends AppCompatActivity implements SignUpActivityView {

    private RadioButton rg_btn_A, rg_btn_B, rg_btn_AB, rg_btn_O;
    private RadioGroup radiogroup;
    Button signUP_btn_done;
    Toolbar signUP_toolbar;
    CheckBox signUP_CBox_donor;
    CheckBox signUP_CBox_receiver;
    EditText signUP_edt_id;
    EditText signUP_edt_pw;
    EditText signUP_edt_name;
    EditText signUP_edt_area;

    public int State_donor=0;
    public int State_receiver=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signUP_toolbar = (Toolbar)findViewById(R.id.signUP_toolbar);
        setSupportActionBar(signUP_toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setUp();
        activityMover();
        checkBoxControl();
    }
    
    public void setUp(){
        signUP_btn_done = (Button)findViewById(R.id.signUP_btn_done);
        signUP_CBox_donor = (CheckBox)findViewById(R.id.signUP_CBox_donor);
        signUP_CBox_receiver = (CheckBox)findViewById(R.id.signUP_CBox_receiver);
        radiogroup = (RadioGroup)findViewById(R.id.radiogroup);
        signUP_edt_id = (EditText)findViewById(R.id.signUP_edt_id);
        signUP_edt_id.setFilters(new InputFilter[]{
                new InputFilter.LengthFilter(10)
        });
        signUP_edt_pw = (EditText)findViewById(R.id.signUP_edt_pw); // 영어,한글,특수문자
        signUP_edt_pw.setFilters(new InputFilter[]{
                new InputFilter.LengthFilter(10)
        });
        signUP_edt_name = (EditText)findViewById(R.id.signUP_edt_name);
        signUP_edt_name.setFilters(new InputFilter[]{
                new InputFilter.LengthFilter(5)
        });
        signUP_edt_area = (EditText)findViewById(R.id.signUP_edt_area);

    }
    
    public void activityMover(){
        signUP_btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(State_receiver == 0 && State_donor == 1) {  // 헌혈자 회원가입
                    RadioButton which_btype = (RadioButton)findViewById(radiogroup.getCheckedRadioButtonId());

                    String rg_bloodtype = which_btype.getText().toString();


                    new SignUpService(SignUpActivity.this).postSignUp(
                            signUP_edt_id.getText().toString(),
                            signUP_edt_pw.getText().toString(),
                            signUP_edt_name.getText().toString(),
                            signUP_edt_area.getText().toString(),
                            rg_bloodtype
                    );
//                    Toast.makeText(SignUpActivity.this, "헌혈자 회원가입 성공", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
//                    startActivity(intent);
//                    finish();
                }
                else if(State_receiver == 1 && State_donor ==0){  // 급혈자 회원가입
                    Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
//                    Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
//                    startActivity(intent);
//                    finish();
                else{
                    Toast.makeText(getApplicationContext(),"Please select affiliation",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    
    public void checkBoxControl(){
        signUP_CBox_donor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    State_donor=1;
                    signUP_CBox_receiver.setChecked(false);
                    signUP_edt_id.getText().clear();
                    signUP_edt_pw.getText().clear();
                    signUP_edt_name.getText().clear();
                    signUP_edt_area.getText().clear();
                }else{
                    State_donor=0;
                }
            }
        });
        signUP_CBox_receiver.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    State_receiver=1;
                    signUP_CBox_donor.setChecked(false);
                    signUP_edt_id.getText().clear();
                    signUP_edt_pw.getText().clear();
                    signUP_edt_name.getText().clear();
                    signUP_edt_area.getText().clear();
                }else{
                    State_receiver=0;

                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(SignUpActivity.this)
                .setMessage("뒤로가면 입력한 내용이 저장되지 않습니다")
                .setPositiveButton("뒤로가기", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
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
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                new AlertDialog.Builder(SignUpActivity.this)
                        .setMessage("뒤로가면 입력한 내용이 저장되지 않습니다.")
                        .setPositiveButton("뒤로가기", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
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
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void validateSuccess() {
        Toast.makeText(this, "헌혈자 모드 회원가입 성공", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void validateFailure() {

        Toast.makeText(this, "회원가입 실패", Toast.LENGTH_LONG).show();
    }
}