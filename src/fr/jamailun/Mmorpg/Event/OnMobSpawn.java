package fr.jamailun.Mmorpg.Event;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.player.PlayerMoveEvent;

import fr.jamailun.Ressources.CustomMob;

public class OnMobSpawn implements Listener {
	public OnMobSpawn(fr.jamailun.Mmorpg.Main main) {}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void OnMobSpawnNaturally(CreatureSpawnEvent e) {
		if(e.getLocation().getWorld() != fr.jamailun.Mmorpg.Main.monde) return;
		Bukkit.broadcastMessage("Un mob apparait !");
		if(e.getSpawnReason() != SpawnReason.NATURAL) return;
		Bukkit.broadcastMessage("(naturellement)");
		
		FileConfiguration config = fr.jamailun.Mmorpg.Main.chunkList;
		Chunk c = e.getLocation().getChunk();
		
		String s = ""+c.getX()+","+c.getZ();
		
		if(!config.contains(s)) {
			e.setCancelled(true);
			return;
		}
		String data = config.getString(s);
		if(data.equalsIgnoreCase("safe")) {
			e.setCancelled(true);
			return;
		}
		
		//à modifier ensuite !! Là ça arrete tout si c'est pas NATURE ou SAFE.
		if(!data.contains("nature_")) {
			fr.jamailun.Mmorpg.ErrorReporter.ErrorOnMobSpawn(s);
			return;
		}
		Bukkit.broadcastMessage("dans la nature !");
		int environement = Integer.parseInt(data.split("_")[1]); //sous la forme "nature_1" par exemple
		List<CustomMob> list = fr.jamailun.Ressources.CustomMob.getMobsForEnvironement(environement);
		int randMob = RandInt(1, list.size());
		CustomMob mob = list.get(randMob - 1);
		
		fr.jamailun.Ressources.CustomMob.generateEntity(mob.name, e.getEntity().getLocation());
	}
	
	@EventHandler
	public void OnPlayerChangeChunk(PlayerMoveEvent e) {
		if(e.getPlayer().getLocation().getWorld() != fr.jamailun.Mmorpg.Main.monde) return;
		if(e.getFrom().getChunk() == e.getTo().getChunk()) return;
		if(!fr.jamailun.Mmorpg.Main.debug) return;
		FileConfiguration config = fr.jamailun.Mmorpg.Main.chunkList;
		Chunk c = e.getTo().getChunk();
		
		String s = ""+c.getX()+","+c.getZ();
		
		if(!config.contains(s)) {
			e.getPlayer().sendMessage("§8§oAucune valeur pour le biome ["+s+"].");
			return;
		}
		
		e.getPlayer().sendMessage("§aBiome ["+s+"] = "+config.getString(s));
	}
	
	
	private static int RandInt(int min, int max) {
		if(min==max) return min;
		return min + (int)(Math.random() * ((max - min) + 1));
	}
}
