package com.example.anew;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import java.util.Calendar;

public class notificationbroadcast extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
      //  Toast.makeText(context, "Alarm Set1"+ Calendar.getInstance().getTime().toString() , Toast.LENGTH_SHORT).show();
       // NotificationCompat.Builder builder1=new NotificationCompat.Builder(context,"notify");
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context,"notify")
                        .setSmallIcon(R.drawable.icon)
                        .setContentTitle("New Solts are Updated")
                        .setContentText("Tap to check!")
                        .setAutoCancel(true)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Intent notificationIntent = new Intent(context, MainActivity.class);
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }
}
