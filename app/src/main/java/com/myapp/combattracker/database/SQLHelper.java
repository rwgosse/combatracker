package com.myapp.combattracker.database;


/**
 *   Database Helper Call used to create , upgrade database , create table and perform C.R.U.D.  operations
 *
 */

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.myapp.combattracker.models.*;

public class SQLHelper extends SQLiteOpenHelper {

    // Database Name
    public static String DATABASE_NAME = "combat_database";


    // Current version of database
    private static final int DATABASE_VERSION = 1;

/*    // Students Table
    private static final String TABLE_STUDENTS = "students";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PHONENUMBER = "phone_number";*/

    // Characters Table
    private static final String TABLE_CHARACTERS ="characters";
    private static final String CHARACTER_ID = "id";
    private static final String CHARACTER_NAME ="name";
    private static final String CHARACTER_TEXT = "text";
    private static final String CHARACTER_LEVEL = "level";
    private static final String CHARACTER_CLASS = "class";
    private static final String CHARACTER_XP = "xp";
    private static final String CREATE_TABLE_CHARACTERS = "CREATE TABLE IF NOT EXISTS "
            + TABLE_CHARACTERS + "(" + CHARACTER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + CHARACTER_NAME + " TEXT,"
            + CHARACTER_LEVEL + " INTEGER,"
            + CHARACTER_CLASS + " TEXT,"
            + CHARACTER_XP + " INTEGER,"
            + CHARACTER_TEXT + " TEXT );";




    // Character Class Table
    private static final String TABLE_CHARACTER_CLASSES = "characterclasses";
    private static final String CLASS_ID = "id";
    private static final String CHARACTER_CLASS_NAME = "name";

    private static final String CREATE_TABLE_CHARACTER_CLASSES = "CREATE TABLE IF NOT EXISTS "
            + TABLE_CHARACTER_CLASSES + "(" + CLASS_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + CHARACTER_CLASS_NAME + " TEXT);";

    public static String TAG = "tag";

    // Students Table Create Query
    /**
     * CREATE TABLE students ( id INTEGER PRIMARY KEY AUTOINCREMENT, name
     * TEXT,phone_number TEXT);
     */

    /*private static final String CREATE_TABLE_STUDENTS = "CREATE TABLE "
            + TABLE_STUDENTS + "(" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT,"
            + KEY_PHONENUMBER + " TEXT );";*/

    public SQLHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This method is called by system if the database is accessed but not yet
     * created.
     */

    @Override
    public void onCreate(SQLiteDatabase db) {


        //db.execSQL(CREATE_TABLE_STUDENTS);
        db.execSQL(CREATE_TABLE_CHARACTER_CLASSES);
        db.execSQL(CREATE_TABLE_CHARACTERS);

        addCharacterClass(db, new CharacterClassModel("Fighter"));
        addCharacterClass(db, new CharacterClassModel("Rogue"));
        addCharacterClass(db, new CharacterClassModel("Cleric"));
        addCharacterClass(db, new CharacterClassModel("Wizard"));
        addCharacterClass(db, new CharacterClassModel("Barbarian"));
        addCharacterClass(db, new CharacterClassModel("Monk"));

        addCharacter(db, new CharacterModel("Dave", "Big Guy", getCharacterClass(db, "Fighter")));
        addCharacter(db, new CharacterModel("Ed", "Magic Guy", getCharacterClass(db, "Wizard")));

    }

    /**
     * This method is called when any modifications in database are done like
     * version is updated or database schema is changed
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       // db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_STUDENTS); // drop table if exists
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_CHARACTER_CLASSES); // drop table if exists
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_CHARACTERS); // drop table if exists

        onCreate(db);
    }

/*    *//**
     *
     * This method is used to add students detail in students Table
     *
     * @param student
     * @return
     *//*
    public long addStudentDetail(StudentsModel student) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Creating content values
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, student.name);
        values.put(KEY_PHONENUMBER, student.phone_number);

        // insert row in students table

        long insert = db.insert(TABLE_STUDENTS, null, values);

        return insert;
    }*/

