package activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import com.example.mydictionary.AlarmReceiver;
import com.example.mydictionary.ListAlarms;
import com.example.mydictionary.R;

import java.util.Calendar;

import objects.Word;

public class AlarmActivity extends AppCompatActivity {

    TimePicker timePicker;
    Button btnConfirm;
    Toolbar toolbar;
    Intent intent;
    Word word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        intent = getIntent();
        findViewById();
        word = (Word) intent.getSerializableExtra("word");

        toolbar.setTitle(word.getKey());

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int alarmHour = timePicker.getHour();
                int alarmMinute = timePicker.getMinute();

                AlarmManager alarmManager = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
                Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
                //intent.setAction("alarm action");
                intent.putExtra("word", word.getKey());

                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.set(Calendar.HOUR_OF_DAY, alarmHour);
                calendar.set(Calendar.MINUTE, alarmMinute);

                long timeInMillis = calendar.getTimeInMillis();

                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, timeInMillis,AlarmManager.INTERVAL_DAY, pendingIntent);
                ListAlarms.add(word);
                ListAlarms.writeFile(getBaseContext());
                finish();
            }
        });
    }

    void findViewById(){
        timePicker = findViewById(R.id.activity_alarm_timepicker);
        btnConfirm = findViewById(R.id.activity_alarm_confirm);
        toolbar = findViewById(R.id.activity_alarm_toolbar);
    }
}
