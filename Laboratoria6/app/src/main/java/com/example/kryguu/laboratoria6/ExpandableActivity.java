package com.example.kryguu.laboratoria6;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ExpandableActivity extends AppCompatActivity {

    ExpandableListView expListView;
    ArrayList<Entry> entries;
    private String[] mCarNames;
    TypedArray mCarImages;
    TypedArray mCarModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable);

        initUIComponents();
        initArrays();
        initAdapter();
        setOnListItemClick();
    }

    private void initUIComponents() { // inits user interface components
        expListView = (ExpandableListView) findViewById(R.id.expandableListView);
    }

    private void initArrays() { // inits all arrays from resources
        mCarNames = getResources().getStringArray(R.array.cars);
        mCarImages = getResources().obtainTypedArray(R.array.cars_references);
        mCarModels = getResources().obtainTypedArray(R.array.cars_model);
        entries = new ArrayList<>();
        for (int i = 0; i < mCarNames.length; i++) {
            String[] models = getResources().getStringArray(mCarModels.getResourceId(i,-1));
            Entry entry = new Entry(mCarImages.getResourceId(i, 0), mCarNames[i], models);
            entries.add(entry);
        }
    }

    private void initAdapter() { // inits adapter
        ExpandableAdapter adapter = new ExpandableAdapter(this,entries);
        expListView.setAdapter(adapter);
    }

    private void setOnListItemClick() { // sets listeners for item click
        expListView.setOnGroupClickListener(getOnGroupClickListener());
        expListView.setOnChildClickListener(getOnChildClickListener());
    }

    private ExpandableListView.OnGroupClickListener getOnGroupClickListener() { // returns click listener for section
        return new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                String text = ((TextView) view.findViewById(R.id.carBrandName)).getText().toString();
                String result = i + " - " + text;
                Toast.makeText(ExpandableActivity.this, result, Toast.LENGTH_SHORT).show();
                return false;
            }
        };
    }

    private ExpandableListView.OnChildClickListener getOnChildClickListener() { // returns click listener for item
        return new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                String text = ((TextView) view.findViewById(R.id.child_text_view)).getText().toString();
                String result = i + "." + i1 + " - " + text;
                Toast.makeText(ExpandableActivity.this, result, Toast.LENGTH_SHORT).show();
                return false;
            }
        };
    }
}
