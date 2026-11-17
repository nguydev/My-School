package com.edupulse.app.domain.usecase;

import androidx.lifecycle.LiveData;
import com.edupulse.app.data.model.AuthState;
import com.edupulse.app.data.model.ResetPasswordRequest;
import com.edupulse.app.data.repository.AuthRepository;

public class ResetPasswordUseCase {
    private final AuthRepository repository;

    public ResetPasswordUseCase(AuthRepository repository) {
        this.repository = repository;
    }

    public LiveData<AuthState> execute(ResetPasswordRequest request) {
        return repository.resetPassword(request);
    }
}
