package fr.jamailun.Mmorpg.Event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import fr.jamailun.Mmorpg.Classes.MmoPlayer;

public class OnRespawn implements Listener {
	public OnRespawn(fr.jamailun.Mmorpg.Main main) {}

	@EventHandler
	public void OnPlayerRespawn(PlayerRespawnEvent e) {
		if(e.getPlayer().getWorld() != fr.jamailun.Mmorpg.Main.monde) return;
		
		MmoPlayer p = fr.jamailun.Mmorpg.Main.mpm.getPlayer(e.getPlayer());
		
		e.setRespawnLocation(p.getRespawn());
	}
	
}
