package fr.jamailun.Mmorpg.Event;

import java.io.IOException;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.jamailun.Mmorpg.Classes.MmoPlayer;

public class OnLeave implements Listener {

	public OnLeave(fr.jamailun.Mmorpg.Main main) {}

	@EventHandler
	public void OnLeaveServer(PlayerQuitEvent e) throws IOException {
		if(e.getPlayer().getWorld() != fr.jamailun.Mmorpg.Main.monde) return;
		MmoPlayer p = fr.jamailun.Mmorpg.Main.mpm.getPlayer(e.getPlayer());
		fr.jamailun.Mmorpg.Main.mpm.removeMmoPlayer(p);
	}
	
	@EventHandler
	public void OnJoinWorldMmorpg(PlayerChangedWorldEvent e) throws IOException {
		if(e.getFrom() != fr.jamailun.Mmorpg.Main.monde) return;
		MmoPlayer p = fr.jamailun.Mmorpg.Main.mpm.getPlayer(e.getPlayer());
		fr.jamailun.Mmorpg.Main.mpm.removeMmoPlayer(p);
	}
}
