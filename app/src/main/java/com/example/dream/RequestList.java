package com.example.dream;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.dream.Interfaces.GetRL_ActivityView;
import com.example.dream.Interfaces.OnRequestListClickListener;
import com.example.dream.Models.GetRL_Response;
import com.example.dream.Models.RequestListInfo;
import com.example.dream.Services.GetRL_Service;

import java.util.ArrayList;
import java.util.Collection;

import static com.example.dream.ApplicationClass.b_type;
import static com.example.dream.ApplicationClass.selection;
import static com.example.dream.ApplicationClass.u_id;

public class RequestList extends AppCompatActivity implements GetRL_ActivityView {

    RecyclerView mRecyclerView = null;
    private ArrayList<RequestList_Item> mList = new ArrayList<>();
    private ArrayList<RequestListInfo> mmList = new ArrayList<>();
    private RequestList_Adapter requestList_adapter;

    private String btype;
    private String title;
    private String bkind;
    private String hospital;
    private String duedate;

    ImageButton RL_imgbtn_back;
    Button RL_btn_bybtype,RL_btn_getall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_list);

        setUp();
        activityMover();

        selection = "1";
        System.out.println("sel"+selection);

        SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.swipe_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(RequestList.this, "새로고침", Toast.LENGTH_SHORT).show();
                requestList_adapter.clearData();
                getRecyclerView();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("리쥼");
        getRecyclerView();
    }

    public void setUp(){
        RL_btn_bybtype = (Button)findViewById(R.id.RL_btn_bybtype);
        RL_btn_getall = (Button)findViewById(R.id.RL_btn_getall);
        RL_imgbtn_back = (ImageButton)findViewById(R.id.RL_imgbtn_back);
    }

    public void activityMover(){
        RL_imgbtn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RequestList.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
        RL_btn_bybtype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(RequestList.this, "혈액형별 조회 성공", Toast.LENGTH_SHORT).show();
                selection = "2";
                requestList_adapter.clearData();
                getRecyclerView();
            }
        });
        RL_btn_getall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(RequestList.this, "전체 리스트 조회 성공", Toast.LENGTH_SHORT).show();
                selection = "1";
                requestList_adapter.clearData();
                getRecyclerView();
            }
        });
    }

    private void send2Detail(String from,String request_id,String title, String bkind,String btype,String name, String hospital,String patient_id, String patient_loc, String start_date, String end_date, String need){
        Intent intent = new Intent(RequestList.this, Detail_RequestList.class);
        intent.putExtra("from",from);
        intent.putExtra("request_id",request_id);
        intent.putExtra("title",title);
        intent.putExtra("bkind",bkind);
        intent.putExtra("btype",btype);
        intent.putExtra("name",name);
        intent.putExtra("hospital",hospital);
        intent.putExtra("patient_id",patient_id);
        intent.putExtra("patient_loc",patient_loc);
        intent.putExtra("start_date",start_date);
        intent.putExtra("end_date",end_date);
        intent.putExtra("need",need);
        startActivity(intent);
    }

    private void getRecyclerView(){
        new GetRL_Service(RequestList.this).getRequestList(u_id);
        System.out.println("API 호출");
        System.out.println("전체 리스트 조회");

        RecyclerView recyclerView = findViewById(R.id.RL_recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        requestList_adapter = new RequestList_Adapter(mmList);
        requestList_adapter.clearData();
        recyclerView.setAdapter(requestList_adapter);
        recyclerView.getAdapter().notifyDataSetChanged();

        requestList_adapter.setOnRequestListClickListener(new OnRequestListClickListener() {
            @Override
            public void onRequestListClick(RequestList_Adapter.ViewHolder holder, View view, int position) {
                RequestListInfo item = requestList_adapter.getItem(position);
                System.out.println("RequestList : " + item.getRequest_id().toString());
                //액티비티 이동
                send2Detail("RequestList",
                        item.getRequest_id().toString(),
                        item.getRequest_detail(),
                        item.getBlood_kind(),
                        item.getBlood_type(),
                        item.getPatient_name(),
                        item.getHospital_name(),
                        item.getPatient_id(),
                        item.getPatient_loc(),
                        item.getDate(),
                        item.getDeadline(),
                        item.getNeed().toString());
            }
        });
        System.out.println("end");
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(RequestList.this,HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void getRLSuccess(GetRL_Response getRL_response) {
        ArrayList<RequestListInfo> datas = (ArrayList<RequestListInfo>)getRL_response.getData();
        System.out.println("sel"+selection);
        if (getRL_response!=null){
            for (RequestListInfo requestListInfo : datas){
                //mList.clear();
                if (selection.equals("1")){
                    mmList.add(new RequestListInfo(
                            requestListInfo.getRequest_id(),
                            requestListInfo.getId(),
                            requestListInfo.getDate().toString(),
                            requestListInfo.getBlood_type().toString(),
                            requestListInfo.getBlood_kind().toString(),
                            requestListInfo.getNeed(),
                            requestListInfo.getDeadline().toString(),
                            requestListInfo.getPatient_name(),
                            requestListInfo.getPatient_id(),
                            requestListInfo.getPatient_loc(),
                            requestListInfo.getPatient_sex(),
                            requestListInfo.getPatient_age(),
                            requestListInfo.getPatient_birth(),
                            requestListInfo.getHospital_name(),
                            requestListInfo.getPhone(),
                            requestListInfo.getRequest_detail()));
                }else if(selection.equals("2")){
                    //System.out.println(b_type);
                    //System.out.println("2"+requestListInfo.getBlood_type());
                    if (requestListInfo.getBlood_type().equals(b_type)){
                        System.out.println("test"+requestListInfo.getRequest_id());
                        mmList.add(new RequestListInfo(
                                requestListInfo.getRequest_id(),
                                requestListInfo.getId(),
                                requestListInfo.getDate().toString(),
                                requestListInfo.getBlood_type().toString(),
                                requestListInfo.getBlood_kind().toString(),
                                requestListInfo.getNeed(),
                                requestListInfo.getDeadline().toString(),
                                requestListInfo.getPatient_name(),
                                requestListInfo.getPatient_id(),
                                requestListInfo.getPatient_loc(),
                                requestListInfo.getPatient_sex(),
                                requestListInfo.getPatient_age(),
                                requestListInfo.getPatient_birth(),
                                requestListInfo.getHospital_name(),
                                requestListInfo.getPhone(),
                                requestListInfo.getRequest_detail()));
                    }
                }
            }
        }

        requestList_adapter.notifyDataSetChanged();
        System.out.println("조회 성공");
    }

    @Override
    public void getRLFailure() {
        System.out.println("get List Failure");
    }
}