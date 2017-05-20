package fr.jamailun.Mmorpg.JobsEffects;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.inventory.ItemStack;

import fr.jamailun.Mmorpg.Classes.*;
import fr.jamailun.Ressources.*;

public class Crafting {

	public static void CraftingMineur(MmoPlayer p, CraftMineur craft) {
		//tous les items nécessaires au craft
		List<ItemStack> list = new ArrayList<ItemStack>();
		if(craft.item1 != null) {ItemStack it = fr.jamailun.Ressources.CustomItem.getItem(craft.item1.name); it.setAmount(craft.amount1);list.add(it);}
		if(craft.item2 != null) {ItemStack it = fr.jamailun.Ressources.CustomItem.getItem(craft.item2.name); it.setAmount(craft.amount2);list.add(it);}
		if(craft.item3 != null) {ItemStack it = fr.jamailun.Ressources.CustomItem.getItem(craft.item3.name); it.setAmount(craft.amount3);list.add(it);}
		if(craft.item4 != null) {ItemStack it = fr.jamailun.Ressources.CustomItem.getItem(craft.item4.name); it.setAmount(craft.amount4);list.add(it);}
		if(craft.item5 != null) {ItemStack it = fr.jamailun.Ressources.CustomItem.getItem(craft.item5.name); it.setAmount(craft.amount5);list.add(it);}
		if(craft.item6 != null) {ItemStack it = fr.jamailun.Ressources.CustomItem.getItem(craft.item6.name); it.setAmount(craft.amount6);list.add(it);}
		
		//regarde si il y a assez de matériaux.
		for(ItemStack item : list) {
			if(!(p.getInventory().containsAtLeast(item, item.getAmount()))) {
				p.sendMessage("§4Vous n'avez pas assez de "+item.getItemMeta().getDisplayName()+"§4.");
				return;
			}
		}
		//craft valide: on peut enlever les items
		for(ItemStack item : list) p.getInventory().removeItem(item);
		//give item issu du craft
		ItemStack result = fr.jamailun.Ressources.CustomItem.getItem(craft.craftItem.name);
		result.setAmount(craft.craftAmount);
		p.getInventory().addItem(result);
		
		p.sendMessage("§a§l[+"+craft.xp+"xp]§7 Pour le craft de "+craft.craftAmount+" "+craft.craftItem.name+"§7.");
		p.getJob("mineur").addXp(craft.xp);
	}
	
	public static void CraftingForgeron(MmoPlayer p, CraftForgeron craft) {
		//tous les items nécessaires au craft
		List<ItemStack> list = new ArrayList<ItemStack>();
		if(craft.item1 != null) {ItemStack it = fr.jamailun.Ressources.CustomItem.getItem(craft.item1.name); it.setAmount(craft.amount1);list.add(it);}
		if(craft.item2 != null) {ItemStack it = fr.jamailun.Ressources.CustomItem.getItem(craft.item2.name); it.setAmount(craft.amount2);list.add(it);}
		if(craft.item3 != null) {ItemStack it = fr.jamailun.Ressources.CustomItem.getItem(craft.item3.name); it.setAmount(craft.amount3);list.add(it);}
		if(craft.item4 != null) {ItemStack it = fr.jamailun.Ressources.CustomItem.getItem(craft.item4.name); it.setAmount(craft.amount4);list.add(it);}
		if(craft.item5 != null) {ItemStack it = fr.jamailun.Ressources.CustomItem.getItem(craft.item5.name); it.setAmount(craft.amount5);list.add(it);}
		if(craft.item6 != null) {ItemStack it = fr.jamailun.Ressources.CustomItem.getItem(craft.item6.name); it.setAmount(craft.amount6);list.add(it);}
		
		//regarde si il y a assez de matériaux.
		for(ItemStack item : list) {
			if(!(p.getInventory().containsAtLeast(item, item.getAmount()))) {
				p.sendMessage("§4Vous n'avez pas assez de "+item.getItemMeta().getDisplayName()+"§4.");
				return;
			}
		}
		//craft valide: on peut enlever les items
		for(ItemStack item : list) p.getInventory().removeItem(item);
		//give item issu du craft
		ItemStack result = fr.jamailun.Ressources.CustomEquipment.getItem(craft.craftItem.name);
		result.setAmount(1);
		p.getInventory().addItem(result);
		
		p.sendMessage("§a§l[+"+craft.xp+"xp]§7 Pour le craft de "+craft.craftAmount+" "+craft.craftItem.name+"§7.");
		p.getJob("forgeron").addXp(craft.xp);
	}

	public static void CraftingPaysan(MmoPlayer p, CraftPaysan craft) {
		//tous les items nécessaires au craft
		List<ItemStack> list = new ArrayList<ItemStack>();
		if(craft.item1 != null) {ItemStack it = fr.jamailun.Ressources.CustomItem.getItem(craft.item1.name); it.setAmount(craft.amount1);list.add(it);}
		if(craft.item2 != null) {ItemStack it = fr.jamailun.Ressources.CustomItem.getItem(craft.item2.name); it.setAmount(craft.amount2);list.add(it);}
		if(craft.item3 != null) {ItemStack it = fr.jamailun.Ressources.CustomItem.getItem(craft.item3.name); it.setAmount(craft.amount3);list.add(it);}
		if(craft.item4 != null) {ItemStack it = fr.jamailun.Ressources.CustomItem.getItem(craft.item4.name); it.setAmount(craft.amount4);list.add(it);}
		if(craft.item5 != null) {ItemStack it = fr.jamailun.Ressources.CustomItem.getItem(craft.item5.name); it.setAmount(craft.amount5);list.add(it);}
		if(craft.item6 != null) {ItemStack it = fr.jamailun.Ressources.CustomItem.getItem(craft.item6.name); it.setAmount(craft.amount6);list.add(it);}
		
		//regarde si il y a assez de matériaux.
		for(ItemStack item : list) {
			if(!(p.getInventory().containsAtLeast(item, item.getAmount()))) {
				p.sendMessage("§4Vous n'avez pas assez de "+item.getItemMeta().getDisplayName()+"§4.");
				return;
			}
		}
		//craft valide: on peut enlever les items
		for(ItemStack item : list) p.getInventory().removeItem(item);
		//give item issu du craft
		ItemStack result = fr.jamailun.Ressources.CustomItem.getItem(craft.craftItem.name);
		result.setAmount(1);
		p.getInventory().addItem(result);
		
		p.sendMessage("§a§l[+"+craft.xp+"xp]§7 Pour le craft de "+craft.craftAmount+" "+craft.craftItem.name+"§7.");
		p.getJob("paysan").addXp(craft.xp);
	}
	
}
