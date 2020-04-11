package com.hfs.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class NumberPickerFragment extends AppCompatDialogFragment {
    NumberPicker.OnValueChangeListener valueChangeListener;
    NumberPicker.OnValueChangeListener valueChangeListener2;
    NumberPicker.OnValueChangeListener valueChangeListener3;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        //
        LinearLayout LL = new LinearLayout(getActivity());
        //
        final NumberPicker numberPickerHH = new NumberPicker(getActivity());
        final NumberPicker numberPickerMM = new NumberPicker(getActivity());
        final NumberPicker numberPickerSS = new NumberPicker(getActivity());


        numberPickerHH.setMinValue(0);
        numberPickerHH.setMaxValue(5);

        numberPickerMM.setMinValue(0);
        numberPickerMM.setMaxValue(59);

        numberPickerSS.setMinValue(0);
        numberPickerSS.setMaxValue(59);

        //
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(50, 50);
        params.gravity = Gravity.CENTER;
        //
        LinearLayout.LayoutParams hourPicerParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        hourPicerParams.weight = 1;
        //
        LinearLayout.LayoutParams minutePicerParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        minutePicerParams.weight = 1;
        //
        LinearLayout.LayoutParams secondPicerParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        secondPicerParams.weight = 1;
        //
        LL.setLayoutParams(params);
        LL.addView(numberPickerHH, hourPicerParams);
        LL.addView(numberPickerMM, minutePicerParams);
        LL.addView(numberPickerSS, secondPicerParams);
        //

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose a Duration");
        builder.setMessage("(Format = HH:MM:SS)");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                valueChangeListener.onValueChange(numberPickerHH, numberPickerMM.getValue(), numberPickerSS.getValue());
            }
        });

        builder.setView(LL);
        return builder.create();
    }

    public NumberPicker.OnValueChangeListener getValueChangeListener() {
        return valueChangeListener;
    }

    public void setValueChangeListener(NumberPicker.OnValueChangeListener valueChangeListener) {
        this.valueChangeListener = valueChangeListener;
    }
}
