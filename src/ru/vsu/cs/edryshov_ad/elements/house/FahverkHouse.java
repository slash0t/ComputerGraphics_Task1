package ru.vsu.cs.edryshov_ad.elements.house;

import ru.vsu.cs.edryshov_ad.elements.IDrawableElement;
import ru.vsu.cs.edryshov_ad.elements.house.sections.BlankSection;
import ru.vsu.cs.edryshov_ad.elements.house.sections.DoorSection;
import ru.vsu.cs.edryshov_ad.elements.house.sections.Section;
import ru.vsu.cs.edryshov_ad.elements.house.sections.WindowSection;

import java.awt.*;
import java.util.Random;

public class FahverkHouse implements IDrawableElement {
    private final int floorCount;
    private final int sectionCount;
    private final int floorHeight;
    private final int sectionWidth;
    private final int plankWidth;

    private final int houseNumber;

    private final Color woodColor;
    private final Color windowColor;
    private final Color windowFrameColor;
    private final Color baseColor;
    private final Color roofColor;

    private final int roofOffsetX;
    private final int roofOffsetY;

    private final Roof roof;
    private final BlankSection blankSectionLeft;
    private final BlankSection blankSectionRight;
    private final BlankSection blankSectionMiddle;
    private final DoorSection doorSection;
    private final WindowSection windowSection;

    public FahverkHouse(
            int floorCount, int sectionCount,
            int floorHeight, int sectionWidth, int plankWidth, int houseNumber,
            Color woodColor, Color windowColor, Color windowFrameColor, Color baseColor, Color roofColor
    ) {
        this.floorCount = Math.max(2, floorCount);
        this.sectionCount = Math.max(3, sectionCount);
        this.floorHeight = floorHeight;
        this.sectionWidth = sectionWidth;
        this.plankWidth = plankWidth;

        this.houseNumber = houseNumber;

        this.woodColor = woodColor;
        this.windowColor = windowColor;
        this.windowFrameColor = windowFrameColor;
        this.baseColor = baseColor;
        this.roofColor = roofColor;

        this.roofOffsetX = plankWidth * 3 / 2;
        this.roofOffsetY = plankWidth;

        this.roof = new Roof(getBaseWidth() + plankWidth * 3, floorHeight * 4 / 3, this);

        this.blankSectionLeft = new BlankSection(sectionWidth, floorHeight, this, BlankSection.LEFT_SIDE);
        this.blankSectionMiddle = new BlankSection(sectionWidth, floorHeight, this, BlankSection.MIDDLE);
        this.blankSectionRight = new BlankSection(sectionWidth, floorHeight, this, BlankSection.RIGHT_SIDE);

        this.doorSection = new DoorSection(sectionWidth, floorHeight, this);

        this.windowSection =  new WindowSection(sectionWidth, floorHeight, this, 1);
    }

    public int getPlankWidth() {
        return plankWidth;
    }

    public int getRoofOffsetX() {
        return roofOffsetX;
    }

    public int getRoofOffsetY() {
        return roofOffsetY;
    }

    public int getFloorHeight() {
        return floorHeight;
    }

    public Color getWoodColor() {
        return woodColor;
    }

    public Color getWindowColor() {
        return windowColor;
    }

    public Color getWindowFrameColor() {
        return windowFrameColor;
    }

    public Color getBaseColor() {
        return baseColor;
    }

    public Color getRoofColor() {
        return roofColor;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    @Override
    public int getWidth() {
        return getBaseWidth() + getRoofOffsetX() * 2;
    }

    @Override
    public int getHeight() {
        return getBaseHeight() + roof.getHeight() - roofOffsetY;
    }

    public int getBaseWidth() {
        return sectionWidth * sectionCount + plankWidth * (sectionCount + 1);
    }

    public int getBaseHeight() {
        return floorHeight * floorCount + plankWidth * (floorCount - 1);
    }

    private void drawFirstFloor(Graphics2D g, int x, int y) {
        Color old = g.getColor();

        fillFloor(g, x, y);

        doorSection.draw(g, x + plankWidth, y - floorHeight);

        drawWindowedSectionLine(g, x + plankWidth + sectionWidth, y, sectionCount - 1, sectionCount >= 4);

        g.setColor(old);
    }

    private void drawMiddleFloor(Graphics2D g, int x, int y) {
        Color old = g.getColor();

        fillFloor(g, x, y);

        drawWindowedSectionLine(g, x, y, sectionCount, true);

        g.setColor(old);
    }

    private void fillFloor(Graphics2D g, int x, int y) {
        g.setColor(woodColor);

        int height = floorHeight + plankWidth;
        int width = sectionWidth * sectionCount + (sectionCount + 1) * plankWidth;
        g.fillRect(x, y - height, width, height);
    }

    private void drawWindowedSectionLine(Graphics2D g, int x, int y, int count, boolean withWindows) {
        if (!withWindows) {
            for (int i = 0; i < count; i++) {
                int currX = x + plankWidth + (plankWidth + sectionWidth) * i;
                blankSectionMiddle.draw(g, currX, y - floorHeight);
            }
        } else {
            blankSectionLeft.draw(g, x + plankWidth, y - floorHeight);

            for (int i = 1; i < count - 1; i++) {
                int currX = x + plankWidth + (plankWidth + sectionWidth) * i;

                Section section;
                if (count % 2 == 1 && i % 2 == 0) {
                    section = blankSectionMiddle;
                } else {
                    section = windowSection;
                }

                section.draw(g, currX, y - floorHeight);
            }

            blankSectionRight.draw(g, x + plankWidth + (plankWidth + sectionWidth) * (count - 1), y - floorHeight);
        }
    }

    private void drawRoof(Graphics2D g, int x, int y) {
        roof.draw(g, x - roofOffsetX, y - roof.getHeight() + roofOffsetY);
    }

    @Override
    public void draw(Graphics2D g, int x, int y) {
        drawFirstFloor(g, x, y);

        for (int i = 1; i < floorCount - 1; i++) {
            drawMiddleFloor(g, x, y - (floorHeight + plankWidth) * i);
        }

        int floorY = y - (floorCount - 1) * (floorHeight + plankWidth);

        drawRoof(g, x, floorY);
    }
}
