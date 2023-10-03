package org.example.actions;

import org.example.Map;

public abstract class Actions {
    protected final Map map;

    protected Actions(Map map) {
        this.map = map;
    }

    public abstract void perform();
}
