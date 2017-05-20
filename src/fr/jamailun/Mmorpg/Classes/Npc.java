package fr.jamailun.Mmorpg.Classes;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.EntityVillager;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityLiving;
import net.minecraft.server.v1_8_R3.WorldServer;

public class Npc {

	private String name;
	private int profession;
	private Location loc;
	
	public Npc(String name) {
		FileConfiguration qL = fr.jamailun.Mmorpg.Main.QuestsList;
		this.name = name;
		
		this.profession = qL.getInt("npcs."+name+".profession");
		
		double x = qL.getDouble("npcs."+name+".pos.x");
		double y = qL.getDouble("npcs."+name+".pos.y");
		double z = qL.getDouble("npcs."+name+".pos.z");
		double w = qL.getDouble("npcs."+name+".pos.w");
		double p = qL.getDouble("npcs."+name+".pos.p");
		this.loc = new Location(Bukkit.getServer().getWorld("mmorpg"),x,y,z);
		this.loc.setYaw((float) p);
		this.loc.setPitch((float) w);
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getProfession() {
		return this.profession;
	}
	
	public Location getLocation() {
		return this.loc;
	}
	
	@SuppressWarnings("deprecation")
	public void createNpc(Player p) {
		Villager vi = (Villager) Bukkit.getServer().getWorld("mmorpg").spawnCreature(this.loc, EntityType.VILLAGER);
		vi.setCustomName("§"+fr.jamailun.Mmorpg.Main.QuestsList.getString("npcs."+this.name+".color")+this.name);
		vi.setAdult();
		
		WorldServer world = ((CraftWorld)loc.getWorld()).getHandle();
		EntityPlayer nmsPlayer = ((CraftPlayer)p).getHandle();
		EntityVillager v = new EntityVillager(world);
		v.setCustomName("§"+fr.jamailun.Mmorpg.Main.QuestsList.getString("npcs."+this.name+".color")+this.name);
		v.setInvisible(false);
		v.setProfession(this.profession);
		v.setPositionRotation(this.loc.getX(), this.loc.getY(), this.loc.getZ(), this.loc.getPitch(), this.loc.getYaw());
		
		PacketPlayOutSpawnEntityLiving packetV = new PacketPlayOutSpawnEntityLiving(v);
		nmsPlayer.playerConnection.sendPacket(packetV);
	}
	
}
