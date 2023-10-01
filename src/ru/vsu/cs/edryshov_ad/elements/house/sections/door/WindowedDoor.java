package ru.vsu.cs.edryshov_ad.elements.house.sections.door;

import ru.vsu.cs.edryshov_ad.elements.house.FahverkHouse;
import ru.vsu.cs.edryshov_ad.utils.ColorsUtils;

import java.awt.*;

public class WindowedDoor extends DoorSection {

    public WindowedDoor(FahverkHouse house) {
        super(house);
    }

    @Override
    public void draw(Graphics2D g, int x, int y) {
        super.draw(g, x, y);

        Color old = g.getColor();

        drawDoor(g, x, y);

        g.setColor(old);
    }

    public void drawDoor(Graphics2D g, int x, int y) {
        int doorWidth = width * 4 / 5;
        int doorHeight = height * 4 / 5;

        g.setColor(house.getWoodColor());
        g.fillRect(x + (width - doorWidth) / 2, y + (height - doorHeight), doorWidth, doorHeight);

        int gapWidth = house.getPlankWidth() / 2;
        int offset = doorWidth / 7;
        int partHeight = (doorHeight - offset * 2 - gapWidth) / 2;

        g.setColor(ColorsUtils.getDarker(house.getWoodColor()));
        g.fillRect(
                x + (width - doorWidth) / 2 + offset,
                y + (height - doorHeight) + offset,
                doorWidth - offset * 2,
                partHeight
        );

        int partWidth = (doorWidth - offset * 2 - gapWidth) / 2;
        for (int i = 0; i < 2; i++) {
            g.fillRect(
                    x + (width - doorWidth) / 2 + offset + (partWidth + gapWidth) * i,
                    y + (height - doorHeight) + offset + partHeight + gapWidth,
                    partWidth,
                    partHeight
            );
        }

        g.setColor(house.getWindowColor());
        g.fillRect(
                x + (width - doorWidth) / 2 + offset * 2,
                y + (height - doorHeight) + offset * 2,
                doorWidth - offset * 4,
                partHeight - offset * 2
        );

        int doorHandleSize = house.getPlankWidth() * 2 / 3;
        g.setColor(house.getWindowFrameColor());
        g.fillRect(
                x + (width - doorWidth) / 2 + doorWidth * 5 / 6 - doorHandleSize / 2,
                y + (height - doorHeight) + doorHeight / 2 - doorHandleSize / 2,
                doorHandleSize,
                doorHandleSize
        );
    }
}
