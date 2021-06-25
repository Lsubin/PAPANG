package com.papang.perfume.custom;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.papang.perfume.JoinActivity;
import com.papang.perfume.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetDialog extends BottomSheetDialogFragment {

    NumberPicker gender_spinner;
    Button gender_check;

    String[] values = {"선택안함", "남자", "여자"};
    public static String gender = "선택안함";

    public static BottomSheetDialog getInstance(){
        return new BottomSheetDialog();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.custom_spinner, container, false);
        gender_spinner = (NumberPicker)view.findViewById(R.id.gender_spinner);
        gender_spinner.setMaxValue(values.length - 1);
        gender_spinner.setMinValue(0);
        gender_spinner.setDisplayedValues(values);
        ((JoinActivity)JoinActivity.mContext).setGender(gender);
        gender_check = (Button)view.findViewById(R.id.gender_check);

        gender_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("값", gender);
                ((JoinActivity)JoinActivity.mContext).setGender(gender);
                dismiss();
            }
        });

        gender_spinner.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                gender = values[newVal];
            }
        });
        return view;
    }


}
