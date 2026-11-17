package com.edupulse.app.ui.auth;

import android.os.Handler;
import android.os.Looper;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.edupulse.app.data.model.AuthState;
import com.edupulse.app.data.model.ResetPasswordRequest;
import com.edupulse.app.data.model.StudentRegistrationRequest;
import com.edupulse.app.data.model.User;
import com.edupulse.app.ui.base.UiEvent;

public class AuthViewModel extends ViewModel {

    private final Handler handler = new Handler(Looper.getMainLooper());
    private final MutableLiveData<AuthState> authState = new MutableLiveData<>(AuthState.idle());
    private final MutableLiveData<UiEvent<String>> toastMessage = new MutableLiveData<>();
    private final MutableLiveData<String> currentVerifiedEmail = new MutableLiveData<>("alex.p@school.edu");

    private User currentUser = new User(
            "STU-84920",
            "alex.p@school.edu",
            "Alex Parker",
            "Apex International Academy",
            "Class 9",
            "Computer Science"
    );

    public AuthViewModel() {}

    public LiveData<AuthState> getAuthState() {
        return authState;
    }

    public LiveData<UiEvent<String>> getToastMessage() {
        return toastMessage;
    }

    public LiveData<String> getCurrentVerifiedEmail() {
        return currentVerifiedEmail;
    }

    public void setVerifiedEmail(String email) {
        currentVerifiedEmail.setValue(email);
    }

    public void resetState() {
        authState.setValue(AuthState.idle());
    }

    public void loginWithCredentials(String email, String password, boolean rememberMe) {
        // UI validation feedback
        if (email == null || email.trim().isEmpty()) {
            email = "alex.p@school.edu"; // Friendly default for frontend preview
        }
        if (password == null || password.trim().isEmpty()) {
            password = "Password123!"; // Friendly default for frontend preview
        }

        authState.setValue(AuthState.loading());
        final String finalEmail = email;

        handler.postDelayed(() -> {
            currentUser.setEmail(finalEmail);
            authState.setValue(AuthState.success(currentUser));
            toastMessage.setValue(new UiEvent<>("Welcome Back, Scholar! Nexus SSO Verified."));
        }, 600);
    }

    public void loginWithSchoolPortal(String fullName, String schoolName, boolean rememberDevice) {
        if (fullName == null || fullName.trim().isEmpty()) {
            fullName = "Alex Johnson";
        }
        if (schoolName == null || schoolName.trim().isEmpty()) {
            schoolName = "Apex International Academy";
        }

        authState.setValue(AuthState.loading());
        final String finalName = fullName;
        final String finalSchool = schoolName;

        handler.postDelayed(() -> {
            currentUser.setFullName(finalName);
            currentUser.setSchoolName(finalSchool);
            authState.setValue(AuthState.success(currentUser));
            toastMessage.setValue(new UiEvent<>("Welcome, " + finalName + "! Signed in to Learning Portal."));
        }, 600);
    }

    public void registerStudent(StudentRegistrationRequest request) {
        authState.setValue(AuthState.loading());

        handler.postDelayed(() -> {
            if (request != null && !request.isTermsAccepted()) {
                authState.setValue(AuthState.error("Please accept the School Portal Terms to proceed."));
                return;
            }
            if (request != null) {
                currentUser.setStream(request.getSubject());
                currentUser.setGrade(request.getGradeClass());
                currentUser.setPhoneNumber(request.getMobileNumber());
            }
            authState.setValue(AuthState.success(currentUser));
            toastMessage.setValue(new UiEvent<>("Registration details saved! Proceeding to verification."));
        }, 700);
    }

    public void sendEmailVerification(String email) {
        if (email == null || email.trim().isEmpty()) {
            email = "alex.p@school.edu";
        }
        currentVerifiedEmail.setValue(email);
        authState.setValue(AuthState.loading());

        final String targetEmail = email;
        handler.postDelayed(() -> {
            currentUser.setEmail(targetEmail);
            authState.setValue(AuthState.success(currentUser));
            toastMessage.setValue(new UiEvent<>("Passcode sent to " + targetEmail));
        }, 600);
    }

    public void verifyOtp(String otpCode) {
        authState.setValue(AuthState.loading());

        handler.postDelayed(() -> {
            authState.setValue(AuthState.success(currentUser));
            toastMessage.setValue(new UiEvent<>("Identity Verified! 2FA Activated."));
        }, 600);
    }

    public void resetPassword(ResetPasswordRequest request) {
        authState.setValue(AuthState.loading());

        handler.postDelayed(() -> {
            authState.setValue(AuthState.success(currentUser));
            toastMessage.setValue(new UiEvent<>("Password reset successfully! You can now sign in."));
        }, 600);
    }

    public void simulateState(AuthState.Status status, String message) {
        if (status == AuthState.Status.ERROR) {
            authState.setValue(AuthState.error(message != null ? message : "SSO Error: Campus authentication rejected."));
        } else if (status == AuthState.Status.SUCCESS) {
            authState.setValue(AuthState.success(currentUser));
            toastMessage.setValue(new UiEvent<>("Simulation: Authentication Successful!"));
        } else {
            authState.setValue(AuthState.idle());
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        handler.removeCallbacksAndMessages(null);
    }
}
