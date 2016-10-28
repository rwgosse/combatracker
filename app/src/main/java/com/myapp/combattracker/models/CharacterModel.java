package com.myapp.combattracker.models;

/**
 * Created by Richard on 2016-10-26.
 */

public class CharacterModel {
    public int id;
    public String name;
    public int level;
    public int xp;
    public CharacterClassModel characterClassModel;
    public String alignment;

    public int ac;
    public int str;
    public int con;
    public int dex;
    public int wis;
    public int intel;
    public int chr;


    public String text;


    public CharacterModel(String name, String text, CharacterClassModel characterClassModel, String alignment) {
        // TODO Auto-generated constructor stub
        //this.id = id;
        this.name = name;
        this.text = text;
        this.level = 0;
        this.xp = 0;
        this.characterClassModel = characterClassModel;
        this.alignment = alignment;



    }
    public CharacterModel(){

    }

    public String getClassName() {
        return characterClassModel.name;
    }


}
