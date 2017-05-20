package fr.jamailun.Mmorpg.Event;

import java.util.List;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import com.codingforcookies.armorequip.ArmorEquipEvent;

import fr.jamailun.Mmorpg.Classes.MmoPlayer;

public class OnEquip implements Listener {

	private String prefix = fr.jamailun.Mmorpg.Main.prefix;
	
	public OnEquip(fr.jamailun.Mmorpg.Main main) {}

	@EventHandler
	public void OnEquipArmor(ArmorEquipEvent e) {
		if(e.getPlayer().getGameMode() == GameMode.CREATIVE) return;
		if(e.getPlayer().getWorld() != fr.jamailun.Mmorpg.Main.monde) return;
		if(e.getNewArmorPiece()== null) return;
		ItemStack newA = e.getNewArmorPiece();
		if(newA.getItemMeta() == null) return;
		if(newA.getType() == Material.AIR) return;

		MmoPlayer p = fr.jamailun.Mmorpg.Main.mpm.getPlayer(e.getPlayer());
		
		List<String> lore = newA.getItemMeta().getLore();
		int lvl = Integer.parseInt(lore.get(0).split(" ")[2]);
		
		if(p.getXpLevel() < lvl) {
			e.setCancelled(true);
			p.sendMessage(prefix+"§4Vous n'avez pas le niveau suffisant pour équiper l'armure "+newA.getItemMeta().getDisplayName()+"§4 ! Vous avez le niveau §c"+p.getXpLevel()+"§4, et il faut le niveau §c"+lvl+"§4.");
			return;
		}
	}
	
}
