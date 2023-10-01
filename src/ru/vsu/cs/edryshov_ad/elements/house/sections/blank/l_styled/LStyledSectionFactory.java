package ru.vsu.cs.edryshov_ad.elements.house.sections.blank.l_styled;

import ru.vsu.cs.edryshov_ad.elements.house.FahverkHouse;
import ru.vsu.cs.edryshov_ad.elements.house.sections.blank.IBlankSectionFactory;
import ru.vsu.cs.edryshov_ad.elements.house.sections.blank.MidBlankSection;
import ru.vsu.cs.edryshov_ad.elements.house.sections.blank.SideBlankSection;

public class LStyledSectionFactory implements IBlankSectionFactory {
    @Override
    public MidBlankSection createMidBlankSection(FahverkHouse house) {
        return new LStyledMidSection(house);
    }

    @Override
    public SideBlankSection createSideBlankSection(FahverkHouse house, boolean isLeft) {
        return new LStyledSideSection(house, isLeft);
    }
}
