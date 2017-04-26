package com.example.kryguu.laboratoria6;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GridActivity extends AppCompatActivity {

    private ArrayList<Entry> entries;
    private GridView mGridView;
    private String[] mCarNames;
    TypedArray mCarImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        initUIComponents();
        initArrays();
        initAdapter();
        setOnListItemClick();
    }

    private void initUIComponents() {
        mGridView = (GridView) findViewById(R.id.grid_view);
    }

    private void initArrays() {
        mCarNames = getResources().getStringArray(R.array.cars);
        mCarImages = getResources().obtainTypedArray(R.array.cars_references);
        entries = new ArrayList<>();
        for (int i = 0; i < mCarNames.length; i++) {
            Entry entry = new Entry(mCarImages.getResourceId(i, 0), mCarNames[i]);
            entries.add(entry);
        }
    }

    private void initAdapter() {
        EntryAdapter adapter = new EntryAdapter(this, R.layout.grid_item, entries);
        mGridView.setAdapter(adapter);
    }

private void setOnListItemClick() {
    mGridView.setOnItemClickListener(getItemClickListener());
}

    private AdapterView.OnItemClickListener getItemClickListener() {
        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView textView = (TextView) view.findViewById(R.id.carBrandName);
                String text = (i+1) + ". " + textView.getText().toString();
                Toast.makeText(view.getContext(), text, Toast.LENGTH_SHORT).show();
            }
        };
        return listener;
    }
}
