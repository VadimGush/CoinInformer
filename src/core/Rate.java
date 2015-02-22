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

    private static String[][] history = new String[37][5];

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

        try {

            String webPage = "https://btc-e.com/api/3/ticker/" + v1 + "_" + v2;

            URL url = new URL(webPage);
            URLConnection connection  = url.openConnection();

            InputStreamReader isr = new InputStreamReader(connection.getInputStream());
            BufferedReader br = new BufferedReader(isr);

            StringBuffer sb = new StringBuffer(br.readLine());
            sb.deleteCharAt(sb.length() - 1);
            sb.deleteCharAt(sb.length() - 1);
            data = sb.toString();

            String[] one = data.split("\\{");
            String[] two = one[2].split("\\,");

            hight = two[0].split("\\:")[1];
            low = two[1].split("\\:")[1];
            buy = two[6].split("\\:")[1];
            sell = two[7].split("\\:")[1];
            last = two[5].split("\\:")[1];
            time = two[8].split("\\:")[1];

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // History

        try {

            String webPage = "https://btc-e.com/exchange/" + v1 + "_" + v2;

            URL url = new URL(webPage);
            URLConnection connection  = url.openConnection();

            InputStreamReader isr = new InputStreamReader(connection.getInputStream());
            BufferedReader br = new BufferedReader(isr);

            List<String> file = new ArrayList<String>();
            String line;
            while ((line = br.readLine()) != null) {
                file.add(line);
            }

            line = file.get(66);

            for (int counter = 0; counter < line.split("\\[").length - 2; counter++) {
                String stage = "";

                stage = line.split("\\[")[2 + counter];

                StringBuffer sb = new StringBuffer(stage);
                sb.deleteCharAt(sb.length() - 1);
                sb.deleteCharAt(sb.length() - 1);

                if (counter == line.split("\\[").length - 3) {
                    sb.delete(sb.length() - 8, sb.length());
                }

                stage = sb.toString();

                System.out.println(stage);

                history[counter][0] = stage.split("\\,")[0]; // Time
                history[counter][1] = stage.split("\\,")[1]; // Min. price
                history[counter][2] = stage.split("\\,")[2]; // Price 1
                history[counter][3] = stage.split("\\,")[3]; // Price 2
                history[counter][4] = stage.split("\\,")[4]; // Max. price
            }

            System.out.println();

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
