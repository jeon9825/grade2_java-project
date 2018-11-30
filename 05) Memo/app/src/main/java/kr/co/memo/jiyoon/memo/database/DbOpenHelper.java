package kr.co.memo.jiyoon.memo.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbOpenHelper {

    private static final String DATABASE_NAME = "Memo.db";
    private static final int DATABASE_VERSION = 1;
    public static SQLiteDatabase mDB;
    private DatabaseHelper mDBHelper;
    private Context mCtx;

    private class DatabaseHelper extends SQLiteOpenHelper {
        // 생성자
        public DatabaseHelper(Context context, String name,
                              SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        // 최초 DB를 만들때 한번만 호출된다.
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(Database.CreateDB.CREATETABLE);

        }

        // 버전이 업데이트 되었을 경우 DB를 다시 만들어 준다.
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+Database.CreateDB.TABLENAME);
            onCreate(db);
        }
    }

    public DbOpenHelper(Context context){
        this.mCtx = context;
    }

    public int find(int position)
    {
        Cursor cursor= mDB.rawQuery("select * from Memo",null);

        while(cursor.moveToNext()){
            if(cursor.getInt(0)==position)
                return cursor.getInt(1);
        }
        return -1;
    }

    public void insert(int position, String category, String memo, String date)
    {
        mDB.execSQL("INSERT INTO Memo" + " VALUES ("+ position +",'"+category +"','"+memo+"','"+date+"');");
    }

    public void update(int position, String category, String memo, String date)
    {
        mDB.execSQL("UPDATE Memo set CATEGORY="+category+", MEMO="+memo+", DATE="+date+" where POSITION="+position+";");
    }

    public DbOpenHelper open() throws SQLException {
        mDBHelper = new DatabaseHelper(mCtx, DATABASE_NAME, null, DATABASE_VERSION);
        mDB = mDBHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        mDB.close();
    }

}