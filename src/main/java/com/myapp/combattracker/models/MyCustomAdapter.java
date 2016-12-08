package com.myapp.combattracker.models;

import com.myapp.combattracker.R;
import com.myapp.combattracker.database.SQLHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Button;


import java.util.ArrayList;


public class MyCustomAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<ItemModel> list = new ArrayList<ItemModel>();
    private Context context;
    private SQLHelper sqlHelper;
    private SQLiteDatabase db;


    public MyCustomAdapter(ArrayList<ItemModel> list, Context context) {
        this.list = list;
        this.context = context;
        sqlHelper = new SQLHelper(context.getApplicationContext());
        db = sqlHelper.getWritableDatabase();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public ItemModel getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return list.get(pos).id;

        //just return 0 if your list items do not have an Id variable.
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.my_custom_list_layout, null);
        }

        //Handle TextView and display string from your list
        TextView listItemText = (TextView) view.findViewById(R.id.list_item_string);
        listItemText.setText(list.get(position).toString());

//Handle buttons and add onClickListeners
        Button deleteBtn = (Button) view.findViewById(R.id.delete_btn);


        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do something

                ItemModel item = getItem(position);
                System.out.println(item.toString());
                System.out.println(item.id);
                int itemId = item.id;

                sqlHelper.deleteItem(itemId);
                list.remove(position); //or some other task
                notifyDataSetChanged();
            }
        });


        return view;
    }

    public static int safeLongToInt(long l) {
        if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
            throw new IllegalArgumentException
                    (l + " cannot be cast to int without changing its value.");
        }
        return (int) l;
    }
}