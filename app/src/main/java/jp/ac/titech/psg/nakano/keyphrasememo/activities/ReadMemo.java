package jp.ac.titech.psg.nakano.keyphrasememo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import jp.ac.titech.psg.nakano.keyphrasememo.R;
import jp.ac.titech.psg.nakano.keyphrasememo.activities.fragments.MaxHeightListView;
import jp.ac.titech.psg.nakano.keyphrasememo.database.TableConnector;
import jp.ac.titech.psg.nakano.keyphrasememo.model.Memo;


public class ReadMemo extends AppCompatActivity {

    private static final String TAG = "ReadMemo";

    private List<Memo> memos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_memo);
    }

    @Override
    public void onStart(){
        super.onStart();
        memos = new TableConnector(this).getAllMemo();
        ArrayAdapter<Memo> adapter = new ArrayAdapter<Memo>(this, R.layout.rowitem, memos);
        MaxHeightListView listView = (MaxHeightListView) findViewById(R.id.memo_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new MyListener());
        TextView emtpyText = (TextView) findViewById(R.id.read_empty_list);
        listView.setEmptyView(emtpyText);
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


    // Listener for clicking listView item
    class MyListener implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View v, int position, long id){
            Intent intent = new Intent(ReadMemo.this, MemoDetail.class);
            intent.putExtra("memo", memos.get(position));
            startActivity(intent);
        }
    }
}
