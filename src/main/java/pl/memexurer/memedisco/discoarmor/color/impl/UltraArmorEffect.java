package pl.memexurer.memedisco.discoarmor.color.impl;

import pl.memexurer.memedisco.discoarmor.color.DiscoArmorEffect;

import java.util.Random;

public class UltraArmorEffect extends DiscoArmorEffect {
    private static final Random RANDOM = new Random();
    @Override
    public void doTick() {
        this.r = RANDOM.nextInt(255);
        this.g = RANDOM.nextInt(255);
        this.b = RANDOM.nextInt(255);
    }
}
