package ru.vsu.cs.edryshov_ad.elements.house.sections.window.frame;

import ru.vsu.cs.edryshov_ad.elements.house.FahverkHouse;

public class WindowFrameFactory {
    public WindowFrame createWindowFrame(WindowFrameTypes type, int width, int height, FahverkHouse house) {
        switch (type) {
            case SIX_SECTION_FRAME:
                return new WindowSixSectionFrame(width, height, house);
            default:
                return null;
        }
    }
}
