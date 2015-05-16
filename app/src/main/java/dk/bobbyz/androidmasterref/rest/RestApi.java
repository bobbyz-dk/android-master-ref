package dk.bobbyz.androidmasterref.rest;

import dk.bobbyz.androidmasterref.rest.impl.GetCommentsEvent;
import dk.bobbyz.androidmasterref.rest.impl.PostCommentEvent;

/**
 * Created by Bobby on 01-05-2015.
 */
public interface RestApi {
    public void onPostComment(PostCommentEvent event);
    public void onGetComments(GetCommentsEvent event);
}
