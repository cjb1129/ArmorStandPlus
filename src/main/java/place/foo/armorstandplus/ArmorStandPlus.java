package place.foo.armorstandplus;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import place.foo.armorstandplus.listeners.EntityListener;
import place.foo.armorstandplus.listeners.PlayerListener;

public final class ArmorStandPlus extends JavaPlugin {

    private static Plugin instance = null;

    @Override
    public void onEnable() {

        instance = this;

        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(new PlayerListener(this), this);
        pm.registerEvents(new EntityListener(this), this);

    }

    @Override
    public void onDisable() {}
}
