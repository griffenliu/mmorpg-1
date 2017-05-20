package fr.jamailun.Ressources;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;
public enum CraftForgeron {
		//LVL-NEED, XP-DONNé, nb-donné, {Ingrédient, nombre}[1,2,3,4,5,6]
	CASQUE_RECRUE(1, 10, CustomEquipment.CASQUE_RECRUE, 1, CustomItem.FER, 10, CustomItem.CHARBON, 5, null, 0, null, 0, null, 0, null, 0),
	PLASTRON_RECRUE(1, 16, CustomEquipment.PLASTRON_RECRUE, 1, CustomItem.FER, 16, CustomItem.CHARBON, 8, null, 0, null, 0, null, 0, null, 0),
	JAMBES_RECRUE(1, 14, CustomEquipment.JAMBES_RECRUE, 1, CustomItem.FER, 14, CustomItem.CHARBON, 7, null, 0, null, 0, null, 0, null, 0),
	BOTTES_RECRUE(1, 8, CustomEquipment.BOTTES_RECRUE, 1, CustomItem.FER, 8, CustomItem.CHARBON, 4, null, 0, null, 0, null, 0, null, 0),
	EPEE_RECRUE(1, 4, CustomEquipment.EPEE_RECRUE, 1, CustomItem.FER, 4, CustomItem.CHARBON, 2, CustomItem.BATON, 2, null, 0, null, 0, null, 0),
	
	CASQUE_SOLDAT(2, 20, CustomEquipment.CASQUE_SOLDAT, 1, CustomItem.FER, 20, CustomItem.CHARBON, 10, CustomItem.PIERRE_POLIE, 1, null, 0, null, 0, null, 0),
	PLASTRON_SOLDAT(2, 32, CustomEquipment.PLASTRON_SOLDAT, 1, CustomItem.FER, 32, CustomItem.CHARBON, 16, CustomItem.PIERRE_POLIE, 1, null, 0, null, 0, null, 0),
	JAMBES_SOLDAT(2, 24, CustomEquipment.JAMBES_SOLDAT, 1, CustomItem.FER, 24, CustomItem.CHARBON, 12, CustomItem.PIERRE_POLIE, 1, null, 0, null, 0, null, 0),
	BOTTES_SOLDAT(2, 16, CustomEquipment.BOTTES_SOLDAT, 1, CustomItem.FER, 16, CustomItem.CHARBON, 8, CustomItem.PIERRE_POLIE, 1, null, 0, null, 0, null, 0),
	EPEE_SOLDAT(2, 18, CustomEquipment.EPEE_SOLDAT, 1, CustomItem.FER, 20, CustomItem.CHARBON, 10, CustomItem.PIERRE_POLIE, 2, CustomItem.BATON, 4, null, 0, null, 0),
	
	CASQUE_GARDE(3, 30, CustomEquipment.CASQUE_GARDE, 1, CustomItem.FER, 40, CustomItem.CHARBON, 25, CustomItem.PIERRE_POLIE, 5, CustomItem.MANA, 1, null, 0, null, 0),
	PLASTRON_GARDE(3, 48, CustomEquipment.PLASTRON_GARDE, 1, CustomItem.FER, 64, CustomItem.CHARBON, 40, CustomItem.PIERRE_POLIE, 5, CustomItem.MANA, 1, null, 0, null, 0),
	JAMBES_GARDE(3, 42, CustomEquipment.JAMBES_GARDE, 1, CustomItem.FER, 48, CustomItem.CHARBON, 35, CustomItem.PIERRE_POLIE, 5, CustomItem.MANA, 1, null, 0, null, 0),
	BOTTES_GARDE(3, 24, CustomEquipment.BOTTES_GARDE, 1, CustomItem.FER, 32, CustomItem.CHARBON, 20, CustomItem.PIERRE_POLIE, 5, CustomItem.MANA, 1, null, 0, null, 0),
	EPEE_GARDE(3, 32, CustomEquipment.EPEE_GARDE, 1, CustomItem.FER, 48, CustomItem.CHARBON, 30, CustomItem.PIERRE_POLIE, 10, CustomItem.MANA, 2, null, 0, null, 0),
	
