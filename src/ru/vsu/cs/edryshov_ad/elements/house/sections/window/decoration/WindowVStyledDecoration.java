package ru.vsu.cs.edryshov_ad.elements.house.sections.window.decoration;

import ru.vsu.cs.edryshov_ad.elements.house.FahverkHouse;
import ru.vsu.cs.edryshov_ad.elements.house.InclinedPlank;

import java.awt.*;

public class WindowVStyledDecoration extends WindowDecoration {
    InclinedPlank inclinedPlankLeft;
    InclinedPlank inclinedPlankRight;

    public WindowVStyledDecoration(int width, int height, FahverkHouse house) {
        super(width, height, house);

        inclinedPlankLeft = new InclinedPlank(
                width / 2, height, house.getPlankWidth(),
                false, false,
                house.getWoodColor()
        );

        inclinedPlankRight = new InclinedPlank(
                width - width / 2, height, house.getPlankWidth(),
                false, true,
                house.getWoodColor()
        );
    }

    @Override
    public void draw(Graphics2D g, int x, int y) {
        inclinedPlankLeft.draw(g, x, y);

        inclinedPlankRight.draw(g, x + width / 2, y);
    }
}
