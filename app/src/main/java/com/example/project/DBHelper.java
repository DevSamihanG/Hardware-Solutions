package com.example.project;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private Context context;

    private static final String DATABASE_NAME = "usersDb.db";

    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "myusers";

    private static final String COLUMN_ID = "id";

    private static final String COLUMN_NAME = "username";

    private static final String COLUMN_EMAIL = "Email";

    private static final String COLUMN_PASSWORD = "password";

    private static final String COLUMN_PHONE = "Phone";

    DBHelper(@Nullable Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="CREATE TABLE " +TABLE_NAME +
                " (" +COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME +" TEXT, " +
                COLUMN_EMAIL + " TEXT, " +
                COLUMN_PASSWORD + " TEXT, " +
                COLUMN_PHONE + " TEXT); ";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertuser(String username, String Email, String password, String Phone) {
        SQLiteDatabase Mydb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, username);
        contentValues.put(COLUMN_EMAIL, Email);
        contentValues.put(COLUMN_PASSWORD, password);
        contentValues.put(COLUMN_PHONE, Phone);
        long result= Mydb.insert(TABLE_NAME, null, contentValues);
        if(result ==-1)
        {
            Toast.makeText(context,"Failure",Toast.LENGTH_LONG).show();
            return false;
        }
        else {

            Toast.makeText(context,"Registration Successful",Toast.LENGTH_LONG).show();
            Intent i =new Intent(context,MainActivity.class);
            context.startActivity(i);
            return true;
        }
    }

    void updateData(String id, String username, String Email, String password, String Phone)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_NAME,username);
        cv.put(COLUMN_EMAIL,Email);
        cv.put(COLUMN_PASSWORD,password);
        cv.put(COLUMN_PHONE,Phone);

        long result =  db.update(TABLE_NAME,cv,"Phone=?",new String[]{Phone});
        if(result ==-1)
        {
            Toast.makeText(context,"No result found",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(context,"Updated Successfully",Toast.LENGTH_LONG).show();
        }
    }


    void delete(String Phone)
    {
        SQLiteDatabase Mydb=this.getWritableDatabase();
        long result= Mydb.delete(TABLE_NAME,"Phone = ?",new String[]{Phone});
        if(result ==-1)
        {
            Toast.makeText(context,"No result found",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(context,"Deleted Successfully",Toast.LENGTH_LONG).show();

        }

    }

    public Boolean checkusername(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[] {username});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from myusers where username = ? and password = ?", new String[] {username, password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Cursor retrieve(String Phone) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();

        return MyDatabase.rawQuery("SELECT * FROM myusers WHERE Phone=?", new String[]{Phone});
    }
    public Cursor showall() {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();

        return MyDatabase.rawQuery("SELECT * FROM myusers",null);
    }
}
