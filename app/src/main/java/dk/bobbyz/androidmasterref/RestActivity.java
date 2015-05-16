package dk.bobbyz.androidmasterref;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.squareup.otto.ThreadEnforcer;

import dk.bobbyz.androidmasterref.bus.AndroidBus;
import dk.bobbyz.androidmasterref.model.Comment;
import dk.bobbyz.androidmasterref.rest.RestApi;
import dk.bobbyz.androidmasterref.rest.impl.CommentPostedEvent;
import dk.bobbyz.androidmasterref.rest.impl.CommentsReceivedEvent;
import dk.bobbyz.androidmasterref.rest.impl.GetCommentsEvent;
import dk.bobbyz.androidmasterref.rest.impl.PostCommentEvent;
import dk.bobbyz.androidmasterref.rest.impl.RestApiImpl;


public class RestActivity extends ActionBarActivity {

    private AndroidBus mBus;
    private RestApi rest;

    private EditText txtComment;
    private EditText txtEmail;
    private Button btnPost;
    private Button btnGet;
    private ListView tvComments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest);

        rest = new RestApiImpl(getBus());
        getBus().register(rest);

        getBus().register(this);

        txtComment = (EditText) findViewById(R.id.txtComment);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        btnPost = (Button) findViewById(R.id.btnPost);
        btnGet = (Button) findViewById(R.id.btnGet);
        tvComments = (ListView) findViewById(R.id.tvComments);
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

    @Override
    public void onPause() {
        super.onPause();

        getBus().unregister(this);
    }

    private AndroidBus getBus() {
        if (mBus == null) {
            mBus = new AndroidBus(ThreadEnforcer.ANY);
        }
        return mBus;
    }

    public void setBus(AndroidBus bus) {
        mBus = bus;
    }

    @Subscribe
    public void onCommentPosted(CommentPostedEvent event) {
        new AlertDialog.Builder(this)
                .setTitle("Posted")
                .setMessage("Comment posted")
                .setNegativeButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    @Subscribe
    public void onCommentsReceived(CommentsReceivedEvent event) {
        // use the SimpleCursorAdapter to show the
        // elements in a ListView
        ArrayAdapter<Comment> adapter = new ArrayAdapter<Comment>(this,
                android.R.layout.simple_list_item_1, event.getComments());
        tvComments.setAdapter(adapter);
    }

    public void postComment(View view) {
        getBus().post(new PostCommentEvent(new Comment(txtComment.getText().toString(), txtEmail.getText().toString())));
    }

    public void getComments(View view) {
        getBus().post(new GetCommentsEvent());
    }
}
