package ru.vsu.cs.edryshov_ad.elements;

import java.awt.*;

public interface IDrawableElement {
    int getWidth();
    int getHeight();
    void draw(Graphics2D g, int x, int y);
}
