package fr.jamailun.Mmorpg.JobsEffects;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;

import fr.jamailun.Mmorpg.Classes.*;

public class Bucheron {
	
	private static String prefix = fr.jamailun.Mmorpg.Main.prefix;
	
	public static void BreakBloc(MmoPlayer p, Job bucheron, Block b, BlockBreakEvent e) {
		
		int sec = fr.jamailun.Ressources.BucheronBlockType.getTimer(b);
		int xp = fr.jamailun.Ressources.BucheronBlockType.getXpGiven(b);
		int levelNeed = fr.jamailun.Ressources.BucheronBlockType.getLevelNeed(b);
		
		if(!(p.hasJobLevel("bucheron", levelNeed))) {
			p.sendMessage(prefix+"§cIl faut être bûcheron de niveau §4"+levelNeed+"§c !");
			e.setCancelled(true);
			return;
		}
		
		Location loc = b.getLocation();
		fr.jamailun.Mmorpg.Event.OnBreak.ReplaceBlock(sec, b, loc);
		b.breakNaturally();
		bucheron.addXp(xp);
		fr.jamailun.Mmorpg.Event.OnBreak.DisplayXp(loc, xp, p.getRealPlayer());
		return;
	}
	
}
