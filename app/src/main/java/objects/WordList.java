package objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WordList implements Serializable {
    protected List<Word> listWord = new ArrayList<>();

    public List<Word> getListWord() {
        return listWord;
    }

    public Word getWord(int position){
        return listWord.get(position);
    }

    public int size(){ return listWord.size();}

    public void add(Word word){
        listWord.add(word);
    }

    public void add(String key){
        listWord.add(new Word(key));
    }

    public Word getLast(){
        int size = listWord.size();
        if(size > 0) return listWord.get(size -1);
        else return null;
    }

    public void remove(int position){
        listWord.remove(position);
    }

    public void remove(Word word){
        listWord.remove(word);
    }

    public boolean isNull(){
        return (listWord.isEmpty());
    }

}
