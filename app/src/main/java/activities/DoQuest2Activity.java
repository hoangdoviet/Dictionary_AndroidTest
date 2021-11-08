package activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.mydictionary.ListQuests;
import com.example.mydictionary.ListWords;
import com.example.mydictionary.R;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import adapters.QuestAdapter;
import fragments.FinishDialog;
import interfaces.OnItemClickListener;
import objects.Word;

public class DoQuest2Activity extends AppCompatActivity {

    RecyclerView recyclerView;
    QuestAdapter mAdapter;
    RecyclerView.LayoutManager layoutManager;

    int numQuest;

    String filename = "test.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_quest2);

        findViewById();

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        recyclerView.hasFixedSize();
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new QuestAdapter(ListQuests.getAll(), getBaseContext());
        numQuest = mAdapter.getItemCount();
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Word word = mAdapter.get(position);
                Intent intent = new Intent(getBaseContext(), WordDetailActivity.class);
                intent.putExtra("word", word);
                startActivity(intent);
            }

            @Override
            public void onSubmit(View view, int position, boolean isCorrect) {
            }
        });

        mAdapter.setSubmitListener(new OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
            }

            @Override
            public void onSubmit(View view, int position, boolean isCorrect) {
                if(isCorrect){
                    mAdapter.remove(position);
                    mAdapter.notifyDataSetChanged();
                } else mAdapter.incMistake();
                if(mAdapter.getItemCount() == 0){
                    Toast.makeText(DoQuest2Activity.this, "you finish the quest with " +  mAdapter.getMistake() + " mistake!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        recyclerView.setAdapter(mAdapter);
    }

    private void findViewById(){
        recyclerView = findViewById(R.id.activity_do_quest2_listquest);
    }

    ///////////////////////////////////////////////
}
