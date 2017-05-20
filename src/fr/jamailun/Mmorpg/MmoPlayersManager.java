package fr.jamailun.Mmorpg;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import fr.jamailun.Mmorpg.Classes.MmoPlayer;

public class MmoPlayersManager {
	
	private List<MmoPlayer> lP;
	private JavaPlugin plugin;
	 
	MmoPlayersManager(JavaPlugin plugin) {
		this.plugin = plugin;
		this.lP = new ArrayList<MmoPlayer>();
		for(Player p : Bukkit.getOnlinePlayers()) {
			this.lP.add(new MmoPlayer(p, true));
		}
	}
	
	public JavaPlugin getPlugin() {
		return this.plugin;
	}
	
	public List<MmoPlayer> getPlayersList() {
		return this.lP;
	}
	
	public MmoPlayer getPlayer(Player p) {
		for(MmoPlayer mp : this.lP) {
			if(mp.getRealPlayer() == p)
				return mp;
		}
		return null;
	}
	
	public MmoPlayer getPlayer(String playerName) throws IOException {
		for(MmoPlayer mp : this.lP) {
			if(mp.getName().equalsIgnoreCase(playerName))
				return mp;
		}
		return null;
	}
	
	public boolean isAlreadyHere(MmoPlayer p) {
		for(MmoPlayer pT : this.lP)
			if(pT == p)
				return true;
		return false;
	}
	
	public void addMmoPlayer(MmoPlayer p) throws IOException {
		this.lP.add(p);
	}
	
	public void removeMmoPlayer(MmoPlayer p) throws IOException {
		if(p == null) {
			Main.console.sendMessage("§cLe joueur n'existe pas dans le MmoManager !.");
			return;
		}
		if(!lP.contains(p)) {
			Main.console.sendMessage("§cLe joueur §4"+p.getName()+"§c n'existe pas dans le MmoManager !.");
			return;
		}
		this.lP.remove(p);
	}
}
