package place.foo.armorstandplus.listeners;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import place.foo.armorstandplus.ArmorStandPlus;

public class PlayerListener implements Listener {

    ArmorStandPlus plugin;

    public PlayerListener(ArmorStandPlus instance) {
        plugin = instance;
    }

    @EventHandler
    public void onPlayerInteractAtEntity(PlayerInteractAtEntityEvent e) {
        if (e.getRightClicked().getType().equals(EntityType.ARMOR_STAND)) {
            if (e.getPlayer().isSneaking()) {

                ItemStack hand = e.getPlayer().getInventory().getItemInMainHand();
                ItemMeta meta = hand.getItemMeta();

                ArmorStand stand = (ArmorStand) e.getRightClicked();

                if (hand.getType().equals(Material.STICK) && hand.getAmount() >= 2 && !stand.hasArms()) {

                    hand.setAmount(hand.getAmount() - 2); // Takes 2 sticks away from hand
                    stand.setArms(true); // Gives ArmorStand arms

                    e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);

                    e.setCancelled(true);
                }

                if (hand.getType().toString().toLowerCase().contains("_axe") && stand.hasBasePlate()) {

                    if (!meta.isUnbreakable()) {
                        ((Damageable) meta).setDamage(((Damageable) meta).getDamage() + 1);
                        hand.setItemMeta(meta);
                    }

                    stand.setBasePlate(false);
                    stand.getWorld().dropItemNaturally(stand.getLocation(), new ItemStack(Material.SMOOTH_STONE_SLAB, 1));

                    e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_ZOMBIE_ATTACK_WOODEN_DOOR, 1.0F, 1.0F);

                    e.setCancelled(true);
                }

                if (hand.getType().toString().toLowerCase().contains("_shovel") && !stand.isSmall()) {

                    if (!meta.isUnbreakable()) {
                        ((Damageable) meta).setDamage(((Damageable) meta).getDamage() + 1);
                        hand.setItemMeta(meta);
                    }

                    stand.setSmall(true);

                    e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_ZOMBIE_ATTACK_WOODEN_DOOR, 1.0F, 1.0F);

                    e.setCancelled(true);
                }
            }
        }
    }

}
