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


    public String text;


    public CharacterModel(String name, String text, CharacterClassModel characterClassModel) {
        // TODO Auto-generated constructor stub
        //this.id = id;
        this.name = name;
        this.text = text;
        this.level = 0;
        this.xp = 0;
        this.characterClassModel = characterClassModel;

    }
    public CharacterModel(){

    }

    public String getClassName() {
        return characterClassModel.name;
    }


}
