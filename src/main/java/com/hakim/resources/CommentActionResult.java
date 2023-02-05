package com.hakim.resources;

/**
 *
 * @author Hakim
 */
public class CommentActionResult {
    private boolean done;

    public CommentActionResult() {
    }

    public CommentActionResult(boolean done) {
        this.done = done;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
    
    
}
