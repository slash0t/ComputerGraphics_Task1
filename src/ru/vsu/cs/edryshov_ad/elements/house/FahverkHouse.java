package ru.vsu.cs.edryshov_ad.elements.house;

import ru.vsu.cs.edryshov_ad.elements.IDrawableElement;
import ru.vsu.cs.edryshov_ad.elements.house.roof.Roof;
import ru.vsu.cs.edryshov_ad.elements.house.sections.Section;
import ru.vsu.cs.edryshov_ad.elements.house.sections.blank.IBlankSectionFactory;
import ru.vsu.cs.edryshov_ad.elements.house.sections.window.WindowSection;
import ru.vsu.cs.edryshov_ad.elements.house.sections.window.decoration.WindowDecorationFactory;
import ru.vsu.cs.edryshov_ad.elements.house.sections.window.frame.WindowFrameFactory;


import java.awt.*;

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
    private final Section[][] sections;

    public FahverkHouse(
            int floorCount, int sectionCount,
            int floorHeight, int sectionWidth, int plankWidth, int houseNumber,
            Color woodColor, Color windowColor, Color windowFrameColor, Color baseColor, Color roofColor,
            HouseConfiguration configuration, HouseFactory factory
    ) {
        this.floorCount = floorCount;
        this.sectionCount = sectionCount;
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

        this.roof = factory.getRoofFactory().createRoof(
                configuration.getRoofType(),
                this
        );

        this.sections = createSectionsMatrix(configuration, factory);
    }

    private Section[][] createSectionsMatrix(HouseConfiguration configuration, HouseFactory factory) {
        Section[][] sections = new Section[floorCount][sectionCount];

        sections[0][0] = factory.getDoorFactory().createDoor(configuration.getDoorType(), this);

        fillSectionArray(sections[0], 1, sectionCount, configuration, factory);
        for (int i = 1; i < floorCount; i++) {
            fillSectionArray(sections[i], 0, sectionCount, configuration, factory);
        }
        return sections;
    }

    private void fillSectionArray(Section[] sections, int start, int end, HouseConfiguration configuration, HouseFactory factory) {
        IBlankSectionFactory blankFactory = factory.getBlankSuperFactory().createBlankSectionFactory(
                configuration.getBlankSectionType()
        );
        sections[start] = blankFactory.createSideBlankSection(this, true);

        WindowFrameFactory frameFactory = factory.getFrameFactory();
        WindowDecorationFactory decorationFactory = factory.getDecorationFactory();

        for (int i = 0; i + start + 1 < end - 1; i++) {
            Section section;
            if ((end - start) % 2 == 1 && i % 2 == 1) {
                section = blankFactory.createMidBlankSection(this);
            } else {
                section = new WindowSection(
                        this,
                        frameFactory, decorationFactory,
                        configuration.getFrameType(), configuration.getDecorationType()
                );
            }
            sections[i + start + 1] = section;
        }
        sections[end - 1] = blankFactory.createSideBlankSection(this, false);
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

    public int getSectionWidth() {
        return sectionWidth;
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
        return getBaseWidth() + roofOffsetX * 2;
    }

    @Override
    public int getHeight() {
        return getBaseHeight() + roof.getHeight() - roofOffsetY;
    }

    public int getBaseWidth() {
        return sectionWidth * sectionCount + plankWidth * (sectionCount + 1);
    }

    public int getBaseHeight() {
        return (floorHeight + plankWidth) * floorCount;
    }

    @Override
    public void draw(Graphics2D g, int x, int y) {
        Color old = g.getColor();

        int baseHeight = getBaseHeight();

        g.setColor(woodColor);
        g.fillRect(x, y - baseHeight, getBaseWidth(), baseHeight);

        for (int i = 0; i < sectionCount; i++) {
            for (int j = 0; j < floorCount; j++) {
                int currX = x + plankWidth + (plankWidth + sectionWidth) * i;
                int currY = y - floorHeight - (floorHeight + plankWidth) * j;

                sections[j][i].draw(g, currX, currY);
            }
        }

        roof.draw(g, x - roofOffsetX, y - roof.getHeight() + roofOffsetY - baseHeight);

        g.setColor(old);
    }
}
