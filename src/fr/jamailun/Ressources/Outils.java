package fr.jamailun.Ressources;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum Outils {

	HACHE_BOIS(Material.WOOD_AXE, "hache"),
	HACHE_PIERRE(Material.STONE_AXE, "hache"),
	HACHE_FER(Material.IRON_AXE, "hache"),
	HACHE_OR(Material.GOLD_AXE, "hache"),
	HACHE_DIAMANT(Material.DIAMOND_AXE, "hache"),
	
	PIOCHE_BOIS(Material.WOOD_PICKAXE, "pioche"),
	PIOCHE_PIERRE(Material.STONE_PICKAXE, "pioche"),
	PIOCHE_FER(Material.IRON_PICKAXE, "pioche"),
	PIOCHE_OR(Material.GOLD_PICKAXE, "pioche"),
	PIOCHE_DIAMANT(Material.DIAMOND_PICKAXE, "pioche"),
	
	FAUX_BOIS(Material.WOOD_HOE, "faux"),
	FAUX_PIERRE(Material.STONE_HOE, "faux"),
	FAUX_FER(Material.IRON_HOE, "faux"),
	FAUX_OR(Material.GOLD_HOE, "faux"),
	FAUX_DIAMANT(Material.DIAMOND_HOE, "faux"),
	
	PELLE_BOIS(Material.WOOD_SPADE, "pelle"),
	PELLE_PIERRE(Material.STONE_SPADE, "pelle"),
	PELLE_FER(Material.IRON_SPADE, "pelle"),
	PELLE_OR(Material.GOLD_SPADE, "pelle"),
	PELLE_DIAMANT(Material.DIAMOND_SPADE, "pelle");
	
	public Material type;
	public String cate;
	
	private Outils(Material type, String cate) {
		this.type = type;
		this.cate = cate;
	}
	
	public static boolean isType(ItemStack outil, String categorie) {
		for (Outils t : values())
			if (t.type == outil.getType())
				if (t.cate.equalsIgnoreCase(categorie))
					return true;
		return false;
	}
}
