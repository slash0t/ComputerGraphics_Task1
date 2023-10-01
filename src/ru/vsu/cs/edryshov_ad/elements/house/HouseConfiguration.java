package ru.vsu.cs.edryshov_ad.elements.house;

import ru.vsu.cs.edryshov_ad.elements.house.roof.RoofTypes;
import ru.vsu.cs.edryshov_ad.elements.house.sections.blank.BlankSectionTypes;
import ru.vsu.cs.edryshov_ad.elements.house.sections.door.DoorTypes;
import ru.vsu.cs.edryshov_ad.elements.house.sections.window.decoration.WindowDecorationTypes;
import ru.vsu.cs.edryshov_ad.elements.house.sections.window.frame.WindowFrameTypes;

import java.util.Random;

public class HouseConfiguration {
    private final WindowFrameTypes frameType;
    private final WindowDecorationTypes decorationType;
    private final BlankSectionTypes blankSectionFactory;
    private final DoorTypes doorType;
    private final RoofTypes roofType;

    public HouseConfiguration(
            WindowFrameTypes frameType, WindowDecorationTypes decorationType,
            BlankSectionTypes blankSectionFactory, DoorTypes doorType, RoofTypes roofType
    ) {
        this.frameType = frameType;
        this.decorationType = decorationType;
        this.blankSectionFactory = blankSectionFactory;
        this.doorType = doorType;
        this.roofType = roofType;
    }

    public HouseConfiguration() {
        Random random = new Random();

        this.frameType = WindowFrameTypes.values()[random.nextInt(0, WindowFrameTypes.values().length)];
        this.decorationType = WindowDecorationTypes.values()[random.nextInt(0, WindowDecorationTypes.values().length)];
        this.doorType = DoorTypes.values()[random.nextInt(0, DoorTypes.values().length)];
        this.roofType = RoofTypes.values()[random.nextInt(0, RoofTypes.values().length)];
        this.blankSectionFactory = BlankSectionTypes.values()[random.nextInt(0, BlankSectionTypes.values().length)];
    }

    public WindowFrameTypes getFrameType() {
        return frameType;
    }

    public WindowDecorationTypes getDecorationType() {
        return decorationType;
    }

    public BlankSectionTypes getBlankSectionType() {
        return blankSectionFactory;
    }

    public DoorTypes getDoorType() {
        return doorType;
    }

    public RoofTypes getRoofType() {
        return roofType;
    }
}
