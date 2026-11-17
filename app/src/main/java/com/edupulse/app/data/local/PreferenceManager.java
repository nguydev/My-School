package com.edupulse.app.data.local;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Handles persistent storage of app preferences and session tokens.
 */
public class PreferenceManager {
    private static final String PREF_NAME = "edupulse_preferences";
    private static final String KEY_REMEMBER_ME = "key_remember_me";
    private static final String KEY_REMEMBER_DEVICE = "key_remember_device";
    private static final String KEY_AUTH_TOKEN = "key_auth_token";
    private static final String KEY_SAVED_EMAIL = "key_saved_email";
    private static final String KEY_SAVED_STUDENT_ID = "key_saved_student_id";

    private final SharedPreferences sharedPreferences;

    public PreferenceManager(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void setRememberMe(boolean enabled) {
        sharedPreferences.edit().putBoolean(KEY_REMEMBER_ME, enabled).apply();
    }

    public boolean isRememberMe() {
        return sharedPreferences.getBoolean(KEY_REMEMBER_ME, false);
    }

    public void setRememberDevice(boolean enabled) {
        sharedPreferences.edit().putBoolean(KEY_REMEMBER_DEVICE, enabled).apply();
    }

    public boolean isRememberDevice() {
        return sharedPreferences.getBoolean(KEY_REMEMBER_DEVICE, true);
    }

    public void saveAuthToken(String token) {
        sharedPreferences.edit().putString(KEY_AUTH_TOKEN, token).apply();
    }

    public String getAuthToken() {
        return sharedPreferences.getString(KEY_AUTH_TOKEN, null);
    }

    public void saveUserCredentials(String email, String studentId) {
        sharedPreferences.edit()
                .putString(KEY_SAVED_EMAIL, email)
                .putString(KEY_SAVED_STUDENT_ID, studentId)
                .apply();
    }

    public String getSavedEmail() {
        return sharedPreferences.getString(KEY_SAVED_EMAIL, "alex.p@school.edu");
    }

    public String getSavedStudentId() {
        return sharedPreferences.getString(KEY_SAVED_STUDENT_ID, "STU-84920");
    }

    public void clearSession() {
        sharedPreferences.edit().remove(KEY_AUTH_TOKEN).apply();
    }
}
