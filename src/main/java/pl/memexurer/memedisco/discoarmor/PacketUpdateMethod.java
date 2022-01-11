package pl.memexurer.memedisco.discoarmor;

import net.minecraft.server.v1_8_R3.PacketPlayOutEntityEquipment;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.memexurer.memedisco.discoarmor.data.DiscoPlayer;
import pl.memexurer.memedisco.util.ItemSlot;

public class PacketUpdateMethod implements ArmorUpdateMethod {
    @Override
    public void updateArmor(DiscoPlayer discoPlayer) {
        if (discoPlayer.isCrouching()) {
            sendSelfArmorChange(discoPlayer.getPlayer(), discoPlayer);
        }

        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.getUniqueId().equals(discoPlayer.getUniqueId())) continue;
            sendArmorChange(p, discoPlayer);
        }
    }

    @Override
    public void resetArmor(DiscoPlayer player) {
        int entityId = player.getPlayer().getEntityId();
        ItemStack[] armorContents = player.getPlayer().getInventory().getArmorContents();

        for (Player p : Bukkit.getOnlinePlayers()) {
            if(p.getUniqueId().equals(player.getUniqueId())) continue;
            sendArmorChange(p, entityId, armorContents);
        }
    }

    private void sendSelfArmorChange(Player receiver, DiscoPlayer player) {
        for (ItemSlot slot : ItemSlot.values()) {
            PacketPlayOutEntityEquipment nmsPacket = new PacketPlayOutEntityEquipment(player.getPlayer().getEntityId(), slot.getSlot() - 1, CraftItemStack.asNMSCopy(player.getItem(slot)));
            ((CraftPlayer) receiver).getHandle().playerConnection.sendPacket(nmsPacket);
        }
    }


    private void sendArmorChange(Player receiver, DiscoPlayer player) {
        Player play = player.getPlayer();
        if(play == null) return;

        for (ItemSlot slot : ItemSlot.values()) {
            PacketPlayOutEntityEquipment nmsPacket = new PacketPlayOutEntityEquipment(play.getEntityId(), slot.getSlot(), CraftItemStack.asNMSCopy(player.getItem(slot)));
            ((CraftPlayer) receiver).getHandle().playerConnection.sendPacket(nmsPacket);
        }
    }

    private void sendArmorChange(Player receiver, int entityId, ItemStack[] armorContents) {
        for (ItemSlot slot : ItemSlot.values()) {
            PacketPlayOutEntityEquipment nmsPacket = new PacketPlayOutEntityEquipment(entityId, slot.getSlot(), CraftItemStack.asNMSCopy(armorContents[slot.getSlot() - 1]));
            ((CraftPlayer) receiver).getHandle().playerConnection.sendPacket(nmsPacket);
        }
    }
}
