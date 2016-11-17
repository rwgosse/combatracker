package com.myapp.combattracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.myapp.combattracker.dmmodule.DmHomeActivity;
import com.myapp.combattracker.playermodule.PlayerHomeActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    public void click_player_home(View view) {
        Intent myIntent = new Intent(this, PlayerHomeActivity.class);
        startActivity(myIntent);
    }

    public void click_dm_home(View view) {
        Intent myIntent = new Intent(this, DmHomeActivity.class);
        startActivity(myIntent);
    }
}