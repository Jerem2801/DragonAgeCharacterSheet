package com.galaxia.dragonagecharactersheet.controller.create;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.bluejamesbond.text.DocumentView;
import com.bluejamesbond.text.style.TextAlignment;
import com.galaxia.dragonagecharactersheet.R;
import com.galaxia.dragonagecharactersheet.controller.ActivityConstant;
import com.galaxia.dragonagecharactersheet.controller.create.manager.ChooseFocusManager;
import com.galaxia.dragonagecharactersheet.element.classe.Classe;
import com.galaxia.dragonagecharactersheet.element.classe.ClasseManager;
import com.galaxia.dragonagecharactersheet.element.classe.ClasseUiManager;
import com.galaxia.dragonagecharactersheet.element.focus.Focus;
import com.galaxia.dragonagecharactersheet.element.focus.FocusManager;
import com.galaxia.dragonagecharactersheet.element.race.Race;
import com.galaxia.dragonagecharactersheet.element.race.RaceManager;
import com.galaxia.dragonagecharactersheet.element.weapongroup.WeaponGroup;
import com.galaxia.dragonagecharactersheet.element.weapongroup.WeaponGroupManager;
import com.galaxia.dragonagecharactersheet.player.Player;
import com.galaxia.dragonagecharactersheet.ressource.RessourceUtils;
import com.galaxia.dragonagecharactersheet.ui.UiUtils;
import com.galaxia.dragonagecharactersheet.ui.ViewFormaterString;

import java.util.List;
import java.util.Map;

public class ChooseClasseActivity extends AppCompatActivity {

    private Spinner classesSpinner;
    private ImageView classeImage;
    private TextView descriptionText;
    private TextView healthText;
    private TextView weaponGroupList;
    private RadioGroup radioGroup;
    private Map<Integer,WeaponGroup> order;
    private TextView primaryAttributesList;
    private TextView secondaryAttributesList;
    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_classe);

        Intent intent = getIntent();
        player = intent.getParcelableExtra(ActivityConstant.EXTRA_PLAYER);
        Race race = RaceManager.getRace(player.getRaceId());

        classesSpinner = findViewById(R.id.choose_classe_spinner);
        classeImage = findViewById(R.id.choose_classe_image);

        descriptionText = findViewById(R.id.choose_classe_description_txt);

        healthText= findViewById(R.id.choose_classe_health_txt);

        weaponGroupList = findViewById(R.id.choose_classe_weapon_group_txt);

        radioGroup = findViewById(R.id.choose_classe_weapon_group_radio);

        primaryAttributesList = findViewById(R.id.choose_classe_primary_attributes_txt);

        secondaryAttributesList = findViewById(R.id.choose_classe_secondary_attributes_txt);

        initializeSpinner(race);
        onSelectedItem();
    }

    private void initializeSpinner(Race race) {
        List<String> classeAvailableIds = race.getClasseAvailableIds();
        List<Classe> classes = ClasseManager.getClasses(classeAvailableIds);
        UiUtils.setSpinnerClasse(ChooseClasseActivity.this, classesSpinner,classes);
    }

    private void onSelectedItem() {
        classesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Classe classe= (Classe) classesSpinner.getItemAtPosition(position);

                classeImage.setImageBitmap(RessourceUtils.getImage(ChooseClasseActivity.this,classe.getImagePath()));

                descriptionText.setText(ViewFormaterString.setLineSeparator(classe.getDescription()));

                healthText.setText(ClasseUiManager.setHeatlh(classe,getString(R.string.health_classe)));

                weaponGroupList.setText(ClasseUiManager.getWeaponGroupStarting(classe));

                if(classe.getNumberOfWeaponGroup() != 0){
                    radioGroup.setVisibility(View.VISIBLE);
                    List<WeaponGroup> weaponGroups = WeaponGroupManager.getWeaponGroups(classe.getWeaponGroupToChoose());
                    order = ChooseWeaponGroupManager.initializeChoice(ChooseClasseActivity.this,weaponGroups,radioGroup);
                }else{
                    radioGroup.setVisibility(View.GONE);
                }

                primaryAttributesList.setText(ClasseUiManager.getAttributes(classe.getPrimaryAttributesId()));

                secondaryAttributesList.setText(ClasseUiManager.getAttributes(classe.getSecondaryAttributesId()));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }

    public void chooseBackgroundActivity(View view){
        Intent classeActivity = new Intent(ChooseClasseActivity.this, ChooseBackgroundActivity.class);
        Classe Classe = (Classe) classesSpinner.getSelectedItem();
        player.setClasseId(Classe.getId());
        classeActivity.putExtra(ActivityConstant.EXTRA_PLAYER,player);
        startActivity(classeActivity);
    }
}
