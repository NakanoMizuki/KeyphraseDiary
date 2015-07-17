package jp.ac.titech.psg.nakano.keyphrasediary.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by nakanomizuki on 15/07/17.
 */
public class DiaryTableHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "diary.db";
    private static final String TABLE = "diary";
    private static final int DB_VERSION = 1;
    private static final String CREATE_TABLE =
            "create table " + TABLE + "("
            + "id integer primary key autoincrement,"
            + "title varchar(30) not null,"
            + "cdate date not null,"
            + "udate date not null,"
            + "content varchar(255)"
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
}
