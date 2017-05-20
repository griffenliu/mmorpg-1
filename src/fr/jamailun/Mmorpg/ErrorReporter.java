package fr.jamailun.Mmorpg;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;

import fr.jamailun.Mmorpg.Classes.MmoPlayer;

public class ErrorReporter {

	public static void ImpossibleAdding(MmoPlayer p, IOException e) {
		ConsoleCommandSender console = Bukkit.getConsoleSender();
		console.sendMessage("§c[ERREUR LOGIQUE MMORPG.JAVA]");
		console.sendMessage("§cRapport d'erreur #1 :");
		console.sendMessage(" ");
		console.sendMessage("§c- Le MmoPlayer §4"+p.getName()+"§c n'a pu etre ajoute au systeme de MmoPlayersManager pour une raison inconnue.");
		if(!fr.jamailun.Mmorpg.Main.playerList.contains(p.getUuid())) {
			console.sendMessage("§cCause propable [1]: Le nouveau jouer ne s'est pas correctement ajoute a la base de donnee. Essayez de:");
			console.sendMessage("§c - Verifier si §4"+fr.jamailun.Mmorpg.Main.playerListFile.getPath()+"§c existe.");
			console.sendMessage("§c - Verifier si §4"+fr.jamailun.Mmorpg.Main.playerListFile.getPath()+"§c contient l'uuid du joueur.");
		} else {
			console.sendMessage("§c - Erreur interne, merci de donner le code ci-dessous au developpeur.");
			e.printStackTrace();
		}
		
	}
	
	public static void ErrorOnMobSpawn(String s) {
		ConsoleCommandSender console = Bukkit.getConsoleSender();
		console.sendMessage("§c[ERREUR DE SPAWN MMORPG.JAVA]");
		console.sendMessage("§cRapport d'erreur #2 :");
		console.sendMessage(" ");
		console.sendMessage("§cLors du spawn d'un monstre, une erreur est survenue.");
		console.sendMessage("§cDans ce cas particulier, l'erreur est due a une erreur");
		console.sendMessage("§cde lecture du fichier "+fr.jamailun.Mmorpg.Main.chunkList.getCurrentPath()+".");
		console.sendMessage("§cCelui-ci a la donnée suivante qui a ete corompue:");
		console.sendMessage("§4 - "+s);
		console.sendMessage("§cEssayez de supprimer la ligne correspondante, apres");
		console.sendMessage("§cavoir stoppe le serveur. Vous pourrez le relancer ensuite.");
	}
	
}
