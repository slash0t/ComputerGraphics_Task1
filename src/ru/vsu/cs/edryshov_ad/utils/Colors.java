package ru.vsu.cs.edryshov_ad.utils;

import java.awt.*;

public class Colors {
    private static final int SHADOW_DIFFERENCE = 20;

    public static Color getDarker(Color color) {
        return new Color(
                Math.max(color.getRed() - SHADOW_DIFFERENCE, 0),
                Math.max(color.getGreen() - SHADOW_DIFFERENCE, 0),
                Math.max(color.getBlue() - SHADOW_DIFFERENCE, 0)
        );
    }
}
