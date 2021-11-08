package activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.mydictionary.ListTags;
import com.example.mydictionary.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;

import objects.Word;

public class WordMarkActivity extends AppCompatActivity {

    BufferedWriter bufferedWriter;
    String filename = "vocabulary_blabla.txt";
    BufferedReader br;

    Toolbar toolbar;
    TextView tvKey;
    EditText etTag;
    Button btnConfirm;

    Word word;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_mark);

        //file = new File(getBaseContext().getFilesDir(), "vocabulary_01.txt");

        findViewById();

        intent = getIntent();
        word = (Word) intent.getSerializableExtra("word");
        toolbar.setTitle(word.getKey());
        tvKey.setText(word.getKey());

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tag = etTag.getText().toString();
                if (tag != null && !tag.equals("")) {
                    ListTags.add(tag, word);
                    ListTags.saveToFile(getBaseContext());
                    finish();
                } else Toast.makeText(WordMarkActivity.this, "invalid tag", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void findViewById(){
        toolbar = findViewById(R.id.word_mark_toolbar);
        tvKey = findViewById(R.id.word_mark_key);
        etTag = findViewById(R.id.word_mark_edit_tag);
        btnConfirm = findViewById(R.id.word_mard_button_confirm);
    }

    /*void writeFile(String str) throws IOException {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(getBaseContext().openFileOutput(filename, Context.MODE_PRIVATE));
        } catch (FileNotFoundException e) {
            Log.d("hehe", "Error/writeFile: " + e.getMessage());
        }

        writer.print(str);
        writer.close();
    }*/
}
