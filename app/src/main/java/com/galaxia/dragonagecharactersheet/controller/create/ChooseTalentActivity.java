package com.galaxia.dragonagecharactersheet.controller.create;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.galaxia.dragonagecharactersheet.R;
import com.galaxia.dragonagecharactersheet.controller.ActivityConstant;
import com.galaxia.dragonagecharactersheet.data.DataPool;
import com.galaxia.dragonagecharactersheet.element.classe.Classe;
import com.galaxia.dragonagecharactersheet.element.classe.ClasseManager;
import com.galaxia.dragonagecharactersheet.element.classe.ClasseUiManager;
import com.galaxia.dragonagecharactersheet.element.race.Race;
import com.galaxia.dragonagecharactersheet.element.race.RaceManager;
import com.galaxia.dragonagecharactersheet.element.talent.Talent;
import com.galaxia.dragonagecharactersheet.player.Player;
import com.galaxia.dragonagecharactersheet.ressource.RessourceUtils;
import com.galaxia.dragonagecharactersheet.ui.UiUtils;
import com.galaxia.dragonagecharactersheet.ui.ViewFormaterString;

import java.util.List;
import java.util.Map;

public class ChooseTalentActivity extends AppCompatActivity {

    private Player player;

    private Spinner talentSpinner;
    private TextView descriptionText;
    private TextView noviceText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_talent);

        Intent intent = getIntent();
        player = intent.getParcelableExtra(ActivityConstant.EXTRA_PLAYER);


        DataPool instance = DataPool.getInstance();
        Map<String, Talent> talents = instance.getTalents();

        talentSpinner = findViewById(R.id.choose_talent_spinner);
        descriptionText = findViewById(R.id.choose_talent_description_txt);
        noviceText = findViewById(R.id.choose_talent_novice_txt);

        initializeSpinner(talents);
        onSelectedItem();
    }


    private void initializeSpinner(List<Talent> talents) {
        UiUtils.setSpinnerTalent(ChooseTalentActivity.this, talentSpinner,talents);
    }

    private void onSelectedItem() {
        talentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Talent talent= (Talent) talentSpinner.getItemAtPosition(position);

                descriptionText.setText(talent.getDescription());
                noviceText.setText(talent.getNovice());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }





}
