package jp.ac.titech.psg.nakano.keyphrasememo.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import jp.ac.titech.psg.nakano.keyphrasememo.R;
import jp.ac.titech.psg.nakano.keyphrasememo.activities.fragments.MarkDownViewCreator;
import jp.ac.titech.psg.nakano.keyphrasememo.database.MemoTableHelper;
import jp.ac.titech.psg.nakano.keyphrasememo.database.TableConnector;
import jp.ac.titech.psg.nakano.keyphrasememo.model.Memo;
import jp.ac.titech.psg.nakano.keyphrasememo.model.Tag;

public class MemoDetail extends AppCompatActivity {

    private static final String TAG = "MemoDetail";
    private static final int REQUEST_CODE = 1;
    public static final int RESULT_CODE_CHANGED = 100;

    private Memo memo;
    private AlertDialog deleteDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_detail);

        memo = (Memo) getIntent().getSerializableExtra("memo");
        Log.d(TAG, memo.toString());

        // dialog for deleteButton
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.delete_dialog_message);
        builder.setPositiveButton(R.string.delete_dialog_positive, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MemoTableHelper tableHelper = new MemoTableHelper(MemoDetail.this);
                tableHelper.deleteMemo(memo.getId());
                finish();
            }
        });
        builder.setNegativeButton(R.string.delete_dialog_negative, null);
        deleteDialog = builder.create();


        ViewGroup container = (ViewGroup) findViewById(R.id.memo_detail_container);
        LayoutInflater inflater = getLayoutInflater();
        inflater.inflate(R.layout.fragment_preview, container);

        setMemoValue();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        if(requestCode != REQUEST_CODE) return;
        if(resultCode == RESULT_CODE_CHANGED){
            TableConnector tableConnector = new TableConnector(this);
            Memo newMemo = tableConnector.getMemo(memo.getId());
            memo = newMemo;
            setMemoValue();
        }
    }

    private void setMemoValue(){
        ((TextView)findViewById(R.id.preview_fragment_title)).setText(memo.getTitle());
        List<Tag> tags = memo.getTags();
        TextView tagView = (TextView) findViewById(R.id.preview_fragment_tag);
        if(tags != null && !tags.isEmpty()) {
            String str = "";
            for (Tag tag : tags) {
                str += tag.getName() + ",";
            }
            tagView.setText(str);
        }else{
            tagView.setText("");
        }
        new MarkDownViewCreator(this).createMarkDownView(memo.getContent());
    }

    public void clickDelete(View v){
        deleteDialog.show();
    }

    public void clickEdit(View v){
        Intent intent = new Intent(this, EditMemo.class);
        intent.putExtra("memo", memo);
        startActivityForResult(intent, REQUEST_CODE);
    }
}
