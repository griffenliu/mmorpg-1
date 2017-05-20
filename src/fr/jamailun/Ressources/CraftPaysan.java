package fr.jamailun.Ressources;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;
public enum CraftPaysan {
		//LVL-NEED, XP-DONNé, nb-donné, {Ingrédient, nombre}[1,2,3,4,5,6]
	FARINE(1, 1, CustomItem.FARINE, 1, CustomItem.BLE, 3, null, 0, null, 0, null, 0, null, 0, null, 0),
	PAIN_BASIQUE(1, 2, CustomItem.PAIN_BASIQUE, 1, CustomItem.FARINE, 10, CustomItem.EAU, 1, null, 0, null, 0, null, 0, null, 0),
	
	SUCRE(2, 3, CustomItem.SUCRE, 1, CustomItem.CANNE, 5, null, 0, null, 0, null, 0, null, 0, null, 0),
	BONBON(2, 4, CustomItem.BONBON, 1, CustomItem.SUCRE, 15, CustomItem.CANNE, 3, CustomItem.CHARBON_RAFINE, 1, null, 0, null, 0, null, 0),
	
	FARINE_FECULEE(3, 4, CustomItem.FARINE_FECULEE, 2, CustomItem.BLE, 32, CustomItem.PATATE, 16, CustomItem.PIERRE_LISSE, 4, null, 0, null, 0, null, 0),
	PAIN_GOURMAND(3, 5, CustomItem.PAIN_GOURMAND, 1, CustomItem.FARINE_FECULEE, 4, CustomItem.SUCRE, 10, CustomItem.EAU, 2, null, 0, null, 0, null, 0),
	
	MELON_COMPRESSE(4, 5, CustomItem.MELON_COMPRESSE, 1, CustomItem.MELON, 18, null, 0, null, 0, null, 0, null, 0, null, 0),
	MELON_EPURE(4, 10, CustomItem.MELON_EPURE, 1, CustomItem.MELON_DORE, 1, CustomItem.MELON, 16, CustomItem.MANA, 4, null, 0, null, 0, null, 0),
	
	CAROTTE(5, 2, CustomItem.CAROTTE, 5, CustomItem.CAROTTE_SALE, 5, CustomItem.EAU, 1, null, 0, null, 0, null, 0, null, 0),
	PAIN_SOYEUX(5, 9, CustomItem.PAIN_SOYEUX, 1, CustomItem.CAROTTE, 20, CustomItem.FARINE_FECULEE, 3, CustomItem.MELON_COMPRESSE, 1, CustomItem.EAU, 5, null, 0, null, 0),
	;
	
	public int neededLevel;
	public CustomItem craftItem;
	public int craftAmount;
	public int xp;
	
	public CustomItem item1;
	public int amount1;
	public CustomItem item2;
	public int amount2;
	public CustomItem item3;
	public int amount3;
	public CustomItem item4;
	public int amount4;
	public CustomItem item5;
	public int amount5;
	public CustomItem item6;
	public int amount6;
	
	//constructeur
	private CraftPaysan(int neededLevel, int xp, CustomItem craftItem, int craftAmount, CustomItem item1, int amount1, CustomItem item2, int amount2, CustomItem item3, int amount3, CustomItem item4, int amount4, CustomItem item5, int amount5, CustomItem item6, int amount6) {
		this.neededLevel = neededLevel;
		this.craftItem = craftItem;
		this.xp = xp;
		this.craftAmount = craftAmount;
		this.item1 = item1;
		this.amount1 = amount1;
		this.item2 = item2;
		this.amount2 = amount2;
		this.item3 = item3;
		this.amount3 = amount3;
		this.item4 = item4;
		this.amount4 = amount4;
		this.item5 = item5;
		this.amount5 = amount5;
		this.item6 = item6;
		this.amount6 = amount6;
	}
	
	public static List<CraftPaysan> ItemsCraftableWithLevel(int level) {
		List<CraftPaysan> list = new ArrayList<CraftPaysan>();
		for(CraftPaysan craft : values()) {
			if(craft.neededLevel == level) {
				list.add(craft);
			}
		}
		return list;
	}
	
	//icone de l'item qui apparait dans la GUI
	public static ItemStack IconeCraft(CraftPaysan craft) {
		ItemStack item = new ItemStack(craft.craftItem.type, 1, (byte) craft.craftItem.sousId);
		
		item.setAmount(craft.craftAmount);
		ItemMeta itemM = item.getItemMeta();
		if(craft.craftAmount == 1) {
			itemM.setDisplayName(craft.craftItem.name);
		} else {
			itemM.setDisplayName(craft.craftItem.name + " §r[x"+craft.craftAmount+"]");
		}
		if(craft.craftItem.brillant) itemM.addEnchant(Enchantment.ARROW_INFINITE, 0, false);
		List<String> lore = new ArrayList<String>();
		if(craft.item1 != null) lore.add("§8> "+craft.item1.name+" [x"+craft.amount1+"]");
		if(craft.item2 != null) lore.add("§8> "+craft.item2.name+" [x"+craft.amount2+"]");
		if(craft.item3 != null) lore.add("§8> "+craft.item3.name+" [x"+craft.amount3+"]");
		if(craft.item4 != null) lore.add("§8> "+craft.item4.name+" [x"+craft.amount4+"]");
		if(craft.item5 != null) lore.add("§8> "+craft.item5.name+" [x"+craft.amount5+"]");
		if(craft.item6 != null) lore.add("§8> "+craft.item6.name+" [x"+craft.amount6+"]");
		itemM.setLore(lore);
		item.setItemMeta(itemM);
		return item;
	}
	
	
}
