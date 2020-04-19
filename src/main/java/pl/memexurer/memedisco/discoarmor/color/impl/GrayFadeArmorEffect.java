package pl.memexurer.memedisco.discoarmor.color.impl;

import pl.memexurer.memedisco.discoarmor.color.DiscoArmorEffect;

public class GrayFadeArmorEffect extends DiscoArmorEffect {
    private int grayscale;

    @Override
    public void doTick() {
        if(grayscale > 250) {
            grayscale = 0;
        } else grayscale += 3;
    }
}
