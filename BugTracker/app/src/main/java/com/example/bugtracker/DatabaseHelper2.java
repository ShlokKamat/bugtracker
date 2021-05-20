package com.example.bugtracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper2 extends SQLiteOpenHelper {


    public static final String BUG_TABLE = "BUG_TABLE";
    public static final String COLUMN_BUGID = "BUGID";
    public static final String COLUMN_BUGDESC = "BUGDESC";
    public static final String COLUMN_STEPS_2_REP = "STEPS2REP";
    public static final String COLUMN_PRIORITY = "PRIORITY";
    public static final String COLUMN_REPORTER = "REPORTER";
    public static final String COLUMN_CREATED = "CREATED";
    public static final String COLUMN_PROCESSOR = "PROCESSOR";
    public static final String COLUMN_LASTUPDATED = "LASTUPDATED";
    public static final String COLUMN_STATUS = "STATUS";
    public static final String COLUMN_SOLUTION = "SOLUTION";

    public DatabaseHelper2(@Nullable Context context) {
        super(context, "bug.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createtableStatement = "CREATE TABLE " + BUG_TABLE + " (" + COLUMN_BUGID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_BUGDESC + " TEXT, " + COLUMN_STEPS_2_REP + " TEXT, " + COLUMN_PRIORITY + " INT, " + COLUMN_REPORTER + " TEXT, " + COLUMN_CREATED + " TEXT, " + COLUMN_PROCESSOR + " TEXT, " + COLUMN_LASTUPDATED + " TEXT, " + COLUMN_STATUS + " INT, " + COLUMN_SOLUTION + " TEXT)";

        db.execSQL(createtableStatement);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    public boolean addBug(BugModel bugModel){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_BUGDESC, bugModel.getBugDesc());
        cv.put(COLUMN_STEPS_2_REP, bugModel.getSteps2rep());
        cv.put(COLUMN_PRIORITY, bugModel.getPriority());
        cv.put(COLUMN_REPORTER, bugModel.getReporter());
        cv.put(COLUMN_CREATED, bugModel.getCreated());
        cv.put(COLUMN_PROCESSOR, bugModel.getProcessor());
        cv.put(COLUMN_LASTUPDATED, bugModel.getLastUpdated());
        cv.put(COLUMN_STATUS, bugModel.getStatus());
        cv.put(COLUMN_SOLUTION, bugModel.getSoln());

        long insert = db.insert(BUG_TABLE, null, cv);
        if(insert == -1)
        {
            return false;
        }
        else{
            return true;
        }
    }

    public List<BugModel> getAllBugs()
    {
        List<BugModel> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + BUG_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);
        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String bd = cursor.getString(1);
                String s2r = cursor.getString(2);
                int prio = cursor.getInt(3);
                String repo = cursor.getString(4);
                String crea = cursor.getString(5);
                String pro = cursor.getString(6);
                String lastup = cursor.getString(7);
                int stat = cursor.getInt(8);
                String soln = cursor.getString(9);

                BugModel newBug = new BugModel(id,bd,s2r,prio,repo,crea,pro,lastup,stat,soln);
                returnList.add(newBug);
            }while(cursor.moveToNext());
        }
        else{
            //NO ITEMS IN TABLE
        }
        cursor.close();
        db.close();
        return returnList;
    }

    public List<BugModel> getBugs()
    {
        List<BugModel> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + BUG_TABLE + " WHERE " + COLUMN_STATUS + " = " + 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);
        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String bd = cursor.getString(1);
                String s2r = cursor.getString(2);
                int prio = cursor.getInt(3);
                String repo = cursor.getString(4);
                String crea = cursor.getString(5);
                String pro = cursor.getString(6);
                String lastup = cursor.getString(7);
                int stat = cursor.getInt(8);
                String soln = cursor.getString(9);

                BugModel newBug = new BugModel(id,bd,s2r,prio,repo,crea,pro,lastup,stat,soln);
                returnList.add(newBug);
            }while(cursor.moveToNext());
        }
        else{
            //NO ITEMS IN TABLE
        }
        cursor.close();
        db.close();
        return returnList;
    }

    public List<BugModel> getResBugs()
    {
        List<BugModel> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + BUG_TABLE + " WHERE " + COLUMN_STATUS + " = " + 1;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);
        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String bd = cursor.getString(1);
                String s2r = cursor.getString(2);
                int prio = cursor.getInt(3);
                String repo = cursor.getString(4);
                String crea = cursor.getString(5);
                String pro = cursor.getString(6);
                String lastup = cursor.getString(7);
                int stat = cursor.getInt(8);
                String soln = cursor.getString(9);

                BugModel newBug = new BugModel(id,bd,s2r,prio,repo,crea,pro,lastup,stat,soln);
                returnList.add(newBug);
            }while(cursor.moveToNext());
        }
        else{
            //NO ITEMS IN TABLE
        }
        cursor.close();
        db.close();
        return returnList;
    }

    public List<BugModel> getMyBugs(String user)
    {
        List<BugModel> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + BUG_TABLE + " WHERE " + COLUMN_REPORTER + " = '" + user + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);
        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String bd = cursor.getString(1);
                String s2r = cursor.getString(2);
                int prio = cursor.getInt(3);
                String repo = cursor.getString(4);
                String crea = cursor.getString(5);
                String pro = cursor.getString(6);
                String lastup = cursor.getString(7);
                int stat = cursor.getInt(8);
                String soln = cursor.getString(9);

                BugModel newBug = new BugModel(id,bd,s2r,prio,repo,crea,pro,lastup,stat,soln);
                returnList.add(newBug);
            }while(cursor.moveToNext());
        }
        else{
            //NO ITEMS IN TABLE
        }
        cursor.close();
        db.close();
        return returnList;
    }

    public boolean deleteBug(BugModel bugModel)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + BUG_TABLE + " WHERE " + COLUMN_BUGID + " = " + bugModel.getBugID();

        Cursor cursor = db.rawQuery(queryString,null);
        if(cursor.moveToFirst())
        {
            return true;
        }
        else{
            return false;
        }
    }

    public boolean updateBug(BugModel bugModel)
    {
        SQLiteDatabase db = this.getWritableDatabase();
//        String queryString = "UPDATE " + BUG_TABLE + " SET " + COLUMN_SOLUTION + " = '" + bugModel.getSoln() + "' WHERE " + COLUMN_BUGID + " = " + bugModel.getBugID();
        String queryString = "UPDATE BUG_TABLE SET PROCESSOR = '"+bugModel.getProcessor()+"', LASTUPDATED = '"+bugModel.getLastUpdated()+"', STATUS = "+bugModel.getStatus()+", SOLUTION = '"+bugModel.getSoln()+"' WHERE " + COLUMN_BUGID + " = " + bugModel.getBugID();

        Cursor cursor = db.rawQuery(queryString,null);
        if(cursor.moveToFirst())
        {
            return true;
        }
        else{
            return false;
        }
    }

}
