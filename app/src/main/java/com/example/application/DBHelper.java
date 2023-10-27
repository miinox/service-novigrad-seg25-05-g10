package com.example.application;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DBNAME = "login.db";
    public DBHelper( Context context) {
        super(context,"login.db", null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("Create Table users(username TEXT primary key, password TEXT, email TEXT, role INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
    }

    /**
     * Insérer un profil dans la base de donnée
     * @param profil
     * @return
     */
    public boolean insertData(Profil profil) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", profil.getUser());
        contentValues.put("password", profil.getPassword());
        contentValues.put("email", profil.getEmail());
        contentValues.put("role", profil.getRole()); // 2 = admin, 1 = employé, 0 = cleint
        long result = MyDB.insert("users", null, contentValues);
        if (result == -1) {
            return false;
        } else
            return true;
    }

    /**
     * Vérifier si le nom d'utilisateur n'extiste pas déjà. Il doit être unique
     * @param profil
     * @return
     */
    public boolean checkusername(Profil profil){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String [] {profil.getUser()});
        if (cursor.getCount()>0){
            return true;
        }else
            return false;
    }

    /**
     * Védifier les infos de connexions sont correctes
     * @param username
     * @param password
     * @return
     */
    public boolean checkusernamepassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password=?", new String [] {username,password});
        if (cursor.getCount()>0){
            return true;
        }else
            return false;
    }

    /**
     * Obtenir un profil depuis la base de donnée en se basant sur username
     * @param user
     * @return
     */
    public Profil getProfil(String user) {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        String[] columns = {"username", "password", "email", "role"};
        String selection = "username = ?";
        String[] selectionArgs = {user};
        Cursor cursor = MyDB.query("users", columns, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            @SuppressLint("Range") String username = cursor.getString(cursor.getColumnIndex("username"));
            @SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex("password"));
            @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex("email"));
            @SuppressLint("Range") Integer role = cursor.getInt(cursor.getColumnIndex("role"));

            // Créez un objet Profil avec les données récupérées
            Profil profile = new Profil(username, email, password, role); // sauvegarder ces infos dans profil

            // Fermez le curseur et la base de données
            cursor.close();
            MyDB.close();
            return profile;
        } else {
            // Aucun profil correspondant à l'ID n'a été trouvé
            cursor.close();
            MyDB.close();
            return null;
        }
    }
}
