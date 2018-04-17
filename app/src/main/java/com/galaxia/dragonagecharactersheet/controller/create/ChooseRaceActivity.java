package com.galaxia.dragonagecharactersheet.controller.create;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.galaxia.dragonagecharactersheet.R;
import com.galaxia.dragonagecharactersheet.controller.ActivityConstant;
import com.galaxia.dragonagecharactersheet.data.DataPool;
import com.galaxia.dragonagecharactersheet.element.race.Race;
import com.galaxia.dragonagecharactersheet.element.race.RaceUiManager;
import com.galaxia.dragonagecharactersheet.player.Player;
import com.galaxia.dragonagecharactersheet.ressource.RessourceUtils;
import com.galaxia.dragonagecharactersheet.ui.UiUtils;
import com.galaxia.dragonagecharactersheet.ui.ViewFormaterString;

import java.util.ArrayList;
import java.util.List;

public class ChooseRaceActivity extends AppCompatActivity {

    private Spinner racesSpinner;
    private TextView raceDescriptionText;
    private TextView raceInitialSpeedText;
    private TextView raceClassesAvailableText;
    private ImageView raceImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_race);

        racesSpinner = findViewById(R.id.choose_race_spinner);
        raceDescriptionText = findViewById(R.id.choose_race_description_txt);
        raceInitialSpeedText = findViewById(R.id.choose_race_initial_speed_txt);
        raceClassesAvailableText = findViewById(R.id.choose_race_classes_available_txt);
        raceImage = findViewById(R.id.choose_race_image);

        initializeSpinner();
        onSelectedItem();
    }


    private void initializeSpinner() {
        List<Race> races = new ArrayList(DataPool.getInstance().getRaces().values());
        UiUtils.setSpinnerRace(ChooseRaceActivity.this, racesSpinner,races);
    }

    private void onSelectedItem() {
        racesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                Race race = (Race) racesSpinner.getItemAtPosition(position);

                raceDescriptionText.setText(ViewFormaterString.setLineSeparator(race.getDescription()));

                raceInitialSpeedText.setText(RaceUiManager.setSpeed(race,getString(R.string.speed_race)));

                raceClassesAvailableText.setText(RaceUiManager.getClasseAvailable(race));

                raceImage.setImageBitmap(RessourceUtils.getImage(ChooseRaceActivity.this,race.getImagePath()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }

    public void nextActivityChooseClasse(View view){
        Intent chooseClasseIntent = new Intent(ChooseRaceActivity.this, ChooseClasseActivity.class);
        Race race = (Race) racesSpinner.getSelectedItem();
        Player player = new Player();
        player.setRaceId(race.getId());
        chooseClasseIntent.putExtra(ActivityConstant.EXTRA_PLAYER,player);
        startActivity(chooseClasseIntent);
    }


}
