package freijer.app.sucktest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DataHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "contactDb";
    public static final String TABLE_NAME = "contacts";

    public static final String KEY_ID = "_id";
    public static final String KEY_SCORE = "scores";
    public static final String KEY_LVL = "lvls";
    public static final String KEY_TRYS = "tryss";

    SQLiteDatabase database;
    ContentValues contentValues = new ContentValues();

    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(" + KEY_ID
            + " integer primary key," + KEY_SCORE + " text," +  KEY_LVL+ " text," +  KEY_TRYS + " text" +  ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(db);
    }

    public void WriteDB(String score, String lvl, String tryss){
        database = getWritableDatabase();
        contentValues.put(KEY_SCORE, score);
        contentValues.put(KEY_LVL, lvl);
        contentValues.put(KEY_TRYS, tryss);

        database.insert(TABLE_NAME, null, contentValues);
    }


    public void ReadDB(){
        Cursor cursor = database.query(TABLE_NAME, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(KEY_ID);
            int scoreIndex = cursor.getColumnIndex(KEY_SCORE);
            int lvlIndex = cursor.getColumnIndex(KEY_LVL);
            int tryslIndex = cursor.getColumnIndex(KEY_TRYS);
            do {
                Log.d("mLog", "ID = " + cursor.getInt(idIndex) +
                        ", очки = " + cursor.getString(scoreIndex) +
                        ", уровень = " + cursor.getString(lvlIndex) +
                        ", попыток = " + cursor.getString(tryslIndex) );
            } while (cursor.moveToNext());
        } else
            Log.d("mLog","0 rows");

        cursor.close();
    }

    public void DeleteDB(){
        database.delete(TABLE_NAME, null, null);
        database.close();
    }

}
