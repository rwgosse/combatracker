package com.myapp.combattracker.models;

/**
 * Created by Richard on 2016-11-03.
 */

public class Alignment {
    private int id;
    private String name;

    public Alignment() {

    }

    public Alignment(int id, String name) {
        this.id = id;
        this.name = name;

    }

    @Override
    public String toString() {
        return this.name;
    }

    public int getId(){
        return id;
    }
}
