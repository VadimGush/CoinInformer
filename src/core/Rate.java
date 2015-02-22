package core;

/*
CoinInformer 1.0.4
Author: Gush Vadim Vadimovich
Last update: 19.02.2015

(C) Copyright Gush Vadim Vadimovich 2015 year. All right reserved.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class Rate {

    private static List<String[]> history = new ArrayList<String[]>();

    private static String data = ""; // data from page

    private static String hight = "0";
    private static String low = "0";
    private static String buy = "0";
    private static String sell = "0";
    private static String last = "0";
    private static String time = "0";

    private static String v1 = "ltc";
    private static String v2 = "rur";

    public static void connect() {

        // Loading last rate information
        try {

            // Creating connection
            URL url = new URL("https://btc-e.com/api/3/ticker/" + v1 + "_" + v2);
            URLConnection connection  = url.openConnection();

            InputStreamReader isr = new InputStreamReader(connection.getInputStream());
            BufferedReader br = new BufferedReader(isr);

            // Read first string from page
            StringBuffer sb = new StringBuffer(br.readLine());
            sb.deleteCharAt(sb.length() - 1);
            sb.deleteCharAt(sb.length() - 1);
            data = sb.toString();

            // Loading rate from first string
            String[] info = data.split("\\{")[2].split("\\,");

            // Read information
            hight = info[0].split("\\:")[1];
            low = info[1].split("\\:")[1];
            buy = info[6].split("\\:")[1];
            sell = info[7].split("\\:")[1];
            last = info[5].split("\\:")[1];
            time = info[8].split("\\:")[1];

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Read history from main page

        try {

            // Create connection
            URL url = new URL("https://btc-e.com/exchange/" + v1 + "_" + v2);
            URLConnection connection  = url.openConnection();

            InputStreamReader isr = new InputStreamReader(connection.getInputStream());
            BufferedReader br = new BufferedReader(isr);

            // Read data from page
            List<String> page = new ArrayList<String>();
            String line;
            while ((line = br.readLine()) != null) {
                page.add(line);
            }

            // Get string №66
            line = page.get(66);

            // Read information from string
            for (int counter = 0; counter < line.split("\\[").length - 2; counter++) {

                StringBuffer sb = new StringBuffer(line.split("\\[")[2 + counter]);
                sb.deleteCharAt(sb.length() - 1);
                sb.deleteCharAt(sb.length() - 1);
                if (counter == line.split("\\[").length - 3) {
                    sb.delete(sb.length() - 8, sb.length());
                }
                String stage;
                stage = sb.toString();

                // Save information
                history.add(new String[]{
                        stage.split("\\,")[0], // Time
                        stage.split("\\,")[1], // Min. price
                        stage.split("\\,")[2], // Price 1
                        stage.split("\\,")[3], // Price 2
                        stage.split("\\,")[4]}); // Max Price
            }

            System.out.println(history.size());

            for (int counter = 0; counter < history.size(); counter++) {
                Graphic.addValueMin(counter, history.get(counter)[1]);
                Graphic.addValueMax(counter, history.get(counter)[4]);
            }

            history = new ArrayList<String[]>();

            // Add history to graphic
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String getV2() {
        return v2;
    }

    public static String getV1() {
        return v1;
    }

    public static void setV1(String v1) {
        Rate.v1 = v1;
    }

    public static void setV2(String v2) {
        Rate.v2 = v2;
    }

    public static String getTime() {
        return time;
    }

    public static float getLast() {
        return Float.parseFloat(last);
    }

    public static float getSell() {
        return Float.parseFloat(sell);
    }

    public static float getLow() {
        return Float.parseFloat(low);
    }

    public static float getHight() {
        return Float.parseFloat(hight);
    }

    public static float getBuy() {
        return Float.parseFloat(buy);
    }

    // History

}
