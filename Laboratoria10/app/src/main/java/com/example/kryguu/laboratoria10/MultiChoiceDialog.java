package com.example.kryguu.laboratoria10;

import android.support.v4.app.DialogFragment;

/**
 * Created by kryguu on 07.06.2017.
 */

public class MultiChoiceDialog extends DialogFragment {




    public interface MultiChoiceDialogListener {
        void onDialogOkClick(DialogFragment dialog, int selected);
        void onDialogCancelClick(DialogFragment dialog);
    }

}

