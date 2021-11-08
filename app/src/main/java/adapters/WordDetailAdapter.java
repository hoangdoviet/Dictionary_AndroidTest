package adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mydictionary.R;

import java.util.List;

import objects.Meaning;

public class WordDetailAdapter extends RecyclerView.Adapter<WordDetailAdapter.MyViewHolder> {

    private List<Meaning> meanings;

    public WordDetailAdapter(List<Meaning> meanings) {
        this.meanings = meanings;
    }

    @NonNull
    @Override
    public WordDetailAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_meaning, parent, false);
        MyViewHolder vh = new MyViewHolder(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull WordDetailAdapter.MyViewHolder holder, int position) {
        holder.tvType.setText(meanings.get(position).getType());
        holder.tvMeaning.setText(meanings.get(position).getMeanings());
    }

    @Override
    public int getItemCount() {
        return meanings.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvType;
        TextView tvMeaning;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvType = itemView.findViewById(R.id.meaning_type);
            tvMeaning = itemView.findViewById(R.id.meaning_meanings);
        }
    }
}
