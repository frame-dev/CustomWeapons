package ch.framedev.customweapons.arrows;

import org.bukkit.Color;

public enum ArrowType {

    DEFAULT(null),
    SPECTRAL(Color.YELLOW),
    BLUE(Color.BLUE);

    Color color;
    ArrowType(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
