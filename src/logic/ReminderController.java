package logic;

import beans.Reminder;
import timerTask.ReminderTimerTask;

import javax.swing.*;
import java.util.*;
import java.util.Timer;

public class ReminderController {
    private static List<Reminder> reminders;

    public ReminderController() {
        this.reminders = new ArrayList<>();
        Reminder reminder = new Reminder(2222, 1, 1, 1, 1, "Test", false);
        this.reminders.add(reminder);
    }


    public static boolean addReminder() throws Exception {
        Reminder reminder = new Reminder();
        reminder = userInputForReminder();
        if (isReminderExists(reminder, (ArrayList<Reminder>) reminders)) {
            throw new Exception("Oops, the reminder is already exists");
        }
        reminders.add(reminder);
        setTimer(reminder);
        return true;
    }

    public void seeAllReminders() {
        Collections.sort(reminders);
        Iterator<Reminder> reminderIterator = reminders.iterator();
        while (reminderIterator.hasNext()) {
            System.out.println(reminderIterator.next());
        }
    }

    private static Reminder setTimer(Reminder reminder) {
        java.util.Timer timer = new Timer();

        TimerTask timerTask;
        if (reminder.isImportant()) {
            timerTask = new ReminderTimerTask(reminder, timer, 3);
            // Tell the timer to run the task every 60 seconds, starting of reminder date
            try {
                timer.scheduleAtFixedRate(timerTask, reminder.getDate().getTime(), 60 * 1000);
            } catch (Exception exception) {
                exception.printStackTrace();
            }

            try {
                // a bit more than 2 minutes delay before canceling the task
                Thread.sleep(60 * 1000 * 2 + 10 * 000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Removing the task
            return reminder;
        }

        else {
            timerTask = new ReminderTimerTask(reminder, timer, 1);
            timer.schedule(timerTask, reminder.getDate().getTime());
            return reminder;
        }

    }


    private static Reminder userInputForReminder() {
        Reminder reminder = new Reminder();

        //          set year
        int year = getYear();
        reminder.setYear(year);

//        set month
        int month = getMonth();
        reminder.setMonth(month);

//        set day
        int day = getDay(reminder);
        reminder.setDay(day);

//        set hour
        int hour = getHour();
        reminder.setHour(hour);

        //        set minutes
        int minutes = getMinutes();
        reminder.setMinute(minutes);

        if (!isDateValid(reminder)) {
            userInputForReminder();
        }

//        set text
        String reminderText = getText();
        reminder.setReminderText(reminderText);

//        set importance
        reminder.setImportant(getImportance());

        return reminder;
    }


    private static boolean getImportance() {
        String userInput = JOptionPane.showInputDialog("Please type 1 if the reminder IS IMPORTANT");
        int importance = 0;
        try {
            importance = Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (importance == 1) {
            return true;
        } else return false;
    }

    private static String getText() {
        return JOptionPane.showInputDialog("Please enter the text of the reminder.");
    }

    private static int getMinutes() {
        int minutes = 0;
        while (minutes > 60 || minutes < 1) {
            String userInput = JOptionPane.showInputDialog("Please enter the minutes of the reminder. The date cannot be in the past");
            try {
                minutes = Integer.parseInt(userInput);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return minutes;
    }

    private static int getHour() {
        int hour = 0;
        while (hour > 24 || hour < 1) {
            String userInput = JOptionPane.showInputDialog("Please enter the hour of the reminder. The date cannot be in the past");
            try {
                hour = Integer.parseInt(userInput);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return hour;
    }

    private static int getMonth() {
        int month = 0;
        while (month > 12 || month < 1) {
            String userInput = JOptionPane.showInputDialog("Please enter the month of the reminder. 0 = January, 11 = December. The date cannot be in the past");
            try {
                month = Integer.parseInt(userInput);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return month;
    }

    private static int getYear() {
        int yearToday = Calendar.getInstance().get(Calendar.YEAR);
        int year = 0;
        while (year < yearToday) {
            String userInput = JOptionPane.showInputDialog("Please enter the year of the reminder. The date cannot be in the past");
            try {
                year = Integer.parseInt(userInput);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return year;
    }

    private static int getDay(Reminder reminder) {
        int day = getDayFromUser();
        if (dayValidation(day, reminder)) {
            return day;
        } else return getDay(reminder);
    }

    private static int getDayFromUser() {
        int day = 0;
        while (day > 31 || day < 1) {
            String userInput = JOptionPane.showInputDialog("Please enter the day of the reminder. The date cannot be in the past");
            try {
                day = Integer.parseInt(userInput);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return day;
    }

    //    VALIDATIONS
    private static boolean isDateValid(Reminder reminder) {
        Date today = Calendar.getInstance().getTime();
        Date reminderDate = new Date(reminder.getYear()-1900, reminder.getMonth(), reminder.getDay(), reminder.getHour(), reminder.getMinute());
        if (reminderDate.getTime() < today.getTime()) {
            return false;
        }
        return true;
    }

    private static boolean dayValidation(int day, Reminder reminder) {
        if (reminder.getMonth() == 4 ||
                reminder.getMonth() == 6 ||
                reminder.getMonth() == 9 ||
                reminder.getMonth() == 11) {
            if (day > 30) {
                return false;
            }
        }
        if (reminder.getMonth() == 2) {
            if (day > 29) {
                return false;
            }
            if (day == 29 && !yearIsLeap(reminder.getYear())) {
                return false;
            }
        }
        return true;
    }

    private static boolean yearIsLeap(int year) {
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                if (year % 400 == 0)
                    return true;
                else
                    return false;
            } else
                return true;
        } else {
            return false;
        }

    }

    private static boolean isReminderExists(Reminder reminder, ArrayList<Reminder> reminders) {
        Iterator<Reminder> reminderIterator = reminders.iterator();
        while (reminderIterator.hasNext()) {
            if (reminderIterator.next().equals(reminder)) {
                return true;
            }
        }

        return false;
    }
}
