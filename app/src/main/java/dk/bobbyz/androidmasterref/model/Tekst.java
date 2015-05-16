package dk.bobbyz.androidmasterref.model;

/**
 * Created by Bobby on 05-03-2015.
 */
public class Tekst {

    private int id;
    private String tekst;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public Tekst() {}

    public Tekst(int id, String tekst) {
        this.id = id;
        this.tekst = tekst;
    }

    public Tekst(String tekst) {
        this.tekst = tekst;
    }

    @Override
    public String toString() {
        return tekst;
    }
}
