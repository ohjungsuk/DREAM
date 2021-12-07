package com.example.dream.Interfaces;

import android.view.View;

import com.example.dream.RequestList_Adapter;

public interface OnRequestListClickListener {
    public void onRequestListClick(RequestList_Adapter.ViewHolder holder, View view, int position);
}
