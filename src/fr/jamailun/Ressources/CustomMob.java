package fr.jamailun.Ressources;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Monster;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;

@SuppressWarnings("deprecation")
public enum CustomMob {

	// DISPLAY-NAME, LEVEL, ENTITY-TYPE, VIE, ID-ENVIRONEMENT-DE-SPAWN
	// [[EQUIPEMENT(arme->bottes)]],
	// XP-DROP, LOOT{TYPE, CHANCE}[1-5]
	VAGABOND("§8Vagabond", 1, EntityType.ZOMBIE, 10, 1,
			CustomEquipment.M_BATON, CustomTete.VAGABOND, null, null, null,
			5, CustomItem.BATON, 75, null, 0, null, 0, null, 0, null, 0
	),
	ZOMBIE_ERRANT("§8Zombie errant", 2, EntityType.ZOMBIE, 20, 1,
			CustomEquipment.M_E_B1, CustomTete.ZOMBIE1, CustomEquipment.M_2_C1, null, null,
			75, CustomItem.BATON, 75, null, 0, null, 0, null, 0, null, 0
	),
	
	
	;
	
	public String name;
	public int lvl;
	public EntityType entityType;
	public double vie;
	public int idTypeSpawn;
	
	public CustomItem arme;
	public CustomTete tete;
	public CustomEquipment plastron;
	public CustomEquipment jambes;
	public CustomEquipment pieds;
	
	public int lootXp;
	public CustomItem loot1;
	public double drop1;
	public CustomItem loot2;
	public double drop2;
	public CustomItem loot3;
	public double drop3;
	public CustomItem loot4;
	public double drop4;
	public CustomItem loot5;
	public double drop5;
	
	private CustomMob(
			String name, int lvl, EntityType entityType, double vie, int idTypeSpawn,
			CustomEquipment arme, CustomTete tete, CustomEquipment plastron, CustomEquipment jambes, CustomEquipment pieds,
			int lootXp, CustomItem loot1, double drop1, CustomItem loot2, double drop2, CustomItem loot3, double drop3, CustomItem loot4, double drop4, CustomItem loot5, double drop5
			) {
		this.name = name;this.lvl = lvl;this.entityType = entityType; this.vie = vie; this.idTypeSpawn = idTypeSpawn;
		this.tete = tete;this.plastron = plastron;this.jambes = jambes;this.pieds = pieds;
		this.lootXp = lootXp;this.loot1 = loot1;this.drop1 = drop1;this.loot2 = loot2;this.drop2 = drop2;this.loot3 = loot3;this.drop3 = drop3;this.loot4 = loot4;this.drop4 = drop4;this.loot5 = loot5;this.drop5 = drop5;
	}
	
	public static Entity generateEntity(int lvl, Location loc) {
		for(CustomMob mob : values()) {
			if(mob.lvl == lvl)
				return generateEntity(mob.name, loc);
		}
		return null;
	}
	
