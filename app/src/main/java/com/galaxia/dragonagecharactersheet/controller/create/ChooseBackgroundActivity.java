package com.galaxia.dragonagecharactersheet.controller.create;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.galaxia.dragonagecharactersheet.R;
import com.galaxia.dragonagecharactersheet.controller.ActivityConstant;
import com.galaxia.dragonagecharactersheet.controller.create.manager.ChooseBenefitManager;
import com.galaxia.dragonagecharactersheet.controller.create.manager.ChooseFocusManager;
import com.galaxia.dragonagecharactersheet.data.DataPool;
import com.galaxia.dragonagecharactersheet.element.attribute.Attribute;
import com.galaxia.dragonagecharactersheet.element.attribute.AttributeManager;
import com.galaxia.dragonagecharactersheet.element.background.Background;
import com.galaxia.dragonagecharactersheet.element.background.BackgroundManager;
import com.galaxia.dragonagecharactersheet.element.background.backgroundtable.BackgroundTable;
import com.galaxia.dragonagecharactersheet.element.background.backgroundtable.BackgroundTableManager;
import com.galaxia.dragonagecharactersheet.element.background.backgroundtable.BackgroundTableUiManager;
import com.galaxia.dragonagecharactersheet.element.focus.Focus;
import com.galaxia.dragonagecharactersheet.element.focus.FocusManager;
import com.galaxia.dragonagecharactersheet.element.language.Language;
import com.galaxia.dragonagecharactersheet.element.language.LanguageManager;
import com.galaxia.dragonagecharactersheet.element.language.LanguageUiManager;
import com.galaxia.dragonagecharactersheet.player.Player;
import com.galaxia.dragonagecharactersheet.player.PlayerManager;
import com.galaxia.dragonagecharactersheet.ressource.RessourceUtils;
import com.galaxia.dragonagecharactersheet.ui.UiUtils;
import com.galaxia.dragonagecharactersheet.ui.ViewFormaterString;
import com.google.common.collect.Lists;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChooseBackgroundActivity extends AppCompatActivity {

    private Player player;

    private Spinner backgroundsSpinner;
    private ImageView backgroundImage;
    private TextView description;
    private TextView attributBonusExplain;
    private TextView attributBonus;
    private RadioGroup radioGroup;
    private Map<Integer,Focus> order;
    private TextView spokenLanguages;
    private TextView writenLanguages;
    private TextView numberUpgrade;
    private TableLayout tableBonus;
    private BackgroundTableUiManager uiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_background);


        Intent intent = getIntent();
        player = intent.getParcelableExtra(ActivityConstant.EXTRA_PLAYER);
        String raceId = player.getRaceId();
        String classeId = player.getClasseId();

        backgroundsSpinner = findViewById(R.id.choose_background_spinner);
        backgroundImage = findViewById(R.id.choose_background_image);
        description = findViewById(R.id.choose_background_description_txt);
        attributBonusExplain = findViewById(R.id.choose_background_attribute_bonus_explain_txt);
        attributBonus = findViewById(R.id.choose_background_attribute_bonus_txt);
        radioGroup = findViewById(R.id.choose_focus_radio_group);
        spokenLanguages = findViewById(R.id.choose_background_spoken_languages_txt);
        writenLanguages = findViewById(R.id.choose_background_writen_languages_txt);
        numberUpgrade = findViewById(R.id.choose_background_avantage_number);
        tableBonus = findViewById(R.id.choose_background_avantage_table);

        initializeSpinner(raceId,classeId);
        onSelectedItem();
    }



    private void initializeSpinner(String raceId,String classeId) {
        DataPool dataPool = DataPool.getInstance();
        Map<String, Background> backgrounds = dataPool.getBackgrounds();
        List<Background> backgroundsList = BackgroundManager.getBackgroundAvailable(backgrounds,raceId,classeId);
        UiUtils.setSpinnerBackground(ChooseBackgroundActivity.this, backgroundsSpinner,backgroundsList);
    }

    private void onSelectedItem() {
        backgroundsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Background background= (Background) backgroundsSpinner.getItemAtPosition(position);

                if(!TextUtils.isEmpty(background.getImagePath())) {
                    backgroundImage.setImageBitmap(RessourceUtils.getImage(ChooseBackgroundActivity.this, background.getImagePath()));
                }else{
                    backgroundImage.setImageBitmap(null);
                }


                description.setText(ViewFormaterString.setLineSeparator(background.getDescription()));

                String increaseAttributeDesc = background.getIncreaseAttributeDesc();
                attributBonusExplain.setText(ViewFormaterString.setLineSeparator(increaseAttributeDesc));

                Attribute increaseAttribute = AttributeManager.getAttribute(background.getIncreaseAttributeId());
                String increaseAttributePhrase = getString(R.string.attribute_bonus_explain);
                String finalIncreaseAttribute = String.format(increaseAttributePhrase, increaseAttribute.getName());
                attributBonus.setText(finalIncreaseAttribute);

                List<Focus> focus = FocusManager.getFocus(background.getChooseFocusId());
                order = ChooseFocusManager.initializeChoice(ChooseBackgroundActivity.this,focus,radioGroup);

                List<Language> spokenLanguagesList = LanguageManager.getLanguage(background.getSpokenLanguageId());
                String spokenLanguagesString = LanguageUiManager.getLanguages(ChooseBackgroundActivity.this,spokenLanguagesList);
                spokenLanguages.setText(spokenLanguagesString);

                List<Language> writenLanguagesList = LanguageManager.getLanguage(background.getWritenLanguageId());
                String writenLanguagesString = LanguageUiManager.getLanguages(ChooseBackgroundActivity.this,writenLanguagesList);
                writenLanguages.setText(writenLanguagesString);

                List<BackgroundTable> backgroundTables = background.getBonusRoll();
                uiManager = ChooseBenefitManager.initializeBenefit(ChooseBackgroundActivity.this,backgroundTables,tableBonus,numberUpgrade);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }



    public void assigneAttributeActivity(View view){
        Intent assignAttributeActivity = new Intent(ChooseBackgroundActivity.this, AssignAttributeActivity.class);
        CharSequence msgError = StringUtils.EMPTY;
        if(ChooseFocusManager.checkNotFocusSelected(radioGroup)){
            msgError = msgError + getString(R.string.choose_a_focus);
        }
        if(ChooseBenefitManager.checkNotBenefitSelected(uiManager)){
            if(StringUtils.isNotBlank(msgError)){
                msgError = msgError + "\n";
            }
            msgError = msgError + getString(R.string.choose_a_benefit);
        }

        if(StringUtils.isNotBlank(msgError)){
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(ChooseBackgroundActivity.this, msgError, duration);
            toast.show();
        }else{
            Background background = (Background) backgroundsSpinner.getSelectedItem();
            player.setBackgroundId(background.getId());

            player.setAttributeIdsRollFromBackgroundTable(new ArrayList<String>());
            player.setFocusIdsRollFromBackgroundTable(new ArrayList<String>());
            player.setWeaponGroupIds(new ArrayList<String>());
            player.setSpokenLanguages(new ArrayList<String>());
            List<BackgroundTable> selectedBackgroudBonus = uiManager.getSelectedBackgroudBonus();
            PlayerManager.setBonusBackground(player,selectedBackgroudBonus);

            int id = radioGroup.getCheckedRadioButtonId();
            Focus focusSelected = order.get(id);
            player.setFocusIdChooseFromBackground(focusSelected.getId());

            assignAttributeActivity.putExtra(ActivityConstant.EXTRA_PLAYER, player);
            startActivity(assignAttributeActivity);
        }
    }
}
