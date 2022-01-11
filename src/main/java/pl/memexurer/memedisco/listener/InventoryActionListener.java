package pl.memexurer.memedisco.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import pl.memexurer.memedisco.config.ConfigInventoryHolder;
import pl.memexurer.memedisco.discoarmor.data.DiscoPlayerData;

public class InventoryActionListener implements Listener {
    private final DiscoPlayerData playerData;

    public InventoryActionListener(DiscoPlayerData playerData) {
        this.playerData = playerData;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getInventory() == null || !(e.getInventory().getHolder() instanceof ConfigInventoryHolder)) return;
        ConfigInventoryHolder holder = (ConfigInventoryHolder) e.getInventory().getHolder();
        e.setCancelled(true);
        holder.getConfigInventory().getItem(e.getRawSlot()).getAction().getExecutor().execute(playerData.getPlayer(e.getWhoClicked().getUniqueId()));
    }

}
