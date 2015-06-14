package dk.bobbyz.androidmasterref.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import dk.bobbyz.androidmasterref.model.Text;

/**
 * Created by Bobby on 05-03-2015.
 */
public class TextDao extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "teksterDB.db";
    private static final String TABLE_TEXT = "tekster";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TEKST = "tekst";

    public static final String[] allColumns = { COLUMN_ID, COLUMN_TEKST };

    public TextDao(Context context, String name,
                   SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TEKST_TABLE = "CREATE TABLE " +
                TABLE_TEXT + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_TEKST
                + " TEXT" + ")";
        db.execSQL(CREATE_TEKST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEXT);
        onCreate(db);
    }

    public void addText(Text text) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_TEKST, text.getText());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_TEXT, null, values);
        db.close();
    }

    public List<Text> getAllTexts() {
        SQLiteDatabase db = this.getWritableDatabase();

        List<Text> texts = new ArrayList<Text>();

        Cursor cursor = db.query(TABLE_TEXT,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Text text = cursorToText(cursor);
            texts.add(text);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return texts;
    }

    private Text cursorToText(Cursor cursor) {
        Text text = new Text();
        text.setId(cursor.getInt(0));
        text.setText(cursor.getString(1));
        return text;
    }
}
