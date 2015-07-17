package jp.ac.titech.psg.nakano.keyphrasediary.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Date;

/**
 * Created by nakanomizuki on 15/07/17.
 */
public class DiaryTableHelper extends SQLiteOpenHelper {

    private static final String TAG = "DiaryTableHelper";
    private static final String DB_NAME = "diary.db";
    private static final String TABLE = "diary";
    private static final int DB_VERSION = 1;
    private static final String CREATE_TABLE = "create table " + TABLE + "("
            + "id integer primary key autoincrement,"
            + "title text not null,"
            + "content text,"
            + "cdate text not null,"
            + "udate text not null"
            + ");";

    public DiaryTableHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer){

    }

    public void insert(String title, String content){
        Log.d(TAG, "call insert(title=" + title + ", content=" + content + ")");
        Date date = new Date();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("content", content);
        values.put("cdate", date.toString());
        values.put("udate", date.toString());
        db.insert(TABLE, null, values);
        Log.d(TAG, "inserted");
    }
}
