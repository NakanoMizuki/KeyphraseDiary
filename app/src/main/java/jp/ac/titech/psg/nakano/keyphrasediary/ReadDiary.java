package jp.ac.titech.psg.nakano.keyphrasediary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import jp.ac.titech.psg.nakano.keyphrasediary.database.DiaryTableHelper;
import jp.ac.titech.psg.nakano.keyphrasediary.model.Diary;


public class ReadDiary extends AppCompatActivity {

    private static final String TAG = "ReadDiary";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_diary);

        List<Diary> diaries = getAllDiary();
        ArrayAdapter<Diary> adapter = new ArrayAdapter<Diary>(this, R.layout.rowitem, diaries);
        ListView listView = (ListView) findViewById(R.id.diary_list);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_read_diary, menu);
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

    public List<Diary> getAllDiary(){
        DiaryTableHelper diaryTableHelper = new DiaryTableHelper(this);
        return diaryTableHelper.getAllDiary();
    }
}
