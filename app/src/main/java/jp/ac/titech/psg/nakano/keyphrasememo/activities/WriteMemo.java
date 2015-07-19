package jp.ac.titech.psg.nakano.keyphrasememo.activities;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import jp.ac.titech.psg.nakano.keyphrasememo.R;
import jp.ac.titech.psg.nakano.keyphrasememo.activities.fragments.WriteMemoFragment;
import jp.ac.titech.psg.nakano.keyphrasememo.database.MemoTableHelper;


public class WriteMemo extends AppCompatActivity {

    private static final String TAG = "WriteMemo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_memo);

        // fragment
        WriteMemoFragment fragment = WriteMemoFragment.newInstance(null);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.write_memo_container, fragment);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_write_memo, menu);
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

    public void clickReset(View v){
        Log.d(TAG, "clickReset");
        finish();
    }

    public void clickSave(View v){
        EditText titleText = (EditText) findViewById(R.id.fragment_write_memo_title);
        String title = titleText.getText().toString();
        EditText contentText = (EditText) findViewById(R.id.fragment_write_memo_content);
        String content = contentText.getText().toString();
        Log.d(TAG, "title=" + title);
        Log.d(TAG, "content=" + content);
        MemoTableHelper memoTableHelper = new MemoTableHelper(this);
        memoTableHelper.insert(title, content);
    }

}
