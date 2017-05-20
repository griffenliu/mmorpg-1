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
	PIERRE_TAILLEE(Material.COBBLESTONE, 0, "§rPierre taillée", "§8Une roche fraichement minée", null, null, false),
	PIERRE_LISSE(Material.STONE, 0,"§rPierre lisse", "§8Une piere lissé avec amour", null, null, false),
	PIERRE_POLIE(Material.FLINT, 0,"§bPierre polie", "§8Joliment manufractuée: cette pierre", "§8pourrait avoir de nombreuses utilités.", "", false),
	CHARBON(Material.COAL, 0, "§rCharbon", "§8Assez salissant, mais utile.", null, null, false),
	CHARBON_RAFINE(Material.COAL, 1, "§rCharbon rafiné", "§8Ce charbon rafiné est un condensé de", "§8ce qu'il y a de meilleur dans le charbon.", null, false),
	CHARBON_CONDENSE(Material.COAL_BLOCK, 0, "§bCharbon condensé", "§8Un amat condensé de poudre", "§8de charbon brut.", null, false),
	PIERRE_LUSTREE(Material.FLINT, 0, "§bPierre lustrée", "§8L'éclat qu'elle émet ", "§8est assez hypnotisant...", null, true),
	FER_MINERAI(Material.IRON_ORE, 0, "§rMinerai de fer", "§8Ce minerai a du potentiel.", null, null, false),
	FER(Material.IRON_INGOT, 0, "§7Lingot de fer", "§8Un matériau résistant !", null, null, false),
	FER_CONDENSE(Material.IRON_BLOCK, 0, "§bFer condensé", "§8Entre ça et votre main, c'est", "§8la dernière qui perd.", null, false),
	ACIER(Material.IRON_INGOT, 0, "§7Acier", "§8Vraiment plus résistant que", "§8le fer classique !", null, true),
	OR_MINERAI(Material.GOLD_ORE, 0, "§rMinerai d'or", "§8Un minerai souvent disputé.", null, null, false),
	OR_POUDRE(Material.GOLD_NUGGET, 0, "§rPépite d'or", "§8Une pépite qui a une valeur", "§8repectée par tous les peuples.", null, false),
	OR(Material.GOLD_INGOT, 0, "§7Lingot d'or", "§8Ce matériau à des propriétés magiques", "§8qui permettent d'expliquer son prix.", null, false),
	OR_CONDENSE(Material.GOLD_BLOCK, 0, "§rOr condensé", "§8Comment faites-vous pour porter", "§8quelque chose d'aussi lours ?", null, false),
	MANA_MINERAI(Material.LAPIS_ORE, 0, "§rMinerai de mana", "§8Ce minerai à été formé dans la magie", "§8persistente originelle.", null, false),
	MANA(Material.INK_SACK, 4, "§bMana", "§8Une pierre longtemps recherchée.", "§8Vous sentez comme une émanation de chaleur.", null, false),
	MANA_PURE(Material.INK_SACK, 4, "§aMana pure", "§7La magie dégagée par cette pierre est", "§8très impressionante. Evitez de la briser...", null, true),
	OR_PURIFIE(Material.GOLD_INGOT, 0, "§aOr purifié", "§7La résistance de ce matériau est", "§7vraiment inouïe: sont prix l'est aussi", null, true),
	DIAMANT_MINERAI(Material.DIAMOND_ORE, 0, "§rMinerai de diamant", "§8Un minerai qui brille de mille feux", null, null, false),
	DIAMANT(Material.DIAMOND, 0, "§bDiamant", "§8Cette pierre précieuse est connue", "§8pour sa pureté et sa rigidité.", null, false),
	DIAMANT_POLI(Material.DIAMOND, 0, "§aDiamant poli", "§7Le diamant est resplendissant et scintille", "§7d'une manière enjouée.", null, true),
	CRISTAL_DIAMANTE(Material.DIAMOND_BLOCK, 0, "§dCrystal diamanté", "§7Ce crystal est très recherché pour", "§7sa pureté et sa capacité à absorber la magie.", "§7Evitez de l'égarer !", true),

