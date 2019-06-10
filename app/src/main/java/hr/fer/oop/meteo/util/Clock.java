package hr.fer.oop.meteo.util;

import java.util.Calendar;

// Clock class for working with current and past dates
public class Clock {
    private final Calendar clock;

    public Clock() {
        clock = Calendar.getInstance();
    }

    public Clock(Integer year, Integer month, Integer day) {
        clock = Calendar.getInstance();
        this.setDate(year, month, day);
    }

    public Clock(long milis) {
        clock = Calendar.getInstance();
        this.setDateInMillis(milis);
    }

    public Clock(Clock c1) {
        this.clock = Calendar.getInstance();
        this.setDate(c1.getYear(), getDay(), getMonth());
    }

    public Integer getDay() {
        return this.clock.get(Calendar.DAY_OF_MONTH);
    }

    public Integer getMonth() {
        return this.clock.get(Calendar.MONTH);
    }

    public Integer getYear() {
        return this.clock.get(Calendar.YEAR);
    }

    public long getDateInMillis() { return this.clock.getTimeInMillis(); }

    public void setDate(Integer year, Integer month, Integer day) {
        this.clock.set(year, month, day);
    }

    public void setDateInMillis(long milis) {
        this.clock.setTimeInMillis(milis);
    }

    public void addDays(Integer amount) {
        this.clock.add(Calendar.DATE, amount);
    }

    public String toString() {
        String year = getYear().toString();
        Integer monthInt = getMonth() + 1;
        String month = monthInt.toString();
        if(month.length() == 1) month = "0" + month;
        String day = getDay().toString();
        if(day.length() == 1) day = "0" + day;
        return year + "-" + month + "-" + day;
    }

}
