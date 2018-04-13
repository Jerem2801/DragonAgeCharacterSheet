package com.galaxia.dragonagecharactersheet.controller.create;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bluejamesbond.text.DocumentView;
import com.bluejamesbond.text.style.TextAlignment;
import com.galaxia.dragonagecharactersheet.R;
import com.galaxia.dragonagecharactersheet.data.DataPool;
import com.galaxia.dragonagecharactersheet.element.race.Race;
import com.galaxia.dragonagecharactersheet.element.race.RaceUiManager;
import com.galaxia.dragonagecharactersheet.ressource.RessourceUtils;
import com.galaxia.dragonagecharactersheet.ui.UiUtils;
import com.galaxia.dragonagecharactersheet.ui.ViewFormaterString;

import java.util.ArrayList;
import java.util.List;

public class ChooseRaceActivity extends AppCompatActivity {

    private Spinner racesSpinner;
    private DocumentView descriptionText;
    private TextView speedText;
    private TextView classeAvailableList;
    private ImageView raceImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_race);

        racesSpinner = findViewById(R.id.choose_race_spinner);
        descriptionText = findViewById(R.id.choose_race_description_txt);
        speedText = findViewById(R.id.choose_race_speed_txt);
        classeAvailableList = findViewById(R.id.choose_race_classe_available_txt);
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

                descriptionText.setText(ViewFormaterString.setLineSeparator(race.getDescription()));
                descriptionText.getDocumentLayoutParams().setTextAlignment(TextAlignment.JUSTIFIED);
                speedText.setText(RaceUiManager.setInitialSpeed(race));
                classeAvailableList.setText(RaceUiManager.setClasseAvailable(race));
                raceImage.setImageBitmap(RessourceUtils.getImage(ChooseRaceActivity.this,race.getImagePath()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }


}
