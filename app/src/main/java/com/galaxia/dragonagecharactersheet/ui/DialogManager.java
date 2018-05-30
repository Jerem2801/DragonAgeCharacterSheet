package com.galaxia.dragonagecharactersheet.ui;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.SupportActivity;
import android.support.v7.app.AlertDialog;
import android.text.Layout;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.galaxia.dragonagecharactersheet.R;
import com.galaxia.dragonagecharactersheet.controller.sheet.SheetAttribute;
import com.galaxia.dragonagecharactersheet.element.attribute.AttributeManager;

public class DialogManager {

    private DialogManager(){

    }

    public static Dialog createDialog(Context context, String title, String message){
        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        // 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage(message)
                .setTitle(title);

        // 3. Get the AlertDialog from create()
        AlertDialog dialog = builder.create();
        return dialog;
    }

    @SuppressLint("NewApi")
    public static Dialog createCustomDialog(Context context, LayoutInflater inflater, String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View inflate = inflater.inflate(R.layout.dialog_help, null);
        LinearLayout linear = (LinearLayout) inflate;

        TextView titleView =  new TextView(context);
        LinearLayout.LayoutParams titleViewParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, UiUtils.sizeInDp(context,35));
        titleView.setLayoutParams(titleViewParams);
        titleView.setTextColor(context.getResources().getColor(R.color.colorAccent));
        titleView.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        titleView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
        titleView.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
        titleView.setText(title);
        titleView.setGravity(Gravity.CENTER);
        linear.addView(titleView);

        TextView descView =  new TextView(context);
        LinearLayout.LayoutParams descViewParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        descViewParams.setMargins(UiUtils.sizeInDp(context,16),  UiUtils.sizeInDp(context,16), UiUtils.sizeInDp(context,16),UiUtils.sizeInDp(context,16));
        descView.setLayoutParams(descViewParams);
        message = message.substring(0, 1).toUpperCase() + message.substring(1);
        descView.setText(message);
        descView.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
        linear.addView(descView);


        builder.setView(inflate);
        AlertDialog dialog = builder.create();
        return dialog;
    }




}
