package fr.jamailun.Mmorpg.Guis;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.material.Dye;

import fr.jamailun.Mmorpg.Classes.*;

public class GuiPlayer {

	public static void OpenInfos(MmoPlayer p) {
		Inventory inv = Bukkit.createInventory(null, 9*3, "§6§lInformations personnelles");
		
		ItemStack mur = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte)8);
		ItemMeta murM = mur.getItemMeta();
		murM.setDisplayName(" ");
		mur.setItemMeta(murM);
		for(int i=1; i<=26; i++) inv.setItem(i, mur);
		
		ItemStack tete = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
		SkullMeta teteM = (SkullMeta) tete.getItemMeta();
		teteM.setOwner(p.getPlayer().toString());
		teteM.setDisplayName("§a§l"+p.getName());
		List<String> teteL = new ArrayList<String>();
		teteL.add(" ");
		teteL.add("§8Niveau: §7"+p.getXpLevel());
		teteL.add("§8§oXp: §7§o"+p.getXpPoints());
		teteM.setLore(teteL);
		tete.setItemMeta(teteM);
		
		
		ItemStack money = new ItemStack(Material.EMERALD);
		ItemMeta moneyM = money.getItemMeta();
		moneyM.setDisplayName("§6Argent :");
		List<String> moneyL = new ArrayList<String>();
		moneyL.add("§a= §n"+p.getMoney()+"§a $");
		moneyM.setLore(moneyL);
		money.setItemMeta(moneyM);
		
		inv.setItem(0, tete);
		inv.setItem(9, money);
		
		//METIERS
		int slot = 2;
		for(Job job : p.getJobExtension().getJobs()) {
			if(job.getName().equalsIgnoreCase("null")) {
				ItemStack vide = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte)15);
				ItemMeta videM = vide.getItemMeta();
				videM.setDisplayName("§8[Métier vide]");
				vide.setItemMeta(videM);
				inv.setItem(slot, vide);
			} else {
				inv.setItem(slot, job.getIcone());
			}
			slot += 9;
		}
		
		//GRADE
		Dye dye = new Dye();
		ItemStack grade = new ItemStack(Material.BARRIER);
		ItemMeta gradeM = grade.getItemMeta();
		List<String> lore = new ArrayList<String>();
		lore.add(" ");
		if(p.getFactionName().equalsIgnoreCase("red")) {
			String desc = "";
			if(p.getFactionRank() == 1) {dye.setColor(DyeColor.PURPLE);desc = "Recrue";}
			if(p.getFactionRank() == 2) {dye.setColor(DyeColor.RED);desc = "Soldat";}
			if(p.getFactionRank() == 3) {dye.setColor(DyeColor.LIME);desc = "Garde";}
			if(p.getFactionRank() == 4) {dye.setColor(DyeColor.PINK);desc = "Sentinelle";}
			if(p.getFactionRank() == 5) {dye.setColor(DyeColor.YELLOW);desc = "Contre-maître";}
			if(p.getFactionRank() == 6) {dye.setColor(DyeColor.SILVER);desc = "Lieutenant";}
			if(p.getFactionRank() >= 7) {dye.setColor(DyeColor.MAGENTA);desc = "Commandant";}
			grade = dye.toItemStack();
			gradeM = grade.getItemMeta();
			gradeM.setDisplayName("§4§lFaction rouge");
			lore.add("§c§n"+desc);
		} else if(p.getFactionName().equalsIgnoreCase("blue")) {
			String desc = "";
			if(p.getFactionRank() == 1) {dye.setColor(DyeColor.BROWN);desc = "Recrue";}
			if(p.getFactionRank() == 2) {dye.setColor(DyeColor.BLACK);desc = "Soldat";}
			if(p.getFactionRank() == 3) {dye.setColor(DyeColor.CYAN);desc = "Garde";}
			if(p.getFactionRank() == 4) {dye.setColor(DyeColor.GRAY);desc = "Sentinelle";}
			if(p.getFactionRank() == 5) {dye.setColor(DyeColor.GREEN);desc = "Contre-maître";}
			if(p.getFactionRank() == 6) {dye.setColor(DyeColor.LIGHT_BLUE);desc = "Lieutnant";}
			if(p.getFactionRank() >= 7) {dye.setColor(DyeColor.ORANGE);desc = "Commandant";}
			grade = dye.toItemStack();
			gradeM = grade.getItemMeta();
			gradeM.setDisplayName("§1§lFaction bleue");
			lore.add("§b§n"+desc);
		} else {
			grade = new ItemStack(Material.CAULDRON_ITEM);
			gradeM = grade.getItemMeta();
			gradeM.setDisplayName("§7§lNEUTRE");
			lore.add("§8Vous n'appartenez à aucune faction.");
		}
		
		gradeM.setLore(lore);
		grade.setItemMeta(gradeM);
		grade.setAmount(1);
		
		inv.setItem(18, grade);
		p.openInventory(inv);
	}
	
	
}
