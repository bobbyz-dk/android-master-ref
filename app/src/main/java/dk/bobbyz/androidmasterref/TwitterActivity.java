package dk.bobbyz.androidmasterref;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import dk.bobbyz.androidmasterref.twitter.TwitterApi;
import dk.bobbyz.androidmasterref.twitter.impl.LastTweetLoadedEvent;
import dk.bobbyz.androidmasterref.twitter.impl.LoadLastTweetEvent;
import dk.bobbyz.androidmasterref.twitter.impl.TwitterApiImpl;

public class TwitterActivity extends ActionBarActivity {

    private Bus mBus;
    private TwitterApi twitter;

    private EditText txtScreenName;
    private Button btnRetrieveTweet;
    private TextView lblResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter);

        twitter = new TwitterApiImpl(getBus());
        getBus().register(twitter);

        getBus().register(this);

        txtScreenName = (EditText) findViewById(R.id.txtScreenName);
        btnRetrieveTweet = (Button) findViewById(R.id.btnRetrieveTweet);
        lblResult = (TextView) findViewById(R.id.lblResult);
    }

    @Override
    public void onResume() {
        super.onResume();
        //getBus().register(this);
        getBus().post(new LoadLastTweetEvent(txtScreenName.getText().toString()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_rest, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Subscribe
    public void onStoriesLoaded(LastTweetLoadedEvent event) {
        lblResult.setText(event.getTweet());
    }

    @Override
    public void onPause() {
        super.onPause();

        getBus().unregister(this);
    }

    private Bus getBus() {
        if (mBus == null) {
            mBus = new Bus();
        }
        return mBus;
    }

    public void setBus(Bus bus) {
        mBus = bus;
    }

    public void lastTweet(View view) {
        getBus().post(new LoadLastTweetEvent(txtScreenName.getText().toString()));
    }
}
