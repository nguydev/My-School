package com.edupulse.app.domain.usecase;

import androidx.lifecycle.LiveData;
import com.edupulse.app.data.model.AuthState;
import com.edupulse.app.data.repository.AuthRepository;

/**
 * Use Case handling student credentials authentication.
 */
public class LoginUseCase {
    private final AuthRepository repository;

    public LoginUseCase(AuthRepository repository) {
        this.repository = repository;
    }

    public LiveData<AuthState> execute(String email, String password, boolean rememberMe) {
        return repository.loginWithCredentials(email, password, rememberMe);
    }
}
