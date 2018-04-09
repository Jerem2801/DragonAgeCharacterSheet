package com.galaxia.dragonagecharactersheet.stream;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;

public class WriterUtils {

    private WriterUtils(){

    }

    public static void writeFile(Context context, String fileName, String content){
        File file = new File(context.getFilesDir(), fileName);
        FileOutputStream outputStream;
        try {
            outputStream = context.openFileOutput(file.getName(), Context.MODE_PRIVATE);
            outputStream.write(content.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
