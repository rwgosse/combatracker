package com.myapp.combattracker.playermodule;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.myapp.combattracker.database.SQLHelper;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.content.Intent;
import android.view.View;
import com.myapp.combattracker.*;
import com.myapp.combattracker.models.*;
import java.util.*;




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



        textTemp = (TextView) findViewById(R.id.textTemp);

    }


    public void click_new(View view) {
        Intent myIntent = new Intent(this, EditCharacterActivity.class);

        startActivity(myIntent);
    }




    private void populate_character_choice() {
        spinner = (Spinner) findViewById(R.id.spinner_character_choice);


        // Spinner Drop down elements
        List<String> characters = new ArrayList<String>();
    /*        categories.add("Business Services");
            categories.add("Computers");
            categories.add("Education");
            categories.add("Personal");
            categories.add("Travel");*/

        list = sqlHelper.getAllCharactersList();

        for(CharacterModel character : list){
            characters.add(character.name + " " + character.getClassName());

        }


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, characters);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }
}
