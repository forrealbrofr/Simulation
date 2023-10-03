package org.example.entities.items;

public class CatFood extends Food {
    public static final String ICON = "🥩";
    private final static int BONUS_HEALTH_POINTS = 100;

    public CatFood() {
        super(BONUS_HEALTH_POINTS);
    }

    @Override
    public String getIcon() {
        return ICON;
    }

}
