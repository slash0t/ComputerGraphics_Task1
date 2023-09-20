package ru.vsu.cs.edryshov_ad.elements.house;

import ru.vsu.cs.edryshov_ad.elements.DrawElement;

import java.awt.*;
import java.awt.geom.GeneralPath;

public class InclinedPlank implements DrawElement {
    private final int width;
    private final int height;

    private final boolean isMirrored;
    private final boolean drawAbove;

    private final Color color;

    private final double inclineAngle;

    private final int projectionX;
    private final int projectionY;

    public InclinedPlank(int width, int height, int plankWidth, boolean drawAbove, boolean isMirrored, Color color) {
        this.width = width;
        this.height = height;

        this.drawAbove = drawAbove;
        this.isMirrored = isMirrored;

        this.color = color;

        inclineAngle = Math.atan(((double) width) / height);
        projectionY = (int) (plankWidth / Math.sin(inclineAngle));
        projectionX = (int) (plankWidth / Math.cos(inclineAngle));
    }

    public int getProjectionX() {
        return projectionX;
    }

    public int getProjectionY() {
        return projectionY;
    }

    public double getInclineAngle() {
        return inclineAngle;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void draw(Graphics2D g, int x, int y) {
        GeneralPath path = new GeneralPath();

        int startX = !isMirrored ? x : x + width;
        int endX = !isMirrored ? x + width : x;

        path.moveTo(startX, y);

        int mirroredSign = isMirrored ? -1 : 1;
        if (!drawAbove) {
            path.lineTo(startX, y + projectionY);
            path.lineTo(endX - projectionX * mirroredSign, y + height);
        } else {
            path.lineTo(startX + projectionX * mirroredSign, y);
            path.lineTo(endX, y + height - projectionY);
        }
        path.lineTo(endX, y + height);

        Color old = g.getColor();
        g.setColor(color);

        g.fill(path);

        g.setColor(old);
    }
}
