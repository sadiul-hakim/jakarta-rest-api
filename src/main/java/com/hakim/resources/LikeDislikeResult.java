package com.hakim.resources;

/**
 *
 * @author Hakim
 */
public class LikeDislikeResult {
    private boolean done;
    private int count;

    public LikeDislikeResult() {
    }

    public LikeDislikeResult(boolean done, int count) {
        this.done = done;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public boolean isDone() {
        return done;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
    
    
}
