package ru.vsu.cs.edryshov_ad.elements.house.sections.blank.l_styled;

import ru.vsu.cs.edryshov_ad.elements.house.FahverkHouse;
import ru.vsu.cs.edryshov_ad.elements.house.InclinedPlank;
import ru.vsu.cs.edryshov_ad.elements.house.sections.blank.SideBlankSection;

import java.awt.*;

public class LStyledSideSection extends SideBlankSection {
    private final InclinedPlank bigPlank;
    private final InclinedPlank littlePlank;

    private final int abovePlank;

    public LStyledSideSection(FahverkHouse house, boolean isLeft) {
        super(house, isLeft);

        abovePlank = height / 8;
        int plankWidth = house.getPlankWidth();

        this.bigPlank = new InclinedPlank(
                width, height - abovePlank - plankWidth, plankWidth,
                false, !isLeft,
                house.getWoodColor()
        );

        int littlePlankWidth = abovePlank * 3 / 2;
        this.littlePlank = new InclinedPlank(
                littlePlankWidth, abovePlank, plankWidth,
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
        bigPlank.draw(g, x, y + abovePlank + house.getPlankWidth());

        int littlePlankWidth = abovePlank * 3 / 2;
        int littlePlankX = !isLeft ? x + width - littlePlankWidth : x;

        littlePlank.draw(g, littlePlankX, y);
    }

    private void drawHorizontalPlanks(Graphics2D g, int x, int y) {
        int plankWidth = house.getPlankWidth();
        int belowWindowHeight = height / 4;

        g.fillRect(x, y + (height - belowWindowHeight) - plankWidth, width, plankWidth);
        g.fillRect(x, y + abovePlank, width, plankWidth);
    }
}
