package com.example.dream.Interfaces;

import com.example.dream.Models.GetRL_Response;
import com.example.dream.Models.RequestListInfo;

import java.util.ArrayList;

public interface GetRL_ActivityView {
    void getRLSuccess(GetRL_Response getRL_response);
    void getRLFailure();
}
