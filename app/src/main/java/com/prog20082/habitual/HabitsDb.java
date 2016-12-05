package com.prog20082.habitual;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by mmous on 11/22/2016.
 */
public class HabitsDb {

    // instance members for accessing the database
    private SQLiteDatabase database;
    private SQLiteOpenHelper openHelper;

    // constants for the database
    public static final String DB_NAME = "habits.db";
    public static final int DB_VERSION = 1;

    // constants for the table
    public static final String HABITS_TABLE = "Habits";

    public static final String ID = "_id";
    public static final int ID_COLUMN = 0;

    public static final String NAME = "name";
    public static final int NAME_COLUMN = 1;

    public static final String GOOD = "good";
    public static final int GOOD_COLUMN = 2;

    public static final String GOAL = "goal";
    public static final int GOAL_COLUMN = 3;

    public static final String GOAL_UNITS = "goalUnits";
    public static final int GOAL_UNITS_COLUMN = 4;

    public static final String COMPLETE = "complete";
    public static final int COMPLETE_COLUMN = 5;

    public static final String STREAK = "streak";
    public static final int STREAK_COLUMN = 6;


    // DDL for creating the table. Careful with adding spaces!!
    public static final String CREATE_HABITS_TABLE =
            "CREATE TABLE " + HABITS_TABLE + " (" +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NAME + " TEXT, " +
                    GOOD + " INTEGER, " +
                    GOAL + " INTEGER, " +
                    GOAL_UNITS + " TEXT, " +
                    COMPLETE + " INTEGER)";

    public HabitsDb(Context context){
        openHelper = new DBHelper(context, DB_NAME, null, DB_VERSION);
    }

    //save habit
    public Habit saveHabit(Habit habit){
        database = openHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NAME, habit.getName());
        values.put(GOOD, habit.isGood() ? 1 : 0);
        values.put(GOAL, habit.getGoal());
        values.put(GOAL_UNITS, habit.getGoalUnits());
        values.put(COMPLETE, habit.isComplete() ? 1 : 0);

        long dbId = database.insert(HABITS_TABLE, null, values);

        if(dbId > 0){
            habit.setId(dbId);
        }

        database.close();
        return habit;
    }

    public int deleteAllHabits(){
        int numberDeleted = 0;

        database = openHelper.getWritableDatabase();
        numberDeleted = database.delete(HABITS_TABLE, null, null);

        database.close();
        return numberDeleted;

    }

    public int deleteHabit(Habit habit){
        int numberDeleted = 0;

        database = openHelper.getWritableDatabase();
        long id = habit.getId();

        String where = ID + "=?";
        String[] whereArgs = new String[]{Long.toString(id)};

        numberDeleted = database.delete(HABITS_TABLE, where, whereArgs);

        database.close();
        return numberDeleted;
    }

    //update database
    public Habit updateHabitStreak(Habit habit){
        database = openHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(STREAK, habit.getStreak());

        String where = ID + "=?";
        String[] args = new String[]{String.valueOf(habit.getId())};

        database.update(HABITS_TABLE, values, where, args);


        database.close();
        return habit;
    }

    //load all habits
    public ArrayList<Habit> loadAllHabits(){
        ArrayList<Habit> habits = new ArrayList<>();

        database = openHelper.getReadableDatabase();

        Cursor result = database.query(HABITS_TABLE, null, null, null, null, null, NAME);

        while(result.moveToNext()){

            long dbId = result.getLong(result.getColumnIndex(ID));
            String name = result.getString(result.getColumnIndex(NAME));
            Boolean good = result.getInt(result.getColumnIndex(GOOD)) == 1;
            int goal = result.getInt(result.getColumnIndex(GOAL));
            String goalUnits = result.getString(result.getColumnIndex(GOAL_UNITS));
            Boolean complete = result.getInt(result.getColumnIndex(COMPLETE)) == 1;

            habits.add(new Habit(name, good, goal, goalUnits, complete));
        }

        result.close();
        database.close();

        return habits;
    }

    private static class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context, String dbName, SQLiteDatabase.CursorFactory o, int dbVersion){
            super(context, dbName, o, dbVersion);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_HABITS_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS" + HABITS_TABLE);
            onCreate(db);
        }
    }
}
