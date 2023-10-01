package ru.vsu.cs.edryshov_ad.elements.house.sections;

import ru.vsu.cs.edryshov_ad.elements.InBoxElement;
import ru.vsu.cs.edryshov_ad.elements.house.FahverkHouse;

import java.awt.*;

public abstract class Section extends InBoxElement {
    protected final FahverkHouse house;

    public Section(FahverkHouse house) {
        super(house.getSectionWidth(), house.getFloorHeight());

        this.house = house;
    }

    @Override
    public void draw(Graphics2D g, int x, int y) {
        Color old = g.getColor();
        g.setColor(house.getBaseColor());

        g.fillRect(x, y, width, height);

        g.setColor(old);
    }
}
