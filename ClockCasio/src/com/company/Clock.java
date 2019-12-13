package com.company;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import static java.lang.Thread.sleep;

public class Clock implements Runnable {
    private JPanel rootPanel;
    private JTabbedPane tabbedPane1;
    private JPanel FieldClock;
    private JLabel lblTime;
    private JPanel tabPanel2SW;
    private JPanel PanelMain;
    private JPanel PanelTop;
    private JButton btStart;
    private JButton btStop;
    private JButton btReset;
    private JPanel PanelCenter;
    private JLabel lblHour;
    private JLabel lblMinute;
    private JLabel lblSecond;
    private JLabel lblMilliSecond;

    int hours = 0;
    int minutes = 0;
    int seconds = 0;

    public int millisecond = 0;
    public int second = 0;
    public int minute = 0;
    public int hour = 0;

    static boolean state = true;

    public Clock() throws IOException, FontFormatException {
        String filename = ".\\resource\\font\\DIGITALDREAM.ttf";

        Font font = Font.createFont(Font.TRUETYPE_FONT, new File(filename));

        Font Hour = Font.createFont(Font.TRUETYPE_FONT, new File(filename));
        Font Minute = Font.createFont(Font.TRUETYPE_FONT, new File(filename));
        Font Second = Font.createFont(Font.TRUETYPE_FONT, new File(filename));
        Font Millisecond = Font.createFont(Font.TRUETYPE_FONT, new File(filename));

        font = font.deriveFont(Font.BOLD, 25);

        Hour = Hour.deriveFont(Font.BOLD,25);
        Minute = Minute.deriveFont(Font.BOLD,25);
        Second = Second.deriveFont(Font.BOLD,25);
        Millisecond = Millisecond.deriveFont(Font.BOLD,25);

        GraphicsEnvironment grap = GraphicsEnvironment.getLocalGraphicsEnvironment();

        grap.registerFont(font);

        grap.registerFont(Hour);
        grap.registerFont(Minute);
        grap.registerFont(Second);
        grap.registerFont(Millisecond);

        lblTime.setFont(font);

        lblHour.setFont(Hour);
        lblMinute.setFont(Minute);
        lblSecond.setFont(Second);
        lblMilliSecond.setFont(Millisecond);

        Thread th = new Thread(this);
        th.start();

        lblMilliSecond.setText(" : " + millisecond);
        lblSecond.setText(" : " + second);
        lblMinute.setText(" : " + minute);
        lblHour.setText("" + hour);

        FieldClock.setBackground(Color.BLACK);
        lblTime.setForeground(Color.pink);

        tabbedPane1.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                PanelTop.setBackground(Color.pink);
                PanelCenter.setBackground(Color.BLACK);
                lblHour.setForeground(Color.YELLOW);
                lblMinute.setForeground(Color.YELLOW);
                lblSecond.setForeground(Color.YELLOW);
                lblMilliSecond.setForeground(Color.YELLOW);
            }
        });
        btStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                state = true;
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            if (state == true) {
                                try {
                                    sleep(1);
                                    if (millisecond > 1000) {
                                        millisecond = 0;
                                        second++;
                                    }
                                    if (second > 60) {
                                        millisecond = 0;
                                        second = 0;
                                        minute++;
                                    }
                                    if (minute > 60) {
                                        millisecond = 0;
                                        second = 0;
                                        minute = 0;
                                        hour++;
                                    }
                                    lblMilliSecond.setText(" : " + millisecond);
                                    millisecond++;
                                    lblSecond.setText(" : " + second);
                                    lblMinute.setText(" : " + minute);
                                    lblHour.setText("" + hour);
                                } catch (InterruptedException ex) {
                                    ex.printStackTrace();
                                }
                            } else {
                                break;
                            }
                        }
                    }
                });
                t.start();
            }
        });
        btStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                state = false;
            }
        });
        btReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                state = false;
                millisecond = 0;
                second = 0;
                minute = 0;
                hour = 0;

                lblMilliSecond.setText(" : " + millisecond);
                lblSecond.setText(" : " + second);
                lblMinute.setText(" : " + minute);
                lblHour.setText("" + hour);
            }
        });
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    @Override
    public void run(){
        try{
            while (true){
                Calendar cal = Calendar.getInstance();
                hours = cal.get(Calendar.HOUR_OF_DAY);
                minutes = cal.get(Calendar.MINUTE);
                seconds = cal.get(Calendar.SECOND);

                lblTime.setText("" + hours + ":" + minutes + ":" + seconds);
            }
        }catch (Exception e){}
    }
}
