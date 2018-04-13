package com.galaxia.dragonagecharactersheet.ui;


public class ViewFormaterString {

    public static final String SEPARATOR = "&";
    public static final String LINE_SEPARATOR = "\n";

    private ViewFormaterString(){

    }

    public static String setLineSeparator(String text){
        StringBuilder builder = new StringBuilder();

        String[] textSep = text.split(SEPARATOR);
        for(String textLine : textSep){
           builder.append(textLine);
           builder.append(LINE_SEPARATOR);
           builder.append(LINE_SEPARATOR);
        }

        return builder.toString().trim();
    }
}
