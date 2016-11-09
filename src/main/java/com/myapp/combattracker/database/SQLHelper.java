package com.myapp.combattracker.database;


/**
 * Database Helper Call used to create , upgrade database , create table and perform C.R.U.D.  operations
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

    // Current version of database
    private static final int DATABASE_VERSION = 1;
    // Database Name
    public static String DATABASE_NAME = "combat_database";
    public static String TAG = "tag";

    // Characters Table
    private static final String TABLE_CHARACTERS = "characters";
    private static final String CHARACTER_ID = "id";
    private static final String CHARACTER_NAME = "name";
    private static final String CHARACTER_TEXT = "text";
    private static final String CHARACTER_LEVEL = "level";
    private static final String CHARACTER_CLASS = "class";
    private static final String CHARACTER_XP = "xp";
    private static final String CHARACTER_AC = "ac";
    private static final String CHARACTER_STR = "str";
    private static final String CHARACTER_CON = "con";
    private static final String CHARACTER_DEX = "dex";
    private static final String CHARACTER_WIS = "wis";
    private static final String CHARACTER_INT = "int";
    private static final String CHARACTER_CHR = "chr";
    private static final String CHARACTER_ALIGN = "align";
    private static final String CREATE_TABLE_CHARACTERS = "CREATE TABLE IF NOT EXISTS "
            + TABLE_CHARACTERS + "("
            + CHARACTER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + CHARACTER_NAME + " TEXT,"
            + CHARACTER_LEVEL + " INTEGER,"
            + CHARACTER_CLASS + " TEXT,"
            + CHARACTER_XP + " INTEGER,"
            + CHARACTER_ALIGN + " INTEGER,"
            + CHARACTER_TEXT + " TEXT,"
            + CHARACTER_AC + " INTEGER,"
            + CHARACTER_STR + " INTEGER,"
            + CHARACTER_CON + " INTEGER,"
            + CHARACTER_DEX + " INTEGER,"
            + CHARACTER_WIS + " INTEGER,"
            + CHARACTER_INT + " INTEGER,"
            + CHARACTER_CHR + " INTEGER);";

    // Character Class Table
    private static final String TABLE_CHARACTER_CLASSES = "characterclasses";
    private static final String CLASS_ID = "id";
    private static final String CHARACTER_CLASS_NAME = "name";
    private static final String CREATE_TABLE_CHARACTER_CLASSES = "CREATE TABLE IF NOT EXISTS "
            + TABLE_CHARACTER_CLASSES + "("
            + CLASS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + CHARACTER_CLASS_NAME + " TEXT);";

    // Alignment Table
    private static final String TABLE_ALIGNMENTS = "alignments";
    private static final String ALIGNMENT_ID = "id";
    private static final String ALIGNMENT_NAME = "name";
    private static final String CREATE_TABLE_ALIGNMENTS = "CREATE TABLE IF NOT EXISTS "
            + TABLE_ALIGNMENTS + "("
            + ALIGNMENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ALIGNMENT_NAME + " TEXT);";

    // Items Table

    private static final String TABLE_ITEMS = "items";
    private static final String ITEM_ID = "id";
    private static final String ITEM_TYPE = "itemtype";
    private static final String OWNER_ID = "ownerid";
    private static final String ITEM_NAME = "name";
    private static final String ITEM_TEXT = "text";
    private static final String WEAPON_ATTACK = "attack";
    private static final String WEAPON_DAMAGE = "damage";
    private static final String WEAPON_TYPE = "type";
    private static final String CREATE_TABLE_ITEMS = "CREATE TABLE IF NOT EXISTS "
            + TABLE_ITEMS + "("
            + ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ITEM_TYPE + " TEXT,"
            + OWNER_ID + " INTEGER,"
            + ITEM_NAME + " TEXT,"
            + ITEM_TEXT + " TEXT,"
            + WEAPON_DAMAGE + " TEXT,"
            + WEAPON_ATTACK + " INTEGER,"
            + WEAPON_TYPE + " TEXT);";



    // Abilities Table

    // Encounters Table

    // Monsters Table

    // Spells Table

    // Weapons Table







    public SQLHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This method is called by system if the database is accessed but not yet
     * created.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {


        // create all tables
        db.execSQL(CREATE_TABLE_CHARACTER_CLASSES);
        db.execSQL(CREATE_TABLE_CHARACTERS);
        db.execSQL(CREATE_TABLE_ALIGNMENTS);
        db.execSQL(CREATE_TABLE_ITEMS);
        // end create all tables


        //sample data
        addCharacterClass(db, new CharacterClassModel("Fighter"));
        addCharacterClass(db, new CharacterClassModel("Rogue"));
        addCharacterClass(db, new CharacterClassModel("Cleric"));
        addCharacterClass(db, new CharacterClassModel("Wizard"));
        addCharacterClass(db, new CharacterClassModel("Barbarian"));
        addCharacterClass(db, new CharacterClassModel("Monk"));

        addAlignment(db, new Alignment("Lawful Good"));
        addAlignment(db, new Alignment("Neutral Good"));
        addAlignment(db, new Alignment("Chaotic Good"));
        addAlignment(db, new Alignment("Lawful Evil"));
        addAlignment(db, new Alignment("Neutral Evil"));
        addAlignment(db, new Alignment("Chaotic Evil"));

        addCharacter(db, new CharacterModel("Dave", "Big Guy", getCharacterClass(db, "Fighter"), getAlignment(db, 1)));
        addCharacter(db, new CharacterModel("Ed", "Magic Guy", getCharacterClass(db, "Wizard"), getAlignment(db, 2)));

        addItem(db, new ItemModel("Torch", "Lights Up"));
        addItem(db, new ItemModel(0, "Bag of Holding", "Contains Items"));
        addItem(db, new ItemModel(0, "Thieve's Tools", "Lockpicks, etc"));
        addItem(db, new ItemModel(1, "Bag of Holding", "Contains Items"));
        addItem(db, new ItemModel(2, "Shield", "Extra Protection"));
        addItem(db, new ItemModel(3, "Bag of Holding", "Contains Items"));
        addItem(db, new WeaponModel(0, "Sword", "Sharp & Pointy", 2, "1d6", "Slashing"));
        addItem(db, new WeaponModel(1, "Axe", "Sharp & Pointy", 3, "1d6", "Slashing"));
        addItem(db, new WeaponModel(2, "Sword", "Sharp & Pointy", 2, "1d6", "Slashing"));


        // end sample data
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
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_ALIGNMENTS); // drop table if exists

        onCreate(db);
    }


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
     *
     * This method is used to add classes to the character classes Table
     *
     * @param alignment
     * @return
     */
    public long addAlignment(SQLiteDatabase db,  Alignment alignment) {
        //SQLiteDatabase db = this.getWritableDatabase();

        // Creating content values
        ContentValues values = new ContentValues();
        values.put(ALIGNMENT_NAME, alignment.name);

        // insert row in table

        long insert = db.insert(TABLE_ALIGNMENTS, null, values);

        return insert;
    }

    public long addItem(SQLiteDatabase db, ItemModel item) {
        //SQLiteDatabase db = this.getWritableDatabase();

        // Creating content values
        ContentValues values = new ContentValues();
        values.put(ITEM_NAME, item.name);
        values.put(ITEM_TEXT, item.name);
        values.put(OWNER_ID, item.ownerid);

        if (item instanceof WeaponModel) {
            values.put(ITEM_TYPE, "weapon");
            values.put(WEAPON_ATTACK, ((WeaponModel) item).atk);
            values.put(WEAPON_DAMAGE, ((WeaponModel) item).dmg);
            values.put(WEAPON_TYPE, ((WeaponModel) item).dmgType);
        }
        else values.put(ITEM_TYPE, "item");
        // insert row in table

        long insert = db.insert(TABLE_ITEMS, null, values);

        return insert;
    }

    //public ItemModel getItem(int itemId) {
        //not yet implemented
  //  }

    /**
     * Used to get a particular character class
     *
     * * @param name
     * @return
     */
    public CharacterClassModel getCharacterClass(SQLiteDatabase db, String name) {
        // SQLiteDatabase db = this.getReadableDatabase();

        // SELECT * FROM students WHERE id = ?;
        String selectQuery = "SELECT * FROM " + TABLE_CHARACTER_CLASSES + " WHERE "
                + CHARACTER_CLASS_NAME + " = '" + name + "'";
        Log.d(TAG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        CharacterClassModel characterClassModel = new CharacterClassModel();
        characterClassModel.id = c.getInt(c != null ? c.getColumnIndex(CLASS_ID) : 0);
        characterClassModel.name = c.getString(c.getColumnIndex(CHARACTER_CLASS_NAME));

        c.close();
        return characterClassModel;
    }

    public Alignment getAlignment(SQLiteDatabase db, int id) {
        // SQLiteDatabase db = this.getReadableDatabase();

        // SELECT * FROM students WHERE id = ?;
        String selectQuery = "SELECT * FROM " + TABLE_ALIGNMENTS + " WHERE "
                + ALIGNMENT_ID + " = '" + id + "'";
        Log.d(TAG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Alignment alignment = new Alignment();
        alignment.id = c.getInt(c != null ? c.getColumnIndex(ALIGNMENT_ID) : 0);
        alignment.name = c.getString(c.getColumnIndex(ALIGNMENT_NAME));

        c.close();
        return alignment;
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
        values.put(CHARACTER_ALIGN, character.alignment.id);

        // insert row in characters table

        long insert = db.insert(TABLE_CHARACTERS, null, values);

        return insert;
    }

    /**
     * Used to get detail of all characters and save in array list of data type
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
                character.ac = c.getInt(c.getColumnIndex(CHARACTER_AC));
                character.str = c.getInt(c.getColumnIndex(CHARACTER_STR));
                character.con = c.getInt(c.getColumnIndex(CHARACTER_CON));
                character.dex = c.getInt(c.getColumnIndex(CHARACTER_DEX));
                character.wis = c.getInt(c.getColumnIndex(CHARACTER_WIS));
                character.intel = c.getInt(c.getColumnIndex(CHARACTER_INT));
                character.chr = c.getInt(c.getColumnIndex(CHARACTER_CHR));
                character.level = c.getInt(c.getColumnIndex(CHARACTER_LEVEL));
                character.xp = c.getInt(c.getColumnIndex(CHARACTER_XP));

                // adding to Students list
                charactersArrayList.add(character);
            } while (c.moveToNext());
        }
        c.close();
        return charactersArrayList;
    }

    public CharacterModel getCharacter(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_CHARACTERS + " WHERE " + CHARACTER_ID + " = '" + id + "'";
        Log.d(TAG, selectQuery);
        Cursor c = db.rawQuery(selectQuery, null);

        String itemQuery = "SELECT  * FROM " + TABLE_ITEMS + " WHERE " + OWNER_ID + " = '" + id + "'";
        Log.d(TAG, itemQuery);
        Cursor w = db.rawQuery(itemQuery, null);

        if (c != null)
            c.moveToFirst();
        CharacterModel character = new CharacterModel();
        character.id = c.getInt(c.getColumnIndex(CHARACTER_ID));
        character.name = c.getString(c.getColumnIndex(CHARACTER_NAME));
        character.characterClassModel = getCharacterClass(db, c.getString(c.getColumnIndex(CHARACTER_CLASS)));
        character.alignment = getAlignment(db, (c.getColumnIndex(CHARACTER_ALIGN)));
        character.ac = c.getInt(c.getColumnIndex(CHARACTER_AC));
        character.str = c.getInt(c.getColumnIndex(CHARACTER_STR));
        character.con = c.getInt(c.getColumnIndex(CHARACTER_CON));
        character.dex = c.getInt(c.getColumnIndex(CHARACTER_DEX));
        character.wis = c.getInt(c.getColumnIndex(CHARACTER_WIS));
        character.intel = c.getInt(c.getColumnIndex(CHARACTER_INT));
        character.chr = c.getInt(c.getColumnIndex(CHARACTER_CHR));
        character.level = c.getInt(c.getColumnIndex(CHARACTER_LEVEL));
        character.xp = c.getInt(c.getColumnIndex(CHARACTER_XP));

        // looping through all rows and adding to list
        if (w.moveToFirst()) {
            do {
                String type = w.getString(w.getColumnIndex(ITEM_TYPE));
                if (type.equals("weapon")) {
                    WeaponModel weapon = new WeaponModel();
                    weapon.id = w.getInt(w.getColumnIndex(ITEM_ID));
                    weapon.ownerid = w.getInt(w.getColumnIndex(OWNER_ID));
                    weapon.name = w.getString(w.getColumnIndex(ITEM_NAME));
                    weapon.text = w.getString(w.getColumnIndex(ITEM_TEXT));
                    weapon.atk = w.getInt(w.getColumnIndex(WEAPON_ATTACK));
                    weapon.dmg = w.getString(w.getColumnIndex(WEAPON_DAMAGE));
                    weapon.dmgType = w.getString(w.getColumnIndex(WEAPON_TYPE));
                    character.addItem(weapon);
                }
                else {
                    ItemModel item = new ItemModel();
                    item.id = w.getInt(w.getColumnIndex(ITEM_ID));
                    item.ownerid = w.getInt(w.getColumnIndex(OWNER_ID));
                    item.name = w.getString(w.getColumnIndex(ITEM_NAME));
                    item.text = w.getString(w.getColumnIndex(ITEM_TEXT));
                    character.addItem(item);
                }
            } while (w.moveToNext());
        }
        w.close();
        c.close();
        return character;

    }

    public List<CharacterClassModel> getAllClassList() {
        List<CharacterClassModel> classArrayList = new ArrayList<CharacterClassModel>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_CHARACTER_CLASSES;
        Log.d(TAG, selectQuery);


        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {

                CharacterClassModel character_class = new CharacterClassModel();
                character_class.id = c.getInt(c.getColumnIndex(CLASS_ID));
                character_class.name = c.getString(c.getColumnIndex(CHARACTER_CLASS_NAME));

                // adding to list
                classArrayList.add(character_class);
            } while (c.moveToNext());
        }
        c.close();
        return classArrayList;
    }

    public List<Alignment> getAllAlignList() {
        List<Alignment> alignArrayList = new ArrayList<Alignment>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_ALIGNMENTS;
        Log.d(TAG, selectQuery);


        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {

                Alignment alignment = new Alignment();
                alignment.id = c.getInt(c.getColumnIndex(ALIGNMENT_ID));
                alignment.name = c.getString(c.getColumnIndex(ALIGNMENT_NAME));

                // adding to list
                alignArrayList.add(alignment);
            } while (c.moveToNext());
        }
        c.close();
        return alignArrayList;
    }
}
