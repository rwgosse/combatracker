package com.myapp.combattracker.playermodule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.myapp.combattracker.R;
import com.myapp.combattracker.models.CharacterModel;
import com.myapp.combattracker.models.WeaponModel;
import com.myapp.combattracker.database.SQLHelper;


public class EditItemActivity extends AppCompatActivity {

    private Intent mIntent;
    private SQLHelper sqlHelper;
    private EditText name;
    private EditText description;
    private EditText atk;
    private EditText dmg;
    private EditText type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        sqlHelper = new SQLHelper(getApplicationContext());
        name = (EditText) findViewById(R.id.editTextName);
        description = (EditText) findViewById(R.id.editTextDescription);
        atk = (EditText) findViewById(R.id.editTextAtk);
        dmg  = (EditText) findViewById(R.id.editTextDmg);
        type = (EditText) findViewById(R.id.editTextType);
        populate();


    }

    private void populate() {
        mIntent = getIntent();
        int intValue = mIntent.getIntExtra("weapon_id", 0);
        WeaponModel weapon = sqlHelper.getItem(intValue);
        System.out.println("got Weapon: " + weapon);
        name.setText(weapon.name);
        description.setText(weapon.text);
        atk.setText(Integer.toString(weapon.atk));
        dmg.setText(weapon.dmg);
        type.setText(weapon.dmgType);
    }

    public void click_cancel(View view) {
       finish();
    }



}