	CASQUE_SENTINELLE(4, 40, CustomEquipment.CASQUE_SENTINELLE, 1, CustomItem.ACIER, 5, CustomItem.CHARBON_RAFINE, 10, CustomItem.PIERRE_LUSTREE, 1, CustomItem.MANA, 10, null, 0, null, 0),
	PLASTRON_SENTINELLE(4, 64, CustomEquipment.PLASTRON_SENTINELLE, 1, CustomItem.ACIER, 8, CustomItem.CHARBON_RAFINE, 16, CustomItem.PIERRE_LUSTREE, 1, CustomItem.MANA, 16, null, 0, null, 0),
	JAMBES_SENTINELLE(4, 48, CustomEquipment.JAMBES_SENTINELLE, 1, CustomItem.ACIER, 7, CustomItem.CHARBON_RAFINE, 14, CustomItem.PIERRE_LUSTREE, 1, CustomItem.MANA, 14, null, 0, null, 0),
	BOTTES_SENTINELLE(4, 32, CustomEquipment.BOTTES_SENTINELLE, 1, CustomItem.ACIER, 4, CustomItem.CHARBON_RAFINE, 8, CustomItem.PIERRE_LUSTREE, 1, CustomItem.MANA, 8, null, 0, null, 0),
	EPEE_SENTINELLE(4, 44, CustomEquipment.EPEE_SENTINELLE, 1, CustomItem.ACIER, 6, CustomItem.CHARBON_RAFINE, 12, CustomItem.PIERRE_LUSTREE, 2, CustomItem.MANA, 12, null, 0, null, 0),
	
	CASQUE_CONTRE_MAITRE(5, 60, CustomEquipment.CASQUE_CONTRE_MAITRE, 1, CustomItem.OR, 10, CustomItem.CHARBON_RAFINE, 30, CustomItem.ACIER, 10, CustomItem.MANA, 20, null, 0, null, 0),
	PLASTRON_CONTRE_MAITRE(5, 96, CustomEquipment.PLASTRON_CONTRE_MAITRE, 1, CustomItem.OR, 16, CustomItem.CHARBON_RAFINE, 40, CustomItem.ACIER, 16, CustomItem.MANA, 32, null, 0, null, 0),
	JAMBES_CONTRE_MAITRE(5, 84, CustomEquipment.JAMBES_CONTRE_MAITRE, 1, CustomItem.OR, 14, CustomItem.CHARBON_RAFINE, 35, CustomItem.ACIER, 14, CustomItem.MANA, 28, null, 0, null, 0),
	BOTTES_CONTRE_MAITRE(5, 48, CustomEquipment.BOTTES_CONTRE_MAITRE, 1, CustomItem.OR, 8, CustomItem.CHARBON_RAFINE, 25, CustomItem.ACIER, 18, CustomItem.MANA, 16, null, 0, null, 0),
	EPEE_CONTRE_MAITRE(5, 72, CustomEquipment.EPEE_CONTRE_MAITRE, 1, CustomItem.OR, 12, CustomItem.CHARBON_RAFINE, 32, CustomItem.ACIER, 12, CustomItem.MANA, 24, CustomItem.MANA_PURE, 1, null, 0),
	
	CASQUE_LIEUTENANT(6, 80, CustomEquipment.CASQUE_LIEUTENANT, 1, CustomItem.OR_PURIFIE, 2, CustomItem.OR, 30, CustomItem.CHARBON_CONDENSE, 5, CustomItem.ACIER, 20, CustomItem.MANA_PURE, 5, null, 0),
	PLASTRON_LIEUTENANT(6, 128, CustomEquipment.PLASTRON_LIEUTENANT, 1, CustomItem.OR_PURIFIE, 4, CustomItem.OR, 48, CustomItem.CHARBON_CONDENSE, 8, CustomItem.ACIER, 32, CustomItem.MANA_PURE, 8, null, 0),
	JAMBES_LIEUTENANT(6, 96, CustomEquipment.JAMBES_LIEUTENANT, 1, CustomItem.OR_PURIFIE, 3, CustomItem.OR, 32, CustomItem.CHARBON_CONDENSE, 7, CustomItem.ACIER, 28, CustomItem.MANA_PURE, 7, null, 0),
	BOTTES_LIEUTENANT(6, 64, CustomEquipment.BOTTES_LIEUTENANT, 1, CustomItem.OR_PURIFIE, 2, CustomItem.OR, 24, CustomItem.CHARBON_CONDENSE, 4, CustomItem.ACIER, 16, CustomItem.MANA_PURE, 4, null, 0),
	EPEE_LIEUTENANT(6, 88, CustomEquipment.EPEE_LIEUTENANT, 1, CustomItem.OR_PURIFIE, 5, CustomItem.OR, 36, CustomItem.CHARBON_CONDENSE, 6, CustomItem.ACIER, 24, CustomItem.MANA_PURE, 6, null, 0),
	
