package com.example.dream;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dream.Interfaces.WriteRequestListActivityView;
import com.example.dream.Services.WriteRL_Service;

import java.util.Calendar;

import static com.example.dream.BaseActivity.getTodayDate;
import static com.example.dream.ApplicationClass.u_id;

public class WriteRequestList extends AppCompatActivity implements WriteRequestListActivityView {

    private final String init_date = getTodayDate();
    private int mEndYear, mEndMonth, mEndDay, mEndHour, mEndMinute;
    private String ENDDT = null, final_date = null;

    ImageButton WRL_imgbtn_back;
    RadioGroup WRL_radiogroup_bloodtype,radiogroup_bloodkind;
    RadioButton WRL_rg_A,WRL_rg_B,WRL_rg_AB,WRL_rg_O;
    RadioButton WRL_rg_kind1,WRL_rg_kind2,WRL_rg_kind3,WRL_rg_kind4,WRL_rg_kind5;
    TextView WRL_tv_start_date,WRL_tv_end_date;

    EditText WRL_edt_bloodnum,WRL_edt_requester_name,WRL_edt_requester_loc,WRL_edt_registerNum,WRL_edt_hospital,WRL_edt_requester_phonenum;
    EditText WRL_edt_requester_sex,WRL_edt_requester_age,WRL_edt_requester_birthday,WRL_edt_request_detail;
    CheckBox WRL_checkkbox_private;
    Button WRL_btn_upload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_request_list);

        setUp();
        activityMover();

        WRL_tv_start_date.setText(init_date);
        WRL_tv_start_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int mYear = calendar.get(Calendar.YEAR);
                int mMonth = calendar.get(Calendar.MONTH);
                int mDay = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog;
                datePickerDialog = new DatePickerDialog(WriteRequestList.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        // i : year, i1 : month, i2 : day
                        mEndYear = i;
                        mEndMonth = i1 + 1;
                        mEndDay = i2;

                        // i1에서 1월이면 0, 2월이면 1, 3월이면 2가 나오므로 1을 더해줘야함
                        String enddt;
                        System.out.println("i : " + mEndYear + "  i1 : " + mEndMonth + "  i2 : " + mEndDay);

                        // 날짜를 2021-01-01 형식으로 맞춰주기
                        if (mEndMonth < 10) {
                            enddt = i + "-0" + mEndMonth + "-";
                        } else {
                            enddt = i + "-" + mEndMonth + "-";
                        }

                        if (i2 < 10) {
                            enddt = enddt + "0" + mEndDay;
                        }
                        else {
                            enddt = enddt + mEndDay;
                        }
                        System.out.println("선택된 마감 날짜 : " + enddt);
                        WRL_tv_start_date.setText(enddt);

                        final_date = enddt;

                        ENDDT = enddt;
                    }
                },mYear,mMonth,mDay);
                datePickerDialog.show();
            }
        });
        WRL_tv_end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar1 = Calendar.getInstance();
                int mYear1 = calendar1.get(Calendar.YEAR);
                int mMonth1 = calendar1.get(Calendar.MONTH);
                int mDay1 = calendar1.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mdatePickerDialog;
                mdatePickerDialog = new DatePickerDialog(WriteRequestList.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker mdatePicker, int i, int i1, int i2) {
                        // i : year, i1 : month, i2 : day
                        mEndYear = i;
                        mEndMonth = i1 + 1;
                        mEndDay = i2;

                        // i1에서 1월이면 0, 2월이면 1, 3월이면 2가 나오므로 1을 더해줘야함
                        String enddt;
                        System.out.println("i : " + mEndYear + "  i1 : " + mEndMonth + "  i2 : " + mEndDay);

                        // 날짜를 2021-01-01 형식으로 맞춰주기
                        if (mEndMonth < 10) {
                            enddt = i + "-0" + mEndMonth + "-";
                        } else {
                            enddt = i + "-" + mEndMonth + "-";
                        }

                        if (i2 < 10) {
                            enddt = enddt + "0" + mEndDay;
                        }
                        else {
                            enddt = enddt + mEndDay;
                        }
                        System.out.println("선택된 마감 날짜 : " + enddt);
                        WRL_tv_end_date.setText(enddt);

                        final_date = enddt;

                        ENDDT = enddt;
                    }
                },mYear1,mMonth1,mDay1);
                mdatePickerDialog.show();
            }
        });

    }

    public void setUp(){
        WRL_imgbtn_back = (ImageButton)findViewById(R.id.WRL_imgbtn_back);
        WRL_radiogroup_bloodtype = (RadioGroup)findViewById(R.id.WRL_radiogroup_bloodtype);
        radiogroup_bloodkind = (RadioGroup)findViewById(R.id.radiogroup_bloodkind);

        WRL_edt_bloodnum = (EditText) findViewById(R.id.WRL_edt_bloodnum);
        WRL_edt_requester_name = (EditText) findViewById(R.id.WRL_edt_requester_name);
        WRL_edt_requester_loc = (EditText) findViewById(R.id.WRL_edt_requester_loc);
        WRL_edt_registerNum = (EditText) findViewById(R.id.WRL_edt_registerNum);
        WRL_edt_hospital = (EditText) findViewById(R.id.WRL_edt_hospital);
        WRL_edt_requester_phonenum = (EditText) findViewById(R.id.WRL_edt_requester_phonenum);
        WRL_edt_requester_sex = (EditText) findViewById(R.id.WRL_edt_requester_sex);
        WRL_edt_requester_age = (EditText) findViewById(R.id.WRL_edt_requester_age);
        WRL_edt_requester_birthday = (EditText) findViewById(R.id.WRL_edt_requester_birthday);
        WRL_edt_request_detail = (EditText) findViewById(R.id.WRL_edt_request_detail);

        WRL_checkkbox_private = (CheckBox) findViewById(R.id.WRL_checkkbox_private);
        WRL_btn_upload = (Button) findViewById(R.id.WRL_btn_upload);

        WRL_rg_A = (RadioButton)findViewById(R.id.WRL_rg_A);
        WRL_rg_B = (RadioButton)findViewById(R.id.WRL_rg_B);
        WRL_rg_AB = (RadioButton)findViewById(R.id.WRL_rg_AB);
        WRL_rg_O = (RadioButton)findViewById(R.id.WRL_rg_O);
        WRL_rg_kind1 = (RadioButton)findViewById(R.id.WRL_rg_kind1);
        WRL_rg_kind2 = (RadioButton)findViewById(R.id.WRL_rg_kind2);
        WRL_rg_kind3 = (RadioButton)findViewById(R.id.WRL_rg_kind3);
        WRL_rg_kind4 = (RadioButton)findViewById(R.id.WRL_rg_kind4);
        WRL_rg_kind5 = (RadioButton)findViewById(R.id.WRL_rg_kind5);

        WRL_tv_start_date = (TextView)findViewById(R.id.WRL_tv_start_date);
        WRL_tv_end_date = (TextView)findViewById(R.id.WRL_tv_end_date);
    }

    public void activityMover(){
        WRL_btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(WriteRequestList.this)
                        .setMessage("수혈을 요청하시겠습니까?")
                        .setPositiveButton("네", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                RadioButton bloodtype = (RadioButton)findViewById(WRL_radiogroup_bloodtype.getCheckedRadioButtonId());
                                RadioButton bloodkind = (RadioButton)findViewById(radiogroup_bloodkind.getCheckedRadioButtonId());
                                String rg_btype = bloodtype.getText().toString();
                                String rg_bkind = bloodkind.getText().toString();
                                new WriteRL_Service(WriteRequestList.this).postRL(
                                        u_id,
                                        WRL_tv_start_date.getText().toString(),
                                        rg_btype,
                                        rg_bkind,
                                        Integer.valueOf(WRL_edt_bloodnum.getText().toString()),
                                        WRL_tv_end_date.getText().toString(),
                                        WRL_edt_requester_name.getText().toString(),
                                        WRL_edt_requester_loc.getText().toString(),
                                        WRL_edt_registerNum.getText().toString(),
                                        WRL_edt_hospital.getText().toString(),
                                        WRL_edt_requester_phonenum.getText().toString(),
                                        WRL_edt_requester_sex.getText().toString(),
                                        Integer.valueOf(WRL_edt_requester_age.getText().toString()),
                                        WRL_edt_requester_birthday .getText().toString(),
                                        WRL_edt_request_detail .getText().toString()
                                );
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).show();
            }
        });
        WRL_imgbtn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(WriteRequestList.this)
                        .setMessage("뒤로가시면 작성중인 내용이 저장되지 않습니다.")
                        .setPositiveButton("뒤로가기", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                Intent intent = new Intent(WriteRequestList.this,HomeActivity.class);
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
        });
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(WriteRequestList.this)
                .setMessage("뒤로가시면 작성중인 내용이 저장되지 않습니다.")
                .setPositiveButton("뒤로가기", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        Intent intent = new Intent(WriteRequestList.this,HomeActivity.class);
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
    public void validateSuccess() {
        Toast.makeText(this, "요청서 작성 성공", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        intent.putExtra("mode",2);
        startActivity(intent);
        finish();
    }

    @Override
    public void validateFailure() {
        Toast.makeText(this, "요청서 작성 실패", Toast.LENGTH_LONG).show();
    }
}