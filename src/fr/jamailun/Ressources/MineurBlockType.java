package fr.jamailun.Ressources;

import org.bukkit.Material;

public enum MineurBlockType {

			//TYPE, XP, SEC, LEVEL-NEED
	GRAVEL(Material.GRAVEL, 2, 120, 1),
	COBBLESTONE(Material.COBBLESTONE, 3, 5, 1),
	COAL_ORE(Material.COAL_ORE, 6, 210, 2),
	IRON_ORE(Material.IRON_ORE, 12, 300, 3),
	GOLD_ORE(Material.GOLD_ORE, 20, 600, 4),
	LAPIS_ORE(Material.LAPIS_ORE, 25, 900, 5),
	DIAMOND_ORE(Material.DIAMOND_ORE, 50, 1200, 6);
	
	public Material type;
	public int xp;
	public int sec; //temps en seconde: la conversion en LONG est effectuée post-process
	public int levelNeed;
	
	private MineurBlockType(Material type, int xp, int sec, int levelNeed) {
		this.type = type;
		this.xp = xp;
		this.sec = sec;
		this.levelNeed = levelNeed;
	}
	
	public static int getXpGiven(Material type) {
		for (MineurBlockType blockType : values()) {
            if (blockType.type == type) {
                return blockType.xp;
			}
		}
		return 0;
	}
	
	public static int getTimer(Material type) {
		for (MineurBlockType blockType : values()) {
            if (blockType.type == type) {
                return blockType.sec;
            }
		}
		return 0;
	}
	
	public static int getLevelNeed(Material type) {
		for (MineurBlockType blockType : values()) {
            if (blockType.type == type) {
                return blockType.levelNeed;
            }
		}
		return 0;
	}
	
	public static boolean isMineurType(Material type) {
		for (MineurBlockType block : values())
			if(block.type == type)
				return true;
		return false;
	}
}
