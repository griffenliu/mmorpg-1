package fr.jamailun.Ressources;

import org.bukkit.block.Block;

@SuppressWarnings("deprecation")
public enum PaysanBlockType {
	//TYPE(ID), XP, SEC, LEVEL-NEED
		BLE(59, 3, 5, 1),
		CANNE(83, 5, 210, 2),
		PATATE(142, 10, 300, 3),
		MELON(103, 26, 600, 4),
		CAROTTE(141, 20, 900, 5),
		CITROUILLE(86, 38, 1200, 6),
		CACTUS(81, 46, 1500, 7);
	
		public int id;
		public int xp;
		public int sec; //temps en seconde: la conversion en LONG est effectuée post-process
		public int levelNeed;

		private PaysanBlockType(int id, int xp, int sec, int levelNeed) {
			this.id = id;
			this.xp = xp;
			this.sec = sec;
			this.levelNeed = levelNeed;
		}

		
		public static int getXpGiven(Block block) {
			for (PaysanBlockType blockType : values()) {
				if (blockType.id == block.getTypeId()) {
					return blockType.xp;
				}
			}
			return 0;
		}

		public static int getTimer(Block block) {
			for (PaysanBlockType blockType : values()) {
				if (blockType.id == block.getTypeId()) {
						return blockType.sec;
				}
			}
		return 0;
		}

		public static int getLevelNeed(Block block) {
			for (PaysanBlockType blockType : values()) {
				if (blockType.id == block.getTypeId()) {
					return blockType.levelNeed;
				}
			}
		return 0;
		}

		public static boolean isPaysanType(int id) {
			for (PaysanBlockType block : values())
				if(block.id == id)
					return true;
			return false;
		}
}
