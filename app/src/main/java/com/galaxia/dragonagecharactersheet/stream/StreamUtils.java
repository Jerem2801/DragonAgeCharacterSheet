package com.galaxia.dragonagecharactersheet.stream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class StreamUtils {

    private StreamUtils(){

    }


    public static void close(Reader br,Reader isr, InputStream is){
        try {
            closeReader(br);
            closeReader(isr);
            closeInputStream(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void closeReader(Reader r) throws IOException {
        if(r != null){
            r.close();
        }
    }

    private static void closeInputStream(InputStream is)  throws IOException{
        if(is != null){
            is.close();
        }
    }


}
