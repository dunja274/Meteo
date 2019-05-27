package hr.fer.oop.meteo.util;

import java.util.Calendar;

// Clock class for working with current and past dates
public class Clock {
    private final Calendar clock;

    public Clock() {
        clock = Calendar.getInstance();
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

    public long getDateInMillis() {
        return this.clock.getTimeInMillis();
    }

    public void setDate(int year, int month, int day) {
        this.clock.set(year, month, day);
    }

}
