package ui;

import model.Event;
import model.EventLog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyWindowListener extends WindowAdapter {

    // EFFECTS: print and clear the event log
    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("CLOSED");
        print(EventLog.getInstance());
        ClearLogAction clear = new ClearLogAction();
    }

    //EFFECTS: prints the event log in console
    private void print(EventLog eventLog) {
        String msg = "";
        for (Event next : eventLog) {
            msg = msg + next.toString() + "\n\n";
        }
        System.out.println(msg);

    }

    /**
     * Represents the action to be taken when the user wants to
     * clear the event log.
     */
    private class ClearLogAction extends AbstractAction {
        ClearLogAction() {
            super("Clear log");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            EventLog.getInstance().clear();
        }
    }
}
