package com.tp1.persist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.tp1.model.User;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "register.db";
    public static final String TABLE_NAME = "RegisterUser";
    public static final String COL_1 = "id";
    public static final String COL_2 = "username";
    public static final String COL_3 = "password";
    public static final String COL_4 = "email";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE RegisterUser (id INTEGER PRIMARY KEY AUTOINCREMENT, userName TEXT, password TEXT, email TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, user.getUsername());
        contentValues.put(COL_3, user.getPassword());
        contentValues.put(COL_4, user.getEmail());
        long res = db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return res;
    }

    public Integer checkUser(User user){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COL_1};
        String selection = COL_2 + "=?" + " and " + COL_3 + "=?";
        String[] selectionArgs = {user.getUsername(), user.getPassword()};
        Cursor cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null);
        int res = cursor.getCount();
        int id = 0;
        while (cursor.moveToNext()){
            id = cursor.getInt(0);
        }
        cursor.close();
        db.close();
        if(res > 0)
            return id;
        else
            return 0;
    }

    public User getUser(Integer id){
        User user = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COL_1 + "=?";
        String[] selectionArgs = {id.toString()};
        Cursor cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null);
        while(cursor.moveToNext()){
            user = new User();
            user.setUsername(cursor.getString(1));
            user.setPassword(cursor.getString(2));
            user.setEmail(cursor.getString(3));
        }
        return user;
    }

    public void dropTable(String tableName){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + tableName);
    }

    public void updateUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, user.getUsername());
        contentValues.put(COL_3, user.getPassword());
        contentValues.put(COL_4, user.getEmail());
        String selection = COL_1 + "=?";
        String[] selectionArgs = {user.getId().toString()};
        db.update(TABLE_NAME, contentValues, selection, selectionArgs);
    }
}
