package com.myapp.combattracker.playermodule;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.myapp.combattracker.Helpers.PlayerHelper;
import com.myapp.combattracker.R;
import com.myapp.combattracker.database.SQLHelper;
import com.myapp.combattracker.models.Alignment;
import com.myapp.combattracker.models.CharacterClassModel;
import com.myapp.combattracker.models.CharacterModel;
import com.myapp.combattracker.models.WeaponModel;

import java.util.ArrayList;
import java.util.List;

public class EditCharacterActivity extends AppCompatActivity {


    ListView lv;
    SQLHelper sqlHelper;
    EditText text_name;
    Spinner spin_class;
    Spinner spin_align;
    EditText text_level;
    EditText text_xp;
    TextView textView_acbox;
    TextView textView_strstat;
    TextView textView_constat;
    TextView textView_dexstat;
    TextView textView_wisstat;
    TextView textView_intstat;
    TextView textView_chrstat;
    Button button_save;
    boolean isNew = true;
    Intent mIntent;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_character);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        sqlHelper = new SQLHelper(getApplicationContext());
        db = sqlHelper.getWritableDatabase();
        loadViews();
        populate_alignment_spinner();
        populate_class_spinner();
        mIntent = getIntent();

        if (mIntent.hasExtra("character_id")) {
            isNew = false;
            loadCharacter();
        } else {
            newCharacter();
        }

    }


    private void newCharacter() {
        text_name.setText("");
        //spin_class.setSelection(character.characterClassModel.id - 1);
        text_level.setText(String.valueOf(1));
        text_xp.setText(String.valueOf(0));
        //spin_align.setSelection(character.alignment.id);
        //populate_attack_listview(character);
        textView_acbox.setText(String.valueOf(PlayerHelper.roll(3, 6)));
        textView_strstat.setText(String.valueOf(PlayerHelper.roll(3, 6)));
        textView_dexstat.setText(String.valueOf(PlayerHelper.roll(3, 6)));
        textView_constat.setText(String.valueOf(PlayerHelper.roll(3, 6)));
        textView_wisstat.setText(String.valueOf(PlayerHelper.roll(3, 6)));
        textView_intstat.setText(String.valueOf(PlayerHelper.roll(3, 6)));
        textView_chrstat.setText(String.valueOf(PlayerHelper.roll(3, 6)));
        populate_attack_listview();
    }

    private void loadViews() {
        lv = (ListView) findViewById(R.id.listView_attacks);
        text_name = (EditText) findViewById(R.id.editText_name);
        spin_class = (Spinner) findViewById(R.id.spinner_class);
        spin_align = (Spinner) findViewById(R.id.spinner_align);
        text_level = (EditText) findViewById(R.id.editText_level);
        text_xp = (EditText) findViewById(R.id.editText_xp);
        textView_acbox = (TextView) findViewById(R.id.textView_acbox);
        textView_strstat = (TextView) findViewById(R.id.textView_strstat);
        textView_constat = (TextView) findViewById(R.id.textView_constat);
        textView_dexstat = (TextView) findViewById(R.id.textView_dexstat);
        textView_wisstat = (TextView) findViewById(R.id.textView_wizstat);
        textView_intstat = (TextView) findViewById(R.id.textView_intstat);
        textView_chrstat = (TextView) findViewById(R.id.textView_chrstat);
        button_save = (Button) findViewById(R.id.button_save);
    }

    private void loadCharacter() {
        int intValue = mIntent.getIntExtra("character_id", 0);
        CharacterModel character = sqlHelper.getCharacter(intValue);
        text_name.setText(character.name);
        spin_class.setSelection(character.characterClassModel.id - 1);
        text_level.setText(String.valueOf(character.level));
        text_xp.setText(String.valueOf(character.xp));
        spin_align.setSelection(character.alignment.id);
        populate_attack_listview(character);
        textView_acbox.setText(String.valueOf(character.ac));
        textView_strstat.setText(String.valueOf(character.str));
        textView_dexstat.setText(String.valueOf(character.dex));
        textView_constat.setText(String.valueOf(character.con));
        textView_wisstat.setText(String.valueOf(character.wis));
        textView_intstat.setText(String.valueOf(character.intel));
        System.out.println("chr =" + character.chr);
        textView_chrstat.setText(String.valueOf(character.chr));
    }

    private void populate_alignment_spinner() {
        // Spinner Drop down elements
        List<Alignment> alignments = new ArrayList<Alignment>();
        List<Alignment> list = sqlHelper.getAllAlignList();

        for (Alignment alignment : list) {
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

        for (CharacterClassModel character_class : list) {
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

        //unarmed strike deals bludgeoning damage equal to 1 + your Strength modifier
        // if (countAttacks == 0){
        //   attackList.add("Unarmed" + " +" + characterModel.getModifier(characterModel.dex)  + "   " + characterModel.getUnarmedStrikeDMG() + " " + "Bludgeoning");
        // }

        while (countAttacks < minAttacks) {
            attackList.add("none");
            countAttacks = attackList.size();

        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                attackList);

        lv.setAdapter(arrayAdapter);
    }

    private void populate_attack_listview(CharacterModel characterModel) {
        ArrayList<WeaponModel> weapons = characterModel.getWeapons();
        List<String> attackList = new ArrayList<String>();
        int minAttacks = 4;
        for (WeaponModel weapon : weapons) {
            attackList.add(weapon.toString());
        }
        int countAttacks = attackList.size();

        //unarmed strike deals bludgeoning damage equal to 1 + your Strength modifier
        if (countAttacks == 0) {
            attackList.add("Unarmed" + " +" + PlayerHelper.getModifier(characterModel.dex) + "   " + characterModel.getUnarmedStrikeDMG() + " " + "Bludgeoning");
        }

        while (countAttacks < minAttacks) {
            attackList.add("none");
            countAttacks = attackList.size();

        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                attackList);

        lv.setAdapter(arrayAdapter);


    }

    public void click_save(View view) {
        String name = text_name.getText().toString();
        CharacterClassModel characterClass = sqlHelper.getCharacterClass(db, spin_class.getSelectedItem().toString());
        int level = Integer.parseInt(text_level.getText().toString());
        int xp = Integer.parseInt(text_xp.getText().toString());
        Alignment alignment = sqlHelper.getAlignment(spin_align.getSelectedItem().toString());
        int ac = Integer.parseInt(textView_acbox.getText().toString());
        int str = Integer.parseInt(textView_strstat.getText().toString());
        int dex = Integer.parseInt(textView_dexstat.getText().toString());
        int con = Integer.parseInt(textView_constat.getText().toString());
        int wis = Integer.parseInt(textView_wisstat.getText().toString());
        int intel = Integer.parseInt(textView_intstat.getText().toString());
        int chr = Integer.parseInt(textView_chrstat.getText().toString());

        if (name != null && characterClass != null && level >= 1 && xp >= 0 && alignment != null
                && ac > 0 && str > 0 && dex > 0 && con > 0 && wis > 0 && intel > 0 && chr > 0) {

            if (isNew) {
                CharacterModel newCharacter = new CharacterModel(name, "", characterClass, alignment, level, xp, ac, str, dex, con, wis, intel, chr);
                sqlHelper.addCharacter(newCharacter);
            } else {
                // existing character;
                int id = mIntent.getIntExtra("character_id", 0);
                CharacterModel oldCharacter = new CharacterModel(id, name, "", characterClass, alignment, level, xp, ac, str, dex, con, wis, intel, chr);
                sqlHelper.updateCharacter(oldCharacter);
            }
            alertView("Character Saved");


        } else {
            // unfilled fields
            alertView("Please complete all fields");
        }


    }

    private void alertView(String message) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setTitle("Combat Tracker")
                //.setIcon(R.drawable.ic_launcher)
                .setMessage(message)
                //  .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                //      public void onClick(DialogInterface dialoginterface, int i) {
                //          dialoginterface.cancel();
                //          }})
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        finish();
                    }
                }).show();
    }


}
