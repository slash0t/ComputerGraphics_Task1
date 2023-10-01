package ru.vsu.cs.edryshov_ad.elements.house.sections.blank;

import ru.vsu.cs.edryshov_ad.elements.house.FahverkHouse;
import ru.vsu.cs.edryshov_ad.elements.house.sections.Section;

public abstract class SideBlankSection extends Section {
    protected final boolean isLeft;

    public SideBlankSection(FahverkHouse house, boolean isLeft) {
        super(house);

        this.isLeft = isLeft;
    }
}
