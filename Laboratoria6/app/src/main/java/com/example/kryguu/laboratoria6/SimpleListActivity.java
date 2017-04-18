package com.example.kryguu.laboratoria6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SimpleListActivity extends AppCompatActivity {

    ListView listView;
    private ArrayAdapter<String> mAdapter;
    private String[] mCarNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_list);
        initUIComponents();
        setOnListItemClick();
    }

    private void initUIComponents() {
        listView = (ListView) findViewById(R.id.listView);
        initArray();
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mCarNames);
        listView.setAdapter(mAdapter);
    }

    private void initArray() {
        mCarNames = getResources().getStringArray(R.array.cars);
    }

    private void setOnListItemClick() {
        listView.setOnItemClickListener(getItemClickListener());
    }

    private AdapterView.OnItemClickListener getItemClickListener() {
        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView textView = (TextView) view.findViewById(android.R.id.text1);
                String text = (i+1) + ". " + textView.getText().toString();
                Toast.makeText(view.getContext(), text, Toast.LENGTH_SHORT).show();
            }
        };
        return listener;
    }
}
