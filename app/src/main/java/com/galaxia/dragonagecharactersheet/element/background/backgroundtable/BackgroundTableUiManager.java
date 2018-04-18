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
import com.galaxia.dragonagecharactersheet.element.attribute.AttributeManager;
import com.galaxia.dragonagecharactersheet.element.background.Background;
import com.galaxia.dragonagecharactersheet.element.background.backgroundtable.backgroundbonus.BackgroundBonusConstant;
import com.galaxia.dragonagecharactersheet.element.focus.Focus;
import com.galaxia.dragonagecharactersheet.element.focus.FocusManager;
import com.galaxia.dragonagecharactersheet.element.focus.FocusUiManager;
import com.galaxia.dragonagecharactersheet.element.language.Language;
import com.galaxia.dragonagecharactersheet.element.language.LanguageManager;
import com.galaxia.dragonagecharactersheet.element.weapongroup.WeaponGroup;
import com.galaxia.dragonagecharactersheet.element.weapongroup.WeaponGroupManager;

import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.List;

public class BackgroundTableUiManager {

    private BackgroundTableUiManager(){

    }

    public static void setTableUi(Context context, TableLayout bonusTable, Background background){
        List<BackgroundTable> bonusRoll = background.getBonusRoll();
        Collections.sort(bonusRoll);

        bonusTable.setStretchAllColumns(true);
        bonusTable.setShrinkAllColumns(true);

        createFirstLine(context,bonusTable);

        for(BackgroundTable backgroundTable : bonusRoll){
            TableRow tableRow = createRowValue(context,backgroundTable);
            bonusTable.addView(tableRow);
        }

    }

    private static TableRow createRowValue(Context context,BackgroundTable backgroundTable){
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
        String bonusString = getBonusString(context,backgroundTable);
        bonusView.setText(bonusString);
        bonusView.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        bonusView.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
        bonusView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 10);
        bonusView.setGravity(Gravity.CENTER);
        tableRow.addView(bonusView);


        if(isEven(backgroundTable.getOrder())){
            tableRow.setBackgroundColor(context.getResources().getColor(R.color.colorLight));
        }
        return tableRow;
    }

    private static void createFirstLine(Context context, TableLayout bonusTable) {
        TableRow firstRow = new TableRow(context);
        TableLayout.LayoutParams rowLayoutFirst = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.MATCH_PARENT);
        firstRow.setLayoutParams(rowLayoutFirst);

        TextView resultView = createModelText(context,R.string.result);
        firstRow.addView(resultView);

        TextView avantageView = createModelText(context,R.string.avantage);
        firstRow.addView(avantageView);

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



    public static String getBonusString(Context context,BackgroundTable backgroundTable) {
        String bonus = StringUtils.EMPTY;
        String type = backgroundTable.getType();
        String bonusId = backgroundTable.getBonus();

        switch(type){
            case BackgroundBonusConstant.ATTRIBUTE:
                Attribute attribute = AttributeManager.getAttribute(bonusId);
                bonus = attribute.getName() + " + 1";
                break;
            case BackgroundBonusConstant.FOCUS:
                String focusString = context.getString(R.string.focus);
                Focus focus = FocusManager.getFocus(bonusId);
                bonus = focusString + " " + FocusUiManager.getFocusWithAttribute(focus);
                break;
            case BackgroundBonusConstant.LANGUAGE:
                String languageString = context.getString(R.string.spoken_language);
                Language language = LanguageManager.getLanguage(bonusId);
                bonus = languageString + " " + language.getName();
                break;
            case BackgroundBonusConstant.WEAPON_GROUP:
                String weaponGroupString = context.getString(R.string.weapon_group) ;
                WeaponGroup weaponGroup = WeaponGroupManager.getWeaponGroup(bonusId);
                bonus = weaponGroupString + " " + weaponGroup.getName();
                break;
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
