package com.galaxia.dragonagecharactersheet.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.galaxia.dragonagecharactersheet.R;

public class TableUiManager {

    private TableUiManager(){

    }

    public static void createFirstLine(Context context, TableLayout bonusTable,int... columnNames) {
        TableRow firstRow = new TableRow(context);
        TableLayout.LayoutParams rowLayoutFirst = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.MATCH_PARENT);
        firstRow.setLayoutParams(rowLayoutFirst);

        for(int columnName : columnNames){
            TextView resultView = createModelText(context, columnName);
            firstRow.addView(resultView);
        }

        firstRow.setBackgroundColor(context.getResources().getColor(R.color.colorPrimaryDark));

        bonusTable.addView(firstRow);
    }

    private static TextView createModelText(Context context,int textId){
        String text = context.getString(textId);
        TextView textView = new TextView(context);
        textView.setText(text);
        textView.setTextColor(context.getResources().getColor(R.color.colorAccent));
        textView.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }

    public static TableRow createRowValue(Context context,int rowNumber,String... values){
        TableRow tableRow = new TableRow(context);
        TableLayout.LayoutParams rowLayout = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.MATCH_PARENT);
        tableRow.setLayoutParams(rowLayout);

        for(String value : values){
            TextView rollsView = new TextView(context);
            String rollsString = value;
            rollsView.setText(rollsString);
            rollsView.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
            rollsView.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            rollsView.setGravity(Gravity.CENTER);
            tableRow.addView(rollsView);
        }

        if(isEven(rowNumber)){
            tableRow.setBackgroundColor(context.getResources().getColor(R.color.colorLight));
        }
        return tableRow;
    }

    private static boolean isEven(double num){
        return ((num % 2) == 0);
    }
}
