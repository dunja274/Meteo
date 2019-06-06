package hr.fer.oop.meteo.util;

import java.util.Calendar;

// Clock class for working with current and past dates
public class Clock {
    private final Calendar clock;

    public Clock() {
        clock = Calendar.getInstance();
    }

    public Clock(int year, int month, int day) {
        clock = Calendar.getInstance();
        this.setDate(year, month, day);
    }

    public Clock(long milis) {
        clock = Calendar.getInstance();
        this.setDateInMillis(milis);
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

    public float getFloatDateInMilis() { return (float) getDateInMillis(); }

    public void setDate(int year, int month, int day) {
        this.clock.set(year, month, day);
    }

    public void setDateInMillis(long milis) {
        this.clock.setTimeInMillis(milis);
    }

    public void addDays(int amount) {
        this.clock.add(Calendar.DATE, amount);
    }

    public String toString() {
        return getYear() + "-" + getMonth() + "-" + getDay();
    }

}
