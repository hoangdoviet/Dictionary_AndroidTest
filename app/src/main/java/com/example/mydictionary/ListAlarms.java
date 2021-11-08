package com.example.mydictionary;

import android.content.Context;
import android.provider.ContactsContract;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import objects.Meaning;
import objects.Word;

public class ListAlarms implements Serializable {
    private static List<Word> list = new ArrayList<>();

    private static String filename = "bookmark_test01.txt";
    static private BufferedReader br;

    public static List<Word> get(){
        return list;
    }

    public ListAlarms() {
    }

    public static boolean isNull(){
        int size = list.size();
        return (size == 0);
    }

    public static void add(Word word) {
        list.add(word);
    }

    public static void delete(int position) {
        list.remove(position);
    }


    //ĐỌC NHỮNG WORD ĐƯỢC TẠO ALARM TRONG FILE TEXT
    public static void readFile(Context context) {
        try {
            // Open stream to read file.
            FileInputStream in = context.openFileInput(filename);
            br = new BufferedReader(new InputStreamReader(in));
            Word tmp = new Word();
            while((tmp = readWord()) != null){
                Word newWord = tmp;
                list.add(newWord);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static Word readWord() {
        Word word = new Word();

        String line = null;
        try {
            line = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(line == null) return null;
        else {
            line = line.replace("@", "");
            word.setKey(line);
            Meaning tmp = null;
            while((line = readLine()) != null)
                if(line.charAt(0) == '*' || line.charAt(0) == '@') {
                    if (tmp != null) {
                        word.addMeaning(new Meaning(tmp.getType(), tmp.getMeaning()));
                        tmp = null;
                    }
                    line = line.replace("*", "");
                    line = line.replace("@", "");
                    tmp = new Meaning(line);
                }   else {
                    if (tmp != null) tmp.addMeaning(line);
                }
            if (tmp != null) word.addMeaning(tmp);
        }

        return  word;
    }

    static String readLine()  {
        String line1 = null;
        try {
            line1 = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if((line1 != null) && (!line1.equals(""))) return line1;
        else return null;
    }

    //GHI NHỮNG WORD ĐƯỢC TẠO ALARM RA FILE TEXT
    public static void writeFile(Context context){
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(context.openFileOutput(filename, Context.MODE_PRIVATE));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String str = "";
        int size = list.size();
        for(int i = 0; i < size; i++){
            Word word = list.get(i);
            str = str + "@" + word.getKey() + "\n";
            str = str + word.showFullMeanings();
            str = str + "\n";
        }

        writer.print(str);
        writer.close();
    }
}