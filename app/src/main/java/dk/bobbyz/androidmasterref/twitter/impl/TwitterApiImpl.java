package dk.bobbyz.androidmasterref.twitter.impl;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.services.StatusesService;

import java.util.List;

import dk.bobbyz.androidmasterref.twitter.TwitterApi;

/**
 * Created by Bobby on 28-03-2015.
 */
public class TwitterApiImpl implements TwitterApi {

    private Bus mBus;

    public TwitterApiImpl(Bus bus) {
        mBus = bus;
    }

    @Subscribe
    public void onLoadLastTweet(final LoadLastTweetEvent event) {
        if (event.getScreenName() != null && event.getScreenName().length() > 0)
        TwitterCore.getInstance().logInGuest(new Callback() {
            @Override
            public void success(Result appSessionResult) {
                // REST API REQUEST
                TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient();
                // Can also use Twitter directly: Twitter.getApiClient()
                StatusesService statusesService = twitterApiClient.getStatusesService();
                statusesService.userTimeline(null, event.getScreenName(), 1, null, null, null, null, null, null, new Callback<List<Tweet>>() {

                    @Override
                    public void success(Result<List<Tweet>> listResult) {
                        System.out.println(listResult.data.get(0).text);
                        mBus.post(new LastTweetLoadedEvent(listResult.data.get(0).text));
                    }

                    public void failure(TwitterException exception) {
                        exception.printStackTrace();
                    }
                });
            }

            @Override
            public void failure(TwitterException e) {
                e.printStackTrace();
            }
        });
    }
}
