package com.edupulse.app.domain.usecase;

import androidx.lifecycle.LiveData;
import com.edupulse.app.data.model.AuthState;
import com.edupulse.app.data.repository.AuthRepository;

public class VerifyOtpUseCase {
    private final AuthRepository repository;

    public VerifyOtpUseCase(AuthRepository repository) {
        this.repository = repository;
    }

    public LiveData<AuthState> execute(String email, String otp) {
        return repository.verifyOtp(email, otp);
    }
}
