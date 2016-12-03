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

public class TimePickerFragment extends DialogFragment  {




    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        //Using the current time as the default values
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minutes = cal.get(Calendar.MINUTE);
        cal.set(Calendar.HOUR, hour);
        cal.set(Calendar.MINUTE, minutes);
        TimePickerDialog dialog;
        TimeSettings timeSettings = new TimeSettings(getActivity());
        dialog = new TimePickerDialog(getActivity(), timeSettings,hour,minutes, DateFormat.is24HourFormat(getActivity()));





        return dialog;


    }


}
