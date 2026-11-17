package com.edupulse.app.domain.usecase;

import androidx.lifecycle.LiveData;
import com.edupulse.app.data.model.AuthState;
import com.edupulse.app.data.repository.AuthRepository;

public class SendEmailVerificationUseCase {
    private final AuthRepository repository;

    public SendEmailVerificationUseCase(AuthRepository repository) {
        this.repository = repository;
    }

    public LiveData<AuthState> execute(String email) {
        return repository.sendEmailVerification(email);
    }
}
