package ru.vsu.cs.edryshov_ad.elements.house.sections.window.decoration;

import ru.vsu.cs.edryshov_ad.elements.house.FahverkHouse;

public class WindowDecorationFactory {
    public WindowDecoration createWindowDecoration(WindowDecorationTypes type, int width, int height, FahverkHouse house) {
        switch (type) {
            case V_STYLED:
                return new WindowVStyledDecoration(width, height, house);
            default:
                return null;
        }
    }
}
