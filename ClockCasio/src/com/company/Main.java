package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, FontFormatException {
	// write your code here
        JFrame jfame = new JFrame();

        jfame.setTitle("Clock Casio");

        jfame.setContentPane(new Clock().getRootPanel());

        jfame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jfame.setPreferredSize(new Dimension(400,200));

        jfame.setLocationRelativeTo(null);

        jfame.pack();

        jfame.setVisible(true);
    }
}
