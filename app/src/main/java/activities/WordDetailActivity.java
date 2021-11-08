package activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mydictionary.LeinerSystem;
import com.example.mydictionary.ListQuests;
import com.example.mydictionary.R;

import adapters.WordDetailAdapter;
import objects.Word;
import objects.WordQuest;

public class WordDetailActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView tvKey;
    TextView tvMeanings;
    Word word;
    Intent intent;
    ViewGroup vgWord;

    RecyclerView recyclerView;
    WordDetailAdapter mAdapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_detail);

        findViewById();

        intent = getIntent();
        word =(Word) intent.getSerializableExtra("word");

        tvKey.setText(word.getKey());
        toolbar.setTitle(word.getKey());
        setSupportActionBar(toolbar);

        recyclerView.hasFixedSize();
        layoutManager = new LinearLayoutManager(getBaseContext());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new WordDetailAdapter(word.getMeanings());
        recyclerView.setAdapter(mAdapter);

        ActionBar actionBar = getSupportActionBar();
    }

    private void findViewById(){
        tvKey = findViewById(R.id.detail_key);
        toolbar = findViewById(R.id.detail_toolbar);
        vgWord = findViewById(R.id.activity_word_detail_main_layout);
        recyclerView = findViewById(R.id.detail_list);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.word_detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        /*Intent intent = new Intent(getBaseContext(), WordMarkActivity.class);
        intent.putExtra("word", word);
        startActivity(intent);*/

        switch (item.getItemId()){
            case R.id.mark_word_item:
                Intent intent = new Intent(getBaseContext(), WordMarkActivity.class);
                intent.putExtra("word", word);
                startActivity(intent);
                break;
            case R.id.memorize_word_item:
                Intent intent1 = new Intent(getBaseContext(), AlarmActivity.class);
                intent1.putExtra("word", word);
                startActivity(intent1);
                break;
            case R.id.add_to_quest_item:
                WordQuest wordQuest = new WordQuest(word, false);
                ListQuests.add(wordQuest, getBaseContext());
                break;
        }
        return true;
    }
}
