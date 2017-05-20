package fr.jamailun.Mmorpg.Classes;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Achievement;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.EntityEffect;
import org.bukkit.GameMode;
import org.bukkit.Instrument;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Note;
import org.bukkit.Server;
import org.bukkit.Sound;
import org.bukkit.Statistic;
import org.bukkit.WeatherType;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationAbandonedEvent;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.InventoryView.Property;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.map.MapView;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.util.Vector;

public class MmoPlayer implements Player {

	private Player p;
	private String name;
	private String uuid;
	private double fullVie;
	private int money;
	private int xpLevel;
	private int xpPoints;
	private JobExtension jE;
	private double spawnX;
	private double spawnY;
	private double spawnZ;
	private String facName;
	private int facGrade;
	
	private File file;
	private FileConfiguration config;
	
	
	/**@category Permet de créer la classe MMoPlayer. Peut aussi modifier l'environement serveur !
	 * 
	 * @param p = Player originel.
	 * @param exist = si FALSE, crée les données. Sinon, les récupère juste.
	 */
	
	public MmoPlayer(Player p, boolean exist) {
		this.p = p;
		this.name = p.getName();
		this.uuid = p.getUniqueId().toString();
		this.fullVie = 20.0;
		this.file = new File("plugins/Mmorpg/Players-TEST/"+this.getName()+".yml");
		if(!this.file.exists()) {
			fr.jamailun.Mmorpg.Main.jp.getLogger().severe("Fichier impossible à créer.");
			fr.jamailun.Mmorpg.Main.jp.getLogger().severe("fr.jamailun.MMorpg.Classes.MmoPlayer.java [75]");
			this.money = 0;
			this.xpLevel = 1;
			this.xpPoints = 0;
			this.spawnX = 4.5;
			this.spawnY = 4;
			this.spawnZ = 13.5;
			this.facName = "neutre";
			this.facGrade = 0;
			this.jE = new JobExtension(this, false);
			this.firstSave();
		}
		this.config = YamlConfiguration.loadConfiguration(file);
		
		if(exist) {
			if(!this.config.contains("money")) {fr.jamailun.Mmorpg.Main.jp.getLogger().severe("Erreur lors de la création précédente du fichier: le PATH 'money' n'existe pas.");return;}
			this.money = this.config.getInt("money");
			this.xpLevel = this.config.getInt("xp.lvl");
			this.xpPoints = this.config.getInt("xp.pts");
			this.spawnX = this.config.getDouble("spawn.x");
			this.spawnY = this.config.getDouble("spawn.y");
			this.spawnZ = this.config.getDouble("spawn.z");
			this.facName = this.config.getString("faction.name");
			this.facGrade = this.config.getInt("faction.rank");
			this.jE = new JobExtension(this, true);
		} else {
			this.money = 0;
			this.xpLevel = 1;
			this.xpPoints = 0;
			this.spawnX = 4.5;
			this.spawnY = 4;
			this.spawnZ = 13.5;
			this.facName = "neutre";
			this.facGrade = 0;
			this.jE = new JobExtension(this, false);
			this.firstSave();
		}

		for(String qName : fr.jamailun.Mmorpg.Main.QuestsList.getStringList("quests.list"))
			if(!this.config.contains("quests."+qName))
				this.config.set("quests."+qName, 0);
		
	}
	
	//geters
	public boolean isAdmin() {
		FileConfiguration adL = fr.jamailun.Mmorpg.Main.adminList;
		if(!adL.contains(this.uuid))
			return false;
		if(adL.getBoolean(this.uuid))
			return true;
		return false;
	}
	
	public double getFullVie() {
		return this.fullVie;
	}
	
	public String getFactionName() {
		return this.facName;
	}
	
	public int getFactionRank() {
		return this.facGrade;
	}
	
	public Location getRespawn() {
		return new Location(Bukkit.getWorld("mmorpg"), this.spawnX, this.spawnY, this.spawnZ);
	}
	
