package ru.vsu.cs.edryshov_ad.elements.house;

import ru.vsu.cs.edryshov_ad.elements.house.roof.RoofFactory;
import ru.vsu.cs.edryshov_ad.elements.house.sections.blank.BlankSuperFactory;
import ru.vsu.cs.edryshov_ad.elements.house.sections.door.DoorFactory;
import ru.vsu.cs.edryshov_ad.elements.house.sections.window.decoration.WindowDecorationFactory;
import ru.vsu.cs.edryshov_ad.elements.house.sections.window.frame.WindowFrameFactory;

import java.awt.*;

public class HouseFactory {
    private final WindowFrameFactory frameFactory;
    private final WindowDecorationFactory decorationFactory;
    private final DoorFactory doorFactory;
    private final RoofFactory roofFactory;
    private final BlankSuperFactory blankSuperFactory;

    public HouseFactory(
            WindowFrameFactory frameFactory,
            WindowDecorationFactory decorationFactory,
            DoorFactory doorFactory,
            RoofFactory roofFactory,
            BlankSuperFactory blankSuperFactory) {
        this.frameFactory = frameFactory;
        this.decorationFactory = decorationFactory;
        this.doorFactory = doorFactory;
        this.roofFactory = roofFactory;
        this.blankSuperFactory = blankSuperFactory;
    }

    public WindowFrameFactory getFrameFactory() {
        return frameFactory;
    }

    public WindowDecorationFactory getDecorationFactory() {
        return decorationFactory;
    }

    public DoorFactory getDoorFactory() {
        return doorFactory;
    }

    public RoofFactory getRoofFactory() {
        return roofFactory;
    }

    public BlankSuperFactory getBlankSuperFactory() {
        return blankSuperFactory;
    }

    public FahverkHouse createHouse(
            int floorCount, int sectionCount,
            int floorHeight, int sectionWidth, int plankWidth, int houseNumber,
            Color woodColor, Color windowColor, Color windowFrameColor, Color baseColor, Color roofColor,
            HouseConfiguration configuration
    ) {
        return new FahverkHouse(
                floorCount, sectionCount,
                floorHeight, sectionWidth, plankWidth, houseNumber,
                woodColor, windowColor, windowFrameColor, baseColor, roofColor,
                configuration, this
        );
    }
}
