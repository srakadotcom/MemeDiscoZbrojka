package pl.memexurer.memedisco.tasks;

import pl.memexurer.memedisco.discoarmor.ArmorUpdateMethod;
import pl.memexurer.memedisco.discoarmor.color.DiscoArmorEffect;
import pl.memexurer.memedisco.discoarmor.color.impl.GrayFadeArmorEffect;
import pl.memexurer.memedisco.discoarmor.color.impl.RainbowArmorEffect;
import pl.memexurer.memedisco.discoarmor.color.impl.UltraArmorEffect;
import pl.memexurer.memedisco.discoarmor.data.DiscoPlayer;
import pl.memexurer.memedisco.discoarmor.data.DiscoPlayerData;

public class ColorUpdateTask implements Runnable {
    private final ArmorUpdateMethod updateMethod;
    private final DiscoPlayerData playerData;

    private final DiscoArmorEffect rainbowArmorEffect;
    private final DiscoArmorEffect grayFadeArmorEffect;
    private final DiscoArmorEffect ultraArmorEffect;

    public ColorUpdateTask(DiscoPlayerData playerData, ArmorUpdateMethod method) {
        this.playerData = playerData;
        this.updateMethod = method;
        this.rainbowArmorEffect = new RainbowArmorEffect();
        this.grayFadeArmorEffect = new GrayFadeArmorEffect();
        this.ultraArmorEffect = new UltraArmorEffect();
    }

    @Override
    public void run() {
        this.rainbowArmorEffect.update();
        this.grayFadeArmorEffect.update();
        this.ultraArmorEffect.update();

        for (DiscoPlayer player : playerData.getPlayers()) updateMethod.updateArmor(player);
    }

    public DiscoArmorEffect getRainbowArmorEffect() {
        return rainbowArmorEffect;
    }

    public DiscoArmorEffect getGrayFadeArmorEffect() {
        return grayFadeArmorEffect;
    }

    public DiscoArmorEffect getUltraArmorEffect() {
        return ultraArmorEffect;
    }
}
