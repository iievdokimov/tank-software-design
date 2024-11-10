package ru.mipt.bit.platformer.visuals;

public class HealthBarSettings {
    private boolean state;

    public HealthBarSettings(boolean state){
        this.state = state;
    }

    public void toggle(){
        state = !state;
    }

    public boolean isOn(){
        return state;
    }
}
