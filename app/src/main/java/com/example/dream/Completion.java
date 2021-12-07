package com.example.dream;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dream.Interfaces.DeleteRequestListActivityView;
import com.example.dream.Interfaces.UpdateDonationCountActivityView;
import com.example.dream.Services.DeleteRL_Service;
import com.example.dream.Services.UpdateDCountService;

import java.util.Calendar;

import static com.example.dream.ApplicationClass.donor_count;
import static com.example.dream.ApplicationClass.u_id;
import static com.example.dream.BaseActivity.getTodayDate;

public class Completion extends AppCompatActivity implements DeleteRequestListActivityView, UpdateDonationCountActivityView {

    private final String init_date = getTodayDate();
    private int mEndYear, mEndMonth, mEndDay, mEndHour, mEndMinute;
    private String ENDDT = null, final_date = null;
    private String request_id;
    Button cmp_btn_done,cmp_btn_cancel;
    TextView Completion_tv_date, cmp_tv_bkind, cmp_tv_uid,cmp_tv_btype;
    EditText cmp_edt_serialNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completion);

        setUp();
        cmp_tv_uid.setText(u_id);
        Intent intent = getIntent();
        request_id = intent.getStringExtra("request_id");
        System.out.println("Completion : " + request_id);
        cmp_tv_btype.setText(intent.getStringExtra("btype"));
        cmp_tv_bkind.setText(intent.getStringExtra("bkind"));
        Completion_tv_date.setText(init_date);
        Completion_tv_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar1 = Calendar.getInstance();
                int mYear1 = calendar1.get(Calendar.YEAR);
                int mMonth1 = calendar1.get(Calendar.MONTH);
                int mDay1 = calendar1.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mdatePickerDialog;
                mdatePickerDialog = new DatePickerDialog(Completion.this, new DatePickerDialog.OnDateSetListener() {
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
                        Completion_tv_date.setText(enddt);

                        final_date = enddt;

                        ENDDT = enddt;
                    }
                },mYear1,mMonth1,mDay1);
                mdatePickerDialog.show();
            }
        });
        cmp_btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        cmp_btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //request_id (해당 요청서 작성 id)를 통해 요청서 삭제 API
                new DeleteRL_Service(Completion.this).deleteRequestList(request_id);
                //date, serial, u_id 급혈횟수 업데이트
                new UpdateDCountService(Completion.this).updateDonationCount(u_id,cmp_edt_serialNum.getText().toString(),Completion_tv_date.toString());
            }
        });
    }

    public void setUp(){
        cmp_btn_done = (Button)findViewById(R.id.cmp_btn_done);
        cmp_btn_cancel = (Button)findViewById(R.id.cmp_btn_cancel);
        Completion_tv_date = (TextView) findViewById(R.id.Completion_tv_date);
        cmp_tv_bkind = (TextView) findViewById(R.id.cmp_tv_bkind);
        cmp_tv_uid = (TextView) findViewById(R.id.cmp_tv_uid);
        cmp_tv_btype = (TextView) findViewById(R.id.cmp_tv_btype);
        cmp_edt_serialNum = (EditText)findViewById(R.id.cmp_edt_serialNum);
    }

    @Override
    public void deleteRLSuccess() {
        Toast.makeText(this, "헌혈완료후 해당 요청서 삭제 성공", Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void deleteRLFailure() {
        Toast.makeText(this, "헌혈완료후 해당 요청서 삭제 실패", Toast.LENGTH_LONG).show();
    }

    @Override
    public void updateDCSuccess() {
        donor_count = String.valueOf(Integer.valueOf(donor_count) + 1);
        Toast.makeText(this, "헌혈완료후 급혈자 헌혈 횟수 업데이트 성공", Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void updateDCFailure() {
        Toast.makeText(this, "헌혈완료후 급혈자 헌혈 횟수 업데이트 실패", Toast.LENGTH_LONG).show();
    }
}