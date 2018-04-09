package com.galaxia.dragonagecharactersheet.stream;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ReaderUtils {

    private ReaderUtils(){

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
            close(br,isr,fis);
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
            close(br,isr,fis);
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
            close(br,isr,is);
        }
        return content;
    }

    public static List<String> readFileWithList(InputStream is)  {
        List<String> lines = new ArrayList<>();
        BufferedReader br = null;
        InputStreamReader isr = null;
        try {
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            String line;
            while(( line = br.readLine()) != null ) {
                lines.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            close(br,isr,is);
        }
        return lines;
    }

    private static void close(BufferedReader br,InputStreamReader isr, FileInputStream fis){
        try {
            if(br != null){
                br.close();
            }
            if(isr != null){
                isr.close();
            }
            if(fis != null){
                fis.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void close(BufferedReader br,InputStreamReader isr, InputStream is){
        try {
            if(br != null){
                br.close();
            }
            if(isr != null){
                isr.close();
            }
            if(is != null){
                is.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
