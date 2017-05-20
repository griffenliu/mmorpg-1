package fr.jamailun.Mmorpg.Event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import fr.jamailun.Mmorpg.Classes.*;
import fr.jamailun.Ressources.*;

public class OnInventoryClic implements Listener {
	public OnInventoryClic(fr.jamailun.Mmorpg.Main main) {}
	
	@EventHandler
	public void OnInventoryClick(InventoryClickEvent e) {
		if(!(e.getWhoClicked() instanceof Player)) return;
		MmoPlayer p = fr.jamailun.Mmorpg.Main.mpm.getPlayer((Player) e.getWhoClicked());
		if(p.getWorld() != fr.jamailun.Mmorpg.Main.monde) return;
		if(e.getCurrentItem() == null) return;
		if(e.getCurrentItem().getItemMeta() == null) return;
		if(e.getCurrentItem().getItemMeta().getDisplayName() == null) return;
		ItemStack item = e.getCurrentItem();
		String disp = item.getItemMeta().getDisplayName();
		
		if(e.getClickedInventory().getTitle().equalsIgnoreCase("§2§lRespawner ici ?")) {
			e.setCancelled(true);
			p.updateInventory();
			if(item.getItemMeta().getDisplayName().equalsIgnoreCase("§aOUI")) {
				p.setRespawn(p.getLocation());
				p.sendMessage("§2[Aubergiste] §aVous réaparaitrez ici lorsque vous mourrez. Bonne journée !");
				p.closeInventory();
			} if(item.getItemMeta().getDisplayName().equalsIgnoreCase("§cNON")) {
				p.sendMessage("§2[Aubergiste] §aVous conservez votre ancien point de réaparition. Bonne journée !");
				p.closeInventory();
			} 
			
		}
		
		else if(e.getClickedInventory().getTitle().equalsIgnoreCase("§6§lInformations personnelles")) {
			e.setCancelled(true);
		}
		
		
		if(e.getClickedInventory().getTitle().equalsIgnoreCase("§3§lFour de mineur")) {
			e.setCancelled(true);
			//peut-etre foutre ça dans une classe à part (methode statique)
			for(int i=1; i<=p.getJob("mineur").getLevel(); i++) {
				for(CraftMineur craft : fr.jamailun.Ressources.CraftMineur.ItemsCraftableWithLevel(i)) {
			
					if(item.getAmount() > 1) {
						if(craft.craftAmount == item.getAmount()) {
							if(disp.contains(craft.craftItem.name)) {
								fr.jamailun.Mmorpg.JobsEffects.Crafting.CraftingMineur(p, craft);
								return;
							}
						}
					} else
					if(craft.craftItem.name.equalsIgnoreCase(disp)) {
						if(craft.craftAmount == 1) {
							fr.jamailun.Mmorpg.JobsEffects.Crafting.CraftingMineur(p, craft);
							return;
						}
					}
				}
			}
		}
		if(e.getClickedInventory().getTitle().equalsIgnoreCase("§3§lEnclume de forgeron")) {
			e.setCancelled(true);
			//peut-etre foutre ça dans une classe à part (methode statique)
			for(int i=1; i<=p.getJob("forgeron").getLevel(); i++) {
				for(CraftForgeron craft : fr.jamailun.Ressources.CraftForgeron.ItemsCraftableWithLevel(i)) {
			
					if(item.getAmount() > 1) {
						if(craft.craftAmount == item.getAmount()) {
							if(disp.contains(craft.craftItem.name)) {
								fr.jamailun.Mmorpg.JobsEffects.Crafting.CraftingForgeron(p, craft);
								return;
							}
						}
					} else
					if(craft.craftItem.name.equalsIgnoreCase(disp)) {
						if(craft.craftAmount == 1) {
							fr.jamailun.Mmorpg.JobsEffects.Crafting.CraftingForgeron(p, craft);
							return;
						}
					}
				}
			}
		}
		if(e.getClickedInventory().getTitle().equalsIgnoreCase("§3§lPétrin de paysan")) {
			e.setCancelled(true);
			//peut-etre foutre ça dans une classe à part (methode statique)
			for(int i=1; i<=p.getJob("paysan").getLevel(); i++) {
				for(CraftPaysan craft : fr.jamailun.Ressources.CraftPaysan.ItemsCraftableWithLevel(i)) {
			
					if(item.getAmount() > 1) {
						if(craft.craftAmount == item.getAmount()) {
							if(disp.contains(craft.craftItem.name)) {
								fr.jamailun.Mmorpg.JobsEffects.Crafting.CraftingPaysan(p, craft);
								return;
							}
						}
					} else
					if(craft.craftItem.name.equalsIgnoreCase(disp)) {
						if(craft.craftAmount == 1) {
							fr.jamailun.Mmorpg.JobsEffects.Crafting.CraftingPaysan(p, craft);
							return;
						}
					}
				}
			}
		}
		
	}
}
