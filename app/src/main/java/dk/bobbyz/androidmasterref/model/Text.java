package dk.bobbyz.androidmasterref.model;

/**
 * Created by Bobby on 05-03-2015.
 */
public class Text {

    private int id;
    private String tekst;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return tekst;
    }

    public void setText(String tekst) {
        this.tekst = tekst;
    }

    public Text() {}

    public Text(int id, String tekst) {
        this.id = id;
        this.tekst = tekst;
    }

    public Text(String tekst) {
        this.tekst = tekst;
    }

    @Override
    public String toString() {
        return tekst;
    }
}
