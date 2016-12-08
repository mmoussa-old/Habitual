package com.prog20082.habitual;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Random;

/**
 * Created by prabh on 2016-12-04.
 */ //test

public class TimeService extends Service {

    public static final String YES_ACTION = "com.prog20082.habitual.YES";
    public static final String STOP_ACTION = "com.prog20082.habitual.NO";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        generateNotification();


        return Service.START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void generateNotification() {

        Intent intentBR = new Intent(getApplicationContext(), CreateHabitActivity.class);

        //Random generator will generate notification for each habit
        Random random = new Random();
        int m = random.nextInt(9999 - 1000) + 1000;

        //Yes Intent
        Intent yesIntent = new Intent();
        yesIntent.setAction(ActionClass.YES_ACTION);
        PendingIntent yesPi = PendingIntent.getBroadcast(getApplicationContext(), 1, yesIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        //No Intent
        Intent noIntent = new Intent();
        noIntent.setAction(ActionClass.NO_ACTION);
        PendingIntent noPi = PendingIntent.getBroadcast(getApplicationContext(), 1, noIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, intentBR, PendingIntent.FLAG_UPDATE_CURRENT);

        //Vibration Pattern
        long[] pattern = {500,500,500,500,500,500};

        NotificationCompat.Builder notificationCompBuilder = new NotificationCompat.Builder(getApplicationContext())
                .setContentTitle("Reminder: Habitual")
                .setContentText("Did you engage in your habit? ")
                .setTicker("New Habit")
                .setVibrate(pattern)
                .setAutoCancel(true)
                .addAction(R.mipmap.check, "Yes", yesPi)
                .addAction(R.mipmap.cross, "No", noPi)
                .setSmallIcon(R.mipmap.ic_launcher);

        notificationCompBuilder.setContentIntent(pi);
        NotificationManager manager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);


        manager.notify(m, notificationCompBuilder.build());
    }
}
