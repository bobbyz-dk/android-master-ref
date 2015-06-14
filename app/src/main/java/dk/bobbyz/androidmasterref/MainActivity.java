package dk.bobbyz.androidmasterref;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import dk.bobbyz.androidmasterref.dao.TextDao;
import dk.bobbyz.androidmasterref.model.Text;


public class MainActivity extends ActionBarActivity {

    EditText txtText;
    Button btnSave;
    ListView list;

    TextDao textDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtText = (EditText) findViewById(R.id.txtText);
        btnSave = (Button) findViewById(R.id.btnSave);
        list = (ListView) findViewById(R.id.list);

        textDao = new TextDao(this, null, null, 1);
        fillList();
    }

    private void fillList() {
        List<Text> values = textDao.getAllTexts();

        // use the SimpleCursorAdapter to show the
        // elements in a ListView
        ArrayAdapter<Text> adapter = new ArrayAdapter<Text>(this,
                android.R.layout.simple_list_item_1, values);
        list.setAdapter(adapter);
    }

    public void newText(View view) {
        textDao = new TextDao(this, null, null, 1);

        Text text = new Text(txtText.getText().toString());

        textDao.addText(text);
        txtText.setText("");

        fillList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
