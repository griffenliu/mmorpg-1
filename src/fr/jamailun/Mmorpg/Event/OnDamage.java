package fr.jamailun.Mmorpg.Event;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import fr.jamailun.Mmorpg.Classes.MmoPlayer;

public class OnDamage implements Listener {
	public OnDamage(fr.jamailun.Mmorpg.Main main) {}
	private String prefix = fr.jamailun.Mmorpg.Main.prefix;
	
	@EventHandler
	public void OnDamageDone(EntityDamageByEntityEvent e) {
		if(e.getEntity().getWorld() != fr.jamailun.Mmorpg.Main.monde) return;
		if(e.getDamager() instanceof Player) {
			MmoPlayer att = fr.jamailun.Mmorpg.Main.mpm.getPlayer((Player) e.getDamager());
			if(att.getGameMode() == GameMode.CREATIVE)return;
			if(e.getEntity() instanceof Villager) {e.setCancelled(true);return;}
			if(e.getEntity() instanceof Player) {
				MmoPlayer def = new MmoPlayer((Player)e.getEntity(), true);
				
				if(att.getFactionName() != null && def.getFactionName() != null&&att.getFactionName().equalsIgnoreCase(def.getFactionName())) {
					if(att.getFactionName().equalsIgnoreCase("neutre") || def.getFactionName().equalsIgnoreCase("neutre")) return;
					att.sendMessage(prefix+"§cCe joueur est dans la même faction que vous !");
					e.setCancelled(true);
					return;
				}
				
			}
			
		}
		
		
	}
	
	
}
