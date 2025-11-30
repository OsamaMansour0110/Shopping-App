package com.example.onlineshopping.DateBaseSql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Login_Register_DBHelper extends SQLiteOpenHelper {
    public Login_Register_DBHelper(Context context) {
        super(context,"UserData",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create table Userdetails(username TEXT primary key, password TEXT , question TEXT, birthdate TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop table if exists Userdetails");
    }


    public Cursor fetchAllUsers(){
        SQLiteDatabase DB = this.getReadableDatabase();
        String[] rowDetails={"username","password","question","birthdate"};
        Cursor cursor =DB.query("Userdetails",rowDetails,null,null,null,null,null);
        if (cursor != null)
            cursor.moveToFirst();
        DB.close();
        return cursor;
    }


    public Boolean inserData(String username , String password , String answer, String birthdate){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username" , username);
        contentValues.put("password" , password);
        contentValues.put("question" , answer);
        contentValues.put("birthdate" , birthdate);

        long result = DB.insert("Userdetails",null,contentValues);
        if(result == -1){
            return false;
        }
        else{
            return true;
        }

    }

    public Boolean checkUserName(String username) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from Userdetails where username = ?",new String[] {username});

        if(cursor.getCount() > 0){
            return true;
        }
        else{
            return false;
        }

    }

    public Boolean checkUserName_password(String username , String password) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from Userdetails where username = ? and password = ?",new String[] {username , password});

        if(cursor.getCount() > 0){
            return true;
        }
        else{
            return false;
        }
    }
    public Boolean checkUserName_question(String username , String answer) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from Userdetails where username = ? and question = ?",new String[] {username , answer});

        if(cursor.getCount() > 0){
            return true;
        }
        else{
            return false;
        }

    }

    public Boolean update_password(String username , String newpassword , String answer) {
        SQLiteDatabase DB = this.getWritableDatabase();
       ContentValues row = new ContentValues();
       row.put("username" , username);
       row.put("password" , newpassword);
       row.put("question" , answer);
       DB.update("Userdetails",row,"username like ?",new String[]{username});
       DB.close();
        return true;

    }
}
