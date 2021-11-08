package fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mydictionary.ListQuests;
import com.example.mydictionary.R;

import activities.AddWordActivity;
import activities.DoQuest2Activity;
import objects.WordQuest;

public class User2Fragment extends Fragment {

    Button btnAddWord;
    Button btnQuest;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnAddWord = view.findViewById(R.id.fragment_user2_btn_addword);
        btnQuest = view.findViewById(R.id.fragment_user2_btn_quest);

        btnAddWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getActivity(), AddWordActivity.class);
                startActivity(intent);
            }
        });

        btnQuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListQuests.readFile(getContext());
                startActivity(new Intent(getContext(), DoQuest2Activity.class));
            }
        });
    }


}
