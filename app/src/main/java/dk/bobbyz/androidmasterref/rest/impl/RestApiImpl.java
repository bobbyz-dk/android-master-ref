package dk.bobbyz.androidmasterref.rest.impl;

import android.os.Looper;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import dk.bobbyz.androidmasterref.bus.AndroidBus;
import dk.bobbyz.androidmasterref.model.Comment;
import dk.bobbyz.androidmasterref.rest.RestApi;

/**
 * Created by Bobby on 01-05-2015.
 */
public class RestApiImpl implements RestApi {

    private static final String url = "http://192.168.1.103:2403/comment";
    private AndroidBus mBus;

    public RestApiImpl(AndroidBus bus) {
        mBus = bus;
    }

    @Subscribe
    public void onPostComment(final PostCommentEvent event) {
        Thread t = new Thread() {

            public void run() {
                Looper.prepare();
                HttpClient client = new DefaultHttpClient();
                HttpConnectionParams.setConnectionTimeout(client.getParams(), 10000); //Timeout Limit
                HttpResponse response;
                JSONObject json = new JSONObject();

                try {
                    HttpPost post = new HttpPost(url);
                    json.put("text", event.getComment().getText());
                    json.put("email", event.getComment().getEmail());
                    json.put("created", event.getComment().getCreated());
                    System.out.println(json.toString());
                    StringEntity se = new StringEntity( json.toString());
                    se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
                    post.setEntity(se);
                    response = client.execute(post);

                    if(response != null) {
                        InputStream in = response.getEntity().getContent();
                        mBus.post(new CommentPostedEvent());
                    }

                } catch(Exception e) {
                    e.printStackTrace();
                }
                Looper.loop(); //Loop in the message queue
            }
        };

        t.start();
    }

    @Subscribe
    public void onGetComments(final GetCommentsEvent event) {
        Thread t = new Thread() {

            public void run() {
                Looper.prepare();
                HttpClient client = new DefaultHttpClient();
                HttpConnectionParams.setConnectionTimeout(client.getParams(), 10000); //Timeout Limit
                HttpResponse response;

                try {
                    HttpGet get = new HttpGet(url);
                    response = client.execute(get);

                    if(response != null) {
                        InputStream inputStream = response.getEntity().getContent();

                        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
                        String line = "";
                        String result = "";
                        while((line = bufferedReader.readLine()) != null) {
                            result += line;
                        }
                        inputStream.close();

                        if(result.length() > 0) {
                            JSONArray jsonArray = new JSONArray(result);
                            List<Comment> comments = new ArrayList<Comment>();
                            for (int i=0; i < jsonArray.length(); i++) {
                                Comment comment = new Comment(
                                        jsonArray.getJSONObject(i).getString("text"),
                                        jsonArray.getJSONObject(i).getString("email"),
                                        jsonArray.getJSONObject(i).getString("created"));
                                comments.add(comment);
                            }
                            mBus.postOnMain(new CommentsReceivedEvent(comments));
                        }
                    }

                } catch(Exception e) {
                    e.printStackTrace();
                }
                Looper.loop(); //Loop in the message queue
            }
        };

        t.start();
    }
}
