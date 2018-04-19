package com.galaxia.dragonagecharactersheet.controller.create;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.galaxia.dragonagecharactersheet.R;
import com.galaxia.dragonagecharactersheet.controller.ActivityConstant;
import com.galaxia.dragonagecharactersheet.player.Player;
import com.galaxia.dragonagecharactersheet.player.PlayerManager;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class AssignAttributeActivity extends AppCompatActivity {

    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assign_attribute);

        Intent intent = getIntent();
        player = intent.getParcelableExtra(ActivityConstant.EXTRA_PLAYER);
        String numbersInOneString =intent.getStringExtra(RollAttributesActivity.ROLLS);
        List<String> numbers = Arrays.asList(numbersInOneString.split(","));

        Map<String, Integer> totalAttribute = PlayerManager.getTotalAttribute(player);

        for(Map.Entry<String, Integer> entry : totalAttribute.entrySet()){
            String attributeId = entry.getKey();
            int value = entry.getValue();
        }



    }
}
