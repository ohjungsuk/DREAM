package com.example.dream.Services;

import com.example.dream.ApplicationClass;
import com.example.dream.Interfaces.DeleteRequestListActivityView;
import com.example.dream.Interfaces.DeleteRequestListRetrofitInterface;
import com.example.dream.Models.DeleteResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteRL_Service {
    private final DeleteRequestListActivityView mDeleteRequestListActivityView;

    public DeleteRL_Service(final DeleteRequestListActivityView deleteRequestListActivityView){
        mDeleteRequestListActivityView = deleteRequestListActivityView;
    }

    public void deleteRequestList(String request_id){
        final DeleteRequestListRetrofitInterface deleteRequestListRetrofitInterface = ApplicationClass.getRetrofit2().create(DeleteRequestListRetrofitInterface.class);
        deleteRequestListRetrofitInterface.deleteRL(request_id).enqueue(new Callback<DeleteResponse>() {
            @Override
            public void onResponse(Call<DeleteResponse> call, Response<DeleteResponse> response) {
                DeleteResponse deleteResponse = response.body();
                if (response.isSuccessful()){
                    System.out.println("삭제 통신 성공");
                    mDeleteRequestListActivityView.deleteRLSuccess();
                }else {
                    System.out.println("삭제 통신 실패");
                    mDeleteRequestListActivityView.deleteRLFailure();
                }
            }

            @Override
            public void onFailure(Call<DeleteResponse> call, Throwable t) {
                System.out.println("아예 실패");
            }
        });
    }
}