//PAYSAN
	/*=============================ITEMS CUSTOMS=================================*\
	 *  item de farine = REDSTONE_COMPARATOR
	 *  item de bonbon = RAW_FISH / 2 (poisson clown)
	 *  item de carotte sale = RAW_FISH / 3 (poisson ballon)
	 */
	
	BLE(Material.WHEAT, 0, "§rBlé", "§8Le fruit de votre dur labeur.", null, null, false),
	FARINE(Material.REDSTONE_COMPARATOR, 0, "§rFarine", "§8Ouais ba c'est du blé moulut quoi.", "§8Arrêtez d'essayer de le vendre !", null, false),
	PAIN_BASIQUE(Material.BREAD, 0, "§rPain", "§8Un pain sec. Avec peu de mie.", "§8Essayez de faire mieux.", "§c[regen I / 10 secondes]", false),
	
	CANNE(Material.SUGAR_CANE, 0, "§rCanne à sucre", "§8Plus sucré que la bettrave.", null, null, false),
	SUCRE(Material.SUGAR, 0, "§rSucre", "§8Issu de la canne à sucre, tous", "§8les enfants l'adore !", null, false),
	BONBON(Material.RAW_FISH, 2, "§bBonbon", "§8C'est certes bon, mais n'en", "§8abusez pas: le diabète c'est pas cool.", "§b[vitesse II / 5 minutes]", false),
	
	PATATE(Material.POTATO_ITEM, 0, "§rPatate", "§8Mangé par les paysans du monde", "§8entier depuis des sciècles.", null, false),
	PATATE_POURRIE(Material.POISONOUS_POTATO, 0, "§rPatate pourrie", "§8Pas de chance ! Cette patate a été", "§8dévorée par les vers. Vous", "§8devriez la jeter...", false),
	FARINE_FECULEE(Material.REDSTONE_COMPARATOR, 0, "§rFarine féculée", "§8Très riche en féculents", null, null, false),
	PAIN_GOURMAND(Material.BREAD, 0, "§bPain gourmand", "§8Un pain vraiment bon, qui", "§8fait du bien au corps.", "§c[+3#coeur / 6min]", true),
	
	MELON(Material.MELON, 0, "§rMelon", "§8Est-ce un melon...", "§8ou une pastèque ?", null, false),
	MELON_DORE(Material.SPECKLED_MELON, 0, "§bMelon doré", "§8Une tranche de melon extrèment rare.", "§8La conserver serait une bonne", "§7idée non ?", false),
	MELON_COMPRESSE(Material.MELON_BLOCK, 0, "§rMelon compressé", "§7Une tranche de melon extrèment rare.", "§7La conserver serait une bonne", "§7idée non ?", false),
	MELON_EPURE(Material.SPECKLED_MELON, 0, "§aMelon épuré", "§7Ce melon a des effets extrèmement bénéfiques", "§7pour l'organisme qui l'ingère. Goûtez !", "§c[+10#coeur / 8min]", true),
	
	CAROTTE_SALE(Material.RAW_FISH, 3, "§rCarotte sale", "§8Elle est pleine de terre, ne", "§8la mangez pas !", null, false),
	CAROTTE(Material.CARROT_ITEM, 0, "§rCarotte", "§8A ce qu'il parait, en manger fait", "§8fait grandir les oreilles. Dans", "§8le doute, arrêtez d'en manger.", false),
	PAIN_SOYEUX(Material.BREAD, 0, "§bPain soyeux", "§8Cette mie mène l'esprit au 7e ciel !", null, "§c[+5#coeur / 10min]", true),
	
	
//AUTRE
	EAU(Material.POTION, 0, "§rEau", "§8Une eau claire, ayant de multiples usages.", null, null, false),
	BATON(Material.STICK, 0, "§rBaton", "§8Ce matériaux de base est très utile.", null, null, false),
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
			return getItem("§rPierre taillée");
		if(ressource.equalsIgnoreCase("coal")) 
			return getItem("§rCharbon");
		if(ressource.equalsIgnoreCase("iron")) 
			return getItem("§rMinerai de fer");
		if(ressource.equalsIgnoreCase("gold")) 
			return getItem("§rMinerai d'or");
		if(ressource.equalsIgnoreCase("lapis")) 
			return getItem("§rMinerai de mana");
		if(ressource.equalsIgnoreCase("diamond")) 
			return getItem("§rMinerai de diamant");
		
		//PAYSAN
		if(ressource.equalsIgnoreCase("ble"))
			return getItem("§rBlé");
		if(ressource.equalsIgnoreCase("canne"))
			return getItem("§rCanne à sucre");
		if(ressource.equalsIgnoreCase("patate")) {
			if(RandInt(1,25) == 10) return getItem("§rPatate pourrie");
			return getItem("§rPatate");
		}
		if(ressource.equalsIgnoreCase("melon")) {
			if(RandInt(1,100) == 10) return getItem("§rMelon doré");
			ItemStack it = getItem("§rMelon");
			it.setAmount(RandInt(1,4));
			return it;
		}
		if(ressource.equalsIgnoreCase("carotte"))
			return getItem("§rCarotte sale");
		
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
