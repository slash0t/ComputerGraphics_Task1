package ru.vsu.cs.edryshov_ad.elements.house.sections;

import ru.vsu.cs.edryshov_ad.elements.house.FahverkHouse;
import ru.vsu.cs.edryshov_ad.elements.house.InclinedPlank;

import java.awt.*;

public class WindowSection extends Section {
    private static final int STYLES_COUNT = 2;

    private final int windowStyle;


    public WindowSection(int width, int height,  FahverkHouse house, int windowStyle) {
        super(width, height, house);

        this.windowStyle = windowStyle % STYLES_COUNT;
    }

    @Override
    public void draw(Graphics2D g, int x, int y) {
        Color old = g.getColor();
        g.setColor(house.getBaseColor());

        g.fillRect(x, y, width, height);

        int windowHeight = height * 7 / 10;
        int belowWindowHeight = height / 4;
        int aboveWindowHeight = height - windowHeight - belowWindowHeight;

        g.setColor(house.getWindowFrameColor());
        g.fillRect(x, y + aboveWindowHeight, width, windowHeight);

        g.setColor(house.getWoodColor());

        int borderPlankWidth = house.getPlankWidth() * 2 / 3;

        g.fillRect(x, y + aboveWindowHeight, width, borderPlankWidth);
        g.fillRect(x, y + aboveWindowHeight + windowHeight - borderPlankWidth, width, borderPlankWidth);

        int insideWindowY = y + aboveWindowHeight + borderPlankWidth;
        int insideWindowHeight = windowHeight - borderPlankWidth * 2;
        if (windowStyle == 0) {
            drawWindowStyle1(g, x, insideWindowY, insideWindowHeight);
        } else {
            drawWindowStyle2(g, x, insideWindowY, insideWindowHeight);
        }

        drawWindowDecoration1(g, x, y + aboveWindowHeight + windowHeight, belowWindowHeight);

        g.setColor(old);
    }

    private void drawWindowStyle1(Graphics2D g, int x, int y, int height) {
        int plankWidth = house.getPlankWidth();

        g.setColor(house.getWindowColor());
        g.fillRect(x + plankWidth, y + plankWidth, width - plankWidth * 2, height - plankWidth * 2);
    }

    private void drawWindowStyle2(Graphics2D g, int x, int y, int height) {
        int plankWidth = house.getPlankWidth();

        int offset = plankWidth * 2 / 3;
        int innerY = y + offset, innerX = x + offset;
        int innerWidth = width - 2 * offset, innerHeight = height - 2 * offset;

        int separator = offset / 2;
        int glassWidth = (innerWidth - separator) / 2, glassHeight = (innerHeight - 2 * separator) / 3;

        g.setColor(house.getWindowColor());
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                g.fillRect(
                        innerX + (separator + glassWidth) * j,
                        innerY + (separator + glassHeight) * i,
                        glassWidth, glassHeight
                );
            }
        }
    }

    private void drawWindowDecoration1(Graphics2D g, int x, int y, int height) {
        InclinedPlank inclinedPlank1 = new InclinedPlank(
                width / 2, height, house.getPlankWidth(),
                false, false,
                house.getWoodColor()
        );
        inclinedPlank1.draw(g, x, y);

        InclinedPlank inclinedPlank2 = new InclinedPlank(
                width - width / 2, height, house.getPlankWidth(),
                false, true,
                house.getWoodColor()
        );
        inclinedPlank2.draw(g, x + width / 2, y);
    }
}
