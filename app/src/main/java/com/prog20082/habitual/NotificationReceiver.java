package com.prog20082.habitual;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import java.sql.Time;

/**
 * Created by prabh on 2016-12-02.
 */

public class NotificationReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {




        Intent in = new Intent(context, TimeService.class);
        context.startService(in);

        String action = intent.getAction();

        if (action != null) {



            if (action == ActionClass.YES_ACTION) {
                Toast.makeText(context, "You Clicked Yes!", Toast.LENGTH_LONG).show();
//                Habit h = new Habit(HabitsDb.NAME, HabitsDb.GOOD, HabitsDb.GOAL, HabitsDb.GOAL_UNITS, HabitsDb.COMPLETE);
//                int streak = h.getStreak();
//                streak = streak + 1;
//                h.setStreak(streak);



            }

            if (action == ActionClass.NO_ACTION) {
                Toast.makeText(context, "You clicked No!", Toast.LENGTH_SHORT).show();
//                Habit h = new Habit(HabitsDb.NAME, HabitsDb.GOOD, HabitsDb.GOAL, HabitsDb.GOAL_UNITS, HabitsDb.COMPLETE);
//                h.setStreak(0);
            }
        }

        else {

            Log.i("null", "null");
        }











    }


}
