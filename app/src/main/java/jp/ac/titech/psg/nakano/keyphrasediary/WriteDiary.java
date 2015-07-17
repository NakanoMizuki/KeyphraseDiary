package jp.ac.titech.psg.nakano.keyphrasediary;

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


public class WriteDiary extends AppCompatActivity {

    private static final String TAG = "WriteDiary";
    private Button reset_button;
    private Button save_button;
    private EditText titleText;
    private EditText diaryText;
    private Button get_keyphrase_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_diary);

        reset_button = (Button) findViewById(R.id.reset_button);
        save_button = (Button) findViewById(R.id.save_button);
        titleText = (EditText) findViewById(R.id.write_title);
        diaryText = (EditText) findViewById(R.id.diary_text);
        get_keyphrase_button = (Button) findViewById(R.id.get_keyphrase_button);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_write_diary, menu);
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
        finish();
    }

    public void clickSave(View v){
        Log.d(TAG, "title=" + titleText.getText().toString());
        Log.d(TAG, "diary=" + diaryText.getText().toString());
    }

    public void clickGetKeyphrase(View v){
        String text = diaryText.getText().toString();
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
