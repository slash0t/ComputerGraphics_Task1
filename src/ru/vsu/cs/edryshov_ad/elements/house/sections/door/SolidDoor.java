package ru.vsu.cs.edryshov_ad.elements.house.sections.door;

import ru.vsu.cs.edryshov_ad.elements.house.FahverkHouse;
import ru.vsu.cs.edryshov_ad.utils.ColorsUtils;

import java.awt.*;

public class SolidDoor extends DoorSection {

    public SolidDoor(FahverkHouse house) {
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
        int partWidth = (doorWidth - offset * 2 - gapWidth) / 2, partHeight = (doorHeight - offset * 2 - gapWidth) / 2;

        g.setColor(ColorsUtils.getDarker(house.getWoodColor()));
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                g.fillRect(
                        x + (width - doorWidth) / 2 + offset + (partWidth + gapWidth) * i,
                        y + (height - doorHeight) + offset + (partHeight + gapWidth) * j,
                        partWidth,
                        partHeight
                );
            }
        }

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
