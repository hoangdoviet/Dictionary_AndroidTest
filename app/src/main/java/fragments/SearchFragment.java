package fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mydictionary.R;
import activities.WordDetailActivity;

import java.util.List;

import adapters.SearchListAdapter;
import interfaces.OnItemClickListener;
import objects.Word;

public class SearchFragment extends Fragment {

    RecyclerView recyclerView;
    SearchListAdapter mAdapter;
    RecyclerView.LayoutManager layoutManager;
    TextView tvLookUp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.list_search_word);
        tvLookUp = view.findViewById(R.id.tvLookup);
        tvLookUp.setVisibility(View.VISIBLE);
    }

    public void search(final List<Word> listWords){
        tvLookUp.setVisibility(View.GONE);
        recyclerView.hasFixedSize();
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new SearchListAdapter(listWords);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getContext(), WordDetailActivity.class);
                Word word = listWords.get(position);
                intent.putExtra("word", word);
                startActivity(intent);
            }

            @Override
            public void onSubmit(View view, int position, boolean isCorrect) {

            }
        });

        recyclerView.setAdapter(mAdapter);
    }
}
