package com.example.dream.Interfaces;

import com.example.dream.Models.LoginResponse;

public interface LoginActivityView {
    void validateFailure();
    void validateSuccess(LoginResponse response);
}
