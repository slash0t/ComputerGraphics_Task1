package ru.vsu.cs.edryshov_ad.elements.house.sections.door;

import ru.vsu.cs.edryshov_ad.elements.house.FahverkHouse;
import ru.vsu.cs.edryshov_ad.elements.house.sections.Section;

import java.awt.*;

public abstract class DoorSection extends Section {
    Font font;

    public DoorSection(FahverkHouse house) {
        super(house);

        this.font = new Font("Book Antiqua", Font.PLAIN, house.getPlankWidth() * 3 / 2);
    }

    @Override
    public void draw(Graphics2D g, int x, int y) {
        super.draw(g, x, y);

        int doorHeight = height * 4 / 5;
        int aboveDoorHeight = height - doorHeight;

        g.setColor(house.getWoodColor());
        g.fillRect(x, y + aboveDoorHeight / 2 - house.getPlankWidth() / 2, width, house.getPlankWidth());

        Font old = g.getFont();
        g.setFont(font);

        String houseNumber = Integer.toString(house.getHouseNumber());

        FontMetrics metrics = g.getFontMetrics();
        int stringWidth = metrics.stringWidth(houseNumber), stringHeight = metrics.getHeight();

        int signWidth = (stringWidth * 5 / 4), signHeight = (stringHeight * 5 / 4);
        signWidth = Math.max(signWidth, house.getPlankWidth() * 3);

        g.setColor(house.getWoodColor());
        g.fillRect(x + width / 2 - signWidth / 2, y + aboveDoorHeight / 2 - signHeight / 2, signWidth, signHeight);

        g.setColor(house.getWindowFrameColor());
        g.drawString(
                houseNumber,
                x + width / 2 - stringWidth / 2,
                y + aboveDoorHeight / 2 + signHeight / 4
        );

        g.setFont(old);
    }
}
