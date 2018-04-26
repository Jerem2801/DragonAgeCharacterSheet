package com.galaxia.dragonagecharactersheet.ui;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.Spinner;

import com.galaxia.dragonagecharactersheet.R;
import com.galaxia.dragonagecharactersheet.controller.create.ChooseBackgroundActivity;
import com.galaxia.dragonagecharactersheet.controller.create.ChooseClasseActivity;
import com.galaxia.dragonagecharactersheet.element.background.Background;
import com.galaxia.dragonagecharactersheet.element.classe.Classe;
import com.galaxia.dragonagecharactersheet.element.race.Race;
import com.galaxia.dragonagecharactersheet.element.talent.Talent;

import java.util.List;

public class UiUtils {


    private UiUtils(){

    }

    public static void setSpinnerRace(Context context, Spinner spinner, List<Race> races){
        ArrayAdapter<Race> adapter = new ArrayAdapter<>(context.getApplicationContext(), R.layout.custom_spinner, races);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public static void setSpinnerClasse(Context context, Spinner spinner, List<Classe> classes) {
        ArrayAdapter<Classe> adapter = new ArrayAdapter<>(context.getApplicationContext(), R.layout.custom_spinner, classes);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public static void setSpinnerBackground(Context context, Spinner spinner, List<Background> backgrounds) {
        ArrayAdapter<Background> adapter = new ArrayAdapter<>(context.getApplicationContext(), R.layout.custom_spinner, backgrounds);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public static void setSpinnerTalent(Context context, Spinner spinner, List<Talent> talents) {
        ArrayAdapter<Talent> adapter = new ArrayAdapter<>(context.getApplicationContext(), R.layout.custom_spinner, taelnts);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public static ListAdapter getListAdapter(Context context, List<String> attributeRolls) {
        ListAdapter adapter = new ArrayAdapter<>(context.getApplicationContext(), android.R.layout.simple_list_item_1, attributeRolls);
        return adapter;
    }
}
