package com.prog20082.habitual;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

/**
 * Created by prabh on 2016-12-02.
 */

public class NotificationPublisher extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        Intent in = new Intent(context, TimeService.class);
        context.startService(in);

//        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//
//        Intent intentBR = new Intent(context, MainActivity.class);
//
//
//        PendingIntent pi = PendingIntent.getActivity(context, 0, intentBR, PendingIntent.FLAG_UPDATE_CURRENT);
//        long[] pattern = {500,500,500,500,500,500,500,500,500};
//
//
//        NotificationCompat.Builder notificationCompBuilder = new NotificationCompat.Builder(context)
//                .setContentTitle("Reminder: Habitual")
//                .setContentText("Did you engage in your habit? ")
//                .setTicker("New Habit")
//                .setAutoCancel(true)
//                .setVibrate(pattern)
//                .setSmallIcon(R.mipmap.ic_launcher);
//
//        notificationCompBuilder.setContentIntent(pi);
//
//        manager.notify(0, notificationCompBuilder.build());


    }
}
