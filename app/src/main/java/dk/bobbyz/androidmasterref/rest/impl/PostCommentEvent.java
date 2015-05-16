package dk.bobbyz.androidmasterref.rest.impl;

import dk.bobbyz.androidmasterref.model.Comment;

/**
 * Created by Bobby on 01-05-2015.
 */
public class PostCommentEvent {

    private Comment comment;

    public PostCommentEvent(Comment comment) {
        this.comment = comment;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }
}
