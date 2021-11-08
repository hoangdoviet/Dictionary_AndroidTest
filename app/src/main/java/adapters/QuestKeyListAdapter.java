package adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mydictionary.LeinerSystem;
import com.example.mydictionary.ListWords;
import com.example.mydictionary.R;
import com.example.mydictionary.SearchingWords;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static com.example.mydictionary.LeinerSystem.DOWN;
import static com.example.mydictionary.LeinerSystem.UP;

import interfaces.OnItemClickListener;
import objects.QuestItem;
import objects.Word;



public class QuestKeyListAdapter extends RecyclerView.Adapter<QuestKeyListAdapter.MyViewHolder>{

    public List<QuestItem> list = new ArrayList<>();

    private Random random = new Random();
    private int point = 0;

    public QuestKeyListAdapter(List<QuestItem> list, int i){
        this.list = list;
    }

    public QuestKeyListAdapter(List<String> quest){
        Collections.shuffle(quest);
        for (String key : quest){
            try {
                Word word = ListWords.getAll().get(SearchingWords.find(key));
                list.add(new QuestItem(word));
            } catch (Exception e){
                Word newWord = new Word(key);
                list.add((QuestItem) newWord);
            }
        }
    }

    @NonNull
    @Override
    public QuestKeyListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.quest_key_list_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestKeyListAdapter.MyViewHolder holder, final int position) {
        QuestItem item = list.get(position);
        holder.key_tv.setText(item.getRawKey());
        int type_index = random.nextInt(item.getMeaningsSize());
        list.get(position).setType_index(type_index);
        holder.type_tv.setText(item.getTypeByIndex(type_index));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private TextView key_tv;
        private TextView type_tv;
        private EditText meaning_input;
        private EditText ipa_input;
        OnItemClickListener onItemClickListener;

        public MyViewHolder(View itemView){
            super(itemView);
            initItemView();
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            this.onItemClickListener = onItemClickListener;
        }
        private void initItemView(){
            key_tv = itemView.findViewById(R.id.quest_key_list_view_key);
            type_tv = itemView.findViewById(R.id.quest_key_list_view_type);
            meaning_input = itemView.findViewById(R.id.quest_key_list_view_edit_meaning);
            ipa_input = itemView.findViewById(R.id.quest_key_list_view_edit_ipa);
        }

        @Override
        public void onClick(View v) {
            Log.d("hehe", "onClick: ");
        }

        @Override
        public boolean onLongClick(View v){
            int position = getAdapterPosition();
            word_score(position);
            list.remove(position);
            notifyItemRemoved(position);
            announce();
            return true;
        }

        private void word_score(int position){
            String key = list.get(position).getKey();
            LeinerSystem.makeDailyQuest();
            if (isCorrect(position)){
                point++;
                LeinerSystem.updateBox(DOWN,LeinerSystem.findIndexByKey(key));
            }
            else {
                point--;
                LeinerSystem.updateBox(UP,LeinerSystem.findIndexByKey(key));
            }
            LeinerSystem.getQuest().clear();
        }
        private boolean isCorrect(int position){
            QuestItem item = list.get(position);
            List<String> meaning = item.getMeaningByIndex(item.getType_index());
            String mean_ip = meaning_input.getText().toString();
            boolean mean_bool = false;
            for (String mean : meaning)
                if (meaning.equals(mean_ip)) {
                    mean_bool = true;
                    break;
                }
            return (mean_bool && item.getIPA().equals(ipa_input.getText().toString()));
        }

        private void announce(){
            if (getItemCount() < LeinerSystem.questMaxSize()) {
               for ( QuestItem q: list)
                   LeinerSystem.addToQuest(q.getKey());
            }
        }


    }


}
