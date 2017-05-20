package fr.jamailun.Mmorpg.Guis;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.jamailun.Mmorpg.Classes.*;

public class GuiNPC {

	public static void Aubergiste(MmoPlayer p) {
		Inventory inv = Bukkit.createInventory(null, 9*1, "§2§lRespawner ici ?");
		
		ItemStack mur = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte)8);
		ItemMeta murM = mur.getItemMeta();
		murM.setDisplayName(" ");
		mur.setItemMeta(murM);
		for(int i=0; i<=8; i++) inv.setItem(i, mur);
		
		ItemStack oui = new ItemStack(Material.WOOL, 1, (byte)5);
		ItemMeta ouiM = oui.getItemMeta();
		ouiM.setDisplayName("§aOUI");
		oui.setItemMeta(ouiM);
		
		ItemStack non = new ItemStack(Material.WOOL, 1, (byte) 14);
		ItemMeta nonM = non.getItemMeta();
		nonM.setDisplayName("§cNON");
		non.setItemMeta(nonM);
		
		inv.setItem(3, oui);
		inv.setItem(5, non);
		
		p.openInventory(inv);
	}
	
}
