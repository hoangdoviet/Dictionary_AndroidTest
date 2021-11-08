package com.example.mydictionary;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import objects.Hashtag;
import objects.Word;

public class ListTags {
    private static List<Hashtag> list = new ArrayList<>();

    static String filename = "vocabulary_03.txt";
    private static BufferedReader br;

    public static Hashtag getUserWord(){ return list.get(0);}

    public static int userSize(){ return list.get(0).size();}//

    public static int size(){ return list.size();}

    public static void createUserWordtag(){
        if (list.isEmpty()) list.add(new Hashtag("Your Word"));
        else if (!list.get(0).getTag().equals("Your Word"))
            list.add(0,new Hashtag("Your Word"));
    }

    /////////////////
    public static void add(String tag, Word word){

        int size = list.size();
        for(int i = 0;i<size; i++)
            if(tag.equals(list.get(i).getTag())) {
                list.get(i).add(word);
                return;
            }
        Hashtag hashtag = new Hashtag(tag);
        hashtag.add(word);
        list.add(hashtag);
    }

    public static boolean isNull(){
        int size = list.size();
        return (size == 0);
    }

    public static void add(Hashtag hashtag){
        list.add(hashtag);
    }

    public static Hashtag get(int position){
        return list.get(position);
    }

    public static List<Hashtag> getAll(){
        return list;
    }

    public static String show(){
        String result = "";
        int size = list.size();
        for(int i = 0; i < size; i++) {
            //result = result + "Tag: " +  list.get(i).getTag() + "\n";
            result = result + list.get(i).show();
        }
        result = result + "\n";
        return result;
    }

    public static void delete(Hashtag hashtag){
        list.remove(hashtag);
    }

    public static void delete(String tag){
        int size = list.size();
        for(int i = 0; i < size; i++){
            if(tag.equals(list.get(i).getTag())){
                list.remove(i);
                return;
            }
        }
    }

    public static void clear(){
        list.clear();
    }

    //GHI RA FILE TEXT NHỮNG HASHTAG TRONG LIST
    public static void saveToFile(Context context){
        try {
            writeFile(show(), context);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeFile(String str, Context context) throws IOException {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(context.openFileOutput(filename, Context.MODE_PRIVATE));
        } catch (FileNotFoundException e) {
            Log.d("hehe", "Error/writeFile: " + e.getMessage());
        }

        writer.print(str);
        writer.close();
    }


    //ĐỌC NHỮNG HASHTAG ĐÃ ĐƯỢC LƯU TRONG FILE TEXT ĐỂ ĐƯA VÀO LIST
    public static void readData(Context context){

        ListTags.clear();

        try {
            // Open stream to read file.
            FileInputStream in = context.openFileInput(filename);

            br= new BufferedReader(new InputStreamReader(in));

            String line;
            Hashtag tmp = null;

            while((line = readTagLine()) != null) {
                //Log.d("hehe", "readData: " + line);
                if(line.charAt(0) == '#'){
                    if(tmp != null) ListTags.add(tmp);
                    tmp = new Hashtag(line.replaceFirst("#", ""));
                } else
                if(line.charAt(0) == '@'){
                    tmp.add(line.replaceFirst("@", ""));
                } else
                if(line.charAt(0) == '*'){
                    tmp.getLast().add(line.replace("*", ""));
                } else tmp.getLast().getLast().addMeaning(line);
            }
            if (tmp != null) ListTags.add(tmp);

        } catch (Exception e) {
            Log.d("hehe", "Error: " + e.getMessage());
        }

        //Log.d("hehe", "readData: " + ListTags.show());
        List<Hashtag> list = ListTags.getAll();
    }
    static String readTagLine(){
        String result = null;
        try {
            result = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(result.equals("")) return null;
        return result;
    }
}
