package core;

/*
CoinInformer 1.0.4
Author: Gush Vadim Vadimovich
Last update: 19.02.2015

(C) Copyright Gush Vadim Vadimovich 2015 year. All right reserved.
 */

import javax.swing.*;
import java.awt.*;

public class Graphic extends JPanel {

    private static float[] min = new float[50];
    private static float[] max = new float[min.length];
    private static String[] time = new String[max.length];

    private static float minValue;
    private static float maxValue;
    private static float size;

    public void paint(Graphics g) {
        super.paint(g);

        // Clear values
        minValue = 100000;
        maxValue = 0;

        // Search minimum value and maximum value
        for (int counter = 0; counter < min.length; counter++) {
            // Search minimum value and maximum value
            if (min[counter] != 0) {
                if (minValue > min[counter]) minValue = min[counter];
                if (maxValue < min[counter]) maxValue = min[counter];
            }
            if (max[counter] != 0) {
                if (minValue > max[counter]) minValue = max[counter];
                if (maxValue < max[counter]) maxValue = max[counter];
            }
        }

        // Counting graph size
        size = 100 / (maxValue - minValue);

        // Draw graph
        for (int counter = 0; counter < min.length - 1; counter++) {
            // Counting coordinates
            int x1 = counter * (500 / min.length);
            int x2 = (counter + 1) * (500 / min.length);
            int y1 = (int)-( (min[counter] - minValue) * size );
            int y2 = (int)-( (min[counter + 1] - minValue) * size );

            // Draw grid
            g.setColor(new Color(195, 195, 195));
            g.drawLine(x1 + 5, 0, x1 + 5, 125);

            // Draw min. values
            if (min[counter] != 0 & min[counter + 1] != 0) {
                g.setColor(Color.red);
                g.drawLine(x1 + 5, y1 + 125, x2 + 5, y2 + 125);
            }

            // Counting coordinates
            x1 = counter * (500 / max.length);
            x2 = (counter + 1) * (500 / max.length);
            y1 = (int)-( (max[counter] - minValue) * size );
            y2 = (int)-( (max[counter + 1] - minValue) * size );

            // Draw max. values
            if (max[counter] != 0 & max[counter + 1] != 0) {
                g.setColor(new Color(0, 100, 0));
                g.drawLine(x1 + 5, y1 + 125, x2 + 5, y2 + 125);
            }

            // Print time
            if ((max[counter] != 0 & max[counter + 1] == 0) | counter == 0 | counter == max.length / 2) {
                g.setColor(Color.BLACK);
                g.drawString(time[counter] + "", x1 + 2, 140);
            }
        }
    }

    public static void addValueMin(int id, String value) {
        min[id] = Float.parseFloat(value);
    }

    public static void addValueMax(int id, String value) {
        max[id] = Float.parseFloat(value);
    }

    public static void cleanHistory() {
        min = new float[min.length];
        max = new float[min.length];
    }

    public static void addValueTime(int id, String value) {
        time[id] = value;
    }

}
