package fr.jamailun.Mmorpg.Classes;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Job {

	private MmoPlayer p;
	private String name;
	private int level;
	private int xp;
	private int slot;
	
	public Job(MmoPlayer p) {
		this.slot = 0;
		this.p = p;
		this.name = "null";
		this.level = 1;
		this.xp = 0;
	}
	
	public Job(MmoPlayer p, int slot) {
		this.slot = slot;
		this.p = p;
		this.name = "null";
		this.level = 1;
		this.xp = 0;
	}
	
	public Job(MmoPlayer p, int slot, String name) {
		this.slot = slot;
		this.p = p;
		this.name = name;
		this.level = 1;
		this.xp = 0;
	}
	
	public Job(MmoPlayer p, int slot, String name, int level) {
		this.slot = slot;
		this.p = p;
		this.name = name;
		this.level = level;
		this.xp = 0;
	}
	
	public Job(MmoPlayer p, int slot, String name, int level, int xp) {
		this.slot = slot;
		this.p = p;
		this.name = name;
		this.level = level;
		this.xp = xp;
	}
	
	//getters
	
	public String getName() {
		return this.name;
	}
	
	public int getLevel() {
		return this.level;
	}
	
	public int getXp() {
		return this.xp;
	}
	
	public boolean hasLevel(int level) {
		if(this.level >= level) return true;
		return false;
	}
	
	public int getSlot() {
		return this.slot;
	}
	
	public ItemStack getIcone() {
		ItemStack item = new ItemStack(Material.BARRIER);
		if (this.level == 1) item = new ItemStack(Material.STICK);
		if(this.name.equalsIgnoreCase("mineur")) {
			if (this.level == 2) item = new ItemStack(Material.WOOD_PICKAXE);else
			if (this.level == 3) item = new ItemStack(Material.STONE_PICKAXE);else
			if (this.level == 4) item = new ItemStack(Material.IRON_PICKAXE);else
			if (this.level == 5) item = new ItemStack(Material.GOLD_PICKAXE);else
			if (this.level >= 6) item = new ItemStack(Material.DIAMOND_PICKAXE);			
		}
		if(this.name.equalsIgnoreCase("bucheron")) {
			if (this.level == 2) item = new ItemStack(Material.WOOD_AXE);else
			if (this.level == 3) item = new ItemStack(Material.STONE_AXE);else
			if (this.level == 4) item = new ItemStack(Material.IRON_AXE);else
			if (this.level == 5) item = new ItemStack(Material.GOLD_AXE);else
			if (this.level >= 6) item = new ItemStack(Material.DIAMOND_AXE);
		} else
		if(this.name.equalsIgnoreCase("paysan")) {
			if (this.level == 2) item = new ItemStack(Material.WOOD_HOE);else
			if (this.level == 3) item = new ItemStack(Material.STONE_HOE);else
			if (this.level == 4) item = new ItemStack(Material.IRON_HOE);else
			if (this.level == 5) item = new ItemStack(Material.GOLD_HOE);else
			if (this.level >= 6) item = new ItemStack(Material.DIAMOND_HOE);
		}
			
		String cl = "§a";
		if (this.level >= 7) {
			int lvl = this.level - 6;
			item.addEnchantment(Enchantment.DIG_SPEED, lvl);
			if(this.level >= 10) cl = "§6";
		}
		ItemMeta meta = item.getItemMeta();
		//Tout ça sert à mettre la premiere lettre en majuscule xD
		String pr = this.name.split("")[0];
		String maj = pr.toUpperCase();
		String[] resteA = this.name.split("");
		String reste = maj;
		for(String lettre : resteA) {
			if(lettre != resteA[0]) {
				reste = reste + lettre;
			}
		}
		//c'est très l'utilité xd
		meta.setDisplayName(cl+reste);
		List<String> lore = new ArrayList<String>();
		lore.add(" ");
		lore.add("§8Niveau: §7"+this.getLevel());
		lore.add("§8§oXp: §7§o"+this.getXp());
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		return item;
	}
	
	//setters
	public void setType(String type) {
		if(type=="paysan") this.name = type;
		if(type=="bucheron") this.name = type;
		if(type=="forgeron") this.name = type;
		if(type=="artisan") this.name = type;
		if(type=="mineur") this.name = type;
		if(type=="error" || type=="null") this.name = "null";
	}
	
	public void delete() {
		this.name = "null";
		this.xp = 0;
		this.level = 0;
	}
	
	public void setSlot(int slot) {
		this.slot = slot;	
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public void setXp(int xp) {
		this.xp = xp;
	}
	
	public void addXp(int xp) {
		
		this.xp += xp;
		/*
		 * On utilise un affichage par armor stand.
		 * pour afficher dans le tchat:
		 * p.sendMessage("§a§l[+"+xp+"]");
		*/
		if(this.level == 1 && this.xp >= 300) {upLevel(2); this.level = 2; this.xp -= 300;}
		if(this.level == 2 && this.xp >= 900) {upLevel(3); this.level = 3; this.xp -= 900;}
		if(this.level == 3 && this.xp >= 1600) {upLevel(4); this.level = 4; this.xp -= 1600;}
		if(this.level == 4 && this.xp >= 3200) {upLevel(5); this.level = 5; this.xp -= 3200;}
		if(this.level == 5 && this.xp >= 4500) {upLevel(6); this.level = 6; this.xp -= 4500;}
		if(this.level == 6 && this.xp >= 10000) {upLevel(7); this.level = 7; this.xp -= 10000;}
		if(this.level == 7 && this.xp >= 30000) {upLevel(8); this.level = 8; this.xp -= 30000;}
		if(this.level == 8 && this.xp >= 60000) {upLevel(9); this.level = 9; this.xp -= 60000;}
		if(this.level == 9 && this.xp >= 100000) {upLevel(10); this.level = 10; this.xp -= 100000;}
		
		this.save();
		
	}
	
	//méthodes
	private void upLevel(int nL) {
		this.p.sendMessage(fr.jamailun.Mmorpg.Main.prefix+"§a Félicitation ! Votre métier de §c"+this.name+" §aest désormais au niveau §3"+nL+"§a.");
	}
	
	public void save() {
		this.p.updateJob(this);
	}
}


/*(lvl 1: 0xp) = cobble
 * lvl 2: 300xp = charbon
 * lvl 3: 900xp = fer
 * lvl 4: 1'600xp = or
 * lvl 5: 3'200xp = lapis
 * lvl 6: 4'500xp = diamant
 * lvl 7: 10'000xp
 * lvl 8: 30'000xp
 * lvl 9: 60'000xp
 * lvl 10: 100'000xp
 */
