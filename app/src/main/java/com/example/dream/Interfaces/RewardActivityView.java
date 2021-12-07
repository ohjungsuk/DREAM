package com.example.dream.Interfaces;

import com.example.dream.Models.LoginResponse;
import com.example.dream.Models.RewardResponse;

public interface RewardActivityView {
    void validateFailure();
    void validateSuccess(RewardResponse rewardResponse);
}
