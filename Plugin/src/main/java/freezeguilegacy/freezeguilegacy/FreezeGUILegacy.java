package freezeguilegacy.freezeguilegacy;

import freezeguilegacy.freezeguilegacy.commands.UnFreezeCommand;
import freezeguilegacy.freezeguilegacy.events.EventsHandler;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import freezeguilegacy.freezeguilegacy.commands.FreezeCommand;

public class FreezeGUILegacy extends JavaPlugin {
    public FileConfiguration config = getConfig();


    @Override
    public void onEnable() {
        config.addDefault("logFreezes", true);
        config.addDefault("logReFreezes", true);
        config.addDefault("GUIItem", new ItemStack(Material.PAPER, 1));
        config.addDefault("GUIItemName", "You have been frozen by staff, please look at your discord!");
        config.options().copyDefaults(true);
        saveConfig();

        getCommand("freeze").setExecutor(new FreezeCommand());
        getCommand("unfreeze").setExecutor(new UnFreezeCommand());

        getServer().getPluginManager().registerEvents(new EventsHandler(this), this);
    }

    @Override
    public void onDisable() {
        System.out.println("FreezeGUI is shutting down");
    }
}