	CASQUE_COMMANDANT(7, 160, CustomEquipment.CASQUE_COMMANDANT, 1, CustomItem.DIAMANT, 15, CustomItem.OR_PURIFIE, 10, CustomItem.CHARBON_CONDENSE, 15, CustomItem.MANA_PURE, 60, CustomItem.PIERRE_LUSTREE, 20, null, 0),
	PLASTRON_COMMANDANT(7, 160, CustomEquipment.PLASTRON_COMMANDANT, 1, CustomItem.DIAMANT, 24, CustomItem.OR_PURIFIE, 16, CustomItem.CHARBON_CONDENSE, 24, CustomItem.MANA_PURE, 62, CustomItem.PIERRE_LUSTREE, 24, null, 0),
	JAMBES_COMMANDANT(7, 160, CustomEquipment.JAMBES_COMMANDANT, 1, CustomItem.DIAMANT, 21, CustomItem.OR_PURIFIE, 14, CustomItem.CHARBON_CONDENSE, 21, CustomItem.MANA_PURE, 60, CustomItem.PIERRE_LUSTREE, 21, null, 0),
	BOTTES_COMMANDANT(7, 160, CustomEquipment.BOTTES_COMMANDANT, 1, CustomItem.DIAMANT, 12, CustomItem.OR_PURIFIE, 8, CustomItem.CHARBON_CONDENSE, 12, CustomItem.MANA_PURE, 60, CustomItem.PIERRE_LUSTREE, 12, null, 0),
	EPEE_COMMANDANT(7, 160, CustomEquipment.EPEE_COMMANDANT, 1, CustomItem.DIAMANT, 20, CustomItem.OR_PURIFIE, 14, CustomItem.CHARBON_CONDENSE, 20, CustomItem.MANA_PURE, 64, CustomItem.PIERRE_LUSTREE, 20, null, 0),
	
	;
	
	public int neededLevel;
	public CustomEquipment craftItem;
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
	private CraftForgeron(int neededLevel, int xp, CustomEquipment craftItem, int craftAmount, CustomItem item1, int amount1, CustomItem item2, int amount2, CustomItem item3, int amount3, CustomItem item4, int amount4, CustomItem item5, int amount5, CustomItem item6, int amount6) {
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
	
	public static List<CraftForgeron> ItemsCraftableWithLevel(int level) {
		List<CraftForgeron> list = new ArrayList<CraftForgeron>();
		for(CraftForgeron craft : values()) {
			if(craft.neededLevel == level) {
				list.add(craft);
			}
		}
		return list;
	}
	
	//icone de l'item qui apparait dans la GUI
	public static ItemStack IconeCraft(CraftForgeron craft) {
		ItemStack item = new ItemStack(craft.craftItem.type, 1);
		
		item.setAmount(craft.craftAmount);
		ItemMeta itemM = item.getItemMeta();
		if(craft.craftAmount == 1) {
			itemM.setDisplayName(craft.craftItem.name);
		} else {
			itemM.setDisplayName(craft.craftItem.name + " §r[x"+craft.craftAmount+"]");
		}
		List<String> lore = new ArrayList<String>();
		if(craft.item1 != null) lore.add("§8> "+craft.item1.name+" [x"+craft.amount1+"]");
		if(craft.item2 != null) lore.add("§8> "+craft.item2.name+" [x"+craft.amount2+"]");
		if(craft.item3 != null) lore.add("§8> "+craft.item3.name+" [x"+craft.amount3+"]");
		if(craft.item4 != null) lore.add("§8> "+craft.item4.name+" [x"+craft.amount4+"]");
		if(craft.item5 != null) lore.add("§8> "+craft.item5.name+" [x"+craft.amount5+"]");
		if(craft.item6 != null) lore.add("§8> "+craft.item6.name+" [x"+craft.amount6+"]");
		itemM.setLore(lore);
		if(craft.craftItem.ench1 != null) itemM.addEnchant(craft.craftItem.ench1, craft.craftItem.lvl1, true);
		if(craft.craftItem.ench2 != null) itemM.addEnchant(craft.craftItem.ench2, craft.craftItem.lvl2, true);
		if(craft.craftItem.ench3 != null) itemM.addEnchant(craft.craftItem.ench3, craft.craftItem.lvl3, true);
		if(craft.craftItem.ench4 != null) itemM.addEnchant(craft.craftItem.ench4, craft.craftItem.lvl4, true);
		if(craft.craftItem.ench5 != null) itemM.addEnchant(craft.craftItem.ench5, craft.craftItem.lvl5, true);
		item.setItemMeta(itemM);
		//Bukkit.broadcastMessage("§6"+craft.craftItem.sousId+"§rid:"+item.getTypeId()+", §7sous-id:"+item.getData());
		return item;
	}
	
	
}
