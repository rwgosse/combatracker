package com.myapp.combattracker.models;

/**
 * Created by Richard on 2016-10-26.
 */

public class ItemModel {
    public int id;
    public int ownerid;
    public String name;
    public String text;

    public ItemModel(String name, String text) {

        this.name = name;
        this.text = text;
    }

    public ItemModel(int id, int ownerid, String name, String text) {
        this.id = id;
        this.ownerid = ownerid;

        this.name = name;
        this.text = text;
    }

    public ItemModel(){

    }

    public ItemModel(int ownerid, String name, String text){
        this.ownerid = ownerid;
        this.name = name;
        this.text = text;
    }






    public String toString() {
        return name;
    }
}
