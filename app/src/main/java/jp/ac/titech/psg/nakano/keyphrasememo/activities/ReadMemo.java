package jp.ac.titech.psg.nakano.keyphrasememo.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import jp.ac.titech.psg.nakano.keyphrasememo.R;
import jp.ac.titech.psg.nakano.keyphrasememo.database.MemoTableHelper;
import jp.ac.titech.psg.nakano.keyphrasememo.model.Memo;


public class ReadMemo extends AppCompatActivity {

    private static final String TAG = "ReadMemo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_memo);

        List<Memo> memos = getAllMemo();
        ArrayAdapter<Memo> adapter = new ArrayAdapter<Memo>(this, R.layout.rowitem, memos);
        ListView listView = (ListView) findViewById(R.id.memo_list);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_read_memo, menu);
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

    public List<Memo> getAllMemo(){
        MemoTableHelper memoTableHelper = new MemoTableHelper(this);
        return memoTableHelper.getAllMemo();
    }
}
