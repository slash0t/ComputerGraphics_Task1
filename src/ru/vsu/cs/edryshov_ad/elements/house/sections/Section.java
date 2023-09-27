package ru.vsu.cs.edryshov_ad.elements.house.sections;

import ru.vsu.cs.edryshov_ad.elements.IDrawableElement;
import ru.vsu.cs.edryshov_ad.elements.house.FahverkHouse;

import java.awt.*;

public abstract class Section implements IDrawableElement {
    final int width;
    final int height;

    final FahverkHouse house;

    public Section(int width, int height, FahverkHouse house) {
        this.width = width;
        this.height = height;
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

    public abstract void draw(Graphics2D g, int x, int y);
}
