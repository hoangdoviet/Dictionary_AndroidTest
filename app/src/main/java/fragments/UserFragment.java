package fragments;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mydictionary.AlarmReceiver;
import com.example.mydictionary.LeinerSystem;
import com.example.mydictionary.ListTags;
import com.example.mydictionary.ListWords;
import com.example.mydictionary.R;
import com.example.mydictionary.UserWords;

import java.sql.Time;
import java.util.Calendar;
import java.util.concurrent.ScheduledExecutorService;

import activities.AddWordActivity;
import activities.ChangeQuestActivity;
import activities.DoQuestActivity;
import activities.MainActivity;

public class UserFragment extends Fragment {
    private View rootView;

    private Button add_btn;
    private Button leitner_btn;
    private Button changeq_btn;
    private Button complete_btn;
    private Button setting_btn;
    private Button setcolor_btn;
    private Button nextday_btn;
    private Button boxclear_btn;
    private Button setquest_btn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_user, container, false);
        initView();
        onClick();
        return rootView;

    }
    private void onClick(){
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getActivity(), AddWordActivity.class);
                startActivity(intent);
            }
        });
        leitner_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (changeq_btn.getVisibility() == View.GONE) {
                    changeq_btn.setVisibility(View.VISIBLE);
                    complete_btn.setVisibility(View.VISIBLE);
                }
                else {
                    changeq_btn.setVisibility(View.GONE);
                    complete_btn.setVisibility(View.GONE);
                }
            }
        });
        changeq_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getActivity(), ChangeQuestActivity.class);
                startActivity(intent);
            }
        });
        complete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getActivity(), DoQuestActivity.class);
                startActivity(intent);
            }
        });
        setting_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (setcolor_btn.getVisibility() == View.GONE) {
                    setcolor_btn.setVisibility(View.VISIBLE);
                    nextday_btn.setVisibility(View.VISIBLE);
                    boxclear_btn.setVisibility(View.VISIBLE);
                    setquest_btn.setVisibility(View.VISIBLE);
                }
                else {
                    setcolor_btn.setVisibility(View.GONE);
                    nextday_btn.setVisibility(View.GONE);
                    boxclear_btn.setVisibility(View.GONE);
                    setquest_btn.setVisibility(View.GONE);
                }
            }
        });
        nextday_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextday_btn.setText("Next The Day(Current Day: " + LeinerSystem.nextDay() + ")");
            }
        });
        boxclear_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LeinerSystem.clearFile(getContext());
                LeinerSystem.clearBox();
                LeinerSystem.getQuest().clear();
                LeinerSystem.readData(getContext());
                boxclear_btn.setText("Done");
            }
        });
        setquest_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LeinerSystem.add("a b c /'eibi:'si:/");
                LeinerSystem.add("abaddon /ə'bædən/");
                LeinerSystem.add("abdomen /'æbdəmen/");
                LeinerSystem.add("filing /'failiɳ/");
                LeinerSystem.add("shaped /ʃeipt/");
                LeinerSystem.add("invoke /in'vouk/");

            }
        });
    }

    private void initView(){
        add_btn =  rootView.findViewById(R.id.user_addword_btn);
        leitner_btn =  rootView.findViewById(R.id.user_leiner_btn);
        changeq_btn =  rootView.findViewById(R.id.user_changeQ_btn);
        complete_btn =  rootView.findViewById(R.id.user_complete_btn);
        setting_btn =  rootView.findViewById(R.id.user_setting_btn);
        setcolor_btn = rootView.findViewById(R.id.user_colorset_btn);
        nextday_btn = rootView.findViewById(R.id.user_nextday_btn);
        boxclear_btn = rootView.findViewById(R.id.user_boxclear_btn);
        setquest_btn = rootView.findViewById(R.id.user_setquest_btn);
    }
}