package fr.jamailun.Mmorpg.Guis;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;

import fr.jamailun.Mmorpg.Classes.*;
import fr.jamailun.Ressources.*;

public class JobGui {

	public static void OpenMineur(MmoPlayer p) {
		Inventory inv = Bukkit.createInventory(null, 9*3, "§3§lFour de mineur");
		ItemStack lock = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 7);ItemMeta lockM = lock.getItemMeta();lockM.setDisplayName(" ");lock.setItemMeta(lockM);
		for(int i=0; i<=26; i++) inv.setItem(i, lock);
		
		int bSlot = -1;
		for(int i= 1; i <= p.getJob("mineur").getLevel(); i++) {
			bSlot++;
			int slot = 0;
			for(CraftMineur craft : fr.jamailun.Ressources.CraftMineur.ItemsCraftableWithLevel(i)) {
				int s = bSlot + slot;
				ItemStack item = fr.jamailun.Ressources.CraftMineur.IconeCraft(craft);
				inv.setItem(s, item);
				slot += 9;
			}
		}
		
		p.openInventory(inv);
	}
	
	public static void OpenForgeron(MmoPlayer p) {
		Inventory inv = Bukkit.createInventory(null, 9*5, "§3§lEnclume de forgeron");
		ItemStack lock = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 7);ItemMeta lockM = lock.getItemMeta();lockM.setDisplayName(" ");lock.setItemMeta(lockM);
		for(int i=0; i<=44; i++) inv.setItem(i, lock);
		
		int bSlot = -1;
		for(int i= 1; i <= p.getJob("forgeron").getLevel(); i++) {
			bSlot++;
			int slot = 0;
			for(CraftForgeron craft : fr.jamailun.Ressources.CraftForgeron.ItemsCraftableWithLevel(i)) {
				int s = bSlot + slot;
				ItemStack item = fr.jamailun.Ressources.CraftForgeron.IconeCraft(craft);
				inv.setItem(s, item);
				slot += 9;
			}
		}
		
		p.openInventory(inv);
	}
	
	public static void OpenPaysan(MmoPlayer p) {
		Inventory inv = Bukkit.createInventory(null, 9*2, "§3§lPétrin de paysan");
		ItemStack lock = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 7);ItemMeta lockM = lock.getItemMeta();lockM.setDisplayName(" ");lock.setItemMeta(lockM);
		for(int i=0; i<=17; i++) inv.setItem(i, lock);
		
		int bSlot = -1;
		for(int i= 1; i <= p.getJob("paysan").getLevel(); i++) {
			bSlot++;
			int slot = 0;
			for(CraftPaysan craft : fr.jamailun.Ressources.CraftPaysan.ItemsCraftableWithLevel(i)) {
				int s = bSlot + slot;
				ItemStack item = fr.jamailun.Ressources.CraftPaysan.IconeCraft(craft);
				inv.setItem(s, item);
				slot += 9;
			}
		}
		
		p.openInventory(inv);
	}
	
}
