package jp.ac.titech.psg.nakano.keyphrasememo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jp.ac.titech.psg.nakano.keyphrasememo.model.Memo;

/**
 * Created by nakanomizuki on 15/07/17.
 */
public class MemoTableHelper extends SQLiteOpenHelper {

    private static final String TAG = "MemoTableHelper";
    private static final String DB_NAME = "memo.db";
    private static final String TABLE = "memo";
    private static final int DB_VERSION = 1;
    private static final String CREATE_TABLE = "create table " + TABLE + "("
            + "id integer primary key autoincrement,"
            + "title text not null,"
            + "content text,"
            + "cdate text not null,"
            + "udate text not null"
            + ");";

    public MemoTableHelper(Context context){
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

    public List<Memo> getAllMemo(){
        Log.d(TAG, "call getAllMemo");
        List<Memo> diaries = new ArrayList<Memo>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE + ";", null);
        boolean isEOF = cursor.moveToFirst();
        while(isEOF){
            diaries.add(getMemoFromCursor(cursor));
            isEOF = cursor.moveToNext();
        }
        cursor.close();

        return  diaries;
    }

    private Memo getMemoFromCursor(Cursor c){
        String title = c.getString(c.getColumnIndex("title"));
        String content = c.getString(c.getColumnIndex("content"));

        SimpleDateFormat sdf = new SimpleDateFormat();
        Date cdate, udate;
        try {
            cdate = sdf.parse(c.getString(c.getColumnIndex("cdate")));
            udate = sdf.parse(c.getString(c.getColumnIndex("udate")));
        }catch (ParseException e){
            cdate = null;
            udate = null;
        }

        return new Memo(title, content, cdate, udate);
    }

}
