package ru.vsu.cs.edryshov_ad.elements.house.sections.window.decoration;

import ru.vsu.cs.edryshov_ad.elements.house.FahverkHouse;
import ru.vsu.cs.edryshov_ad.elements.house.InclinedPlank;

import java.awt.*;

public class WindowCrossDecoration extends WindowDecoration {
    InclinedPlank plank;
    InclinedPlank plankMirrored;

    public WindowCrossDecoration(int width, int height, FahverkHouse house) {
        super(width, height, house);

        plank = new InclinedPlank(
                width,
                height,
                house.getPlankWidth(),
                true,
                false,
                house.getWoodColor()
        );

        plankMirrored = new InclinedPlank(
                width,
                height,
                house.getPlankWidth(),
                true,
                true,
                house.getWoodColor()
        );
    }

    @Override
    public void draw(Graphics2D g, int x, int y) {
        plank.draw(g, x, y);
        plankMirrored.draw(g, x, y);
    }
}
