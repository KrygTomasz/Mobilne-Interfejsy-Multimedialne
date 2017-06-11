package com.example.kryguu.laboratoria10;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Fragment1.OnFragmentInteractionListener, SimpleDialog.SimpleDialogListener, SingleChoiceDialog.SingleChoiceDialogListener, MultiChoiceDialog.MultiChoiceDialogListener {

    Menu mMenu;
    FragmentManager mFragmentManager;
    Fragment2 mInfoFragment;
    int mPosition;
    boolean[] checkedItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUIComponents();
    }

    @Override
    public void onResume() {
        super.onResume();
        registerForContextMenu(findViewById(android.R.id.content));
    }

    @Override
    public void onPause() {
        unregisterForContextMenu(findViewById(android.R.id.content));
        super.onPause();
    }

    private void initUIComponents() {
        mFragmentManager = getSupportFragmentManager();
        mInfoFragment = (Fragment2) mFragmentManager.findFragmentById(R.id.fragment2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.main_menu, menu);
        mMenu = menu;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.hasSubMenu()) {
            String text = item.getTitle().toString();
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        } else {
            boolean checked = item.isChecked();
            item.setChecked(!checked);
            boolean enabled = item.isEnabled();
            String text = String.format(getResources().getString(R.string.info), item.getTitle(), String.valueOf(enabled), String.valueOf(checked));
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    @Override
    public void onSelectItemInFragment(View view, int position) {
        String text = ((TextView) view).getText().toString();
        mInfoFragment.SetInfo(String.format(getString(R.string.chosen_item), position, text));
        switch (position)
        {
            case 0:
                MenuItem m = mMenu.findItem(R.id.none_submenu);
                boolean visible = !m.isVisible();
                m.setVisible(visible);
                mMenu.setGroupVisible(R.id.noncheckable_group, visible);
                m.getSubMenu().setGroupVisible(R.id.noncheckable_group, visible);
                break;
            case 1:
                m = mMenu.findItem(R.id.all_submenu);
                visible = !m.isVisible();
                m.setVisible(visible);
                m.getSubMenu().setGroupVisible(R.id.checkable_group, visible);
                break;
            case 2:
                m = mMenu.findItem(R.id.single_submenu);
                visible = !m.isVisible();
                m.setVisible(visible);
                m.getSubMenu().setGroupVisible(R.id.exclusive_checkable_group, visible);
                break;
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.main_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.context_1:
                DialogFragment dialog = new SimpleDialog();
                dialog.show(mFragmentManager, "SimpleDialog");
                return true;
            case R.id.context_2:
                dialog = new SingleChoiceDialog();
                Bundle bundle = new Bundle();
                bundle.putInt(SingleChoiceDialog.SELECTED, mPosition);
                dialog.setArguments(bundle);
                dialog.show(mFragmentManager, "SingleChoiceDialog");
                return true;
            case R.id.context_3:
                dialog = new MultiChoiceDialog();
                bundle = new Bundle();
                bundle.putBooleanArray(MultiChoiceDialog.SELECTED_ITEMS, checkedItems);
                dialog.setArguments(bundle);
                dialog.show(mFragmentManager, "MultiChoiceDialog");
                return true;

        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        mInfoFragment.SetInfo(getResources().getString(R.string.answer_yes));
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        mInfoFragment.SetInfo(getResources().getString(R.string.answer_no));
    }

    @Override
    public void onDialogOkClick(DialogFragment dialog, int selected) {
        mPosition = selected;
        String text = getString(R.string.chosen);
        text += String.format(getString(R.string.chosen_item), mPosition, getResources().getStringArray(R.array.items)[mPosition]);
        mInfoFragment.SetInfo(text);
    }

    @Override
    public void onDialogOkClick(DialogFragment dialog, boolean[] checkedItems) {
        this.checkedItems = checkedItems;
        String text = getString(R.string.chosen);
        for (int i = 0; i< checkedItems.length; i++) {
            if(checkedItems[i]) {
                text += String.format(getString(R.string.chosen_item), i, getResources().getStringArray(R.array.items)[i]) + '\n';
            }
        }
        mInfoFragment.SetInfo(text);
    }

    @Override
    public void onDialogCancelClick(DialogFragment dialog) {
        mInfoFragment.SetInfo(getString(R.string.canceled));
    }
}
