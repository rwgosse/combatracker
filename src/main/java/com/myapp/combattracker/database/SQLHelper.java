package com.myapp.combattracker.database;


/**
 * Database Helper Call used to create , upgrade database , create table and perform C.R.U.D.  operations
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.myapp.combattracker.models.Alignment;
import com.myapp.combattracker.models.CharacterClassModel;
import com.myapp.combattracker.models.CharacterModel;
import com.myapp.combattracker.models.ItemModel;
import com.myapp.combattracker.models.WeaponModel;

import java.util.ArrayList;
import java.util.List;

public class SQLHelper extends SQLiteOpenHelper {

    // Current version of database
    private static final int DATABASE_VERSION = 28;
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
    private static final String TABLE_ITEMS = "items";
    private static final String ITEM_ID = "id";
    private static final String ITEM_TYPE = "itemtype";

    // Items Table
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
    // Database Name
    private final static String DATABASE_NAME = "combat_database";
    private final static String TAG = "tag";
    private SQLiteDatabase db;


    // Abilities Table

    // Encounters Table

    // Monsters Table

    // Spells Table



    public SQLHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This method is called by system if the database is accessed but not yet
     * created.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;


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

        addAlignment(new Alignment(0, "Lawful Good"));
        addAlignment(new Alignment(1, "Neutral Good"));
        addAlignment(new Alignment(2, "Chaotic Good"));
        addAlignment(new Alignment(3, "Lawful Evil"));
        addAlignment(new Alignment(4, "Neutral Evil"));
        addAlignment(new Alignment(5, "Chaotic Evil"));

        addCharacter(new CharacterModel("Bob", "Little Guy", getCharacterClass("Rogue"), getAlignment(2)));
        addCharacter(new CharacterModel("Dave", "Big Guy", getCharacterClass("Fighter"), getAlignment(2)));
        addCharacter(new CharacterModel("Ed", "Magic Guy", getCharacterClass("Wizard"), getAlignment(2)));

        addItem(new ItemModel("Torch", "Lights Up"));
        addItem(new ItemModel(0, "Bag of Holding", "Contains Items"));
        addItem(new ItemModel(0, "Thieve's Tools", "Lockpicks, etc"));
        addItem(new ItemModel(1, "Bag of Holding", "Contains Items"));
        addItem(new ItemModel(2, "Shield", "Extra Protection"));
        addItem(new ItemModel(3, "Bag of Holding", "Contains Items"));
        addItem(new WeaponModel(0, "Sword", "Sharp & Pointy", 2, "1d6", "Slashing"));
        addItem(new WeaponModel(1, "Axe", "Sharp & Pointy", 3, "1d6", "Slashing"));
        addItem(new WeaponModel(2, "Sword", "Sharp & Pointy", 2, "1d6", "Slashing"));


        // end sample data
    }

    /**
     * This method is called when any modifications in database are done like
     * version is updated or database schema is changed
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_STUDENTS); // drop table if exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHARACTER_CLASSES); // drop table if exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHARACTERS); // drop table if exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ALIGNMENTS); // drop table if exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS); // drop table if exists

        onCreate(db);
    }



    public long addCharacterClass(SQLiteDatabase db, CharacterClassModel characterClass) {
        checkDB();

        // Creating content values
        ContentValues values = new ContentValues();
        values.put(CHARACTER_CLASS_NAME, characterClass.name);

        // insert row in students table

        return db.insert(TABLE_CHARACTER_CLASSES, null, values);


    }

    /**
     * This method is used to add classes to the character classes Table
     *
     * @param alignment
     * @return long
     */
    public long addAlignment(Alignment alignment) {
        checkDB();

        // Creating content values
        ContentValues values = new ContentValues();
        values.put(ALIGNMENT_ID, alignment.getId());
        values.put(ALIGNMENT_NAME, alignment.toString());

        // insert row in table

        return db.insert(TABLE_ALIGNMENTS, null, values);


    }

    public long addItem(ItemModel item) {
        checkDB();

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
        } else values.put(ITEM_TYPE, "item");
        // insert row in table

        return  db.insert(TABLE_ITEMS, null, values);


    }


    /**
     * Used to get a particular character class
     *
     * * @param name
     *
     * @return characterClassModel
     */
    public CharacterClassModel getCharacterClass(String name) {
        checkDB();

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

    public Alignment getAlignment(int id) {
        System.out.println("getAlignment param = " + id);
        checkDB();
        String selectQuery = "SELECT * FROM " + TABLE_ALIGNMENTS + " WHERE "
                + ALIGNMENT_ID + " = '" + id + "'";
        Log.d(TAG, selectQuery);
        Cursor c = db.rawQuery(selectQuery, null);
        if (c != null)
            c.moveToFirst();

        int alignId = c.getInt(c.getColumnIndex(ALIGNMENT_ID));
        String name = c.getString(c.getColumnIndex(ALIGNMENT_NAME));
        Alignment alignment = new Alignment(alignId, name);

        c.close();
        return alignment;
    }

    public WeaponModel getItem(int id) {
        System.out.println("getWeapon param = " + id);
        checkDB();
        String selectQuery = "SELECT * FROM " + TABLE_ITEMS + " WHERE "
                + ITEM_ID + " = '" + id + "'";
        Log.d(TAG, selectQuery);
        Cursor c = db.rawQuery(selectQuery, null);
        if (c != null)
            c.moveToFirst();

        int weaponId = c.getInt(c.getColumnIndex(ITEM_ID));
        int ownerId = c.getInt(c.getColumnIndex(OWNER_ID));
        String weaponName = c.getString(c.getColumnIndex(ITEM_NAME));
        String weapontext = c.getString(c.getColumnIndex(ITEM_TEXT));
        int atk = c.getInt(c.getColumnIndex(WEAPON_ATTACK));
        String dmg = c.getString(c.getColumnIndex(WEAPON_DAMAGE));
        String dmgType = c.getString(c.getColumnIndex(WEAPON_TYPE));
        WeaponModel weapon = new WeaponModel(weaponId, ownerId, weaponName, weapontext, atk, dmg, dmgType);
        c.close();
        return weapon;
    }


    /**
     * This method is used to add classes to the character classes Table
     *
     * @param character // character to be add to the database
     * @return long
     */

    public long addCharacter(CharacterModel character) {
        checkDB();


        // Creating content values
        ContentValues values = new ContentValues();
        values.put(CHARACTER_NAME, character.name);
        values.put(CHARACTER_TEXT, character.text);
        values.put(CHARACTER_LEVEL, character.level);
        values.put(CHARACTER_CLASS, character.getClassName());
        values.put(CHARACTER_XP, character.xp);
        values.put(CHARACTER_ALIGN, character.getAlignmentId());
        values.put(CHARACTER_AC, character.ac);
        values.put(CHARACTER_STR, character.str);
        values.put(CHARACTER_CON, character.con);
        values.put(CHARACTER_DEX, character.dex);
        values.put(CHARACTER_WIS, character.wis);
        values.put(CHARACTER_INT, character.intel);
        values.put(CHARACTER_CHR, character.chr);

        // insert row in characters table

        long i =  db.insert(TABLE_CHARACTERS, null, values);
        System.out.println("add character long result = " + i);
        return i;


    }

    /**
     * Used to get detail of all characters and save in array list of data type
     * CharacterModel
     *
     * @return charactersArrayList
     */
    public List<CharacterModel> getAllCharactersList() {
        List<CharacterModel> charactersArrayList = new ArrayList<CharacterModel>();
        checkDB();
        String selectQuery = "SELECT  * FROM " + TABLE_CHARACTERS;
        Log.d(TAG, selectQuery);


        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {

                CharacterModel character = new CharacterModel();
                character.id = c.getInt(c.getColumnIndex(CHARACTER_ID));
                character.name = c.getString(c.getColumnIndex(CHARACTER_NAME));
                character.characterClassModel = getCharacterClass(c.getString(c.getColumnIndex(CHARACTER_CLASS)));
                character.ac = c.getInt(c.getColumnIndex(CHARACTER_AC));
                character.str = c.getInt(c.getColumnIndex(CHARACTER_STR));
                character.con = c.getInt(c.getColumnIndex(CHARACTER_CON));
                character.dex = c.getInt(c.getColumnIndex(CHARACTER_DEX));
                character.wis = c.getInt(c.getColumnIndex(CHARACTER_WIS));
                character.intel = c.getInt(c.getColumnIndex(CHARACTER_INT));
                character.chr = c.getInt(c.getColumnIndex(CHARACTER_CHR));
                character.level = c.getInt(c.getColumnIndex(CHARACTER_LEVEL));
                character.xp = c.getInt(c.getColumnIndex(CHARACTER_XP));
                character.setAlignment(getAlignment(c.getInt(c.getColumnIndex(CHARACTER_ALIGN))));

                // adding to Students list
                charactersArrayList.add(character);
            } while (c.moveToNext());
        }
        c.close();
        return charactersArrayList;
    }

    public CharacterModel getCharacter(int id) {
        checkDB();
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
        character.characterClassModel = getCharacterClass(c.getString(c.getColumnIndex(CHARACTER_CLASS)));
        character.setAlignment(getAlignment((c.getInt(c.getColumnIndex(CHARACTER_ALIGN)))));

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
                } else {
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
        checkDB();
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
        checkDB();
        String selectQuery = "SELECT  * FROM " + TABLE_ALIGNMENTS;
        Log.d(TAG, selectQuery);


        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {


                int alignId = c.getInt(c.getColumnIndex(ALIGNMENT_ID));
                String name = c.getString(c.getColumnIndex(ALIGNMENT_NAME));
                Alignment alignment = new Alignment(alignId, name);
                // adding to list
                alignArrayList.add(alignment);
            } while (c.moveToNext());
        }
        c.close();
        return alignArrayList;
    }

    public long updateCharacter(CharacterModel character) {
        checkDB();
        ContentValues values = new ContentValues();
        values.put(CHARACTER_NAME, character.name);
        values.put(CHARACTER_TEXT, character.text);
        values.put(CHARACTER_LEVEL, character.level);
        values.put(CHARACTER_CLASS, character.getClassName());
        values.put(CHARACTER_XP, character.xp);
        values.put(CHARACTER_ALIGN, character.getAlignmentId());
        values.put(CHARACTER_AC, character.ac);
        values.put(CHARACTER_STR, character.str);
        values.put(CHARACTER_CON, character.con);
        values.put(CHARACTER_DEX, character.dex);
        values.put(CHARACTER_WIS, character.wis);
        values.put(CHARACTER_INT, character.intel);
        values.put(CHARACTER_CHR, character.chr);
        return db.update(TABLE_CHARACTERS, values, "id=" + character.id, null);
    }

    public long updateWeapon(WeaponModel weapon) {
        checkDB();
        ContentValues values = new ContentValues();
        values.put(ITEM_NAME, weapon.name);
        values.put(ITEM_TEXT, weapon.text);
        values.put(WEAPON_ATTACK, weapon.atk);
        values.put(WEAPON_DAMAGE, weapon.dmg);
        values.put(WEAPON_TYPE, weapon.dmgType);

        return db.update(TABLE_ITEMS, values, "id=" + weapon.id, null);
    }

    private void checkDB() {
        if (db == null || !db.isOpen()) {
            db = this.getReadableDatabase();
        }
    }
}
