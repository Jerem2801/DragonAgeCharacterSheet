package com.galaxia.dragonagecharactersheet.controller.create;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
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
import com.galaxia.dragonagecharactersheet.data.DataPool;
import com.galaxia.dragonagecharactersheet.element.attribute.Attribute;
import com.galaxia.dragonagecharactersheet.element.attribute.AttributeManager;
import com.galaxia.dragonagecharactersheet.element.background.Background;
import com.galaxia.dragonagecharactersheet.element.background.BackgroundManager;
import com.galaxia.dragonagecharactersheet.element.background.backgroundtable.BackgroundTableUiManager;
import com.galaxia.dragonagecharactersheet.element.focus.Focus;
import com.galaxia.dragonagecharactersheet.element.focus.FocusManager;
import com.galaxia.dragonagecharactersheet.element.focus.FocusUiManager;
import com.galaxia.dragonagecharactersheet.element.language.Language;
import com.galaxia.dragonagecharactersheet.element.language.LanguageManager;
import com.galaxia.dragonagecharactersheet.element.language.LanguageUiManager;
import com.galaxia.dragonagecharactersheet.player.Player;
import com.galaxia.dragonagecharactersheet.ressource.RessourceUtils;
import com.galaxia.dragonagecharactersheet.ui.UiUtils;
import com.galaxia.dragonagecharactersheet.ui.ViewFormaterString;
import com.google.common.collect.Maps;

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
                initializeChoice(focus);

                List<Language> spokenLanguagesList = LanguageManager.getLanguage(background.getSpokenLanguageId());
                String spokenLanguagesString = LanguageUiManager.getLanguages(ChooseBackgroundActivity.this,spokenLanguagesList);
                spokenLanguages.setText(spokenLanguagesString);

                List<Language> writenLanguagesList = LanguageManager.getLanguage(background.getWritenLanguageId());
                String writenLanguagesString = LanguageUiManager.getLanguages(ChooseBackgroundActivity.this,writenLanguagesList);
                writenLanguages.setText(writenLanguagesString);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }

    @SuppressLint("RestrictedApi")
    private void initializeChoice(List<Focus> focuss) {
        radioGroup.removeAllViews();
        int id = 0;
        order = Maps.newHashMap();
        for(Focus focus : focuss){
            order.put(id,focus);
            android.support.v7.widget.AppCompatRadioButton radioButton = new android.support.v7.widget.AppCompatRadioButton(ChooseBackgroundActivity.this);
            radioButton.setId(id++);
            radioButton.setText(FocusUiManager.getFocusWithAttribute(focus));
            radioButton.setTextColor(getResources().getColor(R.color.colorPrimary));
            radioButton.setTextSize(TypedValue.COMPLEX_UNIT_DIP,16);
            radioButton.setTypeface(Typeface.DEFAULT, Typeface.ITALIC);
            radioButton.setSupportButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimaryDark)));
            RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(128, 8, 8, 8);
            radioButton.setLayoutParams(params);
            radioGroup.addView(radioButton);
        }
    }

    public void chooseFocusActivity(View view){

        Intent focusActivity = new Intent(ChooseBackgroundActivity.this, ChooseBonusActivity.class);
        int id = radioGroup.getCheckedRadioButtonId();
        if(id != -1) {
            Background background = (Background) backgroundsSpinner.getSelectedItem();
            player.setBackgroundId(background.getId());
            Focus focusSelected = order.get(id);
            player.setFocusIdChooseFromBackground(focusSelected.getId());
            focusActivity.putExtra(ActivityConstant.EXTRA_PLAYER, player);
            startActivity(focusActivity);
        }else{
            CharSequence text = getString(R.string.choose_a_focus);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(ChooseBackgroundActivity.this, text, duration);
            toast.show();
        }
    }
}
