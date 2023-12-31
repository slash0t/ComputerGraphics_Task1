package ru.vsu.cs.edryshov_ad;

import ru.vsu.cs.edryshov_ad.elements.house.FahverkHouse;
import ru.vsu.cs.edryshov_ad.elements.house.HouseConfiguration;
import ru.vsu.cs.edryshov_ad.elements.house.HouseFactory;
import ru.vsu.cs.edryshov_ad.elements.house.roof.RoofFactory;
import ru.vsu.cs.edryshov_ad.elements.house.sections.blank.BlankSuperFactory;
import ru.vsu.cs.edryshov_ad.elements.house.sections.door.DoorFactory;
import ru.vsu.cs.edryshov_ad.elements.house.sections.window.decoration.WindowDecorationFactory;
import ru.vsu.cs.edryshov_ad.elements.house.sections.window.frame.WindowFrameFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class DrawPanel extends JPanel {
    FahverkHouse house1;
    FahverkHouse house2;
    FahverkHouse house3;

    HouseFactory houseFactory;

    public DrawPanel() {
        houseFactory = new HouseFactory(
                new WindowFrameFactory(),
                new WindowDecorationFactory(),
                new DoorFactory(),
                new RoofFactory(),
                new BlankSuperFactory()
        );

        house1 = houseFactory.createHouse(
                2, 6,
                120, 65, 7, 696,
                new Color(71, 7, 31),
                new Color(131, 187, 204),
                new Color(220, 221, 216),
                new Color(208, 205, 205),
                new Color(39, 40, 42),
                new HouseConfiguration()
        );

        house2 = houseFactory.createHouse(
                3, 5,
                110, 60, 7, 697,
                new Color(63, 43, 13),
                new Color(91, 169, 183),
                new Color(231, 222, 186),
                new Color(232, 234, 217),
                new Color(51, 51, 54),
                new HouseConfiguration()
        );

        house3 = houseFactory.createHouse(
                2, 4,
                108, 62, 7, 69,
                new Color(80, 46, 19),
                new Color(96, 168, 206),
                new Color(220, 202, 199),
                new Color(234, 225, 219),
                new Color(44, 43, 43),
                new HouseConfiguration()
        );
    }

    @Override
    public void paint(Graphics gr) {
        super.paint(gr);

        Graphics2D g = (Graphics2D) gr;

        house1.draw(g, 50, this.getHeight());

        house2.draw(g, 100 + house1.getWidth(), this.getHeight());

        house3.draw(g, 150 + house1.getWidth() + house2.getWidth(), this.getHeight());
    }
}
