package ru.vsu.cs.edryshov_ad.elements.house.roof;

import ru.vsu.cs.edryshov_ad.elements.house.FahverkHouse;
import ru.vsu.cs.edryshov_ad.utils.ColorsUtils;

import java.awt.*;
import java.util.Random;

public class TiledRoof extends Roof {
    private final int tileWidth;
    private final int tileLineCount;
    private final int strokeWidth;

    private final int[] roofTileStarts;
    private final int[] tileHeights;

    public TiledRoof(FahverkHouse house) {
        super(house);

        strokeWidth = Math.max(1, house.getPlankWidth() / 3);

        Random random = new Random();

        tileLineCount = random.nextInt(8, 11);

        tileWidth = house.getSectionWidth() / random.nextInt(5, 7);

        int innerHeight = height - strokeWidth * 2;
        int tileBaseHeight = innerHeight / tileLineCount;

        roofTileStarts = new int[tileLineCount];
        tileHeights = new int[tileLineCount];
        for (int i = 0; i < tileLineCount; i++) {
            while (i > 0 && roofTileStarts[i - 1] == roofTileStarts[i]) {
                roofTileStarts[i] = random.nextInt(tileWidth / 3, tileWidth + 1);
            }
            tileHeights[i] = tileBaseHeight;
        }

        int extraHeight = innerHeight % tileBaseHeight;
        while (extraHeight > 0) {
            tileHeights[random.nextInt(0, tileLineCount)]++;
            extraHeight--;
        }
    }

    @Override
    public void draw(Graphics2D g, int x, int y) {
        Color oldColor = g.getColor();
        Stroke oldStroke = g.getStroke();

        g.setStroke(new BasicStroke(strokeWidth));

        int innerWidth = width - strokeWidth * 2;
        int innerHeight = height - strokeWidth * 2;
        x += strokeWidth;
        y += strokeWidth;

        g.setColor(house.getRoofColor());
        g.fillRect(x, y, innerWidth, innerHeight);

        Color darker = ColorsUtils.getDarker(house.getRoofColor());
        g.setColor(darker);
        g.drawRect(x, y, innerWidth, innerHeight);

        int yOffset = 0;
        for (int i = 0; i < tileLineCount; i++) {
            int posX = roofTileStarts[i];

            while (posX < innerWidth) {
                int currX = x + posX;
                g.drawLine(currX, y + yOffset, currX, y + yOffset + tileHeights[i]);
                posX += tileWidth;
            }

            yOffset += tileHeights[i];

            g.drawLine(x, y + yOffset, x + width - 2 * strokeWidth, y + yOffset);
        }

        g.setStroke(oldStroke);
        g.setColor(oldColor);
    }
}
