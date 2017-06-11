package com.example.kryguu.laboratoria10;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

/**
 * Created by kryguu on 07.06.2017.
 */

public class MultiChoiceDialog extends DialogFragment {

    public static final String SELECTED_ITEMS = "SelectedItems";
    private boolean[] checkedItems;
    private MultiChoiceDialog.MultiChoiceDialogListener mListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        Bundle bundle = getArguments();
        checkedItems = bundle.getBooleanArray(SELECTED_ITEMS);
        if(checkedItems == null) {
            checkedItems = new boolean[getResources().getStringArray(R.array.items).length];
        }
        checkedItems = checkedItems.clone();
        builder.setTitle(R.string.dialog3);
        builder.setMultiChoiceItems(R.array.items, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                checkedItems[i] = b;
            }
        });
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                mListener.onDialogOkClick(MultiChoiceDialog.this, checkedItems);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                mListener.onDialogCancelClick(MultiChoiceDialog.this);
                dialog.dismiss();
            }
        });
        return builder.create();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (MultiChoiceDialog.MultiChoiceDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement MultipleChoiceDialogInterface");
        }
    }

    public interface MultiChoiceDialogListener {
        void onDialogOkClick(android.support.v4.app.DialogFragment dialog, boolean[] checkedItems);

        void onDialogCancelClick(android.support.v4.app.DialogFragment dialog);
    }

}

