package com.prog20082.habitual;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final int CREATE_HABIT_ACTIVITY = 992;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addHabit(View view) {

        Intent intent = new Intent(this, CreateHabitActivity.class);
        startActivityForResult(intent, CREATE_HABIT_ACTIVITY);

    }
}
