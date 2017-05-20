package fr.jamailun.Mmorpg.Event;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class OnPlaceBlock implements Listener {
	public OnPlaceBlock(fr.jamailun.Mmorpg.Main main) {}

	@EventHandler
	public void PlayerPlaceBlock(BlockPlaceEvent e) {
		if(e.getPlayer().getWorld() != fr.jamailun.Mmorpg.Main.monde) return;
		if(e.getPlayer().getGameMode() == GameMode.CREATIVE) return;
		
		e.setCancelled(true); //A changer si debug ou autre.
		
	}
	
}
