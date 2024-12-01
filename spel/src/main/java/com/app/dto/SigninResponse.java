package com.app.dto;

public class SigninResponse {

    private String jwt;
    private String mesg;

    // No-argument constructor
    public SigninResponse() {
    }

    // All-arguments constructor
    public SigninResponse(String jwt, String mesg) {
        this.jwt = jwt;
        this.mesg = mesg;
    }

    // Getter for jwt
    public String getJwt() {
        return jwt;
    }

    // Setter for jwt
    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    // Getter for mesg
    public String getMesg() {
        return mesg;
    }

    // Setter for mesg
    public void setMesg(String mesg) {
        this.mesg = mesg;
    }

    // Optional: Custom toString method
    @Override
    public String toString() {
        return "SigninResponse{" +
                "jwt='" + jwt + '\'' +
                ", mesg='" + mesg + '\'' +
                '}';
    }
}
