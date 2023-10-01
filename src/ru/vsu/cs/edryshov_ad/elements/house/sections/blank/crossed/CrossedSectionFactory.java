package ru.vsu.cs.edryshov_ad.elements.house.sections.blank.crossed;

import ru.vsu.cs.edryshov_ad.elements.house.FahverkHouse;
import ru.vsu.cs.edryshov_ad.elements.house.sections.blank.IBlankSectionFactory;
import ru.vsu.cs.edryshov_ad.elements.house.sections.blank.MidBlankSection;
import ru.vsu.cs.edryshov_ad.elements.house.sections.blank.SideBlankSection;

public class CrossedSectionFactory implements IBlankSectionFactory {

    @Override
    public MidBlankSection createMidBlankSection(FahverkHouse house) {
        return new CrossedMidSection(house);
    }

    @Override
    public SideBlankSection createSideBlankSection(FahverkHouse house, boolean isLeft) {
        return new CrossedSideSection(house, isLeft);
    }
}
