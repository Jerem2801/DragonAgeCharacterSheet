package com.galaxia.dragonagecharactersheet.controller.create;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.bluejamesbond.text.DocumentView;
import com.bluejamesbond.text.style.TextAlignment;
import com.galaxia.dragonagecharactersheet.R;
import com.galaxia.dragonagecharactersheet.controller.ActivityConstant;
import com.galaxia.dragonagecharactersheet.data.DataPool;
import com.galaxia.dragonagecharactersheet.element.attribute.Attribute;
import com.galaxia.dragonagecharactersheet.element.background.Background;
import com.galaxia.dragonagecharactersheet.element.background.BackgroundManager;
import com.galaxia.dragonagecharactersheet.element.background.backgroundtable.BackgroundTableUiManager;
import com.galaxia.dragonagecharactersheet.element.classe.Classe;
import com.galaxia.dragonagecharactersheet.element.focus.Focus;
import com.galaxia.dragonagecharactersheet.element.focus.FocusUiManager;
import com.galaxia.dragonagecharactersheet.element.language.LanguageUiManager;
import com.galaxia.dragonagecharactersheet.element.race.Race;
import com.galaxia.dragonagecharactersheet.player.Player;
import com.galaxia.dragonagecharactersheet.ressource.RessourceUtils;
import com.galaxia.dragonagecharactersheet.ui.UiUtils;
import com.galaxia.dragonagecharactersheet.ui.ViewFormaterString;

import java.util.List;
import java.util.Map;

public class ChooseBackgroundActivity extends AppCompatActivity {

    private Player player;

    private Spinner backgroundsSpinner;
    private ImageView backgroundImage;
    private DocumentView description;
    private DocumentView attributBonusExplain;
    private TextView attributBonus;
    private DocumentView focusExplain;
    private TextView chooseFocus;
    private TextView spokenLanguages;
    private TextView writenLanguages;
    private DocumentView tableBonusExplain;
    private TableLayout tableBonus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_background);


        Intent intent = getIntent();
        player = intent.getParcelableExtra(ActivityConstant.EXTRA_PLAYER);
        Race race = player.getRace();
        Classe classe = player.getClasse();

        backgroundsSpinner = findViewById(R.id.choose_background_spinner);
        backgroundImage = findViewById(R.id.choose_background_image);
        description = findViewById(R.id.choose_background_description_txt);
        attributBonusExplain = findViewById(R.id.choose_background_attribut_bonus_explain_txt);
        attributBonus = findViewById(R.id.choose_background_attribute_bonus_txt);
        focusExplain = findViewById(R.id.choose_background_focus_explain_txt);
        chooseFocus = findViewById(R.id.choose_background_focus_txt);
        spokenLanguages = findViewById(R.id.choose_background_spoken_languages_txt);
        writenLanguages = findViewById(R.id.choose_background_writen_languages_txt);
        tableBonusExplain = findViewById(R.id.choose_background_table_bonus_table_explain);
        tableBonus = findViewById(R.id.choose_background_table_bonus_table);

        initializeSpinner(race,classe);
        onSelectedItem();
    }

    private void initializeSpinner(Race race,Classe classe) {
        DataPool dataPool = DataPool.getInstance();
        Map<String, Background> backgrounds = dataPool.getBackgrounds();
        List<Background> backgroundsList = BackgroundManager.getBackground(backgrounds,race.getId(),classe.getId());
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
                description.getDocumentLayoutParams().setTextAlignment(TextAlignment.JUSTIFIED);


                String increaseAttributeDesc = background.getIncreaseAttributeDesc();
                attributBonusExplain.setText(ViewFormaterString.setLineSeparator(increaseAttributeDesc));
                attributBonusExplain.getDocumentLayoutParams().setTextAlignment(TextAlignment.JUSTIFIED);

                Attribute increaseAttribute = background.getIncreaseAttribute();
                String increaseAttributePhrase = getString(R.string.attribute_bonus_explain);
                String finalIncreaseAttribute = String.format(increaseAttributePhrase, increaseAttribute.getName());
                attributBonus.setText(finalIncreaseAttribute);

                String focusExplainString = getString(R.string.choose_focus_explain);
                focusExplain.setText(focusExplainString);
                focusExplain.getDocumentLayoutParams().setTextAlignment(TextAlignment.JUSTIFIED);
                List<Focus> focus = background.getChooseFocus();
                String listFocus = FocusUiManager.getChooseFocus(focus);
                chooseFocus.setText(listFocus);

                String spokenLanguagesList = LanguageUiManager.getLanguages(ChooseBackgroundActivity.this,background.getSpokenLanguage());
                spokenLanguages.setText(spokenLanguagesList);
                String writenLanguagesList = LanguageUiManager.getLanguages(ChooseBackgroundActivity.this,background.getWritenLanguage());
                writenLanguages.setText(writenLanguagesList);

                String tableBonusExplainString = getString(R.string.rolls_bonus_explain);
                tableBonusExplain.setText(tableBonusExplainString);
                tableBonusExplain.getDocumentLayoutParams().setTextAlignment(TextAlignment.JUSTIFIED);

                tableBonus.removeAllViews();
                BackgroundTableUiManager.setTableUi(ChooseBackgroundActivity.this,tableBonus,background);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }
}