	public static Monster generateEntity(String name, Location loc) {
		for(CustomMob mob : values()) {
			if(mob.name.equalsIgnoreCase(name)) {
				World w = fr.jamailun.Mmorpg.Main.monde;
				Entity en = w.spawnCreature(loc, mob.entityType);
				en.setCustomName(mob.name +"   §6["+mob.lvl+"]");
				if(en instanceof Zombie) {
					Zombie z = (Zombie) en;
					z.setMaxHealth(mob.vie);
					z.setHealth(mob.vie);
					z.setRemoveWhenFarAway(true);
					z.setBaby(false);
					z.getEquipment().setItemInHandDropChance(0);if(mob.arme == null) z.getEquipment().setItemInHand(new ItemStack(Material.AIR));
					z.getEquipment().setBootsDropChance(0);if(mob.arme == null) z.getEquipment().setBoots(new ItemStack(Material.AIR));
					z.getEquipment().setLeggingsDropChance(0);if(mob.arme == null) z.getEquipment().setLeggings(new ItemStack(Material.AIR));
					z.getEquipment().setChestplateDropChance(0);if(mob.arme == null) z.getEquipment().setChestplate(new ItemStack(Material.AIR));
					z.getEquipment().setHelmetDropChance(0);if(mob.arme == null) z.getEquipment().setHelmet(new ItemStack(Material.AIR));
					if(mob.pieds != null) z.getEquipment().setBoots(fr.jamailun.Ressources.CustomEquipment.getItem(mob.pieds.name));
					if(mob.jambes != null) z.getEquipment().setLeggings(fr.jamailun.Ressources.CustomEquipment.getItem(mob.jambes.name));
					if(mob.plastron != null) z.getEquipment().setChestplate(fr.jamailun.Ressources.CustomEquipment.getItem(mob.plastron.name));
					if(mob.tete != null) z.getEquipment().setHelmet(fr.jamailun.Ressources.CustomTete.createHead(mob.tete));
					if(mob.arme != null) z.getEquipment().setItemInHand(fr.jamailun.Ressources.CustomEquipment.getItem(mob.arme.name));
					return z;
				}
				if(en instanceof Skeleton) {
					Skeleton s = (Skeleton) en;
					s.setMaxHealth(mob.vie);
					s.setHealth(mob.vie);
					s.setRemoveWhenFarAway(true);
					s.getEquipment().setItemInHandDropChance(0);if(mob.arme == null) s.getEquipment().setItemInHand(new ItemStack(Material.AIR));
					s.getEquipment().setBootsDropChance(0);if(mob.arme == null) s.getEquipment().setBoots(new ItemStack(Material.AIR));
					s.getEquipment().setLeggingsDropChance(0);if(mob.arme == null) s.getEquipment().setLeggings(new ItemStack(Material.AIR));
					s.getEquipment().setChestplateDropChance(0);if(mob.arme == null) s.getEquipment().setChestplate(new ItemStack(Material.AIR));
					s.getEquipment().setHelmetDropChance(0);if(mob.arme == null) s.getEquipment().setHelmet(new ItemStack(Material.AIR));
					if(mob.pieds != null) s.getEquipment().setBoots(fr.jamailun.Ressources.CustomEquipment.getItem(mob.pieds.name));
					if(mob.jambes != null) s.getEquipment().setLeggings(fr.jamailun.Ressources.CustomEquipment.getItem(mob.jambes.name));
					if(mob.plastron != null) s.getEquipment().setChestplate(fr.jamailun.Ressources.CustomEquipment.getItem(mob.plastron.name));
					if(mob.tete != null) s.getEquipment().setHelmet(fr.jamailun.Ressources.CustomTete.createHead(mob.tete));
					if(mob.arme != null) s.getEquipment().setItemInHand(fr.jamailun.Ressources.CustomEquipment.getItem(mob.arme.name));
					return s;
				}
				if(en instanceof PigZombie) {
					PigZombie z = (PigZombie) en;
					z.setMaxHealth(mob.vie);
					z.setHealth(mob.vie);
					z.setRemoveWhenFarAway(true);
					z.setAngry(true);
					z.setBaby(false);
					z.setAnger(999);
					z.getEquipment().setItemInHandDropChance(0);if(mob.arme == null) z.getEquipment().setItemInHand(new ItemStack(Material.AIR));
					z.getEquipment().setBootsDropChance(0);if(mob.arme == null) z.getEquipment().setBoots(new ItemStack(Material.AIR));
					z.getEquipment().setLeggingsDropChance(0);if(mob.arme == null) z.getEquipment().setLeggings(new ItemStack(Material.AIR));
					z.getEquipment().setChestplateDropChance(0);if(mob.arme == null) z.getEquipment().setChestplate(new ItemStack(Material.AIR));
					z.getEquipment().setHelmetDropChance(0);if(mob.arme == null) z.getEquipment().setHelmet(new ItemStack(Material.AIR));
					if(mob.pieds != null) z.getEquipment().setBoots(fr.jamailun.Ressources.CustomEquipment.getItem(mob.pieds.name));
					if(mob.jambes != null) z.getEquipment().setLeggings(fr.jamailun.Ressources.CustomEquipment.getItem(mob.jambes.name));
					if(mob.plastron != null) z.getEquipment().setChestplate(fr.jamailun.Ressources.CustomEquipment.getItem(mob.plastron.name));
					if(mob.tete != null) z.getEquipment().setHelmet(fr.jamailun.Ressources.CustomTete.createHead(mob.tete));
					if(mob.arme != null) z.getEquipment().setItemInHand(fr.jamailun.Ressources.CustomEquipment.getItem(mob.arme.name));
					return z;
				}
			}
		}
		return null;
	}
	
	public static CustomMob getByName(String name) {
		for(CustomMob mob : values())
			if(name.contains(mob.name))
				return mob;
		return null;
	}
	
	public static List<CustomMob> getMobsForEnvironement(int en) {
		List<CustomMob> list = new ArrayList<CustomMob>();
		for(CustomMob mob : values())
			if(mob.idTypeSpawn == en)
				list.add(mob);
		return list;
	}
	
	public static boolean isCustomMob(Monster mob) {
		if(mob == null) return false;
		if(mob.getCustomName() == null) return false;
		for(CustomMob m : values())
			if(mob.getCustomName().contains(m.name)) 
				return true;
		return false;
	}
}
