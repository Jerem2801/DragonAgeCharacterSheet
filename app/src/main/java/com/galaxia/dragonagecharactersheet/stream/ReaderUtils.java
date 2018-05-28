package com.galaxia.dragonagecharactersheet.stream;

import android.content.Context;

import com.google.common.collect.Maps;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReaderUtils {

    private ReaderUtils(){

    }

    public static List<String> readFileWithList(InputStream is,boolean firstLineIsModel)  {
        List<String> lines = new ArrayList<>();
        BufferedReader br = null;
        InputStreamReader isr = null;

        try {
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            String line;
            boolean firstLine = true;
            while(( line = br.readLine()) != null ) {
                if(firstLineIsModel && firstLine){
                    firstLine = false;
                }else{
                    lines.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            StreamUtils.close(br,isr,is);
        }

        return lines;
    }

    public static Map<Integer,String> readFileWithMap(InputStream is)  {
        Map<Integer,String> lines = Maps.newHashMap();
        BufferedReader br = null;
        InputStreamReader isr = null;

        try {
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            String line;
            boolean firstLine = true;
            int i = 1;
            while(( line = br.readLine()) != null ) {
                lines.put(i++,line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            StreamUtils.close(br,isr,is);
        }

        return lines;
    }



    public static String readFile(Context context, String fileName)  {
        String content = "";
        BufferedReader br = null;
        InputStreamReader isr = null;
        FileInputStream fis = null;
        try {
            fis = context.openFileInput(fileName);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while(( line = br.readLine()) != null ) {
                sb.append( line );
                sb.append( '\n' );
            }
            content = sb.toString();
        } catch (FileNotFoundException e) {
            e.getMessage();
        } catch (IOException e) {
            e.getMessage();
        }finally {
            StreamUtils.close(br,isr,fis);
        }

        return content;
    }

    public static String readFile(File file)  {
        String content;
        BufferedReader br = null;
        InputStreamReader isr = null;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while(( line = br.readLine()) != null ) {
                sb.append( line );
                sb.append( '\n' );
            }
            content = sb.toString();
        } catch (FileNotFoundException e) {
            content = e.getMessage();
        } catch (IOException e) {
            content = e.getMessage();
        }finally {
            StreamUtils.close(br,isr,fis);
        }

        return content;
    }

    public static String readFile(InputStream is)  {
        String content;
        BufferedReader br = null;
        InputStreamReader isr = null;
        try {
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while(( line = br.readLine()) != null ) {
                sb.append( line );
                sb.append( '\n' );
            }
            content = sb.toString();
        } catch (FileNotFoundException e) {
            content = e.getMessage();
        } catch (IOException e) {
            content = e.getMessage();
        }finally {
            StreamUtils.close(br,isr,is);
        }
        return content;
    }



}
