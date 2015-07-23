package jp.ac.titech.psg.nakano.keyphrasememo.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jp.ac.titech.psg.nakano.keyphrasememo.R;
import jp.ac.titech.psg.nakano.keyphrasememo.activities.fragments.MaxHeightListView;
import jp.ac.titech.psg.nakano.keyphrasememo.database.TableConnector;
import jp.ac.titech.psg.nakano.keyphrasememo.model.Memo;

public class SearchResult extends AppCompatActivity {

    public static final String PARAM = "CHECKED_TAGS";
    private static final String NAME = "PREF";
    private static final String KEY = "TAGID_SET";

    private List<Memo> memos;
    private List<String> tagIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        tagIds = getIntent().getStringArrayListExtra(PARAM);

        // saved to SharedPreferences
        SharedPreferences.Editor editor = getSharedPreferences(NAME, MODE_PRIVATE).edit();
        Set<String> set = new HashSet<String>();
        for(String tagid:tagIds){
            set.add(tagid);
        }
        Log.d("search", "onCreate + " + tagIds.size());
        editor.putStringSet(KEY, set);
        editor.apply();

        setMemos(tagIds);
    }

    private void setMemos(List<String> tagIds){
        for(String tagId:tagIds){
            Log.d("SearchResult", "tagId=" + tagId);
        }
        TableConnector tableConnector = new TableConnector(this);
        memos = tableConnector.getMemoHasTag(tagIds);
        for(Memo memo : memos){
            Log.d("SearchResult", "memo=" + memo.toString());
        }
        ArrayAdapter<Memo> adapter = new ArrayAdapter<Memo>(this, R.layout.rowitem, memos);
        MaxHeightListView listView = (MaxHeightListView) findViewById(R.id.search_memo_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new MyListener());
        TextView emtpyText = (TextView) findViewById(R.id.result_empty_list);
        listView.setEmptyView(emtpyText);
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d("search", "onResume");
        Set<String> set = getSharedPreferences(NAME, MODE_PRIVATE).getStringSet(KEY, new HashSet<String>());
        Log.d("search", "size=" + set.size());
        if(!set.isEmpty()){
            setMemos(new ArrayList<String>(set));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_result, menu);
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
            Intent intent = new Intent(SearchResult.this, MemoDetail.class);
            intent.putExtra("memo", memos.get(position));
            startActivity(intent);
        }
    }
}
