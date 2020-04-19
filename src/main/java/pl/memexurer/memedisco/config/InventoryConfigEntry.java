package pl.memexurer.memedisco.config;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;
import pl.memexurer.memedisco.util.ChatUtil;

import java.util.List;

public class InventoryConfigEntry {
    private InventoryAction action;
    private MaterialData material;
    private String name;
    private List<String> lore;
    private int slot;

    InventoryConfigEntry(ConfigurationSection section) {
        this.action = InventoryAction.valueOf(section.getString("action"));
        this.material = new MaterialData(Material.getMaterial(section.getString("type").split(":")[0]), Byte.parseByte(section.getString("type").split(":")[1]));
        this.name = ChatUtil.fixColor(section.getString("name"));
        this.lore = ChatUtil.fixColor(section.getStringList("lore"));
        this.slot = Integer.parseInt(section.getName());
    }

    int getSlot() {
        return slot;
    }

    public InventoryAction getAction() {
        return action;
    }

    ItemStack getItem() {
        ItemStack item = new ItemStack(material.getItemType(), 1, material.getData());
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatUtil.fixColor(name));
        itemMeta.setLore(ChatUtil.fixColor(lore));
        item.setItemMeta(itemMeta);

        return item;
    }
}
