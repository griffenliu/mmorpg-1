package fr.jamailun.Ressources;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public enum CustomEquipment {
		//TYPE, TITLE, LORE[1,2,3], {ENCHANTS+LVL}[1,2,3,4,5]
	//HUMAIN
	CASQUE_RECRUE(Material.CHAINMAIL_HELMET, "§rCasque de recrue", "§8Ce casque vous protégera des premiers chocs", null, null, null, 0, null, 0, null, 0, null, 0, null, 0, 1),
	PLASTRON_RECRUE(Material.CHAINMAIL_CHESTPLATE, "§rPlastron de recrue", "§8La nullité de cette armure devrait vous faire", "§8honte. Ne la mettez pas en public...", "§8Vraiment. Arretez. C'est gênant.", null, 0, null, 0, null, 0, null, 0, null, 0, 1),
	JAMBES_RECRUE(Material.CHAINMAIL_LEGGINGS, "§rJambières de recrue", "Arretez de vous faire du mal et", "enlevez ce truc !", null, null, 0, null, 0, null, 0, null, 0, null, 0, 1),
	BOTTES_RECRUE(Material.CHAINMAIL_BOOTS, "§rBottes de recrue", "§8Vous comptez vraiment marcher avec ça au pied ??", "§8J'ai mal pour vous :/", null, null, 0, null, 0, null, 0, null, 0, null, 0, 1),
	EPEE_RECRUE(Material.IRON_SWORD, "§rEpée de recrue", "§8Vous n'arriveriez pas à couper quoi", "§8que ce soit avec ce genre de lame !", null, null, 0, null, 0, null, 0, null, 0, null, 0, 1),
	CASQUE_SOLDAT(Material.IRON_HELMET, "§rCasque de soldat", "§8Ce casque est très moyen... Essayez", "§8de prendre du galon !", null, null, 0, null, 0, null, 0, null, 0, null, 0, 5),
	PLASTRON_SOLDAT(Material.IRON_CHESTPLATE, "§rPlastron de soldat", "§8Rouillé, fin,... A votre place", "§8je ne porterais pas trop longtemps.", null, null, 0, null, 0, null, 0, null, 0, null, 0, 5),
	JAMBES_SOLDAT(Material.IRON_LEGGINGS, "§rJambières de soldat", "§8Ces jambières ne sont pas les plus", "§8agréables pour vos genoux...", null, null, 0, null, 0, null, 0, null, 0, null, 0, 5),
	BOTTES_SOLDAT(Material.IRON_BOOTS, "§rBottes de soldat", "§8Les trous dans le métal ont au", "§8moins la vertue d'aérer vos pieds.", null, null, 0, null, 0, null, 0, null, 0, null, 0, 5),
	EPEE_SOLDAT(Material.IRON_SWORD, "§rEpée de soldat", "§8La lame est un peu émoussée, mais c'est suffisant", "§8pour éliminer un bado ivre !", null, null, 0, null, 0, null, 0, null, 0, null, 0, 5),
	CASQUE_GARDE(Material.IRON_HELMET, "§rCasque de garde", "§8Ce casque vous permettra de tenir votre ronde.", null, null, Enchantment.PROTECTION_ENVIRONMENTAL, 1, Enchantment.DURABILITY, 1, null, 0, null, 0, null, 0, 10),
	PLASTRON_GARDE(Material.IRON_CHESTPLATE, "§rPlastron de garde", "§8La magie qui perdure dans ce plastron est...", " ", "§8quasi-inexistante ?", Enchantment.PROTECTION_ENVIRONMENTAL, 1, Enchantment.DURABILITY, 1, null, 0, null, 0, null, 0, 10),
	JAMBES_GARDE(Material.IRON_LEGGINGS, "§rJambières de garde", "§8Ces jambières vous grattent énormément mais", "§8ne bougez pas: le contre-maître arrive !", null, Enchantment.PROTECTION_ENVIRONMENTAL, 1, Enchantment.DURABILITY, 1, null, 0, null, 0, null, 0, 10),
	BOTTES_GARDE(Material.IRON_BOOTS, "§rBottes de garde", "§8Pensez à vous venger de votre ami qui vous a", "§8volé vos semelles... OOH et vous", "enlèverez ces ampoules plus tard !", Enchantment.PROTECTION_ENVIRONMENTAL, 1, Enchantment.DURABILITY, 1, null, 0, null, 0, null, 0, 10),
	EPEE_GARDE(Material.IRON_SWORD, "§rEpée de garde", "§8Si un voleur passe par ici, vous", "§8l'égorgerez assez facilement !", null, Enchantment.DAMAGE_ALL, 1, Enchantment.DURABILITY, 1, null, 0, null, 0, null, 0, 10),
	CASQUE_SENTINELLE(Material.IRON_HELMET, "§rCasque de sentinelle", "§8Vous vous sentez protégé hein ?", "§8Ba ne vous la pétez pas trop: ya mieux !", null, Enchantment.PROTECTION_ENVIRONMENTAL, 2, Enchantment.DURABILITY, 3, null, 0, null, 0, null, 0, 15),
	PLASTRON_SENTINELLE(Material.IRON_CHESTPLATE, "§rPlastron de sentinelle", "§8Un plastron sobre et élégant pour", "§8défiler sur les remparts.", null, Enchantment.PROTECTION_ENVIRONMENTAL, 2, Enchantment.DURABILITY, 3, null, 0, null, 0, null, 0, 15),
	JAMBES_SENTINELLE(Material.IRON_LEGGINGS, "§rJambières de sentinelle", "§8Voici là de très belles jambières !", "§8Faites pas votre radin et nettoyez-les !", null, Enchantment.PROTECTION_ENVIRONMENTAL, 2, Enchantment.DURABILITY, 3, null, 0, null, 0, null, 0, 15),
	BOTTES_SENTINELLE(Material.IRON_BOOTS, "§rBottes de sentinelle", "§8ENFIN des bottes confortables pour", "§8vos pieds répugnants !", null, Enchantment.PROTECTION_ENVIRONMENTAL, 2, Enchantment.DURABILITY, 3, null, 0, null, 0, null, 0, 15),
	EPEE_SENTINELLE(Material.IRON_SWORD, "§rEpée de sentinelle", "§8Repoussez l'envahisseur pour votre faction !", " ", "§8Ainsi qu'une future promotion... ", Enchantment.DAMAGE_ALL, 3, Enchantment.DURABILITY, 5, Enchantment.KNOCKBACK, 1, null, 0, null, 0, 15),
	CASQUE_CONTRE_MAITRE(Material.GOLD_HELMET, "§bCasque de contre-maître", "§8Voilà un casque qui en", "§8impressionera plus d'une !", null, Enchantment.PROTECTION_ENVIRONMENTAL, 5, Enchantment.PROTECTION_FIRE, 1, Enchantment.DURABILITY, 11, null, 0, null, 0, 20),
	PLASTRON_CONTRE_MAITRE(Material.GOLD_CHESTPLATE, "§bPlastron de contre-maître", "§8En ce plastron vous trouverez plus d'or que", "§8chez tous les vagabons réunis xd", null, Enchantment.PROTECTION_ENVIRONMENTAL, 5, Enchantment.PROTECTION_FIRE, 1, Enchantment.DURABILITY, 11, null, 0, null, 0, 20),
	JAMBES_CONTRE_MAITRE(Material.GOLD_LEGGINGS, "§bJambières de contre-maître", "§8Certains ont des jantes chromés,", "§8d'autres des jambières lustrées...", null, Enchantment.PROTECTION_ENVIRONMENTAL, 5, Enchantment.PROTECTION_FIRE, 1, Enchantment.DURABILITY, 11, null, 0, null, 0, 20),
	BOTTES_CONTRE_MAITRE(Material.GOLD_BOOTS, "§bBottes de contre-maître", "§8Ces bottes sont en or massif....", "§8Vous devez avoir de sacrés cuisses Oo", null, Enchantment.PROTECTION_ENVIRONMENTAL, 5, Enchantment.PROTECTION_FIRE, 1, Enchantment.DURABILITY, 11, null, 0, null, 0, 20),
	EPEE_CONTRE_MAITRE(Material.GOLD_SWORD, "§bEpée de contre-maître", "§8Cette épée possède un produit", "§8inflammable qui chatouillera vos cibles.", null, Enchantment.DAMAGE_ALL, 5, Enchantment.FIRE_ASPECT, 1, Enchantment.KNOCKBACK, 2, Enchantment.DURABILITY, 18, Enchantment.DAMAGE_UNDEAD, 1, 20),
	CASQUE_LIEUTENANT(Material.GOLD_HELMET, "§bCasque de lieutenant", "§8Confortable, élégant, résistant,...", "§8Ce casque a beaucoup de qualités...", "§8 (que vous n'avez sûrement pas)", Enchantment.PROTECTION_ENVIRONMENTAL, 8, Enchantment.PROTECTION_FIRE, 2, Enchantment.PROTECTION_PROJECTILE , 2, Enchantment.DURABILITY, 16, null, 0, 25),
	PLASTRON_LIEUTENANT(Material.GOLD_CHESTPLATE, "§bPlastron de lieutenant", "§8Arrêtez de manger toute la sauce bolognaise,", "§8ou vous ne pourrez plus rentrer dans ce", "§8magnifique plastron :(", Enchantment.PROTECTION_ENVIRONMENTAL, 8, Enchantment.PROTECTION_FIRE, 2, Enchantment.PROTECTION_PROJECTILE , 2, Enchantment.DURABILITY, 16, Enchantment.THORNS, 1, 25),
	JAMBES_LIEUTENANT(Material.GOLD_LEGGINGS, "§bJambières de lieutenant", "§8Ce déhanché vous donne un air stupide.", "§8Arretez cela immédiatement !", null, Enchantment.PROTECTION_ENVIRONMENTAL, 8, Enchantment.PROTECTION_FIRE, 2, Enchantment.PROTECTION_PROJECTILE , 2, Enchantment.DURABILITY, 16, null, 0, 25),
	BOTTES_LIEUTENANT(Material.GOLD_BOOTS, "§bBottes de lieutenant", "§8Ca ne ressemble pas à", "§8du made in China !", null, Enchantment.PROTECTION_ENVIRONMENTAL, 8, Enchantment.PROTECTION_FIRE, 2, Enchantment.PROTECTION_PROJECTILE , 2, Enchantment.DURABILITY, 16, null, 0, 25),
	EPEE_LIEUTENANT(Material.GOLD_SWORD, "§bEpée de lieutenant", "§8Une efficacité prouvée par toutes", "§8les publicités de votre région !", null, Enchantment.DAMAGE_ALL, 8, Enchantment.FIRE_ASPECT, 2, Enchantment.KNOCKBACK, 3, Enchantment.DURABILITY, 22, Enchantment.DAMAGE_UNDEAD, 2, 25),
	CASQUE_COMMANDANT(Material.DIAMOND_HELMET, "§aCasque de commandant", "§7Un casque qui signalera à vos alliés", "§7où se réfugier quand la bataille sera perdue...", "Courez. Vite.", Enchantment.PROTECTION_ENVIRONMENTAL, 10, Enchantment.PROTECTION_FIRE, 4, Enchantment.PROTECTION_PROJECTILE, 3, Enchantment.DURABILITY, 20, null, 0, 30),
	PLASTRON_COMMANDANT(Material.DIAMOND_CHESTPLATE, "§aPlastron de commandant", "§7La magie que renferme ce plastron est", "§7très puissante: vous pouvez avoir confiance.", "§8(En revanche mettez du déo svp !)", Enchantment.PROTECTION_ENVIRONMENTAL, 10, Enchantment.PROTECTION_FIRE, 4, Enchantment.PROTECTION_PROJECTILE, 7, Enchantment.DURABILITY, 20, Enchantment.THORNS, 2, 30),
	JAMBES_COMMANDANT(Material.DIAMOND_LEGGINGS, "§aJambières de commandant", "§7Votre paire de cuisse musclées sera", "§7à l'abri dans ces jambières puissantes.", null, Enchantment.PROTECTION_ENVIRONMENTAL, 10, Enchantment.PROTECTION_FIRE, 4, Enchantment.PROTECTION_PROJECTILE, 3, Enchantment.DURABILITY, 20, null, 0, 30),
	BOTTES_COMMANDANT(Material.DIAMOND_BOOTS, "§aBottes de commandant", "§7Ces bottes rutillent... Vu que vous n'êtes", "§7pas très soigneux, ce doit être grace", "aux lieutenants désirants une promotion", Enchantment.PROTECTION_ENVIRONMENTAL, 10, Enchantment.PROTECTION_FIRE, 4, Enchantment.PROTECTION_PROJECTILE, 3, Enchantment.DURABILITY, 20, null, 0, 30),
	EPEE_COMMANDANT(Material.DIAMOND_SWORD, "§aEpée de commandant", "§7Avouez-le, cette épée n'a aucun défault.", "§7Elle a été forgé par un forgeron très", "§7expérimenté (qui vous l'a vendu très cher)", Enchantment.DAMAGE_ALL, 10, Enchantment.FIRE_ASPECT, 4, Enchantment.KNOCKBACK, 4, Enchantment.DURABILITY, 26, Enchantment.DAMAGE_UNDEAD, 3, 30),

	//monstres (M_[1-4](1=tete,4=bottes; E=épée)_kit(c=cuir, o=or, f=fer, h=chain, d=diamand))
	M_1_C1(Material.LEATHER_HELMET, "§8m_1_c1", null, null, null, null, 0, null, 0, null, 0, null, 0, null, 0, 1),
	M_2_C1(Material.LEATHER_CHESTPLATE, "§8m_2_c1", null, null, null, null, 0, null, 0, null, 0, null, 0, null, 0, 1),
	M_3_C1(Material.LEATHER_LEGGINGS, "§8m_3_c1", null, null, null, null, 0, null, 0, null, 0, null, 0, null, 0, 1),
	M_4_C1(Material.LEATHER_BOOTS, "§8m_4_c1", null, null, null, null, 0, null, 0, null, 0, null, 0, null, 0, 1),
	M_BATON(Material.STICK, "§8m_baton", null, null, null, null, 0, null, 0, null, 0, null, 0, null, 0, 1),
	M_E_B1(Material.WOOD_SWORD, "§8m_e_b1", null, null, null, null, 0, null, 0, null, 0, null, 0, null, 0, 1),
	
	M_1_C2(Material.LEATHER_HELMET, "§8m_1_c2", null, null, null, Enchantment.PROTECTION_ENVIRONMENTAL, 1, null, 0, null, 0, null, 0, null, 0, 1),
	M_2_C2(Material.LEATHER_CHESTPLATE, "§8m_2_c2", null, null, null, Enchantment.PROTECTION_ENVIRONMENTAL, 1, null, 0, null, 0, null, 0, null, 0, 1),
	M_3_C2(Material.LEATHER_LEGGINGS, "§8m_3_c2", null, null, null, Enchantment.PROTECTION_ENVIRONMENTAL, 1, null, 0, null, 0, null, 0, null, 0, 1),
	M_4_C2(Material.LEATHER_BOOTS, "§8m_4_c2", null, null, null, Enchantment.PROTECTION_ENVIRONMENTAL, 1, null, 0, null, 0, null, 0, null, 0, 1),
	M_E_B2(Material.WOOD_SWORD, "§8m_e_b2", null, null, null, Enchantment.DAMAGE_ALL, 1, null, 0, null, 0, null, 0, null, 0, 1),
	
	M_1_C3(Material.LEATHER_HELMET, "§8m_1_c3", null, null, null, Enchantment.PROTECTION_ENVIRONMENTAL, 4, null, 0, null, 0, null, 0, null, 0, 1),
	M_2_C3(Material.LEATHER_CHESTPLATE, "§8m_2_c3", null, null, null, Enchantment.PROTECTION_ENVIRONMENTAL, 4, null, 0, null, 0, null, 0, null, 0, 1),
	M_3_C3(Material.LEATHER_LEGGINGS, "§8m_3_c3", null, null, null, Enchantment.PROTECTION_ENVIRONMENTAL, 4, null, 0, null, 0, null, 0, null, 0, 1),
	M_4_C3(Material.LEATHER_BOOTS, "§8m_4_c3", null, null, null, Enchantment.PROTECTION_ENVIRONMENTAL, 4, null, 0, null, 0, null, 0, null, 0, 1),
	M_E_B3(Material.WOOD_SWORD, "§8m_e_b3", null, null, null, Enchantment.DAMAGE_ALL, 4, Enchantment.KNOCKBACK, 1, null, 0, null, 0, null, 0, 1),
	;
	
	public Material type;
	public String name, lore1, lore2, lore3;
	public Enchantment ench1, ench2, ench3, ench4, ench5;
	public int lvl1, lvl2, lvl3, lvl4, lvl5;
	public int levelMini;
	
	private CustomEquipment(Material type, String name, String lore1, String lore2, String lore3, Enchantment ench1, int lvl1, Enchantment ench2, int lvl2, Enchantment ench3, int lvl3, Enchantment ench4, int lvl4, Enchantment ench5, int lvl5, int levelMini) {
		this.type = type;
		this.name= name;
		this.lore1 = lore1;
		this.lore2 = lore2;
		this.lore3 = lore3;
		this.ench1 = ench1;
		this.ench2 = ench2;
		this.ench3 = ench3;
		this.ench4 = ench4;
		this.ench5 = ench5;
		this.lvl1 = lvl1;
		this.lvl2 = lvl2;
		this.lvl3 = lvl3;
		this.lvl4 = lvl4;
		this.lvl5 = lvl5;
		this.levelMini = levelMini;
	}
	
	//item que l'on give pour un craft
	public static ItemStack getItem(String name) {
		for(CustomEquipment customItem : values()) {
			if(customItem.name == name) {
				ItemStack item = new ItemStack(customItem.type, 1);
				ItemMeta itemM = item.getItemMeta();
				itemM.setDisplayName(customItem.name);
				if(customItem.lore1 != null) {
					List<String> itemL = new ArrayList<String>();
					itemL.add("§6[ lvl "+customItem.levelMini+" ]");
					itemL.add(customItem.lore1);
					if(customItem.lore2 != null) itemL.add(customItem.lore2);
					if(customItem.lore3 != null) itemL.add(customItem.lore3);
					itemM.setLore(itemL);
				}
				if(customItem.ench1 != null) itemM.addEnchant(customItem.ench1, customItem.lvl1, true);
				if(customItem.ench2 != null) itemM.addEnchant(customItem.ench2, customItem.lvl2, true);
				if(customItem.ench3 != null) itemM.addEnchant(customItem.ench3, customItem.lvl3, true);
				if(customItem.ench4 != null) itemM.addEnchant(customItem.ench4, customItem.lvl4, true);
				if(customItem.ench5 != null) itemM.addEnchant(customItem.ench5, customItem.lvl5, true);
				
				item.setItemMeta(itemM);
				return item;
			}
		}
		return new ItemStack(Material.BARRIER);
	}	
}
