package com.myapp.combattracker.models;

/**
 * Created by Richard on 2016-10-26.
 */

public class ItemModel {
    public String name;
    public String text;

    public ItemModel(String name, String test) {
        this.name = name;
        this.text = text;
    }

    public String toString() {
        return name;
    }
}
