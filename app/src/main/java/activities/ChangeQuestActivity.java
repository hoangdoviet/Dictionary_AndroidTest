package activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mydictionary.LeinerSystem;
import com.example.mydictionary.R;


public class ChangeQuestActivity extends AppCompatActivity {

    Button searchword_btn;
    Button add_btn;
    EditText add_txt;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_quest);
        initView();
        onClick();
    }
    private void initView(){
        searchword_btn = findViewById(R.id.button_searchword);
        add_btn = findViewById(R.id.button_add);
        add_txt = findViewById(R.id.edit_add);
    }

    private void onClick() {
        searchword_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ChangeQuestActivity.this, LeinerSystem.showBox()+LeinerSystem.showQuest(), Toast.LENGTH_SHORT).show();
            }
        });
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key_input = add_txt.getText().toString();
                if(!key_input.equals("") && key_input!= null){
                    LeinerSystem.add(key_input);
                    LeinerSystem.saveToFile(getBaseContext());
                }
                else Toast.makeText(ChangeQuestActivity.this, "Word key must have at least a character!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}


