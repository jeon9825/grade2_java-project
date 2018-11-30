package kr.co.memo.jiyoon.memo.database;

import android.provider.BaseColumns;

public final class Database {

    public static final class CreateDB implements BaseColumns{
        public static final String TABLENAME = "Memo";
        public static final String CREATETABLE =
                "create table "+TABLENAME+"("
                        +"POSITION"+" integer primary key, "
                        +"CATEGORY"+" text,"
                        +"MEMO"+" text, "
                        +"DATE"+" text"+");";
    }
}