package com.hakim.resources;

/**
 *
 * @author Hakim
 */
public class PostActionResult {
    private boolean done;

    public PostActionResult() {
    }

    public PostActionResult(boolean done) {
        this.done = done;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
    
    
}
