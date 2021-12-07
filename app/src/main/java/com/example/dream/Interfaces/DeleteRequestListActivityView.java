package com.example.dream.Interfaces;

import com.example.dream.Models.DeleteResponse;
import com.example.dream.Models.GetRL_Response;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Query;

public interface DeleteRequestListActivityView {
    void deleteRLSuccess();
    void deleteRLFailure();
}
