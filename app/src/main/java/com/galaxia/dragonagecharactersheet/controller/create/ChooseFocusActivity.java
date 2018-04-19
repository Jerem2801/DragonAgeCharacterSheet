package com.galaxia.dragonagecharactersheet.controller.create;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.galaxia.dragonagecharactersheet.R;
import com.galaxia.dragonagecharactersheet.controller.ActivityConstant;
import com.galaxia.dragonagecharactersheet.element.background.Background;
import com.galaxia.dragonagecharactersheet.element.background.BackgroundManager;
import com.galaxia.dragonagecharactersheet.element.focus.Focus;
import com.galaxia.dragonagecharactersheet.element.focus.FocusManager;
import com.galaxia.dragonagecharactersheet.element.focus.FocusUiManager;
import com.galaxia.dragonagecharactersheet.player.Player;
import com.galaxia.dragonagecharactersheet.player.PlayerManager;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

public class ChooseFocusActivity extends AppCompatActivity {

    private Player player;

    private TextView focusExplainTxt;
    private RadioGroup radioGroup;
    private Map<Integer,Focus> order;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_focus);

        Intent intent = getIntent();
        player = intent.getParcelableExtra(ActivityConstant.EXTRA_PLAYER);
        Background background = BackgroundManager.getBackground(player.getBackgroundId());
        List<Focus> focuss = FocusManager.getFocus(background.getChooseFocusId());

        focusExplainTxt = findViewById(R.id.choose_focus_explain_txt);
        String focusExplainTxtString = getString(R.string.choose_focus_background);
        String backgroundName = background.getName();
        String focusExplain = String.format(focusExplainTxtString,backgroundName);
        focusExplainTxt.setText(focusExplain);

        radioGroup = findViewById(R.id.choose_focus_radio_group);
        int id = 0;
        order = Maps.newHashMap();
        for(Focus focus : focuss){
            order.put(id,focus);
            android.support.v7.widget.AppCompatRadioButton radioButton = new android.support.v7.widget.AppCompatRadioButton(ChooseFocusActivity.this);
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

    public void chooseBonusActivity(View view){
        Intent focusActivity = new Intent(ChooseFocusActivity.this, ChooseBonusActivity.class);
        int id = radioGroup.getCheckedRadioButtonId();
        if(id != -1) {
            Focus focusSelected = order.get(id);
            player.setFocusIdChooseFromBackground(focusSelected.getId());
            focusActivity.putExtra(ActivityConstant.EXTRA_PLAYER, player);
            startActivity(focusActivity);
        }else{
            CharSequence text = getString(R.string.choose_a_focus);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(ChooseFocusActivity.this, text, duration);
            toast.show();
        }
    }
}
