package dk.bobbyz.androidmasterref.twitter;

import dk.bobbyz.androidmasterref.twitter.impl.LoadLastTweetEvent;

/**
 * Created by Bobby on 28-03-2015.
 */
public interface TwitterApi {
    public void onLoadLastTweet(LoadLastTweetEvent event);
}
