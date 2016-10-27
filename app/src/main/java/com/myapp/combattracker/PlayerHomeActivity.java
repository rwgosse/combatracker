package com.myapp.combattracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import java.util.*;


import com.myapp.combattracker.EditCharacterActivity;
import com.myapp.espressotesting.R;

public class PlayerHomeActivity extends AppCompatActivity {

    SQLHelper sqlHelper;
    TextView textTemp;
    Spinner spinner;
    List<StudentsModel> list = new ArrayList<StudentsModel>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_home);
        sqlHelper = new SQLHelper(getApplicationContext());

        StudentsModel student1 = new StudentsModel(1, "Reggie", "222-2222");
        StudentsModel student2 = new StudentsModel(2, "Frank", "333-2222");
        sqlHelper.addStudentDetail(student1);
        sqlHelper.addStudentDetail(student2);


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
        List<String> categories = new ArrayList<String>();
        categories.add("Business Services");
        categories.add("Computers");
        categories.add("Education");
        categories.add("Personal");
        categories.add("Travel");

        list = sqlHelper.getAllStudentsList();

        for(StudentsModel sm : list){
            categories.add(sm.name);

        }


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }
}
