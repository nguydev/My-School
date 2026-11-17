package com.edupulse.app.data.repository;

import androidx.lifecycle.LiveData;
import com.edupulse.app.data.model.AuthState;
import com.edupulse.app.data.model.ResetPasswordRequest;
import com.edupulse.app.data.model.StudentRegistrationRequest;
import com.edupulse.app.data.model.User;

public interface AuthRepository {
    LiveData<AuthState> loginWithCredentials(String email, String password, boolean rememberMe);
    LiveData<AuthState> loginWithSchoolPortal(String fullName, String schoolName, boolean rememberDevice);
    LiveData<AuthState> completeRegistration(StudentRegistrationRequest request);
    LiveData<AuthState> sendEmailVerification(String email);
    LiveData<AuthState> verifyOtp(String email, String otpCode);
    LiveData<AuthState> resetPassword(ResetPasswordRequest request);
    void simulateState(AuthState.Status status, String customMessage);
    User getCurrentUser();
}
