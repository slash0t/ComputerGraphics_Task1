package ru.vsu.cs.edryshov_ad.elements.house.sections.blank.simple;

import ru.vsu.cs.edryshov_ad.elements.house.FahverkHouse;
import ru.vsu.cs.edryshov_ad.elements.house.sections.blank.MidBlankSection;

import java.awt.*;

public class SimpleMidSection extends MidBlankSection {
    public SimpleMidSection(FahverkHouse house) {
        super(house);
    }

    @Override
    public void draw(Graphics2D g, int x, int y) {
        super.draw(g, x, y);

        int mid = height / 2;
        int plankWidth = house.getPlankWidth();

        Color old = g.getColor();
        g.setColor(house.getWoodColor());
        g.fillRect(x, y + mid - plankWidth / 2, width, plankWidth);
        g.setColor(old);
    }
}
