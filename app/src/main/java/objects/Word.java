package objects;

import com.example.mydictionary.ListWords;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Word implements Serializable {
    protected String key;
    protected List<Meaning> meanings = new ArrayList<>();

    public Word(){ }

    public Word(Word word){
        key = word.key;
        meanings = word.meanings;
    }

    public Word (int wordListIndex){
        Word word = ListWords.get(wordListIndex);
        key = word.key;
        meanings = word.meanings;
    }

    public Word(String key){ this.key = key; }

    public Word(String key, List<Meaning> meanings) {
        this.key = key;
        this.meanings = meanings;
    }

    public String getKey() {
        return key;
    }

    public String getTypeByIndex(int index){
        return meanings.get(index).getType();
    }

    public List<String> getMeaningByIndex(int index){
        return meanings.get(index).getMeaning();
    }

    public String getRawKey(){
        int end = key.indexOf('/');
        if(end > 1) return key.substring(0,end-1);
        return key;
    }

    public String getIPA(){
        int end = key.indexOf('/');
        if(end > 1) return key.substring(end);
        return null;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void add(String type){
        meanings.add(new Meaning(type));
    }
    public Meaning getLast(){
        int size = meanings.size();
        if(size > 0) return meanings.get(size - 1);
            else {
                meanings.add(new Meaning(" "));
                return meanings.get(0);
            }
    }

    public List<Meaning> getMeanings() {
        return meanings;
    }

    public int getMeaningsSize(){
        return meanings.size();
    }

    public void setMeanings(List<Meaning> meanings) {
        this.meanings = meanings;
    }

    public void addMeaning(Meaning meaning){
        meanings.add(meaning);
    }

    public void show(){
        System.out.println(key + "\n");
        int size = meanings.size();
        for(int i = 0; i < size; i++)
            meanings.get(i).show();
    }

    public String showMeaning(){
        String result = "";
        int size = meanings.size();
        for(int i = 0; i<size; i++)
            if(i != size-1)
            result = result + meanings.get(i).getType() + ", ";
            else result = result + meanings.get(i).getType();
            return result;
    }

    public String showFullMeanings(){
        String result = "";
        int meaningSize = meanings.size();
        for(int i = 0 ; i < meaningSize; i++){
            result = result + "*" + meanings.get(i).getType()+ "\n";
            int size = meanings.get(i).size();
            for(int j = 0; j < size; j++)
                result = result + meanings.get(i).getMeaning().get(j) + "\n";
        }

        return result;
    }
}
