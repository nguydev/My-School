package com.edupulse.app.domain.usecase;

import androidx.lifecycle.LiveData;
import com.edupulse.app.data.model.AuthState;
import com.edupulse.app.data.model.StudentRegistrationRequest;
import com.edupulse.app.data.repository.AuthRepository;

public class RegisterStudentUseCase {
    private final AuthRepository repository;

    public RegisterStudentUseCase(AuthRepository repository) {
        this.repository = repository;
    }

    public LiveData<AuthState> execute(StudentRegistrationRequest request) {
        return repository.completeRegistration(request);
    }
}
