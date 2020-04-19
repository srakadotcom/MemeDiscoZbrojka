package pl.memexurer.memedisco;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import pl.memexurer.memedisco.config.ConfigInventory;
import pl.memexurer.memedisco.discoarmor.ArmorUpdateMethod;
import pl.memexurer.memedisco.discoarmor.PacketUpdateMethod;
import pl.memexurer.memedisco.discoarmor.data.DiscoPlayerData;
import pl.memexurer.memedisco.listener.InventoryActionListener;
import pl.memexurer.memedisco.listener.PlayerMovementListener;
import pl.memexurer.memedisco.tasks.ColorUpdateTask;

public final class MemeDiscoPlugin extends JavaPlugin implements Listener {
    private static MemeDiscoPlugin PLUGIN_INSTANCE;
    private final DiscoPlayerData playerData = new DiscoPlayerData();

    private ArmorUpdateMethod updateMethod;
    private ColorUpdateTask colorUpdateTask;
    private ConfigInventory configInventory;

    public static MemeDiscoPlugin getPluginInstance() {
        return PLUGIN_INSTANCE;
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        MemeDiscoPlugin.PLUGIN_INSTANCE = this;

        this.updateMethod = new PacketUpdateMethod();
        this.colorUpdateTask = new ColorUpdateTask(playerData, updateMethod);
        this.configInventory = new ConfigInventory(getConfig());
        Bukkit.getScheduler().runTaskTimerAsynchronously(this, colorUpdateTask, 0L, 5L);


        Bukkit.getPluginManager().registerEvents(new InventoryActionListener(playerData), this);
        Bukkit.getPluginManager().registerEvents(new PlayerMovementListener(playerData), this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equals("disco")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Ta komenda jest dostepna tylko dla graczy.");
                return true;
            }

            if (!sender.hasPermission("memedisco.disco")) {
                sender.sendMessage(ChatColor.RED + "Nie masz permisji do uzycia tej komendy.");
                return true;
            }

            ((Player) sender).openInventory(configInventory.getConfigInventory());
        }
        return true;
    }

    public ArmorUpdateMethod getUpdateMethod() {
        return updateMethod;
    }

    public ColorUpdateTask getColorUpdateTask() {
        return colorUpdateTask;
    }
}
