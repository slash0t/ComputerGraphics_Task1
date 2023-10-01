package ru.vsu.cs.edryshov_ad.elements.house.sections.blank.simple;

import ru.vsu.cs.edryshov_ad.elements.house.FahverkHouse;
import ru.vsu.cs.edryshov_ad.elements.house.sections.blank.IBlankSectionFactory;
import ru.vsu.cs.edryshov_ad.elements.house.sections.blank.MidBlankSection;
import ru.vsu.cs.edryshov_ad.elements.house.sections.blank.SideBlankSection;

public class SimpleSectionFactory implements IBlankSectionFactory {
    @Override
    public MidBlankSection createMidBlankSection(FahverkHouse house) {
        return new SimpleMidSection(house);
    }

    @Override
    public SideBlankSection createSideBlankSection(FahverkHouse house, boolean isLeft) {
        return new SimpleSideSection(house, isLeft);
    }
}
