package ru.vsu.cs.edryshov_ad.elements.house.sections;

import ru.vsu.cs.edryshov_ad.elements.house.FahverkHouse;
import ru.vsu.cs.edryshov_ad.elements.house.InclinedPlank;

import java.awt.*;

public class BlankSection extends Section {
    public static final int MIDDLE = 0;
    public static final int LEFT_SIDE = 1;
    public static final int RIGHT_SIDE = 2;

    private final int type;

    InclinedPlank bigPlank;
    InclinedPlank littlePlank;

    public BlankSection(int width, int height, FahverkHouse house, int type) {
        super(width, height, house);

        this.type = type;

        int plankWidth = house.getPlankWidth();
        int abovePlank = height / 8;

        this.bigPlank = new InclinedPlank(
                width, height - abovePlank - plankWidth, plankWidth,
                false, type != LEFT_SIDE,
                house.getWoodColor()
        );

        int littlePlankWidth = abovePlank * 3 / 2;
        this.littlePlank = new InclinedPlank(
                littlePlankWidth, abovePlank, plankWidth,
                true, type == LEFT_SIDE,
                house.getWoodColor()
        );
    }

    @Override
    public void draw(Graphics2D g, int x, int y) {
        Color old = g.getColor();
        g.setColor(house.getBaseColor());

        g.fillRect(x, y, width, height);

        if (type != MIDDLE) {
            drawSideSection(g, x, y, type == LEFT_SIDE);
        }
        drawMiddlePlank(g, x, y);

        g.setColor(old);
    }

    public void drawMiddlePlank(Graphics2D g, int x, int y) {
        int belowWindowHeight = height / 4;
        int plankWidth = house.getPlankWidth();

        g.setColor(house.getWoodColor());
        g.fillRect(x, y + (height - belowWindowHeight) - plankWidth, width, plankWidth);

        int abovePlank = height / 8;
        g.fillRect(x, y + abovePlank, width, plankWidth);
    }

    public void drawSideSection(Graphics2D g, int x, int y, boolean isLeft) {
        int plankWidth = house.getPlankWidth();
        int abovePlank = height / 8;

        g.setColor(house.getWoodColor());

        bigPlank.draw(g, x, y + abovePlank + plankWidth);

        int littlePlankWidth = abovePlank * 3 / 2;
        int littlePlankX = !isLeft ? x + width - littlePlankWidth : x;

        littlePlank.draw(g, littlePlankX, y);
    }
}
