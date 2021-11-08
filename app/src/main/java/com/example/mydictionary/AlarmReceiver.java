package com.example.mydictionary;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import objects.Word;

public class AlarmReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d("hehehe", "ALARM");
        Toast.makeText(context, "MAKE NOTIFICATION!", Toast.LENGTH_LONG).show();
        Bundle wordBundle = intent.getExtras();
//        Word word =(Word) intent.getSerializableExtra("word");
       // assert wordBundle != null;
        String word = intent.getStringExtra("word");
        createNotificationChannel(context);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "noti_bookmark")
                .setSmallIcon(R.drawable.ic_vocab_black_24dp)
                .setContentTitle(word)
                .setContentText(word)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Learn your words"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(1, builder.build());
    }

    void createNotificationChannel(Context context){
        NotificationManager notificationManager =(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationChannel notificationChannel = new NotificationChannel("noti_bookmark", "channel_1", NotificationManager.IMPORTANCE_LOW);
        notificationChannel.enableLights(true);
        notificationChannel.setLightColor(Color.BLUE);
        notificationChannel.enableVibration(true);
        notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
        notificationManager.createNotificationChannel(notificationChannel);
    }
}
