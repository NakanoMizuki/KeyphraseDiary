package jp.ac.titech.psg.nakano.keyphrasememo.database;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jp.ac.titech.psg.nakano.keyphrasememo.model.Memo;
import jp.ac.titech.psg.nakano.keyphrasememo.model.Tag;

/**
 * Created by nakanomizuki on 15/07/20.
 */
public class TableConnector {
    private static final String TAG = "TabeleConnector";

    private final Context context;

    public TableConnector(Context context){
        this.context = context;
    }

    public void insertMemo(String title, String content, List<String> tags){
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

    public void updateMemo(long memoId, String title, String content, List<String> tags){
        Log.d(TAG, "updateMemo");
        Log.d(TAG, "title=" + title + ", content=" + content);
        for(String tag:tags){
            Log.d(TAG, "tag=" + tag);
        }
        MemoTableHelper memoTableHelper = new MemoTableHelper(context);
        memoTableHelper.update(memoId, title, content);
        TagTableHelper tagTableHelper = new TagTableHelper(context);
        Set<Long> tagIds = tagTableHelper.updateTag(tags);
        MapTableHelper mapTableHelper = new MapTableHelper(context);
        mapTableHelper.update(memoId, tagIds);
    }

    public Memo getMemo(long memoId){
        Log.d(TAG, "call getMemo(memoId=" + memoId);
        MemoTableHelper memoTableHelper = new MemoTableHelper(context);
        Memo memo = memoTableHelper.getMemo(memoId);
        MapTableHelper mapTableHelper = new MapTableHelper(context);
        List<Long> tagIds = mapTableHelper.getTagIds(memoId);
        TagTableHelper tagTableHelper = new TagTableHelper(context);
        List<Tag> tags = tagTableHelper.getTags(tagIds);
        memo.setTags(tags);
        Log.d(TAG, "getMemo return:" + memo.toString());
        return memo;
    }

    public List<Memo> getAllMemo(){
        MemoTableHelper memoTableHelper = new MemoTableHelper(context);
        List<Memo> memos = memoTableHelper.getAllMemo();
        TagTableHelper tagTableHelper = new TagTableHelper(context);
        MapTableHelper mapTableHelper = new MapTableHelper(context);
        for(Memo memo: memos){
            List<Long> tagIds = mapTableHelper.getTagIds(memo.getId());
            List<Tag> tags = tagTableHelper.getTags(tagIds);
            memo.setTags(tags);
        }
        return memos;
    }

    public List<Tag> getAllTag(){
        TagTableHelper tagTableHelper = new TagTableHelper(context);
        return tagTableHelper.getAllTags();
    }

    public List<Memo> getMemoHasTag(List<String> tagIds){
        if(tagIds == null || tagIds.isEmpty()) return new ArrayList<Memo>();
        long tagId = Long.parseLong(tagIds.get(0));
        tagIds.remove(0);
        MapTableHelper mapTableHelper = new MapTableHelper(context);
        Set<Long> memoIds = mapTableHelper.getMemoIds(tagId);

        for(String idStr:tagIds){
            long id = Long.parseLong(idStr);
            if(memoIds.isEmpty()) break;
            Set<Long> set = mapTableHelper.getMemoIds(id);
            memoIds.retainAll(set);
        }

        MemoTableHelper memoTableHelper = new MemoTableHelper(context);
        return memoTableHelper.getMemo(memoIds);
    }

}
