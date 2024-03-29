package shsmp.paper.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.jetbrains.annotations.NotNull;
import shsmp.paper.recipes.BaseNecronomicon;
import shsmp.paper.recipes.Necronomicon;

public class Refresh {
    public static boolean execute(@NotNull CommandSender sender, @NotNull Command command, String label, String[] args) {

        // If sender is a console or non-player
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only a Player can use this command");
            return true;
        }

        Player player = (Player) sender;
        String necroOnly = "You can only use this command through the Necronomicon!";
        ItemStack necronomicon = new Necronomicon().getItem();
        ItemStack itemInMainHand = player.getInventory().getItemInMainHand();

        // Checks if Item in hand has ItemMeta. If true, returns display name, else it
        // returns null
        String itemInHandName = itemInMainHand.hasItemMeta() ? itemInMainHand.getItemMeta().getDisplayName() : null;

        // Checks if player is already holding Necronomicon
        boolean itemHandCondition = itemInHandName == null
                || !itemInHandName.equals(necronomicon.getItemMeta().getDisplayName());

        if (itemHandCondition) {
            sender.sendMessage(necroOnly);
            return true;
        }

        // Set the updated BookMeta back to the book item
        BookMeta bookMeta = new BaseNecronomicon().getBookMeta();
        itemInMainHand.setItemMeta(bookMeta);
        return true;
    }
}
