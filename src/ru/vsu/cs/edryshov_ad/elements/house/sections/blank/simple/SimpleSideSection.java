package ru.vsu.cs.edryshov_ad.elements.house.sections.blank.simple;

import ru.vsu.cs.edryshov_ad.elements.house.FahverkHouse;
import ru.vsu.cs.edryshov_ad.elements.house.InclinedFitPlank;
import ru.vsu.cs.edryshov_ad.elements.house.sections.blank.SideBlankSection;

import java.awt.*;

public class SimpleSideSection extends SideBlankSection {
    InclinedFitPlank plank;

    public SimpleSideSection(FahverkHouse house, boolean isLeft) {
        super(house, isLeft);

        plank = new InclinedFitPlank(
                width - house.getPlankWidth() * 2,
                height,
                house.getPlankWidth(),
                isLeft,
                house.getWoodColor()
        );
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

        plank.draw(g, x + house.getPlankWidth(), y);
    }
}
