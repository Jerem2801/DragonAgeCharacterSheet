package com.galaxia.dragonagecharactersheet.controller.create;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.galaxia.dragonagecharactersheet.R;
import com.galaxia.dragonagecharactersheet.controller.ActivityConstant;
import com.galaxia.dragonagecharactersheet.dice.DiceManager;
import com.galaxia.dragonagecharactersheet.element.attribute.AttributeManager;
import com.galaxia.dragonagecharactersheet.element.attribute.AttributeUiManager;
import com.galaxia.dragonagecharactersheet.player.Player;
import com.google.common.collect.Lists;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

public class RollAttributesActivity extends AppCompatActivity {

    public static final String ROLLS = "rolls";
    private Player player;
    private Map<Integer,Integer> rollMap;
    private List<Integer> rolls = Lists.newArrayList();

    private TableLayout rollTable;
    private TextView resultText;
    private ImageView diceOneImage;
    private ImageView diceSecondImage;
    private ImageView diceThirdImage;
    private Button rollButton;

    private LinearLayout numberLayout;
    private TextView numberText;

    private int count = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.roll_attributes);

        Intent intent = getIntent();
        player = intent.getParcelableExtra(ActivityConstant.EXTRA_PLAYER);

        rollTable = findViewById(R.id.roll_attribute_table);
        rollMap = AttributeManager.getRollMap(RollAttributesActivity.this);
        AttributeUiManager.setTableUi(RollAttributesActivity.this,rollMap,rollTable);

        resultText = findViewById(R.id.roll_attribute_result_txt);
        diceOneImage = findViewById(R.id.roll_attribute_dice_1_image);
        diceSecondImage = findViewById(R.id.roll_attribute_dice_2_image);
        diceThirdImage = findViewById(R.id.roll_attribute_dice_3_image);
        rollButton = findViewById(R.id.roll_attribute_roll_btn);
        numberLayout = findViewById(R.id.roll_attribute_number_layout);
        numberText = findViewById(R.id.roll_attribute_roll_numbers_txt);
    }

    public void rollDiceForAttributes(View view){
        int sum = DiceManager.rollDices(RollAttributesActivity.this, diceOneImage, diceSecondImage, diceThirdImage);
        String sumString = String.valueOf(sum);
        resultText.setText(sumString);
        --count;

        if(count == 0){
            rollButton.setEnabled(false);
        }

        Integer roll = rollMap.get(sum);
        rolls.add(roll);
        String numberTextString = getUiLIstNumber(rolls);
        numberText.setText(numberTextString);

    }

    private String getUiLIstNumber(List<Integer> rolls) {
        StringBuilder builder = new StringBuilder();
        for(Integer i : rolls){
            if(StringUtils.isBlank(builder.toString())){
                builder.append(String.valueOf(i));
            }else{
                builder.append(", " + String.valueOf(i));
            }
        }
        return builder.toString().trim();
    }

    public void assignAttributeActivity(View view){
        Intent bonusActivity = new Intent(RollAttributesActivity.this, ResumeActivity.class);
        if(count != 0) {
            String text = getString(R.string.roll_a_bonus);
            String countString = String.valueOf(count);
            text = String.format(text,countString);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(RollAttributesActivity.this, text, duration);
            toast.show();
        }else{
            bonusActivity.putExtra(RollAttributesActivity.ROLLS,numberText.getText());
            bonusActivity.putExtra(ActivityConstant.EXTRA_PLAYER, player);
            startActivity(bonusActivity);
        }

    }
}
