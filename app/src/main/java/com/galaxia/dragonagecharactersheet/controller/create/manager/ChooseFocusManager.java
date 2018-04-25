package com.galaxia.dragonagecharactersheet.controller.create.manager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.widget.RadioGroup;

import com.galaxia.dragonagecharactersheet.R;
import com.galaxia.dragonagecharactersheet.element.focus.Focus;
import com.galaxia.dragonagecharactersheet.element.focus.FocusUiManager;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

public class ChooseFocusManager {

    @SuppressLint("RestrictedApi")
    public static Map<Integer,Focus> initializeChoice(Context context, List<Focus> focuss, RadioGroup radioGroup) {
        radioGroup.removeAllViews();
        radioGroup.clearCheck();
        int id = 0;
        Map<Integer,Focus> order = Maps.newHashMap();
        for(Focus focus : focuss){
            order.put(id,focus);
            android.support.v7.widget.AppCompatRadioButton radioButton = new android.support.v7.widget.AppCompatRadioButton(context);
            radioButton.setId(id++);
            radioButton.setText(FocusUiManager.getFocusWithAttribute(focus));
            radioButton.setTextColor(context.getResources().getColor(R.color.colorPrimary));
            radioButton.setTextSize(TypedValue.COMPLEX_UNIT_DIP,16);
            radioButton.setTypeface(Typeface.DEFAULT, Typeface.ITALIC);
            radioButton.setSupportButtonTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.colorPrimaryDark)));
            RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(128, 8, 8, 8);
            radioButton.setLayoutParams(params);
            radioGroup.addView(radioButton);
        }
        return order;
    }

    public static boolean checkNotFocusSelected(RadioGroup radioGroup){
        return radioGroup.getCheckedRadioButtonId() == -1;
    }

}
