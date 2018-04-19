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
import com.galaxia.dragonagecharactersheet.element.background.Background;
import com.galaxia.dragonagecharactersheet.element.background.BackgroundManager;
import com.galaxia.dragonagecharactersheet.element.background.backgroundtable.BackgroundTable;
import com.galaxia.dragonagecharactersheet.element.background.backgroundtable.BackgroundTableManager;
import com.galaxia.dragonagecharactersheet.element.background.backgroundtable.BackgroundTableUiManager;
import com.galaxia.dragonagecharactersheet.element.focus.Focus;
import com.galaxia.dragonagecharactersheet.player.Player;
import com.galaxia.dragonagecharactersheet.player.PlayerManager;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class ChooseBonusActivity extends AppCompatActivity {

    private Player player;
    private List<BackgroundTable> backgroundTables;

    private TableLayout tableBonus;
    private ImageView diceOne;
    private ImageView diceSecond;
    private Button rollButton;
    private TextView result;
    private LinearLayout avantageLayout;
    private TextView avantageOne;
    private TextView avantageSecond;
    private BackgroundTable firstRoll = null;
    private BackgroundTable secondRoll = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_bonus);

        Intent intent = getIntent();
        player = intent.getParcelableExtra(ActivityConstant.EXTRA_PLAYER);
        Background background = BackgroundManager.getBackground(player.getBackgroundId());
        backgroundTables = background.getBonusRoll();

        tableBonus = findViewById(R.id.choose_bonus_table_bonus_table);
        diceOne = findViewById(R.id.choose_bonus_dice_1_image);
        diceSecond = findViewById(R.id.choose_bonus_dice_2_image);
        rollButton = findViewById(R.id.choose_bonus_roll_btn);
        result = findViewById(R.id.choose_bonus_result_txt);
        avantageLayout = findViewById(R.id.choose_bonus_avantage_linear);
        avantageOne = findViewById(R.id.choose_bonus_avantage_1_txt);
        avantageSecond = findViewById(R.id.choose_bonus_avantage_2_txt);

        BackgroundTableUiManager.setTableUi(ChooseBonusActivity.this,tableBonus,background);
        result.setText("0");

    }

    public void rollDices(View view){
        diceOne.setVisibility(View.VISIBLE);
        diceSecond.setVisibility(View.VISIBLE);

        int sum = DiceManager.rollDices(ChooseBonusActivity.this, diceOne, diceSecond);
        String sumString = String.valueOf(sum);
        result.setText(sumString);
        result.setVisibility(View.VISIBLE);
        BackgroundTable backgroundTable = BackgroundTableManager.getBackgroundTableWithRoll(backgroundTables, sum);
        int id = backgroundTable.getOrder();

        if(firstRoll == null){
            avantageLayout.setVisibility(View.VISIBLE);
            String bonusString = BackgroundTableUiManager.getBonusString(ChooseBonusActivity.this, backgroundTable);
            avantageOne.setText(bonusString);
            firstRoll = backgroundTable;
        }else if(firstRoll.getOrder() == id) {
            CharSequence text = getString(R.string.same_result);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(ChooseBonusActivity.this, text, duration);
            toast.show();
        }else{
            avantageSecond.setVisibility(View.VISIBLE);
            String bonusString = BackgroundTableUiManager.getBonusString(ChooseBonusActivity.this, backgroundTable);
            avantageSecond.setText(bonusString);
            secondRoll = backgroundTable;
            rollButton.setEnabled(false);
        }

    }

    public void rollAttributeActivity(View view){
        Intent bonusActivity = new Intent(ChooseBonusActivity.this, RollAttributesActivity.class);
        if(secondRoll != null) {
            PlayerManager.setBonusBackground(player,firstRoll,secondRoll);
            bonusActivity.putExtra(ActivityConstant.EXTRA_PLAYER, player);
            startActivity(bonusActivity);
        }else{
            String text = getString(R.string.roll_a_bonus);
            String number;
            if(firstRoll != null){
                number = "1";
            }else{
                number = "2";
            }
            text = String.format(text,number);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(ChooseBonusActivity.this, text, duration);
            toast.show();
        }
    }
}
