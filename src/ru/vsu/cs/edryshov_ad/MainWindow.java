package ru.vsu.cs.edryshov_ad;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private final DrawPanel dp;

    public MainWindow() throws HeadlessException {
        this.dp = new DrawPanel();
        this.add(dp);
    }
}
