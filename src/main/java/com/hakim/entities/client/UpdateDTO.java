package com.hakim.entities.client;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 *
 * @author Hakim
 */
public class UpdateDTO {
    @NotNull
    @Size(max = 40)
    private String fullname;
    
    @NotNull
    @Size(max=25)
    private String country;

    public UpdateDTO() {
    }

    public UpdateDTO(String fullname, String country) {
        this.fullname = fullname;
        this.country = country;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
    
    
    
   
   
}
