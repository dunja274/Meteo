package hr.fer.oop.meteo.util;

import java.util.Calendar;

// Clock class for working with current and past dates
public class Clock {
    private Calendar clock;

    public Clock() {
        clock = Calendar.getInstance();
    }

    public Clock(int year, int month, int day) {
        clock = Calendar.getInstance();
        clock.set(year, month, day);
    }

    public int getDay() {
        return this.clock.get(Calendar.DAY_OF_MONTH);
    }

    public int getMonth() {
        return this.clock.get(Calendar.MONTH);
    }

    public int getYear() {
        return this.clock.get(Calendar.YEAR);
    }

    public long getDateInMilis() {
        return this.clock.getTimeInMillis();
    }

    public void setDate(int year, int month, int day) {
        this.clock.set(year, month, day);
    }
}
