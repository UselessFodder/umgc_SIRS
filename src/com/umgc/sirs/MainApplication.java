package com.umgc.sirs;

import view.UI;
import javax.swing.SwingUtilities;

public class MainApplication {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                UI ui = new UI(); // Assuming UI class has a constructor that sets up the GUI.
               // ui.display();     // Assuming UI class has a display method that makes the GUI visible.
            }
        });
    }
}
