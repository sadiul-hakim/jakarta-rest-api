package com.hakim.entities.post;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 *
 * @author Hakim
 */
public class EditCommentDTO {
    @NotNull
    @Size(max = 250)
    private String comment;

    public EditCommentDTO() {
    }

    public EditCommentDTO(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    
}
