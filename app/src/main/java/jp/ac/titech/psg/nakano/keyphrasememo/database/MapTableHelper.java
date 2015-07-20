package jp.ac.titech.psg.nakano.keyphrasememo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

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
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("memoId", memoId);
        values.put("tagId", tagId);
        return db.insert(TABLE, null, values);
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
}
