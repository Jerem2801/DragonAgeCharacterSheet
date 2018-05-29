package com.galaxia.dragonagecharactersheet.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.galaxia.dragonagecharactersheet.R;
import com.galaxia.dragonagecharactersheet.controller.create.AssignAttributeActivity;

public class UiViewUtils {

    private UiViewUtils(){

    }

    public static void setTextViewTitle(TextView textView){
        Context context = textView.getContext();
        textView.setTextColor(context.getResources().getColor(R.color.colorPrimary));
        textView.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
    }

    public static void setAttributeName(TextView textView){
        Context context = textView.getContext();
        textView.setTextColor(context.getResources().getColor(R.color.colorAccent));
        textView.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
        textView.setBackgroundColor(context.getResources().getColor(R.color.colorPrimaryDark));
    }

    public static void setValue(TextView textView){
        Context context = textView.getContext();
        textView.setTextColor(context.getResources().getColor(R.color.colorPrimary));
        textView.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30);
    }

    @SuppressLint("NewApi")
    public static LinearLayout createLine(Context context){
        LinearLayout line = new LinearLayout(context);
        LinearLayout.LayoutParams lineParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        line.setLayoutParams(lineParams);
        line.setBackground(context.getResources().getDrawable(R.drawable.custom_line));
        return line;
    }

    private static Integer sizeInDp(Context context, int sizeInDp){
        float v = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, sizeInDp, context.getResources().getDisplayMetrics());
        return (Integer) Math.round(v);
    }
}
