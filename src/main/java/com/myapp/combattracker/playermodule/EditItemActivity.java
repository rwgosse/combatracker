package com.myapp.combattracker.playermodule;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
    private WeaponModel weapon;
    private boolean isNew = true;

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
        if (mIntent.hasExtra("weapon_id")) {
            isNew = false;
            loadWeapon();
        } else {
            newWeapon();
        }



    }

    private void newWeapon() {
        System.out.println("create Weapon");
        name.setHint("Name");
        description.setHint("Description");
        atk.setText(Integer.toString(0));
        dmg.setText("1d6");
        type.setHint("Damage Type");


    }

    private void loadWeapon() {
        int intValue = mIntent.getIntExtra("weapon_id", 0);
        weapon = sqlHelper.getItem(intValue);
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

    public void click_save(View view) {
        sqlHelper.getReadableDatabase();
        String weaponName = name.getText().toString();
        String weaponText = description.getText().toString();
        int weaponAtk;
        try {
            weaponAtk = Integer.parseInt(atk.getText().toString());
        }
        catch(NumberFormatException e) {
            weaponAtk = 0;
        }
        String weaponDmg = dmg.getText().toString();
        String weaponType = type.getText().toString();


        if (!weaponName.isEmpty() && !weaponText.isEmpty() && weaponAtk >= 0 && weaponDmg.matches("\\d*d\\d*") && !weaponType.isEmpty()) {

            if (!isNew) {
                weapon.name = weaponName;
                weapon.text = weaponText;
                weapon.atk = weaponAtk;
                weapon.dmg = weaponDmg;
                weapon.dmgType = weaponType;
                sqlHelper.updateWeapon(weapon);
            }
            else {
                int characterID = 0;

                    characterID = mIntent.getIntExtra("character_id", 0);

                WeaponModel newWeapon = new WeaponModel(characterID, weaponName, weaponText, weaponAtk, weaponDmg, weaponType);
                sqlHelper.addItem(newWeapon);
            }




            alertView(true, "Weapon Saved");


        } else {
            // unfilled fields
            alertView(false, "Please complete all fields");
        }
    }
    private void alertView(final boolean saved, String message) {
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
                        if (saved) {
                            finish();
                        }
                    }
                }).show();
    }



}
