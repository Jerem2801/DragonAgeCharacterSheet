package com.galaxia.dragonagecharactersheet.controller.create;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;

import com.galaxia.dragonagecharactersheet.R;
import com.galaxia.dragonagecharactersheet.data.DataPool;
import com.galaxia.dragonagecharactersheet.element.race.Race;
import com.galaxia.dragonagecharactersheet.ui.UiUtils;

import java.util.ArrayList;
import java.util.List;

public class ChooseRaceActivity extends AppCompatActivity {

    private Spinner spinnerRace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_race);

        spinnerRace = findViewById(R.id.choose_race_spinner);

        initializeSpinner();
    }

    private void initializeSpinner() {
        List<Race> races = new ArrayList(DataPool.getInstance(ChooseRaceActivity.this).getRaces().values());
        UiUtils.setSpinnerRace(ChooseRaceActivity.this,spinnerRace,races);
    }


}
