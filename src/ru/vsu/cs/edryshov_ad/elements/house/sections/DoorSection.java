package ru.vsu.cs.edryshov_ad.elements.house.sections;

import ru.vsu.cs.edryshov_ad.elements.house.FahverkHouse;
import ru.vsu.cs.edryshov_ad.utils.ColorsUtils;

import java.awt.*;

public class DoorSection extends Section {
    private Font font;

    public DoorSection(int width, int height, FahverkHouse house) {
        super(width, height, house);

        this.font = new Font("Book Antiqua", Font.PLAIN, house.getPlankWidth() * 3 / 2);
    }

    @Override
    public void draw(Graphics2D g, int x, int y) {
        Color old = g.getColor();
        g.setColor(house.getBaseColor());

        g.fillRect(x, y, width, height);

        drawDoor(g, x, y);

        int doorHeight = height * 4 / 5;

        drawHouseNumberSign(g, x, y, height - doorHeight);

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

    public void drawHouseNumberSign(Graphics2D g, int x, int y, int height) {
        g.setColor(house.getWoodColor());
        g.fillRect(x, y + height / 2 - house.getPlankWidth() / 2, width, house.getPlankWidth());

        Font old = g.getFont();
        g.setFont(font);

        String houseNumber = Integer.toString(house.getHouseNumber());

        FontMetrics metrics = g.getFontMetrics();
        int stringWidth = metrics.stringWidth(houseNumber), stringHeight = metrics.getHeight();

        int signWidth = (stringWidth * 5 / 4), signHeight = (stringHeight * 5 / 4);
        signWidth = Math.max(signWidth, house.getPlankWidth() * 3);

        g.setColor(house.getWoodColor());
        g.fillRect(x + width / 2 - signWidth / 2, y + height / 2 - signHeight / 2, signWidth, signHeight);

        g.setColor(house.getWindowFrameColor());
        g.drawString(
                houseNumber,
                x + width / 2 - stringWidth / 2,
                y + height / 2 + signHeight / 4
        );

        g.setFont(old);
    }
}
