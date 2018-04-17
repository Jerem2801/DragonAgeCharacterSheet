package com.galaxia.dragonagecharactersheet.controller.create;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.galaxia.dragonagecharactersheet.R;
import com.galaxia.dragonagecharactersheet.controller.ActivityConstant;
import com.galaxia.dragonagecharactersheet.element.background.Background;
import com.galaxia.dragonagecharactersheet.element.background.BackgroundManager;
import com.galaxia.dragonagecharactersheet.element.focus.Focus;
import com.galaxia.dragonagecharactersheet.element.focus.FocusManager;
import com.galaxia.dragonagecharactersheet.player.Player;

import java.util.List;

public class ChooseBonusActivity extends AppCompatActivity {

    private Player player;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_bonus);

        Intent intent = getIntent();
        player = intent.getParcelableExtra(ActivityConstant.EXTRA_PLAYER);
        Background background = BackgroundManager.getBackground(player.getBackgroundId());
    }
}
