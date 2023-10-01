package ru.vsu.cs.edryshov_ad.elements.house.sections.window;

import ru.vsu.cs.edryshov_ad.elements.house.FahverkHouse;
import ru.vsu.cs.edryshov_ad.elements.house.sections.Section;
import ru.vsu.cs.edryshov_ad.elements.house.sections.window.decoration.WindowDecoration;
import ru.vsu.cs.edryshov_ad.elements.house.sections.window.decoration.WindowDecorationFactory;
import ru.vsu.cs.edryshov_ad.elements.house.sections.window.decoration.WindowDecorationTypes;
import ru.vsu.cs.edryshov_ad.elements.house.sections.window.frame.WindowFrame;
import ru.vsu.cs.edryshov_ad.elements.house.sections.window.frame.WindowFrameFactory;
import ru.vsu.cs.edryshov_ad.elements.house.sections.window.frame.WindowFrameTypes;

import java.awt.*;

public class WindowSection extends Section {
    private final WindowDecoration decoration;
    private final WindowFrame frame;

    private final int windowHeight;
    private final int aboveWindowHeight;
    private final int borderPlankWidth;

    public WindowSection(
            FahverkHouse house,
            WindowFrameFactory frameFactory, WindowDecorationFactory decorationFactory,
            WindowFrameTypes frameType, WindowDecorationTypes decorationType
    ) {
        super(house);

        windowHeight = height * 7 / 10;
        borderPlankWidth = house.getPlankWidth() * 2 / 3;

        int insideWindowHeight = windowHeight - borderPlankWidth * 2;
        frame = frameFactory.createWindowFrame(frameType, width, insideWindowHeight, house);

        int belowWindowHeight = height / 4;
        decoration = decorationFactory.createWindowDecoration(decorationType, width, belowWindowHeight, house);

        aboveWindowHeight = height - windowHeight - belowWindowHeight;
    }

    @Override
    public void draw(Graphics2D g, int x, int y) {
        super.draw(g, x, y);

        Color old = g.getColor();

        g.setColor(house.getWindowFrameColor());
        g.fillRect(x, y + aboveWindowHeight, width, windowHeight);

        g.setColor(house.getWoodColor());
        g.fillRect(x, y + aboveWindowHeight, width, borderPlankWidth);
        g.fillRect(x, y + aboveWindowHeight + windowHeight - borderPlankWidth, width, borderPlankWidth);

        frame.draw(g, x, y + aboveWindowHeight + borderPlankWidth);
        decoration.draw(g, x, y + aboveWindowHeight + windowHeight);

        g.setColor(old);
    }
}
