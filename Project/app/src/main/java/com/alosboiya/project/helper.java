package com.alosboiya.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by HP on 7/26/2017.
 */

public class helper extends SQLiteOpenHelper {
    public static String db_name="movie.db";
    public static int version=5;
    public helper(Context context) {
        super(context, db_name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String cre_query="create table "+database.columns.table_name +" ( " +
                (database.columns.name)+" Text , " +
                (database.columns.photo)+" Text ); " ;
               /* (contractor.myfavorite.Favorite)+" Text*/
        db.execSQL(cre_query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop Table IF EXISTS " + database.columns.table_name +" ;");
        onCreate(db);
    }


    public void insert(String n, String p)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(database.columns.name,n);
        cv.put(database.columns.photo,p);
         long x = db.insert(database.columns.table_name,null,cv);
        Log.v("insert", x+"");
       // db.close();
    }
   /*   public List<Movie> select()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Movie> mylist = new ArrayList<>();
        String[] col= new String[]{database.columns.name,database.columns.photo};
        Cursor cursor = getReadableDatabase().query(database.columns.table_name,col,null,null,null,null,null,null);
        if(cursor != null)
           cursor.moveToFirst();
        for (int i=0;i<cursor.getCount();i++){
            Movie item = new Movie();
            item.setTitle(cursor.getString(0));
            item.setPoster_pass(cursor.getString(1));
            mylist.add(item);
            cursor.moveToNext();
        }
        return mylist;
    }*/
    public Cursor selectCursor()
    {
        SQLiteDatabase db = getReadableDatabase();
        String[] cols = new String[] {database.columns.name,database.columns.photo};
        Cursor cursor = db.query(database.columns.table_name, cols, null, null, null, null, null);
        Log.v("cursor", cursor.getCount()+"");
        return cursor;
    }
}
