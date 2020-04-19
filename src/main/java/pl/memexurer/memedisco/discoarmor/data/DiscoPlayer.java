package pl.memexurer.memedisco.discoarmor.data;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.memexurer.memedisco.MemeDiscoPlugin;
import pl.memexurer.memedisco.discoarmor.color.DiscoArmorEffect;
import pl.memexurer.memedisco.util.ItemSlot;

import java.util.Arrays;
import java.util.UUID;

public class DiscoPlayer {
    private final UUID playerUniqueId;
    private DiscoArmorEffect currentEffect;
    private boolean isCrouching;

    DiscoPlayer(UUID playerUniqueId) {
        this.playerUniqueId = playerUniqueId;
    }

    public void setCurrentEffect(DiscoArmorEffect currentEffect) {
        this.currentEffect = currentEffect;
    }

    private ItemStack[] cloneArray(ItemStack[] item) {
        return Arrays.stream(item).map(ItemStack::new).toArray(ItemStack[]::new);
    }

    public CraftItemStack getItem(ItemSlot slot) {
        if(slot == ItemSlot.FEET) {
            return currentEffect.getBoots();
        } else if(slot == ItemSlot.LEGS) {
            return currentEffect.getLeggings();
        } else if(slot == ItemSlot.CHEST) {
            return currentEffect.getChestplate();
        } else if(slot == ItemSlot.HEAD) {
            return currentEffect.getHelmet();
        } else return null;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(playerUniqueId);
    }

    public void disableArmor() {
        MemeDiscoPlugin.getPluginInstance().getUpdateMethod().resetArmor(this);
        currentEffect = null;
    }

    boolean isArmorEnabled() {
        return currentEffect != null;
    }

    public boolean isCrouching() { return isCrouching; }

    public void setCrouching(boolean bool) {
        this.isCrouching = bool;
    }

    public UUID getUniqueId() {
        return playerUniqueId;
    }

    public DiscoArmorEffect getCurrentEffect() {
        return currentEffect;
    }
}
