package com.galaxia.dragonagecharactersheet.ressource;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.galaxia.dragonagecharactersheet.log.LoggerUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class RessourceUtils {

    private RessourceUtils(){

    }

    public static Bitmap getImage(Context context, String path){
        Bitmap image = null;
        InputStream is = null;
        BufferedInputStream bis = null;
        try {
            is = context.getAssets().open(path);
            bis = new BufferedInputStream(is);
            image = BitmapFactory.decodeStream(bis);
        } catch (IOException e) {
            LoggerUtils.log(context,e.getMessage());
        } finally {
            closeInputStream(context,bis,is);
        }

        return image;
    }

    private static void closeInputStream(Context context,BufferedInputStream bis,InputStream is){
        try {
            close(bis);
            close(is);
        } catch (IOException e) {
            LoggerUtils.log(context,e.getMessage());
        }
    }

    private static void close(InputStream is) throws IOException {
        if(is != null){
            is.close();
        }
    }

}
