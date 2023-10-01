package ru.vsu.cs.edryshov_ad.elements.house.sections.window.decoration;

import ru.vsu.cs.edryshov_ad.elements.house.FahverkHouse;

import java.awt.*;

public class WindowVerticalDecoration extends WindowDecoration {

    public WindowVerticalDecoration(int width, int height, FahverkHouse house) {
        super(width, height, house);
    }

    @Override
    public void draw(Graphics2D g, int x, int y) {
        int mid = width / 2;
        int plankWidth = house.getPlankWidth();

        g.setColor(house.getWoodColor());
        g.fillRect(x + mid - plankWidth / 2, y, plankWidth, height);
    }
}
