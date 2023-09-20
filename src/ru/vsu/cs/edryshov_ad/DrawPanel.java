package ru.vsu.cs.edryshov_ad;

import ru.vsu.cs.edryshov_ad.elements.house.FahverkHouse;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class DrawPanel extends JPanel {
    FahverkHouse house1;
    FahverkHouse house2;

    public DrawPanel() {
        house1 = new FahverkHouse(
                3, 6,
                120, 65, 7,
                1, 696,
                false,
                new Color(71, 7, 31),
                new Color(131, 187, 204),
                new Color(220, 221, 216),
                new Color(208, 205, 205),
                new Color(39, 40, 42)
        );

        house2 = new FahverkHouse(
                4, 5,
                110, 60, 7,
                1, 697,
                false,
                new Color(63, 43, 13),
                new Color(91, 169, 183),
                new Color(231, 222, 186),
                new Color(232, 234, 217),
                new Color(51, 51, 54)
        );
    }

    @Override
    public void paint(Graphics gr) {
        super.paint(gr);

        Graphics2D g = (Graphics2D) gr;

        house1.draw(g, this.getWidth() / 2 / 2 - (int) house1.getWidth() / 2, this.getHeight());

        house2.draw(g, this.getWidth() * 3 / 4 - (int) house2.getWidth() / 2, this.getHeight());
    }
}
