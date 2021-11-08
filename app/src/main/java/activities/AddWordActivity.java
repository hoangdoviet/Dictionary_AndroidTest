package activities;

import android.annotation.SuppressLint;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.mydictionary.ListTags;
import com.example.mydictionary.ListWords;
import com.example.mydictionary.R;
import com.example.mydictionary.UserWords;

import objects.Meaning;
import objects.Word;


public class AddWordActivity extends AppCompatActivity {
    Word word;
    Meaning meaning;

    Button button_submit;
    Button button_clear;
    EditText key;
    EditText type;
    EditText meanin;
    EditText example;
    Toolbar toolbar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);
        initView();
        toolbar.setTitle("Hello World!");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        onClick();
    }

    private void onClick(){
        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key_input = key.getText().toString();
                String type_input = type.getText().toString();
                String meaning_input = meanin.getText().toString();
                String example_input = example.getText().toString();
                if(!key_input.equals("") && key_input!= null){
                    meaning = new Meaning();
                    meaning.setType(" " + type_input);
                    meaning.addMeaning("- "+ meaning_input + "\n=" + example_input);
                    word = new Word(key_input.trim().toLowerCase());
                    UserWords.add(word, meaning);
                    ListTags.saveToFile(getBaseContext());
                    Toast.makeText(AddWordActivity.this,"Your word is added successfully!", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else Toast.makeText(AddWordActivity.this, "Word key must have at least a character!", Toast.LENGTH_SHORT).show();
            }
        });
        button_clear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                key.setText(null);
                type.setText(null);
                meanin.setText(null);
                example.setText(null);
            }
        });
    }

    private void initView(){
        button_clear = findViewById(R.id.activity_addword_clear_btn);
        key = findViewById(R.id.activity_addword_key_edit);
        type = findViewById(R.id.activity_addword_wordtype_edit);
        meanin = findViewById(R.id.activity_addword_meaning_edit);
        example = findViewById(R.id.activity_addword_example_edit);
        toolbar = findViewById(R.id.activity_addword_toolbar);
        button_submit = findViewById(R.id.activity_addword_submit_btn);
    }
}