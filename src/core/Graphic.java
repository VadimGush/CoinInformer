package core;

/*
* Copyright (c) 2015, Gush Vadim Vadimovich
* All rights reserved.
*
* Redistribution and use in source and binary forms, with or without
* modification, are permitted provided that the following conditions are met:
*
* 1. Redistributions of source code must retain the above copyright notice, this
* list of conditions and the following disclaimer.
* 2. Redistributions in binary form must reproduce the above copyright notice,
* this list of conditions and the following disclaimer in the documentation
* and/or other materials provided with the distribution.
*
* THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
* ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
* WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
* DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
* ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
* (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
* LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
* ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
* (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
* SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*
* The views and conclusions contained in the software and documentation are those
* of the authors and should not be interpreted as representing official policies,
* either expressed or implied, of the FreeBSD Project.
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
            int y1Min = (int)-( (min[counter] - minValue) * size );
            int y2Min = (int)-( (min[counter + 1] - minValue) * size );

            // Draw grid
            if (Main.inter.gridCheckBox.isSelected()) {
                g.setColor(new Color(195, 195, 195));
                g.drawLine(x1 + 5, 0, x1 + 5, 125);
            }

            // Draw min. values
            if (Main.inter.graphMode.getSelectedIndex() == 0) {
                if (min[counter] != 0 & min[counter + 1] != 0) {
                    g.setColor(Color.red);
                    g.drawLine(x1 + 5, y1Min + 125, x2 + 5, y2Min + 125);
                }
            }

            // Counting coordinates
            int y1Max = (int)-( (max[counter] - minValue) * size );
            int y2Max = (int)-( (max[counter + 1] - minValue) * size );

            // Draw max. values
            if (Main.inter.graphMode.getSelectedIndex() == 0) {
                if (max[counter] != 0 & max[counter + 1] != 0) {
                    g.setColor(new Color(0, 100, 0));
                    g.drawLine(x1 + 5, y1Max + 125, x2 + 5, y2Max + 125);
                }
            }

            // Draw columns
            if (Main.inter.graphMode.getSelectedIndex() == 1) {
                if (min[counter] != 0) {
                    g.setColor(Color.BLACK);
                    g.drawLine(x1 + 5, y1Max + 125, x1 + 5, y1Min + 125);
                }
            }

            // Print text
            if (Main.inter.labelsCheckBox.isSelected()) {
                if ((max[counter] != 0 & max[counter + 1] == 0) | counter == 0 | counter == max.length / 2) {
                    g.setColor(Color.BLACK);
                    g.drawString(time[counter] + "", x1 + 2, 140);
                }
                g.setColor(Color.BLACK);
                g.drawString("Values: " + minValue + " - " + maxValue, 5, 15);
                g.setColor(Color.red);
                g.drawString("- Min", 225, 12);
                g.setColor(new Color(0, 100, 0));
                g.drawString("- Max", 255, 12);
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
