package com.app.dto;

public class SigninRequest {

    private String email;
    private String password;
    private String otp;

    // Getter for email
    public String getEmail() {
        return email;
    }

    // Setter for email
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter for password
    public String getPassword() {
        return password;
    }

    // Setter for password
    public void setPassword(String password) {
        this.password = password;
    }

    // Getter for otp
    public String getOtp() {
        return otp;
    }

    // Setter for otp
    public void setOtp(String otp) {
        this.otp = otp;
    }

    // Custom toString method
    @Override
    public String toString() {
        return "SigninRequest{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", otp='" + otp + '\'' +
                '}';
    }
}
