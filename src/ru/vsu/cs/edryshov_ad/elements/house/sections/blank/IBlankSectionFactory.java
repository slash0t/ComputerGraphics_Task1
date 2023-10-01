package ru.vsu.cs.edryshov_ad.elements.house.sections.blank;

import ru.vsu.cs.edryshov_ad.elements.house.FahverkHouse;

public interface IBlankSectionFactory {
    MidBlankSection createMidBlankSection(FahverkHouse house);
    SideBlankSection createSideBlankSection(FahverkHouse house, boolean isLeft);
}
