package core;

/*
CoinInformer 1.0.4
Author: Gush Vadim Vadimovich
Last update: 19.02.2015

(C) Copyright Gush Vadim Vadimovich 2015 year. All right reserved.
 */

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Graphic extends JPanel {

    private static Random random = new Random();

    private static float[] rate = new float[37];
    private static float width = 20;

    private static float size = 100;
    private static float minus = 113;

    public void paint(Graphics g) {
        super.paint(g);
        g.drawString("", 5, 15);

        g.setColor(Color.red);
        g.drawLine(0, 100, 500, 100);

        g.setColor(Color.green);
        for (int counter = 0; counter < rate.length; counter++) {
            //g.drawLine();
        }

    }

    public static void addNew(float value) {
        for (int counter = 0; counter < 49; counter++) {
            rate[counter] = rate[counter + 1];
        }
        rate[49] = value;
    }

}
