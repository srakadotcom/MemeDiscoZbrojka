package pl.memexurer.memedisco.config;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class ConfigInventoryHolder implements InventoryHolder {
    private ConfigInventory inventory;

    ConfigInventoryHolder(ConfigInventory inventory) {
        this.inventory = inventory;
    }

    public ConfigInventory getConfigInventory() {
        return inventory;
    }

    @Override
    public Inventory getInventory() {
        return null;
    }
}
