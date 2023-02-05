package com.hakim.resources;

/**
 *
 * @author Hakim
 */
public class RegisterResult {
    private boolean registered;
    private String email;

    public RegisterResult() {
    }

    public RegisterResult(boolean registered, String email) {
        this.registered = registered;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public boolean isRegistered() {
        return registered;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }
    
    
}
