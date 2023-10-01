package ru.vsu.cs.edryshov_ad.elements.house.sections.window.frame;

import ru.vsu.cs.edryshov_ad.elements.InBoxElement;
import ru.vsu.cs.edryshov_ad.elements.house.FahverkHouse;

public abstract class WindowFrame extends InBoxElement {
    protected final FahverkHouse house;

    public WindowFrame(int width, int height, FahverkHouse house) {
        super(width, height);

        this.house = house;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }
}
