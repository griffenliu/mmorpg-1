package fr.jamailun.Mmorpg.Classes;

import org.bukkit.configuration.file.FileConfiguration;

public class QuestStep {

	private Quest quest;
	private String type;
	private String npcName;
	private String data; //soit un dialog, soit un bring, soit un kill.
	private int number;
	
	public QuestStep(Quest quest, int nStep) {
		this.quest = quest;
		FileConfiguration qL = fr.jamailun.Mmorpg.Main.QuestsList;
			int i = quest.getConfigNumber();
		this.type = qL.getString("quests."+i+".steps."+nStep+".type");
		this.npcName = qL.getString("quests."+i+".steps."+nStep+".npc");
		this.data = qL.getString("quests."+i+".steps."+nStep+".data");
		this.number = nStep;
	}
	
	//GETTERS
	public String getType() {
		return this.type;
	}
	
	public String getNpcName() {
		return this.npcName;
	}
	
	public String getData() {
		return this.data;
	}
	
	public int getStepNumber() {
		return this.number;
	}
	
	public Quest getQuest() {
		return this.quest;
	}
}
