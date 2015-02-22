package core;

/*
CoinInformer 1.0.4
Author: Gush Vadim Vadimovich
Last update: 19.02.2015

(C) Copyright Gush Vadim Vadimovich 2015 year. All right reserved.
 */

import javax.swing.*;

public class Main extends JFrame {

    public static Interface inter;

    public static void main(String[] args) {

        // Loading theme for Interface

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {}

        // Create Interface

        inter = new Interface();
        inter.input();

        //Updating interface

        long time;
        long lastTime = 0;

        while (true) {
            time = System.currentTimeMillis();
            if (time > lastTime + 2000) {
                lastTime = System.currentTimeMillis();
                inter.update();
            }
        }
    }

}