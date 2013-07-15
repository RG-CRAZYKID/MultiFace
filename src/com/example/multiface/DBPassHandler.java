package com.example.multiface;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBPassHandler extends SQLiteOpenHelper
{
private static final int DATABASE_VERSION = 1;
private static final String DATABASE_NAME = "PepperSpray";
private static final String TABLE_NAME = "empass";

private static final String KEY_ID = "id"; 
private static final String KEY_PASS= "password";

public DBPassHandler(Context context)
{
super(context, DATABASE_NAME, null, DATABASE_VERSION);
}

public void onCreate(SQLiteDatabase db)
{
String CREATE_PASS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_PASS + "INTEGER" + ")";
db.execSQL(CREATE_PASS_TABLE);
}

public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
{
db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
onCreate(db);
}

void addpasswd(Empass emp) 
{
        SQLiteDatabase db = this.getWritableDatabase();
 	ContentValues values = new ContentValues();
        values.put(KEY_PASS, emp.getPass()); 
        db.insert(TABLE_NAME, null, values);
        db.close(); 
}

Empass getpasswd() 
{
        SQLiteDatabase db = this.getReadableDatabase();
 
        Cursor cursor = db.rawQuery("SELECT  * FROM " + TABLE_NAME,null);
        if (cursor != null)
            cursor.moveToFirst();
 
        Empass emp = new Empass(Integer.parseInt(cursor.getString(0)),Integer.parseInt(cursor.getString(1)));
        return emp;
}

public int updatepasswd(Empass emp) 
{
SQLiteDatabase db = this.getWritableDatabase();
ContentValues values = new ContentValues();
values.put(KEY_PASS, emp.getPass());
 
return db.update(TABLE_NAME, values, KEY_ID + " = ?",
new String[] { String.valueOf(emp.getid()) });
}

}