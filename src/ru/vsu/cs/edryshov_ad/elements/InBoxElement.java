package ru.vsu.cs.edryshov_ad.elements;

public abstract class InBoxElement implements IDrawableElement{
    protected final int width;
    protected final int height;

    public InBoxElement(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
