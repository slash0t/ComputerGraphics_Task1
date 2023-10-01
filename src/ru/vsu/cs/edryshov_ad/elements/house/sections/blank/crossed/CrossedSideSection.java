package ru.vsu.cs.edryshov_ad.elements.house.sections.blank.crossed;

import ru.vsu.cs.edryshov_ad.elements.house.FahverkHouse;
import ru.vsu.cs.edryshov_ad.elements.house.InclinedPlank;
import ru.vsu.cs.edryshov_ad.elements.house.sections.blank.SideBlankSection;

import java.awt.*;

public class CrossedSideSection extends SideBlankSection {
    private final InclinedPlank upperPlank;
    private final InclinedPlank bottomPlank;

    private final int abovePlank;
    private final int belowPlank;

    public CrossedSideSection(FahverkHouse house, boolean isLeft) {
        super(house, isLeft);

        abovePlank = height / 8;
        belowPlank = height / 4;
        int plankWidth = house.getPlankWidth();

        this.upperPlank = new InclinedPlank(
                width, height - abovePlank - plankWidth, plankWidth,
                false, !isLeft,
                house.getWoodColor()
        );

        this.bottomPlank = new InclinedPlank(
                width, height - belowPlank - plankWidth, plankWidth,
                true, isLeft,
                house.getWoodColor()
        );
    }

    @Override
    public void draw(Graphics2D g, int x, int y) {
        super.draw(g, x, y);

        Color old = g.getColor();

        g.setColor(house.getWoodColor());

        drawInclinedPlanks(g, x, y);
        drawHorizontalPlanks(g, x, y);

        g.setColor(old);
    }

    private void drawInclinedPlanks(Graphics2D g, int x, int y) {
        upperPlank.draw(g, x, y + abovePlank + house.getPlankWidth());

        bottomPlank.draw(g, x, y);
    }

    private void drawHorizontalPlanks(Graphics2D g, int x, int y) {
        int plankWidth = house.getPlankWidth();
        int belowWindowHeight = height / 4;

        g.fillRect(x, y + (height - belowWindowHeight) - plankWidth, width, plankWidth);
        g.fillRect(x, y + abovePlank, width, plankWidth);
    }
}
