package jp.ac.titech.psg.nakano.keyphrasememo.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.astuetz.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

import jp.ac.titech.psg.nakano.keyphrasememo.R;
import jp.ac.titech.psg.nakano.keyphrasememo.activities.fragments.MyFragmentPagerAdapter;
import jp.ac.titech.psg.nakano.keyphrasememo.database.TableConnector;
import jp.ac.titech.psg.nakano.keyphrasememo.model.Memo;
import jp.ac.titech.psg.nakano.keyphrasememo.model.Tag;

public class EditMemo extends AbstractWriteActivity {
    private long memoId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_memo);

        // set default value
        Memo oldMemo = (Memo) getIntent().getSerializableExtra("memo");
        memoId = oldMemo.getId();
        setMemoTitle(oldMemo.getTitle());
        setMemoContent(oldMemo.getContent());
        List<String> tags = new ArrayList<String>();
        for (Tag tag : oldMemo.getTags()){
            tags.add(tag.getName());
        }
        this.setTags(tags);

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
        getMenuInflater().inflate(R.menu.menu_edit_memo, menu);
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

    public void clickNoEdit(View v){
        finish();
    }

    public void clickUpdate(View v){
        TableConnector tableConnector = new TableConnector(this);
        tableConnector.updateMemo(memoId, memoTitle, memoContent, getTagNames());
        setResult(MemoDetail.RESULT_CODE_CHANGED);
        finish();
    }
}
