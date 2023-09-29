package org.example.entities;

import org.example.Coordinates;

public class MouseFood extends Items{
    public static final String ICON = "🧀";
    Coordinates coordinates;

    @Override
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String getIcon() {
        return ICON;
    }


}
