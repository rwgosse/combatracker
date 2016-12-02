package com.myapp.combattracker.models;

import com.myapp.combattracker.Helpers.PlayerHelper;

import java.util.ArrayList;

/**
 * Created by Richard on 2016-10-26.
 */

public class CharacterModel {
    public int id;
    public String name;
    public int level;
    public int xp;
    public CharacterClassModel characterClassModel;
    private Alignment alignment;
    public String text;

    public int ac;
    public int str;
    public int con;
    public int dex;
    public int wis;
    public int intel;
    public int chr;

    private ArrayList<ItemModel> inventory;


    // constructor for new characters, starting at level 1
    public CharacterModel(String name, String text, CharacterClassModel characterClassModel, Alignment alignment) {


        this.name = name;
        this.text = text;
        this.characterClassModel = characterClassModel;
        this.alignment = alignment;
        this.level = 1;
        this.xp = 0;
        this.inventory = new ArrayList<ItemModel>();

        //random stats for new characters
        this.str = PlayerHelper.generateStat();
        this.con = PlayerHelper.generateStat();
        this.dex = PlayerHelper.generateStat();
        this.wis = PlayerHelper.generateStat();
        this.intel = PlayerHelper.generateStat();
        this.chr = PlayerHelper.generateStat();
        this.ac = 10 + PlayerHelper.getModifier(this.dex);


    }


    // constructor for existing characters, such as those in the database
    public CharacterModel(int id, String name, String text, CharacterClassModel characterClassModel, Alignment alignment, int level, int xp,
                          int ac, int str, int con, int dex, int wis, int intel, int chr) {
        // TODO Auto-generated constructor stub
        this.id = id;
        this.name = name;
        this.text = text;
        this.characterClassModel = characterClassModel;
        this.alignment = alignment;
        this.level = level;
        this.xp = xp;
        this.ac = ac;
        this.str = str;
        this.con = con;
        this.dex = dex;
        this.wis = wis;
        this.intel = intel;
        this.chr = chr;


        this.inventory = new ArrayList<ItemModel>();


    }

    // constructor for new characters when saving
    public CharacterModel(String name, String text, CharacterClassModel characterClassModel, Alignment alignment, int level, int xp,
                          int ac, int str, int con, int dex, int wis, int intel, int chr) {
        this.name = name;
        this.text = text;
        this.characterClassModel = characterClassModel;
        this.alignment = alignment;
        this.level = level;
        this.xp = xp;
        this.ac = ac;
        this.str = str;
        this.con = con;
        this.dex = dex;
        this.wis = wis;
        this.intel = intel;
        this.chr = chr;
        this.inventory = new ArrayList<ItemModel>();


    }

    public CharacterModel() {
        this.inventory = new ArrayList<ItemModel>();

    }

    public String getClassName() {
        return characterClassModel.name;
    }

    @Override
    public String toString() {
        return this.name + " - " + this.characterClassModel.name + " " + "(" + this.level + ")";
    }

    public void addItem(ItemModel item) {
        if (item != null) {
            inventory.add(item);
        }
    }

    public void removeItem(int itemID) {
        //not yet implemented
    }

    public ArrayList<WeaponModel> getWeapons() {
        ArrayList<WeaponModel> weapons = new ArrayList<WeaponModel>();
        for (ItemModel item : inventory) {
            System.out.println(item.getClass());
            if (item instanceof WeaponModel) {

                weapons.add((WeaponModel) item);
            }

        }
        return weapons;

    }

    public ArrayList<ItemModel> getInventory() {
        return inventory;
    }


    public int getUnarmedStrikeDMG() {
        int damage = 1 + PlayerHelper.getModifier(str);
        if (damage >= 1) {
            return damage;
        }
        else {
            return 1;
        }


    }

    public int getAlignmentId() {
        return this.alignment.getId();
    }

    public void setAlignment(Alignment alignment) {
        if (alignment != null) {
            this.alignment = alignment;
        }

    }


}
