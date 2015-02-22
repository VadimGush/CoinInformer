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

    private static float[] min = new float[40];
    private static float[] max = new float[40];

    public void paint(Graphics g) {
        super.paint(g);
        g.drawString("", 5, 15);

        // Draw min
        g.setColor(Color.red);
        for (int counter = 0; counter < min.length - 1; counter++) {
            int x1 = counter * (500 / min.length);
            int x2 = (counter + 1) * (500 / min.length);

            System.out.println((min[counter] - (int)min[counter]) * 25);

            int y1 = (int)-( (min[counter] - (int)min[counter]) * 50 );
            int y2 = (int)-( (min[counter + 1] - (int)min[counter + 1]) * 50 );

            if (min[counter] != 0 & min[counter + 1] != 0) g.drawLine(x1 + 40, y1 + 75, x2 + 40, y2 + 75);
        }

        // Draw max
        g.setColor(new Color(0, 100, 0));
        for (int counter = 0; counter < max.length - 1; counter++) {
            int x1 = counter * (500 / max.length);
            int x2 = (counter + 1) * (500 / max.length);

            System.out.println((max[counter] - (int)max[counter]) * 25);

            int y1 = (int)-( (max[counter] - (int)max[counter]) * 50 );
            int y2 = (int)-( (max[counter + 1] - (int)max[counter + 1]) * 50 );

            if (max[counter] != 0 & max[counter + 1] != 0) g.drawLine(x1 + 40, y1 + 75, x2 + 40, y2 + 75);
        }
    }

    public static void addValueMin(int id, String value) {
        min[id] = Float.parseFloat(value);
    }

    public static void addValueMax(int id, String value) {
        max[id] = Float.parseFloat(value);
    }

}
