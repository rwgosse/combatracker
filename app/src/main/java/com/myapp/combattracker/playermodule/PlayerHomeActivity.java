package com.myapp.combattracker.playermodule;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.myapp.combattracker.R;
import com.myapp.combattracker.database.SQLHelper;
import com.myapp.combattracker.models.CharacterModel;

import java.util.ArrayList;
import java.util.List;


public class PlayerHomeActivity extends AppCompatActivity {

    SQLHelper sqlHelper;
    TextView textTemp;
    Spinner spinner;
    List<CharacterModel> list = new ArrayList<CharacterModel>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_home);
        sqlHelper = new SQLHelper(getApplicationContext());
        populate_character_choice();
    }

    @Override
    public void onResume() {  // After a pause OR at startup
        super.onResume();
        populate_character_choice();
    }


    public void click_new(View view) {
        Intent myIntent = new Intent(this, EditCharacterActivity.class);

        startActivity(myIntent);
    }

    public void click_load(View view) {
        Intent myIntent = new Intent(this, EditCharacterActivity.class);

        CharacterModel selected = (CharacterModel) spinner.getSelectedItem();

        myIntent.putExtra("character_id", selected.id);
        startActivity((myIntent));
    }


    private void populate_character_choice() {
        spinner = (Spinner) findViewById(R.id.spinner_character_choice);


        // Spinner Drop down elements
        List<CharacterModel> characters = new ArrayList<CharacterModel>();
        list = sqlHelper.getAllCharactersList();

        for (CharacterModel character : list) {
            characters.add(character);
        }

        // Creating adapter for spinner
        ArrayAdapter<CharacterModel> dataAdapter = new ArrayAdapter<CharacterModel>(this, android.R.layout.simple_spinner_item, characters);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }
}
