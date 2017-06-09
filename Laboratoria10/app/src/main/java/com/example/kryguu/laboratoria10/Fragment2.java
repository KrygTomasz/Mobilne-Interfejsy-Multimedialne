package com.example.kryguu.laboratoria10;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Fragment2 extends Fragment {

    private TextView mInfoText;
    
    public Fragment2() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment2, container, false);
        mInfoText = (TextView) view.findViewById(R.id.info);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment2_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
    
    // odkomentować poniższy fragment w celu obsługi menu kontekstowego

    @Override
    public void onResume() {
        super.onResume();
        registerForContextMenu(getActivity().findViewById(android.R.id.content));
    }

    @Override
    public void onPause() {
        unregisterForContextMenu(getActivity().findViewById(android.R.id.content));
        super.onPause();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.fragment2_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.clear_information:
                SetInfo(getString(R.string.empty_info));
                return true;
        }
        return super.onContextItemSelected(item);
    }

    public void SetInfo(String info)
    {
        mInfoText.setText(info);
    }
}
