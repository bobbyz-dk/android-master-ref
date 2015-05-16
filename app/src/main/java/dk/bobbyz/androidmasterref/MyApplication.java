package dk.bobbyz.androidmasterref;

import android.app.Application;

import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;

import io.fabric.sdk.android.Fabric;

/**
 * Created by Bobby on 01-04-2015.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        TwitterAuthConfig authConfig =
                new TwitterAuthConfig("goqwJWBRzDjgqyzckBN1zXk7z", "8KfuNwB9aTfBRze1HoAc0vLQeJq6DqBhkwTDMvH8CVx8FFunNX");
        Fabric.with(this, new TwitterCore(authConfig));
    }
}
