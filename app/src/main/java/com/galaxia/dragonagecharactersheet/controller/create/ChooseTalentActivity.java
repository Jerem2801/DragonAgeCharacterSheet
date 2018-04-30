package com.galaxia.dragonagecharactersheet.controller.create;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.galaxia.dragonagecharactersheet.R;
import com.galaxia.dragonagecharactersheet.controller.ActivityConstant;
import com.galaxia.dragonagecharactersheet.controller.create.manager.ChooseTalentManager;
import com.galaxia.dragonagecharactersheet.data.DataPool;
import com.galaxia.dragonagecharactersheet.element.classe.Classe;
import com.galaxia.dragonagecharactersheet.element.classe.ClasseManager;
import com.galaxia.dragonagecharactersheet.element.classe.ClasseUiManager;
import com.galaxia.dragonagecharactersheet.element.race.Race;
import com.galaxia.dragonagecharactersheet.element.race.RaceManager;
import com.galaxia.dragonagecharactersheet.element.talent.Talent;
import com.galaxia.dragonagecharactersheet.element.talent.TalentManager;
import com.galaxia.dragonagecharactersheet.player.Player;
import com.galaxia.dragonagecharactersheet.ressource.RessourceUtils;
import com.galaxia.dragonagecharactersheet.ui.UiUtils;
import com.galaxia.dragonagecharactersheet.ui.ViewFormaterString;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Map;

public class ChooseTalentActivity extends AppCompatActivity {

    private Player player;
    private int restOfTalent;

    private TextView countTalent;
    private LinearLayout mother;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_talent);

        Intent intent = getIntent();
        player = intent.getParcelableExtra(ActivityConstant.EXTRA_PLAYER);
        Classe classe = ClasseManager.getClasse(player.getClasseId());
        List<Talent> talents = TalentManager.getTalents(classe.getStartedTalents());

        restOfTalent = classe.getNumberOfTalent();

        countTalent = findViewById(R.id.choose_talent_number);
        countTalent.setText(String.valueOf(restOfTalent));

        mother = findViewById(R.id.choose_talent_layout_mother);

        ChooseTalentManager.initialize(ChooseTalentActivity.this,talents,mother);
    }


    public void rollHealthActivity(View view){

    }



}
