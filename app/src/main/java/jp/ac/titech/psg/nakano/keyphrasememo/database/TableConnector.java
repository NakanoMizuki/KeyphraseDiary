package jp.ac.titech.psg.nakano.keyphrasememo.database;

import android.content.Context;
import android.util.Log;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jp.ac.titech.psg.nakano.keyphrasememo.model.Memo;

/**
 * Created by nakanomizuki on 15/07/20.
 */
public class TableConnector {
    private static final String TAG = "TabeleConnector";

    private final Context context;

    public TableConnector(Context context){
        this.context = context;
    }

    public void insertMemo(String title, String content, Set<String> tags){
        Log.d(TAG, "insertMemo");
        Log.d(TAG, "title=" + title + ", content=" + content);
        for(String tag:tags){
            Log.d(TAG, "tag=" + tag);
        }
        MemoTableHelper memoTableHelper = new MemoTableHelper(context);
        long memoId = memoTableHelper.insert(title, content);
        Set<Long> tagIds = new HashSet<Long>();
        TagTableHelper tagTableHelper = new TagTableHelper(context);
        for(String name: tags){
            tagIds.add(tagTableHelper.insert(name));
        }
        MapTableHelper mapTableHelper = new MapTableHelper(context);
        for(Long tagId: tagIds){
            mapTableHelper.insert(memoId, tagId);
        }
    }

    public List<Memo> getAllMemo(){
        MemoTableHelper memoTableHelper = new MemoTableHelper(context);
        return memoTableHelper.getAllMemo();
    }

}
