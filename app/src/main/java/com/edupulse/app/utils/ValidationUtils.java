package com.edupulse.app.utils;

import android.util.Patterns;
import java.util.regex.Pattern;

public final class ValidationUtils {

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$");

    private ValidationUtils() {}

    public static boolean isValidEmail(String email) {
        return email != null && !email.trim().isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches();
    }

    public static boolean isStrongPassword(String password) {
        return password != null && PASSWORD_PATTERN.matcher(password).matches();
    }

    public static boolean isValidOtp(String otp) {
        return otp != null && otp.length() == 6 && otp.matches("\\d{6}");
    }

    public static boolean arePasswordsMatching(String p1, String p2) {
        return p1 != null && p1.equals(p2) && !p1.isEmpty();
    }
}
