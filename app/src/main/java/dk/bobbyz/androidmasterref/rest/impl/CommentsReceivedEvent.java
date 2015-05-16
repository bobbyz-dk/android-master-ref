package dk.bobbyz.androidmasterref.rest.impl;

import java.util.List;

import dk.bobbyz.androidmasterref.model.Comment;

/**
 * Created by Bobby on 01-05-2015.
 */
public class CommentsReceivedEvent {

    private List<Comment> comments;

    public CommentsReceivedEvent(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
