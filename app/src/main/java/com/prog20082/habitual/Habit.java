package com.prog20082.habitual;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mmous on 11/22/2016.
 */
public class Habit implements Parcelable{

    private long id;
    private String name;
    private boolean good;
    private int goal;
    private String goalUnits;
    private boolean complete;

    @Override
    public void writeToParcel(Parcel dest, int flags) {
      dest.writeLong(id);
      dest.writeString(name);
      dest.writeByte((byte) (good ? 1 : 0));
      dest.writeInt(goal);
      dest.writeString(goalUnits);
      dest.writeByte((byte) (complete ? 1 : 0));
    }

    public Habit(String name, boolean good, int goal, String goalUnits) {
        this.name = name;
        this.good = good;
        this.goal = goal;
        this.goalUnits = goalUnits;
        complete = false;
    }

    protected Habit(Parcel in) {
        id = in.readLong();
        name = in.readString();
        good = in.readByte() != 0;
        goal = in.readInt();
        goalUnits = in.readString();
        complete = in.readByte() != 0;
    }

    public static final Creator<Habit> CREATOR = new Creator<Habit>() {
        @Override
        public Habit createFromParcel(Parcel in) {
            return new Habit(in);
        }

        @Override
        public Habit[] newArray(int size) {
            return new Habit[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGood() {
        return good;
    }

    public void setGood(boolean good) {
        this.good = good;
    }

    public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public String getGoalUnits() {
        return goalUnits;
    }

    public void setGoalUnits(String goalUnits) {
        this.goalUnits = goalUnits;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    @Override
    public int describeContents() {
        return 0;
    }


}
