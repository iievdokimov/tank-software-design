package ru.mipt.bit.platformer.visuals.actions;

import ru.mipt.bit.platformer.logics.actions.Action;
import ru.mipt.bit.platformer.visuals.HealthBarSettings;

public class ToggleShowHealthAction implements Action {
    private final HealthBarSettings healthBarSettings;

    public ToggleShowHealthAction(HealthBarSettings healthBarSettings){
        this.healthBarSettings = healthBarSettings;
    }

    @Override
    public void process() {
        healthBarSettings.toggle();
    }
}
