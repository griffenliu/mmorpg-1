package fr.jamailun.Mmorpg.Event;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.jamailun.Mmorpg.Classes.MmoPlayer;
import fr.jamailun.Ressources.CustomItem;

public class OnEat implements Listener {
	public OnEat(fr.jamailun.Mmorpg.Main main) {}
	private static String prefix = fr.jamailun.Mmorpg.Main.prefix;
	
	@EventHandler
	public void OnEating(PlayerItemConsumeEvent e) {
		if(e.getPlayer().getWorld() != fr.jamailun.Mmorpg.Main.monde) return;
		if(e.getItem() == null) return;
		if(e.getItem().getItemMeta() == null) return;
		MmoPlayer p = fr.jamailun.Mmorpg.Main.mpm.getPlayer(e.getPlayer());
		ItemStack item = e.getItem();
		if(item.getType() == Material.BREAD) {
			if(fr.jamailun.Ressources.CustomItem.isSameItem(item, CustomItem.PAIN_BASIQUE)) {
				PotionEffect potion = new PotionEffect(PotionEffectType.HEALTH_BOOST, 10, 0);
				p.addPotionEffect(potion);
				p.sendMessage(prefix+"§a[Regénération §lI§a / §l10§a sec]");
			}
			
		}
		
	}
	
}
