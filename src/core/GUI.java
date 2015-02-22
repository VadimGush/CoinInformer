package core;

/*
CoinInformer 1.0.4
Author: Gush Vadim Vadimovich
Last update: 19.02.2015

(C) Copyright Gush Vadim Vadimovich 2015 year. All right reserved.
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;

public class GUI extends JFrame{
    private JTextField coins;
    private JTextField price;
    private JButton btcButton;
    private JButton ltcButton;
    private JButton rurButton;
    private JButton eurButton;
    private JButton usdButton;
    private JButton aboutButton;
    private JButton helpButton;
    private JButton donateButton;
    private JPanel MainPanel;
    private JLabel timeRate;
    private JLabel highRate;
    private JLabel lowRate;
    private JLabel buyRate;
    private JLabel sellRate;
    private JLabel lastRate;
    private JLabel buyPrice;
    private JLabel sellPrice;
    private JLabel high;
    private JLabel buy;
    private JLabel low;
    private JLabel sell;
    private JLabel last;
    private JLabel rateName;
    public JPanel graphPanel;
    public JToolBar mainToolBar;

    public GUI() {
        super("CoinInformer");

        setDefaultLookAndFeelDecorated(true);
        setContentPane(MainPanel);

        pack();

        setLocationByPlatform(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setVisible(true);

        try {
            setIconImage(ImageIO.read(new File("icon64.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void input() {
        usdButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Rate.setV2("usd");
                setUpdate();
            }
        });

        rurButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Rate.setV2("rur");
                setUpdate();
            }
        });

        eurButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Rate.setV2("eur");
                setUpdate();
            }
        });

        btcButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Rate.setV1("btc");
                setUpdate();
            }
        });

        ltcButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Rate.setV1("ltc");
                setUpdate();
            }
        });

        donateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openWebPage(URI.create("http://coininformer.sourceforge.net/donate.html"));
            }
        });

        helpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String info = "<html>" +
                        "<img src=https://a.fsdn.com/con/app/proj/coininformer/screenshots/sdafdsa.png>" +
                        "<hr><br>" +
                        "For that would to calculate the selling price,<br>" +
                        "the purchase price or a percentage of profits,<br>" +
                        "enter the number of coins and their price.<br>" +
                        "(The program is automatically updated in 2 times<br>" +
                        "per second.)" +
                        "</html>";

                JOptionPane.showMessageDialog(null, info, "Help", JOptionPane.PLAIN_MESSAGE);
            }
        });

        aboutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final ImageIcon icon = new ImageIcon("icon64.png");

                String info = "<html><font size=+1>CoinInformer</font><br>" +
                        "Version: 1.0.4<br><br>" +
                        "Author: Gush Vadim Vadimovich<br>" +
                        "Contact: djuke.studio@gmail.com<br>" +
                        "Information: http://btc-e.com<br>" +
                        "Site: http://coininformer.sourceforge.com<br>" +
                        "</html>";

                JOptionPane.showMessageDialog(null, info, "About", JOptionPane.INFORMATION_MESSAGE, icon);
            }
        });
    }

    public void update() {

        rateName.setText("<html><font size=+1>" + Rate.getV1().toUpperCase() + " - " + Rate.getV2().toUpperCase() + "</font> (BTC-E)</html>");

        // Get data

        float hightV = Rate.getHight();
        float lowV = Rate.getLow();
        float buyV = Rate.getBuy();
        float sellV = Rate.getSell();
        float lastV = Rate.getLast();

        Rate.connect();

        // Update GUI

        timeRate.setText("" + Rate.getTime());

        if (hightV < Rate.getHight()) highRate.setText("<html><font color=#008000>" + Rate.getHight() + " &#9650;</html>");
        else if (hightV > Rate.getHight()) highRate.setText("<html><font color=#800000>" + Rate.getHight() + " &#9660;</html>");
        else highRate.setText("" + Rate.getHight());

        if (lowV < Rate.getLow()) lowRate.setText("<html><font color=#008000>" + Rate.getLow() + " &#9650;</html>");
        else if (lowV > Rate.getLow()) lowRate.setText("<html><font color=#800000>" + Rate.getLow() + " &#9660;</html>");
        else lowRate.setText("" + Rate.getLow());

        if (lastV < Rate.getLast()) lastRate.setText("<html><font color=#008000>" + Rate.getLast() + " &#9650;</html>");
        else if (lastV > Rate.getLast()) lastRate.setText("<html><font color=#800000>" + Rate.getLast() + " &#9660;</html>");
        else lastRate.setText("" + Rate.getLast());

        if (sellV < Rate.getSell()) sellRate.setText("<html><font color=#008000>" + Rate.getSell() + " &#9650;</html>");
        else if (sellV > Rate.getSell()) sellRate.setText("<html><font color=#800000>" + Rate.getSell() + " &#9660;</html>");
        else sellRate.setText("" + Rate.getSell());

        if (buyV < Rate.getBuy()) buyRate.setText("<html><font color=#008000>" + Rate.getBuy() + " &#9650;</html>");
        else if (buyV > Rate.getBuy()) buyRate.setText("<html><font color=#800000>" + Rate.getBuy() + " &#9660;</html>");
        else buyRate.setText("" + Rate.getBuy());

        if (!price.getText().isEmpty() && !coins.getText().isEmpty()) {
            float newValue = 0; //курс введённый пользователем
            float buyCompare = 0; //цена прибыли в рублях при покупке
            float sellCompare = 0; //цена прибыли в рублях при продажи

            try { //если ввели буквы вместо цифр
                buyCompare = Float.parseFloat(coins.getText()); //получаем coins введённые пользователем
                sellCompare = Float.parseFloat(coins.getText()); //получаем coins введённые пользователем
                newValue = Float.parseFloat(price.getText()); //получаем курс пользователя
            } catch (NumberFormatException error) { //если введены буквы в поля
                buyCompare = 0;
                sellCompare = 0;
                newValue = 0;
            }

            // Get data

            float hightValueCompare = Rate.getHight();
            float lowValueCompare = Rate.getLow();
            float sellValueCompare = Rate.getSell();
            float buyValueCompare = Rate.getBuy();
            float lastValueCompare = Rate.getLast();

            buyPrice.setText("<html>" + buyCompare * buyValueCompare + " " + Rate.getV2().toUpperCase() + " (" + buyValueCompare + ")</html>");
            sellPrice.setText("<html>" + sellCompare * sellValueCompare + " " + Rate.getV2().toUpperCase() + " (" + sellValueCompare + ")</html>");

            if (buyValueCompare < newValue) buyCompare = buyCompare * (newValue - buyValueCompare);
            if (buyValueCompare > newValue) buyCompare = buyCompare * (buyValueCompare - newValue);

            if (sellValueCompare < newValue) sellCompare = sellCompare * (newValue - sellValueCompare);
            if (sellValueCompare > newValue) sellCompare = sellCompare * (sellValueCompare - newValue);

            // Update GUI

            if (buyValueCompare < newValue) buy.setText("<html><font color=#800000>-" + (100 - (buyValueCompare / (newValue / 100))) + "%</font> (-" + buyCompare + " " + Rate.getV2() + ")</html>");
            if (buyValueCompare > newValue) buy.setText("<html><font color=#008000>+" + (buyValueCompare / (newValue / 100) - 100 + "%</font> (+" + buyCompare + " " + Rate.getV2() + ")</html>"));
            if (buyValueCompare == newValue) buy.setText("<html>0%</html>");

            if (sellValueCompare < newValue) sell.setText("<html><font color=#800000>-" + (100 - (sellValueCompare / (newValue / 100))) + "%</font> (-" + sellCompare + " " + Rate.getV2() + ")</html>");
            if (sellValueCompare > newValue) sell.setText("<html><font color=#008000>+" + (sellValueCompare / (newValue / 100) - 100 + "%</font> (+" + sellCompare + " " + Rate.getV2() + ")</html>"));
            if (sellValueCompare == newValue) sell.setText("<html>0%</html>");

            if (lowValueCompare < newValue) low.setText("<html><font color=#800000>-" + (100 - (lowValueCompare / (newValue / 100))) + "%</font></html>");
            if (lowValueCompare > newValue) low.setText("<html><font color=#008000>+" + (lowValueCompare / (newValue / 100) - 100 + "%</font></html>"));
            if (lowValueCompare == newValue) low.setText("<html>0%</html>");

            if (hightValueCompare < newValue) high.setText("<html><font color=#800000>-" + (100 - (hightValueCompare / (newValue / 100))) + "%</font></html>");
            if (hightValueCompare > newValue) high.setText("<html><font color=#008000>+" + (hightValueCompare / (newValue / 100) - 100 + "%</font></html>"));
            if (hightValueCompare == newValue) high.setText("<html>0%</html>");

            if (lastValueCompare < newValue) last.setText("<html><font color=#800000>-" + (100 - (lastValueCompare / (newValue / 100))) + "%</font></html>");
            if (lastValueCompare > newValue) last.setText("<html><font color=#008000>+" + (lastValueCompare / (newValue / 100) - 100 + "%</font></html>"));
            if (lastValueCompare == newValue) last.setText("<html>0%</html>");
        }
    }

    private void openWebPage(URI url) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setUpdate() {
        rateName.setText("<html><font size=+1 color=#000080>Loading...</font></html>");
    }

    private void createUIComponents() {
        graphPanel = new Graphic();
        graphPanel.setBackground(new Color(205, 205, 205));
        graphPanel.setForeground(Color.white);
    }
}
