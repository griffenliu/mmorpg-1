package fr.jamailun.Mmorpg.JobsEffects;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import fr.jamailun.Mmorpg.Classes.*;

public class Mineur {
	
	private static String prefix = fr.jamailun.Mmorpg.Main.prefix;
	
	public static void BreakBloc(MmoPlayer p, Job mineur, Block b, BlockBreakEvent e) {
		
		final Material type = b.getType();
		int sec = fr.jamailun.Ressources.MineurBlockType.getTimer(type);
		int xp = fr.jamailun.Ressources.MineurBlockType.getXpGiven(type);
		int levelNeed = fr.jamailun.Ressources.MineurBlockType.getLevelNeed(type);
		
		final Block oldB = b;
		
		if(!(p.hasJobLevel("mineur", levelNeed))) {
			p.sendMessage(prefix+"§cIl faut être mineur de niveau §4"+levelNeed+"§c !");
			e.setCancelled(true);
			return;
		}
		
		Location loc = b.getLocation();
		fr.jamailun.Mmorpg.Event.OnBreak.ReplaceBlock(sec, oldB, loc);
		ItemStack item = new ItemStack(Material.BARRIER);
		if(b.getType() == Material.COBBLESTONE) item = fr.jamailun.Ressources.CustomItem.getRessourceLoot("cobble");
		if(b.getType() == Material.COAL_ORE) item = fr.jamailun.Ressources.CustomItem.getRessourceLoot("coal");
		if(b.getType() == Material.IRON_ORE) item = fr.jamailun.Ressources.CustomItem.getRessourceLoot("iron");
		if(b.getType() == Material.GOLD_ORE) item = fr.jamailun.Ressources.CustomItem.getRessourceLoot("gold");
		if(b.getType() == Material.LAPIS_ORE) item = fr.jamailun.Ressources.CustomItem.getRessourceLoot("lapis");
		if(b.getType() == Material.DIAMOND_ORE) item = fr.jamailun.Ressources.CustomItem.getRessourceLoot("diamond");
		
		b.breakNaturally(new ItemStack(Material.AIR));
		p.getInventory().addItem(item);
		
		mineur.addXp(xp);
		fr.jamailun.Mmorpg.Event.OnBreak.DisplayXp(loc, xp, p.getRealPlayer());
		return;
	}
	
}
