package ru.vsu.cs.edryshov_ad.elements.house.sections.window.frame;

import ru.vsu.cs.edryshov_ad.elements.house.FahverkHouse;

import java.awt.*;

public class WindowVerticalDividedFrame extends WindowFrame {

    public WindowVerticalDividedFrame(int width, int height, FahverkHouse house) {
        super(width, height, house);
    }

    @Override
    public void draw(Graphics2D g, int x, int y) {
        int plankWidth = house.getPlankWidth();

        int offset = plankWidth * 2 / 3;
        int innerY = y + offset, innerX = x + offset;
        int innerWidth = width - 2 * offset, innerHeight = height - 2 * offset;

        int separator = offset / 2;
        int glassWidth = (innerWidth - separator) / 2;

        Color old = g.getColor();
        g.setColor(house.getWindowColor());
        for (int i = 0; i < 2; i++) {
            g.fillRect(
                    innerX + (separator + glassWidth) * i,
                    innerY,
                    glassWidth, innerHeight
            );
        }

        g.setColor(old);
    }
}