	public String getUuid() {
		return this.uuid;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getMoney() {
		return this.money;
	}
	
	public boolean hasMoney(int money) {
		if(this.money >= money) return true;
		return false;
	}
	
	public int getXpLevel() {
		return this.xpLevel;
	}
	
	public int getXpPoints() {
		return this.xpPoints;
	}
	
	//setters et méthodes
	public void addXpPoints(int xp) {
		this.xpPoints += xp;
		int futurLvl = 1000;
		for(int i=1; i<=this.xpLevel; i++) {
			futurLvl += i*640;
			futurLvl -= i*20;
		}
		if(this.xpPoints >= futurLvl) {
			this.xpPoints -= futurLvl;
			this.xpLevel +=1;
			p.sendMessage(fr.jamailun.Mmorpg.Main.prefix+"§5Vous êtes passé au niveau §d§l"+this.xpLevel+"§5 !");
		}
		this.saveXp();
		//verif de s'il il n(y a pas plusiers lvl de win d'un coup xd
		futurLvl = 1000;
		for(int i=1; i<=this.xpLevel; i++) {
			futurLvl += i*640;
			futurLvl -= i*20;
		}
		if(this.xpPoints >= futurLvl)
			this.addXpPoints(0);
	}
	
	public void setFullVie(double fullVie) {
		this.fullVie = fullVie;
		this.setMaxHealth(this.fullVie);
	}
	
	public void setXpLevel(int lvl) {
		this.xpLevel = lvl;
		this.saveAll();
	}
	
	public void setFactionName(String facName) {
		this.facName = facName;
		this.facGrade = 1;
		this.saveAll();
	}
	
	public void setFactionRank(int facRank) {
		this.facGrade = facRank;
		this.saveAll();
	}
	
	public void setRespawn(Location loc) {
		this.spawnX = loc.getX();
		this.spawnY = loc.getY();
		this.spawnZ = loc.getZ();
		this.saveAll();
	}
	
	public void setMoney(int money) {
		this.money = money;
		this.saveAll();
	}
	
	public void addMoney(int money) {
		this.money += money;
		if (this.money >= 1000000) this.money = 999999;
		if (this.money < 0) this.money = 0;
		this.saveAll();
	}

	public void removeMoney(int money) {
		this.money -= money;
		if (this.money >= 1000000) this.money = 999999;
		if (this.money < 0) this.money = 0;
		this.saveAll();
	}

	public Player getRealPlayer() {
		return this.p;
	}
	
	public JobExtension getJobExtension() {
		return this.jE;
	}
	
	public boolean hasJob(String jobName) {
		for(Job job : this.jE.getJobs())
			if(job.getName().contains(jobName))
				return true;
		return false;
	}
	
	public boolean hasJobLevel(String jobName, int level) {
		for(Job job : this.jE.getJobs()) {
			int levelT = job.getLevel();
			if(jobName.contains(job.getName())) {
				if(levelT >= level) {
					return true;
				} else return false;
			}
		}
		return false;
	}
	
	public boolean addJob(Job job) {	
		for (Job jobT : this.jE.getJobs()) {
			if(jobT.getName().contains("null")) {
				this.jE.setJob(jobT.getSlot(), job);
				return true;
				}
		}
		return false;
	}
	
	public Job getJob(String jobName) {
		
		if(this.jE == null) p.sendMessage("jE null");
		if(this.jE.getJobs() == null) p.sendMessage("jE.getJobs null");
		
		for(Job job : this.jE.getJobs())
			if(!(job == null || job.getName() == null))
				if(jobName.contains(job.getName()))
					return job;
		Job error = new Job(this);
		error.setType("error");
		return error;
	}
	
	public Job getJob(int slot) {
		String name = this.config.getString("jobs.slot-"+slot+".name");
		int level = this.config.getInt("jobs.slot-"+slot+".level");
		int xp = this.config.getInt("jobs.slot-"+slot+".xp");
		
		Job job = new Job(this, slot, name, level, xp);
		return job;
	}
	
	public int getQuestAdvancement(Quest quest) {
		if(!this.config.contains("quetes."+quest.getConfigNumber())) return 0;
		int n = this.config.getInt("quetes."+quest.getConfigNumber());
		return n;
	}
	
	public void completeQuest(Quest quest) {
		this.config.set("quetes."+quest.getConfigNumber(), 999);
		try {this.config.save(file);} catch (IOException e) {e.printStackTrace();}
	}
	
	public void advanceQuest(QuestStep step) {
		this.config.set("quetes."+step.getQuest().getConfigNumber(), step.getStepNumber());
		try {this.config.save(file);} catch (IOException e) {e.printStackTrace();}
	}
	
	public void saveAll() {
		this.config.set("money", this.money);
		this.config.set("xp.lvl", this.xpLevel);
		this.config.set("xp.pts", this.xpPoints);
		this.config.set("spawn.x", this.spawnX);
		this.config.set("spawn.y", this.spawnY);
		this.config.set("spawn.z", this.spawnZ);
		this.config.set("faction.name", this.facName);
		this.config.set("faction.rank", this.facGrade);
		try {this.config.save(file);} catch (IOException e) {e.printStackTrace();}
	}
	
	public void saveXp() {
		this.config.set("xp.lvl", this.xpLevel);
		this.config.set("xp.pts", this.xpPoints);
		try {this.config.save(file);} catch (IOException e) {e.printStackTrace();}
	}
	
	public void firstSave() {
		try {this.file.createNewFile();} catch (IOException e) {
			Bukkit.getLogger().info("Fichier de donnee pour le joueur "+this.getName()+"cree avec succes.");
		}
		this.config = YamlConfiguration.loadConfiguration(file);
		this.config.set("money", this.money);
		this.config.set("xp.lvl", this.xpLevel);
		this.config.set("xp.pts", this.xpPoints);
		this.config.set("spawn.x", this.spawnX);
		this.config.set("spawn.y", this.spawnY);
		this.config.set("spawn.z", this.spawnZ);
		this.config.set("faction.name", this.facName);
		this.config.set("faction.rank", this.facGrade);
		try {this.config.save(file);} catch (IOException e) {}
		fr.jamailun.Mmorpg.Main.jp.getLogger().info("Fichier de donnée '"+file.getName()+".yml' créé avec succès.");
	}
	
	public void updateJob(Job job) {
		int slot = job.getSlot();
		this.config.set("jobs.slot-"+slot+".name", job.getName());
		this.config.set("jobs.slot-"+slot+".level", job.getLevel());
		this.config.set("jobs.slot-"+slot+".xp", job.getXp());
		this.jE.setJob(job.getSlot(), job);
		
		try {this.config.save(file);} catch (IOException e) {e.printStackTrace();};
	}
	
	public void startDialog(String dialog, final String npcName) {
		FileConfiguration f = fr.jamailun.Mmorpg.Main.QuestsList;
		final Player p = this.getRealPlayer();
		final String c = "§" + f.getString("npcs."+npcName+".color");
		for(final String msg : f.getStringList("dialogs."+dialog)) {
			new BukkitRunnable() {
				@Override
				public void run() {
					p.sendMessage(c+"["+npcName+"] "+msg);
				}
			}.runTaskTimer(fr.jamailun.Mmorpg.Main.jp, 0, 20);
		}
	}

	public void startMessage(String msg, String npcName) {
		String c = "§" + fr.jamailun.Mmorpg.Main.QuestsList.getString("npcs."+npcName+".color");
		this.sendMessage(c+"["+npcName+"] "+msg);
	}
	
	//--------------------------------------------------------------------//
	//--------------------------------------------------------------------//
	//--------------------------------------------------------------------//
	//--------------------------------------------------------------------//
	//--------------------------------------------------------------------//
	//--------------------------------------------------------------------//
	//----------------METHODES INERANTES AU PLAYER------------------------//
	//--------------------------------------------------------------------//
	//--------------------------------------------------------------------//
	//--------------------------------------------------------------------//
	//--------------------------------------------------------------------//
	/*
	 *               Méthodes modifiées:
	 *  getHealth() : (return this.health;) au lieu de (return p.getHealth();)
	 *  setHealth(double) : + (this.health = arg0;)
	 *  
	 */
	
	@Override
	public void closeInventory() {
		p.closeInventory();
	}

	@Override
	public Inventory getEnderChest() {
		return p.getEnderChest();
	}

	@Override
	public int getExpToLevel() {
		return p.getExpToLevel();
	}

	@Override
	public GameMode getGameMode() {
		return p.getGameMode();
	}

	@Override
	public PlayerInventory getInventory() {
		return p.getInventory();
	}

	@Override
	public ItemStack getItemInHand() {
		return p.getItemInHand();
	}

	@Override
	public ItemStack getItemOnCursor() {
		return p.getItemOnCursor();
	}

	@Override
	public InventoryView getOpenInventory() {
		return p.getOpenInventory();
	}

	@Override
	public int getSleepTicks() {
		return p.getSleepTicks();
	}

	@Override
	public boolean isBlocking() {
		return p.isBlocking();
	}

	@Override
	public boolean isSleeping() {
		return p.isSleeping();
	}

	@Override
	public InventoryView openEnchanting(Location arg0, boolean arg1) {
		return p.openEnchanting(arg0, arg1);
	}

	@Override
	public InventoryView openInventory(Inventory arg0) {
		return this.p.openInventory(arg0);
	}

	@Override
	public void openInventory(InventoryView arg0) {
		p.openInventory(arg0);
	}

	@Override
	public InventoryView openWorkbench(Location arg0, boolean arg1) {
		return p.openWorkbench(arg0, arg1);
	}

	@Override
	public void setGameMode(GameMode arg0) {
		p.setGameMode(arg0);
	}

	@Override
	public void setItemInHand(ItemStack arg0) {
		p.setItemInHand(arg0);
	}

	@Override
	public void setItemOnCursor(ItemStack arg0) {
		p.setItemOnCursor(arg0);
	}

	@Override
	public boolean setWindowProperty(Property arg0, int arg1) {
		return p.setWindowProperty(arg0, arg1);
	}

	@Override
	public boolean addPotionEffect(PotionEffect arg0) {
		return p.addPotionEffect(arg0);
	}

	@Override
	public boolean addPotionEffect(PotionEffect arg0, boolean arg1) {
		return p.addPotionEffect(arg0, arg1);
	}

	@Override
	public boolean addPotionEffects(Collection<PotionEffect> arg0) {
		return p.addPotionEffects(arg0);
	}

	@Override
	public Collection<PotionEffect> getActivePotionEffects() {
		return p.getActivePotionEffects();
	}

	@Override
	public boolean getCanPickupItems() {
		return p.getCanPickupItems();
	}

	@Override
	public EntityEquipment getEquipment() {
		return p.getEquipment();
	}

	@Override
	public double getEyeHeight() {
		return p.getEyeHeight();
	}

	@Override
	public double getEyeHeight(boolean arg0) {
		return p.getEyeHeight();
	}

	@Override
	public Location getEyeLocation() {
		return p.getEyeLocation();
	}

	@Override
	public Player getKiller() {
		return p.getKiller();
	}

	@Override
	public double getLastDamage() {
		return p.getLastDamage();
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<Block> getLastTwoTargetBlocks(HashSet<Byte> arg0, int arg1) {
		return p.getLastTwoTargetBlocks(arg0, arg1);
	}

	@Override
	public List<Block> getLastTwoTargetBlocks(Set<Material> arg0, int arg1) {
		return p.getLastTwoTargetBlocks(arg0, arg1);
	}

	@Override
	public Entity getLeashHolder() throws IllegalStateException {
		return p.getLeashHolder();
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<Block> getLineOfSight(HashSet<Byte> arg0, int arg1) {
		return p.getLineOfSight(arg0, arg1);
	}

	@Override
	public List<Block> getLineOfSight(Set<Material> arg0, int arg1) {
		return p.getLineOfSight(arg0, arg1);
	}

	@Override
	public int getMaximumAir() {
		return p.getMaximumAir();
	}

	@Override
	public int getMaximumNoDamageTicks() {
		return p.getMaximumNoDamageTicks();
	}

	@Override
	public int getNoDamageTicks() {
		return p.getNoDamageTicks();
	}

	@Override
	public int getRemainingAir() {
		return p.getRemainingAir();
	}

	@Override
	public boolean getRemoveWhenFarAway() {
		return p.getRemoveWhenFarAway();
	}

	@SuppressWarnings("deprecation")
	@Override
	public Block getTargetBlock(HashSet<Byte> arg0, int arg1) {
		return p.getTargetBlock(arg0, arg1);
	}

	@Override
	public Block getTargetBlock(Set<Material> arg0, int arg1) {
		return p.getTargetBlock(arg0, arg1);
	}

	@Override
	public boolean hasLineOfSight(Entity arg0) {
		return p.hasLineOfSight(arg0);
	}

	@Override
	public boolean hasPotionEffect(PotionEffectType arg0) {
		return p.hasPotionEffect(arg0);
	}

	@Override
	public boolean isLeashed() {
		return p.isLeashed();
	}

	@Override
	public void removePotionEffect(PotionEffectType arg0) {
		p.removePotionEffect(arg0);
	}

	@Override
	public void setCanPickupItems(boolean arg0) {
		p.setCanPickupItems(arg0);
	}

	@Override
	public void setLastDamage(double arg0) {
		p.setLastDamage(arg0);
	}

	@Override
	public boolean setLeashHolder(Entity arg0) {
		return p.setLeashHolder(arg0);
	}

	@Override
	public void setMaximumAir(int arg0) {
		p.setMaximumAir(arg0);
	}

	@Override
	public void setMaximumNoDamageTicks(int arg0) {
		p.setMaximumNoDamageTicks(arg0);
	}

	@Override
	public void setNoDamageTicks(int arg0) {
		p.setNoDamageTicks(arg0);
	}

	@Override
	public void setRemainingAir(int arg0) {
		p.setRemainingAir(arg0);
	}

	@Override
	public void setRemoveWhenFarAway(boolean arg0) {
		p.setRemoveWhenFarAway(arg0);
	}

	@SuppressWarnings("deprecation")
	@Override
	public Arrow shootArrow() {
		return p.shootArrow();
	}

	@SuppressWarnings("deprecation")
	@Override
	public Egg throwEgg() {
		return p.throwEgg();
	}

	@SuppressWarnings("deprecation")
	@Override
	public Snowball throwSnowball() {
		return p.throwSnowball();
	}

	@Override
	public boolean eject() {
		return p.eject();
	}

	@Override
	public String getCustomName() {
		return p.getCustomName();
	}

	@Override
	public int getEntityId() {
		return p.getEntityId();
	}

	@Override
	public float getFallDistance() {
		return p.getFallDistance();
	}

	@Override
	public int getFireTicks() {
		return p.getFireTicks();
	}

	@Override
	public EntityDamageEvent getLastDamageCause() {
		return p.getLastDamageCause();
	}

	@Override
	public Location getLocation() {
		return p.getLocation();
	}

	@Override
	public Location getLocation(Location arg0) {
		return p.getLocation(arg0);
	}

	@Override
	public int getMaxFireTicks() {
		return p.getMaxFireTicks();
	}

	@Override
	public List<Entity> getNearbyEntities(double arg0, double arg1, double arg2) {
		return p.getNearbyEntities(arg0, arg1, arg2);
	}

	@Override
	public Entity getPassenger() {
		return p.getPassenger();
	}

	@Override
	public Server getServer() {
		return p.getServer();
	}

	@Override
	public int getTicksLived() {
		return p.getTicksLived();
	}

	@Override
	public EntityType getType() {
		return p.getType();
	}

	@Override
	public UUID getUniqueId() {
		return p.getUniqueId();
	}

	@Override
	public Entity getVehicle() {
		return p.getVehicle();
	}

	@Override
	public Vector getVelocity() {
		return p.getVelocity();
	}

	@Override
	public World getWorld() {
		return p.getWorld();
	}

	@Override
	public boolean isCustomNameVisible() {
		return p.isCustomNameVisible();
	}

	@Override
	public boolean isDead() {
		return p.isDead();
	}

	@Override
	public boolean isEmpty() {
		return p.isEmpty();
	}

	@Override
	public boolean isInsideVehicle() {
		return p.isInsideVehicle();
	}

	@Override
	public boolean isValid() {
		return p.isValid();
	}

	@Override
	public boolean leaveVehicle() {
		return p.leaveVehicle();
	}

	@Override
	public void playEffect(EntityEffect arg0) {
		p.playEffect(arg0);
	}

	@Override
	public void remove() {
		p.remove();
	}

	@Override
	public void setCustomName(String arg0) {
		p.setCustomName(arg0);
	}

	@Override
	public void setCustomNameVisible(boolean arg0) {
		p.setCustomNameVisible(arg0);
	}

	@Override
	public void setFallDistance(float arg0) {
		p.setFallDistance(arg0);
	}

	@Override
	public void setFireTicks(int arg0) {
		p.setFireTicks(arg0);
	}

	@Override
	public void setLastDamageCause(EntityDamageEvent arg0) {
		p.setLastDamageCause(arg0);
	}

	@Override
	public boolean setPassenger(Entity arg0) {
		return p.setPassenger(arg0);
	}

	@Override
	public void setTicksLived(int arg0) {
		p.setTicksLived(arg0);
	}

	@Override
	public void setVelocity(Vector arg0) {
		p.setVelocity(arg0);
	}

	@Override
	public boolean teleport(Location arg0) {
		return p.teleport(arg0);
	}

	@Override
	public boolean teleport(Entity arg0) {
		return p.teleport(arg0);
	}

	@Override
	public boolean teleport(Location arg0, TeleportCause arg1) {
		return p.teleport(arg0, arg1);
	}

	@Override
	public boolean teleport(Entity arg0, TeleportCause arg1) {
		return p.teleport(arg0, arg1);
	}

	@Override
	public List<MetadataValue> getMetadata(String arg0) {
		return p.getMetadata(arg0);
	}

	@Override
	public boolean hasMetadata(String arg0) {
		return p.hasMetadata(arg0);
	}

	@Override
	public void removeMetadata(String arg0, Plugin arg1) {
		p.removeMetadata(arg0, arg1);
	}

	@Override
	public void setMetadata(String arg0, MetadataValue arg1) {
		p.setMetadata(arg0, arg1);
	}

	@Override
	public void sendMessage(String arg0) {
		p.sendMessage(arg0);
	}

	@Override
	public void sendMessage(String[] arg0) {
		p.sendMessage(arg0);
	}

	@Override
	public PermissionAttachment addAttachment(Plugin arg0) {
		return p.addAttachment(arg0);
	}

	@Override
	public PermissionAttachment addAttachment(Plugin arg0, int arg1) {
		return p.addAttachment(arg0, arg1);
	}

	@Override
	public PermissionAttachment addAttachment(Plugin arg0, String arg1, boolean arg2) {
		return p.addAttachment(arg0, arg1, arg2);
	}

	@Override
	public PermissionAttachment addAttachment(Plugin arg0, String arg1, boolean arg2, int arg3) {
		return addAttachment(arg0, arg1, arg2, arg3);
	}

	@Override
	public Set<PermissionAttachmentInfo> getEffectivePermissions() {
		return p.getEffectivePermissions();
	}

	@Override
	public boolean hasPermission(String arg0) {
		return p.hasPermission(arg0);
	}

	@Override
	public boolean hasPermission(Permission arg0) {
		return p.hasPermission(arg0);
	}

	@Override
	public boolean isPermissionSet(String arg0) {
		return p.isPermissionSet(arg0);
	}

	@Override
	public boolean isPermissionSet(Permission arg0) {
		return p.isPermissionSet(arg0);
	}

	@Override
	public void recalculatePermissions() {
		p.recalculatePermissions();
	}

	@Override
	public void removeAttachment(PermissionAttachment arg0) {
		this.p.removeAttachment(arg0);
	}

	@Override
	public boolean isOp() {
		return this.p.isOp();
	}

	@Override
	public void setOp(boolean arg0) {
		this.p.setOp(arg0);
	}

	@Override
	public void damage(double arg0) {
		this.p.damage(arg0);
	}

	@Override
	public void damage(double arg0, Entity arg1) {
		this.p.damage(arg0, arg1);
	}

	@Override
	public double getHealth() {
		return this.p.getHealth();
	}

	@Override
	public double getMaxHealth() {
		return this.p.getMaxHealth();
	}

	@Override
	public void resetMaxHealth() {
		this.p.resetMaxHealth();
	}

	@Override
	public void setHealth(double arg0) {
		this.p.setHealth(arg0);;
	}

	@Override
	public void setMaxHealth(double arg0) {
		this.p.setMaxHealth(arg0);
	}

	@Override
	public <T extends Projectile> T launchProjectile(Class<? extends T> arg0) {
		return this.p.launchProjectile(arg0);
	}

	@Override
	public <T extends Projectile> T launchProjectile(Class<? extends T> arg0, Vector arg1) {
		return this.p.launchProjectile(arg0, arg1);
	}

	@Override
	public void abandonConversation(Conversation arg0) {
		this.p.abandonConversation(arg0);
	}

	@Override
	public void abandonConversation(Conversation arg0, ConversationAbandonedEvent arg1) {
		this.p.abandonConversation(arg0, arg1);
	}

	@Override
	public void acceptConversationInput(String arg0) {
		this.p.acceptConversationInput(arg0);
	}

	@Override
	public boolean beginConversation(Conversation arg0) {
		return this.p.beginConversation(arg0);
	}

	@Override
	public boolean isConversing() {
		return this.p.isConversing();
	}

	@Override
	public long getFirstPlayed() {
		return this.p.getFirstPlayed();
	}

	@Override
	public long getLastPlayed() {
		return this.p.getLastPlayed();
	}

	@Override
	public Player getPlayer() {
		return this.p.getPlayer();
	}

	@Override
	public boolean hasPlayedBefore() {
		return this.p.hasPlayedBefore();
	}

	@Override
	public boolean isBanned() {
		return this.p.isBanned();
	}

	@Override
	public boolean isOnline() {
		return this.p.isOnline();
	}

	@Override
	public boolean isWhitelisted() {
		return this.p.isWhitelisted();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void setBanned(boolean arg0) {
		this.p.setBanned(arg0);
	}

	@Override
	public void setWhitelisted(boolean arg0) {
		this.p.setWhitelisted(arg0);
	}

	@Override
	public Map<String, Object> serialize() {
		return this.p.serialize();
	}

	@Override
	public Set<String> getListeningPluginChannels() {
		return p.getListeningPluginChannels();
	}

	@Override
	public void sendPluginMessage(Plugin arg0, String arg1, byte[] arg2) {
		p.sendPluginMessage(arg0, arg1, arg2);
	}

	@Override
	public void awardAchievement(Achievement arg0) {
		p.awardAchievement(arg0);
	}

	@Override
	public boolean canSee(Player arg0) {
		return p.canSee(arg0);
	}

	@Override
	public void chat(String arg0) {
		p.chat(arg0);
	}

	@Override
	public void decrementStatistic(Statistic arg0) throws IllegalArgumentException {
		p.decrementStatistic(arg0);
	}

	@Override
	public void decrementStatistic(Statistic arg0, int arg1) throws IllegalArgumentException {
		p.decrementStatistic(arg0, arg1);
	}

	@Override
	public void decrementStatistic(Statistic arg0, Material arg1) throws IllegalArgumentException {
		p.decrementStatistic(arg0, arg1);
	}

	@Override
	public void decrementStatistic(Statistic arg0, EntityType arg1) throws IllegalArgumentException {
		p.decrementStatistic(arg0, arg1);
	}

	@Override
	public void decrementStatistic(Statistic arg0, Material arg1, int arg2) throws IllegalArgumentException {
		p.decrementStatistic(arg0, arg1, arg2);
	}

	@Override
	public void decrementStatistic(Statistic arg0, EntityType arg1, int arg2) {
		p.decrementStatistic(arg0, arg1, arg2);
	}

	@Override
	public InetSocketAddress getAddress() {
		return p.getAddress();
	}

	@Override
	public boolean getAllowFlight() {
		return p.getAllowFlight();
	}

	@Override
	public Location getBedSpawnLocation() {
		return p.getBedSpawnLocation();
	}

	@Override
	public Location getCompassTarget() {
		return p.getCompassTarget();
	}

	@Override
	public String getDisplayName() {
		return p.getDisplayName();
	}

	@Override
	public float getExhaustion() {
		return p.getExhaustion();
	}

	@Override
	public float getExp() {
		return p.getExp();
	}

	@Override
	public float getFlySpeed() {
		return p.getFlySpeed();
	}

	@Override
	public int getFoodLevel() {
		return p.getFoodLevel();
	}

	@Override
	public double getHealthScale() {
		return p.getHealthScale();
	}

	@Override
	public int getLevel() {
		return p.getLevel();
	}

	@Override
	public String getPlayerListName() {
		return p.getPlayerListName();
	}

	@Override
	public long getPlayerTime() {
		return p.getPlayerTime();
	}

	@Override
	public long getPlayerTimeOffset() {
		return p.getPlayerTimeOffset();
	}

	@Override
	public WeatherType getPlayerWeather() {
		return p.getPlayerWeather();
	}

	@Override
	public float getSaturation() {
		return p.getSaturation();
	}

	@Override
	public Scoreboard getScoreboard() {
		return p.getScoreboard();
	}

	@Override
	public Entity getSpectatorTarget() {
		return p.getSpectatorTarget();
	}

	@Override
	public int getStatistic(Statistic arg0) throws IllegalArgumentException {
		return p.getStatistic(arg0);
	}

	@Override
	public int getStatistic(Statistic arg0, Material arg1) throws IllegalArgumentException {
		return p.getStatistic(arg0, arg1);
	}

	@Override
	public int getStatistic(Statistic arg0, EntityType arg1) throws IllegalArgumentException {
		return p.getStatistic(arg0, arg1);
	}

	@Override
	public int getTotalExperience() {
		return p.getTotalExperience();
	}

	@Override
	public float getWalkSpeed() {
		return p.getWalkSpeed();
	}

	@Override
	public void giveExp(int arg0) {
		p.giveExp(arg0);
	}

	@Override
	public void giveExpLevels(int arg0) {
		p.giveExpLevels(arg0);
	}

	@Override
	public boolean hasAchievement(Achievement arg0) {
		return p.hasAchievement(arg0);
	}

	@Override
	public void hidePlayer(Player arg0) {
		p.hidePlayer(arg0);
	}

	@Override
	public void incrementStatistic(Statistic arg0) throws IllegalArgumentException {
		p.incrementStatistic(arg0);
	}

	@Override
	public void incrementStatistic(Statistic arg0, int arg1) throws IllegalArgumentException {
		p.incrementStatistic(arg0, arg1);
	}

	@Override
	public void incrementStatistic(Statistic arg0, Material arg1) throws IllegalArgumentException {
		p.incrementStatistic(arg0, arg1);
	}

	@Override
	public void incrementStatistic(Statistic arg0, EntityType arg1) throws IllegalArgumentException {
		p.incrementStatistic(arg0, arg1);
	}

	@Override
	public void incrementStatistic(Statistic arg0, Material arg1, int arg2) throws IllegalArgumentException {
		p.incrementStatistic(arg0, arg1, arg2);
	}

	@Override
	public void incrementStatistic(Statistic arg0, EntityType arg1, int arg2) throws IllegalArgumentException {
		p.incrementStatistic(arg0, arg1, arg2);
	}

	@Override
	public boolean isFlying() {
		return p.isFlying();
	}

	@Override
	public boolean isHealthScaled() {
		return p.isHealthScaled();
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean isOnGround() {
		return p.isOnGround();
	}

	@Override
	public boolean isPlayerTimeRelative() {
		return p.isPlayerTimeRelative();
	}

	@Override
	public boolean isSleepingIgnored() {
		return p.isSleeping();
	}

	@Override
	public boolean isSneaking() {
		return p.isSneaking();
	}

	@Override
	public boolean isSprinting() {
		return p.isSprinting();
	}

	@Override
	public void kickPlayer(String arg0) {
		p.kickPlayer(arg0);
	}

	@Override
	public void loadData() {
		p.loadData();
	}

	@Override
	public boolean performCommand(String arg0) {
		return p.performCommand(arg0);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void playEffect(Location arg0, Effect arg1, int arg2) {
		p.playEffect(arg0, arg1, arg2);
	}

	@Override
	public <T> void playEffect(Location arg0, Effect arg1, T arg2) {
		p.playEffect(arg0, arg1, arg2);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void playNote(Location arg0, byte arg1, byte arg2) {
		p.playNote(arg0, arg1, arg2);
	}

	@Override
	public void playNote(Location arg0, Instrument arg1, Note arg2) {
		p.playNote(arg0, arg1, arg2);
	}

	@Override
	public void playSound(Location arg0, Sound arg1, float arg2, float arg3) {
		p.playSound(arg0, arg1, arg2, arg3);
	}

	@Override
	public void playSound(Location arg0, String arg1, float arg2, float arg3) {
		p.playSound(arg0, arg1, arg2, arg3);
	}

	@Override
	public void removeAchievement(Achievement arg0) {
		p.removeAchievement(arg0);
	}

	@Override
	public void resetPlayerTime() {
		p.resetPlayerTime();
	}

	@Override
	public void resetPlayerWeather() {
		p.resetPlayerWeather();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void resetTitle() {
		p.resetTitle();
	}

	@Override
	public void saveData() {
		p.saveData();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void sendBlockChange(Location arg0, Material arg1, byte arg2) {
		p.sendBlockChange(arg0, arg1, arg2);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void sendBlockChange(Location arg0, int arg1, byte arg2) {
		p.sendBlockChange(arg0, arg1, arg2);
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean sendChunkChange(Location arg0, int arg1, int arg2, int arg3, byte[] arg4) {
		return p.sendChunkChange(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void sendMap(MapView arg0) {
		p.sendMap(arg0);
	}

	@Override
	public void sendRawMessage(String arg0) {
		p.sendRawMessage(arg0);
	}

	@Override
	public void sendSignChange(Location arg0, String[] arg1) throws IllegalArgumentException {
		p.sendSignChange(arg0, arg1);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void sendTitle(String arg0, String arg1) {
		p.sendTitle(arg0, arg1);
	}

	@Override
	public void setAllowFlight(boolean arg0) {
		p.setAllowFlight(arg0);
	}

	@Override
	public void setBedSpawnLocation(Location arg0) {
		p.setBedSpawnLocation(arg0);
	}

	@Override
	public void setBedSpawnLocation(Location arg0, boolean arg1) {
		p.setBedSpawnLocation(arg0, arg1);
	}

	@Override
	public void setCompassTarget(Location arg0) {
		p.setCompassTarget(arg0);
	}

	@Override
	public void setDisplayName(String arg0) {
		p.setDisplayName(arg0);
	}

	@Override
	public void setExhaustion(float arg0) {
		p.setExhaustion(arg0);
	}

	@Override
	public void setExp(float arg0) {
		p.setExp(arg0);
	}

	@Override
	public void setFlySpeed(float arg0) throws IllegalArgumentException {
		p.setFlySpeed(arg0);
	}

	@Override
	public void setFlying(boolean arg0) {
		p.setFlying(arg0);
	}

	@Override
	public void setFoodLevel(int arg0) {
		p.setFoodLevel(arg0);
	}

	@Override
	public void setHealthScale(double arg0) throws IllegalArgumentException {
		p.setHealthScale(arg0);
	}

	@Override
	public void setHealthScaled(boolean arg0) {
		p.setHealthScaled(arg0);
	}

	@Override
	public void setLevel(int arg0) {
		p.setLevel(arg0);
	}

	@Override
	public void setPlayerListName(String arg0) {
		p.setPlayerListName(arg0);
	}

	@Override
	public void setPlayerTime(long arg0, boolean arg1) {
		p.setPlayerTime(arg0, arg1);
	}

	@Override
	public void setPlayerWeather(WeatherType arg0) {
		p.setPlayerWeather(arg0);
	}

	@Override
	public void setResourcePack(String arg0) {
		p.setResourcePack(arg0);
	}

	@Override
	public void setSaturation(float arg0) {
		p.setSaturation(arg0);
	}

	@Override
	public void setScoreboard(Scoreboard arg0) throws IllegalArgumentException, IllegalStateException {
		p.setScoreboard(arg0);
	}

	@Override
	public void setSleepingIgnored(boolean arg0) {
		p.setSleepingIgnored(arg0);
	}

	@Override
	public void setSneaking(boolean arg0) {
		p.setSneaking(arg0);
	}

	@Override
	public void setSpectatorTarget(Entity arg0) {
		p.setSpectatorTarget(arg0);
	}

	@Override
	public void setSprinting(boolean arg0) {
		p.setSprinting(arg0);
	}

	@Override
	public void setStatistic(Statistic arg0, int arg1) throws IllegalArgumentException {
		p.setStatistic(arg0, arg1);
	}

	@Override
	public void setStatistic(Statistic arg0, Material arg1, int arg2) throws IllegalArgumentException {
		p.setStatistic(arg0, arg1, arg2);
	}

	@Override
	public void setStatistic(Statistic arg0, EntityType arg1, int arg2) {
		p.setStatistic(arg0, arg1, arg2);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void setTexturePack(String arg0) {
		p.setTexturePack(arg0);
	}

	@Override
	public void setTotalExperience(int arg0) {
		p.setTotalExperience(arg0);
	}

	@Override
	public void setWalkSpeed(float arg0) throws IllegalArgumentException {
		p.setWalkSpeed(arg0);
	}

	@Override
	public void showPlayer(Player arg0) {
		p.showPlayer(arg0);
	}

	@Override
	public Spigot spigot() {
		return p.spigot();
	}

	@Override
	public void updateInventory() {
		p.updateInventory();
	}

	
}
