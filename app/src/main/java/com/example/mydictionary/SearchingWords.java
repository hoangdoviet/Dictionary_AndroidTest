package com.example.mydictionary;

import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;

import java.util.ArrayList;
import java.util.List;

import objects.Word;
import objects.WordSuggestion;

public class SearchingWords {
    public static List<SearchSuggestion> getSuggestion(String searchWord) {
        List<SearchSuggestion> suggestions = new ArrayList<>();

        int begin = find(searchWord);
        String searchWord2 = searchWord + 'z';
        int end = find(searchWord2);
        for (int i = begin; i < end; i++) {
            final Word word = ListWords.get(i);
            WordSuggestion suggestion = new WordSuggestion(word);
            suggestions.add(suggestion);
        }
        return suggestions;
    }

    public static int find(String word) {
        int left = 0;
        int right = ListWords.size() - 1;
        while (left < right) {
            int middle = (left + right) / 2;
            String midWord = ListWords.get(middle).getKey();
            if (compare(midWord, word) >= 0) right = middle;
            else left = middle + 1;
        }
        return right;
    }

    static int compare(String s1, String s2) {
        int length1 = s1.length();
        int length2 = s2.length();
        int l = Math.min(length1, length2);
        for (int i = 0; i < l; i++) {
            if ((int) s1.charAt(i) - (int) s2.charAt(i) > 0) return 1;
            if ((int) s1.charAt(i) - (int) s2.charAt(i) < 0) return -1;
        }
        if (length1 > length2) return 1;
        if (length2 > length1) return -1;
        return 0;
    }

    public static List<Word> search(String searchWord) {
        List<Word> result = new ArrayList<>();
        int size = ListWords.size();

        for (int i = 0; i < size; i++) {
            Word word = ListWords.get(i);
            if (match(searchWord, word.getKey())) result.add(word);
        }
        return result;
    }

    static boolean match(String searchKey, String wordKey) {
        int l_search = searchKey.length();
        int l_word = wordKey.length();
        if (l_search > l_word) return false;
        else
            for (int i = 0; i < l_search; i++)
                if (searchKey.charAt(i) != wordKey.charAt(i)) return false;
        return true;
    } ////////////
}

