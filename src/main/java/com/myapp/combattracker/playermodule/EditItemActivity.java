package com.myapp.combattracker.playermodule;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.EditText;

import com.myapp.combattracker.R;
import com.myapp.combattracker.models.CharacterModel;
import com.myapp.combattracker.models.WeaponModel;
import com.myapp.combattracker.models.ItemModel;
import com.myapp.combattracker.database.SQLHelper;


public class EditItemActivity extends AppCompatActivity {

    private Intent mIntent;
    private SQLHelper sqlHelper;
    private EditText name;
    private EditText description;
    private EditText atk;
    private EditText dmg;
    private EditText type;
    private ItemModel item;
    private ItemModel gotItem;
    private boolean isNew = true;
    private boolean isWeapon = false;
    private TextView textAtk;
    private TextView textDmg;
    private TextView textType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        sqlHelper = new SQLHelper(getApplicationContext());
        name = (EditText) findViewById(R.id.editTextName);
        description = (EditText) findViewById(R.id.editTextDescription);


            atk = (EditText) findViewById(R.id.editTextAtk);
            dmg = (EditText) findViewById(R.id.editTextDmg);
            type = (EditText) findViewById(R.id.editTextType);

        textAtk = (TextView) findViewById(R.id.textViewAtk);
        textDmg = (TextView) findViewById(R.id.textViewDmg);
        textType = (TextView) findViewById(R.id.textViewType);





        populate();


    }

    private void populate() {
        mIntent = getIntent();
        if (mIntent.hasExtra("item_id")) {
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
        if(isWeapon) {
            atk.setText(Integer.toString(0));
            dmg.setText("1d6");
            type.setHint("Damage Type");
        }


    }

    private void loadWeapon() {
        int intValue = mIntent.getIntExtra("item_id", 0);
        gotItem = sqlHelper.getItem(intValue);
        if (gotItem instanceof WeaponModel) {
            isWeapon = true;
        }
        item = gotItem;
        if(!isWeapon){
            atk.setVisibility(View.GONE);
            dmg.setVisibility(View.GONE);
            type.setVisibility(View.GONE);
            textAtk.setVisibility(View.GONE);
            textDmg.setVisibility(View.GONE);
            textType.setVisibility(View.GONE);
        }
        System.out.println("got item: " + item);
        name.setText(item.name);
        description.setText(item.text);
        if(isWeapon) {
            setWeaponFields((WeaponModel) item);
        }
    }

    private void setWeaponFields(WeaponModel item) {
        atk.setText(Integer.toString(item.atk));
        dmg.setText(item.dmg);
        type.setText(item.dmgType);
    }

    public void click_cancel(View view) {
       finish();
    }

    public void click_save(View view) {
        sqlHelper.getReadableDatabase();
        String weaponName = name.getText().toString();
        String weaponText = description.getText().toString();
        int weaponAtk = -1;
        String weaponDmg = null;
        String weaponType = null;
        if(isWeapon) {

            try {
                weaponAtk = Integer.parseInt(atk.getText().toString());
            } catch (NumberFormatException e) {
                weaponAtk = 0;
            }
            weaponDmg = dmg.getText().toString();
            weaponType = type.getText().toString();
        }


        if (((!weaponName.isEmpty() && !weaponText.isEmpty() && weaponAtk >= 0 && weaponDmg.matches("\\d*d\\d*") && !weaponType.isEmpty()))
                || (!isWeapon && !weaponName.isEmpty() && !weaponText.isEmpty())) {

            if (!isNew) {
                item.name = weaponName;
                item.text = weaponText;
                if(isWeapon) {
                    updateWeaponStats((WeaponModel)item, weaponAtk, weaponDmg, weaponType);
                    sqlHelper.updateItem((WeaponModel)item);
                }
                else {
                    sqlHelper.updateItem(item);
                }
            }
            else {
                int characterID = 0;

                    characterID = mIntent.getIntExtra("character_id", 0);

                WeaponModel newWeapon = new WeaponModel(characterID, weaponName, weaponText, weaponAtk, weaponDmg, weaponType);
                sqlHelper.addItem(newWeapon);
            }




            alertView(true, "Item Saved");


        } else {
            // unfilled fields
            alertView(false, "Please complete all fields");
        }
    }

    private void updateWeaponStats(WeaponModel item, int weaponAtk, String weaponDmg, String weaponType) {
        item.atk = weaponAtk;
        item.dmg = weaponDmg;
        item.dmgType = weaponType;
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
