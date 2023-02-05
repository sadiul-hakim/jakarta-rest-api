package com.hakim.entities.post;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 *
 * @author Hakim
 */
public class EditPostDTO {
    @NotNull
    @Size(max=100)
    private String title;
    
    @NotNull
    @Size(max=450)
    private String description;

    public EditPostDTO() {
    }

    public EditPostDTO(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    
}
