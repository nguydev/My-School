package com.edupulse.app.data.repository;

import android.os.Handler;
import android.os.Looper;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.edupulse.app.data.local.PreferenceManager;
import com.edupulse.app.data.model.AuthState;
import com.edupulse.app.data.model.ResetPasswordRequest;
import com.edupulse.app.data.model.StudentRegistrationRequest;
import com.edupulse.app.data.model.User;

import java.util.UUID;

public class AuthRepositoryImpl implements AuthRepository {

    private final PreferenceManager preferenceManager;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private final MutableLiveData<AuthState> authStateLiveData = new MutableLiveData<>();
    private User currentUser;

    public AuthRepositoryImpl(PreferenceManager preferenceManager) {
        this.preferenceManager = preferenceManager;
        // Default initial student record (from UI mockups)
        this.currentUser = new User(
                "STU-84920",
                "alex.p@school.edu",
                "Alex Parker",
                "Apex International Academy",
                "Class 9",
                "Computer Science"
        );
    }

    @Override
    public LiveData<AuthState> loginWithCredentials(String email, String password, boolean rememberMe) {
        authStateLiveData.setValue(AuthState.loading());
        preferenceManager.setRememberMe(rememberMe);

        handler.postDelayed(() -> {
            if (email != null && email.contains("@") && password != null && password.length() >= 6) {
                currentUser.setEmail(email);
                currentUser.setToken(UUID.randomUUID().toString());
                preferenceManager.saveAuthToken(currentUser.getToken());
                authStateLiveData.setValue(AuthState.success(currentUser));
            } else {
                authStateLiveData.setValue(AuthState.error("Invalid credentials. Please check your institutional email & password."));
            }
        }, 1200);

        return authStateLiveData;
    }

    @Override
    public LiveData<AuthState> loginWithSchoolPortal(String fullName, String schoolName, boolean rememberDevice) {
        authStateLiveData.setValue(AuthState.loading());
        preferenceManager.setRememberDevice(rememberDevice);

        handler.postDelayed(() -> {
            if (fullName != null && !fullName.trim().isEmpty() && schoolName != null && !schoolName.trim().isEmpty()) {
                currentUser.setFullName(fullName);
                currentUser.setSchoolName(schoolName);
                currentUser.setToken(UUID.randomUUID().toString());
                authStateLiveData.setValue(AuthState.success(currentUser));
            } else {
                authStateLiveData.setValue(AuthState.error("Please enter both Student Full Name and School Name."));
            }
        }, 1200);

        return authStateLiveData;
    }

    @Override
    public LiveData<AuthState> completeRegistration(StudentRegistrationRequest request) {
        authStateLiveData.setValue(AuthState.loading());

        handler.postDelayed(() -> {
            if (!request.isTermsAccepted()) {
                authStateLiveData.setValue(AuthState.error("Please accept the School Portal Terms & Conditions."));
                return;
            }
            currentUser.setFullName(request.getFullName() != null ? request.getFullName() : "Alex Parker");
            currentUser.setSchoolName(request.getSchoolName() != null ? request.getSchoolName() : "Apex International Academy");
            currentUser.setStream(request.getSubject());
            currentUser.setGrade(request.getGradeClass());
            currentUser.setPhoneNumber(request.getMobileNumber());
            currentUser.setToken(UUID.randomUUID().toString());

            authStateLiveData.setValue(AuthState.success(currentUser));
        }, 1400);

        return authStateLiveData;
    }

    @Override
    public LiveData<AuthState> sendEmailVerification(String email) {
        authStateLiveData.setValue(AuthState.loading());

        handler.postDelayed(() -> {
            if (email != null && email.contains("@")) {
                currentUser.setEmail(email);
                authStateLiveData.setValue(AuthState.success(currentUser));
            } else {
                authStateLiveData.setValue(AuthState.error("Please enter a valid academic email address."));
            }
        }, 1000);

        return authStateLiveData;
    }

    @Override
    public LiveData<AuthState> verifyOtp(String email, String otpCode) {
        authStateLiveData.setValue(AuthState.loading());

        handler.postDelayed(() -> {
            if (otpCode != null && otpCode.length() == 6) {
                currentUser.setToken(UUID.randomUUID().toString());
                authStateLiveData.setValue(AuthState.success(currentUser));
            } else {
                authStateLiveData.setValue(AuthState.error("Invalid passcode. The 6-digit code has expired or is incorrect."));
            }
        }, 1200);

        return authStateLiveData;
    }

    @Override
    public LiveData<AuthState> resetPassword(ResetPasswordRequest request) {
        authStateLiveData.setValue(AuthState.loading());

        handler.postDelayed(() -> {
            if (request.getNewPassword() != null && request.getNewPassword().equals(request.getConfirmPassword())) {
                currentUser.setToken(UUID.randomUUID().toString());
                authStateLiveData.setValue(AuthState.success(currentUser));
            } else {
                authStateLiveData.setValue(AuthState.error("Passwords do not match. Please re-enter matching passwords."));
            }
        }, 1200);

        return authStateLiveData;
    }

    @Override
    public void simulateState(AuthState.Status status, String customMessage) {
        if (status == AuthState.Status.ERROR) {
            authStateLiveData.setValue(AuthState.error(customMessage != null ? customMessage : "Simulated Campus SSO Authentication Failure."));
        } else if (status == AuthState.Status.SUCCESS) {
            authStateLiveData.setValue(AuthState.success(currentUser));
        } else {
            authStateLiveData.setValue(AuthState.idle());
        }
    }

    @Override
    public User getCurrentUser() {
        return currentUser;
    }
}
