package com.edupulse.app.data.model;

public class StudentRegistrationRequest {
    private String fullName;
    private String schoolName;
    private String subject;
    private String gradeClass;
    private String mobileNumber;
    private boolean termsAccepted;
    private String photoUri;

    public StudentRegistrationRequest() {}

    public StudentRegistrationRequest(String fullName, String schoolName, String subject,
                                      String gradeClass, String mobileNumber, boolean termsAccepted) {
        this.fullName = fullName;
        this.schoolName = schoolName;
        this.subject = subject;
        this.gradeClass = gradeClass;
        this.mobileNumber = mobileNumber;
        this.termsAccepted = termsAccepted;
    }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getSchoolName() { return schoolName; }
    public void setSchoolName(String schoolName) { this.schoolName = schoolName; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getGradeClass() { return gradeClass; }
    public void setGradeClass(String gradeClass) { this.gradeClass = gradeClass; }

    public String getMobileNumber() { return mobileNumber; }
    public void setMobileNumber(String mobileNumber) { this.mobileNumber = mobileNumber; }

    public boolean isTermsAccepted() { return termsAccepted; }
    public void setTermsAccepted(boolean termsAccepted) { this.termsAccepted = termsAccepted; }

    public String getPhotoUri() { return photoUri; }
    public void setPhotoUri(String photoUri) { this.photoUri = photoUri; }
}
