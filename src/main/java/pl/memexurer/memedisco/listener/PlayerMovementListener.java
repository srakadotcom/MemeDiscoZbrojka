package pl.memexurer.memedisco.listener;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import pl.memexurer.memedisco.discoarmor.data.DiscoPlayer;
import pl.memexurer.memedisco.discoarmor.data.DiscoPlayerData;

public class PlayerMovementListener implements Listener {
    private final DiscoPlayerData playerData;

    public PlayerMovementListener(DiscoPlayerData playerData) {
        this.playerData = playerData;
    }

    @EventHandler
    public void onCrouch(PlayerToggleSneakEvent e) {
        DiscoPlayer player = playerData.getPlayer(e.getPlayer().getUniqueId());
        if(player.getCurrentEffect() == null) return;

        if(e.getPlayer().getGameMode() == GameMode.CREATIVE && e.isSneaking()) {
            e.getPlayer().sendMessage(ChatColor.RED + "Nie mozesz zobaczyc swojej zbrojki na creativie!");
            return;
        }

        if (player.isCrouching() && !e.isSneaking())
            e.getPlayer().updateInventory();


        playerData.getPlayer(e.getPlayer().getUniqueId()).setCrouching(e.isSneaking());
    }
}
