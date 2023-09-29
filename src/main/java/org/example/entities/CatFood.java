package org.example.entities;

import org.example.Coordinates;

public class CatFood extends Items{
    public static final String ICON = "🥩";
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
