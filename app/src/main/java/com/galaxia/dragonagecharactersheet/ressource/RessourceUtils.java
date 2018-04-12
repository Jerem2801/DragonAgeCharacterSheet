package com.galaxia.dragonagecharactersheet.ressource;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.galaxia.dragonagecharactersheet.log.LoggerUtils;
import com.galaxia.dragonagecharactersheet.stream.ReaderUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class RessourceUtils {

    private RessourceUtils(){

    }

    public static List<String> getData(Context context, String path){
        List<String> data = new ArrayList<>();
        InputStream is = null;

        try {
            is = context.getAssets().open(path);
            data = ReaderUtils.readFileWithList(is);
        } catch (IOException e) {
           LoggerUtils.log(context,e.getMessage());
        }

        return data;
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
