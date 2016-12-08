package com.prog20082.habitual;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by prabh on 2016-12-03.
 */

public class TimeSettings implements TimePickerDialog.OnTimeSetListener {

    Context context;


    public TimeSettings(Context context) {
        this.context = context;
    }
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {


        Toast.makeText(context, "Time Selected is hour: " + hourOfDay + " minute: " + minute,Toast.LENGTH_LONG).show();
        Intent intent = new Intent(context, TimeService.class);
        intent.putExtra("hour", hourOfDay);
        intent.putExtra("minute", minute);
        context.startActivity(intent);





    }
}
