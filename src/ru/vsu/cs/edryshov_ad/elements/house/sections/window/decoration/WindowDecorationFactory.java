package ru.vsu.cs.edryshov_ad.elements.house.sections.window.decoration;

import ru.vsu.cs.edryshov_ad.elements.house.FahverkHouse;

public class WindowDecorationFactory {
    public WindowDecoration createWindowDecoration(WindowDecorationTypes type, int width, int height, FahverkHouse house) {
        switch (type) {
            case V_STYLED:
                return new WindowVStyledDecoration(width, height, house);
            case VERTICAL:
                return new WindowVerticalDecoration(width, height, house);
            case CROSS:
                return new WindowCrossDecoration(width, height, house);
            default:
                return null;
        }
    }
}
