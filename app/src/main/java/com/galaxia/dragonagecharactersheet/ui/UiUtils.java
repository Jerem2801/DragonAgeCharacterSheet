package com.galaxia.dragonagecharactersheet.ui;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.galaxia.dragonagecharactersheet.R;
import com.galaxia.dragonagecharactersheet.element.race.Race;

import java.util.List;

public class UiUtils {


    private UiUtils(){

    }

    public static void setSpinnerRace(Context context, Spinner spinner, List<Race> races){
        ArrayAdapter<Race> adapter = new ArrayAdapter<>(context.getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, races);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}
