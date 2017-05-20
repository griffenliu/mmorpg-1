package fr.jamailun.Mmorpg.Event;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class OnDrop implements Listener {
	public OnDrop(fr.jamailun.Mmorpg.Main main) {}
	
@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		Bukkit.getServer().broadcastMessage(e.getItemDrop().getItemStack().getType().toString());
	}
}
