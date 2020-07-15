package beans;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class Reminder implements Comparable<Reminder> {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private String reminderText;
    private boolean isImportant;
    private boolean wasShown;

    public Reminder(int year, int month, int day, int hour, int minute, String reminderText, boolean isImportant) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.reminderText = reminderText;
        this.isImportant = isImportant;
        this.wasShown = false;
    }

    public Reminder() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reminder)) return false;
        Reminder reminder = (Reminder) o;
        return getYear() == reminder.getYear() &&
                getMonth() == reminder.getMonth() &&
                getDay() == reminder.getDay() &&
                getHour() == reminder.getHour() &&
                getMinute() == reminder.getMinute() &&
                Objects.equals(getReminderText(), reminder.getReminderText());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getYear(), getMonth(), getDay(), getHour(), getMinute(), getReminderText());
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public String getReminderText() {
        return reminderText;
    }

    public void setReminderText(String reminderText) {
        this.reminderText = reminderText;
    }

    public boolean isImportant() {
        return isImportant;
    }

    public void setImportant(boolean important) {
        isImportant = important;
    }

    public boolean isWasShown() {
        return wasShown;
    }

    public void setWasShown(boolean wasShown) {
        this.wasShown = wasShown;
    }

    public Calendar getDate() {
        Calendar date = Calendar.getInstance();
        date.set(Calendar.DAY_OF_MONTH, day);
        date.set(Calendar.MONTH, month);
        date.set(Calendar.YEAR, year);
        date.set(Calendar.HOUR_OF_DAY, hour);
        date.set(Calendar.MINUTE, minute);

        return date;
    }

    @Override
    public String toString() {
        return "Reminder{" +
                "Date: " + day + "." + month + "." + year +
                ", time: " + hour +
                ":" + minute +
                ", reminderText='" + reminderText + '\'' +
                ", isImportant=" + isImportant +
                ", wasShown=" + wasShown +
                '}';
    }

    @Override
    public int compareTo(Reminder reminder) {
        return Long.compare(getDate().getTime().getTime(), reminder.getDate().getTime().getTime());
    }

}

