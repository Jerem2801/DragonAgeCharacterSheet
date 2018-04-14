package com.galaxia.dragonagecharactersheet.controller.create;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bluejamesbond.text.DocumentView;
import com.bluejamesbond.text.style.TextAlignment;
import com.galaxia.dragonagecharactersheet.R;
import com.galaxia.dragonagecharactersheet.controller.ActivityConstant;
import com.galaxia.dragonagecharactersheet.element.classe.Classe;
import com.galaxia.dragonagecharactersheet.element.classe.ClasseUiManager;
import com.galaxia.dragonagecharactersheet.element.race.Race;
import com.galaxia.dragonagecharactersheet.element.race.RaceUiManager;
import com.galaxia.dragonagecharactersheet.player.Player;
import com.galaxia.dragonagecharactersheet.ressource.RessourceUtils;
import com.galaxia.dragonagecharactersheet.ui.UiUtils;
import com.galaxia.dragonagecharactersheet.ui.ViewFormaterString;

import java.util.List;

public class ChooseClasseActivity extends AppCompatActivity {

    private Spinner classesSpinner;
    private DocumentView descriptionText;
    private TextView healthText;
    private TextView weaponGroupList;
    private ImageView classeImage;
    private TextView primaryAttributesList;
    private TextView secondaryAttributesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_classe);

        Intent intent = getIntent();
        Player player = intent.getParcelableExtra(ActivityConstant.EXTRA_PLAYER);
        Race race = player.getRace();

        classesSpinner = findViewById(R.id.choose_classe_spinner);
        descriptionText = findViewById(R.id.choose_classe_description_txt);
        healthText= findViewById(R.id.choose_classe_health_txt);
        weaponGroupList = findViewById(R.id.choose_classe_weapon_group_txt);
        classeImage = findViewById(R.id.choose_classe_image);
        primaryAttributesList = findViewById(R.id.choose_classe_primary_attributes_txt);
        secondaryAttributesList = findViewById(R.id.choose_classe_secondary_attributes_txt);

        initializeSpinner(race);
        onSelectedItem();
    }

    private void initializeSpinner(Race race) {
        List<Classe> classes = race.getClasseAvailable();
        UiUtils.setSpinnerClasse(ChooseClasseActivity.this, classesSpinner,classes);
    }

    private void onSelectedItem() {
        classesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                Classe classe= (Classe) classesSpinner.getItemAtPosition(position);

                descriptionText.setText(ViewFormaterString.setLineSeparator(classe.getDescription()));
                descriptionText.getDocumentLayoutParams().setTextAlignment(TextAlignment.JUSTIFIED);

                healthText.setText(String.valueOf(classe.getInitialHealth()));
                weaponGroupList.setText(ClasseUiManager.setWeaponGroup(classe));
                classeImage.setImageBitmap(RessourceUtils.getImage(ChooseClasseActivity.this,classe.getImagePath()));

                primaryAttributesList.setText(ClasseUiManager.setAttributeList(classe.getPrimaryAttributes()));
                secondaryAttributesList.setText(ClasseUiManager.setAttributeList(classe.getSecondaryAttributes()));


            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }
}
