package ru.vsu.cs.edryshov_ad.elements.house;

import ru.vsu.cs.edryshov_ad.elements.InBoxElement;

import java.awt.*;
import java.awt.geom.GeneralPath;

public class InclinedFitPlank extends InBoxElement {
    private final boolean isMirrored;

    private final Color color;

    private final double projectionX;

    public InclinedFitPlank(int width, int height, int plankWidth, boolean isMirrored, Color color) {
        super(width, height);

        this.color = color;
        this.isMirrored = isMirrored;

        double inclineAngle = Math.atan(height / (double) width);

        this.projectionX = plankWidth / Math.sin(inclineAngle);
    }

    @Override
    public void draw(Graphics2D g, int x, int y) {
        GeneralPath plank = new GeneralPath();

        int yStart, yEnd;
        if (isMirrored) {
            yStart = y;
            yEnd = y + height;
        } else {
            yStart = y + height;
            yEnd = y;
        }

        plank.moveTo(x, yStart);
        plank.lineTo(x + projectionX, yStart);
        plank.lineTo(x + width, yEnd);
        plank.lineTo(x + width - projectionX, yEnd);

        Color old = g.getColor();
        g.setColor(color);
        g.fill(plank);
        g.setColor(old);
    }
}
