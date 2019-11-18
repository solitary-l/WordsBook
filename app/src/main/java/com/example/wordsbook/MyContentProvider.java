package com.example.wordsbook;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class MyContentProvider extends ContentProvider {
    public static final int WordsBook_DIR = 0;
    public static final int WordsBook_ITEM = 1;
    public static final String AUTHORITY = "com.example.wordsbook.provider";
    public static UriMatcher uriMatcher;

    private database dbHelper;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "wordbook", WordsBook_DIR);
        uriMatcher.addURI(AUTHORITY, "wordbook/#", WordsBook_ITEM);
    }


    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        dbHelper = new database(getContext(), "wordsbookb.db", null, 2);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
        //查询数据
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        switch (uriMatcher.match(uri)) {
            case WordsBook_DIR:
                cursor = db.query("WordsBook", projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case WordsBook_ITEM:
                String word = uri.getPathSegments().get(1);
                cursor = db.query("WordsBook", projection, "id = ?", new String[]{word}, null, null, sortOrder);
                break;
            default:
                break;
        }
        return cursor;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        //添加数据
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Uri uriReturn = null;
        switch (uriMatcher.match(uri)) {
            case WordsBook_DIR:
            case WordsBook_ITEM:
                long newWord = db.insert("WordsBook", null, values);
                uriReturn = Uri.parse("content://" + AUTHORITY + "/wordbook/" + newWord);
                break;
            default:
                break;
        }
        return uriReturn;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        //更新数据
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int updatedRows = 0;
        switch (uriMatcher.match(uri)){
            case WordsBook_DIR:
                updatedRows = db.update("WordsBook",values,selection,selectionArgs);
                break;
            case WordsBook_ITEM:
                String word = uri.getPathSegments().get(1);
                updatedRows = db.update("WordsBook",values,"id=?",new String[]{word});
                break;
            default:
                break;
        }
        return updatedRows;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        //删除数据
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int deletedRows = 0;
        switch (uriMatcher.match(uri)){
            case WordsBook_DIR:
                deletedRows = db.delete("WordsBook",selection,selectionArgs);
                break;
            case WordsBook_ITEM:
                String word = uri.getPathSegments().get(1);
                deletedRows = db.delete("WordsBook","id=?",new String[]{word});
                break;
            default:
                break;
        }
        return deletedRows;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        switch (uriMatcher.match(uri)){
            case WordsBook_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.wordsbook.provider.wordbook";
            case WordsBook_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.wordsbook.provider.wordbook";

        }
        return null;

    }
}





