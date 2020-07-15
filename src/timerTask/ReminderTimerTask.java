package timerTask;

import beans.Reminder;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class ReminderTimerTask extends TimerTask {

    private Reminder reminder;
    private Timer timer;
    private int count;

    public ReminderTimerTask(Reminder reminder, Timer timer, int count) {
        this.reminder = reminder;
        this.timer = timer;
        this.count = count;
    }

    @Override
    public void run() {
        System.out.println(reminder);
        count--;
        if (count==0) {
            reminder.setWasShown(true);
            timer.cancel();
        }
    }
}
