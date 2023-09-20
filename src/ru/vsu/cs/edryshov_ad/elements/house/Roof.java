package ru.vsu.cs.edryshov_ad.elements.house;

import ru.vsu.cs.edryshov_ad.elements.DrawElement;

import java.awt.*;
import java.awt.geom.GeneralPath;

public class Roof implements DrawElement {
    private final int width;
    private final int height;

    private final FahverkHouse house;

    private int offsetInnerPlanks;

    private final InclinedPlank roofPlank1;
    private final InclinedPlank roofPlank2;

    private final InclinedPlank innerRoofPlank1;
    private final InclinedPlank innerRoofPlank2;

    public Roof(int width, int height, FahverkHouse house) {
        this.width = width;
        this.height = height;
        this.house = house;

        int roofWidth = house.getPlankWidth() * 2;

        this.roofPlank1 = new InclinedPlank(
                width / 2, height, roofWidth,
                false, true,
                house.getRoofColor()
        );

        this.roofPlank2 = new InclinedPlank(
                width - width / 2, height, roofWidth,
                false, false,
                house.getRoofColor()
        );

        this.offsetInnerPlanks = (int) (house.getRoofOffsetY() / Math.tan(Math.PI / 2 - roofPlank1.getInclineAngle())) + roofPlank1.getProjectionX();
        int innerPlanksWidth = width - offsetInnerPlanks * 2;

        this.innerRoofPlank1 = new InclinedPlank(
                innerPlanksWidth / 2, height - roofPlank1.getProjectionY() - house.getRoofOffsetY(), house.getPlankWidth(),
                false, true,
                house.getWoodColor()
        );
        this.innerRoofPlank2 = new InclinedPlank(
                innerPlanksWidth - innerPlanksWidth / 2, height - roofPlank1.getProjectionY() - house.getRoofOffsetY(), house.getPlankWidth(),
                false, false,
                house.getWoodColor()
        );
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
        Color old = g.getColor();

        roofPlank1.draw(g, x, y);

        roofPlank2.draw(g, x + width / 2, y);

        innerRoofPlank1.draw(g, x + offsetInnerPlanks, y + roofPlank1.getProjectionY());

        int innerPlanksWidth = width - offsetInnerPlanks * 2;
        innerRoofPlank2.draw(g, x + offsetInnerPlanks + innerPlanksWidth / 2, y + roofPlank1.getProjectionY());

        GeneralPath innerFill = new GeneralPath();
        innerFill.moveTo(x + width / 2, y + roofPlank1.getProjectionY() + innerRoofPlank1.getProjectionY());
        innerFill.lineTo(x + offsetInnerPlanks + innerRoofPlank1.getProjectionY(), y + height - house.getRoofOffsetY());
        innerFill.lineTo(
                x + offsetInnerPlanks + innerRoofPlank1.getProjectionY() + width - (offsetInnerPlanks + innerRoofPlank1.getProjectionX()) * 2,
                y + height - house.getRoofOffsetY()
        );
        g.setColor(house.getBaseColor());
        g.fill(innerFill);

        GeneralPath horizontalPlank = new GeneralPath();

        int horizontalPlankOffsetX = getXOnInnerPlan(house.getFloorHeight());
        int horizontalPlankOffsetY = y + height - house.getRoofOffsetY() - house.getFloorHeight();
        horizontalPlank.moveTo(x + horizontalPlankOffsetX, horizontalPlankOffsetY);
        horizontalPlank.lineTo(x + (width - horizontalPlankOffsetX), horizontalPlankOffsetY);

        horizontalPlankOffsetX = getXOnInnerPlan(house.getFloorHeight() + house.getPlankWidth());
        horizontalPlankOffsetY -= house.getPlankWidth();
        horizontalPlank.lineTo(x + (width - horizontalPlankOffsetX), horizontalPlankOffsetY);
        horizontalPlank.lineTo(x + horizontalPlankOffsetX, horizontalPlankOffsetY);

        g.setColor(house.getWoodColor());
        g.fill(horizontalPlank);

        g.setColor(old);
    }

    private int getXOnInnerPlan(int y) {
        return offsetInnerPlanks + innerRoofPlank1.getProjectionX()
                + (int) (y * Math.tan(roofPlank1.getInclineAngle()));
    }
}
