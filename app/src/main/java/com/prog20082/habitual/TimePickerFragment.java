package com.prog20082.habitual;


import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.app.DialogFragment;
import android.app.Dialog;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by prabh on 2016-12-02.
 */

public class TimePickerFragment extends DialogFragment  implements TimePickerDialog.OnTimeSetListener{




    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {



        //Using the current time as the default values
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));


    }


    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {


        Toast.makeText(getActivity(), "Time SET BRUH: " + hourOfDay + " minute: " + minute,Toast.LENGTH_LONG).show();

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR, hourOfDay);
        cal.set(Calendar.MINUTE, minute);
        Intent intent = new Intent(getActivity(),MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("hour", hourOfDay);
        bundle.putInt("minute", minute);

        intent.putExtras(bundle);
        startActivity(intent);





    }
}


