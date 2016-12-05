package com.prog20082.habitual;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.provider.AlarmClock;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity  {

    private static final int CREATE_HABIT_ACTIVITY = 992;

    ListView habitsListView;
    ArrayList<Habit> habitsArray;
    ArrayAdapter<Habit> adapter;
    TextView txtName;
    TextView txtGoals;
    Bundle b;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        habitsListView = (ListView) findViewById(R.id.habitsListView);
        txtName = (TextView) findViewById(R.id.txtName);
        txtGoals = (TextView) findViewById(R.id.txtGoals);
        habitsArray = new ArrayList<Habit>();

        //get intent data from TimePickerFragment
        Intent in = getIntent();
        Calendar cal = Calendar.getInstance();
        int h = in.getIntExtra("hour",11);
        int m = in.getIntExtra("minute",05);




        Toast.makeText(this, "Content Received: " + h + " " + m , Toast.LENGTH_LONG).show();
        cal.set(Calendar.HOUR_OF_DAY, h);
        cal.set(Calendar.MINUTE, m);




        //go to reciever
        Intent timeIntent = new Intent(this, NotificationPublisher.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0,timeIntent , PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarm.set(AlarmManager.RTC, cal.getTimeInMillis(), pendingIntent);

    }






    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Toast.makeText(this, "Coming from the createHabit Page", Toast.LENGTH_LONG).show();


        if (resultCode == RESULT_OK) {
            b = data.getExtras();
            if (b != null) {
                Habit h = data.getParcelableExtra("habit");
                txtName.setText(h.getName().toString());
                txtGoals.setText(h.getGoalUnits().toString());
                //Habit h = (Habit) data.getParcelableExtra("habit");
                habitsArray.add(h);
                habitsListView.setAdapter(adapter);
            }
        }






    }



    public void addHabit(View view) {

        Intent intent = new Intent(this, CreateHabitActivity.class);
        startActivityForResult(intent, CREATE_HABIT_ACTIVITY);


    }



}
