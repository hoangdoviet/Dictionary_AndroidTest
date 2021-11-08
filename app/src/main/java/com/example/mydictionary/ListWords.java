package com.example.mydictionary;

import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import objects.Meaning;
import objects.Word;

public class ListWords implements Serializable {

    private static List<Word> listWords;

    public static List<Word> getAll(){
        return listWords;
    }

    public static void setListWords(List<Word> list){
        listWords = list;
    }

    public static Word get(int position){
        return listWords.get(position);
    }

    public static int size(){
        return listWords.size();
    }
}
