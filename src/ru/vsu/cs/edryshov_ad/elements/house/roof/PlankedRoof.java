package ru.vsu.cs.edryshov_ad.elements.house.roof;

import ru.vsu.cs.edryshov_ad.elements.house.FahverkHouse;
import ru.vsu.cs.edryshov_ad.elements.house.InclinedPlank;

import java.awt.*;
import java.awt.geom.GeneralPath;

public class PlankedRoof extends Roof {
    private final double offsetInnerPlanks;

    private final InclinedPlank roofPlank1;
    private final InclinedPlank roofPlank2;

    private final InclinedPlank innerRoofPlank1;
    private final InclinedPlank innerRoofPlank2;

    private final InclinedPlank smallPlank1;
    private final InclinedPlank smallPlank2;

    private final int horizontalPlankPlacement;
    private final int plankWidth;

    public PlankedRoof(FahverkHouse house) {
        super(house);

        int roofWidth = house.getPlankWidth() * 2;

        int middle = (width - 1) / 2;

        this.roofPlank1 = new InclinedPlank(
                middle, height, roofWidth,
                false,
                true,
                house.getRoofColor()
        );

        this.roofPlank2 = new InclinedPlank(
                width - roofPlank1.getWidth(), height, roofWidth,
                false,
                false,
                house.getRoofColor()
        );

        this.offsetInnerPlanks = house.getRoofOffsetY() / Math.tan(Math.PI / 2 - roofPlank1.getInclineAngle()) + roofPlank1.getProjectionX();

        this.plankWidth = house.getPlankWidth();

        this.innerRoofPlank1 = new InclinedPlank(
                roofPlank1.getWidth() - (int) offsetInnerPlanks,
                height - (int) roofPlank1.getProjectionY() - house.getRoofOffsetY(),
                plankWidth,
                false,
                true,
                house.getWoodColor()
        );
        this.innerRoofPlank2 = new InclinedPlank(
                roofPlank2.getWidth() - (int) offsetInnerPlanks,
                height - (int) roofPlank2.getProjectionY() - house.getRoofOffsetY(),
                plankWidth,
                false,
                false,
                house.getWoodColor()
        );

        this.horizontalPlankPlacement = (int) (height - roofPlank1.getProjectionY() - innerRoofPlank1.getProjectionY()) * 3 / 7;

        double xLeft = getXOnInnerPlank(horizontalPlankPlacement, true);
        double xRight = getXOnInnerPlank(horizontalPlankPlacement, false);
        this.smallPlank1 = new InclinedPlank(
                middle - plankWidth * 3 / 2 - (int) xLeft,
                horizontalPlankPlacement + plankWidth,
                plankWidth,
                false,
                true,
                house.getWoodColor()
        );
        this.smallPlank2 = new InclinedPlank(
                (int) xRight - middle - plankWidth,
                horizontalPlankPlacement + plankWidth,
                plankWidth,
                false,
                false,
                house.getWoodColor()
        );
    }

    @Override
    public void draw(Graphics2D g, int x, int y) {
        Color old = g.getColor();

        roofPlank1.draw(g, x, y);

        roofPlank2.draw(g, x + roofPlank1.getWidth(), y);

        innerRoofPlank1.draw(g, x + (int) offsetInnerPlanks, y + (int) roofPlank1.getProjectionY());

        innerRoofPlank2.draw(g, x + innerRoofPlank1.getWidth() + (int) offsetInnerPlanks, y + (int) roofPlank2.getProjectionY());

        GeneralPath innerFill = new GeneralPath();
        innerFill.moveTo(x + roofPlank1.getWidth(), y + roofPlank1.getProjectionY() + innerRoofPlank1.getProjectionY());
        innerFill.lineTo(x + offsetInnerPlanks + innerRoofPlank1.getProjectionY(), y + height - house.getRoofOffsetY());
        innerFill.lineTo(
                x + offsetInnerPlanks + innerRoofPlank1.getProjectionY() + width - (offsetInnerPlanks + innerRoofPlank1.getProjectionX()) * 2,
                y + height - house.getRoofOffsetY()
        );
        g.setColor(house.getBaseColor());
        g.fill(innerFill);

        drawInnerDecoration(g, x, y);

        g.setColor(old);
    }

