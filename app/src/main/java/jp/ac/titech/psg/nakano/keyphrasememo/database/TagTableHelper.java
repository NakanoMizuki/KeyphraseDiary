package jp.ac.titech.psg.nakano.keyphrasememo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import jp.ac.titech.psg.nakano.keyphrasememo.model.Tag;

/**
 * Created by nakanomizuki on 15/07/20.
 */
public class TagTableHelper extends SQLiteOpenHelper {

    private static final String TAG = "TagTableHelper";
    private static final String DB_NAME = "tag.db";
    private static final String TABLE = "tag";
    private static final int DB_VERSION = 1;
    private static final String CREATE_TABLE = "create table " + TABLE +"("
            + "id integer primary key autoincrement,"
            + "name text not null"
            + ");";

    public TagTableHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer){

    }


    public long insert(String name){
        Log.d(TAG, "call insert(name=" + name + ")");
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        return db.insert(TABLE, null, values);
    }

    public Tag getTag(long tagId){
        Log.d(TAG, "call getTag(tagId=" + tagId + ")");
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select id, name from " + TABLE + "wher id = " + tagId, null);
        cursor.moveToFirst();
        return createTagFromCursor(cursor);
    }

    public List<Tag> getTags(List<Long> tagIds){
        Log.d(TAG, "call getTags");
        List<Tag> tags = new ArrayList<Tag>();
        if(tagIds.isEmpty()) return tags;

        String targetIds = "";
        for(Long tagId: tagIds){
            targetIds += "'" + tagId + "',";
        }
        targetIds = targetIds.substring(0, targetIds.length() -1);
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select id, name from " + TABLE + " where id in (" + targetIds + ");", null);

        boolean isEOF = cursor.moveToFirst();
        while(isEOF){
            tags.add(createTagFromCursor(cursor));
            isEOF = cursor.moveToNext();
        }
        cursor.close();
        return tags;
    }
    private Tag createTagFromCursor(Cursor c){
        long tagId = c.getLong(c.getColumnIndex("id"));
        String name = c.getString(c.getColumnIndex("name"));
        return new Tag(tagId, name);
    }

}
