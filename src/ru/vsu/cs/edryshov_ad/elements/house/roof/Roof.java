package ru.vsu.cs.edryshov_ad.elements.house.roof;

import ru.vsu.cs.edryshov_ad.elements.InBoxElement;
import ru.vsu.cs.edryshov_ad.elements.house.FahverkHouse;

public abstract class Roof extends InBoxElement {
    final FahverkHouse house;

    public Roof(FahverkHouse house) {
        super(
                house.getBaseWidth() + house.getRoofOffsetX() * 2,
                house.getFloorHeight() * 4 / 3
        );

        this.house = house;
    }
}