    private void drawInnerDecoration(Graphics2D g, int x, int y) {
        g.setColor(house.getWoodColor());

        GeneralPath horizontalPlank = new GeneralPath();

        int horizontalPlankOffsetY = y + height - house.getRoofOffsetY() - horizontalPlankPlacement;
        double xLeft = x + getXOnInnerPlank(horizontalPlankPlacement, true);
        double xRight = x + getXOnInnerPlank(horizontalPlankPlacement, false);

        horizontalPlank.moveTo(xLeft, horizontalPlankOffsetY);
        horizontalPlank.lineTo(xRight, horizontalPlankOffsetY);

        horizontalPlankOffsetY -= plankWidth;

        horizontalPlank.lineTo(x + getXOnInnerPlank(horizontalPlankPlacement + plankWidth, false), horizontalPlankOffsetY);
        horizontalPlank.lineTo(x + getXOnInnerPlank(horizontalPlankPlacement + plankWidth, true), horizontalPlankOffsetY);
        g.fill(horizontalPlank);

        g.fillRect((int) xLeft + plankWidth / 2, horizontalPlankOffsetY + plankWidth, plankWidth, horizontalPlankPlacement);
        g.fillRect((int) xRight - plankWidth, horizontalPlankOffsetY + plankWidth, plankWidth, horizontalPlankPlacement);


        int middle = x + (width - 1) / 2;
        double verticalPlankY = y + roofPlank1.getProjectionY() + innerRoofPlank1.getProjectionY() * 0.75;
        g.fillRect(middle - plankWidth / 2, (int) verticalPlankY, plankWidth, height - (int) verticalPlankY + y);

        int bottomY = y + height - smallPlank1.getHeight() - house.getRoofOffsetY();
        smallPlank1.draw(g, middle - smallPlank1.getWidth(), bottomY);
        smallPlank2.draw(g, middle, bottomY);

        drawSmallestPlank(
                g,
                x + (int) offsetInnerPlanks + innerRoofPlank1.getProjectionX() / 2,
                y,
                middle - smallPlank1.getWidth() - plankWidth,
                true
        );

        drawSmallestPlank(
                g,
                x + width - (int) offsetInnerPlanks - innerRoofPlank2.getProjectionX() / 2,
                y,
                middle + smallPlank2.getWidth() + plankWidth,
                false
        );
    }

    private void drawSmallestPlank(Graphics2D g, double x, double y, double plankStart, boolean isLeftSided) {
        GeneralPath path = new GeneralPath();

        double y0 = y + height - house.getRoofOffsetY();
        double x0, angle1, angle2;
        if (isLeftSided) {
            x0 = plankStart - smallPlank1.getProjectionX();
            angle1 = Math.PI - smallPlank1.getInclineAngle();
            angle2 = innerRoofPlank1.getInclineAngle();
        } else {
            x0 = plankStart + smallPlank2.getProjectionX();
            angle1 = smallPlank2.getInclineAngle();
            angle2 = Math.PI - innerRoofPlank2.getInclineAngle();
        }

        path.moveTo(x0, y0);
        path.lineTo(plankStart, y0);

        double intersection1X = getIntersectionX(angle1, angle2, x, plankStart);
        double intersection1Y = (intersection1X - x) / Math.tan(angle2);
        path.lineTo(intersection1X, y0 - intersection1Y);

        double intersection2X = getIntersectionX(angle1, angle2, x, x0);
        double intersection2Y = (intersection2X - x) / Math.tan(angle2);
        path.lineTo(intersection2X, y0 - intersection2Y);

        g.fill(path);
    }

    private double getXOnInnerPlank(double y, boolean isLeftSide) {
        double projection = isLeftSide ? innerRoofPlank1.getProjectionX() : innerRoofPlank2.getProjectionX();
        double angle = isLeftSide ? innerRoofPlank1.getInclineAngle() : innerRoofPlank2.getInclineAngle();

        double x = offsetInnerPlanks + projection / 2.0 + y * Math.tan(angle);
        return isLeftSide ? x : width - x;
    }

    private double getIntersectionX(double angle1, double angle2, double x1, double x2) {
        return (x2 - x1) / (Math.tan(angle2) / Math.tan(angle1) - 1) + x2;
    }
}
