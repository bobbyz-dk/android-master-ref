package dk.bobbyz.androidmasterref.twitter.impl;

/**
 * Created by Bobby on 02-04-2015.
 */
public class LastTweetLoadedEvent {

    private String tweet;

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public LastTweetLoadedEvent(String tweet) {
        this.tweet = tweet;
    }
}
