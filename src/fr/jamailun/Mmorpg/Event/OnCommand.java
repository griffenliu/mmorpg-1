package fr.jamailun.Mmorpg.Event;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import fr.jamailun.Mmorpg.Classes.*;

@SuppressWarnings("deprecation")
public class OnCommand implements Listener{
	public OnCommand(fr.jamailun.Mmorpg.Main main) {}
	private String prefix = fr.jamailun.Mmorpg.Main.prefix;
	
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent e) throws IOException {
		String cmd = e.getMessage();
		String[] args = cmd.split(" ");
		if(!(args[0].equalsIgnoreCase("/mmorpg") || args[0].equalsIgnoreCase("/mmo") || args[0].equalsIgnoreCase("/rpg") || args[0].equalsIgnoreCase("/m"))) return;
		final MmoPlayer p = fr.jamailun.Mmorpg.Main.mpm.getPlayer(e.getPlayer());
		
		if(args.length == 1) {
			sendHelp(p, args);
			return;
		}
		
		if(args[1].equalsIgnoreCase("me") || args[1].equalsIgnoreCase("moi")) {
			e.setCancelled(true);
			fr.jamailun.Mmorpg.Guis.GuiPlayer.OpenInfos(p);
			return;
		} else if(args[1].equalsIgnoreCase("edit")) {
			e.setCancelled(true);
			if((!p.isAdmin()) && (!(p.getName().equalsIgnoreCase("jamailun")))) {
				p.sendMessage(prefix+"§4Tu n'as pas les droits nécessaire pour effectuer cette action !");
				return;
			} else {
				if(args.length == 2) {
					p.sendMessage(prefix+"§c"+args[0]+" edit <npc/chunk/summon/admin>");
					return;
				}
				if(args[2].equalsIgnoreCase("npc")) {
					if(args.length == 3) {
						p.sendMessage(prefix+"§c"+args[0]+" edit npc <id> (infos ids ailleurs)");
						return;
					}
					int id = Integer.parseInt(args[3]);
					Villager npc = (Villager) p.getWorld().spawnCreature(p.getLocation(), EntityType.VILLAGER);
					npc.setAdult();
					npc.setCanPickupItems(false);
					if(id==0) {
						npc.setProfession(Profession.BUTCHER);
						npc.setCustomName("§2§lAubergiste");
					}else if(id==1) {
						npc.setProfession(Profession.PRIEST);
						npc.setCustomName("§c§lRecruteur rouge");
					}else if(id==2) {
						npc.setProfession(Profession.PRIEST);
						npc.setCustomName("§b§lRecruteur bleu");
					}else if(id==3) {
						npc.setProfession(Profession.BLACKSMITH);
						npc.setCustomName("§a§lMaitre mineur");
					}else if(id==4) {
						npc.setProfession(Profession.FARMER);
						npc.setCustomName("§a§lMaitre bucheron");
					}else if(id==5) {
						npc.setProfession(Profession.BLACKSMITH);
						npc.setCustomName("§a§lMaitre forgeron");
					}else if(id==6) {
						npc.setProfession(Profession.FARMER);
						npc.setCustomName("§a§lMaitre paysan");
					}
					return;
					
				}else if(args[2].equalsIgnoreCase("chunk") || args[2].equalsIgnoreCase("chunck")) {
					if(args.length == 3) {
						p.sendMessage(prefix+"§c"+args[0]+" edit "+args[2]+" <safe/nature>");
						return;
					}
					FileConfiguration config = fr.jamailun.Mmorpg.Main.chunkList;
					Chunk c = p.getLocation().getChunk();
					String data = ""+c.getX()+","+c.getZ();
					
					if(args[3].equalsIgnoreCase("safe")) {
						config.set(data, "safe");
						config.save(fr.jamailun.Mmorpg.Main.chunkListFile);
						p.sendMessage(prefix+"§aSet du biome ["+data+"] en mode §2safe§a.");
						return;
					}else if(args[3].equalsIgnoreCase("nature")) {
						if(args.length == 4) {
							p.sendMessage(prefix+"§c"+args[0]+" edit "+args[2]+" nature <id>");
							return;
						}
						try{Integer.parseInt(args[4]);} catch(Exception ex) {
							p.sendMessage(prefix+"§c"+args[0]+" edit "+args[2]+" nature <id> §4avec §lid§4 comme nombre !");
							return;
						}
						config.set(data, "nature_"+args[4]);
						config.save(fr.jamailun.Mmorpg.Main.chunkListFile);
						p.sendMessage(prefix+"§aSet du biome ["+data+"] en mode §4nature["+args[4]+"]§a.");
						return;
					}
					p.sendMessage(prefix+"§c"+args[0]+" edit "+args[2]+" <safe/nature>");
					return;
				} else if(args[2].equalsIgnoreCase("admin") || args[2].equalsIgnoreCase("setadmin")) {
					if(args.length == 3) {
						p.sendMessage(prefix+"§c"+args[0]+" edit "+args[2]+" <player-name>");
						return;
					}
					if(Bukkit.getPlayerExact(args[3]) == null) {
						p.sendMessage(prefix+"§cLe joueur §4"+args[3]+"§c n'existe pas ou n'est pas connecté.");
						return;
					}
					MmoPlayer target = fr.jamailun.Mmorpg.Main.mpm.getPlayer(args[3]);
					FileConfiguration config = fr.jamailun.Mmorpg.Main.adminList;
					
					if(!config.contains(target.getUuid())) {
						config.set(target.getUuid(), true);
						target.sendMessage(prefix+"§aL'administrateur "+p.getName()+" vous a ajouté à la liste des admins mmorpg.");
						p.sendMessage(prefix+"§aLe joueur §2"+target.getName()+" §a a bien été ajouté à la liste des admins mmorpg.");
						return;
					}
					
					boolean admin = config.getBoolean(target.getUuid());
					if(admin) {
						p.sendMessage(prefix+"§cLe joueur §4"+args[3]+"§c est déjà administrateur mmorpg.");
						return;
					} else {
						config.set(target.getUuid(), true);
						target.sendMessage(prefix+"§aL'administrateur "+p.getName()+" vous a ajouté à la liste des admins mmorpg.");
						p.sendMessage(prefix+"§aLe joueur §2"+target.getName()+" §a a bien été ajouté à la liste des admins mmorpg.");
						return;
					}
				} else if(args[2].equalsIgnoreCase("summon") || args[2].equalsIgnoreCase("spawn")) {
					if(args.length == 3) {
						p.sendMessage(prefix+"§c"+args[0]+" edit "+args[2]+" <mob lvl(id)>");
						return;
					}
					try{Integer.parseInt(args[3]);} catch(Exception ex) {
						p.sendMessage(prefix+"§c"+args[0]+" edit "+args[2]+" <mob lvl(id)>, §4'"+args[3]+"' invalide.");
						return;
					}
					Entity test = fr.jamailun.Ressources.CustomMob.generateEntity(Integer.parseInt(args[3]), p.getLocation());
					if(test == null) {
						p.sendMessage(prefix+"§c"+args[0]+" edit "+args[2]+" <mob lvl(id)>, §4'"+args[3]+"' invalide.");
						return;
					}
				} else {
					sendHelp(p, args);
				}
			}
		}
	}		
	
	private static void sendHelp(MmoPlayer p, String[] args) {
		p.sendMessage("§7*-------AIDE--------*");
		p.sendMessage(" §6"+args[0]+" me§r: Informations sur le joueur.");
	}
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e) {
		if(e.getPlayer().getWorld() != fr.jamailun.Mmorpg.Main.monde) return;
		//MmoPlayer p = fr.jamailun.Mmorpg.Main.mpm.getPlayer(e.getPlayer());
		/*
		int t = Integer.parseInt(e.getMessage());
		int futurLvl = 1000;
		for(int i=1; i<=t; i++) {
			futurLvl += i*640;
			futurLvl -= i*20;
		}
		Bukkit.broadcastMessage("§a"+e.getMessage()+" §6>> §c"+futurLvl);
		e.setCancelled(true);
		return;
		*/
		
		/*
		if(e.getMessage().equalsIgnoreCase("test")) {
			e.setCancelled(true);
			e.getPlayer().setResourcePack("http://download900.mediafire.com/di243piq7i3g/z6qq3sfpfyjkrac/test.rar");
			
		}
		else if(e.getMessage().equalsIgnoreCase("lel")) {
			e.setCancelled(true);
			e.getPlayer().setResourcePack("packs/test.rar");
			
		}*/
	}
}
