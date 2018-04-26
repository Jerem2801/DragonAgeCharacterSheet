package com.galaxia.dragonagecharactersheet.element.background.backgroundtable;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.galaxia.dragonagecharactersheet.R;
import com.galaxia.dragonagecharactersheet.controller.create.ChooseBackgroundActivity;
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
import com.galaxia.dragonagecharactersheet.ressource.RessourcePath;
import com.galaxia.dragonagecharactersheet.ressource.RessourceUtils;
import com.google.common.collect.Lists;

import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.List;

public class BackgroundTableUiManager {

    private Context context;
    private TableLayout bonusTable;
    private List<BackgroundTable> backgroundTables;
    private List<BackgroundTable> selectedBackgroudBonus;
    private TextView numberUpgrade;



    public List<BackgroundTable> getSelectedBackgroudBonus() {
        return selectedBackgroudBonus;
    }

    public BackgroundTableUiManager(Context context, TableLayout bonusTable, List<BackgroundTable> backgroundTables,TextView numberUpgrade) {
        this.context = context;
        this.bonusTable = bonusTable;
        this.backgroundTables = backgroundTables;
        this.selectedBackgroudBonus = Lists.newArrayList();
        this.numberUpgrade = numberUpgrade;
        initializeNumberUpgrade();
    }

    private void initializeNumberUpgrade() {
        Integer total = getTotal(selectedBackgroudBonus);
        int max = 3-total;
        String maxString = String.valueOf(max);
        numberUpgrade.setText("Amélioration : " + maxString);
    }

    public void setTableUi(){
        Collections.sort(backgroundTables);

        bonusTable.setStretchAllColumns(true);
        bonusTable.setShrinkAllColumns(true);

        createFirstLine();

        for(BackgroundTable backgroundTable : backgroundTables){
            TableRow tableRow = createRowValue(backgroundTable);
            bonusTable.addView(tableRow);
        }

    }


    @SuppressLint("NewApi")
    private void createFirstLine() {
        TableRow firstRow = new TableRow(context);
        TableLayout.LayoutParams rowLayoutFirst = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.MATCH_PARENT);
        firstRow.setLayoutParams(rowLayoutFirst);

        createModelText(firstRow,R.string.cost);
        createModelText(firstRow,R.string.avantage);

        firstRow.setBackgroundColor(context.getResources().getColor(R.color.colorPrimaryDark));

        bonusTable.addView(firstRow);
    }

    private void createModelText(TableRow tableRow,int textId){
        String text = context.getString(textId);
        TextView textView = new TextView(context);
        textView.setText(text);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
        textView.setTextColor(context.getResources().getColor(R.color.colorAccent));
        textView.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        textView.setGravity(Gravity.CENTER);
        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER_VERTICAL;
        textView.setLayoutParams(params);
        tableRow.addView(textView);
    }

    private TableRow createRowValue(BackgroundTable backgroundTable){
        TableRow tableRow = new TableRow(context);
        TableLayout.LayoutParams rowLayout = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.MATCH_PARENT);
        tableRow.setLayoutParams(rowLayout);

        TextView costView = new TextView(context);
        String costString = getCostString(backgroundTable);
        costView.setText(costString);
        costView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        costView.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        costView.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
        costView.setGravity(Gravity.CENTER);
        tableRow.addView(costView);

        TextView bonusView = new TextView(context);
        String bonusString = getBonusString(context,backgroundTable);
        bonusView.setText(bonusString);
        bonusView.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        bonusView.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
        bonusView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 11);
        bonusView.setGravity(Gravity.CENTER);
        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER_VERTICAL;
        bonusView.setLayoutParams(params);
        tableRow.addView(bonusView);


        if(isEven(backgroundTable.getOrder())){
            tableRow.setBackgroundColor(context.getResources().getColor(R.color.colorLight));
        }

        tableRow.setOnClickListener(new CustomClickListener(backgroundTable));

        return tableRow;
    }

    class CustomClickListener implements View.OnClickListener{

        private BackgroundTable backgroundTable;

        public CustomClickListener(BackgroundTable backgroundTable) {
            this.backgroundTable = backgroundTable;
        }

        @SuppressLint("NewApi")
        @Override
        public void onClick(View v) {
            if(selectedBackgroudBonus.contains(backgroundTable)){
                selectedBackgroudBonus.remove(backgroundTable);
                if(isEven(backgroundTable.getOrder())){
                    v.setBackgroundColor(context.getResources().getColor(R.color.colorLight));
                }else{
                    v.setBackground(null);
                }

                ((TableRow) v).removeViewAt(0);
                TextView costView = new TextView(context);
                String costString = getCostString(backgroundTable);
                costView.setText(costString);
                costView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
                costView.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
                costView.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
                costView.setGravity(Gravity.CENTER);
                ((TableRow) v).addView(costView,0);

            }else if(checkTotal(selectedBackgroudBonus,backgroundTable)){
                CharSequence text = context.getString(R.string.empty_avantage_point);
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }else{
                selectedBackgroudBonus.add(backgroundTable);
                v.setBackgroundColor(context.getResources().getColor(R.color.colorValid));

                ((TableRow) v).removeViewAt(0);
                ImageView valid = new ImageView(context);
                TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT);
                valid.setLayoutParams(params);
                Bitmap image = RessourceUtils.getImage(context, RessourcePath.DONE_PATH);
                valid.setImageBitmap(image);
                ((TableRow) v).addView(valid,0);
            }
            initializeNumberUpgrade();
        }

    }

    private boolean checkTotal(List<BackgroundTable> selectedBackgroudBonus,BackgroundTable newBackgroundTable) {
        int i = getCost(newBackgroundTable);
        i += getTotal(selectedBackgroudBonus);
        return i > 3;
    }

    private Integer getTotal(List<BackgroundTable> selectedBackgroudBonus){
        int i = 0;
        for(BackgroundTable selected : selectedBackgroudBonus){
            i += getCost(selected);
        }
        return i;
    }




    public String getBonusString(Context context,BackgroundTable backgroundTable) {
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

    private String getCostString(BackgroundTable backgroundTable) {
        int i = getCost(backgroundTable);
        return String.valueOf(i);
    }

    public Integer getCost(BackgroundTable backgroundTable) {
        int cost = 1;
        if(StringUtils.equals(backgroundTable.getType(),BackgroundBonusConstant.ATTRIBUTE)){
            cost = 2;
        }
        return cost;
    }

    private boolean isEven(double num){
        return ((num % 2) == 0);
    }
}
