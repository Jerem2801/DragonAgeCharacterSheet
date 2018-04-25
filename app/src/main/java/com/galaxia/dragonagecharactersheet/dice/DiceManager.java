package com.galaxia.dragonagecharactersheet.dice;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.galaxia.dragonagecharactersheet.data.DataPool;
import com.galaxia.dragonagecharactersheet.ressource.RessourceUtils;
import com.google.common.collect.Maps;

import java.util.Map;

public class DiceManager {

    private static final String DICE_DIR = "dice/";
    private static final String DICE_IMAGE_FILE_NAME = "dice_";
    private static final String DICE_EXTENSTION = ".png";

    private DiceManager(){

    }



    public static Map<Integer,Dice> getDiceData(){
        Map<Integer,Dice> dices = Maps.newHashMap();

        for(int i = 0; i < 7;i++){
            String result = String.valueOf(i);
            String path = DICE_DIR + DICE_IMAGE_FILE_NAME + result + DICE_EXTENSTION;
            Dice dice = new Dice(i,path);
            dices.put(i,dice);
        }

        return dices;
    }

    public static Dice getDice(int number){
        DataPool dataPool = DataPool.getInstance();
        Map<Integer, Dice> dices = dataPool.getDices();
        return dices.get(number);
    }

    public static Dice rollDice(){
        Map<Integer, Dice> dices = DataPool.getInstance().getDices();
        int random = (int) ((7 - 1) * Math.random()) + 1;
        return dices.get(random);
    }

    public static Bitmap getDiceImage(Context context,Dice dice){
        return RessourceUtils.getImage(context,dice.getImagePath());
    }

    public static int rollDices(Context context,ImageView... images){
        int sum = 0;

        for(ImageView image : images){
            Dice diceRoll = rollDice();
            image.setImageBitmap(DiceManager.getDiceImage(context,diceRoll));
            sum += diceRoll.getResult();
        }

        return sum;
    }

    public static void initializeDice(Context context,ImageView... dices) {
        Dice zero = getDice(0);
        for(ImageView dice : dices){
            dice.setImageBitmap(DiceManager.getDiceImage(context,zero));
        }
    }
}
