package ru.vsu.cs.edryshov_ad.elements.house.sections.blank;

import ru.vsu.cs.edryshov_ad.elements.house.sections.blank.crossed.CrossedSectionFactory;
import ru.vsu.cs.edryshov_ad.elements.house.sections.blank.l_styled.LStyledSectionFactory;

public class BlankSuperFactory {
    public IBlankSectionFactory createBlankSectionFactory(BlankSectionTypes type) {
        switch (type) {
            case L_STYLED:
                return new LStyledSectionFactory();
            case CROSSED:
                return new CrossedSectionFactory();
            default:
                return null;
        }
    }
}
