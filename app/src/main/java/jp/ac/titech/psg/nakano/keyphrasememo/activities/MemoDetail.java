package jp.ac.titech.psg.nakano.keyphrasememo.activities;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import jp.ac.titech.psg.nakano.keyphrasememo.R;
import jp.ac.titech.psg.nakano.keyphrasememo.database.MemoTableHelper;
import jp.ac.titech.psg.nakano.keyphrasememo.model.Memo;

public class MemoDetail extends AppCompatActivity {

    private static final String TAG = "MemoDetail";
    private Memo memo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_detail);

        memo = (Memo) getIntent().getSerializableExtra("memo");
        Log.d(TAG, memo.toString());

        // fragment
        MemoFragment fragment = new MemoFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(MemoFragment.ARG_PARAM, memo);
        fragment.setArguments(bundle);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.memo_detail_container, fragment);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_memo_detail, menu);
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

    public void clickDelete(View v){
        MemoTableHelper tableHelper = new MemoTableHelper(this);
        tableHelper.deleteMemo(memo.getId());
    }
}
