package place.foo.armorstandplus.listeners;

import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import place.foo.armorstandplus.ArmorStandPlus;

public class EntityListener implements Listener {

    ArmorStandPlus plugin;

    public EntityListener(ArmorStandPlus instance) {
        plugin = instance;
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {
        Entity entity = e.getEntity();
        if (entity instanceof ArmorStand) {

            ArmorStand stand = (ArmorStand)entity;

            if ((!stand.hasBasePlate() || stand.hasArms()) && !e.getDrops().isEmpty()) {

                if (!stand.hasBasePlate()) {
                    e.getDrops().clear();
                    e.getDrops().add(new ItemStack(Material.STICK, 6));
                }

                if (stand.hasArms()) {
                    e.getDrops().add(new ItemStack(Material.STICK, 2));
                }
            }
        }
    }
}
