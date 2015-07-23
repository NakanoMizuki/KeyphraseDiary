package jp.ac.titech.psg.nakano.keyphrasememo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by nakanomizuki on 15/07/20.
 */
public class MapTableHelper extends SQLiteOpenHelper {

    private static final String TAG = "MapTableHelper";
    private static final String DB_NAME = "map.db";
    private static final String TABLE = "map";
    private static final int DB_VERSION = 1;
    private static final String CREATE_TABLE = "create table " + TABLE + "("
            + "id integer primary key autoincrement,"
            + "memoId integer,"
            + "tagId integer"
            + ");";


    public MapTableHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer){

    }

    public long insert(long memoId, long tagId){
        ContentValues values = new ContentValues();
        values.put("memoId", memoId);
        values.put("tagId", tagId);
        SQLiteDatabase db = getWritableDatabase();
        return db.insert(TABLE, null, values);
    }

    public void update(long memoId, Set<Long> tagIds){
        if(tagIds.isEmpty()){
            Log.d(TAG, "memoId=" + memoId + " has no tag");
            removeAllTag(memoId);
            return;
        }
        String newIdStr = "(";
        for(Long tagId:tagIds){
            newIdStr += tagId + ",";
        }
        newIdStr = newIdStr.substring(0, newIdStr.length() - 1) + ")";
        Log.d(TAG, "newIdStr=" + newIdStr);
        SQLiteDatabase db = getWritableDatabase();

        String deleteQuery = "memoId = " + memoId + " and tagId not in " + newIdStr;
        Log.d(TAG, "update:deleteQuery=" + deleteQuery);
        db.delete(TABLE, deleteQuery, null);

        Cursor cursor = db.rawQuery("select tagId from " + TABLE + " where memoId = " + memoId, null);
        Set<Long> oldTagIds = getTagIdsFromCursor(memoId, cursor);
        for (Long newId: tagIds){
            if(!oldTagIds.contains(newId)){
                ContentValues values = new ContentValues();
                values.put("memoId", memoId);
                values.put("tagId", newId);
                db.insert(TABLE, null, values);
                Log.d(TAG, "update:insert(tagId=" + newId + ")");
            }
        }
    }
    private void removeAllTag(long memoId){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE, "memoId=" + memoId, null);
    }
    private Set<Long> getTagIdsFromCursor(long memoId, Cursor cursor){
        Set<Long> tagIds = new HashSet<Long>();
        boolean isEOF = cursor.moveToFirst();
        while (isEOF){
            tagIds.add(cursor.getLong(cursor.getColumnIndex("tagId")));
            isEOF = cursor.moveToNext();
        }
        return tagIds;
    }

    public List<Long> getTagIds(long memoId){
        List<Long> tagIds = new ArrayList<Long>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select tagId from " + TABLE + " where memoId = " + memoId + ";", null);
        boolean isEOF = cursor.moveToFirst();
        while (isEOF){
            tagIds.add(cursor.getLong(cursor.getColumnIndex("tagId")));
            isEOF = cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return tagIds;
    }

    public Set<Long> getMemoIds(long tagId){
        Set<Long> memoIds = new HashSet<Long>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select memoId from " + TABLE + " where tagId = " + tagId + ";", null);
        boolean isEOF = cursor.moveToFirst();
        while (isEOF){
            memoIds.add(cursor.getLong(cursor.getColumnIndex("memoId")));
            isEOF = cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return memoIds;
    }
}
