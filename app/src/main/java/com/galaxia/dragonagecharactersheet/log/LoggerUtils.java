package com.galaxia.dragonagecharactersheet.log;

import android.content.Context;

import com.galaxia.dragonagecharactersheet.stream.ReaderUtils;
import com.galaxia.dragonagecharactersheet.stream.WriterUtils;

import java.text.SimpleDateFormat;
import java.util.Date;


public class LoggerUtils {

    private static final String LOG = "log.txt";
    private static final int CHARACTER_LIMIT = 5000;

    private LoggerUtils(){

    }

    public static void log(Context context,String message){
        String content = ReaderUtils.readFile(context, LOG);
        StringBuilder sb = new StringBuilder();
        if(content.length() < CHARACTER_LIMIT){
            sb = new StringBuilder(content);
        }
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String dateString = sdf.format(date);
        sb.append(dateString + " : " + message);
        WriterUtils.writeFile(context,LOG,sb.toString());
    }

}
