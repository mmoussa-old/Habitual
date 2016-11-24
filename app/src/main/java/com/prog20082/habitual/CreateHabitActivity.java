package com.prog20082.habitual;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class CreateHabitActivity extends AppCompatActivity {

  EditText edtHabitName;
  EditText edtGoal;
  Spinner spnGoalUnits;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_habit);

      //Receive intent from MainActivity
      Intent intent = getIntent();

      //Receive a toast message
      Toast.makeText(getApplicationContext(), "You're on the second page dorks", Toast.LENGTH_LONG).show();

    }

    public void submitHabit(View view) {

      //Getting all the widgets from activity_create_habit.xml
      edtHabitName = (EditText) findViewById(R.id.edtHabitName);



    }
}
