package objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Hashtag extends WordList implements Serializable {
    String tag;

    public String getTag(){
        return tag;
    }

    public Hashtag(String tag) {
        this.tag = tag;
    }

    public String show(){
        String result = "#" + tag + "\n";
        int size = listWord.size();
        for(int i = 0; i<size; i++){
            Word word = listWord.get(i);
            result = result + "@" + word.key + "\n";
            result = result + word.showFullMeanings();
        }
        //result = result + "\n";
        return result;
    }

}
