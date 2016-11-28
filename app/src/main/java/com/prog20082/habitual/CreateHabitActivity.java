package com.prog20082.habitual;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView.OnItemClickListener;

public class CreateHabitActivity extends AppCompatActivity implements OnItemSelectedListener {

    EditText edtHabitName;
    EditText edtGoal;
    Spinner spnGoalUnits;
    CheckBox chkBadHabit;
    CheckBox chkGoodHabit;
    CheckBox chkNotify;

    private static final int MAIN_ACTIVITY = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_habit);

        //Receive intent from MainActivity
        Intent intent = getIntent();
        //Receive a toast message
        Toast.makeText(getApplicationContext(), "You're on the second page dorks", Toast.LENGTH_LONG).show();

        //Getting all the widgets from activity_create_habit.xml
        edtHabitName = (EditText) findViewById(R.id.edtHabitName);
        edtGoal = (EditText) findViewById(R.id.edtGoal);
        chkBadHabit = (CheckBox) findViewById(R.id.chkBadHabit);
        chkGoodHabit = (CheckBox) findViewById(R.id.chkGoodHabit);


        spnGoalUnits = (Spinner) findViewById(R.id.spnGoalUnits);

        //Added check for notifications
        chkNotify = (CheckBox) findViewById(R.id.chkNotify);
        //Create an ArrayAdapter using the string array and default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.goals_array, android.R.layout.simple_spinner_item);
        //Choose what the list looks like when its selected
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //apply adapter to spinner
        spnGoalUnits.setAdapter(adapter);

        spnGoalUnits.setOnItemSelectedListener(this);


    }

    //notification ID
    public static int MY_NOTIFICATION = 1;

    public void submitHabit(View view) {

        String habitName = edtHabitName.getText().toString();
        String goal = edtGoal.getText().toString();

        //Have to work on spnGoalUnits forgot that mo; onSelected junk

        //Send a new parcelable habit for testing purposes to see if it goes to next screen
        Habit parcelableHabit = new Habit(habitName, true, 3, goal);
        Intent intent = new Intent(this, CreateHabitActivity.class);
        intent.putExtra("habit", parcelableHabit);
        setResult(RESULT_OK, intent);

        //Create a notification if the notification is checked.

        if (chkNotify.isChecked()) {

            NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            //When the pending intent is created, go back to mainActivity
            Intent intentMain =  new Intent(this, MainActivity.class);
            PendingIntent pi = PendingIntent.getActivity(this, 0, intentMain,0);
            Notification notification = new Notification.Builder(this)
                    .setWhen(1000)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setTicker("Habit")
                    .setContentTitle("Did you engage in " + habitName)
                    .setContentIntent(pi)
                    .setAutoCancel(true)
                    .build();

            nm.notify(MY_NOTIFICATION, notification);
        }


    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int pos, long id) {
        parent.getItemAtPosition(pos);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
