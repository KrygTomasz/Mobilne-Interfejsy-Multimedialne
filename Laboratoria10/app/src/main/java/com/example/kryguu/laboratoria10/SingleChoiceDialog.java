package com.example.kryguu.laboratoria10;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;


public class SingleChoiceDialog extends DialogFragment {

    public static final String SELECTED = "SelectedItem";
    private int mPosition;
    private SingleChoiceDialogListener mListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        Bundle bundle = getArguments();
        mPosition = bundle.getInt(SELECTED);
        builder.setTitle(R.string.dialog2);
        builder.setSingleChoiceItems(R.array.items, mPosition, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mPosition = which;
            }
        });
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                mListener.onDialogOkClick(SingleChoiceDialog.this, mPosition);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                mListener.onDialogCancelClick(SingleChoiceDialog.this);
                dialog.dismiss();
            }
        });
        return builder.create();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (SingleChoiceDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement SingleChoiceDialogListener");
        }
    }

    public interface SingleChoiceDialogListener {
        void onDialogOkClick(DialogFragment dialog, int selected);
        void onDialogCancelClick(DialogFragment dialog);
    }
}
