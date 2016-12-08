package com.myapp.combattracker.playermodule;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.content.Intent;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import com.myapp.combattracker.database.SQLHelper;
import com.myapp.combattracker.models.CharacterModel;
import com.myapp.combattracker.models.ItemModel;
import com.myapp.combattracker.models.MyCustomAdapter;
import com.myapp.combattracker.models.WeaponModel;
import com.myapp.combattracker.R;

import java.util.ArrayList;





public class EditInventoryActivity extends AppCompatActivity {

    private SQLHelper sqlHelper;
    private SQLiteDatabase db;
    private ArrayList<ItemModel> inventory;
    private Intent mIntent;
    private ListView lv;
    private CharacterModel character;
    private ItemModel tempItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_inventory);
        lv = (ListView) findViewById(R.id.listView_inventory);
        inventoryClicker();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        sqlHelper = new SQLHelper(getApplicationContext());
        db = sqlHelper.getWritableDatabase();
        inventory = new ArrayList<ItemModel>();
        mIntent = getIntent();
        if (mIntent.hasExtra("character_id")) {
            int intValue = mIntent.getIntExtra("character_id", 0);
            character = sqlHelper.getCharacter(intValue);
            populate(character);

        }
    }

    @Override
    public void onResume() {  // After a pause OR at startup
        super.onResume();
        character = sqlHelper.getCharacter(character.id);
        populate(character);

    }

    private void populate(CharacterModel character) {
        inventory = character.getInventory();

         //   ArrayList<String> inventoryList = new ArrayList<String>();
       // for (ItemModel item : inventory) {
       //     inventoryList.add(item.toString());
       // }
        //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
             //   this,
               // android.R.layout.simple_list_item_1,
             //   inventoryList);

        MyCustomAdapter arrayAdapter = new MyCustomAdapter(inventory, this);

        lv.setAdapter(arrayAdapter);

    }

    private void inventoryClicker() {
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3) {
                String value = adapter.getItemAtPosition(position).toString();
                System.out.println("clicked " + position + " value = " + value);
                try {
                    System.out.println("relates to: " + inventory.get(position));
                    tempItem = inventory.get(position);
                    int clickedItemID = tempItem.id;
                    System.out.println("db weapons id = " + clickedItemID);
                    Intent myIntent = new Intent(getApplicationContext(), EditItemActivity.class);
                    myIntent.putExtra("item_id", clickedItemID);
                    startActivity((myIntent));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("not found");
                   // if (!isNew && value.equals("none")) {
                       // newWeaponDialog("Create a New Weapon?");

                   // }


                    // do nothing
                }
                // assuming string and if you want to get the value on click of list item
                // do what you intend to do on click of listview row
            }
    });
    }




    private void newWeaponDialog(String message) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setTitle("Combat Tracker")
                //.setIcon(R.drawable.ic_launcher)
                .setMessage(message)
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        dialoginterface.cancel();
                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        Intent myIntent = new Intent(getApplicationContext(), EditItemActivity.class);
                        myIntent.putExtra("character_id", character.id);


                        startActivity((myIntent));
                    }
                }).show();
    }

    public void new_item_onclick(View view) {
        Intent myIntent = new Intent(getApplicationContext(), EditItemActivity.class);
        myIntent.putExtra("character_id", character.id);
        myIntent.putExtra("notWeapon", 0);
        startActivity((myIntent));
    }

    public void new_weapon_onclick(View view) {
        Intent myIntent = new Intent(getApplicationContext(), EditItemActivity.class);
        myIntent.putExtra("character_id", character.id);
        startActivity((myIntent));
    }
}
