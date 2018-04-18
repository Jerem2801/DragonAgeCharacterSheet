package com.galaxia.dragonagecharactersheet.controller.create;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;

import com.galaxia.dragonagecharactersheet.R;
import com.galaxia.dragonagecharactersheet.controller.ActivityConstant;
import com.galaxia.dragonagecharactersheet.element.attribute.AttributeUiManager;
import com.galaxia.dragonagecharactersheet.element.background.Background;
import com.galaxia.dragonagecharactersheet.element.background.BackgroundManager;
import com.galaxia.dragonagecharactersheet.player.Player;
import com.galaxia.dragonagecharactersheet.player.PlayerManager;

public class RollAttributesActivity extends AppCompatActivity {

    private Player player;

    private TableLayout rollTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.roll_attributes);

        Intent intent = getIntent();
        player = intent.getParcelableExtra(ActivityConstant.EXTRA_PLAYER);

        Background background = BackgroundManager.getBackground(player.getBackgroundId());
        PlayerManager.addAttribute(player,background.getIncreaseAttributeId(),1);

        rollTable = findViewById(R.id.roll_attribute_table);
        AttributeUiManager.setTableUi(RollAttributesActivity.this,rollTable);




    }
}
