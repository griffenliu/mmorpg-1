package fr.jamailun.Mmorpg.Event;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

import fr.jamailun.Mmorpg.Classes.MmoPlayer;
import fr.jamailun.Mmorpg.Classes.Quest;

public class OnInteractArmorStand implements Listener {
	
	public OnInteractArmorStand(fr.jamailun.Mmorpg.Main main) {}

	@EventHandler
	public void clicvillager(PlayerInteractAtEntityEvent e) {
		if(e.getPlayer().getWorld() != fr.jamailun.Mmorpg.Main.monde) return;
		Entity entity = e.getRightClicked();
		if(!(entity instanceof ArmorStand)) return;
		ArmorStand a = (ArmorStand) e.getRightClicked();
		MmoPlayer p = fr.jamailun.Mmorpg.Main.mpm.getPlayer(e.getPlayer());
		
		if(a.isVisible()) return; //Nos ArmorStands sont invisibles !
		if(a.getCustomName() == null) return; // et ils ont un customName !
		
		String nameT = a.getCustomName();
		if(!nameT.contains("npc_")) return; //ils commencent par "npc_" (ex: "npc_Jhonn")
		String name = nameT.split("_")[1];
		
		FileConfiguration f = fr.jamailun.Mmorpg.Main.QuestsList;
		//on regarde si une quete concerne le pnj:
		//   f = QuestsFileConfiguration;   q = n°quete;   s = n° step
		for(int q=1; q<=f.getInt("quests.nb"); q++) {
			for(int s=1; s<=f.getInt("quests."+q+".steps.nb"); s++) {
				String testName = f.getString("quests."+q+".steps."+s+".npc");
				if(testName.equalsIgnoreCase(name)) { //c'est le bon pnj.
					Quest quest = new Quest(p, q);
					//Maintenant on regarde si le mec a un truc à lui donner xD
					int avancement = p.getQuestAdvancement(quest);
					if(avancement == s) {//Il doit lui lancer une action !
						p.startDialog(f.getString("quests."+q+".steps."+s+".data"), name);
						return;
					} else if(avancement >= s) {
						p.startMessage(f.getString("npcs."+name+".apres"), name);
					} else if(avancement <= s) {
						p.startMessage(f.getString("npcs."+name+".avant"), name);
					}
				}
			}
		
		}
	}
}
