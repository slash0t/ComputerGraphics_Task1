package ru.vsu.cs.edryshov_ad.elements.house.roof;

import ru.vsu.cs.edryshov_ad.elements.house.FahverkHouse;

public class RoofFactory {
    public Roof createRoof(RoofTypes type, FahverkHouse house) {
        switch (type) {
            case PLANKED_ROOF:
                return new PlankedRoof(house);
            default:
                return null;
        }
    }
}
