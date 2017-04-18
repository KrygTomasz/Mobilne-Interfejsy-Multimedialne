package com.example.kryguu.laboratoria6;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ImageListActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Entry> entries;
    private String[] mCarNames;
    TypedArray mCarImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);
        initUIComponents();
        initArrays();
        initAdapter();
        setOnListItemClick();
    }

    private void initUIComponents() {
        listView = (ListView) findViewById(R.id.imageListView);
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
        EntryAdapter adapter = new EntryAdapter(this, R.layout.list_item, entries);
        listView.setAdapter(adapter);
    }

    private void setOnListItemClick() {
        listView.setOnItemClickListener(getItemClickListener());
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
