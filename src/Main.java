import logic.ReminderController;
import timerTask.ReminderTimerTask;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) throws Exception {
        ReminderController reminderController = new ReminderController();
        showMenu(reminderController);
    }

    private static void showMenu(ReminderController reminderController) throws Exception {
        int userChoice = 0;
        while (userChoice < 1 || userChoice > 3) {
            String userInput = JOptionPane.showInputDialog("Please choose what you want to do : \n" +
                    "1 = add a reminder\n" +
                    "2 = see all the reminders\n" +
                    "3 = exit the program");
            userChoice = Integer.parseInt(userInput);
        }
        if (userChoice == 1) {
            addReminder(reminderController);
            showMenu(reminderController);
        }
        if (userChoice == 2) {
            seeAllTheReminders(reminderController);
            showMenu(reminderController);
        }
        if (userChoice == 3) {
            System.exit(0);
        }
    }

    private static void seeAllTheReminders(ReminderController reminderController) {
        reminderController.seeAllReminders();
    }

    private static void addReminder(ReminderController reminderController) throws Exception {
        if (reminderController.addReminder()) {
            JOptionPane.showMessageDialog(null, "The reminder was added");

        }
    }
}
