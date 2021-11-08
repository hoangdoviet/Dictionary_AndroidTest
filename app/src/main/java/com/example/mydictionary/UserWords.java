package com.example.mydictionary;

import com.example.mydictionary.ListTags;
import com.example.mydictionary.ListWords;

import objects.Meaning;
import objects.Word;

import com.example.mydictionary.SearchingWords;

public class UserWords {


    public static void startAdding(){
        ListTags.createUserWordtag();
        if (!ListTags.getUserWord().isNull()) {
            int size = ListTags.getUserWord().size();
            for (int i = 0; i < size; i++)
                addToListWords(ListTags.getUserWord().getWord(i));
        }
    }

    public static void addToListWords( Word word){
        ListWords.getAll().add(SearchingWords.find(word.getKey()),word);
    }

    public static void add(Word word, Meaning meaning){
        String key = word.getKey();
        int index = findKey(key);
        if (findKey(key) == -1) {
            word.addMeaning(meaning);
            addToListWords(word);
            ListTags.add("Your Word",word);

        }
        else {
            ListTags.getUserWord().getWord(index).addMeaning(meaning);
        }
    }

    public static int findKey(String key){
        int size = ListTags.userSize();
        for(int i = 0; i < size; i++){
            if (ListTags.getUserWord().getWord(i).getKey().equals(key)) return i;
        }
        return -1;
    }
}
