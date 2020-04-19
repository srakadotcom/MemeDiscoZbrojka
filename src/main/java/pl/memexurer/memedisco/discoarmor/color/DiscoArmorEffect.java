package pl.memexurer.memedisco.discoarmor.color;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public abstract class DiscoArmorEffect {
    protected int r;
    protected int g;
    protected int b;
    private Color color;

    private CraftItemStack helmet;
    private CraftItemStack chestplate;
    private CraftItemStack leggings;
    private CraftItemStack boots;

    protected DiscoArmorEffect() {
        this.r = 0;
        this.g = 0;
        this.b = 0;
    }

    protected abstract void doTick();

    public void update() {
        doTick();
        try {
            this.color = Color.fromRGB(r, g, b);
        } catch (Exception ex) {
            System.out.println("cos sie wyjebalo\n czemu sie wyjebalo: " + ex.getMessage() + "\n objekt: " + this.toString() + "\n klasa: " + this.getClass().getSimpleName());
        }

        this.helmet = getItem(Material.LEATHER_HELMET);
        this.chestplate = getItem(Material.LEATHER_CHESTPLATE);
        this.leggings = getItem(Material.LEATHER_LEGGINGS);
        this.boots = getItem(Material.LEATHER_BOOTS);
    }

    @Override
    public String toString() {
        return Integer.toHexString(this.hashCode()) + ":[" + r + ", " + g + ", " + b + "]";
    }

    private CraftItemStack getItem(Material armor) {
        ItemStack item = new ItemStack(armor, 1);
        LeatherArmorMeta armorMeta = (LeatherArmorMeta) item.getItemMeta();
        armorMeta.setColor(color);
        item.setItemMeta(armorMeta);

        return CraftItemStack.asCraftCopy(item);
    }

    public CraftItemStack getHelmet() {
        return helmet;
    }

    public CraftItemStack getChestplate() {
        return chestplate;
    }

    public CraftItemStack getLeggings() {
        return leggings;
    }

    public CraftItemStack getBoots() {
        return boots;
    }
}
