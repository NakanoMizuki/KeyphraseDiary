package jp.ac.titech.psg.nakano.keyphrasememo.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import jp.ac.titech.psg.nakano.keyphrasememo.R;
import jp.ac.titech.psg.nakano.keyphrasememo.YahooConnector;
import jp.ac.titech.psg.nakano.keyphrasememo.database.MemoTableHelper;


public class WriteMemo extends AppCompatActivity {

    private static final String TAG = "WriteMemo";
    private Button reset_button;
    private Button save_button;
    private EditText titleText;
    private EditText contentText;
    private Button get_keyphrase_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_memo);

        reset_button = (Button) findViewById(R.id.reset_button);
        save_button = (Button) findViewById(R.id.save_button);
        titleText = (EditText) findViewById(R.id.write_title);
        contentText = (EditText) findViewById(R.id.content_text);
        get_keyphrase_button = (Button) findViewById(R.id.get_keyphrase_button);
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
        String title = titleText.getText().toString();
        String content = contentText.getText().toString();
        Log.d(TAG, "title=" + title);
        Log.d(TAG, "content=" + content);
        MemoTableHelper memoTableHelper = new MemoTableHelper(this);
        memoTableHelper.insert(title, content);
    }

    public void clickGetKeyphrase(View v){
        String text = contentText.getText().toString();
        YahooConnector.getKeyphrase(text, this);
    }

    public void arriveKeyphrase(List<String> keyphrase){
        for(String k:keyphrase){
            Log.d(TAG, k);
        }
    }
    public void failGettingKeyphrase(){
        Toast.makeText(this, "キーフレーズの取得に失敗しました。", Toast.LENGTH_SHORT);
    }
}
