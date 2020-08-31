package pl.memexurer.memedisco.listener;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.ItemMeta;
import pl.memexurer.memedisco.config.ConfigInventoryHolder;
import pl.memexurer.memedisco.discoarmor.data.DiscoPlayerData;

public class InventoryActionListener implements Listener {
    private DiscoPlayerData playerData;

    public InventoryActionListener(DiscoPlayerData playerData) {
        this.playerData = playerData;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        ItemMeta meta = e.getCurrentItem().getItemMeta();
        if (meta.hasItemFlag(ItemFlag.HIDE_ENCHANTS) && meta.hasItemFlag(ItemFlag.HIDE_PLACED_ON) && meta.hasEnchant(Enchantment.LOOT_BONUS_BLOCKS)) {
            e.getCurrentItem().setType(Material.AIR);
            e.setCancelled(true);
            return;
        }

        if (e.getInventory() == null || !(e.getInventory().getHolder() instanceof ConfigInventoryHolder)) return;
        ConfigInventoryHolder holder = (ConfigInventoryHolder) e.getInventory().getHolder();
        e.setCancelled(true);
        holder.getConfigInventory().getItem(e.getRawSlot()).getAction().getExecutor().execute(playerData.getPlayer(e.getWhoClicked().getUniqueId()));
    }

}
