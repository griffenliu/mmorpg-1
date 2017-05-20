package fr.jamailun.Ressources;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;
public enum CraftMineur {
		//LVL-NEED, XP-DONNé, nb-donné, {Ingrédient, nombre}[1,2,3,4,5,6]
	PIERRE_LISSE(1, 0, CustomItem.PIERRE_LISSE, 1, CustomItem.PIERRE_TAILLEE, 2, null, 0, null, 0, null, 0, null, 0, null, 0),
	PIERRE_LISSE_32(1, 0, CustomItem.PIERRE_LISSE, 32, CustomItem.PIERRE_TAILLEE, 64, null, 0, null, 0, null, 0, null, 0, null, 0),
	PIERRE_POLIE(1, 2, CustomItem.PIERRE_POLIE, 1, CustomItem.PIERRE_LISSE, 16, null, 0, null, 0, null, 0, null, 0, null, 0),
	
	CHARBON_RAFINE(2, 1, CustomItem.CHARBON_RAFINE, 1, CustomItem.CHARBON, 9, null, 0, null, 0, null, 0, null, 0, null, 0),
	CHARBON_CONDENSE(2, 8, CustomItem.CHARBON_CONDENSE, 1, CustomItem.CHARBON, 64, null, 0, null, 0, null, 0, null, 0, null, 0),
	PIERRE_LUSTREE(2, 10, CustomItem.PIERRE_LUSTREE, 1, CustomItem.PIERRE_POLIE, 16, CustomItem.CHARBON_RAFINE, 1, null, 0, null, 0, null, 0, null, 0),
	
	FER(3, 2, CustomItem.FER, 8, CustomItem.FER_MINERAI, 8, CustomItem.CHARBON, 1, null, 0, null, 0, null, 0, null, 0),
	FER_CONDENSE(3, 2, CustomItem.FER_CONDENSE, 1, CustomItem.FER, 16, CustomItem.CHARBON, 16, null, 0, null, 0, null, 0, null, 0),
	ACIER(3, 2, CustomItem.ACIER, 9, CustomItem.FER_CONDENSE, 2, CustomItem.CHARBON_CONDENSE, 1, null, 0, null, 0, null, 0, null, 0),
	
	OR_POUDRE(4, 2, CustomItem.OR_POUDRE, 3, CustomItem.OR_MINERAI, 1, CustomItem.CHARBON, 1, null, 0, null, 0, null, 0, null, 0),
	OR(4, 4, CustomItem.OR, 1, CustomItem.OR_POUDRE, 15, CustomItem.CHARBON_RAFINE, 1, null, 0, null, 0, null, 0, null, 0),
	OR_CONDENSE(4, 8, CustomItem.OR_CONDENSE, 1, CustomItem.OR, 9, CustomItem.CHARBON_CONDENSE, 2, null, 0, null, 0, null, 0, null, 0),
	
	MANA(5, 3, CustomItem.MANA, 1, CustomItem.MANA_MINERAI, 1, CustomItem.CHARBON_RAFINE, 1, null, 0, null, 0, null, 0, null, 0),
	MANA_PURE(5, 10, CustomItem.MANA_PURE, 1, CustomItem.MANA, 9, CustomItem.CHARBON_RAFINE, 6, CustomItem.OR, 8, CustomItem.PIERRE_LUSTREE, 1, null, 0, null, 0),
	OR_PURIFIE(5, 15, CustomItem.OR_PURIFIE, 5, CustomItem.MANA_PURE, 1, CustomItem.OR_CONDENSE, 1, CustomItem.CHARBON_RAFINE, 5, null, 0, null, 0, null, 0),
	
	DIAMANT(6, 20, CustomItem.DIAMANT, 1, CustomItem.DIAMANT_MINERAI, 2, CustomItem.CHARBON, 16, CustomItem.PIERRE_POLIE, 1, null, 0, null, 0, null, 0),
	DIAMANT_POLI(6, 50, CustomItem.DIAMANT_POLI, 1, CustomItem.DIAMANT, 2, CustomItem.CHARBON_RAFINE, 8, CustomItem.PIERRE_LUSTREE, 2, CustomItem.MANA_PURE, 10, null, 0, null, 0),
	CRISTAL_DIAMANTE(6, 500, CustomItem.CRISTAL_DIAMANTE, 1, CustomItem.DIAMANT_POLI, 9, CustomItem.CHARBON_CONDENSE, 5, CustomItem.ACIER, 32, CustomItem.MANA_PURE, 16, CustomItem.OR_PURIFIE, 8, null, 0),
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
	private CraftMineur(int neededLevel, int xp, CustomItem craftItem, int craftAmount, CustomItem item1, int amount1, CustomItem item2, int amount2, CustomItem item3, int amount3, CustomItem item4, int amount4, CustomItem item5, int amount5, CustomItem item6, int amount6) {
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
	
	public static List<CraftMineur> ItemsCraftableWithLevel(int level) {
		List<CraftMineur> list = new ArrayList<CraftMineur>();
		for(CraftMineur craft : values()) {
			if(craft.neededLevel == level) {
				list.add(craft);
			}
		}
		return list;
	}
	
	//icone de l'item qui apparait dans la GUI
	public static ItemStack IconeCraft(CraftMineur craft) {
		ItemStack item = new ItemStack(craft.craftItem.type, craft.craftAmount, (byte)craft.craftItem.sousId);
		
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
		//Bukkit.broadcastMessage("§6"+craft.craftItem.sousId+"§rid:"+item.getTypeId()+", §7sous-id:"+item.getData());
		return item;
	}
	
	
}
