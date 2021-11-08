package objects;

import java.util.Random;

public class WordQuest extends Word {

    boolean isPass = false;

    private int mistake;

    public void incMistake(){
        mistake++;
    }

    public int getMistake() {
        return mistake;
    }

    public void setMistake(int mistake) {
        this.mistake = mistake;
    }

    public WordQuest(Word word, boolean isPass) {
        super(word);
        this.isPass = isPass;
        mistake = 0;
    }

    public boolean isPass() {
        return isPass;
    }

    public void setPass(boolean pass) {
        isPass = pass;
    }

    public String writeDown(){
        String result = "";
        result = result + "@" + super.key + "\n" + super.showFullMeanings() + "\n";
        return result;
    }

    public Meaning getRandomMeaning(){
        boolean flag = false;
        int position = 0;
        while (!flag){
            int size = super.meanings.size();
            Random rd = new Random();
            position = rd.nextInt(size);
            if(super.meanings.get(position).getType().contains(super.getRawKey())) flag = false;
                else flag = true;
        }

        return super.meanings.get(position);
    }
}
