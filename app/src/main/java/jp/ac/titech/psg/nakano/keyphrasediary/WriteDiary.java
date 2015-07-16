package jp.ac.titech.psg.nakano.keyphrasediary;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;


public class WriteDiary extends ActionBarActivity {

    private EditText diaryText;
    private Button get_keyphrase_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_diary);

        diaryText = (EditText) findViewById(R.id.diary_text);
        diaryText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE);
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

    public void clickGetKeyphrase(View v){
        String text = diaryText.getText().toString();
        YahooConnector.getKeyphrase(text, this);
    }

    public void ariveKeyphrase(List<String> keyphrase){
        for(String k:keyphrase){
            System.out.println(k);
        }
    }
}
