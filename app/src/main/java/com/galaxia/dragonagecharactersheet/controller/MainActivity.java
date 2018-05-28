package com.galaxia.dragonagecharactersheet.controller;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.galaxia.dragonagecharactersheet.R;
import com.galaxia.dragonagecharactersheet.controller.create.ChooseRaceActivity;
import com.galaxia.dragonagecharactersheet.controller.sheet.SheetMenu;
import com.galaxia.dragonagecharactersheet.data.DataPool;
import com.galaxia.dragonagecharactersheet.ressource.RessourcePath;
import com.galaxia.dragonagecharactersheet.ressource.RessourceUtils;

public class MainActivity extends AppCompatActivity {

    private ImageView logoImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set the logo
        logoImage = findViewById(R.id.main_logo_image);
        Bitmap logo = RessourceUtils.getImage(MainActivity.this, RessourcePath.LOGO_PATH);
        logoImage.setImageBitmap(logo);

        //set the data in a singleton
        DataPool.initialize(MainActivity.this);
    }


    /**
     * @param view
     *
     * get to next activity, which is Race choice.
     */
    public void nextActivityCreateCharacter(View view){
       Intent createCharacterIntent = new Intent(MainActivity.this, ChooseRaceActivity.class);
       startActivity(createCharacterIntent);
    }

    public void nextActivitySheetCharacter(View view){
        Intent sheetCharacterIntent = new Intent(MainActivity.this, SheetMenu.class);
        startActivity(sheetCharacterIntent);
    }


}
