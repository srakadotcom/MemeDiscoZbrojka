package pl.memexurer.memedisco.discoarmor.color.impl;

import pl.memexurer.memedisco.discoarmor.color.DiscoArmorEffect;

public class RainbowArmorEffect extends DiscoArmorEffect {
    public RainbowArmorEffect() {
        this.r = 255;
        this.g = 0;
        this.b = 0;
    }

    @Override
    public void doTick() {

        if(r > 3 && b < 4){
            r -= 3;
            g += 3;
        }
        if(g > 3 && r < 4){
            g -= 3;
            b += 3;
        }
        if(b > 3 && g < 4){
            r += 3;
            b -= 3;
        }
    }
}
