package pl.memexurer.memedisco.util;

import org.bukkit.Material;

public enum ItemSlot {
    HEAD(Material.LEATHER_HELMET, 1),
    CHEST(Material.LEATHER_CHESTPLATE, 2),
    LEGS(Material.LEATHER_LEGGINGS, 3),
    FEET(Material.LEATHER_BOOTS, 4);

    private final int slot;
    private final Material armor;

    ItemSlot(Material armor, int slot) {
        this.armor = armor;
        this.slot = slot;
    }

    public int getSlot() {
        return slot;
    }

    public Material getArmor() {
        return armor;
    }
}
