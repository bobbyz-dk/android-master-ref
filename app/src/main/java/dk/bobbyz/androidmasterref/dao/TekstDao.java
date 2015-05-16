package dk.bobbyz.androidmasterref.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import dk.bobbyz.androidmasterref.model.Tekst;

/**
 * Created by Bobby on 05-03-2015.
 */
public class TekstDao extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "teksterDB.db";
    private static final String TABLE_TEKST = "tekster";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TEKST = "tekst";

    public static final String[] allColumns = { COLUMN_ID, COLUMN_TEKST };

    public TekstDao(Context context, String name,
                    SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TEKST_TABLE = "CREATE TABLE " +
                TABLE_TEKST + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_TEKST
                + " TEXT" + ")";
        db.execSQL(CREATE_TEKST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEKST);
        onCreate(db);
    }

    public void addTekst(Tekst tekst) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_TEKST, tekst.getTekst());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_TEKST, null, values);
        db.close();
    }

    public List<Tekst> getAllTekster() {
        SQLiteDatabase db = this.getWritableDatabase();

        List<Tekst> teksts = new ArrayList<Tekst>();

        Cursor cursor = db.query(TABLE_TEKST,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Tekst tekst = cursorToTekst(cursor);
            teksts.add(tekst);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return teksts;
    }

    private Tekst cursorToTekst(Cursor cursor) {
        Tekst tekst = new Tekst();
        tekst.setId(cursor.getInt(0));
        tekst.setTekst(cursor.getString(1));
        return tekst;
    }
}
