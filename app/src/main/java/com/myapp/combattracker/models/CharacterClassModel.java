package com.myapp.combattracker.models;

/**
 * Created by Richard on 2016-10-27.
 */

public class CharacterClassModel {

    public int id;
    public String name;

    public CharacterClassModel() {

    }

    public CharacterClassModel(String name) {
        this.name = name;
    }


public CharacterClassModel(int id, String name) {
    this.id = id;
    this.name = name;
}
}

