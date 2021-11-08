package adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mydictionary.R;

import java.util.List;

import interfaces.OnItemClickListener;
import objects.Hashtag;

public class TagListAdapter extends RecyclerView.Adapter<TagListAdapter.MyViewHolder> {

    List<Hashtag> list;
    OnItemClickListener itemClickListener;

    public TagListAdapter(List<Hashtag> list){
        this.list = list;
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.tag_list_view, parent, false);
        MyViewHolder vh = new MyViewHolder(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvTag.setText(list.get(position).getTag());
        holder.setItemClickListener(itemClickListener);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public Hashtag get(int position) {
        return list.get(position);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvTag;
        OnItemClickListener itemClickListener = new OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
            }

            @Override
            public void onSubmit(View view, int position, boolean isCorrect) {

            }
        };

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tvTag = itemView.findViewById(R.id.tag_list_view_key);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition());
        }

        public void setItemClickListener(OnItemClickListener itemClickListener){
            this.itemClickListener = itemClickListener;
        }
    }
}
