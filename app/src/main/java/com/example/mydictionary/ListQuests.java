package com.example.mydictionary;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import objects.Meaning;
import objects.Word;
import objects.WordQuest;

public class ListQuests {
    static  List<WordQuest> listQuests = new ArrayList<>();
    final static String filename = "dict_quest.txt";

    private static BufferedReader reader;

    public static WordQuest get(int position){
        return listQuests.get(position);
    }

    public static List<WordQuest> getAll(){
        return listQuests;
    }

    public static void add(WordQuest wordQuest, Context context){
        listQuests.add(wordQuest);
        saveToFile(context);
    }

    private static void saveToFile(Context context){
        int size = listQuests.size();
        String str = "";
        for(int i = 0; i < size; i++){
            WordQuest wordQuest = listQuests.get(i);
            str = str + wordQuest.writeDown();
        }

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(context.openFileOutput(filename, Context.MODE_PRIVATE));
        } catch (FileNotFoundException e) {
            Log.d("hehe", "Error/writeFile: " + e.getMessage());
        }

        writer.print(str);
        writer.close();
    }

    public static void readFile(Context context){
        if(listQuests.size() == 0){
            try {
                FileInputStream in = context.openFileInput(filename);

                reader = new BufferedReader(new InputStreamReader(in));

                Word tmp = new Word();
                while((tmp = readWord()) != null){
                    Word newWord = tmp;
                    listQuests.add(new WordQuest(newWord, false));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    static Word readWord() throws IOException {
        Word word = new Word();

        String line = reader.readLine();
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

    static String readLine() throws IOException {
        String line1 = reader.readLine();
        if((line1 != null) && (!line1.equals(""))) return line1;
        else return null;
    }
}
