package com.galaxia.dragonagecharactersheet.controller.create;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.galaxia.dragonagecharactersheet.R;
import com.galaxia.dragonagecharactersheet.controller.ActivityConstant;
import com.galaxia.dragonagecharactersheet.element.attribute.AttributeManager;
import com.galaxia.dragonagecharactersheet.element.background.BackgroundManager;
import com.galaxia.dragonagecharactersheet.element.classe.ClasseManager;
import com.galaxia.dragonagecharactersheet.element.focus.FocusManager;
import com.galaxia.dragonagecharactersheet.element.language.LanguageUiManager;
import com.galaxia.dragonagecharactersheet.element.race.RaceManager;
import com.galaxia.dragonagecharactersheet.element.weapongroup.WeaponGroup;
import com.galaxia.dragonagecharactersheet.element.weapongroup.WeaponGroupUiManager;
import com.galaxia.dragonagecharactersheet.player.Player;
import com.google.common.collect.Maps;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

public class ResumeActivity extends AppCompatActivity {

    private Player player;

    private LinearLayout mother;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume);

        Intent intent = getIntent();
        player = intent.getParcelableExtra(ActivityConstant.EXTRA_PLAYER);
        String raceId = player.getRaceId();
        String raceName = RaceManager.getRace(raceId).getName();
        String classeId = player.getClasseId();
        String classeName = ClasseManager.getClasse(classeId).getName();
        String backgroundId = player.getBackgroundId();
        String backgroundName = BackgroundManager.getBackground(backgroundId).getName();
        List<String> focusIds = player.getFocusIds();
        String focusNames = FocusManager.getFocusNames(focusIds);
        List<String> spokenLanguageIds = player.getSpokenLanguages();
        String spokenLanguageNames = LanguageUiManager.getLanguagesName(ResumeActivity.this,spokenLanguageIds);
        List<String> weaponGroupIds = player.getWeaponGroupIds();
        String weaponGroupNames = WeaponGroupUiManager.getWeaponGroupName(weaponGroupIds);
        Map<String,Integer> attributeIdsAndValue = player.getAttribute();
        Map<String,String> attributeNameWithValue = getAttribute(attributeIdsAndValue);



        mother = findViewById(R.id.mother);

        createNewTextView(ResumeActivity.this,"Race : ",raceName);
        createNewTextView(ResumeActivity.this,"Classe : ",classeName);
        createNewTextView(ResumeActivity.this,"Race : ",backgroundName);
        createNewTextView(ResumeActivity.this,"Focus : ",focusNames);
        createNewTextView(ResumeActivity.this,"Langues Parlées : ",spokenLanguageNames);
        createNewTextView(ResumeActivity.this,"Groupe d'Arme : ",weaponGroupNames);
        for(Map.Entry<String,String> entry : attributeNameWithValue.entrySet()){
            String attributeName = entry.getKey();
            String value = entry.getValue();
            createNewTextView(ResumeActivity.this,attributeName + " : ",value);
        }
        

    }

    private Map<String, String> getAttribute(Map<String, Integer> attributeIdsAndValue) {
        Map<String,String> attributeNameWithValue = Maps.newHashMap();
        for(Map.Entry<String, Integer> entry : attributeIdsAndValue.entrySet()){
            String attributeId = entry.getKey();
            Integer valueInt = entry.getValue();

            String attributeName = AttributeManager.getAttribute(attributeId).getName();
            String valueString = String.valueOf(valueInt);

            attributeNameWithValue.put(attributeName,valueString);
        }
        return attributeNameWithValue;
    }

    private void createNewTextView(Context context, String name, String value) {
        if(StringUtils.isNotBlank(value)) {
            TextView textView = new TextView(context);
            String text = name + value;
            textView.setText(text);
            textView.setTextColor(getResources().getColor(R.color.colorPrimary));
            textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
            textView.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(16, 16, 16, 16);
            textView.setLayoutParams(params);
            mother.addView(textView);
        }
    }




}
