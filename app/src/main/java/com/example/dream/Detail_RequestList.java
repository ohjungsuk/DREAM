package com.example.dream;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import static com.example.dream.ApplicationClass.u_id;

public class Detail_RequestList extends AppCompatActivity {

    TextView detail_tv_btype, detail_tv_bkind, detail_tv_patient_name, detail_tv_patient_id,contents;
    TextView detail_tv_hospital, detail_tv_patient_loc, detail_tv_start_date, detail_tv_end_date, detail_tv_need;
    Button btn_donation_complete;
    ImageButton btn_back;
    private String whereFrom;
    private String r_id,title,bkind,btype,name,hospital,patient_id,patient_loc,start_date,end_date,need;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail__request_list);

        setUp();
        activityMover();
        Intent intent = getIntent();
        whereFrom = intent.getStringExtra("from");
        if (whereFrom.equals("RequestList")){
            getDetailData();
        }
        detail_tv_bkind.setText(bkind);
        detail_tv_btype.setText(btype);
        detail_tv_patient_name.setText(name);
        detail_tv_patient_id.setText(patient_id);
        detail_tv_hospital.setText(hospital);
        detail_tv_patient_loc.setText(patient_loc);
        detail_tv_start_date.setText(start_date);
        detail_tv_end_date.setText(end_date);
        detail_tv_need.setText(need);
        contents.setText("요청사연 >> "+ title);
    }

    public void setUp(){
        detail_tv_btype = (TextView)findViewById(R.id.detail_tv_btype);
        detail_tv_bkind = (TextView)findViewById(R.id.detail_tv_bkind);
        detail_tv_patient_name = (TextView)findViewById(R.id.detail_tv_patient_name);
        detail_tv_patient_id = (TextView)findViewById(R.id.detail_tv_patient_id);
        detail_tv_hospital = (TextView)findViewById(R.id.detail_tv_hospital);
        detail_tv_patient_loc = (TextView)findViewById(R.id.detail_tv_patient_loc);
        detail_tv_start_date = (TextView)findViewById(R.id.detail_tv_start_date);
        detail_tv_end_date = (TextView)findViewById(R.id.detail_tv_end_date);
        detail_tv_need = (TextView)findViewById(R.id.detail_tv_need);
        contents = (TextView)findViewById(R.id.contents);

        btn_back = (ImageButton)findViewById(R.id.DRL_imgbtn_back);
        btn_donation_complete = (Button)findViewById(R.id.btn_donation_complete);
    }

    public void getDetailData(){
        intent = getIntent();
        r_id = intent.getStringExtra("request_id");
        System.out.println("Detail : " + r_id);
        title = intent.getStringExtra("title");
        bkind = intent.getStringExtra("bkind");
        btype = intent.getStringExtra("btype");
        name = intent.getStringExtra("name");
        hospital = intent.getStringExtra("hospital");
        patient_id = intent.getStringExtra("patient_id");
        patient_loc = intent.getStringExtra("patient_loc");
        start_date = intent.getStringExtra("start_date");
        end_date = intent.getStringExtra("end_date");
        need = intent.getStringExtra("need");
    }

    public void activityMover(){
        btn_donation_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Detail_RequestList.this,Completion.class);
                intent.putExtra("request_id",r_id);
                intent.putExtra("btype",btype);
                intent.putExtra("bkind",bkind);
                startActivity(intent);
                //finish();
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Detail_RequestList.this, RequestList.class);
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Detail_RequestList.this,RequestList.class);
        startActivity(intent);
        finish();
    }
}