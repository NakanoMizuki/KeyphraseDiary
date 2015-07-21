package jp.ac.titech.psg.nakano.keyphrasememo.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.astuetz.PagerSlidingTabStrip;

import java.util.HashSet;
import java.util.Set;

import jp.ac.titech.psg.nakano.keyphrasememo.R;
import jp.ac.titech.psg.nakano.keyphrasememo.activities.fragments.MyFragmentPagerAdapter;
import jp.ac.titech.psg.nakano.keyphrasememo.database.TableConnector;


public class WriteMemo extends AbstractWriteActivity {

    private static final String TAG = "WriteMemo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_memo);

        // fragment
        ViewPager viewPager = (ViewPager)findViewById(R.id.pager);
        pager = viewPager;
        MyFragmentPagerAdapter pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setViewPager(viewPager);
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
        EditText tagsText = (EditText) findViewById(R.id.fragment_write_memo_tag);
        Set<String> tags = new HashSet<String>();
        for(String tag: tagsText.getText().toString().split(",")) {
            tags.add(tag);
        }
        new TableConnector(this).insertMemo(title, content, tags);

        finish();
    }

}
