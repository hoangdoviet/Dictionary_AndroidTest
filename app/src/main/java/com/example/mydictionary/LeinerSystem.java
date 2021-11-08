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
import java.util.Random;

import activities.DoQuestActivity;
import objects.QuestItem;
import objects.Word;
import objects.Hashtag;

public class LeinerSystem {
    private static List<List<String>> Box = new ArrayList<>();
    static String filename = "box_data.txt";
    private static BufferedReader br;

    private static int Day = 0;
    private static int CurrentDay = 1;
    private static List<String> Quest = new ArrayList<>();

    public static final boolean UP = true;
    public static final boolean DOWN = false;

    public static int getCurrentDay() {
        return CurrentDay;
    }

    public static int catchNewDay(){
        Day = CurrentDay;
        return Day;
    }

    public static boolean isNewDay(){
        if (Day!= CurrentDay) return true;
        else return false;
    }

    public static int nextDay(){
        CurrentDay++;
        makeDailyQuest();
        int size = Quest.size();
        for(int i = 0; i < size; i++) updateBox(UP,i);
        return CurrentDay;
    }

    public static String getKeyInQuest( int index){
        return Quest.get(index);
    }

    public static int questMaxSize() {
        int size = Box.get(0).size();
        if (Day % 2 == 0) size = size + Box.get(1).size();
        if (Day % 4 == 0) size = size + Box.get(2).size();
        if (Day % 10 == 0) size = size + Box.get(3).size();
        if (Day == -1) size = size + Box.get(4).size();
        return size;
    }
    public static Word getWordByKey(String key){
        return ListWords.getAll().get(SearchingWords.find(key));
    }

    public static int findIndexByKey(String key){
        return Quest.indexOf(key);
    }

    public static List<String> getQuest(){
        return Quest;
    }
    public static void makeDailyQuest(){
        Quest.clear();
        addToQuest(0);
        if (Day % 2 == 0) addToQuest(1);
        if (Day % 4 == 0) addToQuest(2);
        if (Day % 10 == 0) addToQuest(3);
        if (Day == -1) addToQuest(4);
    }

    public static void setQuest(List<String> quest){
        Quest = quest;

    }

    public static String showBox() {
        String result = "";
        for (int i = 0; i < 5; i++) {
            result = result + i + "\n";
            int size = Box.get(i).size();
            for (int j = 0; j < size; j++) {
                result = result + Box.get(i).get(j) + "\n";
            }
        }
        return result;
    }

    public static String showQuest(){
        int qSize = Quest.size();
        String result =  'Q' + "\n";
            for (int i = 0; i < qSize; i++)
                result = result + Quest.get(i) + "\n";
        return result;
    }

    public static boolean add(String key){
        boolean exist = false;
        for(int i = 0; i < 5; i++) {
            int size = Box.get(i).size();
            for (int j = 0; j < size; j++)
                if (Box.get(i).get(j).equals(key)) {
                    exist = true;
                    break;
                }
        }
        if (!exist) Box.get(0).add(key);
        return exist;
    }
    public static void createBox(){
        for(int i = 0; i < 5; i++){
            Box.add(new ArrayList<String>());
        }
    }

    public static void clearBox(){
        for(int i = 0; i<5; i++ ){
            Box.get(i).clear();
        }
    }
    public static void choosePastBox(int index){

    }

    public static void addToQuest(int boxNumber){
        Quest.addAll(Box.get(boxNumber));
    }

    public static void addToQuest(String key){
        if (Quest.indexOf(key)==-1)
            Quest.add(key);
    }

    public static void updateBox(boolean semaphore, int index) {
        for (int i = 0; i<4; i++){
            if (Box.get(i).size() <= index && index < Box.get(i+1).size()) {
                moveWord(semaphore, i +1 - (semaphore == UP ? 0: 1), i + (semaphore == UP ? 0: 1) , index);
                break;
            }
        }
    }

    private static void moveWord(boolean semaphore, int source, int des, int index){
        if (semaphore = UP ) {
            Box.get(des).add(Box.get(source).get(index));
            Box.get(source).remove(index);
            makeDailyQuest();
        }
        else {
            Box.get(source).add(Box.get(des).get(index));
            Box.get(des).remove(index);
            makeDailyQuest();
        }
    }

    // IO

    public static void clearFile(Context context){
        try {
            writeFile(null,context);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void saveToFile(Context context){
        try {
            writeFile(showBox()+showQuest(),context);
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
        try {
            // Open stream to read file.
            FileInputStream in = context.openFileInput(filename);

            br= new BufferedReader(new InputStreamReader(in));

            String line;
            int boxNumber = 0;

            while((line = readTagLine()).charAt(0) != 'Q') {
                if (line.charAt(0) == (char)(boxNumber+'0')) boxNumber++;
                else Box.get(boxNumber-1).add(line);
            }
            while ((line = readTagLine()) != null) Quest.add(line);
        } catch (Exception e) {
            Log.d("hehe", "Error: " + e.getMessage());
        }
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

