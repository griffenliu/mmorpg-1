package fr.jamailun.Mmorpg.Event;

import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import fr.jamailun.Mmorpg.Classes.*;

public class OnPlayerInteract implements Listener {
	public OnPlayerInteract(fr.jamailun.Mmorpg.Main main) {}

	private String prefix = fr.jamailun.Mmorpg.Main.prefix;
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void OnInteract(PlayerInteractEvent e) {
		if(e.getPlayer().getWorld() != fr.jamailun.Mmorpg.Main.monde) return;
		MmoPlayer p = fr.jamailun.Mmorpg.Main.mpm.getPlayer(e.getPlayer());
		
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			//MINEUR
			if(e.getClickedBlock().getType() == Material.FURNACE) {
				e.setCancelled(true);
				if(p.hasJob("mineur")) {
					fr.jamailun.Mmorpg.Guis.JobGui.OpenMineur(p);
				} else {
					p.sendMessage(prefix+"§cTu n'as pas le métier §4mineur §c!");
					return;
				}
			} else
			//FORGERON
			if(e.getClickedBlock().getType() == Material.ANVIL) {
				e.setCancelled(true);
				if(p.hasJob("forgeron")) {
					fr.jamailun.Mmorpg.Guis.JobGui.OpenForgeron(p);
				} else {
					p.sendMessage(prefix+"§cTu n'as pas le métier §4forgeron §c!");
					return;
				}
			} else
			if(e.getClickedBlock().getType() == Material.ENDER_PORTAL_FRAME) {
				e.setCancelled(true);
				if(p.hasJob("paysan")) {
					fr.jamailun.Mmorpg.Guis.JobGui.OpenPaysan(p);
				} else {
					p.sendMessage(prefix+"§cTu n'as pas le métier §4paysan §c!");
					return;
				}
			} else
			if(e.getClickedBlock().getType() == Material.ARMOR_STAND) {
				
			}
			
			
		} else if(e.getAction() == Action.PHYSICAL) { //empecher destruction du blé par saut
			if(e.getClickedBlock() == null) return;
			int b = e.getClickedBlock().getTypeId();
			if(b == 59 || b == 60) {
				e.setUseInteractedBlock(Event.Result.DENY);
				e.setCancelled(true);
			}
		}
		
		
		
	}
	
	
}
