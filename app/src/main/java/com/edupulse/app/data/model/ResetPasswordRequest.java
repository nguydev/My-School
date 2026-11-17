package com.edupulse.app.data.model;

public class ResetPasswordRequest {
    private String identifier; // Email or Student ID
    private String newPassword;
    private String confirmPassword;

    public ResetPasswordRequest(String identifier, String newPassword, String confirmPassword) {
        this.identifier = identifier;
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
    }

    public String getIdentifier() { return identifier; }
    public void setIdentifier(String identifier) { this.identifier = identifier; }

    public String getNewPassword() { return newPassword; }
    public void setNewPassword(String newPassword) { this.newPassword = newPassword; }

    public String getConfirmPassword() { return confirmPassword; }
    public void setConfirmPassword(String confirmPassword) { this.confirmPassword = confirmPassword; }
}
