package fr.jamailun.Mmorpg.Event;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import fr.jamailun.Mmorpg.Classes.*;

public class OnInteractVillager implements Listener {
	public OnInteractVillager(fr.jamailun.Mmorpg.Main main) {}
	private String prefix = fr.jamailun.Mmorpg.Main.prefix;
	
	@EventHandler
	public void clicvillager(PlayerInteractEntityEvent e) {
		if(e.getPlayer().getWorld() != fr.jamailun.Mmorpg.Main.monde) return;
		Entity entity = e.getRightClicked();
		if(!(entity instanceof Villager)) return;
		Villager v = (Villager) entity;
		if(v.getCustomName() == null) return;
		String name = v.getCustomName();
		MmoPlayer p = fr.jamailun.Mmorpg.Main.mpm.getPlayer(e.getPlayer());
		e.setCancelled(true);
		
		if(name.equalsIgnoreCase("§2§lAubergiste")) {
			fr.jamailun.Mmorpg.Guis.GuiNPC.Aubergiste(p);
		}
		
		if(name.contains("§a§lMaitre ")) {
			String metier = name.split(" ")[1];
			for(Job job : p.getJobExtension().getJobs()) {
				if(job.getName() == null || job.getName().equalsIgnoreCase("null")) {
					//apprentissage (genre une question, un dialogue ou autre)
					job = new Job(p, job.getSlot(), metier);
					job.save();
					p.sendMessage(prefix+"§2Tu as appris le métier de §a"+metier+" §2!");
					return;
				}
				if(job.getName().equalsIgnoreCase(metier)) {
					p.sendMessage(prefix+"§4Tu as déjà le métier de §c"+metier+"§4 !");
					return;
				}
			}
			p.sendMessage(prefix+"§4Tu ne peux pas apprendre de métier supplémentaire !");
		}
		
		else if(name.contains("§lRecruteur ")) {
			String fac = name.split(" ")[1];
			if(fac.equalsIgnoreCase("bleu")) {
				//là aussi faut faire un apprentissage xd
				p.sendMessage(prefix+"§lTu rejoins la faction §b§lBLEUE§r§l !");
				p.sendMessage(prefix+"§7Tu es désormais une §1§lrecrue§7.");
				p.setFactionName("blue");
			}else if(fac.equalsIgnoreCase("rouge")) {
				//là aussi faut faire un apprentissage xd
				p.sendMessage(prefix+"§lTu rejoins la faction §c§lBLEUE§r§l !");
				p.sendMessage(prefix+"§7Tu es désormais une §4§lrecrue§7.");
				p.setFactionName("red");
			}
		}
		
	}
	
}
