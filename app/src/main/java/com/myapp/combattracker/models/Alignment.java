package com.myapp.combattracker.models;

/**
 * Created by Richard on 2016-11-03.
 */

public class Alignment {
    public int id;
    public String name;

    public Alignment() {

    }

    public Alignment(String name) {

        this.name = name;

    }

    @Override
    public String toString() {
        return this.name;
    }
}
