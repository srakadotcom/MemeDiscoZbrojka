package pl.memexurer.memedisco.config;

import pl.memexurer.memedisco.MemeDiscoPlugin;

public enum InventoryAction {
    RESET(player -> {
        if (player.getCurrentEffect() != null) player.disableArmor();
    }),
    GRAY_FADE_EFFECT(player -> player.setCurrentEffect(MemeDiscoPlugin.getPluginInstance().getColorUpdateTask().getGrayFadeArmorEffect())),
    RAINBOW_EFFECT(player -> player.setCurrentEffect(MemeDiscoPlugin.getPluginInstance().getColorUpdateTask().getRainbowArmorEffect())),
    RANDOM_EFFECT(player -> player.setCurrentEffect(MemeDiscoPlugin.getPluginInstance().getColorUpdateTask().getUltraArmorEffect())),
    NONE(player -> {});
    private InventoryEntryExecutor executor;

    InventoryAction(InventoryEntryExecutor e) {
        this.executor = e;
    }

    public InventoryEntryExecutor getExecutor() {
        return executor;
    }
}
