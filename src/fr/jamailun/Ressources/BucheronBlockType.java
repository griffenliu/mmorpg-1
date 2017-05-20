package fr.jamailun.Ressources;

import org.bukkit.Material;
import org.bukkit.block.Block;

@SuppressWarnings("deprecation")
public enum BucheronBlockType {
	//TYPE, SOUS-ID, XP, SEC, LEVEL-NEED
	OAK(Material.LOG, (byte)0, 3, 5, 1),
	BIRCH(Material.LOG, (byte)2, 5, 210, 2),
	SPRUCE(Material.LOG, (byte)1, 10, 300, 3),
	JUNGLE(Material.LOG, (byte)3, 26, 600, 4),
	DARK_OAK(Material.LOG_2, (byte)20, 20, 900, 5),
	ACACIA(Material.LOG_2, (byte)1, 38, 1200, 6);

	public Material type;
	public int sousId;
	public int xp;
	public int sec; //temps en seconde: la conversion en LONG est effectuée post-process
	public int levelNeed;

	private BucheronBlockType(Material type, byte sousId, int xp, int sec, int levelNeed) {
		this.type = type;
		this.sousId = sousId;
		this.xp = xp;
		this.sec = sec;
		this.levelNeed = levelNeed;
	}

	
	public static int getXpGiven(Block block) {
		for (BucheronBlockType blockType : values()) {
			if (blockType.type == block.getType()) {
				if(blockType.sousId == block.getData()) {
					return blockType.xp;
				}
			}
		}
		return 0;
	}

	public static int getTimer(Block block) {
		for (BucheronBlockType blockType : values()) {
			if (blockType.type == block.getType()) {
				if(blockType.sousId == block.getData()) {
					return blockType.sec;
				}
			}
		}
	return 0;
	}

	public static int getLevelNeed(Block block) {
		for (BucheronBlockType blockType : values()) {
			if (blockType.type == block.getType()) {
				if(blockType.sousId == block.getData()) {
					return blockType.levelNeed;
				}
			}
		}
	return 0;
	}

	public static boolean isBucheronType(Material type) {
		for (BucheronBlockType block : values())
			if(block.type == type)
				return true;
		return false;
	}
}
