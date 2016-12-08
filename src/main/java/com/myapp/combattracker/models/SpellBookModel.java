package com.myapp.combattracker.models;

import java.util.ArrayList;

import com.myapp.combattracker.models.ItemModel;

/**
 * Created by Richard on 2016-12-03.
 */

public class SpellBookModel extends ItemModel {


    private ArrayList<SpellModel> spellList;


    public SpellBookModel(int id, int ownerid, String name, String text) {
        super(id, ownerid, name, text);
        spellList = new ArrayList<SpellModel>();

    }


    public SpellBookModel(int id, int ownerid, String name, String text, ArrayList<SpellModel> spellList)  {
        super(id, ownerid, name, text);
        this.spellList = spellList;
    }

}
