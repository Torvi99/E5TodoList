package com.androidpprog2.e4todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ShareActionProvider;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdapter;
    private ListView lvItems;
    private String allTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadPreferences();
        lvItems = (ListView) findViewById(R.id.lvItems);
        items = new ArrayList<String>(Arrays.asList(allTask.split(",")));
        itemsAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, items);
        lvItems.setAdapter(itemsAdapter);

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        savePreferences(allTask);
    }

    public void onAddItem(View v) {
        EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString();
        itemsAdapter.add(itemText);
        etNewItem.setText("");
        allTask = allTask + "," + itemText;
    }

    public void savePreferences(String item){
        SharedPreferences preferences = getSharedPreferences("tasks", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("task", item);
        editor.commit();
    }

    public void loadPreferences(){
        SharedPreferences preferences = getSharedPreferences("tasks", Context.MODE_PRIVATE);
        allTask = preferences.getString("task", "");
    }
}




