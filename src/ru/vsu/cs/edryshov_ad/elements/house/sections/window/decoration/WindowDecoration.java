package ru.vsu.cs.edryshov_ad.elements.house.sections.window.decoration;

import ru.vsu.cs.edryshov_ad.elements.InBoxElement;
import ru.vsu.cs.edryshov_ad.elements.house.FahverkHouse;

public abstract class WindowDecoration extends InBoxElement {
    protected final FahverkHouse house;

    public WindowDecoration(int width, int height, FahverkHouse house) {
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
