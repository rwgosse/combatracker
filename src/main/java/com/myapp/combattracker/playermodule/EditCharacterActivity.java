package com.myapp.combattracker.playermodule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.EditText;

import com.myapp.combattracker.database.SQLHelper;
import com.myapp.combattracker.*;

import java.util.ArrayList;
import java.util.List;
import com.myapp.combattracker.models.*;

public class EditCharacterActivity extends AppCompatActivity {


    ListView lv;
    SQLHelper sqlHelper;
    EditText name;
    Spinner spin_class;
    Spinner spin_align;
    EditText text_level;
    EditText text_xp;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_character);
        sqlHelper = new SQLHelper(getApplicationContext());

        lv = (ListView) findViewById(R.id.listView_attacks);
        name = (EditText) findViewById(R.id.editText_name);
        spin_class = (Spinner) findViewById(R.id.spinner_class);
        spin_align = (Spinner) findViewById(R.id.spinner_align);
        text_level = (EditText) findViewById(R.id.editText_level);
        text_xp = (EditText) findViewById(R.id.editText_xp);


        populate_alignment_spinner();
        populate_class_spinner();

        Intent mIntent = getIntent();

        if (mIntent.hasExtra("character_id")) {
            int intValue = mIntent.getIntExtra("character_id", 0);
            CharacterModel character = sqlHelper.getCharacter(intValue);
            name.setText(character.name);
            spin_class.setSelection(character.characterClassModel.id - 1);
            String levelString = "1";
            levelString = levelString.valueOf(character.level);
            text_level.setText(levelString);
            String xpString = "0";
            xpString = levelString.valueOf(character.xp);
            text_xp.setText(xpString);
            spin_align.setSelection(character.alignment.id);
            populate_attack_listview(character.getWeapons());

        }
        else {
            name.setText("");
            populate_attack_listview();
        }







    }

    private void populate_alignment_spinner() {
        // Spinner Drop down elements
        List<Alignment> alignments = new ArrayList<Alignment>();
        List<Alignment> list = sqlHelper.getAllAlignList();

        for(Alignment alignment : list){
            alignments.add(alignment);
        }

        // Creating adapter for spinner
        ArrayAdapter<Alignment> dataAdapter = new ArrayAdapter<Alignment>(this, android.R.layout.simple_spinner_item, alignments);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spin_align.setAdapter(dataAdapter);
    }

    private void populate_class_spinner() {
        // Spinner Drop down elements
        List<CharacterClassModel> character_classes = new ArrayList<CharacterClassModel>();
        List<CharacterClassModel> list = sqlHelper.getAllClassList();

        for(CharacterClassModel character_class : list){
            character_classes.add(character_class);
        }

        // Creating adapter for spinner
        ArrayAdapter<CharacterClassModel> dataAdapter = new ArrayAdapter<CharacterClassModel>(this, android.R.layout.simple_spinner_item, character_classes);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spin_class.setAdapter(dataAdapter);
    }

    private void populate_attack_listview() {


        //WeaponModel sword = new WeaponModel("Sword", "Sharp Stabby Thing", 2, "1d6", "Slashing");


        List<String> attackList = new ArrayList<String>();
        //attackList.add(sword.toString());

        int minAttacks = 4;
        int countAttacks = attackList.size();

        while(countAttacks < minAttacks) {
        attackList.add("none");
            countAttacks = attackList.size();

        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                attackList );

        lv.setAdapter(arrayAdapter);
    }

    private void populate_attack_listview(ArrayList<WeaponModel> weapons) {
        List<String> attackList = new ArrayList<String>();
        int minAttacks = 4;
        for(WeaponModel weapon : weapons) {
            attackList.add(weapon.toString());
        }
        int countAttacks = attackList.size();

        while(countAttacks < minAttacks) {
            attackList.add("none");
            countAttacks = attackList.size();

        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                attackList );

        lv.setAdapter(arrayAdapter);



    }



}
