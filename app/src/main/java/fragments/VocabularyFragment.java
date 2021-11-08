package fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import activities.HashtagActivity;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.example.mydictionary.ListTags;
import com.example.mydictionary.R;

import java.util.ArrayList;
import java.util.List;

import adapters.TagListAdapter;
import interfaces.OnItemClickListener;
import objects.Hashtag;

public class VocabularyFragment extends Fragment {

    RecyclerView recyclerView;
    TagListAdapter mAdapter;
    RecyclerView.LayoutManager layoutManager;
    FloatingSearchView searchView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_vocab, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchView = view.findViewById(R.id.fragment_vocab_searchview);
        recyclerView = view.findViewById(R.id.fragment_vocab_listtags);

        recyclerView.hasFixedSize();
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        setRecyclerView(ListTags.getAll());

        searchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {
                String sg = searchSuggestion.getBody();
                sg = sg.toLowerCase();
                List<Hashtag> list = new ArrayList<>();
                int size = ListTags.size();
                for(int i = 0; i < size; i++) {
                    String tag = ListTags.get(i).getTag();
                    tag = tag.toLowerCase();
                    if (tag.startsWith(sg)) list.add(ListTags.get(i));
                }
                setRecyclerView(list);
                searchView.clearSearchFocus();
            }

            @Override
            public void onSearchAction(String currentQuery) {
                int size = ListTags.size();
                currentQuery = currentQuery.toLowerCase();
                List<Hashtag> list = new ArrayList<>();
                for(int i = 0; i < size; i++){
                    String tag = ListTags.get(i).getTag();
                    tag = tag.toLowerCase();
                    if(tag.startsWith(currentQuery)) list.add(ListTags.get(i));
                }
                setRecyclerView(list);
            }
        });

        searchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, String newQuery) {

                newQuery = newQuery.toLowerCase();

                List<SearchSuggestion> list = new ArrayList<>();
                int size = ListTags.size();
                for(int i = 0;i < size; i++){
                    final String tag = ListTags.get(i).getTag();
                    String tag2 = tag.toLowerCase();
                    if(tag2.startsWith(newQuery)) list.add(new SearchSuggestion() {

                        String body = tag;

                        @Override
                        public String getBody() {
                            return body;
                        }

                        @Override
                        public int describeContents() {
                            return 0;
                        }

                        @Override
                        public void writeToParcel(Parcel dest, int flags) {

                        }
                    });
                }
                searchView.swapSuggestions(list);
            }
        });
    }

    @Override
    public void onResume() {
        ListTags.readData(getContext());
        mAdapter = new TagListAdapter(ListTags.getAll());
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getContext(), HashtagActivity.class);
                intent.putExtra("hashtag", ListTags.get(position));
                startActivity(intent);
            }

            @Override
            public void onSubmit(View view, int position, boolean isCorrect) {

            }
        });
        recyclerView.setAdapter(mAdapter);
        super.onResume();
        super.onResume();
    }

    void setRecyclerView(List<Hashtag> list){
        mAdapter = new TagListAdapter(list);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getContext(), HashtagActivity.class);
                intent.putExtra("hashtag", mAdapter.get(position));
                startActivity(intent);
            }

            @Override
            public void onSubmit(View view, int position, boolean isCorrect) {

            }
        });
        recyclerView.setAdapter(mAdapter);
    }
}