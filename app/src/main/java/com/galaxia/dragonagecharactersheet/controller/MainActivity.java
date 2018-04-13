package com.galaxia.dragonagecharactersheet.controller;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.galaxia.dragonagecharactersheet.R;
import com.galaxia.dragonagecharactersheet.controller.create.ChooseRaceActivity;
import com.galaxia.dragonagecharactersheet.data.DataPool;
import com.galaxia.dragonagecharactersheet.ressource.RessourcePath;
import com.galaxia.dragonagecharactersheet.ressource.RessourceUtils;

public class MainActivity extends AppCompatActivity {

    private ImageView logoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set the logo
        logoView = findViewById(R.id.main_image_logo);
        Bitmap logo = RessourceUtils.getImage(MainActivity.this, RessourcePath.LOGO_PATH);
        logoView.setImageBitmap(logo);

        //set the data in a singleton
        DataPool dataPool = DataPool.initialize(MainActivity.this);
    }

    public void createCharacterActivity(View view){
       Intent gameActivity = new Intent(MainActivity.this, ChooseRaceActivity.class);
       startActivity(gameActivity);
    }


}
