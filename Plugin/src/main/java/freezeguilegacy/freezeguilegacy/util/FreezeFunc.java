package freezeguilegacy.freezeguilegacy.util;
import freezeguilegacy.freezeguilegacy.FreezeGUILegacy;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class FreezeFunc {
    FileConfiguration config = FreezeGUILegacy.getPlugin(FreezeGUILegacy.class).getConfig();
    public void Freeze(HumanEntity p, Boolean logToSys, Boolean rejoin) {
        Inventory GUI = Bukkit.createInventory(p, 9, "You have been frozen by staff");

        ItemStack item1 = config.getItemStack("GUIItem");
        ItemMeta item1_meta = item1.getItemMeta();
        item1_meta.setDisplayName(config.getString("GUIItemName"));
        item1.setItemMeta(item1_meta);
        GUI.setItem(4, item1);

        p.openInventory(GUI);

        if(!logToSys) return;
        if(rejoin) {
            System.out.println((p.getName()) + "'s frozen screen has been re-applied (They left)");
        } else {
            System.out.println((p.getName()) + "'s frozen screen has been applied (Command)");
        }

    }
}
