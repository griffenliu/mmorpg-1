package fr.jamailun.Mmorpg.Event;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import fr.jamailun.Mmorpg.Classes.*;
import net.minecraft.server.v1_8_R3.EntityArmorStand;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityLiving;
import net.minecraft.server.v1_8_R3.WorldServer;

@SuppressWarnings("deprecation")
public class OnBreak implements Listener {
	public OnBreak(fr.jamailun.Mmorpg.Main main) {}
	
	public static void ReplaceBlock(int sec, Block b, final Location loc) {
		
		final Material type = b.getType();
		final byte data = (byte) b.getData();
		
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(fr.jamailun.Mmorpg.Main.jp, new Runnable() {
			public void run() {
				Block fB = loc.getBlock();
				fB.setType(type);
				fB.setData(data);
			}
		},sec*20L);
	}
	
	public static void DisplayXp(final Location loc, final int xp, Player pp) {
		
		final World w = loc.getWorld();
		double x = loc.getX();x += 0.5;
		double y = loc.getY();y -= 1.75;
		double z = loc.getZ();z += 0.5;
		
		Location location = new Location(w, x, y, z);
		WorldServer world = ((CraftWorld)location.getWorld()).getHandle();
		final EntityArmorStand armorStand = new EntityArmorStand(world);
		armorStand.setLocation(x, y, z, loc.getYaw(), loc.getPitch());
		armorStand.setCustomName("§a§l[+"+xp+"xp]");
		armorStand.setCustomNameVisible(true);
		armorStand.setGravity(false);
		armorStand.setInvisible(true);
		
		PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving(armorStand);
		final EntityPlayer nmsPlayer = ((CraftPlayer)pp).getHandle();
		nmsPlayer.playerConnection.sendPacket(packet);
		final int id = armorStand.getId();
		
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(fr.jamailun.Mmorpg.Main.jp, new Runnable() {
			public void run() {
				PacketPlayOutEntityDestroy pack = new PacketPlayOutEntityDestroy(id);
				nmsPlayer.playerConnection.sendPacket(pack);
			}
		},10L);		
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e){
		if(e.getBlock().getWorld() != fr.jamailun.Mmorpg.Main.monde) return;
		if(e.getPlayer().getGameMode() == GameMode.CREATIVE) return;
		FileConfiguration config = fr.jamailun.Mmorpg.Main.chunkList;
		Chunk c = e.getBlock().getLocation().getChunk();
		String s = ""+c.getX()+","+c.getZ();
		if(config.contains(s)) {
			if(config.getString(s).equalsIgnoreCase("safe")) {
				e.setCancelled(true);
				e.getPlayer().sendMessage(fr.jamailun.Mmorpg.Main.prefix+"§cN'essaie pas de détruire la ville !");
				return;
			}
		}		
		
		MmoPlayer p = fr.jamailun.Mmorpg.Main.mpm.getPlayer(e.getPlayer());
		Block b = e.getBlock();
		
		if(fr.jamailun.Ressources.MineurBlockType.isMineurType(b.getType())) {
			Job mineur = p.getJob("mineur");
			fr.jamailun.Mmorpg.JobsEffects.Mineur.BreakBloc(p, mineur, b, e);
		}
		
		if(fr.jamailun.Ressources.BucheronBlockType.isBucheronType(b.getType())) {
			Job bucheron = p.getJob("bucheron");
			fr.jamailun.Mmorpg.JobsEffects.Bucheron.BreakBloc(p, bucheron, b, e);
		}
		
		if(fr.jamailun.Ressources.PaysanBlockType.isPaysanType(b.getTypeId())) {
			Job paysan = p.getJob("paysan");
			fr.jamailun.Mmorpg.JobsEffects.Paysan.BreakBloc(p, paysan, b, e);
		}
		
		if(b.getType() == Material.LEAVES || b.getType() == Material.LEAVES_2) {
			ReplaceBlock(20, b, b.getLocation());
			b.breakNaturally(null);
			return;
		}
		e.setCancelled(true);
	}
	
}
