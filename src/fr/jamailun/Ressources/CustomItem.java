package fr.jamailun.Ressources;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public enum CustomItem {
		//TYPE, sous-id, TITLE, LORE[1,2,3], brillant
//MINEUR
	PIERRE_TAILLEE(Material.COBBLESTONE, 0, "�rPierre taill�e", "�8Une roche fraichement min�e", null, null, false),
	PIERRE_LISSE(Material.STONE, 0,"�rPierre lisse", "�8Une piere liss� avec amour", null, null, false),
	PIERRE_POLIE(Material.FLINT, 0,"�bPierre polie", "�8Joliment manufractu�e: cette pierre", "�8pourrait avoir de nombreuses utilit�s.", "", false),
	CHARBON(Material.COAL, 0, "�rCharbon", "�8Assez salissant, mais utile.", null, null, false),
	CHARBON_RAFINE(Material.COAL, 1, "�rCharbon rafin�", "�8Ce charbon rafin� est un condens� de", "�8ce qu'il y a de meilleur dans le charbon.", null, false),
	CHARBON_CONDENSE(Material.COAL_BLOCK, 0, "�bCharbon condens�", "�8Un amat condens� de poudre", "�8de charbon brut.", null, false),
	PIERRE_LUSTREE(Material.FLINT, 0, "�bPierre lustr�e", "�8L'�clat qu'elle �met ", "�8est assez hypnotisant...", null, true),
	FER_MINERAI(Material.IRON_ORE, 0, "�rMinerai de fer", "�8Ce minerai a du potentiel.", null, null, false),
	FER(Material.IRON_INGOT, 0, "�7Lingot de fer", "�8Un mat�riau r�sistant !", null, null, false),
	FER_CONDENSE(Material.IRON_BLOCK, 0, "�bFer condens�", "�8Entre �a et votre main, c'est", "�8la derni�re qui perd.", null, false),
	ACIER(Material.IRON_INGOT, 0, "�7Acier", "�8Vraiment plus r�sistant que", "�8le fer classique !", null, true),
	OR_MINERAI(Material.GOLD_ORE, 0, "�rMinerai d'or", "�8Un minerai souvent disput�.", null, null, false),
	OR_POUDRE(Material.GOLD_NUGGET, 0, "�rP�pite d'or", "�8Une p�pite qui a une valeur", "�8repect�e par tous les peuples.", null, false),
	OR(Material.GOLD_INGOT, 0, "�7Lingot d'or", "�8Ce mat�riau � des propri�t�s magiques", "�8qui permettent d'expliquer son prix.", null, false),
	OR_CONDENSE(Material.GOLD_BLOCK, 0, "�rOr condens�", "�8Comment faites-vous pour porter", "�8quelque chose d'aussi lours ?", null, false),
	MANA_MINERAI(Material.LAPIS_ORE, 0, "�rMinerai de mana", "�8Ce minerai � �t� form� dans la magie", "�8persistente originelle.", null, false),
	MANA(Material.INK_SACK, 4, "�bMana", "�8Une pierre longtemps recherch�e.", "�8Vous sentez comme une �manation de chaleur.", null, false),
	MANA_PURE(Material.INK_SACK, 4, "�aMana pure", "�7La magie d�gag�e par cette pierre est", "�8tr�s impressionante. Evitez de la briser...", null, true),
	OR_PURIFIE(Material.GOLD_INGOT, 0, "�aOr purifi�", "�7La r�sistance de ce mat�riau est", "�7vraiment inou�e: sont prix l'est aussi", null, true),
	DIAMANT_MINERAI(Material.DIAMOND_ORE, 0, "�rMinerai de diamant", "�8Un minerai qui brille de mille feux", null, null, false),
	DIAMANT(Material.DIAMOND, 0, "�bDiamant", "�8Cette pierre pr�cieuse est connue", "�8pour sa puret� et sa rigidit�.", null, false),
	DIAMANT_POLI(Material.DIAMOND, 0, "�aDiamant poli", "�7Le diamant est resplendissant et scintille", "�7d'une mani�re enjou�e.", null, true),
	CRISTAL_DIAMANTE(Material.DIAMOND_BLOCK, 0, "�dCrystal diamant�", "�7Ce crystal est tr�s recherch� pour", "�7sa puret� et sa capacit� � absorber la magie.", "�7Evitez de l'�garer !", true),

//PAYSAN
	/*=============================ITEMS CUSTOMS=================================*\
	 *  item de farine = REDSTONE_COMPARATOR
	 *  item de bonbon = RAW_FISH / 2 (poisson clown)
	 *  item de carotte sale = RAW_FISH / 3 (poisson ballon)
	 */
	
	BLE(Material.WHEAT, 0, "�rBl�", "�8Le fruit de votre dur labeur.", null, null, false),
	FARINE(Material.REDSTONE_COMPARATOR, 0, "�rFarine", "�8Ouais ba c'est du bl� moulut quoi.", "�8Arr�tez d'essayer de le vendre !", null, false),
	PAIN_BASIQUE(Material.BREAD, 0, "�rPain", "�8Un pain sec. Avec peu de mie.", "�8Essayez de faire mieux.", "�c[regen I / 10 secondes]", false),
	
	CANNE(Material.SUGAR_CANE, 0, "�rCanne � sucre", "�8Plus sucr� que la bettrave.", null, null, false),
	SUCRE(Material.SUGAR, 0, "�rSucre", "�8Issu de la canne � sucre, tous", "�8les enfants l'adore !", null, false),
	BONBON(Material.RAW_FISH, 2, "�bBonbon", "�8C'est certes bon, mais n'en", "�8abusez pas: le diab�te c'est pas cool.", "�b[vitesse II / 5 minutes]", false),
	
	PATATE(Material.POTATO_ITEM, 0, "�rPatate", "�8Mang� par les paysans du monde", "�8entier depuis des sci�cles.", null, false),
	PATATE_POURRIE(Material.POISONOUS_POTATO, 0, "�rPatate pourrie", "�8Pas de chance ! Cette patate a �t�", "�8d�vor�e par les vers. Vous", "�8devriez la jeter...", false),
	FARINE_FECULEE(Material.REDSTONE_COMPARATOR, 0, "�rFarine f�cul�e", "�8Tr�s riche en f�culents", null, null, false),
	PAIN_GOURMAND(Material.BREAD, 0, "�bPain gourmand", "�8Un pain vraiment bon, qui", "�8fait du bien au corps.", "�c[+3#coeur / 6min]", true),
	
	MELON(Material.MELON, 0, "�rMelon", "�8Est-ce un melon...", "�8ou une past�que ?", null, false),
	MELON_DORE(Material.SPECKLED_MELON, 0, "�bMelon dor�", "�8Une tranche de melon extr�ment rare.", "�8La conserver serait une bonne", "�7id�e non ?", false),
	MELON_COMPRESSE(Material.MELON_BLOCK, 0, "�rMelon compress�", "�7Une tranche de melon extr�ment rare.", "�7La conserver serait une bonne", "�7id�e non ?", false),
	MELON_EPURE(Material.SPECKLED_MELON, 0, "�aMelon �pur�", "�7Ce melon a des effets extr�mement b�n�fiques", "�7pour l'organisme qui l'ing�re. Go�tez !", "�c[+10#coeur / 8min]", true),
	
	CAROTTE_SALE(Material.RAW_FISH, 3, "�rCarotte sale", "�8Elle est pleine de terre, ne", "�8la mangez pas !", null, false),
	CAROTTE(Material.CARROT_ITEM, 0, "�rCarotte", "�8A ce qu'il parait, en manger fait", "�8fait grandir les oreilles. Dans", "�8le doute, arr�tez d'en manger.", false),
	PAIN_SOYEUX(Material.BREAD, 0, "�bPain soyeux", "�8Cette mie m�ne l'esprit au 7e ciel !", null, "�c[+5#coeur / 10min]", true),
	
	
//AUTRE
	EAU(Material.POTION, 0, "�rEau", "�8Une eau claire, ayant de multiples usages.", null, null, false),
	BATON(Material.STICK, 0, "�rBaton", "�8Ce mat�riaux de base est tr�s utile.", null, null, false),
	;
	
	public Material type;
	public int sousId;
	public String name;
	public String lore1;
	public String lore2;
	public String lore3;
	public boolean brillant; 
	
	private CustomItem(Material type, int sousId, String name, String lore1, String lore2, String lore3, boolean brillant) {
		this.type = type;
		this.sousId = sousId;
		this.name= name;
		this.lore1 = lore1;
		this.lore2 = lore2;
		this.lore3 = lore3;
		this.brillant = brillant;
	}
	
	//item que l'on give pour un craft
	public static ItemStack getItem(String name) {
		for(CustomItem customItem : values()) {
			if(customItem.name == name) {
				ItemStack item = new ItemStack(customItem.type, 1, (byte) customItem.sousId);
				ItemMeta itemM = item.getItemMeta();
				itemM.setDisplayName(customItem.name);
				if(customItem.lore1 != null) {
					List<String> itemL = new ArrayList<String>();
					itemL.add(customItem.lore1);
					if(customItem.lore2 != null) itemL.add(customItem.lore2);
					if(customItem.lore3 != null) {
						if(customItem.lore3.contains("#coeur")) {
							itemL.add(customItem.lore3.replaceAll("#coeur", new String(Character.toChars(10084))));
						} else {
							itemL.add(customItem.lore3);
						}
					}
					itemM.setLore(itemL);
				}
				if(customItem.brillant) itemM.addEnchant(Enchantment.ARROW_INFINITE, 0, false);
				item.setItemMeta(itemM);
				return item;
			}
		}
		return new ItemStack(Material.BARRIER);
	}
	
	public static ItemStack getRessourceLoot(String ressource) {
		//MINEUR
		if(ressource.equalsIgnoreCase("cobble"))
			return getItem("�rPierre taill�e");
		if(ressource.equalsIgnoreCase("coal")) 
			return getItem("�rCharbon");
		if(ressource.equalsIgnoreCase("iron")) 
			return getItem("�rMinerai de fer");
		if(ressource.equalsIgnoreCase("gold")) 
			return getItem("�rMinerai d'or");
		if(ressource.equalsIgnoreCase("lapis")) 
			return getItem("�rMinerai de mana");
		if(ressource.equalsIgnoreCase("diamond")) 
			return getItem("�rMinerai de diamant");
		
		//PAYSAN
		if(ressource.equalsIgnoreCase("ble"))
			return getItem("�rBl�");
		if(ressource.equalsIgnoreCase("canne"))
			return getItem("�rCanne � sucre");
		if(ressource.equalsIgnoreCase("patate")) {
			if(RandInt(1,25) == 10) return getItem("�rPatate pourrie");
			return getItem("�rPatate");
		}
		if(ressource.equalsIgnoreCase("melon")) {
			if(RandInt(1,100) == 10) return getItem("�rMelon dor�");
			ItemStack it = getItem("�rMelon");
			it.setAmount(RandInt(1,4));
			return it;
		}
		if(ressource.equalsIgnoreCase("carotte"))
			return getItem("�rCarotte sale");
		
		return new ItemStack(Material.BARRIER);
	}
	
	private static int RandInt(int min, int max) {
		return min + (int)(Math.random() * ((max - min) + 1));
	}
	
	public static boolean isSameItem(ItemStack item, CustomItem cI) {
		if(item.getItemMeta() == null) return false;
		if(item.getAmount() == 0) return false;
		ItemMeta meta = item.getItemMeta();
		if(meta.getLore() == null) return false;
		ItemMeta metaCI = getItem(cI.name).getItemMeta();
		if(meta.getLore() ==metaCI.getLore())
			if(meta.getDisplayName().equalsIgnoreCase(metaCI.getDisplayName()))
				return true;
		return false;
	}
}
