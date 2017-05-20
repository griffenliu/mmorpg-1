package fr.jamailun.Mmorpg;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;

import fr.jamailun.Mmorpg.Classes.MmoPlayer;
import fr.jamailun.Mmorpg.Classes.Npc;

public class Main extends JavaPlugin {
	
	/** @Configuration **/
	public static String prefix="§6§l[MMORPG] §r";
	public static boolean debug = true;
	private static String nomMonde = "mmorpg";
	private static String version ="0.1.1a";
	/**  @fin  **/
	
	public static JavaPlugin jp;
	public static World monde;
	public static ConsoleCommandSender console;
	public static Calendar date;
	
	public static FileConfiguration QuestsList;
	private static File Qf;
	public static File playerListFile;
	public static FileConfiguration playerList;
	public static File adminListFile;
	public static FileConfiguration adminList;
	public static File chunkListFile;
	public static FileConfiguration chunkList;
	
	public static MmoPlayersManager mpm;
	public static ProtocolManager protocolManager;
	
	public void onEnable() {
		jp = this;
		mpm = new MmoPlayersManager(this);
		console = Bukkit.getConsoleSender();
		date = Calendar.getInstance();
		if(!Bukkit.getWorlds().contains(nomMonde)){Bukkit.createWorld(new WorldCreator("mmorpg").seed(000000001));}
		monde = Bukkit.getWorld(nomMonde);
		
		protocolManager = ProtocolLibrary.getProtocolManager();
		
		Bukkit.getServer().getPluginManager().registerEvents(new fr.jamailun.Mmorpg.Event.OnBreak(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new fr.jamailun.Mmorpg.Event.OnJoin(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new fr.jamailun.Mmorpg.Event.OnPlayerInteract(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new fr.jamailun.Mmorpg.Event.OnInventoryClic(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new fr.jamailun.Mmorpg.Event.OnDrop(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new fr.jamailun.Mmorpg.Event.OnCommand(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new fr.jamailun.Mmorpg.Event.OnDeath(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new fr.jamailun.Mmorpg.Event.OnInteractVillager(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new fr.jamailun.Mmorpg.Event.OnRespawn(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new fr.jamailun.Mmorpg.Event.OnDamage(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new fr.jamailun.Mmorpg.Event.OnEquip(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new fr.jamailun.Mmorpg.Event.OnPlaceBlock(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new fr.jamailun.Mmorpg.Event.OnInteractArmorStand(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new fr.jamailun.Mmorpg.Event.OnEat(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new fr.jamailun.Mmorpg.Event.OnLeave(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new fr.jamailun.Mmorpg.Event.OnMobSpawn(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new fr.jamailun.Mmorpg.Event.OnPlayerKillMob(this), this);
		
		playerListFile = new File("plugins/Mmorpg/Server/PlayerList.yml");
		if(!playerListFile.exists())
			try {playerListFile.createNewFile();} catch (IOException e) {e.printStackTrace();}
		playerList = YamlConfiguration.loadConfiguration(playerListFile);
		try {playerList.save(playerListFile);} catch (IOException e) 
			{console.sendMessage("§cImpossible de creer le fichier §4PlayerList.yml§c.");}
		
		adminListFile = new File("plugins/Mmorpg/Server/AdminList.yml");
		if(!adminListFile.exists())
			try {adminListFile.createNewFile();} catch (IOException e) {e.printStackTrace();}
		adminList = YamlConfiguration.loadConfiguration(adminListFile);
		try {adminList.save(adminListFile);} catch (IOException e)
			{console.sendMessage("§cImpossible de creer le fichier §4AdminList.yml§c.");}
		
		chunkListFile = new File("plugins/Mmorpg/Server/ChunkList.yml");
		if(!chunkListFile.exists())
			try {chunkListFile.createNewFile();} catch (IOException e) {e.printStackTrace();}
		chunkList = YamlConfiguration.loadConfiguration(chunkListFile);
		try {chunkList.save(chunkListFile);} catch (IOException e)
			{console.sendMessage("§cImpossible de creer le fichier §4ChunkList.yml§c.");}
		
		//Mettre à jour le fichier de data avec les NPCs/quêtes.
		Qf = new File("plugins/Mmorpg/Ressource/Liste-quetes.yml");
		if(Qf.exists())
			try {Qf.delete();} 
			catch (Exception e) 
				{Bukkit.getLogger().severe("Fichier des QUETES insupprimable.");}
		InputStream in = getResource("quetes.yml");
		try {
			Qf.createNewFile();
		} catch (IOException e) {e.printStackTrace();}
		copy(in, Qf);
		QuestsList = YamlConfiguration.loadConfiguration(Qf);
		
		//Mettre à jour les positions des npcs de quêtes.
		for(Entity en : Bukkit.getWorld("mmorpg").getEntities()) {
			if(en instanceof ArmorStand) {
				ArmorStand a = (ArmorStand) en;
				if(a.getCustomName() != null) {
					if(a.getCustomName().contains("npc_")) {
						Location dt = a.getLocation();
						dt.setY(-100);
						a.teleport(dt);
						Bukkit.getLogger().info("Le receptacle "+a.getCustomName()+" a ete supprime.");
					}
				}
			}
		}
		for(String name : QuestsList.getStringList("npcs.list")) {
			Npc npc = new Npc(name);
			ArmorStand a = (ArmorStand) Bukkit.getWorld("mmorpg").spawnEntity(npc.getLocation(), EntityType.ARMOR_STAND);
			a.setCustomName("npc_"+name);
			a.setVisible(false);
			a.setGravity(false);
			Bukkit.getLogger().info("Le receptacle "+a.getCustomName()+" a ete reinitialise.");
		}
		
		/*  //LISTENER NMS EVENTS
		protocolManager.addPacketListener(	
			new PacketAdapter(this, ListenerPriority.NORMAL, PacketType.Play.Server.NAMED_SOUND_EFFECT) {
				@SuppressWarnings("deprecation")
				@Override
				public void onPacketSending(PacketEvent e) {
					// Item packets (id: 0x29)
						e.getPlayer().sendMessage(":"+e.getPacketID());
				}
			}
		);
		*/
		console.sendMessage("§a--------------------------");
		console.sendMessage("        §emmorpg.java       ");
		console.sendMessage(" ");
		console.sendMessage("§aVersion: §e"+version);
		console.sendMessage("§aAuthor: §ejamailun");
		console.sendMessage("§a--------------------------");
		
		/*console.sendMessage("§c"+date.getTime()+" §r/ §a"+date.getTimeInMillis());
		int annee = date.getTime().getYear();
		annee += 1900;
		console.sendMessage("Annee ="+annee);
		int mois = date.getTime().getMonth();
		mois += 1;
		console.sendMessage("Mois ="+mois);
		console.sendMessage("Jour ="+date.getTime().getDay());
		*/
		
		for(Player pT : Bukkit.getOnlinePlayers()) {
			MmoPlayer p = new MmoPlayer(pT, true);
			if(!mpm.isAlreadyHere(p)) {
				try {
					mpm.addMmoPlayer(p);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		new BukkitRunnable() {
			@Override
			public void run() {
				for(MmoPlayer p : mpm.getPlayersList()) {
					p.setFullVie(p.getFullVie());
				}
			}
		}.runTaskTimer(this, 0, 20);
		
	}

	
	public void onDisable() {
		if(!(mpm.getPlayersList() == null)) {
			console.sendMessage(prefix+"§bSauvegarde des donnees des joueurs en cours.");
			for(MmoPlayer p : mpm.getPlayersList())
				p.saveAll();
			console.sendMessage(prefix+"§bTermine !");
		}
		try {playerList.save(playerListFile);} catch (IOException e) {
			console.sendMessage("§c[ERREUR] Le fichier des joueurs n'a pu etre sauvegarde !");
		}
	}
	
	public static FileConfiguration createRessource(File file, String ressource) {
		copy(jp.getResource(ressource), file);
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		return config;
	}
	
	public static void copy(InputStream in, File file) {
		try {
			OutputStream out = new FileOutputStream(file);
			byte[] buf = new byte[1024];
			int len;
			while((len=in.read(buf))>0){
				out.write(buf,0,len);
			}
			out.close();
			in.close();
		} catch (Exception e) {
			ConsoleCommandSender console = Bukkit.getConsoleSender();
			console.sendMessage("§c-------------------------------------------");
			console.sendMessage("§c--------ERREUR DE FICHIER MMORPG.JAVA------");
			console.sendMessage("§c-------------------------------------------");
			console.sendMessage("§cATTENTION le fichier n'a pas pu etre cree !");
			console.sendMessage("§cFichier : "+file);
			console.sendMessage("§cRessource: "+in);
			if(Qf.getPath().isEmpty()) {
				console.sendMessage("§cCause propable [1]: L'erreur pourrait venir d'une inexistance du fichier.");
				console.sendMessage("§cMerci d'essayer de le creer manuellement : '"+Qf.getAbsolutePath()+"'");
			}
			if(in == null) {
				console.sendMessage("§cCause propable [2]: La ressource n'existe pas : §4Erreur interne de compilation.");
			}
			console.sendMessage("§c-------------------------------------------");
			console.sendMessage("§c-error: fr.jamailun.Mmorpg.Main.copy()-");
			console.sendMessage("§c-------------------------------------------");
		}
	}
}
