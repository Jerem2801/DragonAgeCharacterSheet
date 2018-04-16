package com.galaxia.dragonagecharactersheet.element.background.backgroundtable;

import android.content.Context;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.galaxia.dragonagecharactersheet.R;
import com.galaxia.dragonagecharactersheet.element.attribute.Attribute;
import com.galaxia.dragonagecharactersheet.element.background.Background;
import com.galaxia.dragonagecharactersheet.element.background.backgroundtable.backgroundbonus.BackgroundBonusConstant;
import com.galaxia.dragonagecharactersheet.element.focus.Focus;
import com.galaxia.dragonagecharactersheet.element.focus.FocusUiManager;
import com.galaxia.dragonagecharactersheet.element.language.Language;
import com.galaxia.dragonagecharactersheet.element.weapongroup.WeaponGroup;

import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.List;

public class BackgroundTableUiManager {

    private BackgroundTableUiManager(){

    }

    public static void setTableUi(Context context, TableLayout tableLayout, Background background){
        List<BackgroundTable> bonusRoll = background.getBonusRoll();
        Collections.sort(bonusRoll);

        tableLayout.setStretchAllColumns(true);
        tableLayout.setShrinkAllColumns(true);

        TableRow row = new TableRow(context);
        TableLayout.LayoutParams rowLayoutFirst = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.MATCH_PARENT);
        row.setLayoutParams(rowLayoutFirst);

        TextView resultView = new TextView(context);
        String result = "Résultat";
        resultView.setText(result);
        resultView.setTextColor(context.getResources().getColor(R.color.colorAccent));
        resultView.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        resultView.setGravity(Gravity.CENTER);
        row.addView(resultView);

        TextView avantageView = new TextView(context);
        String avantage = "Avantage";
        avantageView.setText(avantage);
        avantageView.setTextColor(context.getResources().getColor(R.color.colorAccent));
        avantageView.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        avantageView.setGravity(Gravity.CENTER);
        row.addView(avantageView);

        row.setBackgroundColor(context.getResources().getColor(R.color.colorPrimaryDark));

        tableLayout.addView(row);

        for(BackgroundTable backgroundTable : bonusRoll){
            TableRow tableRow = new TableRow(context);
            TableLayout.LayoutParams rowLayout = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.MATCH_PARENT);
            tableRow.setLayoutParams(rowLayout);

            TextView rollsView = new TextView(context);
            String rollsString = getRollsString(backgroundTable);
            rollsView.setText(rollsString);
            rollsView.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
            rollsView.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            rollsView.setGravity(Gravity.CENTER);
            tableRow.addView(rollsView);

            TextView bonusView = new TextView(context);
            String bonusString = getBonusString(backgroundTable);
            bonusView.setText(bonusString);
            bonusView.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            bonusView.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
            bonusView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 10);
            bonusView.setGravity(Gravity.CENTER);
            tableRow.addView(bonusView);


            if(isEven(backgroundTable.getOrder())){
                tableRow.setBackgroundColor(context.getResources().getColor(R.color.colorLight));
            }

            tableLayout.addView(tableRow);
        }


    }

    private static String getBonusString(BackgroundTable backgroundTable) {
        String bonus = StringUtils.EMPTY;
        if(StringUtils.equals(backgroundTable.getType() ,BackgroundBonusConstant.ATTRIBUTE)){
            Attribute attribute = (Attribute) backgroundTable.getBonus();
            bonus = attribute.getName() + " + 1";
        }else if(StringUtils.equals(backgroundTable.getType() ,BackgroundBonusConstant.FOCUS)){
            Focus focus = (Focus) backgroundTable.getBonus();
            bonus = "Compétence : " + FocusUiManager.getFocusWithAttribute(focus);
        }else if(StringUtils.equals(backgroundTable.getType() ,BackgroundBonusConstant.LANGUAGE)){
            Language language = (Language) backgroundTable.getBonus();
            bonus = "Langue Parlée : " + language.getName();
        }else if(StringUtils.equals(backgroundTable.getType() ,BackgroundBonusConstant.WEAPON_GROUP)){
            WeaponGroup weaponGroup = (WeaponGroup) backgroundTable.getBonus();
            bonus = "Groupe d'Arme : " + weaponGroup.getName();
        }

        return bonus;
    }

    private static String getRollsString(BackgroundTable backgroundTable) {
        StringBuilder builder = new StringBuilder();
        for(Integer i : backgroundTable.getRoll()){
            if(StringUtils.isBlank(builder.toString())){
                builder.append(i);
            }else{
                builder.append("-" + i);
            }
        }
        return builder.toString().trim();
    }

    private static boolean isEven(double num){
        return ((num % 2) == 0);
    }
}
