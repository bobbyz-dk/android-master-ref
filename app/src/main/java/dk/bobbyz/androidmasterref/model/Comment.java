package dk.bobbyz.androidmasterref.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Bobby on 01-05-2015.
 */
public class Comment {

    private String text;
    private String email;
    private String created;

    public Comment() {
    }

    public Comment(String text, String email) {
        this.text = text;
        this.email = email;
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.created = sdfDate.format(new Date());
    }

    public Comment(String text, String email, String created) {
        this.text = text;
        this.email = email;
        this.created = created;
    }

    public String getText() {
        return text;
    }

    public void setText(String comment) {
        this.text = text;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return text + " (" + created + ")";
    }
}
