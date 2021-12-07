package com.example.dream;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dream.Interfaces.OnRequestListClickListener;
import com.example.dream.Models.RequestListInfo;

import java.util.ArrayList;

public class RequestList_Adapter extends RecyclerView.Adapter<RequestList_Adapter.ViewHolder> implements OnRequestListClickListener {
    private ArrayList<RequestListInfo> mData = null;
    OnRequestListClickListener listener;

    public RequestList_Adapter(ArrayList<RequestListInfo> data){
        mData = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        Context context = parent.getContext();
//        LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
//
//        View view = inflater.inflate(R.layout.requestlist_item,parent,false);
//        RequestList_Adapter.ViewHolder vh = new RequestList_Adapter.ViewHolder(view);
//        return vh;

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.requestlist_item,parent,false);
        return new RequestList_Adapter.ViewHolder(view,this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RequestListInfo item = mData.get(position);

        holder.btype.setText(item.getBlood_type());
        holder.title.setText(item.getRequest_detail());
        holder.bkind.setText(item.getBlood_kind());
        holder.hospital.setText(item.getHospital_name());
        holder.duedate.setText(item.getDeadline());

        Animation mAnimation = new AlphaAnimation(1, 0);
        mAnimation.setDuration(700);
        mAnimation.setInterpolator(new AccelerateInterpolator());
        mAnimation.setRepeatCount(Animation.INFINITE);
        mAnimation.setRepeatMode(Animation.REVERSE);

        holder.title.startAnimation(mAnimation);
        holder.title.setTextColor(Color.RED);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public void onRequestListClick(ViewHolder holder, View view, int position) {
        if (listener!= null){
            listener.onRequestListClick(holder,view,position);
        }
    }

    public void setOnRequestListClickListener(OnRequestListClickListener listClickListener){
        this.listener = listClickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView btype;
        TextView title;
        TextView bkind;
        TextView hospital;
        TextView duedate;

        public ViewHolder(@NonNull View itemView,final OnRequestListClickListener listClickListener) {
            super(itemView);

            btype = itemView.findViewById(R.id.RL_item_Btype);
            title = itemView.findViewById(R.id.RL_item_title);
            bkind = itemView.findViewById(R.id.RL_item_Bkind);
            hospital = itemView.findViewById(R.id.RL_item_hospital);
            duedate = itemView.findViewById(R.id.RL_item_duedate);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if (listClickListener!=null){
                        listClickListener.onRequestListClick(ViewHolder.this,view,pos);
                    }
                }
            });
        }
    }
    public void clearData(){
        mData.clear();
    }
    public RequestListInfo getItem(int position){
        return mData.get(position);
    }

    public void setItem(int position,RequestListInfo item){
        mData.set(position,item);
    }
}
