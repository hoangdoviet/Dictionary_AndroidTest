package objects;

public class QuestItem extends Word{
    int type_index = -1;

    public QuestItem(int wordIndex){
        super(wordIndex);
    }
    public QuestItem(Word word){
        super(word);
    }

    public int getType_index(){
        return type_index;
    }

    public void setType_index(int type_index){
        this.type_index = type_index;
    }
}
