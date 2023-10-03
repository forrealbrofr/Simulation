package org.example.entities.items;

public class MouseFood extends Food {
    public static final String ICON = "🧀";
    private final static int BONUS_HEALTH_POINTS = 150;

    public MouseFood() {
        super(BONUS_HEALTH_POINTS);
    }

    @Override
    public String getIcon() {
        return ICON;
    }
}
