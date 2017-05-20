package fr.jamailun.Mmorpg.Event;

import org.bukkit.Sound;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import fr.jamailun.Mmorpg.Classes.MmoPlayer;
import fr.jamailun.Ressources.CustomItem;
import fr.jamailun.Ressources.CustomMob;

public class OnPlayerKillMob implements Listener {
	public OnPlayerKillMob(fr.jamailun.Mmorpg.Main main) {}
	private static String prefix = fr.jamailun.Mmorpg.Main.prefix;
	
	@EventHandler
	public void OnMobKill(EntityDeathEvent e) {
		if(e.getEntity().getWorld() != fr.jamailun.Mmorpg.Main.monde) return;
		if(!(e.getEntity() instanceof Monster)) return;
		if(e.getEntity().getCustomName() == null) return;
		if(!(e.getEntity().getKiller() instanceof Player)) return;
		
		Monster mob = (Monster) e.getEntity();
        Player player = (Player) mob.getKiller();
        if (player == null) return;
		
        MmoPlayer p = fr.jamailun.Mmorpg.Main.mpm.getPlayer(player);
        
        if(fr.jamailun.Ressources.CustomMob.isCustomMob(mob)) {
        	CustomMob m = fr.jamailun.Ressources.CustomMob.getByName(mob.getCustomName());
        	//xp:
        	int xpWin = m.lootXp;
        	p.addXpPoints(xpWin);
        	p.sendMessage(prefix+"§a§l[+§b§l"+xpWin+"§a§lxp]");
        	//loots:
        	e.setDroppedExp(0);
        	e.getDrops().clear();
        	if(m.loot1 != null) if(RandWin(m.drop1)) giveLoot(p, m.loot1);
        	if(m.loot2 != null) if(RandWin(m.drop2)) giveLoot(p, m.loot2);
        	if(m.loot3 != null) if(RandWin(m.drop3)) giveLoot(p, m.loot3);
        	if(m.loot4 != null) if(RandWin(m.drop4)) giveLoot(p, m.loot4);
        	if(m.loot5 != null) if(RandWin(m.drop5)) giveLoot(p, m.loot5);
        }
        
        //tester si c'est un customBoss
        
	}
	
	private void giveLoot(MmoPlayer p, CustomItem loot) {
		giveLoot(p, loot, 1);
	}
	private void giveLoot(MmoPlayer p, CustomItem loot, int qtt) {
		ItemStack item = fr.jamailun.Ressources.CustomItem.getItem(loot.name);
		p.getInventory().addItem(item);
		p.playSound(p.getLocation(), Sound.ITEM_PICKUP, 1, 1);
	}

	private static boolean RandWin(double drop1) {
		double proba = RandInt(1, 101);
		double max = drop1 + 1;
		if(proba <= max)
			return true;
		return false;
	}
	
	private static int RandInt(int min, int max) {
		return min + (int)(Math.random() * ((max - min) + 1));
	}
}
