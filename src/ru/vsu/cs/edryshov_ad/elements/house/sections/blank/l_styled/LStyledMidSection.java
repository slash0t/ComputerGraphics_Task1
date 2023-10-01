package ru.vsu.cs.edryshov_ad.elements.house.sections.blank.l_styled;

import ru.vsu.cs.edryshov_ad.elements.house.FahverkHouse;
import ru.vsu.cs.edryshov_ad.elements.house.sections.blank.MidBlankSection;

import java.awt.*;

public class LStyledMidSection extends MidBlankSection {

    public LStyledMidSection(FahverkHouse house) {
        super(house);
    }

    @Override
    public void draw(Graphics2D g, int x, int y) {
        super.draw(g, x, y);

        int belowWindowHeight = height / 4;
        int plankWidth = house.getPlankWidth();

        Color old = g.getColor();
        g.setColor(house.getWoodColor());
        g.fillRect(x, y + (height - belowWindowHeight) - plankWidth, width, plankWidth);

        int abovePlank = height / 8;
        g.fillRect(x, y + abovePlank, width, plankWidth);

        g.setColor(old);
    }
}
