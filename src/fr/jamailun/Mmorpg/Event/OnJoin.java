package fr.jamailun.Mmorpg.Event;

import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import fr.jamailun.Mmorpg.Classes.*;
import net.minecraft.server.v1_8_R3.Blocks;
import net.minecraft.server.v1_8_R3.EntityArmorStand;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.EntityVillager;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityLiving;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.WorldServer;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityEquipment;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle.EnumTitleAction;

public class OnJoin implements Listener {

	public OnJoin(fr.jamailun.Mmorpg.Main main) {}

	@EventHandler
	public void OnJoinServer(PlayerJoinEvent e) {
		Player pT = e.getPlayer();
		if(pT.getWorld() != fr.jamailun.Mmorpg.Main.monde) return;
		e.setJoinMessage("");
		JoinMmorpg(pT);
	}
	
	@EventHandler
	public void OnJoinWorldMmorpg(PlayerChangedWorldEvent e) {
		if(e.getFrom() == fr.jamailun.Mmorpg.Main.monde) return;
		if(e.getPlayer().getWorld() != fr.jamailun.Mmorpg.Main.monde) return;
		JoinMmorpg(e.getPlayer());
	}
	
	private void JoinMmorpg(Player pT) {
		if(fr.jamailun.Mmorpg.Main.playerList.contains(pT.getUniqueId().toString())) {
			MmoPlayer p = new MmoPlayer(pT, true);
			try {fr.jamailun.Mmorpg.Main.mpm.addMmoPlayer(p);} catch (IOException e1) {
				fr.jamailun.Mmorpg.ErrorReporter.ImpossibleAdding(p, e1);
			}
			fr.jamailun.Mmorpg.Main.playerList.set(p.getUuid(), p.getName());
		} else {
			//nouveau joueur
			MmoPlayer p = new MmoPlayer(pT, false);
			fr.jamailun.Mmorpg.Main.playerList.set(p.getUuid(), p.getName());
			try {fr.jamailun.Mmorpg.Main.mpm.addMmoPlayer(p);} catch (IOException e1) {
				fr.jamailun.Mmorpg.ErrorReporter.ImpossibleAdding(p, e1);
			}
		}
		PacketPlayOutTitle title = new PacketPlayOutTitle(EnumTitleAction.TITLE, ChatSerializer.a("{\"text\":\"Bienvenue\",\"color\":\"red\",\"bold\"=\"true\"}"), 10, 30, 20);
        PacketPlayOutTitle subtitle = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, ChatSerializer.a("{\"text\":\"sur le \",\"color\":\"red\",\"bold\"=\"false\",\"extra\":[{\"text\":\"MMORPG\",\"color\":\"dark_green\",\"bold\"=\"true\"},{\"text\":\" !\"}]} "), 30, 40, 10);
        ((CraftPlayer) pT).getHandle().playerConnection.sendPacket(title);
        ((CraftPlayer) pT).getHandle().playerConnection.sendPacket(subtitle);
		
        Location loc = pT.getLocation();
		WorldServer world = ((CraftWorld)loc.getWorld()).getHandle();
		EntityPlayer nmsPlayer = ((CraftPlayer)pT).getHandle();
		
		EntityVillager v = new EntityVillager(world);
		v.setLocation(4.5, 5, -11.5, 0, 0);
		PacketPlayOutSpawnEntityLiving packetV = new PacketPlayOutSpawnEntityLiving(v);
		nmsPlayer.playerConnection.sendPacket(packetV);
		
		EntityArmorStand a = new EntityArmorStand(world);
		a.setLocation(4.5, 5, -11.5, 0, 0);
		a.setInvisible(true);
		a.setGravity(false);
		PacketPlayOutSpawnEntityLiving packetA1 = new PacketPlayOutSpawnEntityLiving(a);
		nmsPlayer.playerConnection.sendPacket(packetA1);
		PacketPlayOutEntityEquipment packetA2 = new PacketPlayOutEntityEquipment(a.getId(), 0, new net.minecraft.server.v1_8_R3.ItemStack(Blocks.MONSTER_EGG, 1, (byte) 3));
		nmsPlayer.playerConnection.sendPacket(packetA2);
		
		FileConfiguration qL = fr.jamailun.Mmorpg.Main.QuestsList;
		for(String name : qL.getStringList("npcs.list")) {
			Npc npc = new Npc(name);
			npc.createNpc(pT);
		}
		
		//PacketPlayOutResourcePackSend rss = new PacketPlayOutResourcePackSend();
		
	}
}
