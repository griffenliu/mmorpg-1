package fr.jamailun.Mmorpg.Classes;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;

public class Quest {

	private String name;
	private int lvlMini;
	private int recXp;
	private int recMoney;
	private List<QuestStep> steps;
	private int configNumber;

	public Quest(MmoPlayer p, int pathN) {
		FileConfiguration qL = fr.jamailun.Mmorpg.Main.QuestsList;
		this.name = qL.getString("quests."+pathN+".name");
		this.lvlMini = qL.getInt("quests."+pathN+".lvl-mini");
		this.recXp = qL.getInt("quests."+pathN+".recompenses.xp");
		this.recMoney = qL.getInt("quests."+pathN+".recompenses.money");
		
		this.steps = new ArrayList<QuestStep>();
		for(int i=1; i<=qL.getInt("quests."+pathN+".steps.nb"); i++) {
			this.steps.add(new QuestStep(this, i));
		}
	}
	
	//GETTERS
	public int getConfigNumber() {
		return this.configNumber;
	}
	
	public List<QuestStep> getSteps() {
		return this.steps;
	}
	
	public QuestStep getStep(int n) {
		return this.steps.get(n);
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getLvlMini() {
		return this.lvlMini;
	}
	
	public int getRecompenseXp() {
		return this.recXp;
	}
	
	public int getRecompenseMoney() {
		return this.recMoney;
	}
}
