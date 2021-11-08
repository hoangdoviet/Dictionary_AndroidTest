package adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mydictionary.R;

import java.util.List;

import interfaces.OnItemClickListener;
import objects.Word;

public class SearchListAdapter extends RecyclerView.Adapter<SearchListAdapter.MyViewHolder> {

    private List<Word> listWords;
    private OnItemClickListener itemClickListener;

    public SearchListAdapter(List<Word> listWords) {
        this.listWords = listWords;
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.word_search_list_view, parent, false);
        MyViewHolder vh = new MyViewHolder(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvKey.setText(listWords.get(position).getKey());
        holder.tvType.setText(listWords.get(position).showMeaning());
        holder.setItemClickListener(itemClickListener);
    }

    @Override
    public int getItemCount() {
        return listWords.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvKey;
        TextView tvType;
        OnItemClickListener itemClickListener;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tvKey = itemView.findViewById(R.id.list_search_word_key);
            tvType = itemView.findViewById(R.id.list_search_word_type);
        }

        @Override
        public void onClick(View v) {
            Log.d("hehe", "onClick: ");
            itemClickListener.onClick(v, getAdapterPosition());
        }

        public void setItemClickListener(OnItemClickListener itemClickListener){
            this.itemClickListener = itemClickListener;
        }

    }
}
