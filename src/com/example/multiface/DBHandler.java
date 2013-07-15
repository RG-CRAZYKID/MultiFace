package com.example.multiface;

import java.util.ArrayList;
import java.util.List;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class DBHandler extends SQLiteOpenHelper

{
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "MultiFace";
	private static final String TABLE_NAME = "profile";
	
	private static final String KEY_ID = "id"; 
	private static final String KEY_NAME="profile_name";
	private static final String KEY_PASS= "password";
	private static final String KEY_APPS="apps_table";

	private static final String KEY_IDA="id";
	private static final String KEY_APP="App_Name";
	private static final String KEY_PACK="Package_Name";
	private static final String KEY_IC="Icon_Resource";
	private static final String KEY_INT="Intent_Name";
	
	
public DBHandler(Context context)
{
	super(context, DATABASE_NAME, null, DATABASE_VERSION);
}
	
	
public void onCreate(SQLiteDatabase db)
{
	
	Log.d(" inside "," on create of db handler ");
	String CREATE_PROFILE_TABLE = " CREATE TABLE " + TABLE_NAME + " ( "
            + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_NAME + " TEXT, "
            + KEY_PASS + " INTEGER, " + KEY_APPS + " TEXT " + " ) ";
db.execSQL(CREATE_PROFILE_TABLE);

int id=0;
String pname="Default";
long pass=(long)12345;
String aname=pname+"_Apps";

Profile_frame pf=new Profile_frame(id,pname,pass,aname);
Log.d(" inside "," Add nos ");    
ContentValues values = new ContentValues();
values.put(KEY_ID, pf.getid());
values.put(KEY_NAME, pf.getProfName()); 
values.put(KEY_PASS, pf.getPass()); 
values.put(KEY_APPS, pf.getAppTab());
db.insert(TABLE_NAME, null, values);

Log.d(" default profile created"," now ");	

String TABLE_APP=pf.getAppTab();
String CREATE_TAB=" CREATE TABLE " + TABLE_APP + " ( " + KEY_IDA + " INTEGER PRIMARY KEY, " + KEY_APP + " TEXT, "+ KEY_PACK + " TEXT, " +KEY_IC + " TEXT, " +KEY_INT + " TEXT " +" ) " ;
db.execSQL(CREATE_TAB);

Log.d(" apps table default "," created ");

//insert system apps

}

public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
{
	db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
	onCreate(db);
}


public void createAppsTab(Profile_frame pf)
{
	
	
	SQLiteDatabase db=this.getWritableDatabase();
	String TABLE_APP=pf.getAppTab();
	String CREATE_TAB=" CREATE TABLE " + TABLE_APP + " ( " + KEY_IDA + " INTEGER PRIMARY KEY, " + KEY_APP + " TEXT, "+ KEY_PACK + " TEXT, " +KEY_IC + " TEXT, " +KEY_INT + " TEXT " +" ) " ;
	db.execSQL(CREATE_TAB);
		
}


public int getLastId()
{
	Log.d("inside "," get last id");
	SQLiteDatabase db=this.getReadableDatabase();
	
	String SELECT_QUERY=" SELECT * FROM " + TABLE_NAME;
	try
	{
	Cursor cursor=db.rawQuery(SELECT_QUERY,null);
	if(cursor!=null)
		cursor.moveToLast();

	//Profile_frame pf=new Profile_frame();
	Integer  id=Integer.parseInt(cursor.getString(0));
	
	Log.d("completed "," get last id");
	
	return id;
	}
	catch(CursorIndexOutOfBoundsException e)
	{
	return 0;	
	}
}

public void insertProfile(Profile_frame pf)
{
	
	Log.d("inside "," insert profile ");
	
	SQLiteDatabase db=this.getWritableDatabase();
	ContentValues values = new ContentValues();
	values.put(KEY_ID, pf.getid());
	values.put(KEY_NAME, pf.getProfName()); 
	values.put(KEY_PASS, pf.getPass()); 
	values.put(KEY_APPS, pf.getAppTab());
	db.insert(TABLE_NAME, null, values);

	db.execSQL("DROP TABLE IF EXISTS " + pf.getAppTab());

	Log.d("completed "," inserting");
	Log.d(" creating apps table ",pf.getAppTab());
	String TABLE_APP=pf.getAppTab();
	String CREATE_TAB=" CREATE TABLE " + TABLE_APP + " ( " + KEY_IDA + " INTEGER PRIMARY KEY, " + KEY_APP + " TEXT, "+ KEY_PACK + " TEXT, " +KEY_IC + " TEXT, " +KEY_INT + " TEXT " +" ) " ;
	db.execSQL(CREATE_TAB);
	
	Log.d("Apps table created ",pf.getAppTab());
	
	//get and insert system apps	
	
}

// Getting All Contacts
public List<Profile_frame> getAllProfiles() {
	
	Log.d("inside "," get all profiles ");
    List<Profile_frame> profileList = new ArrayList<Profile_frame>();
    // Select All Query
    String selectQuery = "SELECT  * FROM " + TABLE_NAME;

    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor = db.rawQuery(selectQuery, null);

    // looping through all rows and adding to list
    if (cursor.moveToFirst()) {
        do {
            Profile_frame pff = new Profile_frame();
            pff.setid(Integer.parseInt(cursor.getString(0)));
            pff.setname(cursor.getString(1));
            pff.setpass(Integer.parseInt(cursor.getString(2)));
            pff.setapp(cursor.getString(3));
            // Adding contact to list
            profileList.add(pff);
        } while (cursor.moveToNext());
    }

    // return profile list
    return profileList;
}


//Getting All Apps
public List<Apps_frame> getAllApps(Profile_frame pf) 
{
 
String App_Tab=pf.getAppTab();	
List<Apps_frame> AppList = new ArrayList<Apps_frame>();
 // Select All Query
 String selectQuery = "SELECT  * FROM " + App_Tab;
try
{
 SQLiteDatabase db = this.getReadableDatabase();
 Cursor cursor = db.rawQuery(selectQuery, null);

 // looping through all rows and adding to list
 if (cursor.moveToFirst()) {
     do {
         Apps_frame af = new Apps_frame();
         af.setid(Integer.parseInt(cursor.getString(0)));
         af.setApps(cursor.getString(1));
         af.setPackage(cursor.getString(2));
         af.setIconRes(cursor.getString(3));
         af.setIntentSrc(cursor.getString(4));
         
         // Adding contact to list
         AppList.add(af);
     } while (cursor.moveToNext());
 }

 // return contact list
 return AppList;
}
 catch(Exception e)
 {	
 	Log.d(" possibly "," cursor index out of bounds in get app ( multiple ) ");
 	return null;	
 }

}


public int getProfileCount() {
    
	String countQuery = "SELECT  * FROM " + TABLE_NAME;
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor = db.rawQuery(countQuery, null);
    //cursor.close();

    // return count
    return cursor.getCount();

}

public void deleteProfile(Profile_frame pf) {
    
	String atab=pf.getAppTab();
	
	SQLiteDatabase db = this.getWritableDatabase();
    db.delete(TABLE_NAME, KEY_ID + " = ?",
            new String[] { String.valueOf(pf.getid()) });
    //SQLiteDatabase db =this.getWritableDatabase();
	//deleting app table associated with the profile to be deleted
    
    db.delete(atab, null, null);
	db.execSQL("DROP TABLE " + atab);
	Log.d(" app table "," deleted ");
    //db.close();

}

public int updateProfile(Profile_frame pf) {
    SQLiteDatabase db = this.getWritableDatabase();

    ContentValues values = new ContentValues();
    values.put(KEY_NAME, pf.getProfName());
    values.put(KEY_PASS, pf.getPass());

    // updating row
    return db.update(TABLE_NAME, values, KEY_ID + " = ?",
            new String[] { String.valueOf(pf.getid()) });
}

public void insertApp(Profile_frame pf,Apps_frame af)
{
	String tabn=pf.getAppTab();
	SQLiteDatabase db=this.getWritableDatabase();
	ContentValues values = new ContentValues();
	values.put(KEY_IDA, af.getid());
	values.put(KEY_APP, af.getappname());
	values.put(KEY_PACK, af.getPackagesName());
	values.put(KEY_IC, af.getIconRes());
	values.put(KEY_INT, af.getIntentSrc());
	db.insert(tabn, null, values);
}
Profile_frame getProfile(int ids) {
    SQLiteDatabase db = this.getReadableDatabase();

    Cursor cursor = db.query(TABLE_NAME, new String[] { KEY_ID,
            KEY_NAME, KEY_PASS, KEY_APPS }, KEY_ID + "=?",
            new String[] { String.valueOf(ids) }, null, null, null, null);
    if (cursor != null)
        cursor.moveToFirst();

    Profile_frame prof = new Profile_frame(Integer.parseInt(cursor.getString(0)),
            cursor.getString(1), Integer.parseInt(cursor.getString(2)),cursor.getString(3));
    return prof;
}
Apps_frame getApp(int ids,Profile_frame pf) {
    SQLiteDatabase db = this.getReadableDatabase();

    String Table=pf.getAppTab();
    try{
    Cursor cursor = db.query(Table, new String[] { KEY_IDA,
            KEY_APP, KEY_PACK, KEY_IC, KEY_INT }, KEY_IDA + "=?",
            new String[] { String.valueOf(ids) }, null, null, null, null);
    if (cursor != null)
        cursor.moveToFirst();

    Apps_frame a = new Apps_frame(Integer.parseInt(cursor.getString(0)),
            cursor.getString(1),cursor.getString(2));
    return a;
    }
    catch(Exception e)
    {	
    	Log.d(" possibly "," cursor index out of bounds in get app ( single ) ");
    	return null;	
    }
}
public void deleteApp(Apps_frame af,Profile_frame pf)
{
	
	SQLiteDatabase db=this.getWritableDatabase();
	db.delete(pf.getAppTab(), KEY_IDA + " = ?",
            new String[] { String.valueOf(af.getid()) });
    Log.d(" app "," removed ");
}

}
