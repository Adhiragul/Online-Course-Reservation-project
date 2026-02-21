package app;

import gui.MainFrame;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        // Use the system look and feel for native appearance
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        // Launch the GUI on the Event Dispatch Thread
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
