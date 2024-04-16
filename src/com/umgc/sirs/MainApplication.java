package com.umgc.sirs;

import view.UI;
import controller.Controller;
import javax.swing.SwingUtilities;

public class MainApplication {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	Controller controller = new Controller();
                UI ui = new UI(controller); //startup UI
            }
        });
    }
}
