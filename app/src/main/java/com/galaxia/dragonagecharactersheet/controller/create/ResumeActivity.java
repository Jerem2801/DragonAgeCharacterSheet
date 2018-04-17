package com.galaxia.dragonagecharactersheet.controller.create;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.galaxia.dragonagecharactersheet.R;
import com.galaxia.dragonagecharactersheet.controller.ActivityConstant;
import com.galaxia.dragonagecharactersheet.element.background.BackgroundManager;
import com.galaxia.dragonagecharactersheet.element.classe.ClasseManager;
import com.galaxia.dragonagecharactersheet.element.focus.FocusManager;
import com.galaxia.dragonagecharactersheet.element.race.RaceManager;
import com.galaxia.dragonagecharactersheet.player.Player;

public class ResumeActivity extends AppCompatActivity {

    private Player player;

    private LinearLayout mother;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume);

        Intent intent = getIntent();
        player = intent.getParcelableExtra(ActivityConstant.EXTRA_PLAYER);
        String raceId = player.getRaceId();
        String raceName = RaceManager.getRace(raceId).getName();
        String classeId = player.getClasseId();
        String classeName = ClasseManager.getClasse(classeId).getName();
        String backgroundId = player.getBackgroundId();
        String backgroundName = BackgroundManager.getBackground(backgroundId).getName();
        String focusId = player.getFocusIds().iterator().next();
        String focusName = FocusManager.getFocus(focusId).getName();

        mother = findViewById(R.id.mother);

        createNewTextView(ResumeActivity.this,"Race : ",raceName);
        createNewTextView(ResumeActivity.this,"Classe : ",classeName);
        createNewTextView(ResumeActivity.this,"Race : ",backgroundName);
        createNewTextView(ResumeActivity.this,"Focus : ",focusName);
    }

    private void createNewTextView(Context context, String name, String raceId) {
        TextView textView = new TextView(context);
        String text = name + raceId;
        textView.setText(text);
        textView.setTextColor(getResources().getColor(R.color.colorPrimary));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);
        textView.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(16, 16, 16, 16);
        textView.setLayoutParams(params);
        mother.addView(textView);
    }
}