 /*   *//**
     * This method is used to update particular student entry
     *
     * @param student
     * @return
     *//*
    public int updateEntry(StudentsModel student) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Creating content values
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, student.name);
        values.put(KEY_PHONENUMBER, student.phone_number);

        // update row in students table base on students.is value
        return db.update(TABLE_STUDENTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(student.id) });
    }

    *//**
     * Used to delete particular student entry
     *
     * @param id
     *//*
    public void deleteEntry(long id) {

        // delete row in students table based on id
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STUDENTS, KEY_ID + " = ?",
                new String[] { String.valueOf(id) });
    }

    *//**
     * Used to get particular student details
     *
     * @param id
     * @return
     *//*
    public StudentsModel getStudent(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        // SELECT * FROM students WHERE id = ?;
        String selectQuery = "SELECT  * FROM " + TABLE_STUDENTS + " WHERE "
                + KEY_ID + " = " + id;
        Log.d(TAG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        StudentsModel students = new StudentsModel();
        students.id = c.getInt(c.getColumnIndex(KEY_ID));
        students.phone_number = c.getString(c.getColumnIndex(KEY_PHONENUMBER));
        students.name = c.getString(c.getColumnIndex(KEY_NAME));

        return students;
    }

    *//**
     * Used to get detail of entire database and save in array list of data type
     * StudentsModel
     *
     * @return
     *//*
    public List<StudentsModel> getAllStudentsList() {
        List<StudentsModel> studentsArrayList = new ArrayList<StudentsModel>();

        String selectQuery = "SELECT  * FROM " + TABLE_STUDENTS;
        Log.d(TAG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {

                StudentsModel students = new StudentsModel();
                students.id = c.getInt(c.getColumnIndex(KEY_ID));
                students.phone_number = c.getString(c
                        .getColumnIndex(KEY_PHONENUMBER));
                students.name = c.getString(c.getColumnIndex(KEY_NAME));

                // adding to Students list
                studentsArrayList.add(students);
            } while (c.moveToNext());
        }

        return studentsArrayList;
    }
*/
    /**
     *
     * This method is used to add classes to the character classes Table
     *
     * @param characterClass
     * @return
     */

    public long addCharacterClass(SQLiteDatabase db, CharacterClassModel characterClass) {
        //SQLiteDatabase db = this.getWritableDatabase();

        // Creating content values
        ContentValues values = new ContentValues();
        values.put(CHARACTER_CLASS_NAME, characterClass.name);

        // insert row in students table

        long insert = db.insert(TABLE_CHARACTER_CLASSES, null, values);

        return insert;
    }

    /**
    * Used to get particular student details
    *
            * @param name
    * @return
            */
    public CharacterClassModel getCharacterClass(SQLiteDatabase db, String name) {
       // SQLiteDatabase db = this.getReadableDatabase();

        // SELECT * FROM students WHERE id = ?;
        String selectQuery = "SELECT * FROM " + TABLE_CHARACTER_CLASSES + " WHERE "
                + CHARACTER_CLASS_NAME + " = '" + name +"'";
        Log.d(TAG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        CharacterClassModel characterClassModel = new CharacterClassModel();
        characterClassModel.id = c.getInt(c.getColumnIndex(CLASS_ID));
        characterClassModel.name = c.getString(c.getColumnIndex(CHARACTER_CLASS_NAME));


        return characterClassModel;
    }

    /**
     *
     * This method is used to add classes to the character classes Table
     *
     * @param character
     * @return
     */

    public long addCharacter(SQLiteDatabase db, CharacterModel character) {


        // Creating content values
        ContentValues values = new ContentValues();
        values.put(CHARACTER_NAME, character.name);
        values.put(CHARACTER_TEXT, character.text);
        values.put(CHARACTER_LEVEL, character.level);
        values.put(CHARACTER_CLASS, character.getClassName());
        values.put(CHARACTER_XP, character.xp);

        // insert row in characters table

        long insert = db.insert(TABLE_CHARACTERS, null, values);

        return insert;
    }

    /**
     * Used to get detail of entire database and save in array list of data type
     * CharacterModel
     *
     * @return
     */
    public List<CharacterModel> getAllCharactersList() {
        List<CharacterModel> charactersArrayList = new ArrayList<CharacterModel>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_CHARACTERS;
        Log.d(TAG, selectQuery);


        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {

                CharacterModel character = new CharacterModel();
                character.id = c.getInt(c.getColumnIndex(CHARACTER_ID));
                character.name = c.getString(c.getColumnIndex(CHARACTER_NAME));
                character.characterClassModel = getCharacterClass(db, c.getString(c.getColumnIndex(CHARACTER_CLASS)));

                // adding to Students list
                charactersArrayList.add(character);
            } while (c.moveToNext());
        }

        return charactersArrayList;
    }
}