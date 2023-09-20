package ru.vsu.cs.edryshov_ad.elements;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public interface DrawElement {
    public int getWidth();
    public int getHeight();
    public void draw(Graphics2D g, int x, int y);
}
