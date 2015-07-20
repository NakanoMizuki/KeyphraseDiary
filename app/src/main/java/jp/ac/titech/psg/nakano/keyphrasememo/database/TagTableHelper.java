package jp.ac.titech.psg.nakano.keyphrasememo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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

}
