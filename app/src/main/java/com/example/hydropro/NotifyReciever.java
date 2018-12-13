package com.example.hydropro;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class NotifyReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notifyManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        String channelID = "testChannelID";
        Intent newIntent = new Intent(context, MainActivity.class);
        newIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 100, newIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelID);
                .setContentIntent(pendingIntent)
                .setSmallIcon(android.R.drawable.arrow_up_float)
                .setContentTitle("HydroPro Reminder")
                .setContentText("This is your reminder to use a reusable water bottle!")
                .setAutoCancel(true);

        notifyManager.notify(100, builder.build());
    }
}
