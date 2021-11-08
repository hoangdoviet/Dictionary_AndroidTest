package activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mydictionary.LeinerSystem;
import com.example.mydictionary.ListWords;
import com.example.mydictionary.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import adapters.QuestKeyListAdapter;
import adapters.SearchListAdapter;
import interfaces.OnItemClickListener;
import objects.QuestItem;

public class DoQuestActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    QuestKeyListAdapter mAdapter;
    RecyclerView.LayoutManager layoutManager;
    TextView noquest_tv;
    Button save_btn;

    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_quest);
        makeQuest();
        init();
        showText();
        onClick();
    }
    private void makeQuest(){
        if (LeinerSystem.isNewDay()) {
            LeinerSystem.catchNewDay();
            LeinerSystem.makeDailyQuest();
        }
    }


    private void init(){
        noquest_tv = findViewById(R.id.activity_do_quest_noquest_tv);
        recyclerView = findViewById(R.id.activity_do_quest_listkey);
        save_btn = findViewById(R.id.activity_do_quest_save_btn);

        layoutManager = new LinearLayoutManager(getBaseContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.hasFixedSize();
        mAdapter = new QuestKeyListAdapter(LeinerSystem.getQuest());
        recyclerView.setAdapter(mAdapter);
    }

    private List<QuestItem> customList(){
        List<QuestItem> list = new ArrayList<>();
        list.add(new QuestItem(100));
        list.add(new QuestItem(200));
        return list;
    }
    private void onClick(){
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onResume();
                LeinerSystem.saveToFile(getBaseContext());
            }
        });
    }

    private void showText(){
        if (mAdapter.getItemCount() == 0) {
            noquest_tv.setVisibility(View.VISIBLE);
            save_btn.setVisibility(View.GONE);
        }
        else if (noquest_tv.getVisibility() == View.VISIBLE) {
            noquest_tv.setVisibility(View.GONE);
            save_btn.setVisibility(View.VISIBLE);
        }
    }

}
