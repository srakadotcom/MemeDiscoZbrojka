package pl.memexurer.memedisco.config;

import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import pl.memexurer.memedisco.MemeDiscoPlugin;
import pl.memexurer.memedisco.util.ChatUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ConfigInventory {
    private Map<Integer, InventoryConfigEntry> inventoryItemMap;
    private final Inventory configInventory;

    public ConfigInventory(Configuration config) {
        ConfigurationSection inventoryEntrySection = config.getConfigurationSection("items");
        this.inventoryItemMap = inventoryEntrySection.getKeys(false).stream()
                .map(inventoryEntrySection::getConfigurationSection)
                .map(InventoryConfigEntry::new)
                .collect(Collectors.toMap(InventoryConfigEntry::getSlot, Function.identity()));
        this.configInventory = generateInventory(config.getString("title"), config.getInt("size"));
    }

    private Inventory generateInventory(String inventoryTitle, int inventorySize) {
        Inventory inv = Bukkit.createInventory(new ConfigInventoryHolder(this), inventorySize, ChatUtil.fixColor(inventoryTitle));
        for(Map.Entry<Integer, InventoryConfigEntry> entry: inventoryItemMap.entrySet()) {
            inv.setItem(entry.getKey(), entry.getValue().getItem());
        }

        return inv;
    }

    public InventoryConfigEntry getItem(int slot) {
        return inventoryItemMap.get(slot);
    }

    public Inventory getConfigInventory() {
        return configInventory;
    }
}
