package com.hakim.resources;

/**
 *
 * @author Hakim
 */
public class LoginResult {
    private boolean loggedin;
    private Long id;
    private String fullname;
    private String email;
    private String country;

    public LoginResult() {
    }

    public LoginResult(boolean loggedin, Long id, String fullname, String email, String country) {
        this.loggedin = loggedin;
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.country = country;
    }

    public boolean isLoggedin() {
        return loggedin;
    }

    public void setLoggedin(boolean loggedin) {
        this.loggedin = loggedin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
    
    
    
}
