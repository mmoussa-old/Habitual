package com.prog20082.habitual;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Billy Goat on 11/25/2016.
 */

public class HabitsArrayAdapter extends ArrayAdapter<Habit> {

  private Context context;
  private List<Habit> habits;
  private Habit habit;
  HabitsDb db;



  //Constructor call on Creation
  public HabitsArrayAdapter(Context context, int resource, ArrayList<Habit> objects) {
    super(context, resource, objects);
    this.context = context;
    this.habits = objects;
  }

  //Called when rendering the list
  public View getView(int position, View convertView, ViewGroup parent) {

    //get the habit we are displaying
    habit = habits.get(position);
    //comment yeezy

    //Get the inflater and inflate the xml layout for each item
    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
    View view = inflater.inflate(R.layout.habit_layout, null);

    TextView habitName = (TextView) view.findViewById(R.id.txtHabitName);
    TextView goalUnit = (TextView) view.findViewById(R.id.txtHabitGoals);
    Button deleteButton = (Button) view.findViewById(R.id.btnDeleteHabit);

    deleteButton.setTag(position);

    db = new HabitsDb(context);

    deleteButton.setOnClickListener(
      new Button.OnClickListener() {

        @Override
        public void onClick(View view) {


          //Returns the index of the habit item from the list
          Integer index = (Integer) view.getTag();
          Habit habitToDelete = habits.get(index); //gets habit from list
          //long dbId = habitToDelete.getId();
          db.deleteHabit(habitToDelete);
          habits.remove(index.intValue());
          notifyDataSetChanged();

        }
      }
    );


    //Set habit name and description
    habitName.setText(habit.getName().toString());
    goalUnit.setText(habit.getGoalUnits());

    //Return the view
    return view;

  }

}
