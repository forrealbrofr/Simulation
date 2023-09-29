package org.example.entities;

import org.example.Coordinates;

public class Box extends Items{
    public static final String ICON = "📦";
    private Coordinates coordinates;


    @Override
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String getIcon() {
        return ICON;
    }
}
