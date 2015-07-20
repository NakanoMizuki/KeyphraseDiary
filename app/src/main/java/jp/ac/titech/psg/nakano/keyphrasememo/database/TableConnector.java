package jp.ac.titech.psg.nakano.keyphrasememo.database;

import android.content.Context;
import android.util.Log;

import java.util.Set;

/**
 * Created by nakanomizuki on 15/07/20.
 */
public class TableConnector {
    private static final String TAG = "TabeleConnector";

    public static void insertMemo(Context context, String title, String content, Set<String> tags){
        Log.d(TAG, "insertMemo");
        Log.d(TAG, "title=" + title + ", content=" + content);
        for(String tag:tags){
            Log.d(TAG, "tag=" + tag);
        }
        MemoTableHelper memoTableHelper = new MemoTableHelper(context);
        long id = memoTableHelper.insert(title, content);
    }
}
