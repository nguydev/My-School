package com.edupulse.app.di;

import android.content.Context;
import com.edupulse.app.data.local.PreferenceManager;
import com.edupulse.app.data.repository.AuthRepository;
import com.edupulse.app.data.repository.AuthRepositoryImpl;
import com.edupulse.app.domain.usecase.LoginUseCase;
import com.edupulse.app.domain.usecase.RegisterStudentUseCase;
import com.edupulse.app.domain.usecase.ResetPasswordUseCase;
import com.edupulse.app.domain.usecase.SendEmailVerificationUseCase;
import com.edupulse.app.domain.usecase.VerifyOtpUseCase;

/**
 * Service Locator pattern providing production-grade Dependency Injection
 * without the build overhead of heavy annotation processors.
 */
public class ServiceLocator {
    private static volatile ServiceLocator INSTANCE;

    private PreferenceManager preferenceManager;
    private AuthRepository authRepository;

    private LoginUseCase loginUseCase;
    private RegisterStudentUseCase registerStudentUseCase;
    private VerifyOtpUseCase verifyOtpUseCase;
    private SendEmailVerificationUseCase sendEmailVerificationUseCase;
    private ResetPasswordUseCase resetPasswordUseCase;

    private ServiceLocator() {}

    public static ServiceLocator getInstance() {
        if (INSTANCE == null) {
            synchronized (ServiceLocator.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ServiceLocator();
                }
            }
        }
        return INSTANCE;
    }

    public void init(Context context) {
        if (preferenceManager == null) {
            preferenceManager = new PreferenceManager(context.getApplicationContext());
        }
        if (authRepository == null) {
            authRepository = new AuthRepositoryImpl(preferenceManager);
        }
    }

    public PreferenceManager getPreferenceManager() {
        return preferenceManager;
    }

    public AuthRepository getAuthRepository() {
        return authRepository;
    }

    public LoginUseCase getLoginUseCase() {
        if (loginUseCase == null) {
            loginUseCase = new LoginUseCase(getAuthRepository());
        }
        return loginUseCase;
    }

    public RegisterStudentUseCase getRegisterStudentUseCase() {
        if (registerStudentUseCase == null) {
            registerStudentUseCase = new RegisterStudentUseCase(getAuthRepository());
        }
        return registerStudentUseCase;
    }

    public VerifyOtpUseCase getVerifyOtpUseCase() {
        if (verifyOtpUseCase == null) {
            verifyOtpUseCase = new VerifyOtpUseCase(getAuthRepository());
        }
        return verifyOtpUseCase;
    }

    public SendEmailVerificationUseCase getSendEmailVerificationUseCase() {
        if (sendEmailVerificationUseCase == null) {
            sendEmailVerificationUseCase = new SendEmailVerificationUseCase(getAuthRepository());
        }
        return sendEmailVerificationUseCase;
    }

    public ResetPasswordUseCase getResetPasswordUseCase() {
        if (resetPasswordUseCase == null) {
            resetPasswordUseCase = new ResetPasswordUseCase(getAuthRepository());
        }
        return resetPasswordUseCase;
    }
}
