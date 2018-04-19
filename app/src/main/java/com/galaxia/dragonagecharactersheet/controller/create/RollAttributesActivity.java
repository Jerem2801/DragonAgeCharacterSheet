package com.galaxia.dragonagecharactersheet.controller.create;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.galaxia.dragonagecharactersheet.R;
import com.galaxia.dragonagecharactersheet.controller.ActivityConstant;
import com.galaxia.dragonagecharactersheet.dice.DiceManager;
import com.galaxia.dragonagecharactersheet.element.attribute.AttributeUiManager;
import com.galaxia.dragonagecharactersheet.element.background.Background;
import com.galaxia.dragonagecharactersheet.element.background.BackgroundManager;
import com.galaxia.dragonagecharactersheet.player.Player;
import com.galaxia.dragonagecharactersheet.player.PlayerManager;

public class RollAttributesActivity extends AppCompatActivity {

    private Player player;

    private TableLayout rollTable;
    private TextView countText;
    private TextView resultText;
    private ImageView diceOneImage;
    private ImageView diceSecondImage;
    private ImageView diceThirdImage;
    private Button rollButton;

    private int count = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.roll_attributes);

        Intent intent = getIntent();
        player = intent.getParcelableExtra(ActivityConstant.EXTRA_PLAYER);

        Background background = BackgroundManager.getBackground(player.getBackgroundId());

        rollTable = findViewById(R.id.roll_attribute_table);
        AttributeUiManager.setTableUi(RollAttributesActivity.this,rollTable);

        countText = findViewById(R.id.roll_attribute_count_txt);
        resultText = findViewById(R.id.roll_attribute_result_txt);
        diceOneImage = findViewById(R.id.roll_attribute_dice_1_image);
        diceSecondImage = findViewById(R.id.roll_attribute_dice_2_image);
        diceThirdImage = findViewById(R.id.roll_attribute_dice_3_image);
        rollButton = findViewById(R.id.roll_attribute_roll_btn);

        countText.setText(String.valueOf(count));
    }

    public void rollDiceForAttributes(View view){
        diceOneImage.setVisibility(View.VISIBLE);
        diceSecondImage.setVisibility(View.VISIBLE);
        diceThirdImage.setVisibility(View.VISIBLE);

        int sum = DiceManager.rollDices(RollAttributesActivity.this, diceOneImage, diceSecondImage, diceThirdImage);
        String sumString = String.valueOf(sum);
        resultText.setText(sumString);
        countText.setText(String.valueOf(--count));

    }
}
