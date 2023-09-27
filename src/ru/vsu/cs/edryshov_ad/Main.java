package ru.vsu.cs.edryshov_ad;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        MainWindow mw = new MainWindow();
        mw.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        mw.setSize(1500, 800);
        mw.setVisible(true);
    }
}