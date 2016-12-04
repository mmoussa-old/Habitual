package com.prog20082.habitual;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

/**
 * Created by prabh on 2016-12-04.
 */

public class TimeService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        generateNotification();

        return Service.START_NOT_STICKY;
    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    public void generateNotification() {


        Intent intentBR = new Intent(getApplicationContext(), CreateHabitActivity.class);


        PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, intentBR, PendingIntent.FLAG_UPDATE_CURRENT);
        long[] pattern = {500,500,500,500,500,500};


        NotificationCompat.Builder notificationCompBuilder = new NotificationCompat.Builder(getApplicationContext())
                .setContentTitle("Reminder: Habitual")
                .setContentText("Did you engage in your habit? ")
                .setTicker("New Habit")
                .setAutoCancel(true)
                .setVibrate(pattern)
                .setSmallIcon(R.mipmap.ic_launcher);

        notificationCompBuilder.setContentIntent(pi);
        NotificationManager manager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        manager.notify(0, notificationCompBuilder.build());
    }
}
