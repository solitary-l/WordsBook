package com.example.wordsbook;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class database extends SQLiteOpenHelper {
    //创建单词表
    public static final String CREAT_BOOK =
            "create table WordsBook ("
                    + "word varchar(50) primary key,"
                    + "meanings text,"
                    + "exampleSentence text)";

    private Context mContext;

    public database (Context context, String name, SQLiteDatabase.CursorFactory factory ,int version){

        super(context,name,factory,version);
        mContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREAT_BOOK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
