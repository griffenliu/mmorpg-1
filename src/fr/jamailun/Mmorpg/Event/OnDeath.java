package fr.jamailun.Mmorpg.Event;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import net.minecraft.server.v1_8_R3.PacketPlayInClientCommand;
import net.minecraft.server.v1_8_R3.PacketPlayInClientCommand.EnumClientCommand;

public class OnDeath implements Listener {
	public OnDeath(fr.jamailun.Mmorpg.Main main) {}

	@EventHandler
	public void OnPlayerDeath(PlayerDeathEvent e) {
		Player p = e.getEntity();
		if(p.getWorld() != fr.jamailun.Mmorpg.Main.monde) return;
		e.setDeathMessage("");
		
		//forcer le respawn
		PacketPlayInClientCommand packet = new PacketPlayInClientCommand(EnumClientCommand.PERFORM_RESPAWN);
		CraftPlayer craftPlayer = (CraftPlayer)p;
		craftPlayer.getHandle().playerConnection.a(packet);
	}
	
	
}
