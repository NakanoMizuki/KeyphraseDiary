package jp.ac.titech.psg.nakano.keyphrasediary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import jp.ac.titech.psg.nakano.keyphrasediary.database.DiaryTableHelper;
import jp.ac.titech.psg.nakano.keyphrasediary.model.Diary;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    public void goWriteActivity(View v){
        Intent intent = new Intent(this, WriteDiary.class);
        startActivity(intent);
    }

    public void getDiary(View v){
        DiaryTableHelper diaryTableHelper = new DiaryTableHelper(this);
        List<Diary> diaries = diaryTableHelper.getAllDiary();
        for(Diary d : diaries){
            Log.d(TAG, d.toString());
        }
    }
}
