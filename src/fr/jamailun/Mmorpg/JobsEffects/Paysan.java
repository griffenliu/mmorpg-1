package fr.jamailun.Mmorpg.JobsEffects;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import fr.jamailun.Mmorpg.Classes.*;

public class Paysan {
	
	private static String prefix = fr.jamailun.Mmorpg.Main.prefix;
	
	@SuppressWarnings("deprecation")
	public static void BreakBloc(MmoPlayer p, Job paysan, Block b, BlockBreakEvent e) {
		
		int sec = fr.jamailun.Ressources.PaysanBlockType.getTimer(b);
		int xp = fr.jamailun.Ressources.PaysanBlockType.getXpGiven(b);
		int levelNeed = fr.jamailun.Ressources.PaysanBlockType.getLevelNeed(b);
		
		final Block oldB = b;
		
		if(!(p.hasJobLevel("paysan", levelNeed))) {
			p.sendMessage(prefix+"§cIl faut être paysan de niveau §4"+levelNeed+"§c !");
			e.setCancelled(true);
			return;
		}
		
		if(!(fr.jamailun.Ressources.Outils.isType(p.getItemInHand(), "faux"))) {
			p.sendMessage(prefix+"§cIl vous faut une §4faux§c en main !");
			e.setCancelled(true);
			return;
		}
		short d = p.getItemInHand().getDurability();
		d--;
		p.getItemInHand().setDurability(d);
		
		Location loc = b.getLocation();
		fr.jamailun.Mmorpg.Event.OnBreak.ReplaceBlock(sec, oldB, loc);
		ItemStack item = new ItemStack(Material.BARRIER);
		
		if(b.getTypeId() == 59) item = fr.jamailun.Ressources.CustomItem.getRessourceLoot("ble");
		if(b.getTypeId() == 83) item = fr.jamailun.Ressources.CustomItem.getRessourceLoot("canne");
		if(b.getTypeId() == 142) item = fr.jamailun.Ressources.CustomItem.getRessourceLoot("patate");
		if(b.getTypeId() == 103) item = fr.jamailun.Ressources.CustomItem.getRessourceLoot("melon");
		if(b.getTypeId() == 141) item = fr.jamailun.Ressources.CustomItem.getRessourceLoot("carotte");
		if(b.getTypeId() == 86) item = fr.jamailun.Ressources.CustomItem.getRessourceLoot("citrouille");
		if(b.getTypeId() == 81) item = fr.jamailun.Ressources.CustomItem.getRessourceLoot("cactus");
		
		
		b.setType(Material.AIR);;
		p.getInventory().addItem(item);
		
		paysan.addXp(xp);
		fr.jamailun.Mmorpg.Event.OnBreak.DisplayXp(loc, xp, p.getRealPlayer());
		return;
	}
	
}
