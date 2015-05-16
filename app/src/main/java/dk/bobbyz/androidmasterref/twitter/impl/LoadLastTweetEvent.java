package dk.bobbyz.androidmasterref.twitter.impl;

/**
 * Created by Bobby on 02-04-2015.
 */
public class LoadLastTweetEvent {

    private String screenName;

    public LoadLastTweetEvent(String screenName) {
        this.screenName = screenName;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }
}
