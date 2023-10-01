package ru.vsu.cs.edryshov_ad.elements.house.sections.door;

import ru.vsu.cs.edryshov_ad.elements.house.FahverkHouse;

public class DoorFactory {
    public DoorSection createDoor(DoorTypes type, FahverkHouse house) {
        switch (type) {
            case SOLID_DOOR:
                return new SolidDoor(house);
            default:
                return null;
        }
    }
}
