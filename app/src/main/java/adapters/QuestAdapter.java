package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mydictionary.R;

import java.util.List;

import interfaces.OnItemClickListener;
import objects.Meaning;
import objects.Word;
import objects.WordQuest;

public class QuestAdapter extends RecyclerView.Adapter<QuestAdapter.MyViewHolder> {


    Context context;
    private List<WordQuest> list;
    private OnItemClickListener itemClickListener;
    private OnItemClickListener submitListener;

    private int mistake = 0;

    public int getMistake() {
        return mistake;
    }

    public void incMistake(){
        mistake++;
    }

    public void remove(int position){
        list.remove(position);
    }

    public Word get(int position){
        return list.get(position);
    }



    public QuestAdapter(List<WordQuest> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    public void setSubmitListener(OnItemClickListener submitListener){
        this.submitListener = submitListener;
    }

    @NonNull
    @Override
    public QuestAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quest, parent, false);
        MyViewHolder vh = new MyViewHolder(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull QuestAdapter.MyViewHolder holder, int position) {
        Meaning meaning = list.get(position).getRandomMeaning();
        holder.tvType.setText(meaning.getType());
        holder.tvMeaning.setText(meaning.getMeanings(1));
        final int pos = position;
        final MyViewHolder myViewHolder = holder;
        holder.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String answer = myViewHolder.etKey.getText().toString().trim();
                submitListener.onSubmit(v, pos, answer.equals(list.get(pos).getRawKey().trim()));
//                String answer = myViewHolder.etKey.getText().toString().trim();
//                if(answer.equals(list.get(pos).getRawKey().trim())){
//                    list.remove(pos);
//                    notifyDataSetChanged();
//                    Toast.makeText(context, "Correct!!!", Toast.LENGTH_SHORT).show();
//                } else Toast.makeText(context, "Wrong answer!", Toast.LENGTH_SHORT).show();
            }
        });
        holder.setItemClickListener(itemClickListener);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {

        TextView tvType;
        TextView tvMeaning;
        EditText etKey;
        Button btnSubmit;
        OnItemClickListener itemClickListener;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvType = itemView.findViewById(R.id.item_quest_text_type);
            tvMeaning = itemView.findViewById(R.id.item_quest_text_meaning);
            etKey = itemView.findViewById(R.id.item_quest_edit_key);
            btnSubmit = itemView.findViewById(R.id.item_quest_button_submit);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition());
            return true;
        }

        public void setItemClickListener(OnItemClickListener itemClickListener){
            this.itemClickListener = itemClickListener;
        }
    }
}
