package com.myapp.combattracker.models;


/**
 * Created by Richard on 2016-10-28.
 */

public class WeaponModel extends ItemModel {

    public int atk;
    public String dmg;
    public String dmgType;

    public WeaponModel(String name, String text, int atk, String dmg, String dmgType ) {
        super(name, text);
        this.atk = atk;
        this.dmg = dmg;
        this.dmgType = dmgType;

    }

    public WeaponModel(int id, int ownerid, String name, String text, int atk, String dmg, String dmgType ) {
        super(id, ownerid, name, text);
        this.atk = atk;
        this.dmg = dmg;
        this.dmgType = dmgType;

    }

    public WeaponModel(int ownerid, String name, String text, int atk, String dmg, String dmgType ) {
        super(ownerid, name, text);
        this.atk = atk;
        this.dmg = dmg;
        this.dmgType = dmgType;

    }

    public WeaponModel() {

    }

    @Override
    // "Sword   +2    d10 Slashing"
    public String toString() {
        String string;
        string = name + " +" + atk + "   " + dmg + " " + dmgType;
        return string;
    }
}


