package com.edupulse.app.data.model;

import java.io.Serializable;

/**
 * Domain & Data representation of an Authenticated User / Student.
 */
public class User implements Serializable {
    private String id;
    private String email;
    private String fullName;
    private String schoolName;
    private String grade;
    private String stream;
    private String phoneNumber;
    private boolean isMfaActive;
    private String token;

    public User() {}

    public User(String id, String email, String fullName, String schoolName, String grade, String stream) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.schoolName = schoolName;
        this.grade = grade;
        this.stream = stream;
        this.isMfaActive = true;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getSchoolName() { return schoolName; }
    public void setSchoolName(String schoolName) { this.schoolName = schoolName; }

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }

    public String getStream() { return stream; }
    public void setStream(String stream) { this.stream = stream; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public boolean isMfaActive() { return isMfaActive; }
    public void setMfaActive(boolean mfaActive) { isMfaActive = mfaActive; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
}
